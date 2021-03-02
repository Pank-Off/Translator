package ru.punkoff.translator.main.view.main

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.punkoff.translator.R
import ru.punkoff.translator.main.model.data.AppState
import ru.punkoff.translator.main.model.data.DataModel
import ru.punkoff.translator.main.utils.convertMeaningsToString
import ru.punkoff.translator.main.view.base.BaseActivity
import ru.punkoff.translator.main.view.descriptionscreen.DescriptionActivity
import ru.punkoff.translator.main.view.main.adapter.MainAdapter
import ru.punkoff.translator.main.view.main.adapter.OnItemClickListener
import ru.punkoff.translator.main.view.main.searchDialog.OnSearchClickListener
import ru.punkoff.translator.main.view.main.searchDialog.SearchDialogFragment
import ru.punkoff.translator.main.viewmodel.MainViewModel

class MainActivity : BaseActivity<AppState>() {

    private val mainViewModel by viewModel<MainViewModel>()
    private var adapter: MainAdapter? = null
    private val onItemClickListener = object : OnItemClickListener {
        override fun onItemClick(data: DataModel) {
            startActivity(
                DescriptionActivity.getIntent(
                    this@MainActivity, data.text,
                    convertMeaningsToString(data.meanings), data.meanings?.get(0)?.imageUrl
                )
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        search_fab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(object : OnSearchClickListener {
                override fun onClick(searchWord: String) {
                    mainViewModel.getData(searchWord, true)
                }
            })

            mainViewModel.subscribe().observe(this) {
                renderData(it)
            }

            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
                if (dataModel == null || dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                } else {
                    showViewSuccess()
                    if (adapter == null) {
                        main_activity_recyclerview.layoutManager =
                            LinearLayoutManager(applicationContext)
                        main_activity_recyclerview.adapter =
                            MainAdapter(onItemClickListener, dataModel)
                    } else {
                        adapter?.setData(dataModel)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    progress_bar_horizontal.visibility = View.VISIBLE
                    progress_bar_round.visibility = View.GONE
                    progress_bar_horizontal.progress = appState.progress
                } else {
                    progress_bar_horizontal.visibility = View.GONE
                    progress_bar_round.visibility = View.VISIBLE
                }
            }
            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        error_textview.text = error ?: getString(R.string.undefined_error)
        reload_button.setOnClickListener {
            mainViewModel.getData("hi", true)
        }
    }

    private fun showViewSuccess() {
        success_linear_layout.visibility = View.VISIBLE
        loading_frame_layout.visibility = View.GONE
        error_linear_layout.visibility = View.GONE
    }

    private fun showViewLoading() {
        success_linear_layout.visibility = View.GONE
        loading_frame_layout.visibility = View.VISIBLE
        error_linear_layout.visibility = View.GONE
    }

    private fun showViewError() {
        success_linear_layout.visibility = View.GONE
        loading_frame_layout.visibility = View.GONE
        error_linear_layout.visibility = View.VISIBLE
    }

    companion object {
        private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG =
            "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"
    }
}