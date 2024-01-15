package com.inc.lite.mainlite

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.inc.lite.main.R

@Composable
fun MainScreen() {
    Box(Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.ic_launch_foreground),
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center).size(300.dp)
        )
    }
}