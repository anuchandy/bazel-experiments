load("@build_bazel_rules_android//android:rules.bzl", "android_binary", "android_library")

android_library(
    name = "contoso_common_lib",
    srcs = glob(["*.java"]),
    visibility = ["//:__subpackages__"],
)

android_binary(
    name = "contoso_common_lib_bin",
    manifest = "AndroidManifest.xml",
    visibility = ["//:__subpackages__"],
    deps = [":contoso_common_lib"],
)