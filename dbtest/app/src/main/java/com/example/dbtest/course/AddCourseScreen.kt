package com.example.dbtest.course

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Checkbox
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dbtest.R
import com.example.dbtest.TaskViewModel
import com.example.dbtest.database.Course
import com.example.dbtest.database.Task
import com.example.dbtest.task.Addtask
import com.example.dbtest.task.MyTextInput
import com.example.dbtest.task.TaskApp
import com.example.dbtest.task.addTask
import com.example.dbtest.task.showDatePicker
import com.example.dbtest.task.showTimePicker
import com.example.dbtest.ui.theme.BasicsCodelabTheme
import java.time.LocalDate
import java.time.LocalTime
import java.util.Calendar
import java.util.Date
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SingleSelectCheckBox(
    options: List<String>,
    selectedOption: String?,
    onOptionSelected: (String) -> Unit
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        options.forEach { option ->
            Row(modifier = Modifier.padding(3.dp), verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = option == selectedOption,
                    onCheckedChange = { checked ->
                        if (checked) {
                            onOptionSelected(option)
                        }
                    }
                )
                Text(text = option)
            }
        }
    }
}
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MultiSelectCheckBox(options:List<String>,checkedState: SnapshotStateMap<String, Boolean>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text("Select Frequency")

        FlowRow {
            options.forEach { option ->
                Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = checkedState[option] ?: false,
                        onCheckedChange = { checked ->
                            checkedState[option] = checked
                        }
                    )
                    Text(text = option)
                }
            }
        }
    }
}

@Composable
fun AddCourse(
    context: Context,
    taskViewModel: TaskViewModel,
    modifier: Modifier = Modifier,
    onCourseChange: () -> Unit
) {
    var title by remember { mutableStateOf("")}
    val options0 = listOf(
        stringResource(id = R.string.Course),
        stringResource(id = R.string.Lab),
        stringResource(id = R.string.Dis),
        stringResource(id = R.string.Meeting)
    )
    var type by remember { mutableStateOf<String?>(null) }
    var code by remember { mutableStateOf("") }
    var room by remember { mutableStateOf("") }
    var start = remember { mutableStateOf("") }
    val options = listOf(
        stringResource(id = R.string.Monday),
        stringResource(id = R.string.Tuesday),
        stringResource(id = R.string.Wednesday),
        stringResource(id = R.string.Thursday),
        stringResource(id = R.string.Friday)
    )
    val checkedState = remember { mutableStateMapOf<String, Boolean>().apply {
        options.forEach { this[it] = false }
    }}
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyTextInput("Title", title) { title = it }
        SingleSelectCheckBox(options0,type){type = it}
        MyTextInput("Code", code) { code = it }
        MyTextInput("Room", room) { room = it }
        showTimePicker(context, start,stringResource(id = R.string.course_select_start))
        MultiSelectCheckBox(options,checkedState)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    if (title ==""){
                        val warn = context.getString(R.string.task_noTitle)
                        Toast.makeText(context,warn,Toast.LENGTH_SHORT).show()}
                    else if( type == null){
                        val warn = context.getString(R.string.course_noType)
                        Toast.makeText(context,warn,Toast.LENGTH_SHORT).show()}
                    else if(start.value ==""){
                        val warn = context.getString(R.string.course_noStart)
                        Toast.makeText(context,warn,Toast.LENGTH_SHORT).show()}
                    else {
                        var mon = checkedState[context.getString(R.string.Monday)]
                        Log.d("Mon", "$mon")
                        var tue = checkedState[context.getString(R.string.Tuesday)]
                        var wed = checkedState[context.getString(R.string.Wednesday)]
                        var thu = checkedState[context.getString(R.string.Thursday)]
                        var fri = checkedState[context.getString(R.string.Friday)]
                        if(mon == false && tue == false && wed == false && thu == false && fri == false  ){

                        }else{
                            addCourse(context, taskViewModel, title, type!!,code,room,start.value,mon!!,tue!!,wed!!,thu!!,fri!!)
                            val text = context.getString(R.string.task_add) + " " + title
                            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            ) {
                val add = stringResource(id = R.string.task_add)
                Text(add)
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = onCourseChange
            ) {
                val back = stringResource(id = R.string.course_add_back)
                Text(back)
            }
        }

            }
        }

fun addCourse(context: Context,taskViewModel: TaskViewModel,title: String, type: String, code: String, room: String,
               start: String,mon: Boolean,tue: Boolean, wed:Boolean, thu: Boolean, fri: Boolean) {
    Log.d("course", "reach here")
    val course = Course(0,title,type,code,room,start,mon,tue,wed,thu,fri)
    Log.d("course", "$course")
    taskViewModel.addCourse(course)
}
@Composable
fun AddCoursePage(context: Context,
                taskViewModel: TaskViewModel,
                modifier: Modifier = Modifier){
    var shouldShowStackList by rememberSaveable { mutableStateOf(false) }
    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        if (!shouldShowStackList) {
            AddCourse(context = context, taskViewModel = taskViewModel, onCourseChange = { shouldShowStackList = true })
        } else {
            CoursePage(context,taskViewModel,modifier)
        }
    }

}





