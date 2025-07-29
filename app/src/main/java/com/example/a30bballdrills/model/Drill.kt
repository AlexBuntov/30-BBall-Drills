package com.example.a30bballdrills.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable

data class Drill(
    @StringRes val drillNumberResId: Int,
    @StringRes val drillTitleId: Int,
    @StringRes val drillDescriptionId: Int,
    @DrawableRes val drillImageId: Int,
)