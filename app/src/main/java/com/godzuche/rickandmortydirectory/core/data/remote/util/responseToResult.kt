package com.godzuche.rickandmortydirectory.core.data.remote.util

import com.godzuche.rickandmortydirectory.core.domain.utils.DataError
import com.godzuche.rickandmortydirectory.core.domain.utils.Result
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.JsonConvertException

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): com.godzuche.rickandmortydirectory.core.domain.utils.Result<T, DataError.Remote> {
    return when (val statusCode = response.status.value) {
        in 200..<300 -> {
            try {
                Result.Success(response.body<T>())
            } catch (_: NoTransformationFoundException) {
                Result.Error(DataError.Remote.Serialization)
            } catch (_: JsonConvertException) {
                Result.Error(DataError.Remote.Serialization)
            }
        }

        408 -> Result.Error(DataError.Remote.RequestTimeout)
        429 -> Result.Error(DataError.Remote.TooManyRequests)
        in 400..<500 -> {
            try {
//                val exceptionResponse = response.body<BaseResponseWithData<T>>()
//                val message = exceptionResponse.message.plus(
//                    exceptionResponse.errors?.let {
//                        "\nErrors: $it"
//                    } ?: ""
//                )
                val message = "Bad client exception"

//                Result.Error(DataError.Remote.BadClientRequest(message))
                if (statusCode == 401) {
                    Result.Error(DataError.Remote.Unauthorized(message))
                } else Result.Error(DataError.Remote.BadClientRequest(message))
            } catch (_: NoTransformationFoundException) {
                Result.Error(DataError.Remote.Serialization)
            } catch (_: JsonConvertException) {
                Result.Error(DataError.Remote.Serialization)
            }
        }

        in 500..599 -> Result.Error(DataError.Remote.Server)
        else -> Result.Error(DataError.Remote.Unknown)
    }
}