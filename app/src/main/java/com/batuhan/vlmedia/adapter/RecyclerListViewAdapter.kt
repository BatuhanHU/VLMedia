package com.batuhan.vlmedia.adapter

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.batuhan.vlmedia.CharacterDetailsActivity
import com.batuhan.vlmedia.R
import com.batuhan.vlmedia.model.RetroResult


class RecyclerListViewAdapter(var dataset: ArrayList<RetroResult>, var context:Context) :
        RecyclerView.Adapter<RecyclerListViewAdapter.ViewHolder>(){

    class ViewHolder(val linearLayout: LinearLayout) : RecyclerView.ViewHolder(linearLayout){
        var textName: TextView
        init {
            textName = linearLayout.findViewById(R.id.textView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val linearLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_list_layout, parent, false)
                as LinearLayout
        return ViewHolder(linearLayout)
    }

    override fun getItemCount() = dataset.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textName.text = dataset[position].name
        holder.itemView.setOnClickListener {
            openCharacterDetails(dataset[position])
        }
    }


    private fun openCharacterDetails(character : RetroResult){
        val intent = Intent(context, CharacterDetailsActivity::class.java)
        intent.putExtra("name", character.name)
        intent.putExtra("imageUrl", character.image)
        intent.putExtra("status", character.status)
        intent.putExtra("location", character.location.name)

        context.startActivity(intent)
    }


}