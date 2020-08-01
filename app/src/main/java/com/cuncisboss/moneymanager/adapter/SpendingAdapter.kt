package com.cuncisboss.moneymanager.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cuncisboss.moneymanager.R
import com.cuncisboss.moneymanager.databinding.ItemSpendingBinding
import com.cuncisboss.moneymanager.model.Spending


class SpendingAdapter(private val itemClickListener: ItemClickListener): RecyclerView.Adapter<SpendingAdapter.SpendingViewHolder>() {

    private var spendingList = ArrayList<Spending>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpendingViewHolder {
        return SpendingViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_spending,
                parent,
                false)
        )
    }

    override fun getItemCount(): Int = spendingList.size

    override fun onBindViewHolder(holder: SpendingViewHolder, position: Int) {
        holder.binding.spending = spendingList[position]
        holder.binding.cardSpending.setOnClickListener {
            itemClickListener.onItemClick(holder.binding.cardSpending, spendingList[position])
        }
    }

    fun setSpendingList(spendingList: List<Spending>) {
        this.spendingList.clear()
        this.spendingList.addAll(spendingList)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        spendingList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, spendingList.size)
    }

    inner class SpendingViewHolder(val binding: ItemSpendingBinding)
        : RecyclerView.ViewHolder(binding.root)
}

interface ItemClickListener {
    fun onItemClick(view: View, spending: Spending)
}