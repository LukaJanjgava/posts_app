package com.luukitoo.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.luukitoo.myapplication.databinding.ActivityMainBinding;
import com.luukitoo.myapplication.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private MainViewModel viewModel;

    private PostAdapter postAdapter = new PostAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();

        setObservers();

    }

    private void init() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.postsRecyclerView.setAdapter(postAdapter);
        viewModel.getPosts();
    }

    private void setObservers() {
        viewModel.postsLive.observe(this, posts -> {
            postAdapter.updateList(posts);
        });
    }
}