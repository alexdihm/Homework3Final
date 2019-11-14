package app.catsrus.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;

import app.catsrus.R;
import app.catsrus.ui.Cat;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    private HashMap<Integer, Cat> items;
    private OnClickListener listener;

    public SearchAdapter(HashMap<Integer, Cat> items, OnClickListener listener){
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewitem,parent,false);
        return new ViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cat cat = items.get(position);
        holder.name.setText(cat.getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView name;
        public OnClickListener listener;

        public ViewHolder(View v, OnClickListener listener){
            super(v);
            this.name = v.findViewById(R.id.item);
            this.listener = listener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnClickListener{
        void onItemClick(int position);
    }

}
