// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.jetbrains.kotlin.serialization) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        // AGP 9 は Kotlin を組み込みでコンパイルする。使う Kotlin Gradle プラグインをカタログの Kotlin のバージョンにそろえる
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.oss.licenses.plugin)
    }
}