package com.example.dbtest

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dbtest.course.AddCourse
import com.example.dbtest.course.CourseApp
import com.example.dbtest.course.CoursePage
import com.example.dbtest.database.Task
import com.example.dbtest.database.TaskRepository
import com.example.dbtest.task.Addtask
import com.example.dbtest.task.TaskApp
import com.example.dbtest.task.TaskPage
import com.example.dbtest.task.showDatePicker
import com.example.dbtest.ui.theme.BasicsCodelabTheme

class MainActivity : ComponentActivity() {
    private val taskViewModel: TaskViewModel by viewModels() {
        TaskViewModelFactory((application as TaskApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabTheme{
                MyScreen(this,taskViewModel)
                //TaskPage(this,taskViewModel, modifier=Modifier)
                //CoursePage(this,taskViewModel, modifier=Modifier)
            }
        }
    }
}
// bar set
@Composable
private fun BottomNavigation(selectedPage: String, onNavigation: (String) -> Unit) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_home)
                )
            },
            selected = selectedPage == "course",
            onClick = {onNavigation("course")}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_add)
                )
            },
            selected = selectedPage == "task",
            onClick = {onNavigation("task")}
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            },
            label = {
                Text(
                    text = stringResource(R.string.bottom_navigation_profile)
                )
            },
            selected = selectedPage == "profile",
            onClick = {onNavigation("profile")}
        )
    }
}
@Composable
fun MyScreen(context: Context,taskViewModel: TaskViewModel) {
    var currentPage by remember { mutableStateOf("course") }

    Scaffold(
        bottomBar = { BottomNavigation(currentPage, onNavigation = { page ->
            currentPage = page
        }) }
    ) { innerPadding ->
        when (currentPage) {
            "course" -> CoursePage(context,taskViewModel, modifier=Modifier)
            "task" -> TaskPage(context,taskViewModel, modifier=Modifier)
            else -> CoursePage(context,taskViewModel, modifier=Modifier)
        }
    }
}