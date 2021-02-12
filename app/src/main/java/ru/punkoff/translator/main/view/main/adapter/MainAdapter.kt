package ru.punkoff.translator.main.view.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main_recyclerview_item.view.*
import ru.punkoff.translator.R
import ru.punkoff.translator.main.model.data.DataModel

class MainAdapter(
    private val onItemClickListener: OnItemClickListener,
    private var data: List<DataModel>
) : RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {

    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_main_recyclerview_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(currentItem: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.header_textview_recycler_item.text = currentItem.text
                itemView.description_textview_recycler_item.text =
                    currentItem.meanings?.get(0)?.translation?.translation

                itemView.setOnClickListener { openInNewWindow(currentItem) }
            }
        }
    }

    private fun openInNewWindow(listItemData: DataModel) {
        onItemClickListener.onItemClick(listItemData)
    }
}