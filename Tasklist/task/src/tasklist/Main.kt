package tasklist

fun main() {
    val tasks = mutableListOf<List<String>>()
    while (true) {
        when (getAction()) {
            Action.ADD -> getTask(tasks)
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

fun printTasks(tasks: MutableList<List<String>>) {
    if (tasks.isEmpty()) {
        println("No tasks have been input")
        return
    }
    for (i in tasks.indices) {
        println("%-2d %s".format(i + 1, tasks[i].first()))
        for (j in 1..tasks[i].lastIndex) {
            println("%2c %s".format(' ', tasks[i][j]))
        }
        println()
    }
}

fun getTask(tasks: MutableList<List<String>>) {
    val task = mutableListOf<String>()
    println("Input a new task (enter a blank line to end):")
    while (true) {
        val line = readln().trimIndent()
        if (line.isEmpty()) break
        task.add(line)
    }
    if(task.isEmpty()) println("The task is blank")
    else tasks.add(task)
}

enum class Action(val noun: String) {
    ADD("add"),
    PRINT("print"),
    END("end"),
}