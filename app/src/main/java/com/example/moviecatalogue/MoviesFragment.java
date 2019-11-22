package com.example.moviecatalogue;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MoviesFragment extends Fragment {
    private ListMovieAdapter adapter;
    ProgressDialog progressDialog;
    private RecyclerView rvMovies;
    //private ArrayList<Movie> list = new ArrayList<>();

    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading....");
        progressDialog.show();


        //rvMovies.setHasFixedSize(true);

        //list.addAll(getListMovies());
        //showRecyclerList();

        /*Create handle for the RetrofitInstance interface*/
        GetMovieDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetMovieDataService.class);
        Call<MovieResult> call = service.getAllMovies();
        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("FAIL", t.getMessage().toString() );
                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateDataList(MovieResult movieList) {
        rvMovies = getView().findViewById(R.id.rv_movies);
        adapter = new ListMovieAdapter(getContext(),movieList.getResults());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvMovies.setLayoutManager(layoutManager);
        rvMovies.setAdapter(adapter);
    }

//    public ArrayList<Movie> getListMovies() {
//        String[] dataName = getResources().getStringArray(R.array.data_title_movies);
//        String[] dataDescription = getResources().getStringArray(R.array.data_overview_movies);
//        String[] dataPoster = getResources().getStringArray(R.array.data_poster_movies);
//        ArrayList<Movie> listMovie = new ArrayList<>();
//        for (int i = 0; i < dataName.length; i++) {
//            Movie movie = new Movie();
//            movie.setTitle(dataName[i]);
//            movie.setOverview(dataDescription[i]);
//            movie.setPoster_path(dataPoster[i]);
//            listMovie.add(movie);
//        }
//        return listMovie;
//    }

//    private void showRecyclerList(){
//        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
//        ListMovieAdapter listMovieAdapter = new ListMovieAdapter(list);
//        rvMovies.setAdapter(listMovieAdapter);
//    }

}
