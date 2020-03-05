package com.proyecto.appmall.ui.tiendas;

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
import com.proyecto.appmall.response.Tiendas;

import java.util.ArrayList;
import java.util.List;


public class TiendasFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    List<Tiendas> tiendasList;
    MyTiendasRecyclerViewAdapter adapter;
    RecyclerView recyclerView;

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

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tiendas_list, container, false);

        Context context = view.getContext();
        recyclerView = (RecyclerView) view;

        tiendasList = new ArrayList<>();
        tiendasList.add(new Tiendas("Zara", "Tienda para ropa", "9:00 - 22:00", "www.zara.es"));
        tiendasList.add(new Tiendas("Zara", "Tienda para ropa", "9:00 - 22:00", "www.zara.es"));
        tiendasList.add(new Tiendas("Zara", "Tienda para ropa", "9:00 - 22:00", "www.zara.es"));
        tiendasList.add(new Tiendas("Zara", "Tienda para ropa", "9:00 - 22:00", "www.zara.es"));
        tiendasList.add(new Tiendas("Zara", "Tienda para ropa", "9:00 - 22:00", "www.zara.es"));
        tiendasList.add(new Tiendas("Zara", "Tienda para ropa", "9:00 - 22:00", "www.zara.es"));
        tiendasList.add(new Tiendas("Zara", "Tienda para ropa", "9:00 - 22:00", "www.zara.es"));
        tiendasList.add(new Tiendas("Zara", "Tienda para ropa", "9:00 - 22:00", "www.zara.es"));
        tiendasList.add(new Tiendas("Zara", "Tienda para ropa", "9:00 - 22:00", "www.zara.es"));
        tiendasList.add(new Tiendas("Zara", "Tienda para ropa", "9:00 - 22:00", "www.zara.es"));
        tiendasList.add(new Tiendas("Zara", "Tienda para ropa", "9:00 - 22:00", "www.zara.es"));
        tiendasList.add(new Tiendas("Zara", "Tienda para ropa", "9:00 - 22:00", "www.zara.es"));
        tiendasList.add(new Tiendas("Zara", "Tienda para ropa", "9:00 - 22:00", "www.zara.es"));
        tiendasList.add(new Tiendas("Zara", "Tienda para ropa", "9:00 - 22:00", "www.zara.es"));

        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        adapter = new MyTiendasRecyclerViewAdapter(
                getActivity(),
                tiendasList
        );

        recyclerView.setAdapter(adapter);

        return view;
    }

}
