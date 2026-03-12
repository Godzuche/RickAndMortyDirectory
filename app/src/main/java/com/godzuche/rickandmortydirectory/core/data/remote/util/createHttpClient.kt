package com.godzuche.rickandmortydirectory.core.data.remote.util

import com.godzuche.rickandmortydirectory.core.data.remote.HttpClientFactory
import io.ktor.client.engine.HttpClientEngine

fun createHttpClient(
    engine: HttpClientEngine,
) = HttpClientFactory.create(engine)