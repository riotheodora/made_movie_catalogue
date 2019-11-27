package com.example.moviecatalogue.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviecatalogue.adapter.ListTVShowAdapter;
import com.example.moviecatalogue.MainViewModelTVShow;
import com.example.moviecatalogue.R;
import com.example.moviecatalogue.TVShow;

import java.util.ArrayList;

public class TVShowsFragment extends Fragment {
    private ListTVShowAdapter adapter;
    ProgressDialog progressDialog;
    private RecyclerView rvTVShows;
    private MainViewModelTVShow mainViewModel;

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

        rvTVShows = getView().findViewById(R.id.rv_tvshows);
        adapter = new ListTVShowAdapter(getContext());
        adapter.notifyDataSetChanged();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvTVShows.setLayoutManager(layoutManager);
        rvTVShows.setAdapter(adapter);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        mainViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModelTVShow.class);

        mainViewModel.setListTVShows();

        mainViewModel.getListTVShows().observe(this, new Observer<ArrayList<TVShow>>() {
            @Override
            public void onChanged(ArrayList<TVShow> tvshows) {
                if (tvshows != null) {
                    adapter.setData(tvshows);
                    progressDialog.dismiss();
                }
            }
        });
    }
}