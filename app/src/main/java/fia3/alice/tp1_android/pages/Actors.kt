package fia3.alice.tp1_android

import androidx.compose.foundation.ExperimentalFoundationApi
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
import fia3.alice.tp1_android.model.Actor

@Composable
@ExperimentalFoundationApi
fun Actors(windowClass: WindowSizeClass, navController: NavController, viewModel: MainViewModel) {
    when(windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {

            val actors by viewModel.actors.collectAsState()

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp))
            {
                items(actors) {
                        actor -> FCardA(actor = actor)
                }
            }

        }
        else -> {
            val actors by viewModel.actors.collectAsState()

            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp))
            {
                items(actors) {
                        actor -> FCardA(actor = actor)
                }
            }
        }
    }
}

@Composable
    fun FCardA(actor: Actor)
{
    Card(elevation = 15.dp, modifier = Modifier.padding(all = 10.dp)) {
        Column(
            modifier = Modifier.padding(all = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w300" + actor.profile_path,
                    contentDescription = actor.name
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = actor.name,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }
}