# **Descriptif fonctionnel**

## **1. Concept**
EcoDeli est une plateforme web et mobile de mise en relation entre particuliers, commerçants, livreurs et prestataires de services. Son objectif premier est d’optimiser la logistique des livraisons tout en réduisant les coûts et l’empreinte carbone, en utilisant les trajets des particuliers et professionnels déjà prévus. En parallèle, EcoDeli propose aussi un réseau de prestataires pour divers services (transport, installations, entretien, etc.).


## **2. Rôles**

**Les Clients**  
Ce sont les utilisateurs qui ont besoin d’un service, qu’il s’agisse de l’envoi d’un colis, d’un achat à l’étranger, d’un transport de personne ou d’un autre service à la personne. Ils peuvent être :  
- Des particuliers qui souhaitent envoyer un colis ou réserver un service.  
- Des commerçants qui veulent proposer la livraison à domicile pour leurs clients.

**Les Livreurs** 
Ce sont des particuliers ou professionnels qui prennent en charge la livraison de colis ou le transport de personnes. Ils peuvent proposer des trajets qu’ils comptent effectuer à l’avance.  

**Les Commerçants**  
Ce sont des entreprises ou commerçants qui veulent proposer un service de livraison à leurs clients via EcoDeli.  

**Les Prestataires**  
Ce sont des professionnels indépendants qui proposent des services à la personne via EcoDeli (gardiennage d’animaux, aide ménagère, courses à domicile, etc.). Un prestataire peut être livreur et inversement.

**Les Administrateurs**  
Ils gèrent l’ensemble du système "client" EcoDeli et consultent les données de l'application sur un logiciel interne pour collaborateurs.


## **3. Besoins Application Clients**
**Authentification et gestion des utilisateurs**  
- Création de compte
- Vérification des comptes (pièces justificatives pour livreurs et prestataires)  
- Authentification sécurisée (Firebase Auth ou Azure Key Vault)  
- Gestion des rôles et permissions 
- Espace paramètre (modification du profil, ajout d’une photo, ajout de documents, gestion des statistiques du profil, modification du mot de passe)
- Gestion des litiges
- Gestion des avis et notation du service
- Logs centralisés avec Datalog ou autre service Cloud  
- Interface utilisateur multilingue (sans Google ou modification du code)

**Espace des livreurs** 
- Ajout et gestion des trajets futurs  
- Gestion des livraisons acceptées  
- Planning des déplacements et historique

**Espace des commerçants**  
- Gestion et creation des annonces 
- Gestion des commandes 
- Suivi des colis en temps réel  
- Gestion des contrats  

**Espace des clients**  
- Gestion et creation des annonces 
- Consultation et reservation des services proposés  
- Suivi des colis et prestations en temps réel  
- Gestion de l'abonnement 
- Lecture d'une carte NFC sur mobile pour valider une livraison

**Espace des prestataires**  
- Valider ses compétences
- Negocier ses tarifs
- Gestion des interventions et disponibilités via un calendrier    

**Backoffice d'administration**  
- Gestion des utilisateurs (création, modification, suppression, validation)  
- Gestion des annonces et des services  
- Gestion des livraisons et des paiements  
- Gestion des prestataires et livreurs (contrats, documents, habilitations)  
- Gestion financière et suivi des transactions  
- Gestion des litiges 
- Gestion des avis 
- Génération et archivage des factures en PDF  

**Gestion et suivi des services**
- Mise en relation automatique ou manuelle entre clients et livreurs/prestataires  
- Recherche et filtrage des annonces (distance, type de service, délai, prix, etc.)  
- Gestion des services (acceptation, suivi, confirmation de livraison)  
- Historique des annonces et des services effectuées  
- Système de tracking des colis/prestations en temps réel avec (estimation de temps)
- Stockage temporaire des colis dans les entrepôts (gestion des box)  
- Gestion des paiements sécurisés avec Stripe (rémunérations, status, historique)
- Génération automatique des factures en PDF et archivage

**Notifications et communication**  
- Système de messagerie interne (clients-livreurs, clients-prestataires, support etc.)  
- Notifications de notification push pour les paiements, livraisons, services etc. (OneSignal)  
- Tutoriel interactif pour les nouveaux utilisateurs pour tout rôles (overlays bloquants)  

## **4. Besoins Application Collaborateurs**


## **5. Base de données**
La méthode employée pour la conception de la base de données repose sur une approche relationnelle et modulaire, garantissant scalabilité, évolutivité et maintenabilité. L'objectif principal est de permettre l'ajout de nouveaux services et fonctionnalités sans perturber l'architecture existante.