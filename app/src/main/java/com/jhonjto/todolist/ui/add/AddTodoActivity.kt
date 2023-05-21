package com.jhonjto.todolist.ui.add

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.jhonjto.domain.TodoList
import com.jhonjto.todolist.databinding.ActivityAddTodoBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@AndroidEntryPoint
class AddTodoActivity : AppCompatActivity() {

    companion object {
        const val TODO_LIST = "todo_list"
    }

    private var _binding: ActivityAddTodoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddTodoViewModel by viewModels()

    private var isComplete = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAddTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras

        bundle?.let {
            bundle.apply {
                val todoList = com.jhonjto.todolist.ui.common.getSerializable(this@AddTodoActivity, TODO_LIST, TodoList::class.java)

                isComplete = todoList.isComplete == true
                if (isComplete) {
                    binding.imgCheck.visibility = View.INVISIBLE
                } else {
                    binding.imgCheck.visibility = View.VISIBLE
                }
                todoList.id?.let { result -> viewModel.findTodoListById(todoListId = result) }
            }
        }

        binding.imgCheck.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val note = binding.etNote.text.toString()

            if (title.isNotEmpty() && note.isNotEmpty()) {
                val dateTime = LocalDateTime.parse(getDateTime(), DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS"))
                viewModel.createNewTodoList(TodoList(id = null, title = title, note = note, date = dateTime, isComplete = false))
            } else {
                Toast.makeText(this, "Porfavor ingrese un Titulo y una Nota", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
        }

        binding.imgBackArrow.setOnClickListener {
            onBackPressed()
        }

        viewModel.model.observe(this, Observer(::updateUi))
    }

    private fun updateUi(model: AddTodoViewModel.UiModel) = with(binding) {
        val todoList = model.todoList
        binding.etTitle.setText(todoList.title)
        binding.etNote.setText(todoList.note)
    }

    private fun getDateTime(): String? {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS", Locale.getDefault())
        val date = Date()
        return dateFormat.format(date)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}