package me.abhishekkumar.gamehub.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import me.abhishekkumar.gamehub.api.RetrofitService
import me.abhishekkumar.gamehub.model.GameData
import okhttp3.MediaType
import okhttp3.RequestBody

class GamesViewModel : ViewModel() {
    private val gameService = RetrofitService()
    private val disposable = CompositeDisposable()
    val games = MutableLiveData<List<GameData>>()
    val gamesLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchFromRemote()
    }

    private fun fetchFromRemote() {
        loading.value = true
        var bodyText =
            "fields url,id,name,updated_at,summary,popularity,created_at,cover,category,platforms,rating,rating_count,release_dates,screenshots,similar_games; sort popularity desc;"
        var body = RequestBody.create(MediaType.parse("text/plain"), bodyText);
        disposable.add(
            gameService.getGames(body)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<GameData>>() {
                    override fun onSuccess(dogList: List<GameData>) {
                        games.value = dogList
                        gamesLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        gamesLoadError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }

                })
        )
    }

}