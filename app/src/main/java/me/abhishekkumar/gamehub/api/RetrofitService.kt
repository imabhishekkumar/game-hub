package me.abhishekkumar.gamehub.api

import io.reactivex.Single
import me.abhishekkumar.gamehub.model.GameData
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class RetrofitService {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api-v3.igdb.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private val retrofitClient: RetrofitClient = retrofit.create(RetrofitClient::class.java)
    fun getGames(body: RequestBody): Single<List<GameData>> {
        return retrofitClient.getGames(body)
    }
}