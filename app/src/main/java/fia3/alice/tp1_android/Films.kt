package fia3.alice.tp1_android

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.sp

@Composable
fun Films(windowClass: WindowSizeClass, navController: NavController, viewModel: MainViewModel) {
    when(windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {

            val movies by viewModel.movies.collectAsState()

            if(movies.isEmpty())
                viewModel.searchMovies("Hobbit")


            LazyColumn{
                items(movies) {
                    movie -> Text(text = movie.original_title)
                }
            }
        }
    else -> {
        Text(
            text = "Test",
            fontSize = 30.sp
        )


        }
    }
}