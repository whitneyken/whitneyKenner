package com.example.lab4

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.lab4.ui.theme.Lab4Theme
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            Lab4Theme {
                // A surface container using the 'background' color from the theme
                Surface{
                    Column {
                        val vm: ChuckView by viewModels{  ChuckViewFactory((application as ChuckApplication).chuckRepository)}
                        val scope = rememberCoroutineScope()
                        var job: Job? by remember { mutableStateOf((null)) }

                        //val job: State<Job?> = remember {Job()}
                        Row {
                            Button(onClick = {
                                job = scope.launch {
                                    val response = getChuckJokeCoroutine()

                                    vm.addJoke(response.joke)
                                }
                            }) {
                                Text("Make Joke")
                            }
                        }


                        val allJokes = vm.allJokes.observeAsState()
                        LazyColumn {
                            for (entry in (allJokes.value ?: listOf()).asReversed()) {
                                Log.e("data!", "yeah")
//                                item {
//                                    //Log.e("joke!", entry.joke)
////                                    Text(
////                                        "Joke: ${entry.joke}"
////                                    )
//                                }
                            }

                        }
                    }
                }
            }


        }
    }
}

//class ChuckView : ViewModel() {
//    private val _data = MutableLiveData<List<ChuckData>>(listOf())
//    val data = _data as LiveData<List<ChuckData>>
//    fun addData(newVal: ChuckData) {
//        _data.value = _data.value?.plus(newVal)
//        Log.e("UPDATE", "${data.value?.size}")
//    }
//}

//data class ChuckData(var joke: String)


suspend fun getChuckJokeCoroutine(): JokeData {
    return withContext(Dispatchers.IO) {
        Log.e("data!", "inside chuck coroutine")

        val url: Uri = Uri.Builder().scheme("https")
            .authority("api.chucknorris.io")
            .appendPath("jokes")
            .appendPath("random").build()

        val conn = URL(url.toString()).openConnection() as HttpURLConnection
        conn.connect()
        val gson = Gson()
        val result = gson.fromJson(
            InputStreamReader(conn.inputStream, "UTF-8"),
            JokeData::class.java
        )
        Log.e("data!", gson.toJson(result).toString())
        result
    }
}


