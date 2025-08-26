plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
}

apply {
    from("$rootDir/base-module.gradle")
}

android {
    namespace = "com.meetmax.auth_domain"

}

dependencies {

    implementation(project(Modules.COMMON))
}