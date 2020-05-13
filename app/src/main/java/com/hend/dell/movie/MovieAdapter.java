package com.hend.dell.movie;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context mCtx;
    private final ArrayList<MovieModel> movieModelArrayList;
  final private OnClickListerner mListener;
    interface OnClickListerner {
        void onClickItem(int position);
    }

    public MovieAdapter(Context mCtx, ArrayList<MovieModel> movieModelArrayList, OnClickListerner listerner) {
        this.mCtx = mCtx;
        this.movieModelArrayList = movieModelArrayList;
        mListener =listerner;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View item = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);
        return new MovieHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
       MovieModel movieModel = movieModelArrayList.get(i);
    MovieHolder  holder = (MovieHolder)  viewHolder;
        Uri image = MovieModel.buildUrl(movieModel.getPoster_path());
        Picasso.get().load(image).placeholder(R.drawable.error).into(holder.mPosterImage);
           // holder.mPosterTitle.setText(movieModel.getOriginalPoster());
    }

    @Override
    public int getItemCount() {
        return movieModelArrayList.size();
    }
    class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
       // TextView mPosterTitle;
       final ImageView mPosterImage;

        MovieHolder(@NonNull View itemView) {
            super(itemView);
            mPosterImage = itemView.findViewById(R.id.poster_iv);
            itemView.setOnClickListener(this);
           // mPosterTitle = itemView.findViewById(R.id.poster_tilte_tv);

        }

        @Override
        public void onClick(View v) {
            if (mListener!=null){
                mListener.onClickItem(getAdapterPosition());
            }
        }
    }
}
