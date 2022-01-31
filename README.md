1. Introduction 
Nous voulons réaliser un mini-jeu inspiré de Flappy Bird, mais en version simplifiée, dans laquelle un ovale se déplace le long d’une ligne brisée. Le but du jeu est d’éviter que l’ovale sorte de la ligne, et ce pendant le plus longtemps possible. Pour cela, le joueur peut cliquer sur l’écran pour faire monter l’ovale, qui redescend ensuite tout seul. Voici à quoi pourrait ressembler l’interface graphique de notre jeu :
 
2. Analyse globale 
Nous avons différentes fonctionnalités à implémenter :
- Facile :
  - Créer une fenêtre de jeu 
  - Afficher un ovale représentant l'oiseau
  - Utiliser un objet d’état qui sauvegarde l’état de jeu, en sauvegardant la position de l'oiseau
  - Ajouter un contrôleur qui reçoit les commandes de l’utilisateur (clics dans la fenêtre) pour que l'oiseau se déplace
  - Construire un parcours sous forme de ligne brisée
  - Afficher le parcours, l'oiseau et le score
  - Ajouter du mouvement automatique pour l'oiseau (horizontalement et verticalement)
  - Tester la fin de partie et écran de fin de partie
- Difficile :
  - Générer un parcours infini intéressant
  - Rendre le parcours de plus en plus difficile avec l'avancement de l'oiseau
  - Gérer les conditions de fin de parties
- Optionnelles :
  - Animation de l'oiseau
  - Ajout de décors
  - Relancement de partie rapide
  - Intégrer des courbes de bézier pour transformer la ligne brisée




3. Plan de développement 
Liste des tâches :
- Première semaine
  - Analyse du problème (15 mn)
  - Conception, développement et test d’une fenêtre avec un ovale (30 mn)
  - Conception, développement et test du mécanisme de déplacement de l’ovale (45 mn)
  - Acquisition de compétences en Swing (60 mn)
  - Documentation du projet (60 mn)

- Deuxième semaine
  - Test des Threads en vue d'apprentissage (15 mn)
  - Ajout du Thread de chute pour l'oiseau (15 mn)
  - Création de la ligne brisée (60 mn)
  - Ajout du thread pour la ligne brisée (30 mn)
  - Documentation du projet (60 mn)

- Troisième semaine
  - Corrections et ajustements (60 mn)
  - Detection des collisions (60 mn)
  - Réécriture complète du rendu (120 mn)




4. Conception générale 
Nous avons adopté le motif MVC pour le développement de notre interface graphique. Les deux fonctionnalités évoquées (fenêtre et déplacement du ovale) s'intègrent dans ce modèle : la fenêtre est implémentée dans la vue, et modifiée par le modèle, et le contrôle gère le déplacement du ovale. Dans le même temps, les threads s'occupent de faire descendre l'oiseau et avancer la ligne brisée.


5. Conception détaillée 

Une classe Main s'occupe de créer l'ensemble des éléments et de lancer le jeu dans la fenêtre.

Pour la fenêtre avec un ovale, nous utilisons l’API Swing et la classe JPanel. Nous définissons les dimensions de l’oval et de la fenêtre dans des constantes :
public static final int LARG = 600;
public static final int HAUT = 400;
public static final int xCenterOval = 50;
public static final int widthOval = 50;
public static final int heightOval = 150;
public static final int ySaut = 10;



Pour l'état du jeu, on utilise des variable pour stocker des informations sur la hauteur de l'oiseau et deux méthodes différentes pour le déplacer.



Notre controleur implémente la classe MouseListener afin de récupérer les informations d'interactions de l'utilisateur.



