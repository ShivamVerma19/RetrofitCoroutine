package com.example.tetsprep.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testproject2.R
import com.example.testproject2.model.adapterModel

class JokesAdapter(private val context : Context, private val list : ArrayList<adapterModel>) :
    RecyclerView.Adapter<JokesAdapter.jokesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): jokesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false)
        return jokesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: jokesViewHolder, position: Int) {
        holder.txt_name.text = list[position].meme_name
        Glide.with(context).load(list[position].meme_img).into(holder.imgView)
    }

    class jokesViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val txt_name = itemView.findViewById<TextView>(R.id.txt_name)
        val imgView = itemView.findViewById<ImageView>(R.id.imgview)
    }
}