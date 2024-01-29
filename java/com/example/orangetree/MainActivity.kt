package com.example.orangetree

import android.os.Bundle
import android.text.style.BackgroundColorSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.orangetree.ui.theme.OrangeTreeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OrangeTreeTheme {
                OrangeTree()
            }
        }
    }
}

@Composable
fun OrangeTree(modifier:Modifier =
                   Modifier
                       .fillMaxSize()
                       .wrapContentSize(align = Alignment.Center)
)
{
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally)
    {

        var stage by remember{ mutableStateOf(1) }
        var gamesCount by remember {
            mutableStateOf(0)
        }
        var orangeImageID = when(stage)
        {
            1-> R.drawable.orange_tree
            2-> R.drawable.orange_fruit
            3-> R.drawable.orange_drink_full
            else -> R.drawable.orange_drink_empty
        }
        var orangeTextID = when(stage)
        {
            1-> R.string.tree
            2-> R.string.fruit
            3-> R.string.fullGlass
            else -> R.string.emptyGlass
        }
        var randomNeeded by remember {
            mutableStateOf(5)
        }
        var randomCount by remember {
            mutableStateOf(0)
        }

        Button(modifier = Modifier.padding(10.dp),onClick = {
            if (stage == 1)
            {
                randomNeeded = (5..10).random()
                randomCount = 0
                stage++
            }
            else if (stage == 2)
            {
                randomCount++
                if (randomCount >= randomNeeded)
                {
                    stage++
                }
            }
            else if (stage == 3)
            {
                stage++
            }
            else
            {
                stage = 1
                gamesCount++
            }
        }) {

            Image(painter = painterResource(id = orangeImageID), contentDescription = "orangeThing")

        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = stringResource(id = orangeTextID))
        Spacer(modifier = Modifier.height(80.dp))
        Text(text = stringResource(id = R.string.count) + "  " + gamesCount)
    }
}

@Preview
@Composable
fun OrangeTreePreview() {
    OrangeTree()
}