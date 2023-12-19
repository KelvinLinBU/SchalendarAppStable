package com.example.dbtest.database
import android.app.Activity
import java.util.Locale

import androidx.compose.runtime.Composable
import android.content.Context
import android.content.res.Configuration
import android.provider.CalendarContract
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.dbtest.R
import com.example.dbtest.TaskViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import androidx.compose.ui.platform.LocalContext
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.graphics.toArgb
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp






@Composable
fun GoogleSignInButton(googleSignInClient: GoogleSignInClient) {
    val context = LocalContext.current
    val activity = LocalContext.current as? Activity

    val signInLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        // Handle the result of the sign-in process here
        if (result.resultCode == Activity.RESULT_OK) {
            // Signed in successfully, handle the result
            // For example, get the user's information or perform actions after sign-in
            val account = GoogleSignIn.getLastSignedInAccount(context)
            // Handle the account details or navigate to a new screen
        } else {
            // Handle unsuccessful sign-in or canceled sign-in
        }
    }

    Button(
        onClick = {
            val signInIntent = googleSignInClient.signInIntent
            signInLauncher.launch(signInIntent)
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("Sign in with Google")
    }
}

@Composable
fun DropdownMenuSampleProfile(options:List<String>, selectedOption: MutableState<String>) {
    Box(modifier = Modifier){
        var expanded by remember { mutableStateOf(false) }
        Button(onClick = { expanded = true }) {
            Text(selectedOption.value)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    {Text(text = option)},
                    onClick = {
                        selectedOption.value = option
                        expanded = false
                    })
            }
        }

    }
}

@Composable
fun ReplyTheme(

    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val lightColors = lightColorScheme(
        primary = Color.Blue,
        background = Color.White,
        onBackground = Color.Black
        // Define other colors as needed for your light theme
    )

    val darkColors = darkColorScheme(
        primary = Color.Green,
        background = Color.White,
        onBackground = Color.Yellow
        // Define other colors as needed for your dark theme
    )

    val colorScheme = if (!darkTheme) {
        lightColors
    } else {
        darkColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
@Composable
fun ProfileScreen(googleSignInClient: GoogleSignInClient) {
    var isDarkTheme by remember { mutableStateOf(false) }

    ReplyTheme(darkTheme = isDarkTheme) {



        val userName = "John Doe" // Replace with the user's name
        val userEmail = "johndoe@example.com" // Replace with user's email
        val userBio =
            "This is my bio. I love coding and learning new things!" // Replace with user's bio

        var selectedType by remember { mutableStateOf("") }

        val langOptions = listOf(
            stringResource(id = R.string.French),
            stringResource(id = R.string.Chinese)
        )

        // Your content here...

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

            Spacer(modifier = Modifier.height(8.dp))
            var currentLocale by remember { mutableStateOf(Locale.getDefault()) }
            var locale: Locale = Locale.ENGLISH
            if (selectedType == "Chinese") {
                locale = Locale("zh")
            } else if (selectedType == "French") {
                locale = Locale("fr")
            } else if (selectedType == "English") {
                locale = Locale("en")
            }

            //val onLocaleSelected: (Locale) -> Unit = { selectedType ->
            //  if (locale != currentLocale) {
            //    val context = LocalContext.current
            //  updateLocale(context, selectedType)
            //currentLocale = locale
            //}
            //}

            //DropdownMenuSampleProfile(
            //  langOptions,
            // mutableStateOf(""),
            //onLocaleSelected
            //)

            Button(
                onClick = {
                    isDarkTheme = !isDarkTheme

                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(if (isDarkTheme) "Switch to Light Theme" else "Switch to Dark Theme")
            }


            Spacer(modifier = Modifier.height(16.dp))

            // Google Sign-In Button
            GoogleSignInButton(googleSignInClient)
        }
    }

    fun updateLocale(context: Context, locale: Locale) {
        Locale.setDefault(locale)
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        context.createConfigurationContext(config)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }
}
    @Composable
    fun ProfilePage(context: Context, taskViewModel: TaskViewModel, modifier: Modifier) {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            // Add more scopes if needed
            .build()

        val googleSignInClient = GoogleSignIn.getClient(LocalContext.current, gso)

        Surface(modifier, color = MaterialTheme.colorScheme.background) {
            ProfileScreen(googleSignInClient)
        }
    }
