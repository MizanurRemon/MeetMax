package com.meetmax.ui

import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "phone", device = "spec:width=360dp,height=640dp,dpi=480")
@Preview(
    name = "samsung_galaxy_s7",
    device = "spec:width=1440dp,height=2560dp,dpi=577"
)
@Preview(
    name = "samsung_galaxy_s20",
    device = "spec:width=1440dp,height=3200dp,dpi=563"
)
//@Preview(name = "landscape", device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480")
@Preview(
    name = "tablet_portrait",
    device = "spec:width=800dp,height=1280dp,dpi=480"
)
annotation class DevicePreviews