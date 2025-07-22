import org.jetbrains.changelog.Changelog
import org.jetbrains.intellij.platform.gradle.extensions.intellijPlatform
import org.jetbrains.intellij.platform.gradle.tasks.PatchPluginXmlTask
import java.text.SimpleDateFormat
import java.util.*

plugins {
    java
    kotlin("jvm") version "2.2.0"
    id("org.jetbrains.changelog") version "2.2.0"
    id("org.jetbrains.intellij.platform") version "2.6.0"
}

// Apply changelog plugin
apply(plugin = "org.jetbrains.changelog")

group = "com.qqviaja.plugins"
version = "2.0.0"

repositories {
    mavenCentral()

    intellijPlatform {
        defaultRepositories()
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    intellijPlatform {
        intellijIdeaCommunity("2025.1.2")
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21

    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

//configure<KotlinJvmCompilerOptions> {
//    compilerOptions {
//        jvmTarget = JvmTarget.JVM_21
//    }
//}

//tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
//    compilerOptions {
//        jvmTarget = JvmTarget.JVM_21
//    }
//}

tasks.withType<PatchPluginXmlTask> {
    changeNotes.set(provider {
        changelog.renderItem(
            changelog.getUnreleased().withHeader(false).withEmptySections(false),
            Changelog.OutputType.HTML
        )
    })
}


intellijPlatform {
    var projectVersion = project.version.toString()
    pluginConfiguration {
        name = "Golden Radio"
        id = "com.qqviaja.plugins.golden-radio"
        version = projectVersion
//        changeNotes = changelog.get(projectVersion).toHTML()
        ideaVersion {
            sinceBuild = "251"
            untilBuild = ""
        }

    }
}

//intellijPlatform {
//    pluginConfiguration {
//        name = "GoldenRadio"
//        id = "com.qqviaja.plugins.golden-radio"
////        version = project.version
////        changeNotes = changelog.get(project.version).toHTML()
////        ideaVersion {
////            sinceBuild = "251"
////            untilBuild = ""
////        }
//    }
//
//    buildSearchableOptions {
//        enabled = true
//    }
//
//    runIde {
//        // Configure IDE run options if needed
//    }
//
//    signPlugin {
//        // Configure if you need to sign your plugin
//        // certificateChain = System.getenv("CERTIFICATE_CHAIN")
//        // privateKey = System.getenv("PRIVATE_KEY")
//        // password = System.getenv("PRIVATE_KEY_PASSWORD")
//    }
//
//    publishPlugin {
//        token = System.getProperty("ORG_GRADLE_PROJECT_intellijPublishToken")
//    }
//}

changelog {
    version.set(project.version.toString())
    path.set("${project.projectDir}/CHANGELOG.md")
    header.set("[${version.get()}] - ${SimpleDateFormat("yyyy-MM-dd").format(Date())}")
    headerParserRegex.set("""(\d+\.\d+)""".toRegex())
    itemPrefix.set("-")
    keepUnreleasedSection.set(true)
    unreleasedTerm.set("[Unreleased]")
    groups.set(listOf("Added", "Changed", "Deprecated", "Removed", "Fixed", "Security"))
}
