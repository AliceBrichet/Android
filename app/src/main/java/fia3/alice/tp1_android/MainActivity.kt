package fia3.alice.tp1_android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DesktopWindows
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import fia3.alice.tp1_android.pages.SeriesDetailled
import fia3.alice.tp1_android.ui.theme.TP1_AndroidTheme
import kotlin.math.log

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel : MainViewModel by viewModels()

        setContent {
            TP1_AndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    val windowSizeClass = calculateWindowSizeClass(this)
                    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    Scaffold(
                        scaffoldState = scaffoldState,
                        topBar = {
                            if(currentDestination?.hierarchy?.any { it.route == "Home" } == false) {
                                SearchBar(navController, viewModel,currentDestination)
                            }
                        },
                        bottomBar = {
                            if(currentDestination?.hierarchy?.any { it.route == "Home" } == false)
                            {
                                val selectedIndex = remember { mutableStateOf(0) }
                                BottomNavigation(elevation = 10.dp) {

                                    BottomNavigationItem(icon = {
                                        Icon(
                                            Icons.Rounded.Movie,
                                            contentDescription = "Icon film"
                                        )
                                    },
                                        label = { Text(text = "Films") },
                                        selected = currentDestination?.hierarchy?.any { it.route == "Films" } == true,
                                        onClick = {
                                            navController.navigate("Films")
                                        })

                                    BottomNavigationItem(icon = {
                                        Icon(
                                            Icons.Rounded.DesktopWindows,
                                            contentDescription = "Icon Série"
                                        )
                                    },
                                        label = { Text(text = "Séries") },
                                        selected = currentDestination?.hierarchy?.any { it.route == "Series" } == true,
                                        onClick = {
                                            navController.navigate("Series")
                                        })

                                    BottomNavigationItem(icon = {
                                        Icon(
                                            Icons.Rounded.Person,
                                            contentDescription = "Icon acteur"
                                        )
                                    },
                                        label = { Text(text = "Acteurs") },
                                        selected = currentDestination?.hierarchy?.any { it.route == "Actors" } == true,
                                        onClick = {
                                            navController.navigate("Actors")
                                        })
                                }
                            }
                        })
                    {
                        innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = "Home",
                            Modifier.padding(innerPadding)) {
                            composable("Home") {
                                Home(windowSizeClass,navController)
                            }
                            composable("Films") {
                                Films(windowSizeClass,navController, viewModel)
                            }
                            composable("Series") {
                                Series(windowSizeClass,navController, viewModel)
                            }
                            composable("Actors") {
                                Actors(windowSizeClass, navController, viewModel)
                            }
                            composable("FilmsDetailled" + "/{id}"){ NavBackStack ->
                                val id = NavBackStack.arguments?.getString("id")
                                if (id != null) {
                                    FilmsDetailled(windowSizeClass, navController,id, viewModel)
                                }
                            }
                            composable("SeriesDetailled" + "/{id}"){ NavBackStack ->
                                val id = NavBackStack.arguments?.getString("id")
                                if (id != null) {
                                    SeriesDetailled(windowSizeClass, navController,id, viewModel)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
