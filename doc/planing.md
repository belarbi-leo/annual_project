## **Organisation**

Nous avons adopté une **approche Agile** pour le développement de notre projet, permettant ainsi une gestion flexible des tâches, des priorités et une amélioration continue tout au long du cycle de vie du projet. Nous avons donc divisé le travail en **4 sprints** successifs, définis en fonction des dates de rendu et des exigences du syllabus, structurés comme suit :

- **Sprint 1** (28 jours - du 23/02/2025 au 23/03/2025) : Analyse, conception initiale et développement des premières fonctionnalités. Ce sprint est axé sur les bases du projet et l'établissement des priorités techniques.
- **Sprint 2** (? jours - du 24/03/2025 au 11/05/2025) : Développement des fonctionnalités principales, en se concentrant sur les applications web et mobiles ainsi que la mise en place initiale de l'infrastructure réseau.
- **Sprint 3** (42 jours - du 12/05/2025 au 22/06/2025) : Finalisation des fonctionnalités principales et mise en production. Ce sprint comprend la finalisation de l'intégration des applications et des tests d'infrastructure.
- **Sprint 4** (21 jours - du 23/06/2025 au 13/07/2025) : Ce sprint est plus court et dédié à l'innovation, à l'amélioration continue et à la correction des bugs afin de peaufiner les fonctionnalités et d'assurer une présentation réussit pour la soutenance finale.

Avant le démarrage des sprints, nous avons effectué les étapes suivantes :

- **Analyse du sujet** : Compréhension des besoins et des exigences du projet.
- **Création du WBS** : Décomposition du projet en sous-tâches et activités essentielles.
![Texte alternatif](chemin/vers/image.png)
- **Élaboration du PERT** : Identification des dépendances et des relations entre les tâches.
![Texte alternatif](chemin/vers/image.png)
- **Élaboration du Gantt** : Planification temporelle du projet avec des échéances précises.
![Texte alternatif](chemin/vers/image.png)

Pour assurer la collaboration et la gestion optimale du projet, nous avons choisi les outils suivants :

- **Azure DevOps Boards** : Gestion des tâches et suivi de l'avancement de manière centralisée.
- **GitHub** : Gestion du versioning et des contributions sur le code.
- **Discord** : Communication et collaboration en temps réel entre les membres de l'équipe.
- **Figma** : Outil de conception pour le développement des interfaces front-end.
- **Draw.io** : Outil utilisé pour la création de maquettes et diagrammes de processus.

S? done? resp? 

Organistation
Créer un dépôt GitHub S1 good LB
Créer un serveur de messagerie Discord S1 good LB
Créer un compte Microsoft Azure S1 good LB
Créer un espace de travail Figma S1 good EA
Configurer les sprints et tickets dans Azure DevOps Boards S1 good LB

Livrable
Rédiger les devis (dev software, infra on-premise, gestion projet) S1 good LB
Rédiger l'organisation mise en place (outils collaboratif, orga sprint, tickets, WBS, gantt) S1 good LB
Rédiger descriptif fonctionnel des dev S3 in-progess ALL
Rédiger le document d'architecture fonctionnel S2 in-progress LB EA

