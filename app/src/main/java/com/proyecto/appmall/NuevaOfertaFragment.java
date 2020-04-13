package com.proyecto.appmall;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.proyecto.appmall.common.Constantes;
import com.proyecto.appmall.model.Inicio;

public class NuevaOfertaFragment extends DialogFragment implements View.OnClickListener {

    private EditText etNombre, etDescripcion;
    private Button btnSubirFoto, btnPublicar, btnCancelar;
    private String nombre, descripcion, photoUrl = "";
    private StorageReference storage;
    private FirebaseFirestore db;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
        storage = FirebaseStorage.getInstance().getReference();
        db = FirebaseFirestore.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.nueva_oferta_full_dialog, container, false);

        etNombre = view.findViewById(R.id.editTextNuevaOfertaNombre);
        etDescripcion = view.findViewById(R.id.editTextNuevaOfertaDescripcion);
        btnSubirFoto = view.findViewById(R.id.buttonNuevaOfertaSubirFoto);
        btnPublicar = view.findViewById(R.id.buttonNuevaOfertaPublicar);
        btnCancelar = view.findViewById(R.id.buttonNuevaOfertaCancelar);

        btnSubirFoto.setOnClickListener(this);
        btnPublicar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
        etNombre.requestFocus();

        return view;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        nombre = etNombre.getText().toString();
        descripcion = etDescripcion.getText().toString();

        switch (id){
            case R.id.buttonNuevaOfertaSubirFoto:
                subirFoto();
                break;
            case R.id.buttonNuevaOfertaPublicar:
                if(!nombre.isEmpty() && !descripcion.isEmpty() && !photoUrl.equals("")){
                    Inicio inicio = new Inicio(nombre, descripcion, photoUrl);
                    db.collection("inicio")
                            .document()
                            .set(inicio)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getContext(), "Se ha publicado correctamente", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getContext(), "Se ha producido un error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    setErrorText();
                }
                break;
            case R.id.buttonNuevaOfertaCancelar:
                showDialogConfirm();
                break;
        }
    }

    private void subirFoto() {

        // Abrimos Intent para seleccionar la imagen de la galeria
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, Constantes.GALLERY_INTENT);

    }

    // Método que comprueba el resultado del intent para subir la foto
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Constantes.GALLERY_INTENT && resultCode == Activity.RESULT_OK){
            Uri uri = data.getData();
            
            final StorageReference filePath = storage.child("ofertas").child(uri.getLastPathSegment());
            
             filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getContext(), "Foto subida con éxito", Toast.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), "Hubo un error", Toast.LENGTH_SHORT).show();
                }
            });

             Task<Task<Uri>> url = filePath.getDownloadUrl().continueWith(new Continuation<Uri, Task<Uri>>() {
                 @Override
                 public Task<Uri> then(@NonNull Task<Uri> task) throws Exception {
                     return filePath.getDownloadUrl();
                 }
             }).addOnCompleteListener(new OnCompleteListener<Task<Uri>>() {
                 @Override
                 public void onComplete(@NonNull Task<Task<Uri>> task) {
                     if(task.isSuccessful()){
                         Task<Uri> uri = task.getResult();
                         Uri u = uri.getResult();
                         if(u == null){
                             return;
                         }else{
                             photoUrl = u.toString();
                         }
                     }
                 }
             });

        }

    }

    private void setErrorText() {
        if(nombre.isEmpty()){
            etNombre.setError("Campo requerido");
        }else if(descripcion.isEmpty()) {
            etDescripcion.setError("Campo requerido");
        }else if(photoUrl.equals("")){
            showErrorPhoto();
        }
    }

    private void showDialogConfirm(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("¿Desea cancelar la publicación?")
                .setTitle("Cancelar publicación");

        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                getDialog().dismiss();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showErrorPhoto(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("Debe subir una foto").setTitle("Error foto");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
