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

<blockquote class = 'help' markdown="1">

En Java 7, la notion de fonction pouvait être implémentée sous la forme d'une classe anonyme
avec une seule méthode d'instance.  
Par exemple la classe **Function** (Disponible avec la librairie guava en Java < 8) avec une méthode **'apply'** qui prend 1 object en entrée et
 retourne 1 object en sortie.  
 Avec la syntaxe lambda une fonction prenant une chaîne de caractêre en entrée et qui
retourne sa taille peut s'écrire :
{% highlight java %}
//Lambda version longue (input) -> { code}
Function<String,Integer> myFunction = (String input) -> {return input.length();};
// le compilateur déduit le type des paramètres d'entrées donc pas nécéssaire
Function<String,Integer> myFunction = (input) -> {return input.length();};
// un seul argument en entrée pas besoin de parenthèse
Function<String,Integer> myFunction = input -> {return input.length();};
// une seule instruction on peut supprimer les accolades et le return.
Function<String,Integer> myFunction = input -> input.length();
{% endhighlight %}


On peut donc utiliser cette syntaxe mais cerise sur le gâteau on peut faire référence
directement à la méthode length() avec la notation équivalente :
{% highlight java %}
// Instance Method reference
Function<String,Integer> myFunction = String::length;
{% endhighlight %}

De  même des syntaxes équivalentes existent pour :
{% highlight java %}
// Static method reference
IntFunction myFunction = Math::abs;
// Constructor reference
Function<String,StringBuilder> myFunction = StringBuilder::new;
{% endhighlight %}
 </blockquote>

 plus d'infos :

 * [Syntax of Lambda Expressions](http://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax)

 Avant de passer à la suite, sauvegardez votre solution : `git commit -a -m'step0-end' `

-----------------

### <a name="step1"></a>1. Basic collection enhancement
 **Refactorer le code java 7 en Java 8**, sans utiliser stream().

 * Se connecter à la branche step1 :
     `git checkout step1`
 * Modifier les méthodes de la classe BasicCollectionOperations : Utiliser les méthodes ajoutées dans l'api Collection sans passer par les streams.

 plus d'infos :

 * [http://docs.oracle.com/javase/8/docs/api/java/util/Collection.html](http://docs.oracle.com/javase/8/docs/api/java/util/Collection.html)
 * [http://docs.oracle.com/javase/8/docs/api/java/util/Map.html](http://docs.oracle.com/javase/8/docs/api/java/util/Map.html)
 * [http://docs.oracle.com/javase/8/docs/api/java/lang/Iterable.html](http://docs.oracle.com/javase/8/docs/api/java/lang/Iterable.html)

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

  -----------------

### 5 CompletableFuture

 **Refactorer le code java 7 en Java 8**

 * Se connecter à la branche step5 :
 `git checkout step5`
 * Exécuter le test **MerchantService** pour voir le temps de réponse de la méthode **retrieveMerchant**
 * L'utilisation des Futures en Java 7 implique des appels bloquants. En effet, les appels à **products.get()** et **stocks.get()** sont séquentiels. Java 8 nous permet de paralléliser ces appels plus facilement.
 * Implémenter la méthode **retrieveMerchantAsync** en utilisant le type **CompletableFuture**
 * Utiliser la méthode **supplyAsync** pour retrouver les produits et les stocks avec les méthodes **initProducts** et **initStocks**
 * Utiliser la méthode **thenCombine** pour enchaîner les appels et retourner un **Merchant**

 Plus d'infos :

 * [https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Future.html](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Future.html)
 * [http://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html](http://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html)
