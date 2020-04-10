package com.proyecto.appmall.ui.tiendas;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.proyecto.appmall.R;
import com.proyecto.appmall.model.Tiendas;
import com.squareup.picasso.Picasso;

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
        holder.exTvTiendasDescripcion.setText(holder.mItem.getDescripcion());
        holder.tvTiendasHorario.setText(holder.mItem.getHorario());
        holder.tvTiendasWeb.setText(Html.fromHtml("<u>Abrir Web</u>"));

        Picasso.get().load(holder.mItem.getPhotoUrl())
                .into(holder.ivTiendasPhoto);

        // Animación zoom-in
        holder.getView().setAnimation(AnimationUtils.loadAnimation(ctx, R.anim.zoom_in));

        // Evento que abre la página web
        holder.tvTiendasWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(holder.mItem.getWeb());
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                ctx.startActivity(i);
            }
        });

        // Evento de llamada
        holder.ivTiendasLlamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:" + holder.mItem.getNumeroTel()));
                ctx.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvTienadsNombre;
        public final ExpandableTextView exTvTiendasDescripcion;
        public final TextView tvTiendasWeb;
        public final TextView tvTiendasHorario;
        public final ImageView ivTiendasPhoto;
        public final ImageView ivTiendasLlamada;
        public Tiendas mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvTienadsNombre = view.findViewById(R.id.textViewTiendasNombre);
            exTvTiendasDescripcion = view.findViewById(R.id.expand_text_tiendas);
            tvTiendasWeb = view.findViewById(R.id.textViewTiendasWeb);
            tvTiendasHorario = view.findViewById(R.id.textViewTiendasHorario);
            ivTiendasPhoto = view.findViewById(R.id.imageViewTiendasPhoto);
            ivTiendasLlamada = view.findViewById(R.id.imageViewLamada);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvTienadsNombre.getText() + "'";
        }

        public View getView(){
            return mView;
        }
    }
}
