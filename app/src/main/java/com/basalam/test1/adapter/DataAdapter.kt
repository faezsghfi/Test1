package com.basalam.test1.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.basalam.test1.databinding.RowBinding
import com.basalam.test1.model.Item

class DataAdapter (private var dataList: List<Item>)
    : RecyclerView.Adapter<DataAdapter.ItemViewHolder>() {

    private lateinit var binding: RowBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder
    {
        binding = RowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(binding.root)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int)
    {
        binding.name.text =dataList[position].name
    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {

    }

    fun setData(dataList: List<Item>)
    {

        this.dataList=dataList
        notifyDataSetChanged()
    }


}