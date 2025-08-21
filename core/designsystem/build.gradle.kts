plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}


apply {
    from("$rootDir/compose-module.gradle")
}

android {
    namespace = "com.meetmax.designsystem"

}

dependencies {
    implementation(project(Modules.COMMON))
}