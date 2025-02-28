## **Document colaboration**

### **1. Technologies**
- **Front-end** : React.js, TypeScript, Tailwind
- **Back-end** : Node.js
- **Mobile (Android)** : Kotlin
- **Base de données** : MySQL  
- **Authentification** : Firebase ou Azure Key Vault ?
- **Paiements** : Stripe  
- **Notifications** : OneSignal  
- **App data** : Java Spring Boot (UI, data), Pandas, Matplotlib, Seaborn, FPDF
- **Versionning** : Git/GitHub

### **2. Architecture**
```
├── /app
    ├── eco-deli-data
    ├── eco-deli-mobile
    ├── eco-deli-web
        ├── back/
            ├── server.js
            ├── src/
                ├── controllers/ : Logique métier
                ├── services/ : Services applicatifs
                ├── models/ : Modèles (si MongoDB/Mongoose)
                ├── routes/ : Définition des routes
                ├── middlewares/ : Middleware (auth, logs, etc.)
                ├── config/ : Variables d’environnement et config  
                ├── utils/ : Fonctions utilitaires  
                ├── tests/ : Tests unitaires et d’intégration  
        ├── /front
            ├── index.tsx
            ├── src/ 
                ├── components/ : Composants réutilisables  
                ├── pages/ : Pages de l’application  
                ├── layouts/ : Disposition des pages  
                ├── hooks/ : Hooks React personnalisés  
                ├── utils/ : Fonctions utilitaires  
                ├── services/ : Appels API  
                ├── assets/ : Images et styles  
                ├── styles/ : Fichiers Tailwind ou CSS modules  
```

### **3. Convention**

- Utiliser l’anglais pour le code (noms de fichiers, variables, commits, etc.).  
- Utiliser des noms clairs et significatifs pour les variables et fonctions.  
- Ne jamais committer du code qui ne compile pas.  
- Faire attention aux fichiers .env (ne pas les committer).  
- Toujours valider les entrées utilisateur côté client et serveur.  
- Éviter le stockage de mots de passe en clair, utiliser bcrypt.  

| Élément | Convention | Exemple |
|---------|-----------|---------|
| Variables | kebab-case | `user-profile` |
| Classes CSS | kebab-case | `.btn-primary` |
| Fichiers & Dossiers | kebab-case | `user-profile.ts` |
| Fonctions, Props, Hooks | camelCase | `getUserData()` |
| Constantes | SCREAMING_SNAKE_CASE | `API_URL` |

**JavaScript & TypeScript**  
- Indentation : 2 espaces.  
- Guillemets : `'` (simples), sauf en JSX (`"` doubles).  
- Fonctions fléchées privilégiées : `const fetchData = () => { ... }`
- Constantes et `let` seulement (pas de `var`).  

**Node**  
- Utiliser `async/await` et toujours gérer les erreurs avec `try/catch`.  

**React**  
- Un seul composant par fichier.  
- Utiliser useState, useEffect, useContext avec parcimonie.  

**Tailwind**  
- Utiliser `@apply` pour éviter trop de classes dans le JSX.  
- Créer des composants stylisés pour éviter la répétition de code.  
- Pas de styles inline, sauf exception.  
- Respecter la lisibilité (max 3-4 classes par ligne).  

**SQL**  
- Nommage des tables : Pluriel en snake_case (`user_profiles` et non `UserProfiles`).  
- Noms courts, explicites et en minuscules.  
- Clés primaires auto-incrémentées.  
- Relations claires :  
  - Clé primaire : préfixe `id` (`id_user`).  
  - Clé étrangère : préfixe `fk` (`fk_role_id`).  
  - Dates : préfixe `date` (`date_created`).  
- Types recommandés :  
  - `INT` ou `BIGINT` pour les identifiants numériques.  
  - `VARCHAR` pour les chaînes de texte de taille variable.  
  - `DATE` ou `DATETIME` pour les dates et heures.  
  - Éviter `TEXT` ou `LONGTEXT`, sauf si nécessaire (ralentit les requêtes).  
- Limiter les droits en SQL (`GRANT SELECT, INSERT, UPDATE` au lieu de `ALL PRIVILEGES`).  
- Éviter les SELECT * en SQL, préférer des colonnes spécifiques.  

**Git**  
- Branche `main` : Stable et déployable.  
- Branche `dev` : Intégration des nouvelles features.  
- Branche `feature/nom-feature` : Une branche par fonctionnalité.  
- Commits clairs et formatés (`git commit -m "type: message"`) :  
  - `feat:` Ajout d'une feature.  
  - `fix:` Correction de bug.  
  - `chore:` Maintenance / mise à jour.  
  - `refactor:` Amélioration sans changement fonctionnel.  
- Pas de commits volumineux, découper en petites étapes.  
- Code review obligatoire avant de merger une PR.  
