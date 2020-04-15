package com.proyecto.appmall.ui.cines;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.proyecto.appmall.BottomModalFragment;
import com.proyecto.appmall.R;
import com.proyecto.appmall.common.Constantes;
import com.proyecto.appmall.model.Cines;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MyCinesRecyclerViewAdapter extends RecyclerView.Adapter<MyCinesRecyclerViewAdapter.ViewHolder> {

    private final List<Cines> mValues;
    private Context ctx;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    public MyCinesRecyclerViewAdapter(Context contexto, List<Cines> items) {
        ctx = contexto;
        mValues = items;
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
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

        // Comprueba si el usuario es con permiso de admin
        holder.ivShowMenu.setVisibility(View.GONE);
        for(String user : Constantes.ADMIN_UID){
            if(user.equals(firebaseUser.getUid())){
                holder.ivShowMenu.setVisibility(View.VISIBLE);
            }
        }

        // Evento que abre el menú de abajo
        holder.ivShowMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomModalFragment dialogo = BottomModalFragment.newInstance(holder.mItem.getId(), "cines", holder.mItem.getPhotoUrl());
                dialogo.show(((AppCompatActivity)ctx).getSupportFragmentManager(), "BottomModalFragment");
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
        public final TextView tvCinesNombre;
        public final TextView tvCinesHorario;
        public final ImageView ivCinesPhoto;
        public final ImageView ivShowMenu;
        public Cines mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvCinesNombre = view.findViewById(R.id.textViewCinesNombre);
            tvCinesHorario = view.findViewById(R.id.textViewCinesHorario);
            ivCinesPhoto = view.findViewById(R.id.imageViewCinesPhoto);
            ivShowMenu = view.findViewById(R.id.imageViewShowMenu);
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
