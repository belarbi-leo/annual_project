## **Organisation**

Nous avons adopté une approche Agile pour le développement de notre projet, permettant ainsi une gestion flexible des tâches, des priorités et une amélioration continue tout au long du cycle de vie du projet. Nous avons donc divisé le travail en **4 sprints** successifs, définis en fonction des dates de rendu et des exigences du syllabus, structurés comme suit :

- **Sprint 1** (28 jours - du 23/02/2025 au 23/03/2025) : Analyse, conception initiale et développement des premières fonctionnalités. Ce sprint est axé sur les bases du projet et l'établissement des priorités techniques.
- **Sprint 2** (? jours - du 24/03/2025 au 11/05/2025) : Développement des fonctionnalités principales, en se concentrant sur les applications web et mobiles ainsi que la mise en place initiale de l'infrastructure réseau.
- **Sprint 3** (42 jours - du 12/05/2025 au 22/06/2025) : Finalisation des fonctionnalités principales et mise en production. Ce sprint comprend la finalisation de l'intégration des applications et des tests d'infrastructure.
- **Sprint 4** (21 jours - du 23/06/2025 au 13/07/2025) : Ce sprint est plus court et dédié à l'innovation, à l'amélioration continue et à la correction des bugs afin de peaufiner les fonctionnalités et d'assurer une présentation réussit pour la soutenance finale.

Avant le démarrage des sprints, nous avons effectué les étapes suivantes :

- **Analyse du sujet** : Compréhension des besoins et des exigences du projet.
- **Création du WBS** : Décomposition du projet en sous-tâches et activités essentielles.
![](inc/wbs.svg)
- **Élaboration du PERT** : Identification des dépendances et des relations entre les tâches.
![](chemin/vers/image.png)
- **Élaboration du Gantt** : Planification temporelle du projet avec des échéances précises.
![](chemin/vers/image.png)

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
Louer serveur hébergé S2 to-do EA

Livrable
Rédiger les devis (dev software, infra on-premise, gestion projet) S1 good LB
Rédiger l'organisation mise en place (outils collaboratif, orga sprint, tickets, WBS, gantt) S1 good LB
Rédiger descriptif fonctionnel des dev S3 in-progess ALL
Rédiger le document d'architecture fonctionnel S2 in-progress LB EA

API REST
Définir les technologies à utiliser S1 good ALL
Concevoir la base de données (MCD, MLD, SQL) S1 good LB MB
Implémenter la chaine d'intégration continue S1 to-do LB
Créer l'API d'inscription des utilisateurs avec validation des données S1 to-do
Implémenter l'API de connexion avec gestion des erreurs S1 to-do
Générer et valider les tokens JWT pour l'authentification S1 to-do
Mise en place de la gestion des sessions (expiration, rafraîchissement de token) S1 to-do
Implémenter le chiffrement des mots de passe et tokens avant stockage S1 to-do
Configurer Azure Key Vault pour stocker les identifiants sensibles S1 to-do
Créer système de rôles et permissions pour restreindre les accès aux fonctionnalités S1 to-do
Créer un endpoint pour récupérer les informations d'un utilisateur authentifié S2 to-do
Créer endpoint pour modifier ses info personnelles (avec restrictions par rôle) S2 to-do
Créer un endpoint pour la suppression de compte S2 to-do
Créer un endpoint pour l'exportation des données personnelles sur demande S2 to-do
Développer l'API de création, édition et suppression d'une annonce S2 to-do
Créer une API permettant aux clients de faire la demande d'une prestation S2 to-do
Mettre en place l'acceptation, le refus et la négociation des demandes S2 to-do
Créer une conversation entre clients et prestataires lors d'une demande S2 to-do
Créer API pour la recherche des prestations (catégorie, localisation, prix, etc.) S2 to-do
Créer un algo de sélection du trajet le plus adapté (rapport aux entrepôts et trajets) S2 to-do
Créer une API pour l'évaluation des prestations S2 to-do
Créer une API pour les litiges S2 to-do
Développer un service de tracking des colis en temps réel S2 to-do
Permettre aux prestataires d'accepter une annonce de livraison S2 to-do
Créer un système de validation de la livraison par le livreur S2 to-do
Créer un système de scan NFC pour l'authentification des livreurs S2 to-do
Notifier les prestataires lorsqu'un client fait une demande S2 to-do
Notifier les utilisateurs en cas d'acceptation/refus d'une demande S2 to-do
Notifier les utilisateurs en cas de message S2 to-do
Notifier les prestataire en cas de matching d'un trajet a une demande S2 to-do
Gérer les préférences de notifications des utilisateurs S2 to-do
Créer un système support via messagerie (connecté) ou form contact (non-connecté) S2 to-do
Implémenter le paiement en ligne via Stripe S2 to-do
Générer et stocker les factures pour chaque transaction S2 to-do
Permettre l'export des factures par les utilisateurs S2 to-do
Ajouter/modifier/supprimer des annonces S2 to-do
Ajouter/modifier/supprimer des services S2 to-do
Ajouter/modifier/supprimer des abonnements S2 to-do
Ajouter/modifier/supprimer des entrepôts S2 to-do
Ajouter/modifier/supprimer des avis S2 to-do
Valider les demandes d'habilitation et d'inscription S2 to-do
Gérer les comptes utilisateurs (modification, suspension, suppression) S2 to-do
Créer des comptes administrateurs S2 to-do
Gérer et traiter les litiges entre utilisateurs S2 to-do
Consulter et exporter toutes les factures du site S2 to-do
Consulter le tracking de tous les colis S2 to-do
Mettre en place un tableau de boro administrateur avec statistiques S2 to-do
Consulter et répondre aux messages utilisateurs S2 to-do
Implémenter les middlewares de protectior des routes API S2 to-do
Appliquer restrictions d'accès aux différentes routes en fonction d'habilitations S2 to-do
Gérer les erreurs globalement avec un middleware dédié S2 to-do
Sécuriser l’API (CORS, rate limiting) S2 to-do

