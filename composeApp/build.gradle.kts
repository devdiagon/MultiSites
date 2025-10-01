import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.androidx.room)
    alias(libs.plugins.gradleBuildConfig)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.client.okhttp)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.materialIconsExtended)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.androidx.navigation.compose)
            implementation(libs.androidx.room.runtime)
            implementation(libs.androidx.sqlite.bundled)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "io.devdiagon.multisites"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "io.devdiagon.multisites"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

tasks.matching { it.name == "kspDebugKotlinAndroid" }.configureEach {
    println("Config for dependencies $name")
    dependsOn(tasks.named("generateResourceAccessorsForAndroidDebug"))
    dependsOn(tasks.named("generateResourceAccessorsForAndroidMain"))
    dependsOn(tasks.named("generateActualResourceCollectorsForAndroidMain"))
    dependsOn(tasks.named("generateComposeResClass"))
    dependsOn(tasks.named("generateResourceAccessorsForCommonMain"))
    dependsOn(tasks.named("generateExpectResourceCollectorsForCommonMain"))
    dependsOn(tasks.named("generateNonAndroidBuildConfig"))
}

tasks.matching { it.name == "kspReleaseKotlinAndroid" }.configureEach {
    println("Function… $name")
    dependsOn(tasks.named("generateResourceAccessorsForAndroidRelease"))
    dependsOn(tasks.named("generateResourceAccessorsForAndroidMain"))
    dependsOn(tasks.named("generateActualResourceCollectorsForAndroidMain"))
    dependsOn(tasks.named("generateComposeResClass"))
    dependsOn(tasks.named("generateResourceAccessorsForCommonMain"))
    dependsOn(tasks.named("generateExpectResourceCollectorsForCommonMain"))
}

dependencies {
    debugImplementation(compose.uiTooling)
    add("kspAndroid", libs.androidx.room.compiler)
    add("kspIosSimulatorArm64", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
}

room {
    schemaDirectory("$projectDir/schemas")
}

buildConfig {
    packageName("io.deviagon.multisites")
    val properties = Properties()
    properties.load(project.rootProject.file("local.properties").reader())

    val apiKey = properties.getProperty("API_KEY")

    buildConfigField("API_KEY", apiKey)
}