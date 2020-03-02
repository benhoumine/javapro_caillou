# Caillou

## 1 - Objectifs du TP 
L'objectif de ce TP est de réaliser une application Spring Boot permettant d'obtenir des informations de nutritions de différents produits.  
Cette application récupérera des données sur les produits auprès d'une API publique, Open Food Facts. Elle mesurera différentes informations sur les qualités nutritives des produits et exposera les résultats de ces analyses sous forme d'une API REST.  
Vous aurez donc en charge de développer l'application en vous basant sur Spring Boot et sur n'importe qu'elle autre librairie Java disponible. 

## 2 - Présentation de l'application
Il existe une application mobile bien connue, Yuka, permettant d'obtenir les informations nutritives d'un produit en scannant son code barre. Le produit en question est noté et qualifié (Excellent, Bon, Mauvais, Mediocre). C'est très pratique lorsque vous allez faire vos courses en magasin, un peu moins lorsque vous passez par un drive.  

Afin de faire bénéficier du même type de service aux clients des drives, vous allez construire une appication exposant une API REST permettant d'obtenir toutes les informations nutritives d'un produit ainsi que son score nutritionnel associé. Exposer ses informations sous forme d'une API REST permettra aux differents drives de pouvoir facilement intégrer ces données sur leurs plateformes.  

Pour fournir toutes ces informations, votre application utilisera les données d'Open Food Facts. Elles sont accessibles via une API que vous aurez à interroger.


## 3 - Fonctionalités attendues 

#### US 0 : Récuperer les informations d'un produit sur Open Food Fact

Votre application devra utiliser l'API d'Open Food Fact afin de récupérer les informations d'un produit donné. 

L'API est disponible à l'adresse suivante :  
https://fr.openfoodfacts.org/data  
Exemple pour le code barre 3029330003533 : https://fr.openfoodfacts.org/api/v0/produit/3029330003533.json  
La documentation de l'API est diponible ici : https://en.wiki.openfoodfacts.org/API  



#### US 1 : Calcul de la composante N du Score nutritionnel

Basé sur les infos de l'API d'Open Food Fact pour un produit donné, calculez la composante N (Négative) du score nutrionnel. Vous trouverez les détails de ce calcul dans la section 6.1.



#### US 2 : Calcul de la composante P du Score nutritionnel

Basé sur les infos de l'API d'Open Food Fact pour un produit donné, calculez la composante P (Positive) du score nutrionnel. Vous trouverez les détails de ce calcul dans la section 6.2.



#### US 3 : Calcul du Score nutritionnel

Basé sur les composante N et P d'un un produit donné, votre application calculera le score nutritionnel basé sur les spécifications données dans la partie 6.3.  



#### US 4 : API REST

Votre application exposera une API REST, un endpoint fournissant les qualités, les défauts et le score nutritionnel d'un produit dont le code barre sera fournit.  

Elle respectera donc les conventions REST.





## 4 - Contraintes techniques 

Votre application exposera donc une API REST, elle devra à ce titre en respecter les conventions. Le format d'échange sera le JSON.  
Vous utiliserez le framework Spring Boot pour réaliser cette application. Si aucune version n'est imposé, je vous conseil de prendre la plus récente.  
Enfin, aucune contrainte n'est imposé sur les choix des librairies à utilisé dans vore application, ce qui veut dire que vous pouvez utiliser n'importe quelle librairie qui vous facilitera la vie.

Maven + java 8


## 5 - Evaluation
L'application à réaliser étant assez simple, la complétude sera évalué. Mais réaliser entièrement l'application ne sera pas suffisant puisque d'autres points seront évalués :
- Le niveau de test : Une application non testé ne peut pas être déployée en production sans prendre de risques importants. Et une application qui n'est pas en production est une application qui ne sert à rien. Donc tests unitaires et bonne couverture obligatoire.
- **<u>L'organisation du code</u> : Repensez au premier TP, rappelez vous des concepts évoqués (modularité, identification des responsabilités, raisons de changer...) afin de concevoir une application bien pensée, évolutive et testable**
- L'utilisation de Spring Boot : SB vous fournit un cadre de travail ainsi que différentes fonctionalités vous permettant de développer rapidement et proprement votre application. Autant s'en servir. Bien evidement l'utilisation de l'injection de dépendances de SB <u>**sera scruté avec intéret**</u> !

