package org.hungrytessy.stockmarkettracker.util

import java.lang.Exception

sealed class Resource<T> (val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null, val exception: Exception) : Resource<T>(data, message)
    class Loading<T>(val isLoading: Boolean = true) : Resource<T>()
}
