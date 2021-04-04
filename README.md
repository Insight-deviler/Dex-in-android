# Dex-in-android
Convert Jar file to Dex in android using Dalvik Dex library!!!

# Usage
This is a sample app for converting jar files (java 7 and below) to dex in any android phones (versions). 
The main drawback of dx is that it cannot convert jar files which use java 8 features.

# What method used?
I used simple method 

String[] arg = {}; 

for running the dex library.

# Library used
A heartly thanks for JakeWharton for dalvik-dx library

Simply add this dependency to the gradle and sync

compile 'com.jakewharton.android.repackaged:dalvik-dx:11.0.0_r3'
