package com.proyecto.appmall.ui.tiendas;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.proyecto.appmall.R;
import com.proyecto.appmall.response.Tiendas;
import com.proyecto.appmall.ui.inicio.InicioFragment;
import com.proyecto.appmall.ui.inicio.MyInicioRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class TiendasFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    RecyclerView recyclerView;
    MyTiendasRecyclerViewAdapter adapter;
    List<Tiendas> tiendasList;
    private OnListFragmentInteractionListener mListener;

    public TiendasFragment() {
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

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio_list, container, false);

        Context context = view.getContext();
        recyclerView = (RecyclerView) view;

        tiendasList = new ArrayList<>();
        tiendasList.add(new Tiendas("Zara", "Tienda de ropa", "9:00 - 22:00", "www.zara.com"));
        tiendasList.add(new Tiendas("Zara", "Tienda de ropa", "9:00 - 22:00", "www.zara.com"));
        tiendasList.add(new Tiendas("Zara", "Tienda de ropa", "9:00 - 22:00", "www.zara.com"));

        // Declaración del layout
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        adapter = new MyTiendasRecyclerViewAdapter(
                getActivity(),
                tiendasList,
                mListener
        );

        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InicioFragment.OnListFragmentInteractionListener) {
            mListener = (TiendasFragment.OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Tiendas item);
    }
}
