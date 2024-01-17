plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
}

tasks.jar {
    enabled = true
}

dependencies {


    // TODO : api vs implementation
    api("dev.miku:r2dbc-mysql:0.8.2.RELEASE")
    api("org.springframework.data:spring-data-r2dbc")
}
