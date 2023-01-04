package com.ru.gridrecyclerview.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ru.gridrecyclerview.R
import com.ru.gridrecyclerview.model.interfaces.TransactionInterface
import com.ru.gridrecyclerview.model.data.GridData

class GridAdapter(
    val transactionInterface: TransactionInterface,
    val list: List<GridData>
) : RecyclerView.Adapter<GridAdapter.GridViewHolder>() {
    inner class GridViewHolder(var view : View, var transactionInterface: TransactionInterface) : RecyclerView.ViewHolder(view) {
        var photo = view.findViewById<ImageView>(R.id.rv_photo)


        fun setPhoto(photoUrl : String){
            //Set photo from Newtwork
            Glide.with(view.context)
                .load(photoUrl)
                .into(photo)

            //Gives and argument to another fragment
            photo.setOnClickListener {
                transactionInterface.transaction(photoUrl)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        return GridViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.grid_item, parent, false),transactionInterface)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val listItem = list[position]
        holder.setPhoto(listItem.download_url)
    }

    override fun getItemCount(): Int = list.size
}