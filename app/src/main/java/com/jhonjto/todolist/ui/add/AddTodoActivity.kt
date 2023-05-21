package com.jhonjto.todolist.ui.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.jhonjto.todolist.databinding.ActivityAddTodoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTodoActivity : AppCompatActivity() {

    companion object {
        const val TODO_LIST = "AddTodoActivity:todo_list"
    }

    private var _binding: ActivityAddTodoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddTodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAddTodoBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}