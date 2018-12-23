#!/usr/bin/env bash

#$ANDROID_HOME/build-tools/25.0.1/aapt

$ANDROID_HOME/build-tools/25.0.1/aapt list -v app/build/outputs/apk/app-debug.apk > output/list.txt

#$ANDROID_HOME/build-tools/25.0.1/aapt dump resources app/build/outputs/apk/app-debug.apk

#$ANDROID_HOME/build-tools/25.0.1/aapt dump badging app/build/outputs/apk/app-debug.apk

#$ANDROID_HOME/build-tools/25.0.1/aapt dump xmltree app/build/outputs/apk/app-debug.apk res/layout/activity_main.xml