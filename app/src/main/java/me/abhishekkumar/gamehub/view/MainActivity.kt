package me.abhishekkumar.gamehub.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import me.abhishekkumar.gamehub.R
import me.abhishekkumar.gamehub.model.GameData
import me.abhishekkumar.gamehub.utills.RetrofitClient
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api-v3.igdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        var bodyText = "fields url,id,name,updated_at,summary,popularity,created_at,cover,category,platforms,rating,rating_count,release_dates,screenshots,similar_games; sort popularity desc;"
        var body = RequestBody.create(MediaType.parse("text/plain"), bodyText);
        val retrofitClient: RetrofitClient = retrofit.create(RetrofitClient::class.java)
        val call = retrofitClient.getGames(body)
        call.enqueue(object : Callback<List<GameData>> {
            override fun onFailure(call: Call<List<GameData>>, t: Throwable) {

                Toast.makeText(applicationContext, "Please try again later", Toast.LENGTH_SHORT)
                    .show()
                Log.d("Message Failure", t.message)
            }

            override fun onResponse(
                call: Call<List<GameData>>,
                response: Response<List<GameData>>
            ) {
                if (response.isSuccessful) {
                    Log.d("Message Successful", Gson().toJson(response.body()))
                } else {
                    Log.d("Message !Successful", Gson().toJson(response.errorBody()))
                }
            }
        })
    }
}