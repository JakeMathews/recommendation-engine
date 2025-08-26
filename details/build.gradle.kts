plugins {
    id("java")
    id("nu.studer.jooq") version "9.0"
}

group = "com.mathews"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":core"))

    // Database
    implementation("org.postgresql:postgresql:42.7.3")
    implementation("org.jooq:jooq:3.19.6")
    jooqGenerator("org.postgresql:postgresql:42.7.3")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// jOOQ configuration
jooq {
    configurations {
        create("main") {
            jooqConfiguration.apply {
                jdbc.apply {
                    driver = "org.postgresql.Driver"
                    url = "jdbc:postgresql://localhost:5432/postgres"
                    user = "jake"
                    password = "finn_is_the_best_dog"
                }
                generator.apply {
                    database.apply {
                        name = "org.jooq.meta.postgres.PostgresDatabase"
                        includes = "actions|recommendations" // Explicitly only generate these tables
                    }
                    target.apply {
                        packageName = "com.mathews.recommender.jooq"
                        directory = "src/main/generated"
                    }
                }
            }
        }
    }
}

tasks.test {
    useJUnitPlatform()
}
