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

Plus d'infos :

 * [Syntax of Lambda Expressions](http://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax)

 Avant de passer à la suite, sauvegardez votre solution : `git commit -a -m'step0-end' `

-----------------

### <a name="step1"></a>1. Basic collection enhancement
 **Refactorer le code java 7 en Java 8**, sans utiliser stream().

 * Se connecter à la branche step1 :
     `git checkout step1`
 * Modifier les méthodes de la classe BasicCollectionOperations : Utiliser les méthodes ajoutées dans l'api Collection sans passer par les streams.

 <blockquote class = 'help' markdown="1">
  * La méthode **foreach** de List prend en paramètre une fonction de type **Consumer** qui attend en entrée un object mais ne return aucune valeur.  
  Exemple :
  {% highlight java %} val -> System.out.println(val)
  {% endhighlight %}  

  * La méthode **removeIf** de List prend en paramètre une fonction de type **Predicate** qui attend en entrée un object et retourne un boolean.
  Exemple :
  {% highlight java %} val -> val !=null
  {% endhighlight %}  

  * La méthode **replaceAll** de List prend en paramètre une fonction de type **UnaryOperator** qui attend en entrée un object et retourne un object du même type.
  Exemple avec val un Integer:
  {% highlight java %} val -> val + 1
  {% endhighlight %}  

  * La méthode **merge** de map prend en paramètre une **clef**, une **valeur** et une **Bifonction**, une fonction qui prend en entrée 2 objects et renvoie un object.
    * Si la map ne contient pas la **clef**, la valeur est ajoutée à la map associée à cette **clef**.
    * Si la map contient la clef, on remplace la valeur associée par une nouvelle calculée avec la **BiFunction** appliquée sur l'ancienne **valeur** et la nouvelle **valeur**. Exemple :
    {% highlight java %} values.merge(key, word, (words, newWord) -> words + ", " + newWord);
    {% endhighlight %}  

  * La méthode **computeIfAbsent** prend en entrée une **clef** et une **Function**. Si la **clef** existe déjà, **computeIfAbsent** retourne la valeur existante. Si elle n'existe pas la fonction est appelée pour générer la **valeur** qui est stockée dans la map et retournée.

 </blockquote>

Plus d'infos :

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

 <blockquote class = 'help' markdown="1">
 * Pour utiliser les streams :
    1. Transformation de la collection en stream  
    `widgets.stream()`

    2. Cette nouvelle interface donne accès à des opérations intermédiaires sur mes données:  
    `widgets.stream().mapToInt(w -> w.getWeight())`

    3. Ainsi qu'à une opération terminale qui déclenchent les traitements :  
    `widgets.stream().mapToInt(w -> w.getWeight()).sum()`

    **Opération intermédiaires** : map, flatmap, filter, distinct,  limit, skip, sorted  
    **Opération terminales** : allMatch, anyMatch, count, findAny, findFirst,  forEach, max, min ....
Collect
  * La méthode **findFirst** de stream retourne un **Optional**, un objet qui represente une valeur qui peux être présente ou absente. Il est possible d'appliquer un traitement sur cette hypothétique valeur avec la méthode **map(Function)** qui se réalisera uniquement si il y a bien une valeur.
  * **flatMap** fonctionne comme map met remet 'A plat' les valeurs : par exemple une transformation qui aurait renvoyée une `List<List<String>>` avec un map, retournera une `List<String>` avec toutes les valeurs avec flatmap. De même une 'remise à plat' sur un `Optional<Optional<value>>` retournera un `Optional<Value>` qui sera présent uniquement si les 2 valeurs existes.
  * Avec Java 8, **Comparator** est une interface **'Fonctionnelle'**, on peut l'écrire sous forme de fonction :  
  {% highlight java %} Comparator<String> comparing = (val1, val2) -> Integer.compare(val1.length(), val2.length());
  {% endhighlight %}  
  Et même plus simple avec les méthodes ajoutés à l'interface Comparator :
  {% highlight java %} Comparator<String> comparing = Comparator.comparing(String::length);
  {% endhighlight %}
  * Pour limiter le nombre d'élements à traiter dans un stream, utiliser la méthode **limit**
  * Pour 'Collecter' les éléments traiter dans un stream, utiliser la méthode **collect(Collector collector)**
le collector peut être un collector prédéfini dans la classe Collectors ou son propre collector. Par exemple :
{% highlight java %} values.stream().collect(Collectors.toSet()) // retourne un Set avec les éléments
{% endhighlight %}
  * **Collectors.groupingBy** permet de retourner une Map avec les éléments groupés en fonction d'une clef.
Dans sa version la plus simple groupingBy attend en entrée une fonction permettant de déterminer la clef de regroupement
  * **Collectors.toMap** retourne une map également mais ne regroupe par les objects avec la même clé dans un autre object. La clef doit donc être unique. toMap dans sa version la plus simple attends deux fonctions en paramètre une pour déterminer la clef, l'autre pour déterminer la valeur.
  * **Collectors.summarizingInt** permet de renvoyer un object **IntSummaryStatistics** contenant des statistiques sur une valeur numérique des éléments du stream. **summarizingInt** attend une fonction permettant d'extraire cette valeur numérique.


 </blockquote>


 plus d'infos :

  * [Stream Api](http://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)  
  * [Optional](http://docs.oracle.com/javase/8/docs/api/java/util/Optional.html)  
  * [Collectors](http://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html)  

  Avant de passer à la suite, sauvegardez votre solution : `git commit -a -m'step2-end' `

  -----------------

### 3 Others Stream
 **Refactorer le code java 7 en Java 8**

 * Se connecter à la branche step3 :
      `git checkout step3`
 * Refactorer les classes **FileUtils.java** et **NumberUtils.java**
 d

 <blockquote class = 'help' markdown="1">
 * **Files.lines** permet de renvoyer un stream où chaque élément représente une ligne dans le fichier et cela de manière bufferisée. La lecture effective de la ligne ne se fera que lors du traitement réalisé par l'opération terminale.
 * Il est possible de 'skiper' un certain nombre d'élément dans un stream avec la méthode **skip(number)**
 * La méthode **Files.walk** permet de lire récursivement une hérarchie de répertoire de manière 'Lazy'.
 * La méthode **ints** de Random permet de renvoyer un stream infini où chaque itération sur un élément va renvoyer une chiffre aléatoire. Utiliser limit pour les streams infinis.
 * La méthode **Arrays.stream** permet de générer un stream à partir d'un tableau.
 * Pour les types primitifs, existent des streams particulier. Exemple **IntStream** pour des objets de type int. Pour passer d'un stream de primitifs à un stream d'objet il faut utiliser la méthode **mapToObj**. Pour passer d'un stream vers un stream de primitifs il faut utiliser **mapToLong**, **mapToInt** ..etc
 * La méthode **Collectors.partitioningBy** permet de partionner le contenu d'un stream en fonction d'un **Predicate**. La partition est effectuée en retournant une `Map<Boolean,List<Element>>` avec les éléments qui vérifient le prédicat associés à la clé Boolean.TRUE et les autres avec la clef Boolean.FALSE.
 * La méthonde IntStream.rangeClosed(min,max) permet de retourner un stream de int contenant les valeurs de 'min' à 'max'.
 * Il est possible de générer son propre Stream en utilisant la méthode **Stream.generate(Supplier supplier)**
 </blockquote>
  Plus d'infos :  

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

<blockquote class = 'help' markdown="1">
* **LocalDate** représente une date  sans les heures, et **LocalDateTime** une date avec les heures ces 2 objets sans notion de TimeZone. Tous les nouveaux formats de Date/time ont des méthodes pour passer d'un format vers un autre, exemple : {% highlight java %}  
LocalTime time = LocalTime.of(10, 30); // 10h30
LocalDate date = LocalDate.of(2015, 3, 10); // 10 Mars 2015
LocalDateTime dateTime = LocalDateTime.of(date, time); // 10 Mars 2015 10h30
{% endhighlight %}
* **LocalDate.parse** et **LocalDateTime.parse** permettent de parser une date sous forme de chaîne de caractère et formatter cette dernière avec la méthode **ofPattern** de classe **DateFormatter**.
* La méthode **Period.between** de renvoyer un object représentant une période entre deux **LocalDate**.
* **LocalDateTime** simplifie les manipulations des dates. En effet il est possible d'appeler directement les méthodes **plusMinutes** ou **minusMinutes**, etc.
* Pour gérer les TimeZone, les méthodes **atZone** et **withZoneSameInstant** permettent d'appliquer ou de convertir des timezones d'object DateTime.
</blockquote>

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
