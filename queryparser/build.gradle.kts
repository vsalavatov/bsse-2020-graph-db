plugins {
    application
    antlr
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    antlr("org.antlr:antlr4:4.8")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.1")
    testRuntimeOnly("org.slf4j:slf4j-simple:1.7.25")

    implementation(project(":core"))
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClassName = "com.bsse2018.salavatov.flt.queryparser.cli.MainKt"
}

tasks.generateGrammarSource {
    arguments = arguments + listOf("-visitor")
}

tasks.compileKotlin {
    dependsOn(tasks.generateGrammarSource)
}
tasks.compileTestKotlin {
    dependsOn(tasks.generateTestGrammarSource)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}