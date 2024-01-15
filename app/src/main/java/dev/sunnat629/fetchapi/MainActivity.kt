package dev.sunnat629.fetchapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import dev.sunnat629.fetchapi.ui.theme.FetchAPITheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val username = "sunnat629"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FetchAPITheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UserContent(viewModel, username)
                }
            }
        }
    }
}

@Composable
fun UserContent(
    viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    username: String
) {

    val context = LocalContext.current
    val userData by remember { viewModel.userData }
    val error by remember { viewModel.error }
    val loading by remember { viewModel.loading }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Button(onClick = { viewModel.fetchUserData(username) }) {
            Text("Fetch User Data")
        }
        Spacer(Modifier.height(16.dp))

        if (loading) {
            CircularProgressIndicator()
            Spacer(Modifier.height(16.dp))
        }

        if (error) {
            Text(
                text = "Invalid username: $username",
                color = Color.Red
            )
            Spacer(Modifier.height(16.dp))
        }
        if (!error && userData.id != null) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(userData.avatarUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Loaded image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(5.dp)),
            )
            Spacer(Modifier.height(16.dp))

            Text("User: ${userData.name}")
            Spacer(Modifier.height(16.dp))

            Text("Public Repos: ${userData.publicRepos}")
            Spacer(Modifier.height(16.dp))

            Text(
                text = "Bio: ${userData.bio}",
                modifier = Modifier.fillMaxWidth(),
                overflow = TextOverflow.Clip,
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FetchAPITheme {
        UserContent(username = "Android")
    }
}