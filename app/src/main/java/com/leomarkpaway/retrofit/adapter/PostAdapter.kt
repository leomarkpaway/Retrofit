package com.leomarkpaway.retrofit.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.leomarkpaway.retrofit.databinding.ItemPostBinding
import com.leomarkpaway.retrofit.model.PostDetails
import java.util.Locale


class PostAdapter(
    private val itemList: ArrayList<PostDetails>,
    private val onItemClicked: (PostDetails) -> Unit
) : RecyclerView.Adapter<PostAdapter.ItemHolder>(), Filterable {

    private val itemListHolder = ArrayList<PostDetails>(itemList)

    inner class ItemHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
       fun bind(item: PostDetails) = with(binding){
           tvTitle.text = item.title.capitalize(Locale.getDefault())
           root.setOnClickListener { onItemClicked(item) }
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context))
        return ItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val item = itemListHolder[position]
        holder.bind(item)
    }

    override fun getItemCount() = itemList.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val filterPattern =
                    constraint.toString().lowercase(Locale.getDefault()).trim { it <= ' ' }
                val filteredList = mutableListOf<PostDetails>()
                val results = FilterResults()
                if (constraint.isEmpty()) {
                    filteredList.addAll(itemListHolder)
                } else {
                    for (item in itemListHolder) {
                        if (item.title.contains(filterPattern, true)) filteredList.add(item)
                    }
                }
                results.values = filteredList
                return results
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                itemList.clear()
                @Suppress("UNCHECKED_CAST")
                itemList.addAll(results.values as ArrayList<PostDetails>)
                notifyDataSetChanged()
            }
        }
    }

}