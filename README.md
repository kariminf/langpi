# LangPi

[![Project](https://img.shields.io/badge/Project-LangPi-4B0082.svg)](https://github.com/kariminf/langpi)
[![Type](https://img.shields.io/badge/Type-Research-4B0082.svg)](https://github.com/kariminf/langpi)
[![License](https://img.shields.io/badge/License-Apache_2-4B0082.svg)](http://www.apache.org/licenses/LICENSE-2.0)
[![Travis](https://img.shields.io/travis/kariminf/langpi.svg)](https://travis-ci.org/kariminf/langPi)
[![codecov](https://img.shields.io/codecov/c/github/kariminf/langpi.svg)](https://codecov.io/gh/kariminf/langpi)
[![jitpack](https://jitpack.io/v/kariminf/langpi.svg)](https://jitpack.io/#kariminf/langpi)

It is a set of tools for language processing.
It contains:
* Basic language processing: Mainly, they are for preprocessing text before using it.
 * Normalization (norm)
 * Segmentation: sentence Segmentation (seg) and word tokenization (tok)
 * Stemming or lemmatization  (stem)
 * Stop-words elimination (swe)
* Evaluation methods: Methods for evaluating NLP systems.
 * Automatic text summarization: ROUGE method
* Wordnet

## Basic processing
@TODO complete filling the table

Main package (Apache 2 licensed)

| Info module | norm | seg | tok | stem | swe |
| ---- | ---- | ---- | ---- | ---- | ---- |
| arabic.ArInfo | diacritics, line | RegEx | RegEx | Khoja algo. | ---- |
| bulgarian.BgInfo | ---- | ---- | ---- | ---- | ---- |
| catalan.CaInfo | ---- | ---- | ---- | ---- | ---- |
| czech.CsInfo | ---- | ---- | ---- | ---- | ---- |
| german.DeInfo | ---- | ---- | ---- | ---- | ---- |
| greek.ElInfo | ---- | ---- | ---- | ---- | ---- |
| english.EnInfo | ---- | ---- | ---- | ---- | ---- |
| spanish.EsInfo | ---- | ---- | ---- | ---- | ---- |
| basque.EuInfo | ---- | ---- | ---- | ---- | ---- |
| persian.FaInfo | ---- | ---- | ---- | ---- | ---- |
| finnish.FiInfo | ---- | ---- | ---- | ---- | ---- |
| french.FrInfo | ---- | ---- | ---- | ---- | ---- |
| hindi.HiInfo | ---- | ---- | ---- | ---- | ---- |
| hungarian.HuInfo | ---- | ---- | ---- | ---- | ---- |
| indonesian.IdInfo | ---- | ---- | ---- | ---- | ---- |
| italian.ItInfo | ---- | ---- | ---- | ---- | ---- |
| japanese.JaInfo | ---- | ---- | ---- | ---- | ---- |
| dutch.NlInfo | ---- | ---- | ---- | ---- | ---- |
| nynorsk.NnInfo | ---- | ---- | ---- | ---- | ---- |
| norwegian.NoInfo | ---- | ---- | ---- | ---- | ---- |
| portuguese.PtInfo | ---- | ---- | ---- | ---- | ---- |
| romanian.RoInfo | ---- | ---- | ---- | ---- | ---- |
| russian.RuInfo | ---- | ---- | ---- | ---- | ---- |
| swedish.SvInfo | ---- | ---- | ---- | ---- | ---- |
| turkish.TrInfo | ---- | ---- | ---- | ---- | ---- |
| chinese.ZhInfo | ---- | ---- | ---- | ---- | ---- |

Agpl3 package (AGPL 3.0 licensed)

| Info module | norm | seg | tok | stem | swe |
| ---- | ---- | ---- | ---- | ---- | ---- |
| hebrew.HeInfo |  |  |  |  | ---- |
| thai.ThInfo | ---- | ---- | ---- | ---- | ---- |

## Evaluation
Currently, there is just Rouge n-gram implementation for evaluating text summarization

## Wordnet
A module for wordnet management.

Create a folder ./wordnetDB/dict and extract [wordnet 3.0](https://wordnet.princeton.edu/wordnet/download/current-version/) files in it.


@TODO add python script to create wordnet.sqlite

To use kariminf.langpi.wordnet.WNTools.java properly, copy this folder to bin/ folder

## How to compile?

Just open it using gradle and it will do what it has to be done.
This project depends on many libraries:
* Apache Lucene (with Snowball for stemming)
* JWI (for wordnet)
* Apache openNLP
* sqlite-jdbc
* KHebMorph (a port of HebMorph)
* KJHazm (a port of JHazm)
* KOpenNlp1.4 (a port of openNLP 1.4 to work alogside with ulterir versions without conflict; Thai segmentation uses it)
* KToolJa (plugins management)

## How to use?

You can download the jar from "release" section and link it to your project;
Or you can use https://jitpack.io to manage dependency.
Replace "tag" with the release tag; for example "1.1.1".

The project generates many jars; a jar for each module. So there is a way to just use one
jar at a time.

### Gradle

Add this to your "build.gradle":
```
repositories {
    ...
    maven { url 'https://jitpack.io' }
}
```
To download all the modules:
```
dependencies {
    compile 'com.github.kariminf:langpi:tag'
}
```
For each module:
```
dependencies {
    compile 'com.github.kariminf.LangPi:langpi-basic-agpl3:tag'
    compile 'com.github.kariminf.LangPi:langpi-basic-main:tag'
    compile 'com.github.kariminf.LangPi:langpi-eval:eval'
    compile 'com.github.kariminf.LangPi:langpi-wordnet:tag'
}
```
### Maven

```xml
<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
</repositories>
```
For each module:
```xml
<dependency>
	    <groupId>com.github.kariminf.LangPi</groupId>
	    <artifactId>langpi-basic-agpl3</artifactId>
	    <version>tag</version>
</dependency>
<dependency>
	    <groupId>com.github.kariminf.LangPi</groupId>
	    <artifactId>langpi-basic-main</artifactId>
	    <version>tag</version>
</dependency>
<dependency>
	    <groupId>com.github.kariminf.LangPi</groupId>
	    <artifactId>langpi-eval</artifactId>
	    <version>tag</version>
</dependency>
<dependency>
	    <groupId>com.github.kariminf.LangPi</groupId>
	    <artifactId>langpi-wordnet</artifactId>
	    <version>tag</version>
</dependency>
```

## License

Copyright (C) 2016-2017 Abdelkrime Aries

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

## Release license

There are two releases:
* 1st: under Apache2 license; it contains most languages
* 2nd: under LGPL 3.0 license; it contains hebrew and thai
  * The hebrew preprocessing tools set must be released under AGPL3.0, since it uses HebMorph.
  * The Thai preprocessing tools set must be released under LGPL2.1, since it uses OpenNlp 1.4.2 and JWNL.
