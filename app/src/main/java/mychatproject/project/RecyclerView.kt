package mychatproject.project

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/* This is the  Facepalm Messenger
Created by: Roni Jumpponen */

//RecyclerView for putting the messages into scrollable views that can be shown on the screen.
class MyRecyclerViewAdapter(private val context: Context, private val myMessages: List<String>) :
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(vg: ViewGroup, vt: Int): MyViewHolder {
        Log.d("ZZZ", "onCreateViewHolder()")
        val itemView = LayoutInflater.from(context).inflate(R.layout.myitemlayout, vg, false)
        return MyViewHolder(itemView)
    }

    //used for getting the itemcount (list size)
    override fun getItemCount(): Int {
        return myMessages.count()
    }

    //responsible for showing the right message from the list on right position
    override fun onBindViewHolder(vh: MyViewHolder, pos: Int) {
        Log.d("ZZZ", "onBindViewHolder($pos)")
        vh.itemView.findViewById<TextView>(R.id.textView2).text = myMessages[pos]
    }

}

//Holds the views like the name suggests.
class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)