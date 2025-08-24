plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

apply {
    from("$rootDir/compose-module.gradle")
}

android {
    namespace = "com.meetmax.auth_presentation"

}

dependencies {
    implementation(project(Modules.AUTH_DOMAIN))
    implementation(project(Modules.DESIGN_SYSTEM))
    implementation(project(Modules.COMMON))
    implementation(project(Modules.UI))

    implementation (libs.play.services.auth)
    implementation(libs.androidx.credentials)
}