Pour ce TP, la clarté du code sera également évalué. Indentation, nommage des variables, classes, méthodes... et commentaires sont vos amis. 



## 6 - Score nutritionnel

Le score nutritionnel des aliments repose sur le calcul d’un score unique et global prenant en
compte, pour chaque aliment :

- une composante dite « négative » N,

- une composante dite « positive » P

  

### 6.1 - Composante Negative N

La composante N du score prend en compte les éléments nutritionnels dont il est recommandé
de limiter la consommation : densité énergétique (apport calorique en kJ pour 100 g d’aliment),
teneurs en acides gras saturés (AGS), en sucres simples (en g pour 100g d’aliment) et en sel (en
mg pour 100g d’aliment). Sa valeur correspond à la somme des points attribués, de 1 à 10, en
fonction de la teneur de la composition nutritionnelle de l’aliment (cf. tableau 1). La note pour la
composante N peut aller de 0 à 40.

Tableau 1 : Points attribués à chacun des éléments de la composante dite « négative » N  

| Points | Densité énergétique (kJ/100g) (energy_100g) | Graisses saturées (g/100g) (saturated-fat_100g) | Sucres simples (g/100g) (sugars_100g) | Sodium1 (mg/100g) (salt_100g) |
| ------ | ----------------------------- | -------------------------- | ----------------------- | ----------------- |
| 0      | < 335                         | < 1                        | < 4,5                   | < 90              |
| 1      | > 335                         | > 1                        | > 4,5                   | > 90              |
| 2      | > 670                         | > 2                        | > 9                     | > 180             |
| 3      | > 1005                        | > 3                        | > 13,5                  | > 270             |
| 4      | > 1340                        | > 4                        | > 18                    | > 360             |
| 5      | > 1675                        | > 5                        | > 22,5                  | > 450             |
| 6      | > 2010                        | > 6                        | > 27                    | > 540             |
| 7      | > 2345                        | > 7                        | > 31                    | > 630             |
| 8      | > 2680                        | > 8                        | > 36                    | > 720             |
| 9      | > 3015                        | > 9                        | > 40                    | > 810             |
| 10     | > 3350                        | > 10                       | > 45                    | > 900             |



### 6.2 - Composante Negative P

La composante P est calculée, en fonction de la teneur de l’aliment en fibres et en protéines (exprimées en g pour 100 g d’aliment). Pour chacun de ces éléments, des points, allant de 1 à 5 sont attribués en fonction de leur teneur dans l’aliment (cf. tableau 2). La composante positive P du score nutritionnel est la note correspondant à la somme des points définis pour ces deux éléments : cette note est donc comprise entre 0 et 10.  

Tableau 2 : Points attribués à chacun des nutriments de la composante dite « positive » P  

| Points | Fibres (g/100g) (fiber_100g) | Protéines (g/100g) (proteins_100g) |
| ------ | --------------- | ------------------ |
| 0      | < 0,9           | < 1,6              |
| 1      | > 0,9           | > 1,6              |
| 2      | > 1,9           | > 3,2              |
| 3      | > 2,8           | > 4,8              |
| 4      | > 3,7           | > 6,4              |
| 5      | > 4,7           | > 8,0              |

Calcul du score nutritionnel  
Le calcul final du score nutritionnel se fait en soustrayant à la note de la composante négative N la note de la composante positive P avec quelques conditionnalités décrites ci après.  



### 6.3 - Calcul du score nutritionnel

**Score nutritionnel = Total Points N – Total Points P**

La note finale du score nutritionnel attribuée à un aliment est	 donc susceptible d’être comprise entre une valeur théorique de - 10 (le plus favorable sur le plan nutritionnel) et une valeur théorique de + 40 (le plus défavorable sur le plan nutritionnel).  

Classement de l’aliment dans l’échelle nutritionnelle à cinq niveaux sur la base du score calculé  

| Classe    | Bornes du score | Couleur     |
| --------- | --------------- | ----------- |
| Trop Bon  | Min à -1        | green       |
| Bon       | 0 à 2           | light green |
| Mangeable | 3 à 10          | yellow      |
| Mouai     | 11 à 18         | orange      |
| Degueu    | 19 à Max        | red         |

