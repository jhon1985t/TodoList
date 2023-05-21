package com.jhonjto.todolist.ui.add

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.jhonjto.domain.TodoList
import com.jhonjto.todolist.databinding.ActivityAddTodoBinding
import dagger.hilt.android.AndroidEntryPoint

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
                val todoList = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    intent.getSerializableExtra(TODO_LIST, TodoList::class.java)
                } else {
                    TODO("VERSION.SDK_INT < TIRAMISU")
                }
                if (todoList != null) {
                    isComplete = todoList.isComplete == true
                    if (isComplete) {
                        binding.imgCheck.visibility = View.INVISIBLE
                    } else {
                        binding.imgCheck.visibility = View.VISIBLE
                    }
                    todoList.id?.let { result -> viewModel.findTodoListById(todoListId = result) }
                }
            }
        }

        binding.imgCheck.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val note = binding.etNote.text.toString()

            if (title.isNotEmpty() && note.isNotEmpty()) {
                viewModel.createNewTodoList(TodoList(title = title, note = note, isComplete = false))
            } else {
                Toast.makeText(this, "Porfavor ingrese un Titulo y una Nota", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
        }

        viewModel.model.observe(this, Observer(::updateUi))
    }

    private fun updateUi(model: AddTodoViewModel.UiModel) = with(binding) {
        val todoList = model.todoList
        binding.etTitle.setText(todoList.title)
        binding.etNote.setText(todoList.note)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}