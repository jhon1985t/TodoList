package com.jhonjto.todolist.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhonjto.todolist.databinding.ActivityMainBinding
import com.jhonjto.todolist.ui.add.AddTodoActivity
import com.jhonjto.todolist.ui.common.startActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: TodoAdapter

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val manager = LinearLayoutManager(this)
        binding.recycler.layoutManager = manager
        binding.recycler.setHasFixedSize(true)
        adapter = TodoAdapter(viewModel::onTodoListClicked)
        binding.recycler.adapter = adapter
        viewModel.model.observe(this, Observer(::updateUi))

        binding.fabAddTodo.setOnClickListener {
            startActivity<AddTodoActivity> {}
        }
    }

    private fun updateUi(model: MainViewModel.UiModel) {
        binding.progress.visibility = if (model is MainViewModel.UiModel.Loading) View.VISIBLE else View.GONE

        when (model) {
            is MainViewModel.UiModel.Content -> adapter.todoList = model.todoList
            is MainViewModel.UiModel.Navigation -> {
                val intent = Intent(this, AddTodoActivity::class.java)
                intent.putExtra(AddTodoActivity.TODO_LIST, model.todoList as java.io.Serializable)
                startActivity(intent)
            }
            MainViewModel.UiModel.RequestTodoList -> {
                viewModel.onCallTodoList()
            }
            else -> {}
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}