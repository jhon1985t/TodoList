package com.jhonjto.todolist.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jhonjto.domain.TodoList
import com.jhonjto.todolist.ui.common.ScopedViewModel
import com.jhonjto.usecases.CreateNewTodoList
import com.jhonjto.usecases.TodoListById
import com.jhonjto.usecases.ToggleTodoListCheck
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTodoViewModel @Inject constructor(
    private val findTodoListById: TodoListById,
    private val toggleTodoListCheck: ToggleTodoListCheck,
    private val createNewTodoList: CreateNewTodoList
): ScopedViewModel() {

    class UiModel(val todoList: TodoList)

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() = _model

    fun findTodoListById(todoListId: Int = -1) = launch {
        _model.value = UiModel(findTodoListById.invoke(todoListId))
    }

    fun createNewTodoList(todoList: TodoList) = launch {
        createNewTodoList.invoke(todoList)
    }

    fun onFavoriteClicked() = launch {
        _model.value?.todoList?.let {
            _model.value = UiModel(toggleTodoListCheck.invoke(it))
        }
    }
}