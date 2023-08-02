import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.util.removeSuffixIfPresent

val jvmVersion: String by project
fun springBootStarter(module: String = "") =
    "org.springframework.boot:spring-boot-starter-$module".removeSuffixIfPresent("-")

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    kotlin("plugin.allopen")
}

group = "com.wizurd"
version = "no version"

repositories {
    mavenCentral()
    mavenLocal()
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
}

dependencies {
    val springDocOpenApiVersion: String by project
    val postgresqlVersion: String by project
    val feignHttpClientVersion: String by project
    // SpringBoot
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")

    //Swagger
    implementation("org.springdoc:springdoc-openapi-ui:$springDocOpenApiVersion")
    implementation("org.springdoc:springdoc-openapi-kotlin:$springDocOpenApiVersion")

    //Kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    //DB
    implementation("org.liquibase:liquibase-core")
    implementation("org.postgresql:postgresql:$postgresqlVersion")

    //OpenFeign
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("io.github.openfeign:feign-httpclient:$feignHttpClientVersion")

    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.code.gson:gson:2.9.0")

    //VK
    implementation("com.github.yvasyliev:java-vk-bots-longpoll-api:4.1.3")

}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.getByName<Jar>("jar") {
    enabled = false
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict", "-Xjvm-default=all")
        jvmTarget = jvmVersion
    }
}