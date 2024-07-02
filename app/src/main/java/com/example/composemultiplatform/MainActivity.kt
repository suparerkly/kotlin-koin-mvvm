package com.example.composemultiplatform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.common.img.imageListPngJpgUI
import com.example.common.img.imagePngJpgUI
import com.example.composemultiplatform.ui.theme.ComposeMultiplatformTheme
import com.example.composemultiplatform.viewmodel.HomeScreenViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeMultiplatformTheme {
                HomeScreenUI()
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeMultiplatformTheme {
        Greeting("Android")
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenUI(
    viewModel: HomeScreenViewModel = koinViewModel()
) {
    val homeScreen = viewModel.homeBannerUiState.collectAsState()

    val pagerState =
        rememberPagerState(pageCount = {
            homeScreen.value.banner?.size ?: 0
        })

    Column(
        modifier =
        Modifier
            .padding(horizontal = 8.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        HorizontalPager(state = pagerState) { page ->
            imagePngJpgUI(image = homeScreen.value.banner?.get(page)?.image ?: "")
        }
    }
//    Text(text = "${homeScreen.value.message}")

}