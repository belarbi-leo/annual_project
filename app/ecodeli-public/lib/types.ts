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
    id_langue?: Language;
    }

export interface Service {
    id_svc: number;
    id_admin_creator: number;
    date_creation_svc: string;
    name_svc: string;
    category: string;
    auth: string;
}

export interface Language {
    id_langue: number;
    langue: string;
    iso: string;
}
  
export interface Subscription {
    id_sub: number;
    name_sub: string;
    description_sub: string;
}