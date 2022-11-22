package fia3.alice.tp1_android

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
@ExperimentalFoundationApi
fun Films(windowClass: WindowSizeClass, navController: NavController, viewModel: MainViewModel) {
    when(windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {

            val movies by viewModel.movies.collectAsState()

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp))
            {
                items(movies) {
                        movie -> FCardM(movie = movie, navController)
                }
            }

        }
    else -> {

        val movies by viewModel.movies.collectAsState()

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp))
            {
                items(movies) {
                    movie -> FCardM(movie = movie, navController = navController)
                }
            }
        }
    }
}

@Composable
fun FCardM(movie : Movie, navController: NavController)
{
    Card(
        elevation = 15.dp,
        modifier = Modifier
            .padding(all = 10.dp)
            .clickable {
                navController.navigate("FilmsDetailled" + "/${movie.id}")
            }) {
        Column(
            modifier = Modifier.padding(all = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w300" + movie.poster_path,
                    contentDescription = movie.original_title
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = movie.original_title,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = movie.release_date,
                    fontWeight = FontWeight.Light
                )
            }

        }
    }
}