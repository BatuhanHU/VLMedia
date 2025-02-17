package com.pallstock.pallshipping.network

import com.batuhan.vlmedia.model.RetroCharacterListResponse
import retrofit2.Call
import retrofit2.http.*

interface RequestService {

    @GET("/api/character")
    fun characters(
        @Query("page") page: Int,
    ): Call<RetroCharacterListResponse>

}