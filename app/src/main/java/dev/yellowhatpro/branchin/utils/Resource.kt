package dev.yellowhatpro.branchin.utils

class Resource<T>(val status: Status, val data: T?, val response: String) {

    enum class Status {
        LOADING, FAILED, SUCCESS
    }

    companion object {
        fun <S> failure(error: String): Resource<S> = Resource(Status.FAILED, null, error)

        fun <S> loading(): Resource<S> = Resource(Status.LOADING, null, "Loading")

        fun <S> success(data: S, response: String): Resource<S> = Resource(Status.SUCCESS, data, response)
    }
}