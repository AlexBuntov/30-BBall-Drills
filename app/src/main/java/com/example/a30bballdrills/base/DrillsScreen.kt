package com.example.a30bballdrills.base

import android.os.Build.VERSION.SDK_INT
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.OriginalSize
import coil.size.Size import com.example.a30bballdrills.R
import com.example.a30bballdrills.model.Drill
import com.example.a30bballdrills.ui.theme._30BBallDrillsTheme
import kotlin.math.exp

@Composable
internal fun DrillItem(
    drill: Drill,
    modifier: Modifier,
){
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
        ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NumberOfDrill(
                drillNumb = drill.drillNumberResId,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            DrillImage(
                drillImage = drill.drillImageId,
                modifier = Modifier
                    .height(500.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                DrillInfo(
                    drillName = drill.drillTitleId,
                    modifier = Modifier.width(350.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                DrillDescriptionButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded},
                )
            }
            AnimatedVisibility(
                visible = expanded
            ) {
                DrillDescription(
                    drillDescription = drill.drillDescriptionId,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth()
                )
            }

        }
    }
}


@Composable
private fun NumberOfDrill(
    drillNumb: Int,
    modifier: Modifier = Modifier
){
    Text(
        text = stringResource(drillNumb),
        style = MaterialTheme.typography.labelLarge,
        modifier = modifier
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
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(context).data(data = drillImage).apply(block = {
                    size(Size.ORIGINAL)
                }).build(), imageLoader = imageLoader
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier.clip(MaterialTheme.shapes.medium),
        )
    }

@Composable
private fun DrillInfo(
    drillName: Int,
    modifier: Modifier = Modifier
){
        Text(
            text = stringResource(drillName),
            style = MaterialTheme.typography.titleMedium,
            modifier = modifier
        )
}

@Composable
private fun DrillDescription(
    drillDescription: Int,
    modifier: Modifier = Modifier
){
    Text(
        text = stringResource(drillDescription),
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
    )
}

@Composable
private fun DrillDescriptionButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
){
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null
        )
    }
}

//@Preview
//@Composable
//private fun DrillItemPreview(){
//    _30BBallDrillsTheme {
//        DrillItem(modifier = Modifier)
//    }
//}