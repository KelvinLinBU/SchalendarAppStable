package com.example.dbtest.task

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dbtest.R
import com.example.dbtest.TaskViewModel
import com.example.dbtest.database.Task
import com.example.dbtest.ui.theme.BasicsCodelabTheme
import java.time.LocalDate
import java.time.LocalTime
import java.util.Calendar
import java.util.Date

@Composable
fun showTimePicker(context: Context, time: MutableState<String>,s:String) {
    val currentTime = LocalTime.now()
    val hour = currentTime.hour
    val minute = currentTime.minute
    val timePickerDialog = TimePickerDialog(
        context,
        { _, selectedHour, selectedMinute ->
            time.value = "${selectedHour.toString().padStart(2, '0')}:${selectedMinute.toString().padStart(2, '0')}"
        }, hour, minute, true
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        var text = s
        if(time.value != ""){
            text = time.value
        }
        Text(text = text, modifier = Modifier.weight(1f))
        Button(onClick = { timePickerDialog.show() }) {
            Text(text = stringResource(id = R.string.task_open_picker))
        }
    }
}
@Composable
fun showDatePicker(context: Context,date:MutableState<String>){
    val currentDate = LocalDate.now()
    val year = currentDate.year
    val month = currentDate.monthValue - 1
    val day = currentDate.dayOfMonth
    val datePickerDialog = DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDayOfMonth ->
            date.value = "$selectedYear/${selectedMonth + 1}/${selectedDayOfMonth}"
        }, year, month, day
    )
    BasicsCodelabTheme {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(1.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            var text = stringResource(id = R.string.task_select_date)
            if(date.value != ""){
                text = date.value
            }
            Text(text = text,modifier = Modifier.weight(1f))
            Button(onClick = {
                datePickerDialog.show()}
            ) {
                Text(text = stringResource(id = R.string.task_open_picker))
            }
        }
    }

}
@Composable
fun MyTextInput(name:String,text:String,onTextChange: (String) -> Unit) {
    val enter = stringResource(id = R.string.task_enter)
    TextField(
        value = text,
        onValueChange = onTextChange,
        label = { Text("$enter $name") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp),
    )
}

@Composable
fun Addtask(
    context: Context,
    taskViewModel: TaskViewModel,
    modifier: Modifier = Modifier,
    onTaskChange: () -> Unit
) {
    var title by remember { mutableStateOf("")}
    var details by remember { mutableStateOf("") }
    val date = remember { mutableStateOf("") }
    var due = remember { mutableStateOf("") }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyTextInput("Title", title) { title = it }
        MyTextInput("Details", details) { details = it }
        showDatePicker(context,date)
        showTimePicker(context, due,stringResource(id = R.string.task_select_due))
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = {
                if (title ==""){
                    val warn = context.getString(R.string.task_noTitle)
                    Toast.makeText(context,warn,Toast.LENGTH_SHORT).show()}
                else if(date.value ==""){
                    val warn = context.getString(R.string.task_noDate)
                    Toast.makeText(context,warn,Toast.LENGTH_SHORT).show()}
                else if(due.value ==""){
                    val warn = context.getString(R.string.task_noDue)
                    Toast.makeText(context,warn,Toast.LENGTH_SHORT).show()}
                else {
                    addTask(context, taskViewModel, title, details, date.value, due.value)
                    val text = context.getString(R.string.task_add) + " " + title
                    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                }
            }
                ) {
                    val add = stringResource(id = R.string.task_add)
                    Text(add)
                }
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onTaskChange
        ) {
            val back = stringResource(id = R.string.task_add_back)
            Text(back)
        }
            }
        }

@Composable
fun AddTaskPage(context: Context,
            taskViewModel: TaskViewModel,
            modifier: Modifier = Modifier){
    var shouldShowStackList by rememberSaveable { mutableStateOf(false) }
    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        if (!shouldShowStackList) {
            Addtask(context = context, taskViewModel = taskViewModel, onTaskChange = { shouldShowStackList = true })
        } else {
            TaskPage(context,taskViewModel, modifier)
        }
    }

}
fun addTask(context: Context,taskViewModel: TaskViewModel,title: String, details: String, date: String, due: String) {
    val task = Task(0,title,details,date,due,false)
    taskViewModel.addTask(task)
}


