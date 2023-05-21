package com.jhonjto.todolist.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jhonjto.domain.TodoList
import com.jhonjto.todolist.ui.common.ScopedViewModel
import com.jhonjto.usecases.GetAllTodoList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllTodoList: GetAllTodoList
): ScopedViewModel() {

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) refresh()
            return _model
        }

    sealed class UiModel {
        object Loading: UiModel()
        class Content(val todoList: List<TodoList>): UiModel()
        class Navigation(val todoList: TodoList): UiModel()
        object RequestTodoList: UiModel()
    }

    init {
        initScope()
    }

    private fun refresh() {
        _model.value = UiModel.RequestTodoList
    }

    fun onCallTodoList() {
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(getAllTodoList.invoke())
        }
    }

    fun onTodoListClicked(todoList: TodoList) {
        _model.value = UiModel.Navigation(todoList)
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}