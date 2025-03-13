# **Descriptif fonctionnel**

## **1. Concept**
EcoDeli est une plateforme web et mobile de mise en relation entre particuliers, commerçants, livreurs et prestataires de services. Son objectif premier est d’optimiser la logistique des livraisons tout en réduisant les coûts et l’empreinte carbone, en utilisant les trajets des particuliers et professionnels déjà prévus. En parallèle, EcoDeli propose aussi un réseau de prestataires pour divers services (transport, installations, entretien, etc.).


## **2. Rôles**

On a cinq role mais en fonction des regles métier on a appliquer le fais que chaque utilsateur possedera le role client 
**Les Clients**  
Ce sont les utilisateurs qui ont besoin d’un service, qu’il s’agisse de l’envoi d’un colis, d’un achat à l’étranger, d’un transport de personne ou d’un autre service à la personne. Ils peuvent être :  
- Des particuliers qui souhaitent envoyer un colis ou réserver un service.  
- Des commerçants qui veulent proposer la livraison à domicile pour leurs clients.

en suite un commercant a exactement les meme options qu'un client a l'excception qu'il necesite une inscription plus pousser car finalement le contrat qu'il passse avec eco-deli revient au meme que l'abonnement qu'un client passe 
**Les Commerçants**  
Ce sont des entreprises ou commerçants qui veulent proposer un service de livraison à leurs clients via EcoDeli.  

 
Ensuite nous avons par lla meme logique réuni les profils prestataire et livreurs car finalement il procède les deux a un service : 
**Les Livreurs** 
Ce sont des particuliers ou professionnels qui prennent en charge la livraison de colis ou le transport de personnes. Ils peuvent proposer des trajets qu’ils comptent effectuer à l’avance. 
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

**Espace des clients**  
- Gestion et creation des annonces 
- Consultation et reservation des services proposés  
- Suivi des colis et prestations en temps réel  
- Gestion de l'abonnement 
- Lecture d'une carte NFC sur mobile pour valider une livraison

**Espace des commerçants**  
- Gestion et creation des annonces 
- Consultation et reservation des services proposés  
- Suivi des colis et prestations en temps réel  
- Gestion des contrats  
- Lecture d'une carte NFC sur mobile pour valider une livraison

**Espace des livreurs** 
- Ajout et gestion des trajets futurs  
- Gestion des livraisons acceptées  
- Planning des déplacements et historique

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


USER 
DISPO
SERVICE
TYPE_SERVICE
MESSAGE
LITIGE
AVIS
MESSAGE
PAIEMENT 
FACRURE
NOTIFICATION

USER : role (0, 1, 2, 4, 8, 32 : par défaut 0 client, si inscription admin 1 (0 + 1 droit admin et client), si commercant 2 (0+2 droit client et commercant), si livreur 4 (0+4 droit client et livreur), si presta 8 (0+8 droit client et presta), si commercant et livreur 6 (0+2+4 droit client, commercant et livreur) etc. )
CONTRACT : (abonnement, prestataire, commercant)
TYPE_SERVICE : (LIVREUR, PLOMBIER, CHAUFFEUR)
DISPO (si trajet livraison et chauffeur dispo = chemin, sinon dispo = temporalité )

USERS (0,N) --> (1,1) PROPOSE_SERVICES (1,1) --> (0,1) LIST_SERVICES
USERS (0,N) --> (1,1) DEMANDE_SERVICES (1,1) --> (0,1) LIST_SERVICES
USERS (0,N) --> (1,1) REPONSE_SERVICES (1,1) --> (0,1) DEMANDE_SERVICES
USERS (0,N) --> (0,N) AVIS_SERVICES
USERS (0,N) --> (0,N) LITIGES_SERVICES
USERS (0,N) --> (0,N) PAIEMENTS
USERS (0,N) --> (0,N) FACTURES
USERS (0,N) --> (1,N) MESSAGE
USERS (0,N) --> (1,1) DISPONIBILITE 




USERS (id_user, sexe, nom, prenom, date de naissance, telephone, photo, email, mot de passe, nom entreprise, siret, siege social, cni, casier judiciaire B3, date d'inscription, role, id_nfc, bannis, date de bannissement, bloquage, date de bloquage, langue, reduction envoie colis, reduction permanante, envoie prioritaire)
ENTREPRISE (id_entreprise, )
SERVICES (id_service, date_de_creation, nom, description, prix_min, prix_max)

PROPOSE_SERVICES (id_service_proposer, id_user, id_service, habilitation, certification, prix, statut{en attente user, en attente admin, accepter, refuser, bloquer}, date de demande, date d'acceptation, retourner par, valider par)

DISPONIBILITE_SERVICES (id_disponiblite, id_user, id_service_proposer, lieu de depart, date de depart, lieu d'arriver, date d'arriver)
DEMANDE_SERVICES (id_demande, id_user, id_service, id_dispobilite, lieu de depart, date de depart, lieu d'arriver, date d'arriver, statut_demande{en attente, accepter_en_cour, acceter_terminer, aucun_livreur, reporter, refuser}, prix, date de demande) id user = id client, si livraison id disponibilité = NULL, si presta domicile lieu de depart = lieu d'arriver

COLIS ()
COLIS (id_colis, id_demande, description, poids, taille, valeur, fragile, photo, date_creation)

REPONSE_SERVICES (id_reponse, id_user, id_demande, commentaire, statut_reponse{accpeter, reporter, refuser}, date de reponse) id user = id prestataire
AVIS_SERVICES (id_avis, id_service_porposer, id_demande, note, description, date avis) si statut_demande = accepter_terminer
LITIGES_SERVICES (id_litige, id_demande, description, date de litige, statut{en attente traitement, en cour traitement, en attente de retour, resolu}, photo)
PAIEMENTS (id_paiement, id_user, id_demande, montant, moyen_paiement, statut, date_paiement)
FACTURES (id_facture, id_user, id_paiement, montant_total, date_facture, fichier_pdf)
MESSAGE (id_message, id_sender, id_receiver, date_message, contenue)


regle metier :
e veux faire mon mcd mais j'ai du mal :
J'ai des utilisateur qui ont plusieurs rôle client, admin, prestatatire et commercant (qui sont des client mais professionnel), les prestataire fournissent des service (livraison, chauffeur, jardinage etc) et les admin gere les utilisateurs. Je pensait mettre tous les profils dans une table user.
Un prestataire peut fournir plusieurs services differents, il doit s'inscrire en tant que prestataire puis demander a pratiquer tel ou tel service, il fait une demande par service qui doit etre valider par un admin, une demande peut etre retourner par l'admin avec un commentaire afin de de négocier le prix proposer par le prestataire pour sa prestation. Les livraison de colis (coeur de métier du site) n'ont pas de prix proposer par le prestataire et donc les demande de prestation en tant que livreur de colis sont plus rapidement accepter. Un prestataire saisie ensuite :
- Soit ses disponibilité pour une prestation chez pour client (chauffeur, récuperateur de course, jardinage, massage etc.) afin qu'elle s'affiche dans les prestation proposer par des prestataire proche de sa localisation dans l'interface client ou commercant et qu'il fasse une demande
- Soit ses trajet de prévus afin que l'application match avec une demande de livraison de colis saisie par l'utilisateur. Il peut aussi aller consulter les demande de livraison lister bien entendu, un prestataire peut aussi accepter ou refuser les demandes soumisent par client ou matcher par l'application dans sont compte. 