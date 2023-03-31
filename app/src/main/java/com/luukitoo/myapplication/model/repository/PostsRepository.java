package com.luukitoo.myapplication.model.repository;

import com.luukitoo.myapplication.model.remote.Post;
import com.luukitoo.myapplication.model.remote.RetrofitInstance;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PostsRepository {

    public Single<List<Post>> getAllPosts() {
        return RetrofitInstance.getPostsService().getAllPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
