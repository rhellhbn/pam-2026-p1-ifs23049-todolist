package views

import services.ITodoService
import utils.InputUtil

class TodoView(private val todoService: ITodoService) {

    fun showTodos() {
        while (true) {
            todoService.showTodos()

            println("Menu:")
            println("1. Tambah")
            println("2. Ubah")
            println("3. Cari")
            println("4. Urutkan")
            println("5. Hapus")
            println("x. Keluar")

            val input = InputUtil.input("Pilih")
            val stop = when (input) {
                "1" -> { addTodo(); false }
                "2" -> { println(); updateTodo(); false }
                "3" -> { println(); searchTodo(); false }
                "4" -> { println(); sortTodo(); false }
                "5" -> { removeTodo(); false }
                "x" -> true
                else -> {
                    println("[!] Pilihan tidak dimengerti.")
                    false
                }
            }

            if (stop) break
            println()
        }
    }

    fun addTodo() {
        println("[Menambah Todo]")
        val title = InputUtil.input("Judul (x Jika Batal)")

        if (title == "x") return
        todoService.addTodo(title)
    }

    fun removeTodo() {
        println("[Menghapus Todo]")
        val strIdTodo = InputUtil.input("[ID Todo] yang dihapus (x Jika Batal)")

        if (strIdTodo == "x") return
        todoService.removeTodo(strIdTodo.toInt())
    }

    /**
     * ✅ UPDATE TODO
     */
    fun updateTodo() {
        println("[Memperbarui Todo]")

        val strIdTodo = InputUtil.input("[ID Todo] yang diubah (x Jika Batal)")
        if (strIdTodo == "x") {
            println("[x] Pembaruan todo dibatalkan.")
            return
        }

        val newTitle = InputUtil.input("Judul Baru (x Jika Batal)")
        if (newTitle == "x") {
            println("[x] Pembaruan todo dibatalkan.")
            return
        }

        val finishedInput = InputUtil.input("Apakah todo sudah selesai? (y/n)")
        val isFinished = finishedInput.lowercase() == "y"

        todoService.updateTodo(strIdTodo.toInt(), newTitle, isFinished)
    }

    /**
     * ✅ SEARCH TODO
     */
    fun searchTodo() {
        println("[Mencari Todo]")

        val keyword = InputUtil.input("Kata kunci (x Jika Batal)")
        if (keyword == "x") {
            println("[x] Pencarian todo dibatalkan.")
            return
        }

        todoService.searchTodo(keyword)
    }

    /**
     * ✅ SORT TODO
     */
    fun sortTodo() {
        println("[Mengurutkan Todo]")

        val by = InputUtil.input("Urutkan berdasarkan (id/title/finished) (x Jika Batal)")
        if (by == "x") {
            println("[x] Pengurutan todo dibatalkan.")
            return
        }

        val ascInput = InputUtil.input("Urutkan secara ascending? (y/n)")
        val isAscending = ascInput.lowercase() != "n"

        todoService.sortTodo(by, isAscending)
    }
}