Pour la construction du parcours, La méthode utilisée se base sur des tirages aléatoires pour le positionnement des points (afin de réduire la monotonie).
```
    int x1, y1;
    /* on calcule une nouvelle valeur de x à partir du point précédent,
     plus un écart qui dépend de la largeur de la fenêtre et de la distance parcourue (pour la croissance de la difficulté) */
    x1 = (int) (pointList.get(pointList.size() - 1).x + Affichage.LARG / 3. * Math.max(1, (1 - position / 150000.) * (1 + Main.random.nextFloat()) ));
    /* on calcule une nouvelle valeur de l'abscisse dans la partie centrale de la fenêtre, c'est à dire entre HAUT/4 et 3*HAUT/4 */
    y1 = Affichage.HAUT / 4 + Main.random.nextInt(Affichage.HAUT / 2);
    /* on ajoute le point à la liste
     * le coefficient directeur maximal en descente est de (HAUT/2)/((LARG/3)) < 1
     * Sachant qu'on avance en x au rythme de 1 pixel toutes les secondes,
     * et en y de 1 toutes les 1 secondes,
     * on a un coefficient de vitesse de 1, donc l'oiseau chute aussi vite qu'il avance,
     * il est donc impossible avec ces paramètres sur les points que l'oiseau ne puisse pas suivre la pente descendante
     * */
        pointList.add(new Point(x1, y1));
```


L'affichage se fait dans la fenêtre avec les méthodes de la classe Graphics qui récupèrent les différentes informations nécessaires dans l'état et le parcours. L'affichage des différents éléments (parcours, ovale, score) a été séparé dans plusieurs méthodes pour plus de clareté.



Pour le déplacement automatique de l'oiseau, nous utilisons 2 threads, un qui le fait chuter continuellement et un qui fait continuellement avancer la ligne brisée, tous deux au fur-et-à-mesure que le temps passe.



La fin de partie n'est pas encore implémentée, en effet lors de la défaite le jeu continue normalement. Cependant, la défaite est tout de même détectée et signalée par la couleur du rectangle affichant le score. Nous détectons la fin de la partie en comparant la position relative de l'oiseau par rapport au parcours, voici le code utilisé :

```
/* on calcule la pente */
float temp1 = (p2.y - p1.y) / (float)(p2.x - p1.x);
/* on calcule l'ordonnée à la position actuelle */
float temp2 = (float) (p1.y + temp1 * ((birdAbs) - p1.x));
/* on renvoie un test de la différence entre le centre de l'oiseau et le point du parcours, comparé avec la moitié de la hauteur de l'oiseau */
return Math.abs(Etat.getHauteur() + Affichage.heightOval/2. - temp2) < Affichage.heightOval/2.;
```


6. Résultat


Beaucoup de temps a été perdu sur la mise en œuvre des threads, ce qui a conduit a un retard sur les prévisions ; ainsi, toutes les fonctionnalités prévues à l'origine n'ont pas pu être implémentées, par exemple vis-à-vis de la fin du jeu (écran de défaite, tableau de scores, relance de partie). La plupart des fonctionnalités de bases sont cependant implémentées, on a une fenêtre avec un ovale qui descend continuellement et qui remonte lors des clics de la souris, un parcours sous la forme d'une ligne brisée qui défile, avec des points variant aléatoirement, un score, un test si l'ovale est bien sur la ligne, etc...
Visuellement, le rendu est assez cru, voire basique, les lignes sont anguleuses et il n'y a pas de détails pouvant rendre le tout plus «beau», mais il est à noter que ce genre de feature est à plus basse priorité que celles faisant le noyau du projet (les déplacements des objets), et seraient a priori relativement rapides à mettre en place (remplacer le fond uni par une image, l'ovale par un sprite plus complexe mais dessiné autre part (une image) par exemple).
7. Documentation utilisateur 
Prérequis : Java avec un IDE.
Mode d’emploi : Importez le projet dans votre IDE, sélectionnez la classe Main à la racine du projet puis « Run as Java Application ». Cliquez sur la fenêtre pour faire monter l’ovale.

8. Documentation développeur 
Les prochaines fonctionnalités à implémenter devraient être une refonte graphique, la gestion de la fin de partie, et la possibilité d'en relancer une directement (sans devoir relancer l'applet).

9. Conclusion et perspectives 
D'un point de vue personnel, ce projet m'a permis de mieux appréhender les enjeux de la programmation objet et concurrentielle, et de dérouiller ma pratique du langage Java. Les notions abordées étaient théoriquement déjà vues, mais un rappel est toujours bon.
