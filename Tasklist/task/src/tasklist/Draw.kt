package tasklist

import kotlinx.datetime.*
import kotlin.math.min

object Draw {

    fun header() {
        horizontalLine()
        println("| N  |    Date    | Time  | P | D |                   Task                     |")
        horizontalLine()
    }

    fun task(row: Int, task: Task) {
        val compactedDescription = compactDescription(task.description)
        println("| %-2d | %s | %s | %s | %s |%-44s|"
            .format(row, task.date, task.time, priorityColor(task), dueColor(task), compactedDescription.first()))
        for (i in 1..compactedDescription.lastIndex) {
            println("|%4c|%12c|%7c|%3c|%3c|%-44s|"
                .format(' ', ' ', ' ', ' ', ' ', compactedDescription[i]))
        }
    }

    fun horizontalLine() {
        println("+----+------------+-------+---+---+--------------------------------------------+")
    }

    private fun compactDescription(lines: List<String>): List<String> {
        val compacted = mutableListOf<String>()
        for(line in lines) {
            var counter = 0
            while (counter < line.length) {
                compacted.add(line.substring(counter, min(counter + 44, line.length)))
                counter += 44
            }
        }
        return compacted.toList()
    }

    private fun priorityColor(task: Task): String {
        return when(task.priority) {
            'C' -> "\u001B[101m \u001B[0m"
            'H' -> "\u001B[103m \u001B[0m"
            'N' -> "\u001B[102m \u001B[0m"
            else -> "\u001B[104m \u001B[0m"
        }
    }

    private fun dueColor(task: Task): String {
        val (y, m, d) = task.date.split('-')
        val taskDate = LocalDate(y.toInt(), m.toInt(), d.toInt())
        val currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        val duration = currentDate.daysUntil(taskDate)
        return when {
            duration < 0 -> "\u001B[101m \u001B[0m" // overdue
            duration == 0 -> "\u001B[103m \u001B[0m" // today
            else -> "\u001B[102m \u001B[0m" // in time
        }
    }
}