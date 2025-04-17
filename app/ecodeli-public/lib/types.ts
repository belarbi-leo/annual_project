export interface User {
    id_user: number;
    date_registration: string;
    role: string;
    account_status: string;
    date_status: string;
    email: string;
    password: string;
    phone_number: number;
    first_name: string;
    last_name: string;
    company_name: string;
    photo_user: string;
    bio: string;
    siret: string;
    street: string;
    postal_code: string;
    country: string;
    id_subscription?: Subscription; 
    id_langue?: Language;
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