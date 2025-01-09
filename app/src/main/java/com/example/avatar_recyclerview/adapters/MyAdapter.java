package com.example.avatar_recyclerview.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.avatar_recyclerview.R;
import com.example.avatar_recyclerview.models.Data;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> {

    private ArrayList<Data> arr;
    private ArrayList<Data> arrFull;

    public MyAdapter(ArrayList<Data> arr) {
        this.arr = arr;
        this.arrFull = new ArrayList<>(arr);
    }

    public void filter(String text) {
        arr.clear();
        if(text.isEmpty()){
            arr.addAll(arrFull);
        } else {
            text = text.toLowerCase();
            for(Data item: arrFull){
                if(item.getName().toLowerCase().contains(text) || 
                   item.getDescription().toLowerCase().contains(text)){
                    arr.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {


        TextView username;
        TextView Description;
        ImageView imageViewItem;

        public myViewHolder ( View itemView){
            super(itemView);
           username = itemView.findViewById(R.id.CharacterName);
            Description = itemView.findViewById(R.id.description);
           imageViewItem = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    showDetailDialog(v.getContext(), arr.get(position));
                }
            });
        }
    }

    private void showDetailDialog(Context context, Data character) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.character_detail_dialog);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView characterImage = dialog.findViewById(R.id.dialog_image);
        TextView characterName = dialog.findViewById(R.id.dialog_name);
        TextView characterDesc = dialog.findViewById(R.id.dialog_description);

        characterImage.setImageResource(character.getImage());
        characterName.setText(character.getName());
        characterDesc.setText(character.getDescription());
        
        dialog.show();
    }

    @NonNull
    @Override
    public MyAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview , parent , false ) ;

        myViewHolder MyViewHolder = new myViewHolder(view);

       return MyViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.myViewHolder holder, int position) {

        holder.username.setText(arr.get(position).getName());
        holder.Description.setText(arr.get(position).getDescription());
        holder.imageViewItem.setImageResource(arr.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }


}
