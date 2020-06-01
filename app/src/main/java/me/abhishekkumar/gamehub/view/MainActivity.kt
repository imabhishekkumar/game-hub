package me.abhishekkumar.gamehub.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import me.abhishekkumar.gamehub.R
import me.abhishekkumar.gamehub.viewmodel.GamesViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: GamesViewModel
    private val gamesListAdapter = GamesListAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(GamesViewModel::class.java)
        viewModel.refresh()

        gameMainRV.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = gamesListAdapter
        }

        refreshLayout.setOnRefreshListener {
            gameMainRV.visibility = View.GONE
            listError.visibility = View.GONE
            loadingView.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }
        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.games.observe(this, Observer { games ->
            games?.let {
                gameMainRV.visibility = View.VISIBLE
                gamesListAdapter.updateGameList(games)
            }
        })

        viewModel.gamesLoadError.observe(this, Observer { isError ->
            isError?.let {
                listError.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loadingView.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    listError.visibility = View.GONE
                    gameMainRV.visibility = View.GONE
                }
            }
        })
    }
}