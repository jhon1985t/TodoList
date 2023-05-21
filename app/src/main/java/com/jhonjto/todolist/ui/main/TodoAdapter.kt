package com.jhonjto.todolist.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jhonjto.domain.TodoList
import com.jhonjto.todolist.R
import com.jhonjto.todolist.databinding.ListItemBinding
import com.jhonjto.todolist.ui.common.basicDiffUtil
import com.jhonjto.todolist.ui.common.createdDateFormatted
import com.jhonjto.todolist.ui.common.inflate

class TodoAdapter(private val listener: (TodoList) -> Unit):
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    var todoList: List<TodoList> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = parent.inflate(R.layout.list_item, false)
        return TodoViewHolder(view)
    }

    override fun getItemCount(): Int = todoList.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val item = todoList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { listener(item) }
    }

    class TodoViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ListItemBinding.bind(view)
        fun bind(todoList: TodoList) = with(binding) {
            tvTitle.text = todoList.title
            tvNote.text = todoList.note
            tvDate.text = createdDateFormatted(todoList.date)
        }
    }
}
