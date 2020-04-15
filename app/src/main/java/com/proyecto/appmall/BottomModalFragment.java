package com.proyecto.appmall;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.proyecto.appmall.common.Constantes;
import com.proyecto.appmall.common.MyApp;

public class BottomModalFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    private String idBorrar, fragmento, photoUrl;
    private TextView tvEliminar;
    private FirebaseFirestore db;
    private StorageReference photoRef;

    public static BottomModalFragment newInstance(String nombre, String fragmento, String photoUrl) {
        BottomModalFragment fragment = new BottomModalFragment();
        Bundle args = new Bundle();
        args.putString(Constantes.ARG_ID, nombre);
        args.putString(Constantes.ARG_FRAGMENTO, fragmento);
        args.putString(Constantes.ARG_PHOTOURL, photoUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = FirebaseFirestore.getInstance();


        if(getArguments() != null){
            idBorrar = getArguments().getString(Constantes.ARG_ID);
            fragmento = getArguments().getString(Constantes.ARG_FRAGMENTO);
            photoUrl = getArguments().getString(Constantes.ARG_PHOTOURL);
        }

        photoRef = FirebaseStorage.getInstance().getReferenceFromUrl(photoUrl);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_modal_fragment, container, false);

        tvEliminar = v.findViewById(R.id.textViewEliminar);

        tvEliminar.setOnClickListener(this);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id){
            case R.id.textViewEliminar:
                eliminar(fragmento);
                break;
        }

    }

    private void eliminar(String fragmento) {

        switch (fragmento){
            case "inicio":
                eliminarEnInicio();
                break;
            case "tiendas":
                eliminarEnTiendas();
                break;
            case "restaurantes":
                eliminarEnRestaurantes();
                break;
            case "cines":
                eliminarEnCines();
                break;
        }

    }

    private void eliminarEnInicio(){

        // Elimina el documento de la base de datos
        db.collection("inicio")
                .document(idBorrar)
                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(MyApp.getContext(), "Registro eliminado correactamente", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Error al eliminar", e);
            }
        });

        // Función que elimina la foto del Storage
        photoRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.w("Succes", "Foto borrada con éxito");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Error", "Error al borrar la foto");
            }
        });

        dismiss();
    }

    private void eliminarEnTiendas() {

        // Elimina el documento de la base de datos
        db.collection("tiendas")
                .document(idBorrar)
                .delete().addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MyApp.getContext(), "Registro eliminado correactamente", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Error al eliminar", e);
            }
        });

        // Función que elimina la foto del Storage
        photoRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.w("Succes", "Foto borrada con éxito");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Error", "Error al borrar la foto");
            }
        });

        dismiss();
    }

    private void eliminarEnRestaurantes() {

        // Elimina el documento de la base de datos
        db.collection("restaurantes")
                .document(idBorrar)
                .delete().addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MyApp.getContext(), "Registro eliminado correactamente", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Error al eliminar", e);
            }
        });

        // Función que elimina la foto del Storage
        photoRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.w("Succes", "Foto borrada con éxito");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Error", "Error al borrar la foto");
            }
        });

        dismiss();
    }

    private void eliminarEnCines() {

        // Elimina el documento de la base de datos
        db.collection("cines")
                .document(idBorrar)
                .delete().addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MyApp.getContext(), "Registro eliminado correactamente", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Error al eliminar", e);
            }
        });

        // Función que elimina la foto del Storage
        photoRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.w("Succes", "Foto borrada con éxito");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Error", "Error al borrar la foto");
            }
        });

        dismiss();
    }

}
