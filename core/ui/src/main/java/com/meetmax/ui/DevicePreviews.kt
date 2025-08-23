package com.meetmax.ui

import androidx.compose.ui.tooling.preview.Preview


@Preview(name = "phone", device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480")
@Preview(
    name = "samsung_galaxy_s7",
    device = "spec:shape=Normal,width=1440,height=2560,unit=px,dpi=577"
)
@Preview(
    name = "samsung_galaxy_s20",
    device = "spec:shape=Normal,width=1440,height=3200,unit=px,dpi=563"
)

@Preview(
    name = "tablet_portrait",
    device = "spec:shape=Normal,width=1280,height=800,unit=dp,dpi=480"
)
annotation class DevicePreviews