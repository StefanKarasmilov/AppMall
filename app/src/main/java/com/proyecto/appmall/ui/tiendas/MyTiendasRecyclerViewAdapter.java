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
import com.proyecto.appmall.ui.inicio.InicioFragment;

import java.util.List;


public class MyTiendasRecyclerViewAdapter extends RecyclerView.Adapter<MyTiendasRecyclerViewAdapter.ViewHolder> {

    private List<Tiendas> mValues;
    private Context ctx;
    private TiendasFragment.OnListFragmentInteractionListener mListener;

    public MyTiendasRecyclerViewAdapter(Context contexto, List<Tiendas> items, TiendasFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        ctx = contexto;
        mListener = listener;
    }

    @Override
    public MyTiendasRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_inicio, parent, false);
        return new MyTiendasRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyTiendasRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.tvTiendasNombre.setText(holder.mItem.getNombre());
        holder.tvTiendasDescripcion.setText(holder.mItem.getDescripcion());
        holder.tvTiendasHorario.setText(holder.mItem.getHorario());
        holder.tvTiendasWeb.setText(holder.mItem.getWeb());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvTiendasNombre;
        public final TextView tvTiendasDescripcion;
        public final TextView tvTiendasWeb;
        public final TextView tvTiendasHorario;
        public final ImageView ivTiendasPhoto;
        public Tiendas mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvTiendasNombre = view.findViewById(R.id.textViewTiendasNombre);
            tvTiendasDescripcion = view.findViewById(R.id.textViewTiendasDescripcion);
            tvTiendasWeb = view.findViewById(R.id.textViewTiendasWeb);
            tvTiendasHorario = view.findViewById(R.id.textViewTiendasHorario);
            ivTiendasPhoto = view.findViewById(R.id.imageViewTiendasPhoto);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvTiendasNombre.getText() + "'";
        }
    }
}
