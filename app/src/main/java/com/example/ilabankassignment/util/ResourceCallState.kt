package com.example.ilabankassignment.util

data class ResourceCallState<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        // In case of Success,set status as
        // Success and data as the response
        fun <T> success(data: T?): ResourceCallState<T> {
            return ResourceCallState(Status.SUCCESS, data, null)
        }

        // In case of failure ,set state to Error ,
        // add the error message,set data to null
        fun <T> error(msg: String): ResourceCallState<T> {
            return ResourceCallState(Status.ERROR, null, msg)
        }

        // In case of failure ,set state to Error ,
        // add the error message,we can pass data also if required
        fun <T> error(msg: String, data: T?): ResourceCallState<T> {
            return ResourceCallState(Status.ERROR, data, msg)
        }

        // When the call is loading set the state
        // as Loading and rest as null
        fun <T> loading(): ResourceCallState<T> {
            return ResourceCallState(Status.LOADING, null, null)
        }
    }
}


// An enum to store the
// current state of api call
enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
