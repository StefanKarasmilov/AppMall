package com.proyecto.appmall.ui.tiendas;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.proyecto.appmall.R;
import com.proyecto.appmall.response.Tiendas;

import java.util.List;


public class MyTiendasRecyclerViewAdapter extends RecyclerView.Adapter<MyTiendasRecyclerViewAdapter.ViewHolder> {

    private final List<Tiendas> mValues;
    private Context ctx;

    public MyTiendasRecyclerViewAdapter(Context contexto, List<Tiendas> items) {
        ctx = contexto;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_tiendas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.tvTienadsNombre.setText(holder.mItem.getNombre());
        holder.tvTiendasDescripcion.setText(holder.mItem.getDescripcion());
        holder.tvTiendasWeb.setText(holder.mItem.getWeb());
        holder.tvTiendasHorario.setText(holder.mItem.getHorario());

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvTienadsNombre;
        public final TextView tvTiendasDescripcion;
        public final TextView tvTiendasWeb;
        public final TextView tvTiendasHorario;
        public final ImageView ivTiendasPhoto;
        public Tiendas mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvTienadsNombre = view.findViewById(R.id.textViewTiendasNombre);
            tvTiendasDescripcion = view.findViewById(R.id.textViewTiendasDescripcion);
            tvTiendasWeb = view.findViewById(R.id.textViewTiendasWeb);
            tvTiendasHorario = view.findViewById(R.id.textViewTiendasHorario);
            ivTiendasPhoto = view.findViewById(R.id.imageViewTiendasPhoto);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvTienadsNombre.getText() + "'";
        }
    }
}
