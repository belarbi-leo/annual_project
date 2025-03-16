# **Organisation**

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



### **4. Décomposition du projet**  
| **Index** | **Catégorie** | **Tâche** | **Deadline** | **Responsable** |
|-----------|---------------|-----------|--------------|-----------------|
| 1 | Organisation | Lister toutes les étapes du projet, en produire un WBS et un Gantt | 23/03/2025 | LB |  
| 2 | Organisation | Concevoir les sprint en fonction des étapes du syllabus | 23/03/2025 | LB |
| 3 | Organisation | Initialiser le Trello | 23/03/2025 | LB |
| 4 | Organisation | Louer un serveur hébergé | 11/05/2025 |  |
| 5 | Documentation | Rédiger la réponse à l'appel d'offre (chiffrage financier : coût du développement et devis pour l’infrastructure matérielle) | 23/03/2025 | LB |
| 6 | Documentation | Rédiger l'organisation mise en place du projet | 23/03/2025 | LB |
| 7 | Documentation | Rédiger le descriptif visuel de l'application (charte graphique, design système, wireframes et maquettes) | 23/03/2025 | LB |
| 8 | Documentation | Rédiger le descriptif fonctionnel de l’application | 23/03/2025 | LB + BM |
| 9 | Documentation | Rédiger le document d’architecture technique (explication de toute l’infrastructure a mettre en place pour le projet) | 22/06/2025  | LB + EA |

***| 10 | AppWeb:Init | Analyser le cahier des charges et identifier les besoins fonctionnels | 23/03/2025 | LB |
| 11 | AppWeb:Init | Définir les technologies et frameworks à utiliser | 23/03/2025 | LB + EA + BM |
***| 12 | AppWeb:Init | Définir l'architectures de l'application, les routes de l’API REST | 23/03/2025 | LB + BM | *
| 13 | AppWeb:DB | Concevoir la base de données (+ shcéma MCD, MLD, script SQL/NoSQL) | 23/03/2025 | BM | *
| 14 | AppWeb:DB | Créer et peupler la base de données | 23/03/2025 | BM | *
| 15 | AppWeb:DB | Initialiser un projet node et connecter la base | 23/03/2025 | BM | *
| 16 | AppWeb:NC | Implémenter la page d'accueil (client non-connecté) | 23/03/2025 | LB | *
| 17 | AppWeb:NC | Implémenter la page "A propos" (client non-connecté) | 23/03/2025 | EA | *
| 18 | AppWeb:NC | Implémenter la page "Mentions légale" (client non-connecté) | 23/03/2025 | EA | *
| 19 | AppWeb:NC | Implémenter la page de connexion (client non-connecté) | 23/03/2025 | BM | *
| 20 | AppWeb:NC | Implémenter la page d'inscription (client non-connecté) | 23/03/2025 | BM | *
***| 21 | AppWeb:Auth | Implémenter la création de compte | 23/03/2025 | BM | *
| 22 | AppWeb:Auth | Implémenter la vérification des comptes (pièces justificatives) | 11/05/2025 |  |
| 23 | AppWeb:Auth | Concevoir l’authentification sécurisée puis l'implémenter | 23/03/2025 | EA | *
| 24 | AppWeb:Auth | Sécuriser : Vérification des rôles et permissions | 11/05/2025 |  | 
| 25 | AppWeb:Auth | Sécuriser l’API : CORS, rate limiting | 11/05/2025 |  |
| 26 | AppWeb:Auth | Mettre en place un système de logs centralisé | 11/05/2025 |  |
| 27 | AppWeb:Global | Implémenter l’espace paramètre (modification du profil, photo, documents, stats) | 11/05/2025 |  |
| 28 | AppWeb:Global | Implémenter la gestion des litiges | 11/05/2025 |  |
| 29 | AppWeb:Global | Implémenter le système de messagerie interne | 11/05/2025 |  |
| 30 | AppWeb:Global | Implémenter les notifications push | 11/05/2025 |  |
| 31 | AppWeb:Global | Implémenter un tutoriel interactif pour les nouveaux utilisateurs | 11/05/2025 |  |
| 32 | AppWeb:Global | Implémenter l’interface utilisateur multilingue | 23/03/2025 | LB | *

