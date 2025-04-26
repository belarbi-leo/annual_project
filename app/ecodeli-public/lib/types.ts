export interface Languages {
    idLanguage: number;
    name: string;
    iso: string;
    available: string;
}
export interface Subscriptions {
    idSub: number;
    nameSub: string;
    descriptionSub: string;
}
export interface Services {
    idSvc?: number;
    idAdminCreator?: number;
    dateCreationSvc?: string;
    nameSvc: string;
    category: 'sp' | 'tl' | 'tr' | 'id' | 'el';
    auth: 'part' | 'pro' | 'all';
}
export interface ReqServices {
    reqSvc?: number;
    userReq: number;
    adminRes?: number | null;
    svc: number;
    statusReq?: 'pending' | 'accept' | 'refused';
    dateReq?: string;
    dateRes?: string | null;
    reasonRes?: string | null;
}
export type Users = {
    idUser?: number;
    dateRegistration?: string;
    dateAcceptCgu: string;
    dateAcceptCgv: string;
    role: 'admin' | 'part' | 'pro';
    accountStatus?: 'active' | 'suspended' | 'banned' | 'overlays' | 'pro_pending';
    dateStatus?: string;
    email: string;
    password: string;
    phoneNumber: string;
    firstName?: string | null;
    lastName?: string | null;
    companyName?: string | null;
    siret?: string | null;
    photoUser?: string | null;
    bio?: string | null;
    location: string;
    suite?: string | null;
    locality: string;
    state: string;
    postalCode: string;
    country: string;
    latitude: number;
    longitude: number;
    language: string;
    subscription?: number;
}
export interface UserForm {
    role: string;
    wantsPresta: boolean;
    firstName: string;
    lastName: string;
    companyName: string;
    siret: string;
    email: string;
    password: string;
    passwordConfirm: string;
    phoneNumber: string;
    photoUser: File | string;
    bio: string;
    acceptCgu: boolean;
    acceptCgv: boolean;
    dateAcceptCgu: string;
    dateAcceptCgv: string;
    location: string;
    suite: string;
    locality: string;
    state: string;
    postalCode: string;
    country: string;
    latitude: string;
    longitude: string;
    isValidSelection: boolean;
    idSvc?: number;
    language?: number;
}




export interface Address {
    location: string;
    suite: string;
    locality: string;
    state: string;
    postalCode: string;
    country: string;
    latitude: string;
    longitude: string;
    isValidSelection: boolean;
    };
    