[![Hex.pm](https://img.shields.io/badge/Project-LangPi-red.svg?style=plastic)](https://github.com/kariminf/AllSummarizer)
[![Hex.pm](https://img.shields.io/badge/License-Apache_2-red.svg?style=plastic)](https://github.com/kariminf/AllSummarizer/blob/master/LICENSE)
[![Hex.pm](https://img.shields.io/badge/Version-1.0.0-red.svg?style=plastic)](https://github.com/kariminf/AllSummarizer/releases)

LangPi (Language processing interface)
====================================
It is a set of tools for language processing.
It contains:
* Basic language processing: Mainly, they are for preprocessing text before using it.
 * Normalization
 * Segmentation
 * Stemming
 * Stop-words elimination
* Evaluation methods: Methods for evaluating NLP systems.
 * Automatic text summarization: ROUGE method
* Wordnet

# How to compile?
TODO: add a detailed description about the assemblance with the plugins (LangPi project)

# Dependencies
This project depends on many libraries:
* Apache Lucence
* Snowball (for stemming)
* JWI (for wordnet)
* Apache openNLP

# License
The code is released under Apache 2.0 license.
For more details about this license, check [LICENSE](./LICENSE) file

Some parts can not be released under Apache2:
* kariminf.langpi.basic.hebrew: The hebrew preprocessing tools set must be released under AGPL3.0, since it uses HebMorph.
* kariminf.langpi.basic.thai: The Tai preprocessing tools set must be released under LGPL2.1, since it uses OpenNlp 1.4.2 and JWNL.