| 46 | AppWeb:Customer | Implémenter la page d'accueil client | 23/03/2025 | BM | *
| 47 | AppWeb:Customer | Implémenter l’accès aux statistiques du profil client | 11/05/2025 |  | 
***| 48 | AppWeb:Customer | Implémenter la gestion et la création des annonces | 23/03/2025 | LB | *
******| 48 | AppWeb:Customer | Implémenter la gestion et la création des annonces client et commerçant | 23/03/2025 | LB | *
| 49 | AppWeb:Customer | Implémenter la consultation et réservation des services | 11/05/2025 |  |
| 50 | AppWeb:Customer | Implémenter le suivi des colis et prestations en temps réel | 11/05/2025 |  |
| 51 | AppWeb:Customer | Implémenter l’évaluation du service (avis et notation) | 11/05/2025 |  |
| 52 | AppWeb:Customer | Implémenter la gestion de l’abonnement | 11/05/2025 |  |

| 53 | AppWeb:Retailer | Implémenter la page d'accueil commerçant | 23/03/2025 | BM | *
| 54 | AppWeb:Retailer | Implémenter l’accès aux statistiques du profil commerçant | 11/05/2025 |  |
| 55 | AppWeb:Retailer | Implémenter la validation des compétences | 11/05/2025 |  |
| 56 | AppWeb:Retailer | Implémenter la négociation des tarifs | 11/05/2025 |  | 
| 57 | AppWeb:Retailer | Implémenter la gestion des interventions et disponibilités via un calendrier | 11/05/2025 |  |
| 58 | AppWeb:Retailer | Implémenter le suivi des évaluations clients (avis et notation) | 11/05/2025 |  |

| 33 | AppWeb:Delivery | Implémenter la page d'accueil livreur | 23/03/2025 | BM | *
| 34 | AppWeb:Delivery | Implémenter l’accès aux statistiques du profil livreur | 11/05/2025 |  |
| 35 | AppWeb:Delivery | Implémenter l’ajout et la gestion des trajets futurs | 23/03/2025 | EA | *
| 36 | AppWeb:Delivery | Implémenter la gestion des livraisons acceptées | 23/03/2025 | EA | *
| 37 | AppWeb:Delivery | Implémenter le planning des déplacements et historique | 11/05/2025 |  |
| 38 | AppWeb:Delivery | Implémenter le suivi des évaluations clients (avis et notation) | 11/05/2025 |  |

| 39 | AppWeb:Provider | Implémenter la page d'accueil prestataire | 23/03/2025 | BM | *
| 40 | AppWeb:Provider | Implémenter l’accès aux statistiques du profil prestataire | 11/05/2025 |  |
***| 41 | AppWeb:Provider | Implémenter la gestion et la création des annonces | 23/03/2025 | LB | *
| 42 | AppWeb:Provider | Implémenter la gestion des commandes | 11/05/2025 |  |
| 43 | AppWeb:Provider | Implémenter le suivi des colis en temps réel | 11/05/2025 |  |
| 44 | AppWeb:Provider | Implémenter la gestion des contrats | 11/05/2025 |  |
| 45 | AppWeb:Provider | Implémenter le suivi des évaluations clients (avis et notation) | 11/05/2025 |  |




| 59 | AppWeb:Admin | Implémenter la page d'accueil administrateur | 23/03/2025 | BM | *
| 60 | AppWeb:Admin | Implémenter la gestion des utilisateurs | 23/03/2025 | EA |
| 61 | AppWeb:Admin | Implémenter la gestion des annonces et services | 11/05/2025 |  | 
| 62 | AppWeb:Admin | Implémenter la gestion des livraisons et paiements | 11/05/2025 |  |

