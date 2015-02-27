---
layout: default
title: Refactoring fonctionnel avec Java 8
---

### Objectif du Hands-On

Ce hands-on a pour objectif de vous faire coder en utilisant les nouvelles fonctionnalitées de java 8.

Le principe est simple : des fonctionnalités ont été codées en java 7 à vous des les refactorer pour utiliser les améliorations de java 8.
Par chance, toutes les méthodes à modifier sont couvertes par des tests qui passent en Java 7 et qui doivent évidement toujours passer une fois passé en Java 8.

Des commentaires avec des TODO dans le code sont la pour vous aiguiller.

### Pre-requis
* Avoir le jdk 8 dernière version d'installé
* Avoir git
* Avoir maven
* clone le repository git clone [https://github.com/xebia-france/functional-programming-java8.git](https://github.com/xebia-france/functional-programming-java8.git)

Des clefs USB sont disponibles avec ces pre-requis.

-----------------

### (Optionel) Etape 0 Discovery new lambda syntax
 Cette première étape permet de découvir la syntaxe de base des fonctions en Java 8. Si vous êtes déja à l'aise avec les **'Lambda'**, **'Static & Instance method reference'**, et **'Constructor reference'** vous pouvez passer directement à **[l'Etape 1](#step1)**.

 Se connecter à la branche step 0 :
     `git checkout step0`
 * Ouvrir la classe **FunctionGenerator**. Cette classe permet de retourner des fonctions mais elle a été codée en Java 7, avec des classes anonymes. Modifier la pour utiliser la nouvelle syntaxe Java 8 des lambdas.

 plus d'infos :

 * [Syntax of Lambda Expressions](http://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax)

-----------------

### <a name="step1"></a>1 Play With basic collection enhancement
 **Refactorer le code java 7 en Java 8**  (Sans utiliser stream())

 * Se connecter à la branche step2 :
     `git checkout step2`
 * Modifier les méthodes de la classe CollectionUtils : Utiliser les méthodes ajoutées dans l'api collection sans passer par les streams

 plus d'infos :

 * [http://download.java.net/jdk8/docs/api/java/util/Collection.html](http://download.java.net/jdk8/docs/api/java/util/Collection.html)
 * [http://download.java.net/jdk8/docs/api/java/util/Map.html](http://download.java.net/jdk8/docs/api/java/util/Map.html)
 * [http://download.java.net/jdk8/docs/api/java/lang/Iterable.html](http://download.java.net/jdk8/docs/api/java/lang/Iterable.html)

-----------------

### 2 Play with Collection Stream
 **Refactorer le code java 7 en Java 8**

 * Se connecter à la branche step3 :
      `git checkout step3`
 * Refactorer la class UserService.java

 plus d'infos :
  * [Stream Api](http://download.java.net/jdk8/docs/api/java/util/stream/Stream.html)
  * [Optional](http://download.java.net/jdk8/docs/api/java/util/Optional.html)
  * [Collectors](http://download.java.net/jdk8/docs/api/java/util/stream/Collectors.html)

  -----------------

### 3 Play with others Stream
 **Refactorer le code java 7 en Java 8**

 * Se connecter à la branche step4 :
      `git checkout step4`
 * Refactorer les class FileUtils.java et NumberUtils.java

  -----------------

### 4 Date and Time

**Refactorer le code java 7 en Java 8**

* Se connecter à la branche step1 :
    `git checkout step1`
* Modifier les méthodes de la classe DateUtils : Changer le type **Date** par **LocalDate** (Date sans heure) ou **LocalDateTime** (Date avec heure) en fonction du besoin.
* Utiliser les méthodes de l'api **java.time** pour refactorer ce code.

Plus d'infos : [http://download.java.net/jdk8/docs/api/java/time/package-summary.html](http://download.java.net/jdk8/docs/api/java/time/package-summary.html)
