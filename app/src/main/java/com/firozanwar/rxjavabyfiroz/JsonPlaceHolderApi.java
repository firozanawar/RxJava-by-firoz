package com.firozanwar.rxjavabyfiroz;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;
import rx.Observer;

public interface JsonPlaceHolderApi {
    @GET("posts")
    Observable<List<Post>> getPosts();
}
