package com.example.and_pollen;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList <DisplayClass> displayClassArrayList;
        private ArrayList<String> datesList;

    public CustomAdapter(Context context, Activity activity, ArrayList<DisplayClass> displayClassArrayList, ArrayList<String> datesList) {
        this.context = context;
        this.activity = activity;
        this.displayClassArrayList = displayClassArrayList;
        this.datesList = datesList;
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtAllergenName.setText(displayClassArrayList.get(position).getAllergen_name());
        holder.txtConcentrations.setText(String.valueOf(displayClassArrayList.get(position).getValue()));
        holder.txtPlantName.setText(displayClassArrayList.get(position).getDate());

//        holder.book_id_txt.setText(String.valueOf(book_id.get(position)));
//        holder.book_title_txt.setText(String.valueOf(book_title.get(position)));
//        holder.book_author_txt.setText(String.valueOf(book_author.get(position)));
//        holder.book_pages_txt.setText(String.valueOf(book_pages.get(position)));

    }

    @Override
    public int getItemCount() {
        return displayClassArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtAllergenName,txtConcentrations,txtPlantName;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAllergenName = itemView.findViewById(R.id.txtAllergenName);
            txtConcentrations = itemView.findViewById(R.id.txtConcentrations);
            txtPlantName = itemView.findViewById(R.id.txtPlantName);

            mainLayout = itemView.findViewById(R.id.mainLayout);
            // add animation
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
