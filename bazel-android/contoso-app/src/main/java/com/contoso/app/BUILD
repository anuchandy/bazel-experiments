load("@build_bazel_rules_android//android:rules.bzl", "android_binary", "android_library")

android_library(
    name = "main_lib",
    srcs = ["MainActivity.java"],
    manifest = "AndroidManifest.xml",
    resource_files = glob(["res/**"]),
    deps = [
        "//contoso-common/src/main/java/com/contoso/common:contoso_common_lib",
    ],
)

android_binary(
    name = "main_lib_bin",
    manifest = "AndroidManifest.xml",
    deps = [":main_lib"],
)
