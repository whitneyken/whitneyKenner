package com.example.lab4

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import com.example.lab4.ui.theme.Lab4Theme
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm: ChuckView by viewModels { ChuckViewFactory((application as ChuckApplication).chuckRepository) }


        setContent {
            Lab4Theme {
                // A surface container using the 'background' color from the theme
                Surface {
                    val currentJoke by vm.currentJoke.observeAsState()
                    Column {
                        val scope = rememberCoroutineScope()

                        DisplayJoke(currentJoke)
                        Spacer(modifier = Modifier.padding(32.dp))

                        Row {

                            Button(onClick = {
                                scope.launch {
                                    val response = getChuckJokeCoroutine()

                                    vm.addJoke(response)
                                }
                            }) {
                                Text("Make Joke")
                            }
                        }
                        Spacer(modifier = Modifier.padding(16.dp))
                        Text(
                            "Old jokes",
                            fontSize = 12.em,
                            lineHeight = 1.em
                        )
                        Spacer(modifier = Modifier.padding(16.dp))

                        val allJokes by vm.allJokes.observeAsState()
                        LazyColumn {
                            for (joke in allJokes ?: listOf()){
                            item {
                                DisplayJoke(data = joke)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


data class ChuckData(var value: String)

@Composable
fun DisplayJoke(data: JokeData?) {
    Surface(color = MaterialTheme.colorScheme.surface) {
        if (data != null){
            Text(
                "Joke:  ${data.joke}"
            )
        }else
            Text(text = "NO JOKES YET",
            )

    }
}

suspend fun getChuckJokeCoroutine(): ChuckData {
    return withContext(Dispatchers.IO) {

        val url: Uri = Uri.Builder().scheme("https")
            .authority("api.chucknorris.io")
            .appendPath("jokes")
            .appendPath("random").build()

        val conn = URL(url.toString()).openConnection() as HttpURLConnection
        conn.connect()
        val gson = Gson()
        val result = gson.fromJson(
            InputStreamReader(conn.inputStream, "UTF-8"),
            ChuckData::class.java
        )
        Log.e("data!", gson.toJson(result).toString())
        result
    }
}


