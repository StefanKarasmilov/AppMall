package com.proyecto.appmall.ui.inicio;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.proyecto.appmall.R;
import com.proyecto.appmall.response.Inicio;

import java.util.List;


public class MyInicioRecyclerViewAdapter extends RecyclerView.Adapter<MyInicioRecyclerViewAdapter.ViewHolder> {

    private List<Inicio> mValues;
    private Context ctx;

    public MyInicioRecyclerViewAdapter(Context contexto, List<Inicio> items) {
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

        holder.tvInicioTienda.setText(holder.mItem.getNombreOferta());
        holder.tvInicioDescripcion.setText(holder.mItem.getDescripcion());

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
            tvInicioTienda = view.findViewById(R.id.textViewTiendasNombre);
            tvInicioDescripcion = view.findViewById(R.id.textViewTiendasDescripcion);
            ivInicioOferta = view.findViewById(R.id.imageViewTiendasPhoto);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvInicioTienda.getText() + "'";
        }
    }
}
