package com.example.do_eat_application_v1

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoListAdapter(private val realmResult: OrderedRealmCollection<Todo>,
                      private val click: (Long) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false))

    override fun getItemCount(): Int = realmResult.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = realmResult[position]
        holder.itemView.text1.text = item.title
        holder.itemView.text2.text = DateFormat.format("yyyy/MM/dd", item.date)

        holder.itemView.setOnClickListener { click(item.id) }
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view)
}