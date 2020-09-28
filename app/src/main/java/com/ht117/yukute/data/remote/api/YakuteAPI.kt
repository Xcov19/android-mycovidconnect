package com.ht117.yukute.data.remote.api

import retrofit2.http.POST

interface YakuteAPI {

    @POST("")
    suspend fun registerUser(): Void
}