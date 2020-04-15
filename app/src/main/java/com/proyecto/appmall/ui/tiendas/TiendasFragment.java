package com.proyecto.appmall.ui.tiendas;

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
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.proyecto.appmall.R;
import com.proyecto.appmall.model.Tiendas;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;


public class TiendasFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    List<Tiendas> tiendasList;
    MyTiendasRecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    FirebaseFirestore db;
    SwipeRefreshLayout swipeRefreshLayout;

    public TiendasFragment() {
    }


    public static TiendasFragment newInstance(int columnCount) {
        TiendasFragment fragment = new TiendasFragment();
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
        View view = inflater.inflate(R.layout.fragment_tiendas_list, container, false);

        Context context = view.getContext();
        recyclerView = view.findViewById(R.id.list);


        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        // Función para el Swipe refresh
        swipeRefreshLayout = view.findViewById(R.id.tiendasRefresh);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                loadNewData();
            }
        });

        loadData();

        refreshData();

        return view;
    }

    private void loadData() {

        db.collection("tiendas")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task != null){
                            tiendasList = new ArrayList<>();
                            for(DocumentSnapshot document : task.getResult()){
                                Tiendas tiendasItem = document.toObject(Tiendas.class);
                                tiendasItem.setId(document.getId());
                                tiendasList.add(tiendasItem);
                            }
                        }else{
                            tiendasList = new ArrayList<>();
                        }
                        adapter = new MyTiendasRecyclerViewAdapter(
                                getActivity(),
                                tiendasList
                        );
                        recyclerView.setAdapter(adapter);
                    }
                });

    }

    private void loadNewData() {

        db.collection("tiendas")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task != null){
                            tiendasList = new ArrayList<>();
                            for(DocumentSnapshot document : task.getResult()){
                                Tiendas tiendasItem = document.toObject(Tiendas.class);
                                tiendasItem.setId(document.getId());
                                tiendasList.add(tiendasItem);
                            }
                        }else{
                            tiendasList = new ArrayList<>();
                        }
                        adapter = new MyTiendasRecyclerViewAdapter(
                                getActivity(),
                                tiendasList
                        );
                        recyclerView.setAdapter(adapter);
                    }
                });

        swipeRefreshLayout.setRefreshing(false);

    }

    private void refreshData(){
        db.collection("tiendas")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        loadData();
                    }
                });
    }

}
