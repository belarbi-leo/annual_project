"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import { useLocale, useTranslations } from "next-intl";
import Link from "next/link";
import Header from "@/components/header";
import Background from "@/components/background";
import InputAddress from "@/components/input-address";
import Dropdown from "@/components/ui/dropdown";
import type { UserSign, Service } from '@/lib/types';
import { insertUser } from "@/lib/users/insert-users";
import { insertRequestService } from "@/lib/services/insert-requests-services";
import { fetchServicesByAuth } from "@/lib/services/fetch-services-by-auth";
import { fetchAllLanguages } from "@/lib/languages/fetch-all-languages";


export default function Sign() {
  const t = useTranslations('Sign');
  const locale = useLocale();
  const router = useRouter();
  const [etape, setEtape] = useState<1 | 2 | 3 | 4>(1);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [errors, setErrors] = useState<Record<string, string>>({});
  const [services, setServices] = useState<Service[]>([]);
  const [formData, setFormData] = useState<UserSign>({
    role: '',
    wantsPresta: false,
    first_name: '',
    last_name: '',
    company_name: '',
    siret: '',
    email: '',
    password: '',
    password_confirm: '',
    phone_number: '',
    photo_user: '',
    bio: '',
    date_accept_cgu: false,
    date_accept_cgv: false,
    location: '',
    suite: '',
    locality: '',
    state: '',
    postal_code: '',
    country: '',
    latitude: '',
    longitude: '',
    isValidSelection: false,
    id_svc: undefined,
    id_language: undefined
  });
  
  const change = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement>) => {
    const { name, value, type, files } = e.target as HTMLInputElement;
    const checked = (e.target as HTMLInputElement).checked;
    setFormData({
      ...formData,
      [name]: type === 'checkbox' ? checked : type === 'file' && files ? files[0] : value
    });
  };
  
  const updateAddress = (addressData: Partial<UserSign>) => {
    setFormData(prev => ({
      ...prev,
      ...addressData
    }));
  };

  const previous = () => {
    setEtape((etape - 1) as 1 | 2 | 3 | 4);
  };

  const next = () => {
    const erreurs = validate(etape);
    setErrors(erreurs);
    
    if (Object.keys(erreurs).length === 0) {
      setEtape((etape + 1) as 1 | 2 | 3 | 4);
    }
  }; 

  const validate = (etapeActuelle: 1 | 2 | 3 | 4) => {
    let erreurs: Record<string, string> = {};
    
    switch (etapeActuelle) {
      case 1:
        if (!formData.role) erreurs.role = "Veuillez sélectionner votre type de compte";
        break;
      case 2:
        if (formData.role === 'part') {
          if (!formData.first_name.trim()) erreurs.first_name = "Le nom est requis";
          if (!formData.last_name.trim()) erreurs.last_name = "Le prénom est requis";
        } else {
          if (!formData.company_name.trim()) erreurs.company_name = "Le nom de l'entreprise est requis";
          if (!formData.siret.trim()) {
            erreurs.siret = "Le numéro SIRET est requis";
          } else if (!/^\d{14}$/.test(formData.siret)) {
            erreurs.siret = "Le numéro SIRET doit contenir 14 chiffres";
          }
        }
        if (!formData.email.trim()) {
          erreurs.email = "L'email est requis";
        } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(formData.email)) {
          erreurs.email = "Format d'email invalide";
        }
        if (!formData.password) {
          erreurs.password = "Le mot de passe est requis";
        } else if (formData.password.length < 12) {
          erreurs.password = "Le mot de passe doit contenir au moins 12 caractères";
        } else if (!/(?=.*[a-z])(?=.*[A-Z])(?=.*\d)/.test(formData.password)) {
          erreurs.password = "Le mot de passe doit contenir au moins une majuscule, une minuscule et un chiffre";
        }
        if (formData.password !== formData.password_confirm) {
          erreurs.password_confirm = "Les mots de passe ne correspondent pas";
        }
        if (!formData.phone_number.trim()) {
          erreurs.phone_number = "Le numéro de téléphone est requis";
        } else if (!/^\d+$/.test(formData.phone_number)) {
          erreurs.phone_number = "Le numéro de téléphone doit contenir uniquement des chiffres";
        }
        break;
      case 3:
        if (!formData.location) {
          erreurs.address = "L'adresse est requise";
        } else if (!formData.isValidSelection) {
          erreurs.address = "Veuillez sélectionner une adresse dans la liste";
        }
        if (formData.suite && formData.suite.trim().length > 500) erreurs.suite = "Votre complément d'addresse ne doit pas dépasser 500 caractères";
        if (formData.bio && formData.bio.trim().length > 1000) erreurs.bio = "Votre biographie ne doit pas dépasser 1000 caractères";
        if (formData.photo_user instanceof File) {
          const fileSize = formData.photo_user.size / 1024 / 1024;
          const fileType = formData.photo_user.type;
          if (fileSize > 5) {
            erreurs.photo_user = "La taille de l'image ne doit pas dépasser 5MB";
          } else if (!['image/jpeg', 'image/jpg', 'image/png'].includes(fileType)) {
            erreurs.photo_user = "Seuls les formats JPG et PNG sont acceptés";
          }
        } 
        break;
      case 4:
        if ((formData.role === 'pro'|| formData.wantsPresta) && !formData.id_svc) erreurs.id_svc = "Veuillez sélectionner un type de prestation";
        if (!formData.date_accept_cgu) erreurs.date_accept_cgu = "Vous devez accepter les conditions générales d'utilisation";
        if (!formData.date_accept_cgv) erreurs.date_accept_cgv = "Vous devez accepter les conditions générales de ventes";
        break;
    }
    
    return erreurs;
  };

  const submit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const erreurs = validate(etape);
    setErrors(erreurs);
    
    if (Object.keys(erreurs).length === 0) {
      setIsSubmitting(true);

      try {
        const user = new FormData();
        const req_svc = new FormData();

        if (formData.role === 'part') {
          user.append('first_name', formData.first_name);
          user.append('last_name', formData.last_name);
        } else {
          user.append('company_name', formData.company_name);
          user.append('siret', formData.siret);
        }
        user.append('role', formData.role);
        user.append('email', formData.email);
        user.append('password', formData.password);
        user.append('phone_number', formData.phone_number);
        user.append('date_accept_cgu', new Date().toISOString());
        user.append('date_accept_cgv', new Date().toISOString());
        user.append('address.location', formData.location);
        user.append('address.suite', formData.suite || '');
        user.append('address.locality', formData.locality);
        user.append('address.state', formData.state);
        user.append('address.postal_code', formData.postal_code);
        user.append('address.country', formData.country);
        user.append('address.latitude', formData.latitude);
        user.append('address.longitude', formData.longitude);
        if (formData.bio) user.append('bio', formData.bio);
        if (formData.photo_user instanceof File) user.append('photo_user', formData.photo_user);
        user.append('account_status', 'pending');
        user.append("id_language", locale);

        if ((formData.role === 'pro' || formData.wantsPresta) && formData.id_svc) {
          req_svc.append('id_svc', formData.id_svc.toString());
        }
        // id_req_svc auto 
        // id_user_req auto ?
        // id_admin_res ""
        // id_service id_svc
        // status_req auto ?
        // date_req auto ? 
        // date_res ""
        // reason_res ""
        
        // TEST 

        Object.keys(formData).forEach(key => {
            const value = formData[key as keyof UserSign];
            user.append(key, value instanceof File ? value : String(value || ''));
            req_svc.append(key, value instanceof File ? value : String(value || ''));
        });
        
        console.log(user);
        console.log(req_svc);
        
        const res_user = await insertUser(user); // les donneées non definis dans le form ? statut etc 
        // onnlay cree fonction pour changeer a la deconnexion le status account 
        // Subsripttion par defaut puis dans le tuto overlays mettre sub et page d'acceuil mettre sub aussi 
        // 200 201 204 404 400 500 
        if (res_user.status === 200) {
          const res_req_svc = await insertRequestService(req_svc);
          if (res_user.status === 200) {
            router.push('../home');
          } else {
            setErrors({ submit: res_req_svc.data?.message || "Erreur lors de la création de la demande de service" });
          }
        } else {
          setErrors({ submit: res_user.data?.message || "Erreur lors de la création de l'utilisateur" });
        }
      } catch (error) {
        setErrors({ submit: "Erreur de connexion au serveur" });
      } finally {
        setIsSubmitting(false);
      }
    }
  };
  
  useEffect(() => {
    const loadServices = async () => {
      const type = formData.role === 'part' ? 'all' : 'pro';
      const data = await fetchServicesByAuth(type);
      setServices(data);
    };
    if (etape === 4) loadServices();
  }, [etape, formData.role]);

  return (
    <>
      <div className="min-h-screen flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8 overflow-hidden relative">
        <Background />
        <Header />

        <div className="max-w-md w-full mx-auto space-y-8">
          <div>
            <h2 className="mt-6 text-center text-3xl font-extrabold text-gray-900 dark:text-white">
              {etape === 1 && "Créez votre compte"}
            </h2>
            <p className="mt-6 text-center text-xl text-gray-900 dark:text-white">
              {etape === 1 && "Choisissez votre type de compte"}
              {etape === 2 && "Renseignez vos informations personnelles"}
              {etape === 3 && "Complétez vos informations"}
              {etape === 4 && "Finalisez votre inscription"}
            </p>
          </div>
          <div className="flex items-center justify-between">
            {[1, 2, 3, 4].map((step) => (
              <div key={step} className="flex flex-col items-center">
                <div className={`h-8 w-8 rounded-full flex items-center justify-center ${etape >= step ? 'bg-emerald-500 text-white' : 'bg-gray-200 text-gray-500'}`}>
                  {step}
                </div>
                <div className="text-xs mt-1">
                  {step === 1 && "Type"}
                  {step === 2 && "Identité"}
                  {step === 3 && "Détails"}
                  {step === 4 && "Services"}
                </div>
              </div>
            ))}
          </div>
          <form className="mt-8 space-y-6" onSubmit={submit}>
            {/* Étape 1: Choix du type d'utilisateur */}
            {etape === 1 && (
              <div className="space-y-6">
                  <label className="block text-sm text-center font-medium text-gray-700 mt-10 mb-4 dark:text-white">
                    Je m'inscris en tant que :
                  </label>
                  
                  <div className="grid grid-cols-1 gap-4 xl:grid-cols-2">
                    <div 
                      className={`relative border rounded-lg p-4 cursor-pointer hover:border-emerald-500 
                        ${formData.role === 'part' ? 'bg-emerald-50 dark:bg-emerald-900 border-emerald-600' : 'border-gray-300 dark:border-gray-600'}`}
                      onClick={() => setFormData({...formData, role: 'part'})}
                    >
                      <div className="flex items-center justify-between">
                        <div className="flex items-center">
                          <div className="text-sm">
                            <p className="font-medium text-gray-900 dark:text-white">Particulier</p>
                            <p className="text-gray-500 dark:text-white">Compte personnel pour accéder aux services</p>
                          </div>
                        </div>
                      </div>
                    </div>
                    
                    <div 
                      className={`relative border rounded-lg p-4 cursor-pointer hover:border-emerald-500 
                        ${formData.role === 'pro' ? 'bg-emerald-50 dark:bg-emerald-900 border-emerald-600' : 'border-gray-300 dark:border-gray-600'}`}
                      onClick={() => setFormData({...formData, role: 'pro'})}
                    >
                      <div className="flex items-center justify-between">
                        <div className="flex items-center">
                          <div className="text-sm">
                            <p className="font-medium text-gray-900 dark:text-white">Professionnel</p>
                            <p className="text-gray-500 dark:text-white">Compte entreprise avec vérification</p>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  
                  {errors.role && (
                    <div className="mt-5 p-2 rounded-md bg-red-100 text-red-600 dark:text-red-300 dark:bg-red-900 flex items-center justify-center text-sm">
                      {errors.role}
                    </div>
                  )}
                  <div className="text-xs text-center text-gray-500 dark:text-white">
                    <p>⚠️ Attention: Le choix entre particulier et professionnel est irréversible.</p>
                  </div>
              </div>
            )}
            {/* Étape 2: Informations de base */}
            {etape === 2 && (
              <div className="space-y-4">
                {formData.role === 'part' ? (
                  <div className="grid grid-cols-1 gap-4 sm:grid-cols-2">
                    <div>
                      <label htmlFor="first_name" className="block text-sm font-medium text-gray-700 dark:text-white">
                        Nom
                      </label>
                      <input
                      type="text"
                      name="first_name"
                      id="first_name"
                      value={formData.first_name}
                      onChange={change}
                      className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-emerald-500 bg-white text-black dark:bg-gray-700 dark:text-white ${errors.first_name && "border-red-500 dark:border-red-900"}`}
                      required={formData.role === 'part'}
                      />
                      {errors.first_name && <span className="mt-1 text-sm text-red-600 dark:text-red-400">{errors.first_name}</span>}
                    </div>
                    <div>
                        <label htmlFor="last_name" className="block text-sm font-medium text-gray-700 dark:text-white">
                          Prénom
                        </label>
                        <input
                        type="text"
                        name="last_name"
                        id="last_name"
                        value={formData.last_name}
                        onChange={change}
                        className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-emerald-500 bg-white text-black dark:bg-gray-700 dark:text-white ${errors.last_name && "border-red-500 dark:border-red-900"}`}
                        required={formData.role === 'part'}
                        />
                        {errors.last_name && <span className="mt-1 text-sm text-red-600 dark:text-red-400">{errors.last_name}</span>}
                    </div>
                  </div>
                ) : (
                  <div className="grid grid-cols-1 gap-4 sm:grid-cols-2">
                    <div>
                      <label htmlFor="company_name" className="block text-sm font-medium text-gray-700 dark:text-white">
                        Nom de l'entreprise
                      </label>
                      <input
                      type="text"
                      name="company_name"
                      id="company_name"
                      value={formData.company_name}
                      onChange={change}
                      className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-emerald-500 bg-white text-black dark:bg-gray-700 dark:text-white ${errors.company_name && "border-red-500 dark:border-red-900"}`}
                      required={formData.role === 'pro'}
                      />
                      {errors.company_name && <span className="mt-1 text-sm text-red-600 dark:text-red-400">{errors.company_name}</span>}
                    </div>
                    <div>
                        <label htmlFor="siret" className="block text-sm font-medium text-gray-700 dark:text-white">
                          Numéro SIRET
                        </label>
                        <input
                        type="text"
                        name="siret"
                        id="siret"
                        value={formData.siret}
                        onChange={change}
                        maxLength={14}
                        className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-emerald-500 bg-white text-black dark:bg-gray-700 dark:text-white ${errors.siret && "border-red-500 dark:border-red-900"}`}
                        required={formData.role === 'pro'}
                        />
                        {errors.siret && <span className="mt-1 text-sm text-red-600 dark:text-red-400">{errors.siret}</span>}
                    </div>
                  </div>
                )}
                <div>
                  <label htmlFor="email" className="block text-sm font-medium text-gray-700 dark:text-white">
                    Adresse e-mail
                  </label>
                  <input
                    type="email"
                    name="email"
                    id="email"
                    value={formData.email}
                    onChange={change}
                    className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-emerald-500 bg-white text-black dark:bg-gray-700 dark:text-white ${errors.email && "border-red-500 dark:border-red-900"}`}
                    required
                  />
                  {errors.email && <span className="mt-1 text-sm text-red-600 dark:text-red-400">{errors.email}</span>}
                </div>           
                <div>
                  <label htmlFor="phone_number" className="block text-sm font-medium text-gray-700 dark:text-white">
                    Numéro de téléphone
                  </label>
                  <input
                    type="tel"
                    name="phone_number"
                    id="phone_number"
                    value={formData.phone_number}
                    onChange={change}
                    className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-emerald-500 bg-white text-black dark:bg-gray-700 dark:text-white ${errors.phone_number && "border-red-500 dark:border-red-900"}`}
                    required
                  />
                  {errors.phone_number && <span className="mt-1 text-sm text-red-600 dark:text-red-400">{errors.phone_number}</span>}
                </div>  
                <div>
                  <label htmlFor="password" className="block text-sm font-medium text-gray-700 dark:text-white">
                    Mot de passe
                  </label>
                  <input
                    type="password"
                    name="password"
                    id="password"
                    value={formData.password}
                    onChange={change}
                    className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-emerald-500 bg-white text-black dark:bg-gray-700 dark:text-white ${errors.password && "border-red-500 dark:border-red-900"}`}
                    required
                  />
                  {errors.password && <span className="mt-1 text-sm text-red-600 dark:text-red-400">{errors.password}</span>}
                </div>             
                <div>
                  <label htmlFor="password_confirm" className="block text-sm font-medium text-gray-700 dark:text-white">
                    Confirmer le mot de passe
                  </label>
                  <input
                    type="password"
                    name="password_confirm"
                    id="password_confirm"
                    value={formData.password_confirm}
                    onChange={change}
                    className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-emerald-500 bg-white text-black dark:bg-gray-700 dark:text-white ${errors.password_confirm && "border-red-500 dark:border-red-900"}`}
                    required
                  />
                  {errors.password_confirm && <span className="mt-1 text-sm text-red-600 dark:text-red-400">{errors.password_confirm}</span>}
                </div>
              </div> 
            )}
            {/* Étape 3: Informations complémentaires */}
            {etape === 3 && (
              <div className="space-y-4">
                {formData.role === 'pro' && (
                    <div className="bg-emerald-50 p-2 rounded-md mt-10">
                    <p className="text-xs text-center text-emerald-700 font-medium italic">
                        Une photo professionnelle renforce votre crédibilité auprès des clients
                    </p>
                    </div>
                )}
                <div className="mb-6">
                    <label className="block text-sm font-medium text-gray-700 mb-2">
                    Photo de profil {errors.photo_user && <span className="mt-1 text-sm text-red-600 dark:text-red-400">: {errors.photo_user}</span>}
                    </label>
                    <div className="mt-4 flex flex-col items-center">
                    {formData.photo_user ? (
                        <div className="relative">
                        <img
                            src={formData.photo_user instanceof File ? URL.createObjectURL(formData.photo_user) : ''}
                            alt="Aperçu du profil"
                            className="h-32 w-32 rounded-full object-cover"
                        />
                        <button
                            type="button"
                            onClick={() => setFormData({...formData, photo_user: ''})}
                            className="absolute -top-2 -right-2 bg-red-500 text-white rounded-full p-1 hover:bg-red-600"
                        >
                            <svg xmlns="http://www.w3.org/2000/svg" className="h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M6 18L18 6M6 6l12 12" />
                            </svg>
                        </button>
                        </div>
                    ) : (
                        <div className="flex justify-center items-center w-32 h-32 bg-gray-100 rounded-full border-2 border-dashed border-gray-300">
                        <label htmlFor="photo_user" className="cursor-pointer text-center p-2">
                            <svg className="mx-auto h-8 w-8 text-gray-400" stroke="currentColor" fill="none" viewBox="0 0 24 24">
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M4 16l4.586-4.586a2 2 0 012.828 0L16 16m-2-2l1.586-1.586a2 2 0 012.828 0L20 14m-6-6h.01M8 12h.01M12 12h.01M16 12h.01M20 12h.01" />
                            </svg>
                            <p className="text-xs text-gray-500 mt-1">Cliquez pour ajouter</p>
                        </label>
                        <input
                            id="photo_user"
                            name="photo_user"
                            type="file"
                            accept="image/*"
                            className="sr-only"
                            onChange={change}
                        />
                        </div>
                    )}
                    <p className="text-xs text-gray-500 mt-2">
                        Format JPG ou PNG, 5MB max
                    </p>
                    </div>
                </div>
                {formData.role === 'pro' && (
                    <div className="bg-emerald-50 p-2 rounded-md mt-10">
                    <p className="text-xs text-center text-emerald-700 font-medium italic">
                        Une biographie détaillée augmente vos chances d'être sélectionné pour des missions
                    </p>
                    </div>
                )}
                <div className="mb-6">
                <label htmlFor="bio" className="block text-sm font-medium text-gray-700 mb-1">
                    Biographie {errors.bio && <span className="mt-1 text-sm text-red-600 dark:text-red-400">: {errors.bio}</span>}               
                </label>
                <textarea
                    id="bio"
                    name="bio"
                    rows={3}
                    value={formData.bio}
                    onChange={change}
                    placeholder={
                    formData.role === 'pro'
                        ? "Présentez votre parcours, vos compétences et votre expertise..."
                        : "Parlez de vous, de vos passions et de ce qui vous motive..."
                    }                      
                    className={`mt-4 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-emerald-500 bg-white text-black dark:bg-gray-700 dark:text-white placeholder:text-sm ${errors.bio && "border-red-500 dark:border-red-900"}`}
                />
                </div>
                <div className="bg-emerald-50 p-2 rounded-md mt-10">
                    <p className="text-xs text-center text-emerald-700 font-medium italic">
                        {formData.role === 'pro' 
                        ? "L'adresse de l'entreprise est obligatoire pour valider votre inscription et garantir la transparence auprès des clients."
                        : "L'adresse est obligatoire pour valider votre inscription et recevoir des recommandations pertinentes."}
                    </p>
                </div>    
                <InputAddress
                  value={formData}
                  onChange={updateAddress}
                  required={true}
                  error={errors.address}
                  suiteError={errors.suite}
                  label="Adresse"
                  placeholder="Saisissez votre adresse"
                  suiteLabel="Complément d'adresse"
                  suitePlaceholder="Appartement, bâtiment, étage..."
                />
              </div>
            )}          
            {/* Étape 4: Choix des services et finalisation */}
            {etape === 4 && (
              <div className="space-y-6">
                {formData.role === 'part' ? (
                  <div>
                    <div className="flex items-center">
                      <input
                        id="wantsPresta"
                        name="wantsPresta"
                        type="checkbox"
                        checked={formData.wantsPresta}
                        onChange={change}
                        className="h-4 w-4 text-indigo-600 focus:ring-indigo-500 border-gray-300 rounded"
                      />
                      <label htmlFor="wantsPresta" className="ml-2 block text-sm font-medium text-gray-700">
                        Je souhaite également proposer des prestations de services
                      </label>
                    </div>
                    <p className="mt-1 text-xs text-gray-500">Ce choix peut être modifié ultérieurement.</p>
                      {formData.wantsPresta && (
                        <div className="mt-4">
                          <Dropdown
                            label="Type de prestation"
                            options={services.map(service => ({
                              id: service.id_svc,
                              name: service.name_svc,
                            }))}
                            selected={formData.id_svc}
                            onChange={(value) => setFormData(prev => ({ ...prev, id_svc: value }))}
                          />
                        {errors.id_svc && <p className="mt-1 text-sm text-red-600">{errors.id_svc}</p>}                          
                        </div>
                      )}
                  </div>
                ) : (
                  <div>
                    <Dropdown
                      label="Type de prestation"
                      options={services.map(service => ({
                        id: service.id_svc,
                        name: service.name_svc,
                      }))}
                      selected={formData.id_svc}
                      onChange={(value) => setFormData(prev => ({ ...prev, id_svc: value }))}
                    />
                    {errors.id_svc && <p className="mt-1 text-sm text-red-600">{errors.id_svc}</p>}
                  </div>
                )}

                <div className="flex items-center mb-0">
                  <input
                    id="date_accept_cgu"
                    name="date_accept_cgu"
                    type="checkbox"
                    checked={formData.date_accept_cgu}
                    onChange={change}
                    className="h-4 w-4 text-indigo-600 focus:ring-indigo-500 border-gray-300 rounded"
                  />
                  <label htmlFor="date_accept_cgu" className="ml-2 block text-sm text-gray-900">
                    J'accepte les <a href="../legal/cgu" target="_blank" rel="noopener noreferrer" className="text-indigo-600 hover:text-indigo-500">conditions générales d'utilisation</a>
                  </label>
                </div>
                <div className="flex items-center">
                  <input
                    id="date_accept_cgv"
                    name="date_accept_cgv"
                    type="checkbox"
                    checked={formData.date_accept_cgv}
                    onChange={change}
                    className="h-4 w-4 text-indigo-600 focus:ring-indigo-500 border-gray-300 rounded"
                  />
                  <label htmlFor="date_accept_cgv" className="ml-2 block text-sm text-gray-900">
                    J'accepte les <a href="../legal/cgu" target="_blank" rel="noopener noreferrer" className="text-indigo-600 hover:text-indigo-500">conditions générales de ventes</a>
                  </label>
                </div>
                {errors.date_accept_cgu && (
                  <div className="mt-5 mb-1 p-2 rounded-md bg-red-100 text-red-600 dark:text-red-300 dark:bg-red-900 flex items-center justify-center text-sm">
                    {errors.date_accept_cgu}
                  </div>
                )}
                {errors.date_accept_cgv && (
                  <div className="p-2 rounded-md bg-red-100 text-red-600 dark:text-red-300 dark:bg-red-900 flex items-center justify-center text-sm">
                    {errors.date_accept_cgv}
                  </div>
                )}
                {errors.submit && (
                  <div className="rounded-md bg-red-50 p-4">
                    <div className="flex">
                      <div className="ml-3">
                        <h3 className="text-sm font-medium text-red-800">Erreur lors de l'inscription</h3>
                        <div className="mt-2 text-sm text-red-700">
                          <p>{errors.submit}</p>
                        </div>
                      </div>
                    </div>
                  </div>
                )}
              </div>
            )}
            {/* Boutons de navigation */}
            <div className="flex pt-5">
              {etape === 1 && (
                <div className="flex flex-col w-full">
                  <button
                    type="button"
                    onClick={next}
                    className="w-full ml-auto bg-emerald-600 py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white hover:bg-emerald-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-500"
                  >
                    Suivant
                  </button>
                  <p className="text-center text-sm mt-3">
                    <Link href="../auth" className="text-emerald-500 hover:underline">Retour à la connexion</Link>
                  </p>
                </div>
              )}
              {etape > 1 && (
                <button
                  type="button"
                  onClick={previous}
                  className="bg-white py-2 px-4 border border-gray-300 rounded-md shadow-sm text-sm font-medium text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                >
                  Précédent
                </button>
              )}
              {(etape === 2 || etape === 3) && (
                <button
                  type="button"
                  onClick={next}
                  className="ml-auto bg-emerald-600 py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white hover:bg-emerald-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-500"
                >
                  Suivant
                </button>
              )}
              {etape === 4 && (
                <button
                  type="submit"
                  disabled={isSubmitting}
                  className="ml-auto bg-emerald-600 py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white hover:bg-emerald-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                >
                  {isSubmitting ? 'Inscription en cours...' : 'Terminer l\'inscription'}
                </button>
              )}
            </div>
          </form>
        </div>
      </div>
    </>
  );
}