package tasklist

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

class TaskList {
    var tasks = mutableListOf<Task>()

    fun addTask() {
        val priority = getTaskPriority()
        val date = getTaskDate()
        val time = getTaskTime()
        val description = getTaskDescription()
        if (description.isNotEmpty()) tasks.add(Task(priority, date, time, description))
    }

    fun printTasks() {
        if (tasks.isEmpty()) {
            println("No tasks have been input")
            return
        }
        Draw.header()
        for (i in tasks.indices) {
            Draw.task(i + 1, tasks[i])
            Draw.horizontalLine()
        }
    }

    fun editTask() {
        printTasks()
        if (tasks.isEmpty()) return
        while (true) {
            println("Input the task number (1-${tasks.size}):")
            try {
                val number = readln().toInt()
                updateTask(tasks[number - 1])
                println("The task is changed")
                break
            } catch (e: RuntimeException) {
                println("Invalid task number")
            }
        }
    }

    fun deleteTask() {
        printTasks()
        if (tasks.isEmpty()) return
        while (true) {
            println("Input the task number (1-${tasks.size}):")
            try {
                val number = readln().toInt()
                tasks.removeAt(number - 1)
                println("The task is deleted")
                break
            } catch (e: RuntimeException) {
                println("Invalid task number")
            }
        }
    }

    private fun updateTask(task: Task) {
        while (true) {
            println("Input a field to edit (priority, date, time, task):")
            when (readln().lowercase()) {
                "priority" -> {
                    task.priority = getTaskPriority()
                    break
                }
                "date" -> {
                    task.date = getTaskDate()
                    break
                }
                "time" -> {
                    task.time = getTaskTime()
                    break
                }
                "task" -> {
                    val newDescription = getTaskDescription()
                    if (newDescription.isNotEmpty())
                        task.description = newDescription
                    break
                }
                else -> println("Invalid field")
            }
        }
    }

    private fun getTaskDescription(): List<String> {
        println("Input a new task (enter a blank line to end):")
        val description = mutableListOf<String>()
        while (true) {
            val line = readln().trimIndent()
            if (line.isEmpty()) break
            description.add(line)
        }
        if(description.isEmpty()) println("The task is blank")
        return description
    }

    private fun getTaskTime(): String {
        var time: String
        while (true) {
            println("Input the time (hh:mm):")
            try {
                val (h, m) = readln().split(':')
                val localTime = LocalTime(h.toInt(), m.toInt())
                time = localTime.toString()
                break
            } catch (e: RuntimeException) {
                println("The input time is invalid")
            }
        }
        return time
    }

    private fun getTaskDate(): String {
        var date: String
        while (true) {
            println("Input the date (yyyy-mm-dd):")
            try {
                val (y, m, d) = readln().split('-')
                val localDate = LocalDate(y.toInt(), m.toInt(), d.toInt())
                date = localDate.toString()
                break
            } catch (e: RuntimeException) {
                println("The input date is invalid")
            }
        }
        return date
    }

    private fun getTaskPriority(): Char {
        var line: String
        while (true) {
            println("Input the task priority (C, H, N, L):")
            line = readln().uppercase()
            if (line in setOf("C", "H", "N", "L")) break
        }
        return line[0]
    }
}

data class Task(var priority: Char, var date: String, var time: String, var description: List<String>)