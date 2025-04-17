// Type pour les utilisateurs récupérées via l'API /users/read
export interface User {
id_user: number;
first_name: string;
last_name: string;
email: string;
role: string;
account_status: string;
date_registration: string; // ISO string ou Date
date_status: string;       // Idem
company_name: string;
bio: string;
siret: string;
street: string;
postal_code: string;
country: string;
code_payment: string;
expiration_payment: string;
iban: string;
photo_user: string;

id_subscription?: Subscription; // relation possible
id_langue?: Language; // relation possible
}

// Type pour les langues récupérées via l'API /languages/read
export interface Language {
    id_langue: number;
    langue: string;
    iso: string;
}
  
// Type pour les abonnements récupérées via l'API /subscriptions/read
export interface Subscription {
    id_sub: number;
    name_sub: string;
    description_sub: string;
}

export type Service = {
    id_svc: number;
    date_creation_svc: string;
    name_svc: string;
    category: string;
  };  