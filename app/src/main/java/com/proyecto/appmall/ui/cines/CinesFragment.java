package com.proyecto.appmall.ui.cines;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.proyecto.appmall.R;
import com.proyecto.appmall.response.Cines;

import java.util.ArrayList;
import java.util.List;


public class CinesFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 2;
    RecyclerView recyclerView;
    MyCinesRecyclerViewAdapter adapter;
    List<Cines> cinesList;

    public CinesFragment() {
    }

    public static CinesFragment newInstance(int columnCount) {
        CinesFragment fragment = new CinesFragment();
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
        View view = inflater.inflate(R.layout.fragment_cines_list, container, false);

        Context context = view.getContext();
        recyclerView = (RecyclerView) view;

        cinesList = new ArrayList<>();
        cinesList.add(new Cines("Los Vengadores", "17:00 - 19:00 / 17:00 - 19:00 / 17:00 - 19:00", "", "www.compraentrada.com"));
        cinesList.add(new Cines("Los Vengadores", "17:00 - 19:00", "", "www.compraentrada.com"));
        cinesList.add(new Cines("Los Vengadores", "17:00 - 19:00", "", "www.compraentrada.com"));
        cinesList.add(new Cines("Los Vengadores", "17:00 - 19:00", "", "www.compraentrada.com"));
        cinesList.add(new Cines("Los Vengadores", "17:00 - 19:00", "", "www.compraentrada.com"));
        cinesList.add(new Cines("Los Vengadores", "17:00 - 19:00", "", "www.compraentrada.com"));
        cinesList.add(new Cines("Los Vengadores", "17:00 - 19:00", "", "www.compraentrada.com"));
        cinesList.add(new Cines("Los Vengadores", "17:00 - 19:00", "", "www.compraentrada.com"));
        cinesList.add(new Cines("Los Vengadores", "17:00 - 19:00", "", "www.compraentrada.com"));
        cinesList.add(new Cines("Los Vengadores", "17:00 - 19:00", "", "www.compraentrada.com"));
        cinesList.add(new Cines("Los Vengadores", "17:00 - 19:00", "", "www.compraentrada.com"));
        cinesList.add(new Cines("Los Vengadores", "17:00 - 19:00", "", "www.compraentrada.com"));

        recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));

        adapter = new MyCinesRecyclerViewAdapter(
                getActivity(),
                cinesList
        );

        recyclerView.setAdapter(adapter);

        return view;
    }

}
