package com.hfad.simplechat

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    var message: TextView = itemView.findViewById(R.id.message_item)

}