[![Download](https://api.bintray.com/packages/buttink/support-optional/support-optional/images/download.svg) ](https://bintray.com/buttink/support-optional/support-optional/_latestVersion)
[![Build Status](https://travis-ci.org/dmstocking/support-optional.svg?branch=master)](https://travis-ci.org/dmstocking/support-optional)
[![Coverage Status](https://coveralls.io/repos/github/dmstocking/support-optional/badge.svg?branch=master)](https://coveralls.io/github/dmstocking/support-optional?branch=master)

support-optional
===============================================

support-optional provides a Java 9 optional that supports Java 6, Android and
RetroLambda development while having an easy migration to the real optional
class. This library is much in the spirit of ActionBarSherlock that it was only
designed to be a removed.

> It has been deprecated from inception with the intention that you could some
day throw it away. <br>
--<cite>Jake Wharton</cite>

Other backports have various problems like different class names, slightly
different behavior or interface default methods. These make it harder to use and
migrate to the real Java 9 optional class. So here is yet another optional
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
      compile 'com.github.dmstocking:support-optional:1.1'
}
```

Migration
---------

Perform the following replacement regex

```
s/com\.github\.dmstocking\.optional\.//g
```

This could be done with find and sed in your project folder via

```bash
find . -name "*.java" -exec sed -i 's/com\.github\.dmstocking\.optional\.//g' {} \;
```

or with an IDE so a search and replace without regex of
`com.github.dmstocking.optional.` with nothing over your entire src directory.

Unavailable Methods
-------------------

```
- Optional<T>::stream()
- OptionalInt::stream()
- OptionalLong::stream()
- OptionalDouble::stream()
```
Because this library does not backport streams, there is no stream method on any of the Optional
classes.

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
