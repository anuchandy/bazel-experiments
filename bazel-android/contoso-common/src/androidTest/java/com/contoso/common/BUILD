load("@build_bazel_rules_android//android:rules.bzl", "android_binary", "android_library", "android_instrumentation_test")

android_library(
    name = "contoso_common_lib_test",
    srcs = glob(["*.java"]),
    deps = [
        "//contoso-common/src/main/java/com/contoso/common:contoso_common_lib",
        "@maven//:androidx_test_core",
        "@maven//:androidx_test_runner",
        "@maven//:androidx_test_espresso_espresso_core",
        "@maven//:androidx_test_ext_junit",
        "@maven//:junit_junit",
    ],
)

android_binary(
    name = "contoso_common_lib_test_bin",
    instruments = "//contoso-common/src/main/java/com/contoso/common:contoso_common_lib_bin",
    manifest = "AndroidTestManifest.xml",
    deps = [":contoso_common_lib_test"],
)

android_instrumentation_test(
    name = "contoso_common_lib_instrumentation_test",
    test_app = ":contoso_common_lib_test_bin",
    target_device = "@android_test_support//tools/android/emulated_devices/generic_phone:android_23_x86",
)