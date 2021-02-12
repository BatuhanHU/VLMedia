package com.batuhan.vlmedia.adapter

import android.app.AlertDialog
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.batuhan.vlmedia.R
import com.batuhan.vlmedia.model.RetroResult


class RecyclerListViewAdapter(var dataset: ArrayList<RetroResult>, var context:Context) :
        RecyclerView.Adapter<RecyclerListViewAdapter.ViewHolder>(){

    class doAsync(val handler: () -> Unit) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            handler()
            return null
        }
    }

    class ViewHolder(val linearLayout: LinearLayout) : RecyclerView.ViewHolder(linearLayout){
        var textName: TextView
        init {
            textName = linearLayout.findViewById(R.id.textView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        // create a new view
        val linearLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_view_list_layout, parent, false)
                as LinearLayout
        // set the view's size, margins, paddings and layout parameters
        return ViewHolder(linearLayout)
    }

    override fun getItemCount() = dataset.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textName.text = dataset[position].name

        holder.itemView.setOnClickListener {
            Log.d("AA", "am clicked")
        }
    }

}