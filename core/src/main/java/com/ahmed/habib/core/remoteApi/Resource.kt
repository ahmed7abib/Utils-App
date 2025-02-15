package com.ahmed.habib.core.remoteApi

sealed class Resource<T>(val data: T? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(val errorTypes: ErrorTypes) : Resource<T>()
}
