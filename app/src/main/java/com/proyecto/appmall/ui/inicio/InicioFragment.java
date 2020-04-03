package com.proyecto.appmall.ui.inicio;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.proyecto.appmall.R;
import com.proyecto.appmall.model.Inicio;

import java.util.ArrayList;
import java.util.List;

public class InicioFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    RecyclerView recyclerView;
    MyInicioRecyclerViewAdapter adapter;
    List<Inicio> inicioList;
    FirebaseFirestore db;
    SwipeRefreshLayout swipeRefreshLayout;

    public InicioFragment() {
    }


    @SuppressWarnings("unused")
    public static InicioFragment newInstance(int columnCount) {
        InicioFragment fragment = new InicioFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = FirebaseFirestore.getInstance();

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio_list, container, false);

        Context context = view.getContext();
        recyclerView = view.findViewById(R.id.list);

        // Declaración del layout
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        // Función para el Swipe refresh
        swipeRefreshLayout = view.findViewById(R.id.swiperefreshlayout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                loadNewData();
            }
        });

        loadData();

        return view;
    }

    private void loadData() {

        db.collection("inicio")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        inicioList = new ArrayList<>();
                        for(DocumentSnapshot document : task.getResult()){
                            Inicio inicioItem = document.toObject(Inicio.class);
                            inicioList.add(inicioItem);

                            adapter = new MyInicioRecyclerViewAdapter(
                                    getActivity(),
                                    inicioList
                            );
                            recyclerView.setAdapter(adapter);
                        }
                    }
                });

    }

    private void loadNewData() {

        db.collection("inicio")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        inicioList = new ArrayList<>();
                        for(DocumentSnapshot document : task.getResult()){
                            Inicio inicioItem = document.toObject(Inicio.class);
                            inicioList.add(inicioItem);

                            adapter = new MyInicioRecyclerViewAdapter(
                                    getActivity(),
                                    inicioList
                            );
                            recyclerView.setAdapter(adapter);
                        }
                    }
                });

        swipeRefreshLayout.setRefreshing(false);

    }

}
