package services

import entities.Todo
import repositories.ITodoRepository

class TodoService(private val repo: ITodoRepository) : ITodoService {

    override fun showTodos() {
        println("Daftar Todo:")
        val todos = repo.getAllTodos()

        if (todos.isEmpty()) {
            println("- Data todo belum tersedia!")
            return
        }

        todos.forEach { println(it) }
    }

    override fun addTodo(title: String) {
        repo.addTodo(Todo(title = title))
    }

    override fun updateTodo(id: Int, newTitle: String, isFinished: Boolean) {
        val success = repo.updateTodo(id, newTitle, isFinished)
        if (!success) {
            println("[!] Gagal memperbarui todo dengan ID: $id.")
        }
    }

    override fun searchTodo(keyword: String) {
        val result = repo.searchTodo(keyword)
        if (result.isEmpty()) {
            println("- Data todo tidak ditemukan!")
        } else {
            result.forEach { println(it) }
        }
    }

    override fun sortTodo(by: String, ascending: Boolean) {
        repo.sortTodo(by, ascending)
    }

    override fun removeTodo(id: Int) {
        if (!repo.removeTodo(id)) {
            println("[!] Gagal menghapus todo dengan ID: $id.")
        }
    }
}
