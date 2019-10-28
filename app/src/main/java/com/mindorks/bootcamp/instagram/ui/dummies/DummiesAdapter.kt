package com.mindorks.bootcamp.instagram.ui.dummies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mindorks.bootcamp.instagram.data.model.Dummy
import com.mindorks.bootcamp.instagram.databinding.ItemViewDummiesBinding
import com.mindorks.bootcamp.instagram.utils.log.Logger

class DummiesAdapter(
    private val dummies: ArrayList<Dummy>
) : RecyclerView.Adapter<DummiesAdapter.DummyItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DummyItemViewHolder {
        return DummyItemViewHolder(
            ItemViewDummiesBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            DummyItemViewModel()
        )
    }

    fun appendData(dummies: List<Dummy>) {
        val oldCount = itemCount
        this.dummies.addAll(dummies)
        val currentCount = itemCount
        if (oldCount == 0 && currentCount > 0)
            notifyDataSetChanged()
        else if (oldCount > 0 && currentCount > oldCount)
            notifyItemRangeChanged(oldCount - 1, currentCount - oldCount)
    }

    override fun getItemCount(): Int = dummies.size

    override fun onBindViewHolder(holder: DummyItemViewHolder, position: Int) {
        holder.bind(dummies[position])
    }

    class DummyItemViewHolder(
        private val binding: ItemViewDummiesBinding,
        private val viewModel: DummyItemViewModel
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Dummy) {
            viewModel.updateData(data)
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }

    class DummyItemViewModel(var dummy: Dummy? = null) {

        fun onItemClick() {
            Logger.d("DEBUG", "DummyItemViewModel: onItemClick", dummy ?: "NULL")
        }

        fun updateData(dummy: Dummy) {
            this.dummy = dummy
        }
    }
}