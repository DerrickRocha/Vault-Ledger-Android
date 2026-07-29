package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage

@Composable
fun CardImage(image: String?, modifier: Modifier, description: String) {
    if (image == null) {
        Image(
            painterResource(com.example.ui.R.drawable.outline_image_24),
            modifier = modifier,
            contentDescription = description
        )
    } else {
        AsyncImage(
            modifier = modifier,
            model = image,
            contentDescription = description,
            contentScale = ContentScale.Fit
        )
    }

}