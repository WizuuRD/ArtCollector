rootProject.name = "ArtParser"

pluginManagement {
    val kotlinVersion: String by settings
    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings
    plugins {
        id("org.springframework.boot") version springBootVersion
        id("io.spring.dependency-management") version springDependencyManagementVersion
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
        kotlin("plugin.jpa") version kotlinVersion
        kotlin("plugin.allopen") version kotlinVersion
        jacoco
    }
}