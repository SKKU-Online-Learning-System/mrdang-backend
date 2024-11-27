plugins {
    id("java")
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.4"
    id("org.asciidoctor.jvm.convert") version "3.3.2"
}


group = "cs.skku.edu"
version = "0.0.0"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

/** spring REST docs **/
val asciidoctorExt:Configuration by configurations.creating
val snippetsDir by extra { file("build/generated-snippets") }

tasks {
    withType<Test> {
        useJUnitPlatform()
    }
    test {
        useJUnitPlatform()
        outputs.dir(snippetsDir)
    }
    asciidoctor {
        doFirst {
            project.delete(files("src/main/resources/static/docs"))
        }
        inputs.dir(snippetsDir)
        configurations("asciidoctorExt")
        dependsOn(test)
        baseDirFollowsSourceFile()
    }
    register<Copy>("copyDocs") {
        dependsOn(asciidoctor)
        from("${asciidoctor.get().outputDir}")
        into("src/main/resources/static/docs")
    }
    bootJar {
        dependsOn(asciidoctor)
        from("${asciidoctor.get().outputDir}") {
            into("static/docs")
        }
    }
    build {
        dependsOn("copyDocs")
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(files("libs/SIServerAPI.4.3.jar"))

    // spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
//    implementation("org.springframework.boot:spring-boot-starter-aop")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    developmentOnly("org.springframework.boot:spring-boot-devtools")


    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // querydsl
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    annotationProcessor("com.querydsl:querydsl-apt:5.0.0:jakarta")
    annotationProcessor("jakarta.annotation:jakarta.annotation-api")
    annotationProcessor("jakarta.persistence:jakarta.persistence-api")

    // mysql
    runtimeOnly("com.mysql:mysql-connector-j")
    testImplementation("com.h2database:h2:2.2.224")


    // REST docs
    asciidoctorExt("org.springframework.restdocs:spring-restdocs-asciidoctor:3.0.1")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc:3.0.1")

    // jwt
    implementation("io.jsonwebtoken:jjwt-api:0.12.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.5")

}
