plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

apply {
    from("$rootDir/compose-module.gradle")
}

android {
    namespace = "com.meetmax.ui"

}

dependencies {
    implementation(libs.androidx.junit)
    androidTestImplementation(libs.junit)
}