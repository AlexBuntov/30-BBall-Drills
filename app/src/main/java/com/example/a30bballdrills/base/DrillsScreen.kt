package com.example.a30bballdrills.base

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.OriginalSize
import coil.size.Size import com.example.a30bballdrills.R
import com.example.a30bballdrills.model.Drill
import com.example.a30bballdrills.ui.theme._30BBallDrillsTheme

@Composable
internal fun DrillItem(
    //drill: Drill,
    modifier: Modifier,
){
    Card(modifier,) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NumberOfDrill(R.string.drill_numb_1, modifier = Modifier)
            DrillImage(  R.drawable.gif_drill_3,)
            DrillInfo(R.string.drill1, R.string.description1, modifier = Modifier)
        }
    }
}


@Composable
private fun NumberOfDrill(
    drillNumb: Int,
    modifier: Modifier
){
    Text(
        text = stringResource(drillNumb),
        style = MaterialTheme.typography.labelSmall
    )
}


@Composable
fun DrillImage(
    drillImage: Int,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    Box(modifier) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(context).data(data = drillImage).apply(block = {
                    size(Size.ORIGINAL)
                }).build(), imageLoader = imageLoader
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun DrillInfo(
    drillName: Int,
    drillDescription: Int,
    modifier: Modifier
){
    Column(modifier) {
        Text(
            text = stringResource(drillName),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = stringResource(drillDescription),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview
@Composable
private fun DrillItemPreview(){
    _30BBallDrillsTheme {
        DrillItem(modifier = Modifier)
    }
}