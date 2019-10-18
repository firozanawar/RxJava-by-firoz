package com.firozanwar.rxjavabyfiroz;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface JsonPlaceHolderApi {
    @GET("posts")
    Observable<List<Post>> getPosts();

    @GET("posts")
    List<Post> getPostList();
}
