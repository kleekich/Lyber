package com.example.lyber;

/**
 * Created by kangsik_kevin_lee on 8/30/17.
 */

public interface ResponseHandler<T> {
    void onSuccess(T response);
    void onError(int responseCode, String message);
}
