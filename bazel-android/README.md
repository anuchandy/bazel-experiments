
### Building the application using bazel command line

In terminal switch to the directory containing WORKSPACE file.

```
bazel clean
bazel build //contoso-app/src/main/java/com/contoso/app:main_lib_bin
```

### Running the application on emulator using bazel command line

```
~/Library/Android/sdk/emulator/emulator -avd Pixel_2_XL_API_23 &

bazel clean

bazel mobile-install //contoso-app/src/main/java/com/contoso/app:main_lib_bin --start_app

```

Note: You can list all the avds installed on your machine by running:

```
~/Library/Android/sdk/emulator/emulator -list-avds
```

If no avds installed then install one using AndroidStudio Tools > Android > AVD Manager

### Instrumentation test (Supported only on LINUX)

`//contoso-common/src/androidTest/java/com/contoso/common/` contains intrumentation test for the `contoso_common_lib`. Note that this instrumentation test is not for `main_lib_bin` app which has UI elements. 

To run the test, following image is needed (The `android_instrumentation_test` rule in the test module's BUILD file uses this emulated device)

```
@android_test_support//tools/android/emulated_devices/generic_phone:android_23_x86
```

The image can be installed via sdk-manager:

```
~/Library/Android/sdk/tools/bin/sdkmanager "system-images;android-23;default;x86"
```

Now instrumentation test can run on this device:

```
sudo bazel test //contoso-common/src/androidTest/java/com/contoso/common:contoso_common_lib_instrumentation_test  --config=local_device --incompatible_disable_deprecated_attr_params=false --incompatible_new_actions_api=false --host_force_python=PY2 --incompatible_use_python_toolchains
```

Reference:

1. https://docs.bazel.build/versions/master/android-instrumentation-test.html
2. https://docs.bazel.build/versions/master/be/android.html#android_instrumentation_test 
3. Android testing samples: https://github.com/android/testing-samples 

### Using Android Studio

The project can be loaded in android studio.

1. Install bazel plugin for android studio https://ij.bazel.build/docs/bazel-plugin.html#getting-started 
        (Note: The plugin in the market place didn't work for me, so had to build from the plugin source)
2. Import the project using "Import Bazel Project", refer https://ij.bazel.build/docs/import-project.html
   The //bazel-android directory contains WORKSPACE file and .bazelproject those are required by the import step

### About project structure and BUILD files

The workspace contains two modules `contoso-app` and `contoso.common`. 

The input to the build command: `//contoso-app/src/main/java/com/contoso/app:main_lib_bin` refer a `android_binary` rule with name `main_lib_bin` in the BUILD. This rule produces apk which can be installed on mobile device.

The content of BUILD file `//contoso-app/src/main/java/com/contoso/app/BUILD` is:
```json
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
```

`bazel build` takes path to a dir containing BUILD file followed by a : and name of the rule that needs to be build.

The deps section refer `//contoso-common/src/main/java/com/contoso/common:contoso_common_lib`, which is again path to a dir containing BUILD file followed by a colon and name of the rule referring the library that `main_lib` app depends on.

The content of BUILD file `//contoso-common/src/main/java/com/contoso/common/BUILD` is:
```json
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

```

Note: The android_binary rule `contoso_common_lib_bin` is not needed for `main_lib` or `main_lib_bin` app to work. It's there to enable instrumentation test.