API REST
Définir les technologies à utiliser S1 good ALL
Concevoir la base de données (MCD, MLD, SQL) S1 good LB MB
Créer l'API d'inscription des utilisateurs avec validation des données 
Implémenter l'API de connexion avec gestion des erreurs
Générer et valider les tokens JWT pour l'authentification
Mise en place de la gestion des sessions (expiration, rafraîchissement de token)
Implémenter le chiffrement des mots de passe et tokens avant stockage
Configurer Azure Key Vault pour stocker les identifiants sensibles
Créer système de rôles et permissions pour restreindre les accès aux fonctionnalités
Créer un endpoint pour récupérer les informations d'un utilisateur authentifié
Créer endpoint pour modifier ses info personnelles (avec restrictions par rôle)
Créer un endpoint pour la suppression de compte
Créer un endpoint pour l'exportation des données personnelles sur demande
Développer l'API de création, édition et suppression d'une annonce
Créer une API permettant aux clients de faire la demande d'une prestation
Mettre en place l'acceptation, le refus et la négociation des demandes
Créer une conversation entre clients et prestataires lors d'une demande
Créer API pour la recherche des prestations (catégorie, localisation, prix, etc.)
Créer un algo de sélection du trajet le plus adapté (rapport aux entrepôts et trajets)
Créer une API pour l'évaluation des prestations
Créer une API pour les litiges
Développer un service de tracking des colis en temps réel
Permettre aux prestataires d'accepter une annonce de livraison
Créer un système de validation de la livraison par le livreur
Créer un système de scan NFC pour l'authentification des livreurs
Notifier les prestataires lorsqu'un client fait une demande
Notifier les utilisateurs en cas d'acceptation/refus d'une demande
Notifier les utilisateurs en cas de message
Notifier les prestataire en cas de matching d'un trajet a une demande
Gérer les préférences de notifications des utilisateurs
Créer un système support via messagerie (connecté) ou form contact (non-connecté) |
Implémenter le paiement en ligne via Stripe
Générer et stocker les factures pour chaque transaction
Permettre l'export des factures par les utilisateurs
Ajouter/modifier/supprimer des annonces
Ajouter/modifier/supprimer des services
Ajouter/modifier/supprimer des abonnements
Ajouter/modifier/supprimer des entrepôts
Ajouter/modifier/supprimer des avis
Valider les demandes d'habilitation et d'inscription
Gérer les comptes utilisateurs (modification, suspension, suppression)
Créer des comptes administrateurs
Gérer et traiter les litiges entre utilisateurs
Consulter et exporter toutes les factures du site
Consulter le tracking de tous les colis
Mettre en place un tableau de boro administrateur avec statistiques
Consulter et répondre aux messages utilisateurs
Implémenter les middlewares de protectior des routes API
Appliquer restrictions d'accès aux différentes routes en fonction d'habilitations
Gérer les erreurs globalement avec un middleware dédié
Implémenter la chaine d'intégration continue

Application Web
Définir les technologies à utiliser S1 good ALL
Implémenter la connexion avec l'API
Développer les components (nav-bar, button, card, form etc.)
Interfaces "home" non-connecté
Interfaces "about" non-connecté
Interfaces "contact" non-connecté
Interfaces "legal" non-connecté
Interfaces "signin"
Interfaces "login"
Interfaces "forgot password"
Interfaces tutorial à linscription (bloquant)
Interfaces compte bloquer en attente de validation administrateur
Interfaces "home"
Interfaces "param" (stats, modif infos et abonnement, export données et factures)
Interfaces "messages"
Form créa / modif d'une annonces (dispo presta ou demande de livraison colis)
Interfaces affichage des annonces (prestations et livraisons) disponibles
Form postuler a une prestation (+ creation d'une conv pour négociations si presta)
Interfaces suivi de colis en temps réel
Interfaces de paiements
Form avis et affichage des avis sur une presta
Form litiges et affichage du litige sur une presta
Interfaces admin de dashboard avec statistiques globales
Interfaces admin de validation des demandes d'habilitation
Interfaces admin de messagerie
Interfaces admin de gestion des utilisateurs
Interfaces admin de gestion des annonces
Interfaces admin de gestion des litiges
Interfaces admin de gestion d'abonnement
Interfaces admin de gestion des services
Interfaces admin de gestion des entrepôts
Interfaces admin de gestion des avis
Interfaces admin de gestion des paiements
Integration des notifications
Préparer l'application pour déploiement sur Infra

Application Mobile
Définir les technologies à utiliser 
Implémenter la connexion avec l'API
Développer les components (nav-bar, button, card, form etc.)
Interfaces "signin"
Interfaces "login"
Interfaces "forgot password"
Interfaces tutorial à l'inscription (bloquant)
Interfaces compte bloquer en attente de validation administrateur
Interfaces "home"
Interfaces "param" (stats, modif infos et abonnement, export données et factures)
Interfaces "messages"
Form créa / modif d'une annonces (dispo presta ou demande de livraison colis)
Interfaces affichage des annonces (prestations et livraisons) disponibles
Form postuler à une prestation (+ creation d'un conv pour négociations si presta)
Interfaces suivi de colis en temps réel
Interfaces de paiements
Form avis et affichage des avis sur une presta
Form litiges et affichage du litige sur une presta
Interfaces scan NFC pour validation de la réception du colis
Préparer l'application pour déploiement PlayStore

Application Data
Définir les technologies à utiliser
Implémenter la connexion avec l'API
Implémenter le traitement des données pour le calcul des statistiques
Implémenter un tableau de bord
Implémenter la génération des graphiques
Implémenter la génération du rapport PDF
Ajouter une option d'export CSV/Excel
Packager l'application
Déployer l'application sur les machines des employés (via GPO?)

Infrastructure
Louer le serveur