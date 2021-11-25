import org.graalvm.buildtools.gradle.tasks.BuildNativeImageTask

plugins {
    id("com.github.johnrengelman.shadow") version "7.0.0"
    id("io.micronaut.application") version "3.1.0-SNAPSHOT"
    id("io.micronaut.aot") version "3.1.0-SNAPSHOT"
    //id("groovy")
}

version = "0.1"
group = "demo.app"

repositories {
    mavenCentral()
}

micronaut {
    version.set("3.2.0")
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("demo.app.*")
    }

    aot {
//        version = "1.0.1"
        sealEnvironment.set(true)
        optimizeServiceLoading.set(true)
        optimizeClassLoading.set(true)
        convertYamlToJava.set(true)
        precomputeOperations.set(true)
//        replaceLogbackXml.set(true)
    }


}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

dependencies {
    annotationProcessor("io.micronaut:micronaut-http-validation")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-runtime")
    implementation("javax.annotation:javax.annotation-api")
    runtimeOnly("ch.qos.logback:logback-classic")
    implementation("io.micronaut:micronaut-validation")
    //implementation("org.codehaus.groovy:groovy")
}


application {
    mainClass.set("demo.app.Application")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}


graalvmNative {
    binaries {
        named("optimized") {
            verbose.set(true)
            buildArgs.add("-H:DashboardDump=" + file("$buildDir/dump").absolutePath)
            buildArgs.add("-H:+DashboardAll")
            buildArgs.add("-H:+ReportExceptionStackTraces")

            // profiling
            buildArgs.add("-H:-DeleteLocalSymbols")
            buildArgs.add("-H:+PreserveFramePointer")
        }
    }
}

tasks.withType<JavaExec>().configureEach {
    outputs.upToDateWhen { false }
}


val nativeOptimizedCompile = tasks.named<BuildNativeImageTask>("nativeOptimizedCompile")

tasks.register<Exec>("perfRun") {
    dependsOn(nativeOptimizedCompile)
    argumentProviders.add(object : CommandLineArgumentProvider {
        override fun asArguments() = listOf(
                "record",
                "-g",
                "-F", "100000",
                nativeOptimizedCompile.get().outputFile.get().asFile.absolutePath,
                "--call-graph", "dwarf"
        )
    })
    commandLine("perf")
    standardOutput = file("perf.data").outputStream()
}


tasks.named<JavaExec>("optimizedRun") {
    javaLauncher.set(javaToolchains.launcherFor {
        languageVersion.set(JavaLanguageVersion.of(11))
    })
//    debug = true
//    jvmArgs("-agentpath:/home/cchampeau/TOOLS/YourKit-JavaProfiler-2019.8/bin/linux-x86-64/libyjpagent.so=sampling,onexit=snapshot")
}
