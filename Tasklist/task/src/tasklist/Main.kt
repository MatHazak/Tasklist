package tasklist

import kotlinx.datetime.*
fun main() {
    val tasks = mutableListOf<Task>()
    while (true) {
        when (getAction()) {
            Action.ADD -> addTask(tasks)
            Action.PRINT -> printTasks(tasks)
            Action.END -> break
        }
    }
    println("Tasklist exiting!")
}

fun getAction(): Action {
    var action: Action? = null
    while (action == null) {
        println("Input an action (add, print, end):")
        when (readln()) {
            Action.ADD.noun -> action = Action.ADD
            Action.PRINT.noun -> action = Action.PRINT
            Action.END.noun -> action = Action.END
            else -> println("The input action is invalid")
        }
    }
    return action
}

fun printTasks(tasks: MutableList<Task>) {
    if (tasks.isEmpty()) {
        println("No tasks have been input")
        return
    }
    for (i in tasks.indices) {
        println("%-2d %s %s %S".format(i + 1, tasks[i].date, tasks[i].time, tasks[i].priority))
        for (line in tasks[i].description) {
            println("%2c %s".format(' ', line))
        }
        println()
    }
}

fun addTask(tasks: MutableList<Task>) {
    val priority = getTaskPriority()
    val date = getTaskDate()
    val time = getTaskTime()
    val description = getTaskDescription()
    if (description.isNotEmpty()) tasks.add(Task(priority, date, time, description))
}

fun getTaskDescription(): List<String> {
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

fun getTaskTime(): String {
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

fun getTaskDate(): String {
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

fun getTaskPriority(): Char {
    var line: String
    while (true) {
        println("Input the task priority (C, H, N, L):")
        line = readln().uppercase()
        if (line in setOf("C", "H", "N", "L")) break
    }
    return line[0]
}

enum class Action(val noun: String) {
    ADD("add"),
    PRINT("print"),
    END("end"),
}

data class Task(val priority: Char, val date: String, val time: String, val description: List<String>)