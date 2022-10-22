package fia3.alice.tp1_android

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fia3.alice.tp1_android.ui.theme.TP1_AndroidTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Home(windowClass: WindowSizeClass, navController: NavController) {
    when(windowClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize(),
            ) {
                Spacer(modifier = Modifier.size(70.dp))
                Image(
                    painterResource(id = R.drawable.panda1),
                    contentDescription = "Test",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(200.dp)
                        .border(4.dp, Color.Gray, CircleShape)
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = "Alice Brichet",
                    fontSize = 30.sp
                )
                Text(
                    text = "Elève apprentie de l'école d'ingénieur ISIS"
                )
                Spacer(modifier = Modifier.size(30.dp))
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start
                    ){
                        Image(
                            painterResource(id = R.drawable.icon_mail),
                            contentDescription = "Icon mail",
                            modifier = Modifier
                                .size(25.dp)
                                .padding(5.dp)
                        )
                        Text(
                            text = "brichet.alice@gmail.com"
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.Start
                    ){
                        Image(
                            painterResource(id = R.drawable.icon_linkedin),
                            contentDescription = "Icon mail",
                            modifier = Modifier
                                .size(25.dp)
                                .padding(5.dp)
                        )
                        Text(
                            text = "https://www.linkedin.com/in/alice-brichet-dit-france",
                            fontSize = 15.sp
                        )
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                }
                Button(
                    onClick = {navController.navigate("Films")}) {
                    Text(text="Démarrer")
                }
            }
        }
        else -> {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize(),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Image(
                        painterResource(id = R.drawable.panda1),
                        contentDescription = "Test",
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(250.dp)
                            .border(2.dp, Color.Gray, CircleShape)
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = "Alice Brichet",
                        fontSize = 30.sp
                    )
                    Text(
                        text = "Elève apprentie de l'école d'ingénieur ISIS",
                        fontSize = 20.sp
                    )
                }
                Spacer(modifier = Modifier.size(10.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                )
                {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painterResource(id = R.drawable.icon_mail),
                                contentDescription = "Icon mail",
                                modifier = Modifier
                                    .size(35.dp)
                                    .padding(5.dp)
                            )
                            Text(
                                text = "brichet.alice@gmail.com",
                                fontSize = 15.sp
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painterResource(id = R.drawable.icon_linkedin),
                                contentDescription = "Icon mail",
                                modifier = Modifier
                                    .size(35.dp)
                                    .padding(5.dp)
                            )
                            Text(
                                text = "https://www.linkedin.com/in/alice-brichet-dit-france",
                                fontSize = 15.sp
                            )
                        }
                    }
                    Button(
                        onClick = { navController.navigate("Films") }) {
                        Text(text = "Démarrer")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TP1_AndroidTheme {
    }
}