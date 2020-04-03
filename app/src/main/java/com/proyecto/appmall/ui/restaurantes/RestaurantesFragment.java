package com.proyecto.appmall.ui.restaurantes;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.proyecto.appmall.R;
import com.proyecto.appmall.model.Restaurantes;

import java.util.ArrayList;
import java.util.List;


public class RestaurantesFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    RecyclerView recyclerView;
    MyRestaurantesRecyclerViewAdapter adapter;
    List<Restaurantes> restaurantesList;

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

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurantes_list, container, false);

        Context context = view.getContext();
        recyclerView = (RecyclerView) view;

        restaurantesList = new ArrayList<>();
        restaurantesList.add(new Restaurantes("Tagliatella", "Restaurante de pasta y pizza Italiana", "9:00 - 00:00", 947846989, ""));
        restaurantesList.add(new Restaurantes("Tagliatella", "Restaurante de pasta y pizza Italiana", "9:00 - 00:00", 947846989, ""));
        restaurantesList.add(new Restaurantes("Tagliatella", "Restaurante de pasta y pizza Italiana", "9:00 - 00:00", 947846989, ""));
        restaurantesList.add(new Restaurantes("Tagliatella", "Restaurante de pasta y pizza Italiana", "9:00 - 00:00", 947846989, ""));
        restaurantesList.add(new Restaurantes("Tagliatella", "Restaurante de pasta y pizza Italiana", "9:00 - 00:00", 947846989, ""));
        restaurantesList.add(new Restaurantes("Tagliatella", "Restaurante de pasta y pizza Italiana", "9:00 - 00:00", 947846989, ""));
        restaurantesList.add(new Restaurantes("Tagliatella", "Restaurante de pasta y pizza Italiana", "9:00 - 00:00", 947846989, ""));
        restaurantesList.add(new Restaurantes("Tagliatella", "Restaurante de pasta y pizza Italiana", "9:00 - 00:00", 947846989, ""));
        restaurantesList.add(new Restaurantes("Tagliatella", "Restaurante de pasta y pizza Italiana", "9:00 - 00:00", 947846989, ""));
        restaurantesList.add(new Restaurantes("Tagliatella", "Restaurante de pasta y pizza Italiana", "9:00 - 00:00", 947846989, ""));
        restaurantesList.add(new Restaurantes("Tagliatella", "Restaurante de pasta y pizza Italiana", "9:00 - 00:00", 947846989, ""));



        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        adapter = new MyRestaurantesRecyclerViewAdapter(
                getActivity(),
                restaurantesList
        );

        recyclerView.setAdapter(adapter);

        return view;
    }



}
