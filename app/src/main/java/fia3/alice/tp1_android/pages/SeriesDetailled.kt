package fia3.alice.tp1_android.pages

import fia3.alice.tp1_android.MainViewModel
import androidx.compose.ui.graphics.Color
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.compose.foundation.lazy.items
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import fia3.alice.tp1_android.FCardC

@Composable
@ExperimentalFoundationApi
fun SeriesDetailled(windowClass: WindowSizeClass, navController: NavController, id : String, viewModel: MainViewModel) {
    when(windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {

            val serie by viewModel.serie.collectAsState()
            viewModel.getSerieDetails(id)
            serie?.let {
                LazyColumn(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    item {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Spacer(modifier = Modifier.size(5.dp))
                            Text(
                                text = it.name,
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 3.sp
                            )
                            Spacer(modifier = Modifier.size(15.dp))
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/w1280" + serie!!.backdrop_path,
                                contentDescription = serie!!.name,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(15.dp))
                            )
                        }
                    }
                    item {
                        Column(
                            horizontalAlignment = Alignment.Start
                        ) {
                            Spacer(modifier = Modifier.size(15.dp))
                            Row(
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Text(
                                    text = "Date de sortie : ",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    text = it.first_air_date,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Light
                                )

                            }
                            Spacer(modifier = Modifier.size(15.dp))
                        }
                    }
                    item {
                        Text(
                            text = "Genres : ",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    items(serie!!.genres) { genre ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Canvas(
                                modifier = Modifier
                                    .padding(start = 8.dp, end = 8.dp)
                                    .size(6.dp))
                            {
                                drawCircle(Color.Black)
                            }
                            Text(text = genre.name)
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.size(15.dp))
                        Text(
                            text = "Synopsis : ",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.size(15.dp))
                        Text(
                            text = it.overview,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Light
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.size(15.dp))
                        Text(
                            text = "Tête d'affiche : ",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.size(15.dp))
                    }
                    item {
                        FlowRow(
                            mainAxisSize = SizeMode.Expand,
                            mainAxisAlignment = FlowMainAxisAlignment.SpaceEvenly
                        ) {
                            it.credits.cast.forEach() { cast ->
                                FCardC(cast = cast)
                            }
                        }
                    }

                }
            }
        }
        else -> {
            val serie by viewModel.serie.collectAsState()
            viewModel.getSerieDetails(id)
            serie?.let {
                LazyColumn(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    item {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Spacer(modifier = Modifier.size(5.dp))
                            Text(
                                text = it.name,
                                fontSize = 45.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 3.sp
                            )
                            Spacer(modifier = Modifier.size(15.dp))
                            AsyncImage(
                                model = "https://image.tmdb.org/t/p/original" + serie!!.backdrop_path,
                                contentDescription = serie!!.name,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(15.dp))
                            )
                        }
                    }
                    item {
                        Column {
                            Row {
                                Text(
                                    text = "Date de sortie : ",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    text = it.first_air_date,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Light
                                )
                            }
                            Spacer(modifier = Modifier.size(15.dp))
                            Row {
                                Text(
                                    text = "Genres : ",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                serie!!.genres.forEach() { genre ->
                                    Text(
                                        text = genre.name + " - ",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Light
                                    )
                                }
                            }
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.size(15.dp))
                        Text(
                            text = "Synopsis : ",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.size(15.dp))
                        Text(
                            text = it.overview,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Light
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.size(15.dp))
                        Text(
                            text = "Tête d'affiche : ",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.size(15.dp))
                    }
                    item {
                        FlowRow(
                            mainAxisSize = SizeMode.Expand,
                            mainAxisAlignment = FlowMainAxisAlignment.SpaceEvenly
                        ) {
                            it.credits.cast.forEach() { cast ->
                                FCardC(cast = cast)
                            }
                        }
                    }

                }
            }


        }
    }
}