## **Document d'Architecture Technique**

### **1. Contexte**
Face à la croissance exponentielle d'EcoDeli, l’entreprise a décidé d’être à la fois son propre fournisseur d’accès à Internet (FAI) et de gérer l’intégralité de ses connexions intersites, ainsi que son propre datacenter. L’infrastructure doit donc répondre aux besoins suivants :
- Anticiper le devellopement etc. 
- Assurer une connectivité sécurisée entre les différents sites de l’entreprise.
- Garantir la disponibilité des services critiques (web, mail, stockage).
- Mettre en place une redondance efficace pour éviter les interruptions de service.
- Sécuriser les flux inter-sites et externes grâce à des règles de firewalling strictes.
- Déployer une solution de monitoring pour surveiller la performance et la disponibilité des équipements.
- Sécuriser l’accès aux données sensibles en appliquant des politiques d’accès et de chiffrement.
- Permettre un accès distant sécurisé aux employés itinérants.

Actuellement, la structure du groupe repose sur six sites distincts :  
- **Paris** : siège et cœur de l’activité  
- **Marseille, Lille et Lyon** : agences principales  
- **Montpellier et Rennes** : en cours de déploiement  

Les équipes basées au siège se composent de :  
- **Équipe de direction** (5 personnes) : PDG, DRH, DSI, Directeur Commercial, Directrice Marketing  
- **Commerciaux itinérants** (5 personnes) : peuvent naviguer entre toutes les agences  
- **Équipe marketing & communication** (3 personnes) : dirigée par Lucas Hauchard  
- **Équipe informatique** (8 personnes)  
- **Ressources humaines** (4 personnes) : couvrent l’ensemble de la région parisienne  
- **Autres employés** (30 personnes) : répartis entre les agences régionales et les entreprises clientes  

Et sur chaque site en province :  
- **2 salariés** sont rattachés aux équipes de la direction  
- **4 techniciens IT** (sauf à Rennes et Montpellier) assurent le bon fonctionnement des infrastructures  

Chaque employé, quel que soit son lieu de travail, dispose d’un ordinateur professionnel fourni par l’entreprise.  

### **2. Schéma d’architecture**
Pour une entreprise en croissance, il est préférable d’adopter un schéma d’adressage IP évolutif et bien structuré, basée sur 10.0.0.0/8, qui offre une grande flexibilité (65534 adresses IP disponibles) ce qui permet d'ajouter de nouveaux sites et/ou VLANs sans contrainte majeure. 

Le réseau EDN basé sur l’adresse 10.0.0.0/16 connecte donc tous les sites régionaux via des routeurs dédiés, l'interconnexion s'établiera via RIP v2, IPSec over GRE, des ACLs ainsi que la mise en place de HSRP et chaque site disposera d'une segmentation VLAN adaptée à ses besoin : 

![Texte alternatif](img/rsx.svg)

### **3. Technologies**
- **Environnement de virtualisation :** Proxmox
- **Switch :** ? (x5)
- **Routeurs :** OPNSense (x9)
- **Firewalls :** OPNSense (x8)
- **Serveur de stockage :** Debian 12 (x2) – Stockage centralisé + back-up
- **Serveur AD, DHCP, DNS, RODC :** Windows Server 2022 (x3) – Gestion des utilisateurs
- **Serveur de supervision :** Debian 12 (x1) – Héberge Zabbix et GLPI
- **Serveur Web & Proxy :** Debian 12 (x1) – Serveur Apache/Nginx + Reverse Proxy
- **Serveur Mail :** Debian 12 (x2) – Postfix & Serveur mail sécurisé + back-up
- **Serveur de stockage :** Debian 12 (x1) – Stockage partagé direction 
- **Machines collaborateurs :** Windows Pro 11 (x60)


close only port

ORDRE DE DEV :

conf le reseaux
installe les vm  
configurer les regles de routage 
installe les service et backup 
tester la sécu 

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
