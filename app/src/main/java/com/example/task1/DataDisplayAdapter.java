package com.example.task1;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DataDisplayAdapter extends RecyclerView.Adapter<DataDisplayAdapter.DataDisplayViewholder> {

    private ArrayList<String> post_title = new ArrayList<String>();
    private ArrayList<String> post_date = new ArrayList<String>();
    private ArrayList<String> post_image = new ArrayList<String>();

    private ArrayList<String> post_author = new ArrayList<String>();
    private ArrayList<String> post_description = new ArrayList<String>();
    private Context mcontext;

    public DataDisplayAdapter(Context context,ArrayList<String> list1,ArrayList<String> list2,ArrayList<String> list5,ArrayList<String> list3,ArrayList<String> list4)
    {
        post_title = list1;
        post_date = list2;
        post_image= list5;
        post_description = list3;
        post_author = list4;

        mcontext =context;
    }
    @NonNull
    @Override
    public DataDisplayViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detaildisplay,parent , false);
        return new DataDisplayViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataDisplayViewholder holder, int position) {
        holder.title.setText(post_title.get(position));
        holder.date.setText(post_date.get(position));
        holder.description.setText(post_description.get(position));
        holder.author.setText(post_author.get(position));
        //loading image from internet
        Glide.with(mcontext).load(post_image.get(position)).into(holder.postImage);
    }


    @Override
    public int getItemCount() {
        return post_title.size();
    }
    public class DataDisplayViewholder extends RecyclerView.ViewHolder{

        TextView title,date,description, author;
        ImageView postImage;
        public DataDisplayViewholder(@NonNull View itemView) {
            super(itemView);
            title=(TextView) itemView.findViewById(R.id.title);
            date = (TextView) itemView.findViewById(R.id.date);
            postImage= itemView.findViewById(R.id.postImage);
            description = (TextView) itemView.findViewById(R.id.description);
            author = (TextView) itemView.findViewById(R.id.author);

        }
    }
}
