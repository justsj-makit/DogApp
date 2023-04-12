package dev.mitigate.dogapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import dev.mitigate.dogapp.R
import dev.mitigate.dogapp.databinding.DogListItemBinding
import dev.mitigate.dogapp.models.DogModel
import timber.log.Timber

class DogListAdapter(val onItemClick: (DogModel) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var itemList = listOf<DogModel>()
        set(value) {
            field = value
            Timber.e("adapter items set")
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.dog_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    private inner class ViewHolder(private val binding: DogListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: DogModel) {
            with(binding) {
                uiModel = model

                root.setOnClickListener {
                    onItemClick(model)
                }
            }
        }
    }
}