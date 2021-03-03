package ru.punkoff.translator.main.view.history

import android.os.Bundle
import androidx.lifecycle.Observer
import ru.punkoff.model.AppState
import ru.punkoff.model.DataModel
import ru.punkoff.translator.main.view.base.BaseActivity
import kotlinx.android.synthetic.main.activity_history.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.punkoff.translator.R

class HistoryActivity : BaseActivity<ru.punkoff.model.AppState, HistoryInteractor>() {

    override lateinit var model: HistoryViewModel
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        iniViewModel()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        model.getData("", false)
    }

    override fun setDataToAdapter(data: List<ru.punkoff.model.DataModel>) {
        adapter.setData(data)
    }

    private fun iniViewModel() {
        if (history_activity_recyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val viewModel: HistoryViewModel by viewModel()
        model = viewModel
        model.subscribe().observe(this@HistoryActivity, Observer<ru.punkoff.model.AppState> { renderData(it) })
    }

    private fun initViews() {
        history_activity_recyclerview.adapter = adapter
    }
}
