package repositories

import entities.Todo

class TodoRepository : ITodoRepository {
    private val data = ArrayList<Todo>()

    override fun getAllTodos(): ArrayList<Todo> = data

    override fun addTodo(todo: Todo) {
        data.add(todo)
    }

    override fun removeTodo(id: Int): Boolean {
        val todo = data.find { it.id == id } ?: return false
        data.remove(todo)
        return true
    }

    override fun updateTodo(id: Int, newTitle: String, isFinished: Boolean): Boolean {
        val todo = data.find { it.id == id } ?: return false

        todo.title = newTitle
        todo.isFinished = isFinished
        return true
    }

    override fun searchTodo(keyword: String): List<Todo> {
        return data.filter { it.title.contains(keyword, ignoreCase = true) }
    }

    override fun sortTodo(by: String, ascending: Boolean) {

        when (by.lowercase()) {
            "id" -> data.sortBy { it.id }
            "title" -> data.sortBy { it.title }
            "finished" -> data.sortBy { it.isFinished }
            else -> return
        }

        if (!ascending) {
            data.reverse()
        }
    }
}
