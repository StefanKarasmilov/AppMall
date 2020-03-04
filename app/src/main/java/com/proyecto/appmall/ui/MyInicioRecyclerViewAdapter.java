package com.proyecto.appmall.ui;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.proyecto.appmall.response;
import com.proyecto.appmall.R;

import java.util.List;


public class MyInicioRecyclerViewAdapter extends RecyclerView.Adapter<MyInicioRecyclerViewAdapter.ViewHolder> {

    private List<Inicio> mValues;
    private Context ctx;

    public MyInicioRecyclerViewAdapter(List<Inicio> items, Context contexto) {
        mValues = items;
        ctx = contexto;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_inicio, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.tvInicioTienda.setText(holder.mItem.);

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvInicioTienda;
        public final TextView tvInicioDescripcion;
        public final ImageView ivInicioOferta;
        public Inicio mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvInicioTienda = view.findViewById(R.id.textViewInicioTienda);
            tvInicioDescripcion = view.findViewById(R.id.textViewInicioDescripcion);
            ivInicioOferta = view.findViewById(R.id.imageViewInicioOferta);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvInicioTienda.getText() + "'";
        }
    }
}
