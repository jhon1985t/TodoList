package com.jhonjto.todolist.ui.add

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.jhonjto.domain.TodoList
import com.jhonjto.todolist.R
import com.jhonjto.todolist.databinding.ActivityAddTodoBinding
import com.jhonjto.todolist.ui.common.startActivity
import com.jhonjto.todolist.ui.main.MainActivity
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

        receiver(bundle)

        createNote()

        deleteById(bundle)

        binding.fabCheck.setOnClickListener { viewModel.onCheckClicked() }

        binding.imgBackArrow.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        viewModel.model.observe(this, Observer(::updateUi))
    }

    private fun deleteById(bundle: Bundle?) {
        binding.imgDelete.setOnClickListener {
            bundle?.let {
                bundle.apply {
                    val todoList = com.jhonjto.todolist.ui.common.getSerializable(
                        this@AddTodoActivity,
                        TODO_LIST,
                        TodoList::class.java
                    )
                    todoList.id?.let { result -> viewModel.deleteById(id = result) }
                    startActivity<MainActivity> { }
                }
            }
        }
    }

    private fun createNote() {
        binding.imgCheck.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val note = binding.etNote.text.toString()

            if (title.isNotEmpty() && note.isNotEmpty()) {
                val dateTime = LocalDateTime.parse(
                    getDateTime(),
                    DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss.SSS")
                )
                viewModel.createNewTodoList(
                    TodoList(
                        id = null,
                        title = title,
                        note = note,
                        date = dateTime,
                        isComplete = false
                    )
                )
                startActivity<MainActivity> { }
            } else {
                Toast.makeText(this, "Porfavor ingrese un Titulo y una Nota", Toast.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
        }
    }

    private fun receiver(bundle: Bundle?) {
        bundle?.let {
            bundle.apply {
                val todoList = com.jhonjto.todolist.ui.common.getSerializable(
                    this@AddTodoActivity,
                    TODO_LIST,
                    TodoList::class.java
                )
                todoList.id?.let { result -> viewModel.findTodoListById(todoListId = result) }
            }
        }
    }

    private fun updateUi(model: AddTodoViewModel.UiModel) = with(binding) {
        val todoList = model.todoList
        binding.etTitle.setText(todoList.title)
        binding.etNote.setText(todoList.note)

        val icon = if (todoList.isComplete) R.drawable.ic_baseline_check_complete_24 else R.drawable.ic_baseline_check_incomplete_24
        fabCheck.setImageDrawable(ContextCompat.getDrawable(this@AddTodoActivity, icon))

        if (todoList.isComplete) {
            binding.imgCheck.visibility = View.INVISIBLE
        } else {
            binding.imgCheck.visibility = View.VISIBLE
        }
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