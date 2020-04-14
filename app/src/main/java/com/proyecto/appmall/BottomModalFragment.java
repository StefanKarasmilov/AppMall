package com.proyecto.appmall;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.navigation.NavigationView;
import com.proyecto.appmall.common.Constantes;

public class BottomModalFragment extends BottomSheetDialogFragment {

    private String nombreBorrar;

    public static BottomModalFragment newInstance(String nombre) {
        BottomModalFragment fragment = new BottomModalFragment();
        Bundle args = new Bundle();
        args.putString(Constantes.ARG_NOMBRE, nombre);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            nombreBorrar = getArguments().getString(Constantes.ARG_NOMBRE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_modal_fragment, container, false);

        final NavigationView nav = v.findViewById(R.id.navigation_view_bottom);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                if(id == R.id.action_delete){
                    borrar(nombreBorrar);
                }else if(id == R.id.action_edit){
                    editar(nombreBorrar);
                }
                return false;
            }
        });

        return v;
    }

    private void editar(String nombreBorrar) {



    }

    private void borrar(String nombreBorrar) {



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
