package com.example.sampleapicall.android

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sampleapicall.Greeting
import com.example.sampleapicall.viewmodels.PostViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val postViewModel: PostViewModel by viewModels()
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PostListView(modifier = Modifier.padding(16.dp), postViewModel)
                }
            }
        }
    }
}

@Composable
fun PostListView(modifier: Modifier, postViewModel: PostViewModel) {
    val postItems = postViewModel.posListData.collectAsState()
    UiStateHandler(postItems.value) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
        ) {
            itemsIndexed(it) { _, item ->
                GreetingView(
                    text = "Hello ${item.title}",
                    item.body ?: "",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun GreetingView(text: String, subtitle: String, modifier: Modifier) {
    Column {
        Row(modifier, verticalAlignment = Alignment.Top) {
            Image(
                painterResource(id = R.drawable.ic_profile),
                contentDescription = "",
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .clip(CircleShape)

            )
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = text, style = TextStyle(fontSize = 14.sp))
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = subtitle, style = TextStyle(fontSize = 12.sp))
            }
        }
        Divider(Modifier.padding(horizontal = 8.dp))
    }
}

@Preview(
    name = "MyApp",
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            GreetingView(Greeting().greet(), "", Modifier.padding(8.dp))
        }
    }
}

@Preview(
    name = "PostList",
    showSystemUi = true,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun PostListPreview() {
    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            PostListView(Modifier.padding(8.dp), postViewModel = PostViewModel())
        }
    }
}
