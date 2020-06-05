package me.abhishekkumar.gamehub.model

import com.google.gson.annotations.Expose

data class GameCover (
    @Expose
    var id: Int,
    @Expose
    var image_id: String?
)