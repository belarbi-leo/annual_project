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

![](inc/rsx.svg)

### **3. Systèmes**
- **Environnement de virtualisation :** Proxmox
- **Switch :** Open vSwitch (x5)
- **Routeurs :** OPNSense (x9)
- **Firewalls :** OPNSense (x8)
- **Serveur :** Debian 12 (x5) – Windows Server 2022 (x3)

### **4. Besoins**

- **Serveur de stockage**
- **Serveur back-up de stockage**
- **AD + RODC**
- **Serveur DHCP**
- **Serveur DNS**
- **Serveur de supervision (Zabbix & GLPI)**
- **Serveur web**
- **Serveur mail** 
- **Serveur back-up de mail**
- **Serveur de stockage partagé direction**

- **Machines collaborateurs pré-configurer**


close only port

ORDRE DE DEV :

conf le reseaux
def choix materiels
rediger schema rsx
rediger regles de routages
definir direction des ports d'ouvert a prendre ou non 

installe les vm  
5 switch 
9 routeurs
8 firewalls
10 serveurs 
60 laptop

configurer les regles de routage 

installe les services

tester la sécu
 
tcheck backup 

Lire et comprendre les exigences du projet

Définir le choix du matériel
Faire un schéma réseau de l’infrastructure globale et des sites
Déployer proxmox sur le serveur hébergé et le configurer 
Créer/configurer une VM pour Zabbix et GLPI (Paris) 
Créer/configurer une VM OPNSense pour chaque routeur et firewall 
Configurer un switch virtuel dans Proxmox 
Configurer les interfaces réseau (WAN, LAN, VLANs) 
Activer RIP v2 pour la communication inter-routeurs 
Configurer les connexion VPN site-to-site et client-to-site pour chaque site (ACL et IPSec over GRE) 
Implémenter HSRP ou VRRP pour la redondance des routeurs 
Créer/configurer une VM pour l'AD et la baie de stockage 
Configurer des GPOs pour gérer les accès et restrictions utilisateurs 
Créer les groupes et utilisateurs connu dans l'AD 
Créer/configurer un VM pour un serveur web, mail, ainsi qu'un reverse proxy 
Créer/configurer une VM pour le serveur back-up de mail 
Créer/configurer des VM client collaborateur pour chaque VLAN 
Créer/configurer une VM pour le DNS, le DHCP et le serveur back-up de stockage 
Mettre en place un back-up journalier à 23h du serveur de stockage de Paris
Créer/configurer une VM pour le RODC et un serveur de stockage full direction chiffré 
-- Déployer l'application web via docker en accès sécuriser HTTPS avec TLS
-- Créer une GPO pour installer l'application ToolsData automatiquement en client lourd
Test de routage, redondance et simulation de panne
Vérifier la sécurisation des données sensibles, backup
Installer une connexion de secours vers Internet en cas de panne de l’EDN


Je dois configurer l'infra on premise pour un client, je veux faire la liste du matériels qu'il aurais besoin peut tu m'aider voici les contraintes : 

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

### **Architecture **
- 5 Switch
- 9 Routeurs
- 8 Firewalls
- 8 Serveur : 
Serveur de stockage (Paris)
Serveur back-up de stockage (Lyon)
Serveur de supervision : Zabbix & GLPI (Paris)
Serveur AD (Paris)
Serveur DHCP + DNS (Lyon)
Serveur RODC + stockage partagé direction (Lille)
Serveur web + mail (Paris)
Serveur back-up de mail (Marseille)