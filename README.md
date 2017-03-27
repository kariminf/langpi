# LangPi

[![Project](https://img.shields.io/badge/Project-LangPi-4B0082.svg)](https://github.com/kariminf/LangPi)
[![License](https://img.shields.io/badge/License-Apache_2-4B0082.svg)](http://www.apache.org/licenses/LICENSE-2.0)
[![Travis](https://img.shields.io/travis/kariminf/LangPi.svg)](https://travis-ci.org/kariminf/LangPi)
[![codecov](https://img.shields.io/codecov/c/github/kariminf/LangPi.svg)](https://codecov.io/gh/kariminf/LangPi)
[![jitpack](https://jitpack.io/v/kariminf/LangPi.svg)](https://jitpack.io/#kariminf/LangPi)

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

## Supported languages

### Basic processing
@TODO copmlete filling the table

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
