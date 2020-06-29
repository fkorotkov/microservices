# GENERATED
load("@rules_jvm_external//:defs.bzl", "maven_install")

def jvm_dependencies():
  maven_install(
    artifacts = [
      "com.fathzer:javaluator:3.0.3",
      "com.squareup:kotlinpoet:1.5.0",
      "io.grpc:grpc-core:1.28.1",
      "junit:junit:4.13",
      "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.6",
    ],
    repositories = [
      "https://repo1.maven.org/maven2",
    ],
  )