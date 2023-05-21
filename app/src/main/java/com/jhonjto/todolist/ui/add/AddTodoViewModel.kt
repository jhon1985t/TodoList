package com.jhonjto.todolist.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jhonjto.domain.TodoList
import com.jhonjto.todolist.ui.common.ScopedViewModel
import com.jhonjto.usecases.TodoListById
import com.jhonjto.usecases.ToggleTodoListCheck
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class AddTodoViewModel @Inject constructor(
    @Named("todoListId") private val todoListId: Int,
    private val findTodoListById: TodoListById,
    private val toggleTodoListCheck: ToggleTodoListCheck
): ScopedViewModel() {

    class UiModel(val todoList: TodoList)

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) findTodoListById()
            return _model
        }

    private fun findTodoListById() = launch {
        _model.value = UiModel(findTodoListById.invoke(todoListId))
    }

    fun onFavoriteClicked() = launch {
        _model.value?.todoList?.let {
            _model.value = UiModel(toggleTodoListCheck.invoke(it))
        }
    }
}