plugins {
    kotlin("jvm") version "1.9.23"
    id("com.gradleup.shadow") version "9.4.3"
}

group = "pt.dxkr"
version = "1.0.0"

repositories {
    mavenCentral()
    maven { url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") }
    maven { url = uri("https://repo.aikar.co/content/groups/aikar/") }
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
    implementation("co.aikar:acf-bukkit:0.5.1-SNAPSHOT")
    implementation(kotlin("stdlib"))
}

kotlin {
    jvmToolchain(8)
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.processResources {
    filteringCharset = "UTF-8"
}

tasks.shadowJar {
    archiveBaseName.set("DamageManager")
    archiveClassifier.set("")
    archiveVersion.set(version.toString())

    relocate("co.aikar.commands", "com.github.udxkr.libs.acf")
    relocate("co.aikar.locales", "com.github.udxkr.libs.locales")
}

tasks.build {
    dependsOn(tasks.shadowJar)
}