plugins {
    kotlin("jvm") version "1.5.10"
    java
    `maven-publish`
}

group = "org.kjo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.cc:klib:1.1")
    implementation ("com.google.guava:guava:29.0-jre")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.11.0")
    implementation("org.xerial:sqlite-jdbc:3.7.2")
    implementation("org.projectlombok:lombok:1.18.20")
    compileOnly("org.projectlombok:lombok:1.18.20")
    annotationProcessor("org.projectlombok:lombok:1.18.20")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testCompileOnly("org.projectlombok:lombok:1.18.20")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.20")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}