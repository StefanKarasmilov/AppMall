package com.proyecto.appmall.ui.cines;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.proyecto.appmall.R;
import com.proyecto.appmall.model.Cines;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MyCinesRecyclerViewAdapter extends RecyclerView.Adapter<MyCinesRecyclerViewAdapter.ViewHolder> {

    private final List<Cines> mValues;
    private Context ctx;

    public MyCinesRecyclerViewAdapter(Context contexto, List<Cines> items) {
        ctx = contexto;
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_cines, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.tvCinesNombre.setText(holder.mItem.getNombre());
        holder.tvCinesHorario.setText(holder.mItem.getHorarios());

        Picasso.get().load(holder.mItem.getPhotoUrl())
                .into(holder.ivCinesPhoto);

        // Animación zoom-in
        holder.getView().setAnimation(AnimationUtils.loadAnimation(ctx, R.anim.zoom_in));

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvCinesNombre;
        public final TextView tvCinesHorario;
        public final ImageView ivCinesPhoto;
        public Cines mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvCinesNombre = view.findViewById(R.id.textViewCinesNombre);
            tvCinesHorario = view.findViewById(R.id.textViewCinesHorario);
            ivCinesPhoto = view.findViewById(R.id.imageViewCinesPhoto);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvCinesNombre.getText() + "'";
        }

        public View getView(){
            return mView;
        }
    }
}
