support-optional
===============================================

support-optional provides a Java 8 optional that supports Java 6, Android and
RetroLambda development while having an easy migration to the real optional
class. This library is much in the spirit of ActionBarSherlock that it was only
designed to be a removed.

> It has been deprecated from inception with the intention that you could some
day throw it away. <br>
--<cite>Jake Wharton</cite>

Other backports have various problems like different class names, slightly
different behavior or interface default methods. These make it harder to use and
migrate to the real Java 8 optional class. So here is yet another optional
backport, so you don't have to waste your time writing one.

Download
-----

Add the following to your root build.gradle
```gradle
repositories {
     maven { url "https://jitpack.io" }
}
```

Then add the following to your modules build.gradle file
```gradle
dependencies {
      compile 'com.github.buttink:support-optional:1.0-SNAPSHOT'
}
```

Migration
---------

Perform the following replacement regex

```
s/com\.stockingd\.optional\.//g
```

This could be done with find and sed in your project folder via

```bash
find . -name "*.java" -exec sed -i 's/com\.stockingd\.optional\.//g' {} \;
```

or with an IDE so a search and replace without regex of
`com.stockingd.optional.` with nothing over your entire src directory.

License
-------

```
Copyright 2017 David Stocking

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
