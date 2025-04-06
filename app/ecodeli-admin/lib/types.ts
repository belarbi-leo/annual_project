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