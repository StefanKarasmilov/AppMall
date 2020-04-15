package com.proyecto.appmall.ui.inicio;

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
import com.proyecto.appmall.common.MyApp;
import com.proyecto.appmall.model.Inicio;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MyInicioRecyclerViewAdapter extends RecyclerView.Adapter<MyInicioRecyclerViewAdapter.ViewHolder> {

    private List<Inicio> mValues;
    private Context ctx;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    public MyInicioRecyclerViewAdapter(Context contexto, List<Inicio> items) {
        mValues = items;
        ctx = contexto;
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
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

        holder.tvInicioTienda.setText(holder.mItem.getNombreOferta());
        holder.tvInicioDescripcion.setText(holder.mItem.getDescripcion());

        holder.ivShowMenu.setVisibility(View.GONE);
        for(String user : Constantes.ADMIN_UID){
            if(user.equals(firebaseUser.getUid())){
                holder.ivShowMenu.setVisibility(View.VISIBLE);
            }
        }

        holder.ivShowMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomModalFragment dialogo = BottomModalFragment.newInstance(holder.mItem.getId(), "inicio", holder.mItem.getPhotoUrl());
                dialogo.show(((AppCompatActivity)ctx).getSupportFragmentManager(), "BottomModalFragment");
            }
        });

        Picasso.get().load(holder.mItem.getPhotoUrl())
                .into(holder.ivInicioOferta);

        // Animación
        holder.getView().setAnimation(AnimationUtils.loadAnimation(ctx, R.anim.zoom_in));
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
        public final ImageView ivShowMenu;
        public Inicio mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvInicioTienda = view.findViewById(R.id.textViewTiendasNombre);
            tvInicioDescripcion = view.findViewById(R.id.textViewTiendasDescripcion);
            ivInicioOferta = view.findViewById(R.id.imageViewTiendasPhoto);
            ivShowMenu = view.findViewById(R.id.imageViewShowMenu);
        }

        public View getView(){
            return mView;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvInicioTienda.getText() + "'";
        }
    }
}
