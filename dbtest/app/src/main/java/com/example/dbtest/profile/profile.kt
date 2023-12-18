import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.dbtest.TaskViewModel
import com.example.dbtest.course.AddCoursePage
import com.example.dbtest.course.CourseApp

@Composable
fun ProfileScreen() {
    val userName = "John Doe" // Replace with the user's name
    val userEmail = "johndoe@example.com" // Replace with user's email
    val userBio = "This is my bio. I love coding and learning new things!" // Replace with user's bio

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // User Name
        Text(
            text = userName,

            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        // User Email
        Text(
            text = userEmail,

            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        // User Bio
        Text(
            text = userBio,

            color = Color.Black,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}
@Composable
fun ProfilePage(context: Context, taskViewModel: TaskViewModel, modifier: Modifier) {
    var shouldShowAdd by rememberSaveable { mutableStateOf(false) }
    Surface(modifier, color = MaterialTheme.colorScheme.background) {
        ProfileScreen()


    }
}


