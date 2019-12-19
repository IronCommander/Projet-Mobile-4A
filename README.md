# Projet Mobile 4A : Pizzas

    Explication :
    -------------

# But du projet
**Le projet doit contenir :**
 - Des Fragments
 - Appel WebService à une API Rest
 - Design
 - Architecture MVC
 - Git
 
**Le projet contient en plus :**
 - Splash Screen
 - Navigation View
 - Recycler View, pour les images de pizza
 - Gestion de sa propre Api
 - Gestion de son propre serveur
 - Sauvegarde des données si on coupe internet
 - Suppréssion des données si on clique sur **déconnection**
 - Écran de détail contenant :
   - RecyclerView de WebView, pour voir des vidéos sur comment réaliser ses pizzas
   - RecyclerView de TextView, pour voir les ingrédients
   - RecyclerView de TextView, pour voir la recette
 
# Composition du projet
Le projet est composé de plusieurs sous parties :
 - ***Splash Screen:*** Qui récupèrt l'api puis la stock dans la ram du téléphone, si la data est déja existante dans la ram alors elle ne s'affiche plus.
 - ***Écran Principal :***  Pouvant supprimé toute la data stocker dans la ram, en cliquant sur **déconnection**
 
![](rd/sauvegarde_data.gif)
 - ***Écran Principal :*** Une Navigation View, contenant quatres types d'ordre :
    - Alphabétique : Permettant d'afficher les pizzas par ordres alphabétiques
    - Nombre d'ingrédients : Permettant d'afficher les pizzas par nombre d'ingrédients croissant
    - Nombre de vidéos : Permettant d'afficher les pizzas par nombre de vidéos croissant
    - Nombre d'étapes : Permettant d'afficher les pizzas par nombre d'étapes croissant
    
![](rd/menu_ordre.gif) 
 - ***Écran de détail :*** Permettant de voir tout les détails de cette pizza :
    - Des vidéos de création
    - Les ingrédients nécessaires
    - La recette
    
![](rd/details.gif) 
# Serveur
L'api est stocker sur un serveur qui a une base de donnée sql :
![](rd/phpMyAdmin.png)
Via mon code php ci-dessous, il prend la base de donnée et l'emet en json.
![](rd/php.png)
Et voicie le résultat :
![](rd/json.png)
