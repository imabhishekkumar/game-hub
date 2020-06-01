package me.abhishekkumar.gamehub.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_game.view.*
import me.abhishekkumar.gamehub.R
import me.abhishekkumar.gamehub.model.GameData

class GamesListAdapter(val gamesList: ArrayList<GameData>) :
    RecyclerView.Adapter<GamesListAdapter.GameViewHolder>() {

    fun updateGameList(newGamesList: List<GameData>) {
        gamesList.clear()
        gamesList.addAll(newGamesList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_game, parent, false)
        return GameViewHolder((view))
    }

    override fun getItemCount() = gamesList.size


    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.view.gameNameTV.text =gamesList[position].name
    }

    class GameViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}