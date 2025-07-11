package com.example.sampleandroidapps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.example.sampleandroidapps.ui.theme.SampleAndroidAppsTheme
import kotlinx.serialization.Serializable

sealed interface AppNavKey : NavKey {
    @Serializable
    data object GreetingApp : AppNavKey

    @Serializable
    data object GreetingApp2 : AppNavKey
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SampleAndroidAppsTheme {
                val backstack = rememberNavBackStack(AppNavKey.GreetingApp)
                val addToBackStack: (NavKey) -> Unit = { key -> backstack.add(key) }
                val onHandleBackPressed: () -> Unit = { backstack.removeLastOrNull() }

                NavDisplay(
                    backStack = backstack,
                    entryDecorators = listOf(
                        rememberSceneSetupNavEntryDecorator(),
                        rememberSavedStateNavEntryDecorator(),
                        rememberViewModelStoreNavEntryDecorator()
                    ),
                    entryProvider = { key: AppNavKey ->
                        when (key) {
                            AppNavKey.GreetingApp -> NavEntry(key) {
                                GreetingApp(onNavigate = addToBackStack)
                            }

                            AppNavKey.GreetingApp2 -> NavEntry(key) {
                                GreetingApp2(onNavigate = addToBackStack)
                            }
                        }
                    } as (NavKey) -> NavEntry<NavKey>
                )

                BackHandler(enabled = true) {
                    onHandleBackPressed()
                }
            }
        }
    }
}

@Composable
fun GreetingApp(modifier: Modifier = Modifier, onNavigate: (AppNavKey) -> Unit) {
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Greeting(name = "Android")

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = { onNavigate(AppNavKey.GreetingApp2) }) {
                Text("Go to GreetingApp2")
            }
        }
    }
}

@Composable
fun GreetingApp2(modifier: Modifier = Modifier, onNavigate: (AppNavKey) -> Unit) {
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Greeting(name = "world")

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = { onNavigate(AppNavKey.GreetingApp) }) {
                Text("Go to GreetingApp")
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingAppPreview() {
    SampleAndroidAppsTheme {
        GreetingApp {}
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingApp2Preview() {
    SampleAndroidAppsTheme {
        GreetingApp2 {}
    }
}