| 63 | AppWeb:Admin | Implémenter la gestion financière et suivi des transactions | 11/05/2025 |  |
| 64 | AppWeb:Admin | Implémenter la gestion des avis | 11/05/2025 |  |
| 65 | AppWeb:Service | Implémenter la mise en relation automatique ou manuelle | 11/05/2025 |  |
| 66 | AppWeb:Service | Implémenter la recherche et le filtrage des annonces | 11/05/2025 |  |  
***| 67 | AppWeb:Service | Implémenter la gestion des services (acceptation, suivi, confirmation de livraison) | 11/05/2025 |  |
| 68 | AppWeb:Service | Implémenter l’historique des annonces et services | 11/05/2025 |  |
| 69 | AppWeb:Service | Implémenter le système de tracking des colis/prestations en temps réel | 11/05/2025 |  |
| 70 | AppWeb:Service | Implémenter la gestion des box et stockage temporaire | 11/05/2025 |  |
| 71 | AppWeb:Service | Implémenter la gestion des paiements sécurisés | 11/05/2025 |  |
| 72 | AppWeb:Service | Implémenter la génération automatique et l’archivage des factures PDF | 11/05/2025 |  |


| 73 | AppMobile | Définir les technologies et frameworks à utiliser | 22/06/2025  |  |
| 74 | AppMobile | Concevoir l’architecture logicelle de l'application mobile (MVVM) | 22/06/2025  |  |
| 75 | AppMobile | Implémenter la connexion avec l’API | 22/06/2025  |  |
| 76 | AppMobile | Implémenter l’authentification sécurisée | 22/06/2025  |  |
| 77 | AppMobile | Implémenter la page d'accueil | 22/06/2025  |  |
| 78 | AppMobile | Implémenter l’accès aux statistiques du profil | 22/06/2025  |  |
| 79 | AppMobile | Implémenter la gestion et la création des annonces | 22/06/2025  |  |
| 80 | AppMobile | Implémenter la consultation et réservation des services | 22/06/2025  |  | 
| 81 | AppMobile | Implémenter le suivi des colis et prestations en temps réel | 22/06/2025  |  |
| 82 | AppMobile | Implémenter l’évaluation du service (avis et notation) | 22/06/2025  |  |
| 83 | AppMobile | Implémenter la gestion de l’abonnement | 22/06/2025  |  |
| 84 | AppMobile | Implémenter l’espace paramètre (modification du profil, photo, documents, stats) | 22/06/2025  |  |
| 85 | AppMobile | Implémenter la gestion des litiges | 22/06/2025  |  |
| 86 | AppMobile | Implémenter le système de messagerie interne | 22/06/2025  |  |
| 87 | AppMobile | Implémenter les notifications push | 22/06/2025  |  |
| 88 | AppMobile | Implémenter un tutoriel interactif pour les nouveaux utilisateurs | 22/06/2025  |  |
| 89 | AppMobile | Implémenter l’interface utilisateur multilingue | 22/06/2025  |  |
| 90 | AppMobile | Implémenter l'option de validation NFC (suivi des interractions en base, notifications) | 22/06/2025  |  |
***| 91 | AppMobile | Vérifier que le téléphone supporte le mode NFC Reader (Android 5.0+) | 22/06/2025  |  |
***| 92 | AppMobile | Demander les permissions NFC nécessaires dans AndroidManifest.xml | 22/06/2025  |  | 
***| 93 | AppMobile | Implémenter la lecture, vérification et validation | 22/06/2025  |  |
***| 94 | AppMobile | Stocker les interactions NFC pour suivi et logs en base | 22/06/2025  |  |
***| 95 | AppMobile | Envoyer une notification de validation | 22/06/2025  |  |
| 96 | AppMobile | Préparer l’application pour déploiement sur le Play Store (Générer un APK / AAB) | 22/06/2025  |  |
| 97 | AppData | Définir les technologies à utiliser | 22/06/2025  |  |
| 98 | AppData | Concevoir l'architecture logicielle de l'application (MVC) | 22/06/2025  |  |
| 99 | AppData | Implémenter la connexion aux données à l'API (authentification AD) | 22/06/2025  |  |
| 100 | AppData | Implémenter le traitement des données pour le calcul des statistiques | 22/06/2025  |  |
| 101 | AppData | Implémenter un tableau de bord | 22/06/2025  |  |
| 102 | AppData | Implémenter la génération des graphiques | 22/06/2025  |  |
| 103 | AppData | Implémenter la génération du rapport PDF | 22/06/2025  |  |
| 104 | AppData | Ajouter une option d’export CSV/Excel | 22/06/2025  |  |
| 105 | AppData | Packager l’application | 22/06/2025  |  |
| 106 | AppData | Déployer l’application sur les machines des employés | 22/06/2025  |  |
| 107 | AppData | Ajouter un système de logs | 22/06/2025  |  |
***| 108 | AppData | Rédiger la documentation utilisateur | 22/06/2025  |  |


