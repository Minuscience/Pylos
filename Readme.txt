Exécutez le fichier Jar : Pylos.jar et amusez-vous !

Les Mises à jour:
	- Ajout d'une légende montrant la couleur des joueurs.
	- Ajout d'une surbrillance lorsque la pop up "Validation du coup" apparait (fonctionne sous Linux et non sous Windows).
	- Ajout d'une surbrillance pour montrer les endroits jouables (en vert) et non jouables (gris).
	- Correction de bug effet de bords:
		- L'algorithme des effets de bords fonctionnait correctement. Il fallait qu'on ajoute une condition pour
		compter le nombre de boules restants pour le joueur qui a fait un carré et a retiré un ou deux boules.
		Le jeu annonce le gagnant après qu'un des joueurs joue sa dernière boule sur le plateau.
		La modification apportée est dans le fichier Boule.java a partir de la ligne 129.
	 	