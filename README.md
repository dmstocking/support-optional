support-optional: Faithful backport of Optional
===============================================

Inspired by projects like RetroLambda and numerous other optional backports.
The problem with other backports is that they do not provide an easy way to
migrate your code away from there library to actual Java8. Some change the
names of class and others use default methods which make it harder for Android
developers to use. So this is yet another attempt to have optional for java 6.

Setup
-----

Add the following to your build.gradle

```
   repositories {
        jcenter()
        maven { url "https://jitpack.io" }
   }

   dependencies {
         compile 'com.github.buttink:support-optional:1.0-SNAPSHOT'
   }
```

Migration
---------

Perform the following replacement regex

s/import com\.stockingd\.optional\./import /

or with an IDE so a search and replace without regex of

"import com.stockingd.optional."
with
"import "
