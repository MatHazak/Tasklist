package tasklist

fun main() {
    println("Input the tasks (enter a blank line to end):")
    val tasks = readTasksFromInput()
    if(tasks.isEmpty())
        println("No tasks have been input")
    else
        printTasks(tasks)
}

fun printTasks(tasks: List<String>) {
    for (i in tasks.indices)
        println("%-2d %s".format(i + 1, tasks[i]))
}

fun readTasksFromInput(): List<String> {
    val list = mutableListOf<String>()
    while (true) {
        val line = readln().trimIndent()
        if (line.isEmpty()) break
        list.add(line)
    }
    return list.toList()
}