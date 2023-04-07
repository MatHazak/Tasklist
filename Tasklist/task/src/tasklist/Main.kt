package tasklist

fun main() {
    val taskList = TaskList()
    taskList.tasks = DataUtil.readData()
    while (true) {
        when (getAction()) {
            Action.ADD -> taskList.addTask()
            Action.PRINT -> taskList.printTasks()
            Action.EDIT -> taskList.editTask()
            Action.DELETE -> taskList.deleteTask()
            Action.END -> break
        }
    }
    DataUtil.writeData(taskList.tasks)
    println("Tasklist exiting!")
}

fun getAction(): Action {
    var action: Action? = null
    while (action == null) {
        println("Input an action (add, print, edit, delete, end):")
        when (readln()) {
            Action.ADD.noun -> action = Action.ADD
            Action.PRINT.noun -> action = Action.PRINT
            Action.EDIT.noun -> action = Action.EDIT
            Action.DELETE.noun -> action = Action.DELETE
            Action.END.noun -> action = Action.END
            else -> println("The input action is invalid")
        }
    }
    return action
}

enum class Action(val noun: String) {
    ADD("add"),
    PRINT("print"),
    EDIT("edit"),
    DELETE("delete"),
    END("end"),
}