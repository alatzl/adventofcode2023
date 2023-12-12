plugins {
    kotlin("jvm") version "1.9.21"
    id("application")
}

application {
    mainClass = "util.Runner"
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
}

dependencies {
    implementation("org.reflections", "reflections", "0.10.2")
    implementation("org.slf4j:slf4j-nop:2.0.9")

}

tasks {
    wrapper {
        gradleVersion = "8.5"
    }
}
