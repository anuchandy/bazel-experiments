
## build with default Java tool chain: Java8

```
bazel clean

bazel build :application
```

## build with java9 tool chain

```
bazel clean

bazel build \
      --host_java_toolchain=@bazel_tools//tools/jdk:toolchain_java9 \
      --java_toolchain=@bazel_tools//tools/jdk:toolchain_java9 \
      :application
```

## build with java11 tool chain

```
bazel clean

bazel build \
      --host_java_toolchain=@bazel_tools//tools/jdk:toolchain_java11 \
      --java_toolchain=@bazel_tools//tools/jdk:toolchain_java11 \
      :application
```

## To ensure the correct Java tool chain is used, check the byteCode version in output .class files:

Change .jar extension to .zip for:

/Users/anuchan/code/jva/bazel-java-learnings/bazel-java12/bazel-out/darwin-fastbuild/bin/libapplication.jar

extract it and find the App.class file, then run:

```
javap -verbose App.class | grep version
```

## Reference:

https://reverseengineering.stackexchange.com/questions/1328/find-out-a-java-class-files-compiler-version
https://gerrit-documentation.storage.googleapis.com/Documentation/3.0.0/dev-bazel.html 







