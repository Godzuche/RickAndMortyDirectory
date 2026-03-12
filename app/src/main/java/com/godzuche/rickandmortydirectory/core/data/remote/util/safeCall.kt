package com.godzuche.rickandmortydirectory.core.data.remote.util

import com.godzuche.rickandmortydirectory.core.domain.utils.DataError
import com.godzuche.rickandmortydirectory.core.domain.utils.Result
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse,
): Result<T, DataError.Remote> {
    val response = try {
        execute()
    } catch (_: SocketTimeoutException) {
        return Result.Error(DataError.Remote.RequestTimeout)
    } catch (_: HttpRequestTimeoutException) {
        return Result.Error(DataError.Remote.RequestTimeout)
    } catch (_: UnresolvedAddressException) {
        return Result.Error(DataError.Remote.NoInternet)
    } catch (e: Exception) {
        println("safeCall unknown: $e ${e.message}")
        currentCoroutineContext().ensureActive()
        return Result.Error(DataError.Remote.Unknown)
    }

    return responseToResult(response)
}