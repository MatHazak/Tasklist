package tasklist

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.File

object DataUtil {

    private val file = File("tasklist.json")
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private val type = Types.newParameterizedType(MutableList::class.java, Task::class.java)
    private val taskAdapter = moshi.adapter<MutableList<Task>>(type).indent("    ")

    fun writeData(tasks: MutableList<Task>) {
        file.writeText(taskAdapter.toJson(tasks))
    }

    fun readData(): MutableList<Task> {
        if (file.exists())
            return taskAdapter.fromJson(file.readText())!!
        return mutableListOf()
    }
}