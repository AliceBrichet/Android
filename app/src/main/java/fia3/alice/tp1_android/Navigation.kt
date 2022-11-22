package fia3.alice.tp1_android

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun SearchBar(navController: NavController, viewModel: MainViewModel, currentDestination : NavDestination) {
    val contextForToast = LocalContext.current.applicationContext
    val query by viewModel.query.collectAsState()
    TopAppBar(
        title = {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(0.9f),
                value = query,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colors.primary,
                    unfocusedBorderColor = MaterialTheme.colors.primary),
                placeholder = {
                    Text(
                        text = "Fav'App",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )},
                onValueChange = { newValue ->
                    viewModel.editQuery(newValue)
                }
            )
        },
        //backgroundColor = MaterialTheme.colors.primary,
        actions = {
            // search icon
            TopAppBarActionButton(
                imageVector = Icons.Rounded.Search,
                description = "Search"
            ) {
                if(query == "") {
                    Toast.makeText(contextForToast, "Entrez un mot clé", Toast.LENGTH_SHORT)
                        .show()
                }
                else {
                    if (currentDestination?.route.toString() == "Films") {
                        viewModel.searchMovies(query)
                    } else if (currentDestination?.route.toString() == "Series") {
                        viewModel.searchSeries(query)
                    } else if (currentDestination?.route.toString() == "Actors") {
                        viewModel.searchActors(query)
                    } else if (currentDestination?.route.toString() == "FilmsDetailled/{id}") {
                        viewModel.searchMovies(query)
                        navController.navigate("Films")
                    } else if (currentDestination?.route.toString() == "SeriesDetailled/{id}") {
                        viewModel.searchSeries(query)
                        navController.navigate("Series")
                    }

                }
            }

        }
    )
}

@Composable
fun TopAppBarActionButton(imageVector: ImageVector, description: String, onClick: () -> Unit) {
    IconButton(onClick = {
        onClick()
    }) {
        Icon(imageVector = imageVector, contentDescription = description)
    }

}