| 109 | Infra | Lire et comprendre les exigences du projet | 23/03/2025 | LB + EA | 
| 110 | Infra | Définir le choix du matériel | 23/03/2025 | LB | *
| 111 | Infra | Faire un schéma réseau de l’infrastructure globale et des sites | 23/03/2025 | LB + AE |
| 112 | Infra | Déployer proxmox sur le serveur hébergé et le configurer | 11/05/2025 |  |
| 113 | Infra | Créer/configurer une VM pour Zabbix et GLPI (Paris) | 11/05/2025 |  |
| 114 | Infra | Créer/configurer une VM OPNSense pour chaque routeur et firewall | 11/05/2025 |  |
| 115 | Infra | Configurer un switch virtuel dans Proxmox | 11/05/2025 |  |
| 116 | Infra | Configurer les interfaces réseau (WAN, LAN, VLANs) | 11/05/2025 |  |
| 117 | Infra | Activer RIP v2 pour la communication inter-routeurs | 11/05/2025 |  |
| 118 | Infra | Configurer les connexion VPN site-to-site et client-to-site pour chaque site (ACL et IPSec over GRE) | 11/05/2025 |  |
| 119 | Infra | Implémenter HSRP ou VRRP pour la redondance des routeurs | 11/05/2025 |  |
| 120 | Infra | Créer/configurer une VM pour l'AD et la baie de stockage | 11/05/2025 |  |
| 121 | Infra | Configurer des GPOs pour gérer les accès et restrictions utilisateurs | 11/05/2025 |  |
| 122 | Infra | Créer les groupes et utilisateurs connu dans l'AD | 11/05/2025 |  |
| 123 | Infra | Créer/configurer un VM pour un serveur web, mail, ainsi qu'un reverse proxy | 11/05/2025 |  |
| 124 | Infra | Créer/configurer une VM pour le serveur back-up de mail | 11/05/2025 |  |
| 125 | Infra | Créer/configurer des VM client collaborateur pour chaque VLAN | 11/05/2025 |  |
| 126 | Infra | Créer/configurer une VM pour le DNS, le DHCP et le serveur back-up de stockage | 11/05/2025 |  |
| 127 | Infra | Mettre en place un back-up journalier à 23h du serveur de stockage de Paris | 22/06/2025  |  |  
| 128 | Infra | Créer/configurer une VM pour le RODC et un serveur de stockage full direction chiffré | 11/05/2025 |  |
| 129 | Infra | Déployer l'application web via docker en accès sécuriser HTTPS avec TLS | 22/06/2025  |  |
| 130 | Infra | Test de routage, redondance et simulation de panne | 22/06/2025  |  |
| 131 | Infra | Créer une GPO pour installer l'application ToolsData automatiquement en client lourd | 22/06/2025  |  |
| 132 | Infra | Vérifier la sécurisation des données sensibles | 22/06/2025  |  |
| 133 | Infra | Installer une connexion de secours vers Internet en cas de panne de l’EDN | 22/06/2025  |  |



conf le reseaux
installe les vm  
configurer les regles de routage 
installe les service et backup 
tester la sécu 