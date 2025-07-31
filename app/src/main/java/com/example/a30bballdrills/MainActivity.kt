package com.example.a30bballdrills

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a30bballdrills.base.DrillItem
import com.example.a30bballdrills.model.Drills.drills
import com.example.a30bballdrills.ui.theme._30BBallDrillsTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _30BBallDrillsTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    text = "30 баскетбольных \nупражнений",
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontSize = 24.sp,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        )
                    }
                ) { innerPadding ->
                    DrillApp(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                    )
                }
            }
        }
    }
}

@Composable
fun DrillApp(modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState { drills.size }
    Column(modifier) {
        HorizontalPager(
            state = pagerState,
            pageSpacing = 8.dp,
            beyondViewportPageCount = 1,
            modifier = Modifier
                .weight(1f),
        ) { pageIndex ->
            val drill = drills[pageIndex]
            DrillItem(
                drill = drill,
                modifier = Modifier
                    .wrapContentSize()
            )
        }
        PagerDotCounter(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 16.dp)
                .fillMaxWidth(0.2f),
            visibleDotsCount = 5,
        )
    }
}

@Composable
private fun PagerDotCounter(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    visibleDotsCount: Int,
    activeColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    inactiveColor: Color = MaterialTheme.colorScheme.onSurface.copy(0.1f),
    dotSize: Dp = 8.dp,
    selectedDotSize: Dp = 10.dp,
    spacing: Dp = 8.dp,
){
    val scope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()

    val halfVisibleDots = visibleDotsCount / 2

    LaunchedEffect(pagerState.currentPage) {
        val targetIndex = pagerState.currentPage
        val scrollToIndex = if (targetIndex < halfVisibleDots) {
            0
        } else if (targetIndex >= pagerState.pageCount - halfVisibleDots) {
            (pagerState.pageCount - visibleDotsCount).coerceAtLeast(0)
        } else {
            targetIndex - halfVisibleDots
        }
        scope.launch {
            lazyListState.animateScrollToItem(scrollToIndex.coerceIn(0, pagerState.pageCount - 1))
        }
    }

        LazyRow(
            state = lazyListState,
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(spacing),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(pagerState.pageCount){ iteration ->
                val isSelected = pagerState.currentPage == iteration
                val size = if (isSelected) selectedDotSize else dotSize
                val background = if (isSelected) activeColor else inactiveColor
                Box (
                    modifier = Modifier
                        .size(size)
                        .clip(CircleShape)
                        .background(color = background)
                )
            }
        }
    }

