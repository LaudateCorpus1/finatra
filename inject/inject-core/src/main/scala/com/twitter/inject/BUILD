scala_library(
    sources = ["*.scala"],
    compiler_option_sets = ["fatal_warnings"],
    provides = scala_artifact(
        org = "com.twitter",
        name = "inject-core",
        repo = artifactory,
    ),
    strict_deps = True,
    tags = ["bazel-compatible"],
    dependencies = [
        "//:scala-reflect",
        "3rdparty/jvm/aopalliance",
        "3rdparty/jvm/com/google/inject:guice",
        "3rdparty/jvm/com/google/inject/extensions:guice-assistedinject",
        "3rdparty/jvm/javax/inject:javax.inject",
        "3rdparty/jvm/net/codingwell:scala-guice",
        "3rdparty/jvm/org/joda:joda-convert",
        "finatra/inject/inject-slf4j/src/main/scala/com/twitter/inject",  # TODO: remove [DEPRECATED]
        "util/util-app/src/main/scala",
        "util/util-slf4j-api/src/main/scala/com/twitter/util/logging",
    ],
    exports = [
        "3rdparty/jvm/com/google/inject:guice",
        "3rdparty/jvm/com/google/inject/extensions:guice-assistedinject",
        "3rdparty/jvm/net/codingwell:scala-guice",
        "finatra/inject/inject-slf4j/src/main/scala/com/twitter/inject",  # TODO: remove [DEPRECATED]
        "util/util-app/src/main/scala",
        "util/util-slf4j-api/src/main/scala/com/twitter/util/logging",
    ],
)
