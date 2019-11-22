package com.example.moviecatalogue;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVShowsFragment extends Fragment {
    private ListTVShowAdapter adapter;
    ProgressDialog progressDialog;
    private RecyclerView rvTVShows;

    public TVShowsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshows, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        GetTVShowDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetTVShowDataService.class);
        Call<TVShowResult> call = service.getAllTVShows();
        call.enqueue(new Callback<TVShowResult>() {
            @Override
            public void onResponse(Call<TVShowResult> call, Response<TVShowResult> response) {
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<TVShowResult> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateDataList(TVShowResult tvshowList) {
        rvTVShows = getView().findViewById(R.id.rv_tvshows);
        adapter = new ListTVShowAdapter(getContext(),tvshowList.getResults());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvTVShows.setLayoutManager(layoutManager);
        rvTVShows.setAdapter(adapter);
    }
}
