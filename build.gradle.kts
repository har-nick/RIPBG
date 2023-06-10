import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization")
    id("com.google.devtools.ksp")
}

group = "uk.co.harnick.ripbg"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        jvmToolchain(17)
        withJava()
    }

    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation("io.ktor:ktor-client-core-jvm:" + extra["ktor.version"] as String)
                implementation("io.ktor:ktor-client-cio:" + extra["ktor.version"] as String)
                implementation("org.jetbrains.compose.material3:material3-desktop:" + extra["compose.version"] as String)
                implementation("org.jetbrains.compose.material:material-icons-extended-desktop:" + extra["compose.version"] as String)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:" + extra["coroutines.version"] as String)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:" + extra["coroutines.version"] as String)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            buildTypes.release {
                proguard {
                    configurationFiles.from("compose.desktop.pro")
                }
                targetFormats(TargetFormat.AppImage, TargetFormat.Dmg, TargetFormat.Exe)
                packageName = "RIPBG"
                packageVersion = "1.0.0"
            }
        }
    }
}