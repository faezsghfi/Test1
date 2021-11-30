package com.basalam.test1.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.basalam.test1.databinding.RowBinding
import com.basalam.test1.model.CompleteItem
import com.basalam.test1.model.Item
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.google.android.material.internal.ContextUtils
import android.content.Intent
import com.basalam.test1.view.DetailActivity
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.coroutines.NonDisposableHandle.parent

import androidx.core.content.res.ResourcesCompat

import android.graphics.Typeface
import com.basalam.test1.R


class DataAdapter (private var dataList: List<CompleteItem>): RecyclerView.Adapter<DataAdapter.ItemViewHolder>() {

    private lateinit var binding: RowBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder
    {
        binding = RowBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ItemViewHolder(binding.root)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int)
    {

        val face = ResourcesCompat.getFont(binding.names.context, R.font.sans )
        binding.names.text = dataList[position].animalName + " / " + dataList[position].flowerName
        binding.names.typeface = face
        Glide.with(binding.animalImage)
            .load(dataList[position].animalImage)
            .transform(RoundedCorners(45))
            .into(binding.animalImage)

        Glide.with(binding.flowerImage)
            .load(dataList[position].flowerImage)
            .transform(RoundedCorners(45))
            .into(binding.flowerImage)

        val (strr,intt) = getCommonChars(dataList[position].animalName , dataList[position].flowerName)
        binding.commonItems.text = "تعداد حروف مشترک : " + intt.toString()
        binding.commonItems.typeface = face
        binding.detailBtn.setOnClickListener {
            Log.e("faeze",position.toString())
            val intent = Intent(it.context, DetailActivity::class.java)
            intent.putExtra("id", dataList[position].id)
            intent.putExtra("animalName", dataList[position].animalName)
            intent.putExtra("flowerName", dataList[position].flowerName)
            intent.putExtra("animalImage", dataList[position].animalImage)
            intent.putExtra("flowerImage", dataList[position].flowerImage)
            intent.putExtra("common", strr)
            it.context.startActivity(intent)
        }
    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {

    }

    fun setData(dataList: List<CompleteItem>)
    {

        this.dataList=dataList
        notifyDataSetChanged()
    }

    fun getCommonChars(str1 : String , str2 : String) : Pair<String,Int>
    {
        val commonChars : ArrayList<Char> = ArrayList()
        for(i in 0..str1.length-1)
        {
            for(j in 0..str2.length-1)
            {
                if(str1.get(i) == str2.get(j)){
                    commonChars.add(str1.get(i))
                }
            }
        }
        return Pair(commonChars.toString(),commonChars.size)
    }
}