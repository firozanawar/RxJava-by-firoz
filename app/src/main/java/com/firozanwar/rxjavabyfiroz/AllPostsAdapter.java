package com.firozanwar.rxjavabyfiroz;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AllPostsAdapter extends RecyclerView.Adapter<AllPostsAdapter.MyViewHolder> {

    private List<Post> posts = new ArrayList<>();

    public void setData(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View iteamView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_posts, parent, false);
        return new MyViewHolder(iteamView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Post post = posts.get(position);
        //set data to view
        holder.title.setText(post.getTitle());
        holder.body.setText(post.getBody());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        //declare variables here
        private TextView title;
        private TextView body;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //reference declared object here.
            title = (TextView) itemView.findViewById(R.id.tvTitle);
            body = (TextView) itemView.findViewById(R.id.tvBody);
        }
    }
}
