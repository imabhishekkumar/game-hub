package me.abhishekkumar.gamehub.api

import io.reactivex.Single
import me.abhishekkumar.gamehub.model.GameData
import okhttp3.RequestBody
import retrofit2.http.*

public interface RetrofitClient {
    @Headers("user-key: API-KEY","Accept: application/json")
    @POST("games")
    fun getGames(@Body body: RequestBody): Single<List<GameData>>
}