package com.example.movieapp.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.init.AppManager;
import com.example.movieapp.init.Imager;
import com.example.movieapp.R;
import com.example.movieapp.models.Movie;
import com.example.movieapp.models.Session;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class Adapter_MovieCard extends RecyclerView.Adapter<Adapter_MovieCard.EventViewHolder> {

    private List<Movie> movies;
    private MaterialCardView movie;
    private Session currentSess = AppManager.getInstance().getCurrentSession();
    private int currentPosition = 0;
    private OnActionListener listener;


    public Adapter_MovieCard(List<Movie> moviesList) {
        movies = moviesList;
    }

    public void updateList(List<Movie> moviesList) {
        movies = moviesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_card, viewGroup, false);
        return new EventViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Movie movie = getItem(position);
        holder.movie_LBL_title.setText(movie.getName());
        float rate = (float) movie.getRating();
        if (rate > 5) rate /= 2;
        holder.movie_RTG_rating.setRating(rate);
        ;
        holder.movie_LBL_genre.setText(movie.getCategory().toString());
        holder.movie_LBL_year.setText("" + movie.getYear());
        //holder.movie_LBL_description.setText(movie.getDescription());;
        holder.movie_LBL_description.setText("");
        ;
        Imager.me().imageCrop(holder.movie_IMG_main, movie.getImage());
        if (position == getItemCount() - 1) {
            AppManager.getInstance().getCurrentSession().finish();
            ((Activity) holder.itemView.getContext()).finish();
        }
        if (position == currentPosition) {
            // Current card being shown
            holder.itemView.setVisibility(View.VISIBLE);
        } else {
            // Other cards
            holder.itemView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    private Movie getItem(int position) {

        return movies.get(position);
    }


    public class EventViewHolder extends RecyclerView.ViewHolder {
        private MaterialTextView movie_LBL_title;
        private MaterialTextView movie_LBL_genre;
        private MaterialTextView movie_LBL_year;
        private MaterialTextView movie_LBL_description;
        private AppCompatImageView movie_IMG_main;
        private AppCompatRatingBar movie_RTG_rating;
        private MaterialButton movie_BTN_no, movie_BTN_yes;


        EventViewHolder(View itemView) {
            super(itemView);
            movie_LBL_title = itemView.findViewById(R.id.movie_LBL_title);
            movie_LBL_genre = itemView.findViewById(R.id.movie_LBL_genre);
            movie_LBL_year = itemView.findViewById(R.id.movie_LBL_year);
            movie_LBL_description = itemView.findViewById(R.id.movie_LBL_description);
            movie_IMG_main = itemView.findViewById(R.id.movie_IMG_main);
            movie_RTG_rating = itemView.findViewById(R.id.movie_RTG_rating);
            movie_BTN_no = itemView.findViewById(R.id.movie_BTN_no);
            movie_BTN_yes = itemView.findViewById(R.id.movie_BTN_yes);


            movie_BTN_no.setOnClickListener(view -> {
                if (currentPosition >= 0 && currentPosition < movies.size()) {
                    movies.remove(currentPosition);
                    notifyItemRemoved(currentPosition);
                    notifyItemChanged(currentPosition);
                }
            });
            movie_BTN_yes.setOnClickListener(view -> {
                if (currentPosition >= 0 && currentPosition < movies.size()) {
                    updateFBLiked();
                    movies.remove(currentPosition);
                    notifyItemRemoved(currentPosition);
                    notifyItemChanged(currentPosition);
                }
            });
        }

        public void updateFBLiked() {
            Boolean bool = currentSess.addLike(AppManager.getInstance().getLoggedIn(), getItem(currentPosition));
            if (bool) {
                movie_BTN_no.setEnabled(false);
                movie_BTN_yes.setEnabled(false);
                match(getItem(currentPosition));
            }

        }

        private void match(Movie item) {
            if (listener != null) {
                listener.onMatch(item);
            }
        }

    }

    public interface OnActionListener {

        void onMatch(Movie item);
    }

    public void setOnActionListener(OnActionListener listener) {
        this.listener = listener;
    }
}
