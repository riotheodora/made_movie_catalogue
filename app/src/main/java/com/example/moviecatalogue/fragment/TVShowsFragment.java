package com.example.moviecatalogue.fragment;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.adapter.ListTVShowAdapter;
import com.example.moviecatalogue.entity.TVShow;
import com.example.moviecatalogue.viewmodel.MainViewModelTVShow;

import java.util.ArrayList;

public class TVShowsFragment extends Fragment {
    private ListTVShowAdapter adapter;
    private ProgressDialog progressDialog;
    private MainViewModelTVShow mainViewModelTVShow;

    public TVShowsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_tvshows, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchMenu = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(getContext().SEARCH_SERVICE);

        if (searchManager != null){
            final androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) searchMenu.getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.search_hint));

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
//                    Toast.makeText(getContext(), query, Toast.LENGTH_SHORT).show();

                    mainViewModelTVShow.setSpecificListTVShows(query);

                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvTVShows = getView().findViewById(R.id.rv_tvshows);
        adapter = new ListTVShowAdapter(getContext());
        adapter.notifyDataSetChanged();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvTVShows.setLayoutManager(layoutManager);
        rvTVShows.setAdapter(adapter);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        mainViewModelTVShow = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModelTVShow.class);

        mainViewModelTVShow.setListTVShows();

        mainViewModelTVShow.getListTVShows().observe(this, new Observer<ArrayList<TVShow>>() {
            @Override
            public void onChanged(ArrayList<TVShow> tvShows) {
                if (tvShows != null) {

//                    Log.e("onChange", "CALLME" );

                    adapter.setData(tvShows);
                    progressDialog.dismiss();
                }
            }
        });
    }
}