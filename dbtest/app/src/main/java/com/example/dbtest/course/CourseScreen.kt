package com.example.dbtest.course

import android.R.attr.onClick
import android.content.Context
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dbtest.R
import com.example.dbtest.TaskViewModel
import com.example.dbtest.database.Course
import com.example.dbtest.task.AddTaskPage
import com.example.dbtest.task.Addtask
import com.example.dbtest.task.EmptyTasksImage
import com.example.dbtest.task.TaskApp
import com.example.dbtest.ui.theme.BasicsCodelabTheme
import java.time.LocalDate


fun delCourse(taskViewModel: TaskViewModel,course: Course){
    taskViewModel.delCourse(course)
}
@Composable
private fun CourseContent(taskViewModel: TaskViewModel,course: Course) {
    var expanded by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(
                        text = course.start,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.ExtraBold),
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = course.title,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.ExtraBold),
                        modifier = Modifier.weight(1f)
                    )

                }
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = " " + course.type,
                        style = MaterialTheme.typography.headlineMedium.copy(),
                        modifier = Modifier.weight(1f)
                    )
                    var address: String
                    if (course.code == null || course.room == null) {
                        address = stringResource(id = R.string.course_outschool)
                    } else {
                        address = course.code + " " + course.room
                    }
                    Text(
                        text = address,
                        style = MaterialTheme.typography.headlineMedium.copy(),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            if (expanded) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            delCourse(taskViewModel,course)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray,
                            contentColor = Color.White
                        ),
                    ) {
                        val del = stringResource(id = R.string.course_del)
                        Text(del)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = { //using Google map
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Gray,
                            contentColor = Color.White
                        )
                    ) {
                        val dir = stringResource(id = R.string.course_dir)
                        Text(dir)
                    }

                }
            }

        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }
            )
        }
    }
}

@Composable
fun CourseFinish(taskViewModel: TaskViewModel, course: Course) {
    BasicsCodelabTheme {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            CourseContent(taskViewModel,course)
        }
    }
}
@Composable
fun NoCoursesScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val color = colorResource(id = R.color.grey)
            val text = stringResource(id = R.string.course_noCourse)
            EmptyTasksImage()
            Text(text = text, color = color, fontSize = 36.sp, fontWeight = FontWeight.Bold)
        }

    }
}
@Composable
fun WeatherIcon() {
    Icon(
        painter = painterResource(id = R.drawable.icon_weather),
        contentDescription = "Empty Task"
    )
}
@Composable
private fun CoursesList(
    taskViewModel: TaskViewModel,
    modifier: Modifier = Modifier,
    courses: List<Course>
) {
    if(courses.isEmpty()){
        NoCoursesScreen()
    }else {
        LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
            items(items = courses) { task ->
                CourseFinish(taskViewModel, task)
            }
        }
    }
}


@Composable
fun TopBar(context:Context,taskViewModel:TaskViewModel, week:String,left:()->Unit,right:()->Unit,wea:()->Unit) {
    BasicsCodelabTheme {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                IconButton(onClick = left) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = week,
                        style = MaterialTheme.typography.headlineMedium.copy()
                    )
                    IconButton(onClick = wea) {
                        WeatherIcon()
                    }

                }

                IconButton(onClick = right) {
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,)
                }
            }
            var courses:List<Course>
            if(week == context.getString(R.string.Monday_full)){
                courses = taskViewModel.allMC.collectAsState(initial = emptyList()).value
            } else if(week == context.getString(R.string.Tuesday_full)){
                courses = taskViewModel.allTuC.collectAsState(initial = emptyList()).value
            } else if(week == context.getString(R.string.Wednesday_full)){
                courses = taskViewModel.allWC.collectAsState(initial = emptyList()).value
            } else if(week == context.getString(R.string.Thursday_full)){
                courses = taskViewModel.allThC.collectAsState(initial = emptyList()).value
            } else{
                courses = taskViewModel.allFC.collectAsState(initial = emptyList()).value
            }
            CoursesList(taskViewModel,modifier= Modifier, courses)
        }

    }
}
@Composable
fun CourseNoAdd(context: Context,taskViewModel:TaskViewModel,modifier: Modifier){
    var shouldShowWeather by rememberSaveable { mutableStateOf(false) }
    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        if (shouldShowWeather) {
            // Weather Api
        } else {
            var today = LocalDate.now().dayOfWeek.toString()
            var value = 1000
            if(today=="TUESDAY"){ value += 1
            }else if(today=="WEDNESDAY"){ value += 2
            }else if(today=="THURSDAY"){ value += 3
            }else if(today=="FRIDAY"){ value += 4}
            else{value}
            var whichday by rememberSaveable { mutableIntStateOf(value) }
            var day:String
            if(whichday.rem(5)==0){ day = stringResource(id = R.string.Monday_full)
            } else if(whichday.rem(5)==1){ day = stringResource(id = R.string.Tuesday_full)
            } else if(whichday.rem(5)==2){ day = stringResource(id = R.string.Wednesday_full)
            } else if(whichday.rem(5)==3){ day = stringResource(id = R.string.Thursday_full)
            } else{ day = stringResource(id = R.string.Friday_full) }
            TopBar(context, taskViewModel, day, left = { whichday -= 1 },right = { whichday += 1 },wea= {shouldShowWeather=true})
        }
    }
}


@Composable
fun CourseApp(context: Context,taskViewModel: TaskViewModel, modifier: Modifier = Modifier,onAddChange:()->Unit) {
    BasicsCodelabTheme {
        Box(
            modifier = Modifier.padding(16.dp)
        ) {
            CourseNoAdd(context,taskViewModel,modifier)
            FloatingActionButton(
                onClick = onAddChange,
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    }
}

@Composable
fun CoursePage(context: Context,taskViewModel: TaskViewModel, modifier: Modifier) {
    var shouldShowAdd by rememberSaveable { mutableStateOf(false) }
    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        if (shouldShowAdd) {
            AddCoursePage(context, taskViewModel, modifier)
        }else{
            CourseApp(context,taskViewModel,modifier,onAddChange ={shouldShowAdd = true})
        }


    }
}