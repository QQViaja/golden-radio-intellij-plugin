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

tasks.withType<PatchPluginXmlTask> {
    changeNotes.set(provider {
        changelog.renderItem(
            changelog.get(project.version.toString()).withHeader(false).withEmptySections(false),
            Changelog.OutputType.HTML
        )
    })
}

intellijPlatform {
    buildSearchableOptions = true
}

tasks.publishPlugin {
    token = providers.gradleProperty("intellijPlatformPublishingToken")
}

changelog {
    version.set(project.version.toString())
    path.set("${project.projectDir}/CHANGELOG.md")
    header.set("[${version.get()}] - ${SimpleDateFormat("yyyy-MM-dd").format(Date())}")
    headerParserRegex.set("""(\d+\.\d+(?:\.\d+)?)""".toRegex())
    itemPrefix.set("-")
    keepUnreleasedSection.set(true)
    unreleasedTerm.set("[Unreleased]")
    groups.set(listOf("Added", "Changed", "Deprecated", "Removed", "Fixed", "Security"))
}
