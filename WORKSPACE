load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

http_archive(
    name = "rules_proto",
    sha256 = "602e7161d9195e50246177e7c55b2f39950a9cf7366f74ed5f22fd45750cd208",
    strip_prefix = "rules_proto-97d8af4dc474595af3900dd85cb3a29ad28cc313",
    urls = [
        "https://mirror.bazel.build/github.com/bazelbuild/rules_proto/archive/97d8af4dc474595af3900dd85cb3a29ad28cc313.tar.gz",
        "https://github.com/bazelbuild/rules_proto/archive/97d8af4dc474595af3900dd85cb3a29ad28cc313.tar.gz",
    ],
)
load("@rules_proto//proto:repositories.bzl", "rules_proto_dependencies", "rules_proto_toolchains")
rules_proto_dependencies()
rules_proto_toolchains()

RULES_KOTLIN_TAG = "legacy-1.4.0-rc3"
RULES_KOTLIN_SHA = "da0e6e1543fcc79e93d4d93c3333378f3bd5d29e82c1bc2518de0dbe048e6598"

http_archive(
    name = "io_bazel_rules_kotlin",
    url = "https://github.com/bazelbuild/rules_kotlin/releases/download/%s/rules_kotlin_release.tgz" % RULES_KOTLIN_TAG,
    sha256 = RULES_KOTLIN_SHA,
)

load("@io_bazel_rules_kotlin//kotlin:kotlin.bzl", "kotlin_repositories")
kotlin_repositories()
register_toolchains("//:kotlin_toolchain")

RULES_JVM_EXTERNAL_TAG = "3.2"
RULES_JVM_EXTERNAL_SHA = "82262ff4223c5fda6fb7ff8bd63db8131b51b413d26eb49e3131037e79e324af"

http_archive(
    name = "rules_jvm_external",
    sha256 = RULES_JVM_EXTERNAL_SHA,
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

http_archive(
    name = "io_grpc_grpc_java",
    sha256 = "e274597cc4de351b4f79e4c290de8175c51a403dc39f83f1dfc50a1d1c9e9a4f",
    strip_prefix = "grpc-java-1.28.0",
    url = "https://github.com/grpc/grpc-java/archive/v1.28.0.zip",
)

load("@io_grpc_grpc_java//:repositories.bzl", "grpc_java_repositories")
grpc_java_repositories()

RULES_GRPC_KOTLIN_TAG = "0.1.4"
RULES_GRPC_KOTLIN_SHA = "bfc60770a48aaec1489b4cb7dbf0ff712bed7ed7d2479281d94f56f565832048"

http_archive(
    name = "com_github_grpc_grpc_kotlin",
    sha256 = RULES_GRPC_KOTLIN_SHA,
    strip_prefix = "grpc-kotlin-%s" % RULES_GRPC_KOTLIN_TAG,
    url = "https://github.com/grpc/grpc-kotlin/archive/v%s.zip" % RULES_GRPC_KOTLIN_TAG,
)

load("//3rdparty/jvm:workspace.bzl", "jvm_dependencies")
jvm_dependencies()
