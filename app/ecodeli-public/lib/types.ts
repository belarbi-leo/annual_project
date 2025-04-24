// Dans votre fichier de types (par exemple @/lib/types.ts)
export interface UserSign {
    role: string;
    wantsPresta: boolean;
    first_name: string;
    last_name: string;
    company_name: string;
    siret: string;
    email: string;
    password: string;
    password_confirm: string;
    phone_number: string;
    photo_user: File | string;
    bio: string;
    date_accept_cgu: boolean;
    date_accept_cgv: boolean;
    location: string;
    suite: string;
    locality: string;
    state: string;
    postal_code: string;
    country: string;
    latitude: string;
    longitude: string;
    isValidSelection: boolean;
    id_svc?: number;
    id_language?: number;
}

export interface User {
    id_user: number;
    date_registration: string;
    date_accept_cgu: boolean;
    date_accept_cgv: boolean;
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
    address: Address;
    id_subscription?: Subscription; 
    id_language?: Language;
    }
export interface Address {
    location: string;
    suite: string;
    locality: string;
    state: string;
    postal_code: string;
    country: string;
    latitude: string;
    longitude: string;
    isValidSelection: boolean;
    };
    
export interface Service {
    id_svc: number;
    id_admin_creator: number;
    date_creation_svc: string;
    name_svc: string;
    category: string;
    auth: string;
}

export interface Language {
    id_language: number;
    language: string;
    iso: string;
}
  
export interface Subscription {
    id_sub: number;
    name_sub: string;
    description_sub: string;
}