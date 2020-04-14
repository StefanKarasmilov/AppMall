package com.proyecto.appmall;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

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
import com.proyecto.appmall.common.MyApp;
import com.proyecto.appmall.model.Inicio;
import com.proyecto.appmall.ui.inicio.InicioFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NuevaOfertaFragment extends DialogFragment implements View.OnClickListener {

    private EditText etNombre, etDescripcion;
    private Button btnSubirFoto, btnPublicar, btnCancelar;
    private ProgressBar progressBar;
    private LinearLayout linearLayout;
    private String nombre, descripcion, photoUrl = "", fechaPublicacion;
    private StorageReference storage;
    private FirebaseFirestore db;
    private SimpleDateFormat sdf;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
        storage = FirebaseStorage.getInstance().getReference().child("ofertas");
        db = FirebaseFirestore.getInstance();
        sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
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
        progressBar = view.findViewById(R.id.progressBar);
        linearLayout = view.findViewById(R.id.linearLayout);

        progressBar.setVisibility(View.GONE);

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
                showProgresBar();
                break;
            case R.id.buttonNuevaOfertaPublicar:
                if(!nombre.isEmpty() && !descripcion.isEmpty() && !photoUrl.equals("")){
                    setDataToDatabase();
                    HomeActivity home = (HomeActivity) getActivity();
                    home.refreshInicio();
                    dismiss();
                }else{
                    setErrorText();
                }
                break;
            case R.id.buttonNuevaOfertaCancelar:
                showDialogConfirm();
                break;
        }
    }

    // Método para mostrar el progress bar
    private void showProgresBar() {

        CountDownTimer timer;

        timer = new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                linearLayout.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFinish() {
                progressBar.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
            }
        };
        timer.start();

    }

    private void setDataToDatabase() {

        fechaPublicacion = sdf.format(new Date());

        Inicio inicio = new Inicio(nombre, descripcion, photoUrl, fechaPublicacion);
        db.collection("inicio")
                .document()
                .set(inicio)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MyApp.getContext(), "Se ha publicado correctamente", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MyApp.getContext(), "Se ha producido un error", Toast.LENGTH_SHORT).show();
            }
        });

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

             final StorageReference filePath = storage.child(uri.getLastPathSegment());

             final UploadTask uploadTask = filePath.putFile(uri);

             uploadTask.addOnFailureListener(new OnFailureListener() {
                 @Override
                 public void onFailure(@NonNull Exception e) {
                     String message = e.toString();
                     Toast.makeText(MyApp.getContext(), "Hubo un error en la subida de foto", Toast.LENGTH_SHORT).show();
                     Log.w("Error en subida de foto", message);
                 }
             }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                 @Override
                 public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                     Toast.makeText(MyApp.getContext(), "Foto subida con éxito", Toast.LENGTH_SHORT).show();

                     Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                         @Override
                         public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                             if(!task.isSuccessful()){
                                 throw task.getException();
                             }

                             photoUrl = filePath.getDownloadUrl().toString();
                             return filePath.getDownloadUrl();
                         }
                     }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                         @Override
                         public void onComplete(@NonNull Task<Uri> task) {
                             if(task.isSuccessful()){
                                photoUrl = task.getResult().toString();
                             }
                         }
                     });
                 }
             });

        }

    }

    // Método que comprueba si los campos están vacios
    private void setErrorText() {
        if(nombre.isEmpty()){
            etNombre.setError("Campo requerido");
        }else if(descripcion.isEmpty()) {
            etDescripcion.setError("Campo requerido");
        }else if(photoUrl.equals("")){
            showErrorPhoto();
        }
    }

    // Método que muestra un dialogo en caso de que se cancele la publicación
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

    // Método que muestra un mensaje de error si no se a subido una foto
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
