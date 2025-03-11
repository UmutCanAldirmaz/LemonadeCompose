package com.hopecoding.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hopecoding.lemonade.ui.theme.LemonadeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                BusinessLogic()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusinessLogic() {
    var selectIndex by remember { mutableIntStateOf(0) }


    var squeezeCount by remember { mutableIntStateOf(0) }

    val images = listOf(
        R.drawable.lemon_tree,
        R.drawable.lemon_squeeze,
        R.drawable.lemon_drink,
        R.drawable.lemon_restart
    )

    val imagesDescriptions = listOf(
        R.string.Lemon_tree_content_description,
        R.string.Lemon_content_description,
        R.string.Glass_of_lemonade_content_description,
        R.string.Empty_glass_content_description
    )


    val strings = listOf(
        R.string.Lemon_tree,
        R.string.Keep_tapping_the_lemon,
        R.string.tap_the_lemonade,
        R.string.tap_the_empty_glass
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 18.sp
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color.Yellow
                )
            )

        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(Color(0xFFBAEECA), shape = RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = images[selectIndex]),
                    contentDescription = stringResource(id = imagesDescriptions[selectIndex]),
                    modifier = Modifier
                        .size(180.dp)
                        .clickable {
                            when (selectIndex) {
                                0 -> {
                                    selectIndex = 1
                                    squeezeCount = (2..4).random()
                                }

                                1 -> {
                                    squeezeCount--
                                    if (squeezeCount == 0) {
                                        selectIndex = 2
                                    }

                                }

                                2 -> selectIndex = 3
                                3 -> selectIndex = 0

                            }
                        }

                )
            }

            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = stringResource(id = strings[selectIndex]),
                modifier = Modifier.padding(16.dp)

            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        BusinessLogic()
    }
}