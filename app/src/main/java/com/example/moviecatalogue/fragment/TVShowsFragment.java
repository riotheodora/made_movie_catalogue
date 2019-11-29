package com.example.moviecatalogue.fragment;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviecatalogue.adapter.ListTVShowAdapter;
import com.example.moviecatalogue.viewmodel.MainViewModelTVShow;
import com.example.moviecatalogue.R;
import com.example.moviecatalogue.entity.TVShow;

import java.util.ArrayList;

public class TVShowsFragment extends Fragment {
    private ListTVShowAdapter adapter;
    private ProgressDialog progressDialog;
    private RecyclerView rvTVShows;
    private MainViewModelTVShow mainViewModel;

    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

    public TVShowsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_tvshows, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu,inflater);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchManager != null) {
            searchView = (SearchView) searchItem.getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.search_hint));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Toast.makeText(getActivity(), query, Toast.LENGTH_LONG).show();
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    return false;
                }
            });
            searchView.setOnQueryTextListener(queryTextListener);
        }
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search){

        }
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
    }
}