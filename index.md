---
layout: default
title: Refactoring fonctionnel avec Java 8
---

### Objectif du Hands-On

Ce hands-on a pour objectif de vous faire coder en utilisant les nouvelles fonctionnalitées de java 8.

Le principe est simple : des fonctionnalités ont été codées en java 7 à vous des les refactorer pour utiliser les améliorations de java 8.

Des commentaires avec des TODO sont la pour vous aiguiller.

### Pre-requis
* Avoir le jdk 8 dernière version d'installé
* Avoir git
* clone le repository git clone https://github.com/ibeauvais/PlayWithJava8.git
### 1. Date and Time
**Refactorer le code java 7 en Java 8**

* Se connecter à la branche step1 :
    `git checkout step1`
* Modifier les méthodes de la classe DateUtils : Changer le type **Date** par **LocalDate** (Date sans heure) ou **LocalDateTime** (Date avec heure) en fonction du besoin.
* Utiliser les méthodes de l'api **java.time** pour refactorer ce code.

Plus d'infos : [http://download.java.net/jdk8/docs/api/java/time/package-summary.html](http://download.java.net/jdk8/docs/api/java/time/package-summary.html)

-----------------
### 2. Play With basic collection enhancement
 **Refactorer le code java 7 en Java 8**  (Sans utiliser stream())

 * Se connecter à la branche step2 :
     `git checkout step2`
 * Modifier les méthodes de la classe CollectionUtils : Utiliser les méthodes ajoutées dans l'api collection sans passer par les streams

 plus d'infos :

 * [http://download.java.net/jdk8/docs/api/java/util/Collection.html](http://download.java.net/jdk8/docs/api/java/util/Collection.html)
 * [http://download.java.net/jdk8/docs/api/java/util/Map.html](http://download.java.net/jdk8/docs/api/java/util/Map.html)
 * [http://download.java.net/jdk8/docs/api/java/lang/Iterable.html](http://download.java.net/jdk8/docs/api/java/lang/Iterable.html)

-----------------
### 3. Play with Collection Stream
 **Refactorer le code java 7 en Java 8**

 * Se connecter à la branche step3 :
      `git checkout step3`
 * Refactorer la class UserService.java

 plus d'infos :
  * [Stream Api](http://download.java.net/jdk8/docs/api/java/util/stream/Stream.html)
  * [Optional](http://download.java.net/jdk8/docs/api/java/util/Optional.html)
  * [Collectors](http://download.java.net/jdk8/docs/api/java/util/stream/Collectors.html)

  -----------------
### 4. Play with others Stream
 **Refactorer le code java 7 en Java 8**

 * Se connecter à la branche step4 :
      `git checkout step4`
 * Refactorer les class FileUtils.java et NumberUtils.java

  -----------------
### 5. Play With new concurent Api

 * Se connecter à la branche step5 :
      `git checkout step5`