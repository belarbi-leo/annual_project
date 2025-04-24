export interface User {
    id_user: number;
    date_registration: string;
    date_acceptCGU: string;
    role: string;
    account_status: string;
    date_status: string;
    first_name: string;
    last_name: string;
    company_name: string;
    siret: string;
    email: string;
    password: string;
    phone_number: string;
    photo_user: File | string;
    bio: string;
    location: string;
    suite: string;
    locality: string;
    state: string;
    postal_code: string;
    country: string;
    id_subscription?: Subscription; 
    id_language?: Language;
    }

// Type pour les langues récupérées via l'API /languages/read
export interface Language {
    id_language: number;
    language: string;
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