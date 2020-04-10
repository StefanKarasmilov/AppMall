package com.proyecto.appmall.ui.restaurantes;

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
import com.proyecto.appmall.model.Restaurantes;
import com.proyecto.appmall.model.Tiendas;
import com.proyecto.appmall.ui.tiendas.MyTiendasRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class RestaurantesFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    RecyclerView recyclerView;
    MyRestaurantesRecyclerViewAdapter adapter;
    List<Restaurantes> restaurantesList;
    FirebaseFirestore db;
    SwipeRefreshLayout swipeRefreshLayout;

    public RestaurantesFragment() {
    }


    public static RestaurantesFragment newInstance(int columnCount) {
        RestaurantesFragment fragment = new RestaurantesFragment();
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
        View view = inflater.inflate(R.layout.fragment_restaurantes_list, container, false);

        Context context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.list);


        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        // Función para el Swipe refresh
        swipeRefreshLayout = view.findViewById(R.id.restaurantesRefresh);
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

    private void loadData(){

        db.collection("restaurantes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        restaurantesList = new ArrayList<>();
                        for(DocumentSnapshot document : task.getResult()){
                            Restaurantes restaurante = document.toObject(Restaurantes.class);
                            restaurantesList.add(restaurante);

                            adapter = new MyRestaurantesRecyclerViewAdapter(
                                    getActivity(),
                                    restaurantesList
                            );
                            recyclerView.setAdapter(adapter);
                        }
                    }
                });

    }

    private void loadNewData() {

        db.collection("restaurantes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        restaurantesList = new ArrayList<>();
                        for(DocumentSnapshot document : task.getResult()){
                            Restaurantes restaurante = document.toObject(Restaurantes.class);
                            restaurantesList.add(restaurante);

                            adapter = new MyRestaurantesRecyclerViewAdapter(
                                    getActivity(),
                                    restaurantesList
                            );
                            recyclerView.setAdapter(adapter);
                        }
                    }
                });

        swipeRefreshLayout.setRefreshing(false);

    }

}
