package com.luukitoo.myapplication.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.luukitoo.myapplication.model.remote.Post;
import com.luukitoo.myapplication.model.remote.RetrofitInstance;
import com.luukitoo.myapplication.model.repository.PostsRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    private PostsRepository postsRepository = new PostsRepository();

    public MutableLiveData<List<Post>> postsLive = new MutableLiveData<>(new ArrayList<>());

    private CompositeDisposable disposables = new CompositeDisposable();

    public void getPosts() {
        disposables.add(
                postsRepository.getAllPosts().subscribe(posts -> {
                    postsLive.setValue(posts);
                })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.clear();
    }
}
