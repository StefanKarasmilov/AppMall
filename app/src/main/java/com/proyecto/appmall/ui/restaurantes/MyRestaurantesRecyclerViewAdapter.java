package com.proyecto.appmall.ui.restaurantes;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.proyecto.appmall.R;
import com.proyecto.appmall.model.Restaurantes;
import com.squareup.picasso.Picasso;

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
        holder.exTvRestaurantesDescripcion.setText(holder.mItem.getDescripcion());
        holder.tvRestaurantesHorario.setText(holder.mItem.getHorario());
        holder.rbRestaurante.setRating(holder.mItem.getRating());

        Picasso.get().load((holder.mItem.getPhotoUrl()))
                .into(holder.ivRestaurantesPhoto);

        // Evento llamada
        holder.ivRestaurantesTelefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:" + holder.mItem.getTelefono()));
                ctx.startActivity(i);
            }
        });

        // Animación zoom-in
        holder.getView().setAnimation(AnimationUtils.loadAnimation(ctx, R.anim.zoom_in));

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvRestaurantesNombre;
        public final TextView tvRestaurantesHorario;
        public final ImageView ivRestaurantesTelefono;
        public final ImageView ivRestaurantesPhoto;
        public final ExpandableTextView exTvRestaurantesDescripcion;
        public final RatingBar rbRestaurante;
        public Restaurantes mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvRestaurantesNombre = view.findViewById(R.id.textViewRestaurantesNombre);
            exTvRestaurantesDescripcion = view.findViewById(R.id.expand_text_restaurantes);
            tvRestaurantesHorario = view.findViewById(R.id.textViewRestaurantesHorario);
            ivRestaurantesTelefono = view.findViewById(R.id.imageViewTelefonoResutantes);
            ivRestaurantesPhoto = view.findViewById(R.id.imageViewRestaurantesPhoto);
            rbRestaurante = view.findViewById(R.id.ratingBarRestaurantes);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvRestaurantesNombre.getText() + "'";
        }

        public View getView(){
            return mView;
        }

    }


}
