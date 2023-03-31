package com.luukitoo.myapplication.model.remote;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostsService {

    @GET("posts")
    Single<List<Post>> getAllPosts();

    @GET("posts/{index}")
    Single<Post> getPostById(@Path("index") int id);
}