Application Web
Définir les technologies à utiliser S1 good ALL
Implémenter la connexion avec l'API S2 to-do
Développer les components (nav-bar, button, card, form etc.) S1 to-do
Interfaces "home" non-connecté S1 to-do
Interfaces "about" non-connecté S1 to-do
Interfaces "contact" non-connecté S1 to-do
Interfaces "legal" non-connecté S1 to-do
Interfaces "signin" S1 to-do
Interfaces "login" S1 to-do
Interfaces "forgot password" S1 to-do
Interfaces tutorial à linscription (bloquant) S2 to-do
Interfaces compte bloquer en attente de validation administrateur S1 to-do
Interfaces "home" S1 to-do
Interfaces "param" (stats, modif infos et abonnement, export données et factures) S1 to-do
Interfaces "messages" S1 to-do
Form créa / modif d'une annonces (dispo presta ou demande de livraison colis) S2 to-do
Interfaces affichage des annonces (prestations et livraisons) disponibles S2 to-do
Form postuler a une prestation (+ creation d'une conv pour négociations si presta) S2 to-do
Interfaces suivi de colis en temps réel S2 to-do
Interfaces de paiements S2 to-do
Form avis et affichage des avis sur une presta S2 to-do
Form litiges et affichage du litige sur une presta S2 to-do
Interfaces admin de dashboard avec statistiques globales S2 to-do
Interfaces admin de validation des demandes d'habilitation S2 to-do
Interfaces admin de messagerie S2 to-do
Interfaces admin de gestion des utilisateurs S2 to-do
Interfaces admin de gestion des annonces S2 to-do
Interfaces admin de gestion des litiges S2 to-do
Interfaces admin de gestion d'abonnement S2 to-do
Interfaces admin de gestion des services S2 to-do
Interfaces admin de gestion des entrepôts S2 to-do
Interfaces admin de gestion des avis S2 to-do
Interfaces admin de gestion des paiements S2 to-do
Integration des notifications S2 to-do
Déployer l'application via docker sur l'infra en accès sécuriser HTTPS avec TLS S3 to-do

Application Mobile
Définir les technologies à utiliser S3 to-do 
Implémenter la connexion avec l'API S3 to-do
Développer les components (nav-bar, button, card, form etc.) S3 to-do
Interfaces "signin" S3 to-do
Interfaces "login" S3 to-do
Interfaces "forgot password" S3 to-do
Interfaces tutorial à l'inscription (bloquant) S3 to-do
Interfaces compte bloquer en attente de validation administrateur S3 to-do
Interfaces "home" S3 to-do
Interfaces "param" (stats, modif infos et abonnement, export données et factures) S3 to-do
Interfaces "messages" S3 to-do
Form créa / modif d'une annonces (dispo presta ou demande de livraison colis) S3 to-do
Interfaces affichage des annonces (prestations et livraisons) disponibles S3 to-do
Form postuler à une prestation (+ creation d'un conv pour négociations si presta) S3 to-do
Interfaces suivi de colis en temps réel S3 to-do
Interfaces de paiements S3 to-do
Form avis et affichage des avis sur une presta S3 to-do
Form litiges et affichage du litige sur une presta S3 to-do
Interfaces scan NFC pour validation de la réception du colis S3 to-do
Préparer l'application pour déploiement PlayStore S3 to-do

Application Data
Définir les technologies à utiliser S3 to-do
Implémenter la connexion avec l'API S3 to-do
Implémenter le traitement des données pour le calcul des statistiques S3 to-do
Implémenter un tableau de bord S3 to-do
Implémenter la génération des graphiques S3 to-do
Implémenter la génération du rapport PDF S3 to-do
Ajouter une option d'export CSV/Excel S3 to-do
Packager l'application S3 to-do

Infrastructure
Définir le matériel physique et logiciel S1 in-progress LB
Définir la segmentation et l'adressage réseau S1 good LB
Définir les ACLs S1 in-progress LB
Déployer et configurer promox, Terraform, Ansible S2 to-do
Créer 5 switch, 9 routeurs, 8 firewalls, 8 serveurs, 15 laptop S2 to-do
Configurer les switch (ACLs) S2 to-do
Configurer les routeurs (ACLs, RIP v2, VRRP) S2 to-do
Configurer les firewall (ACLs, RIP v2, VRRP, IPSec over GRE) S2 to-do
Configurer le serveur suppervision (Zabbix et GLPI) S2 to-do
Configurer le serveur AD (DC, DNS, GPOs, groups and users) S2 to-do
Configurer le serveur DHCP et DNS S2 to-do
Configurer le serveur RODC et stockage partagé direction S2 to-do
Configurer le serveur stockage principal S2 to-do
Configurer le serveur stockage back-up (journalier à 23h) S2 to-do
Configurer le serveur web, proxy, mail S2 to-do
Configurer le serveur mail back-up S2 to-do
Test de routage, redondance et simulation de panne S3 to-do
Vérifier la sécurisation des données sensibles S3 to-do
Installer une connexion de secours vers Internet en cas de panne du FAI S3 to-do
Créer une GPO pour installer l'application data automatiquement en client lourd S4 to-do