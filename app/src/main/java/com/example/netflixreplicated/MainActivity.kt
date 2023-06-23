package com.example.netflixreplicated

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.netflixreplicated.data.models.Film
import com.example.netflixreplicated.data.viewmodels.MainViewModel
import com.example.netflixreplicated.ui.theme.NetflixReplicatedTheme
import com.example.netflixreplicated.ui.utils.FilmListType

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NetflixReplicatedTheme {
                // A surface container using the 'background' color from the theme
               MainContainer()
            }
        }
    }
}






@Composable
fun MainContainer() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight(),
        color = Color.Black
    ) {
        val viewModel: MainViewModel = viewModel()
        val uiState by viewModel.uiState.collectAsState()

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start
        ) {
            Toolbar()
            MainMovieHome()
            FilmList(uiState.recentlyAddedList, "Recently added",)
            FilmList(uiState.recentlyAddedList, "Tv Series")
            FilmList(
                uiState.topTenList,
                "The Top 10 most popular movies in the Dominican Republic",
                FilmListType.TOP_TEN
            )
            FilmList(uiState.recentlyAddedList, "Comedy",)
            FilmList(uiState.recentlyAddedList, "Action")
            FilmList(uiState.recentlyAddedList, "Watch again", FilmListType.WATCH_IT_AGAIN)

        }
    }
}


@Composable
fun FilmList(list: List<Film>, listTitle: String, type: FilmListType = FilmListType.NORMAL) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(16.dp)
    )
    Text(text = listTitle, style = MaterialTheme.typography.bodySmall, color = Color.White)
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
    )
    LazyRow {
        itemsIndexed(list) { index, item ->
            when(type) {
                FilmListType.TOP_TEN -> TopTenFilmItem(item = item, index + 1)
                FilmListType.WATCH_IT_AGAIN -> WatchAgainFilmItem(item = item)
                else -> FilmItem(item = item)
            }
        }
    }
}

@Composable
fun FilmItem(item: Film) {
    Box(
        modifier = Modifier
            .height(120.dp)
            .width(90.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(4.dp))
    ) {
        val painter = rememberImagePainter(data = item.image)
        Image(
            painter = painter,
            contentDescription = "Item Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        if (item.isTopTen) {
            Surface(
                modifier = Modifier
                    .height(20.dp)
                    .width(12.dp)
                    .align(TopEnd), tonalElevation = 6.dp, color = Color.Red, shadowElevation = 6.dp
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "TOP",
                        fontSize = 3.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 0.dp)
                    )
                    Text(
                        text = "10",
                        fontSize = 6.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

            }
        }

    }
}


@Composable
fun WatchAgainFilmItem(item: Film) {
    Box(
        modifier = Modifier
            .height(120.dp)
            .width(90.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(4.dp))
    ) {
        val painter = rememberImagePainter(data = item.image)
        Image(
            painter = painter,
            contentDescription = "Item Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        if (item.isTopTen) {
            Surface(
                modifier = Modifier
                    .height(20.dp)
                    .width(12.dp)
                    .align(TopEnd), tonalElevation = 6.dp, color = Color.Red, shadowElevation = 6.dp
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "TOP",
                        fontSize = 3.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 0.dp)
                    )
                    Text(
                        text = "10",
                        fontSize = 6.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

            }
        }

    }
}


@Composable
fun TopTenFilmItem(item: Film, index: Int) {
    Box(
        modifier = Modifier
            .height(130.dp)
            .width(110.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(4.dp))
    ) {
        val painter = rememberImagePainter(data = item.image)
        Image(
            painter = painter,
            contentDescription = "Item Image",
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 32.dp),
            contentScale = ContentScale.FillBounds
        )

        Surface(
            modifier = Modifier
                .height(80.dp)
                .width(70.dp)
                .align(BottomStart), tonalElevation = 6.dp, shadowElevation = 6.dp, color = Color.Transparent
        ) {
            val textStroke = Paint().asFrameworkPaint().apply {
                isAntiAlias = true
                style = android.graphics.Paint.Style.STROKE
                textSize = 170f
                color = android.graphics.Color.WHITE
                strokeWidth = 6f
                strokeMiter = 6f
                strokeJoin = android.graphics.Paint.Join.ROUND
            }

            val textPaint = Paint().asFrameworkPaint().apply {
                isAntiAlias = true
                style = android.graphics.Paint.Style.FILL
                textSize = 170f
                color = android.graphics.Color.BLACK
            }

            androidx.compose.foundation.Canvas(modifier = Modifier
                .fillMaxSize(),
                onDraw = {
                    drawIntoCanvas {
                        it.nativeCanvas.drawText(
                            index.toString(),
                            8.dp.toPx(),
                            78.dp.toPx(),
                            textStroke
                        )
                        it.nativeCanvas.drawText(
                            index.toString(),
                            8.dp.toPx(),
                            78.dp.toPx(),
                            textPaint
                        )
                    }
                })
        }
    }
}


@Composable
fun Toolbar() {
    Column(
        modifier = Modifier
            .height(64.dp)
            .background(Color.Transparent)
            .padding(start = 16.dp, top = 8.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(modifier = Modifier.weight(0.7f)) {
                Text(
                    text = "Para Aneudy",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
            }

            Column(
                modifier = Modifier
                    .weight(0.3f)
                    .padding(end = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(R.drawable.baseline_cast_24),
                        contentDescription = "Cast Button"
                    )

                    Image(
                        painter = painterResource(R.drawable.baseline_search_24),
                        contentDescription = "Search button"
                    )

                    Image(
                        modifier = Modifier.size(width = 24.dp, height = 24.dp),
                        painter = painterResource(R.drawable.avatar),
                        contentDescription = "User Icon"
                    )
                }
            }
        }

        Row(modifier = Modifier.padding(top = 8.dp)) {
            TopicButtons("Series")
            TopicButtons("Movies")
            TopicButtons("Categories")
        }
    }
}

@Composable
fun TopicButtons(
    buttonText: String
) {
    Box(
        modifier = Modifier
            .height(25.dp)
            .width(100.dp)
            .padding(end = 8.dp)
            .border(border = BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(15.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = buttonText, fontSize = 10.sp, color = Color.White)
    }
}


@Composable
fun MainMovieHome() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(600.dp), contentAlignment = Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.movie_02),
            contentScale = ContentScale.Crop,
            contentDescription = "movie",
            modifier = Modifier
                .fillMaxSize(0.8f)
                .clip(RoundedCornerShape(10.dp))
                .shadow(
                    elevation = 4.dp,
                    shape = MaterialTheme.shapes.medium,
                    ambientColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                )
        )

        Card(
            modifier = Modifier
                .fillMaxSize(0.8f),
            colors = CardDefaults.cardColors(containerColor = Color.Transparent)
        ) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {

                Row(
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "The Gray Man",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White
                        )
                        Text(
                            text = "Fision",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .height(46.dp)
                        .fillMaxWidth()
                        .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(0.5f)
                            .clip(RoundedCornerShape(5.dp))
                            .fillMaxHeight()
                            .background(Color.LightGray)
                            .padding(end = 8.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.baseline_play_arrow_black),
                                contentDescription = "Play"
                            )
                            Text(text = "Play", fontWeight = FontWeight.Bold)
                        }
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                    Box(
                        modifier = Modifier
                            .weight(0.5f)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(5.dp))
                            .background(Color.DarkGray)
                            .padding(start = 8.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.baseline_add_24_white),
                                contentDescription = "Play"
                            )
                            Text(
                                text = "My List",
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NetflixReplicatedTheme {
        MainContainer()
    }
}