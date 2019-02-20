package com.hfad.simplechat.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hfad.simplechat.R
import com.hfad.simplechat.ViewHolder
import kotlinx.android.synthetic.main.item_message.view.*

@Suppress("DEPRECATION")
class ChatAdapter(context: Context, messages: ArrayList<String>): RecyclerView.Adapter<ViewHolder>() {

    var messages = ArrayList<String>()
    var inflater: LayoutInflater

    init {
        this.messages = messages
        this.inflater = LayoutInflater.from(context)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {

        val msg = messages[p1]
        p0.message.text = msg

    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {

        val view = inflater.inflate(R.layout.item_message, p0,false)

        return ViewHolder(view)

    }

}