package me.abhishekkumar.gamehub.model

import com.google.gson.annotations.Expose

data class GameData(
    @Expose
    var id: Int,
    @Expose
    var category: Int,
    @Expose
    var cover: GameCover,
    @Expose
    var created_at: Int,
    @Expose
    var name: String,
    @Expose
    var popularity: Double,
    @Expose
    var summary: String,
    @Expose
    var updated_at: Long,
    @Expose
    var url: String
)
