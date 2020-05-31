package me.abhishekkumar.gamehub.utills

import me.abhishekkumar.gamehub.model.GameData
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

public interface RetrofitClient {
    @Headers("user-key: ADD-YOUR-API-KEY-HERE","Accept: application/json")
    @POST("games")
    fun getGames(@Body body: RequestBody): Call<List<GameData>>
}