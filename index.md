---
layout: default
title: Refactoring fonctionnel avec Java 8
---

### Objectif du Hands-On

Cet atelier a pour objectif de vous faire coder en utilisant les nouvelles fonctionnalitées de Java 8.

Le principe est simple : des fonctionnalités ont été codées en Java 7, à vous des les refactorer pour utiliser les améliorations de Java 8.
Les tests unitaires passent en java 7, ils doivent toujours passer en Java 8. Ils sont la pour vérifier l'iso fonctionnalité de l'implémentation.

Des commentaires avec des TODO dans le code sont la pour vous aiguiller.

### Pre-requis
* Avoir le jdk 8 dernière version d'installé ([http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html))
* Avoir git
* Avoir maven
* cloner le repository git clone [https://github.com/xebia-france/functional-programming-java8.git](https://github.com/xebia-france/functional-programming-java8.git)
* Si besoin régler votre IDE afin que le projet soit configuré avec la compatibilité java 8

Des clefs USB sont disponibles avec ces pre-requis.

-----------------

### (Optionel) Discovery new lambda syntax
 Cette première étape permet de découvrir la syntaxe de base des fonctions en Java 8. Si vous êtes déja à l'aise avec les **'Lambda'**, **'Static & Instance method reference'**, et **'Constructor reference'** vous pouvez passer directement à **[l'Etape 1](#step1)**.

 * Se connecter à la branche step 0 :
     `git checkout step0`
 * Ouvrir la classe **FunctionGenerator**. Cette classe permet de retourner des fonctions mais elle a été codée en Java 7, avec des classes anonymes. Modifier la pour utiliser la nouvelle syntaxe Java 8 des lambdas.

 plus d'infos :

 * [Syntax of Lambda Expressions](http://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax)

 Avant de passer à la suite, sauvegardez votre solution : `git commit -a -m'step0-end' `

-----------------

### <a name="step1"></a>1. Basic collection enhancement
 **Refactorer le code java 7 en Java 8**, sans utiliser stream().

 * Se connecter à la branche step1 :
     `git checkout step1`
 * Modifier les méthodes de la classe CollectionUtils : Utiliser les méthodes ajoutées dans l'api collection sans passer par les streams.

 plus d'infos :

 * [http://download.java.net/jdk8/docs/api/java/util/Collection.html](http://download.java.net/jdk8/docs/api/java/util/Collection.html)
 * [http://download.java.net/jdk8/docs/api/java/util/Map.html](http://download.java.net/jdk8/docs/api/java/util/Map.html)
 * [http://download.java.net/jdk8/docs/api/java/lang/Iterable.html](http://download.java.net/jdk8/docs/api/java/lang/Iterable.html)

Avant de passer à la suite, sauvegardez votre solution : `git commit -a -m'step1-end' `

-----------------

### 2 Collection Stream
 **Refactorer le code java 7 en Java 8**

 * Se connecter à la branche step2 :
      `git checkout step2`
 * Refactorer la class UserService.java

 plus d'infos :
  * [Stream Api](http://download.java.net/jdk8/docs/api/java/util/stream/Stream.html)
  * [Optional](http://download.java.net/jdk8/docs/api/java/util/Optional.html)
  * [Collectors](http://download.java.net/jdk8/docs/api/java/util/stream/Collectors.html)

  Avant de passer à la suite, sauvegardez votre solution : `git commit -a -m'step2-end' `

  -----------------

### 3 Others Stream
 **Refactorer le code java 7 en Java 8**

 * Se connecter à la branche step3 :
      `git checkout step3`
 * Refactorer les class FileUtils.java et NumberUtils.java

  plus d'infos :

    * [File Api](http://docs.oracle.com/javase/8/docs/api/java/nio/file/Files.html)
    * [Random Api](http://docs.oracle.com/javase/8/docs/api/java/util/Random.html)
    * [Collectors](http://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html)
    * [Stream Api](http://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)

 Avant de passer à la suite, sauvegardez votre solution : `git commit -a -m'step3-end' `

  -----------------

### 4 Date and Time

**Refactorer le code java 7 en Java 8**

* Se connecter à la branche step4 :
    `git checkout step4`
* Modifier les méthodes de la classe DateUtils : Remplacer le type **Date** par **LocalDate** (Date sans heure) ou **LocalDateTime** (Date avec heure) en fonction du besoin.
* Utiliser les méthodes de l'api **java.time** pour refactorer ce code.
* Attention pour cette step il faut dans certains cas modifier également les tests car la signature de la méthode change. Chaque changement de test est marqué d'un TODO.

Plus d'infos :

 * [http://docs.oracle.com/javase/8/docs/api/java/time/package-summary.html](http://docs.oracle.com/javase/8/docs/api/java/time/package-summary.html)
 * [http://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html](http://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html)
 * [http://docs.oracle.com/javase/tutorial/datetime/iso/period.html](http://docs.oracle.com/javase/tutorial/datetime/iso/period.html)
