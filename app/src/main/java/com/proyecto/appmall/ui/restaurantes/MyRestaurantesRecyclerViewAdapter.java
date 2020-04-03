package com.proyecto.appmall.ui.restaurantes;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.proyecto.appmall.R;
import com.proyecto.appmall.model.Restaurantes;

import java.util.List;


public class MyRestaurantesRecyclerViewAdapter extends RecyclerView.Adapter<MyRestaurantesRecyclerViewAdapter.ViewHolder> {

    private final List<Restaurantes> mValues;
    private Context ctx;

    public MyRestaurantesRecyclerViewAdapter(Context contexto, List<Restaurantes> items) {
        ctx = contexto;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_restaurantes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.tvRestaurantesNombre.setText(holder.mItem.getNombre());
        holder.tvRestaurantesDescripcion.setText(holder.mItem.getDescripcion());
        holder.tvRestaurantesHorario.setText(holder.mItem.getHorario());
        holder.tvRestaurantesTelefono.setText(String.valueOf(holder.mItem.getTelefono()));

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvRestaurantesNombre;
        public final TextView tvRestaurantesDescripcion;
        public final TextView tvRestaurantesHorario;
        public final TextView tvRestaurantesTelefono;
        public final ImageView ivRestaurantesPhoto;
        public Restaurantes mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvRestaurantesNombre = view.findViewById(R.id.textViewRestaurantesNombre);
            tvRestaurantesDescripcion = view.findViewById(R.id.textViewRestaurantesDescripcion);
            tvRestaurantesHorario = view.findViewById(R.id.textViewRestaurantesHorario);
            tvRestaurantesTelefono = view.findViewById(R.id.textViewRestaurantesTelefono);
            ivRestaurantesPhoto = view.findViewById(R.id.imageViewRestaurantesPhoto);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvRestaurantesNombre.getText() + "'";
        }
    }


}
