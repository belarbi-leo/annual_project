"use client";

import { useEffect, useRef, useState } from "react";
import { useRouter } from 'next/navigation';
import { useTranslations } from "next-intl";
import Image from "next/image";
import Link from "next/link";
import Header from "@/components/header";
import Background from "@/components/background";
import type { User, Service } from '@/lib/types';
import { fetchAllServices } from "@/lib/services/fetch-all-services";
type FormUser = Omit<User, 'id_user' | 'date_registration' | 'account_status' | 'date_status'> & {
  password_confirm: string;
};

export default function Sign() {
  const router = useRouter();
  const locationInputRef = useRef(null);
  const [etape, setEtape] = useState<1 | 2 | 3 | 4>(1);
  const [formData, setFormData] = useState<FormUser>({role: '',first_name: '',last_name: '',company_name: '',siret: '',email: '',password: '',password_confirm: '',phone_number: '',photo_user: '',bio: '',location: '',suite: '',locality: '',state: '',postal_code: '',country: '',date_acceptCGU: '',});
  const [errors, setErrors] = useState<Record<string, string>>({});
  const [services, setServices] = useState<Service[]>([]);
  const [isSubmitting, setIsSubmitting] = useState(false);

  const change = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement>) => {
    const { name, value, type, files } = e.target as HTMLInputElement;
    const checked = (e.target as HTMLInputElement).checked;
    setFormData({
      ...formData,
      [name]: type === 'checkbox' ? checked : type === 'file' && files ? files[0] : value
    });
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
        if (!formData.role) {
          erreurs.role = "Veuillez sélectionner votre type de compte";
        }
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
        if (formData.role === 'pro') {
          if (!formData.location.trim()) erreurs.location = "L'adresse est requise";
          if (!formData.locality.trim()) erreurs.locality = "La ville est requise";
          if (!formData.state.trim()) erreurs.state = "L'état/région est requis";
          if (!formData.postal_code.trim()) {
            erreurs.postal_code = "Le code postal est requis";
          } else if (!/^\d{5}$/.test(formData.postal_code)) {
            erreurs.postal_code = "Le code postal doit contenir 5 chiffres";
          }
          if (!formData.country.trim()) erreurs.country = "Le pays est requis";
        }
        if (formData.photo_user instanceof File) {
          const fileSize = formData.photo_user.size / 1024 / 1024; // Taille en MB
          const fileType = formData.photo_user.type;
          if (fileSize > 5) {
            erreurs.photo_user = "La taille de l'image ne doit pas dépasser 5MB";
          } else if (!['image/jpeg', 'image/jpg', 'image/png'].includes(fileType)) {
            erreurs.photo_user = "Seuls les formats JPG et PNG sont acceptés";
          }
        } 
        if (formData.bio && formData.bio.trim()) {
          if (formData.bio.trim().length < 50) {
            erreurs.bio = "Votre biographie doit contenir au moins 50 caractères";
          } else if (formData.bio.trim().length > 1000) {
            erreurs.bio = "Votre biographie ne doit pas dépasser 1000 caractères";
          }
        }
        break;
      case 4:
        // if (formData.estPrestataire && !formData.typePrestation) {
        //   erreurs.typePrestation = "Veuillez sélectionner un type de prestation";
        // }
        // if (formData.estPrestataire && !formData.documentIdentite) {
        //   erreurs.documentIdentite = "Une pièce d'identité est requise pour les prestataires";
        // }
        // if (formData.estPrestataire && !formData.documentQualification && 
        //     formData.role === 'pro') {
        //   erreurs.documentQualification = "Un document de qualification est requis";
        // }
        // if (!formData.acceptCGU) {
        //   erreurs.acceptCGU = "Vous devez accepter les conditions générales d'utilisation";
        // }
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
        const formDataToSend = new FormData();
        
        Object.keys(formData).forEach(key => {
            formDataToSend.append(key, formData[key as keyof FormUser] as string);
        });
        
        const response = await fetch('/api/inscription', { // Chemin ???
          method: 'POST',
          body: formDataToSend,
        });
        
        if (response.ok) {
          const result = await response.json();
          router.push('../home');
        } else {
          const errorData = await response.json();
          setErrors({ submit: errorData.message || "Une erreur s'est produite lors de l'inscription" });
        }
      } catch (error) {
        setErrors({ submit: "Erreur de connexion au serveur" });
      } finally {
        setIsSubmitting(false);
      }
    }
  };

  useEffect(() => {
    const loadGoogleMapsScript = () => {
      if (window.google && window.google.maps && window.google.maps.places) {
        initAutocomplete();
        return;
      }

      const apiKey = process.env.NEXT_PUBLIC_GOOGLE_MAPS_API_KEY;
      const googleMapsScript = document.createElement('script');
      googleMapsScript.src = `https://maps.googleapis.com/maps/api/js?key=${apiKey}&libraries=places`;
      googleMapsScript.async = true;
      googleMapsScript.defer = true;
      googleMapsScript.onload = initAutocomplete;
      document.head.appendChild(googleMapsScript);
    };

    if (etape === 3) {
      loadGoogleMapsScript();
    }
  }, [etape]);

  const initAutocomplete = () => {
    if (!window.google || !window.google.maps || !window.google.maps.places) {
      console.error("L'API Google Maps n'est pas correctement chargée");
      return;
    }
    
    if (!locationInputRef.current) {
      console.error("La référence de l'input d'adresse est null");
      return;
    }
  
    try {
      const autocomplete = new window.google.maps.places.Autocomplete(locationInputRef.current, {
        fields: ['address_components', 'geometry', 'name'],
        types: ['address'],
      });
  
      autocomplete.addListener('place_changed', () => {
        const place = autocomplete.getPlace();
        
        if (!place.geometry) {
          console.warn(`Aucun détail disponible pour cette adresse: '${place.name}'`);
          return;
        }
  
        fillInAddress(place);
      });
    } catch (error) {
      console.error("Erreur lors de l'initialisation de l'autocomplétion:", error);
    }
  };

  const fillInAddress = (place: google.maps.places.PlaceResult) => {
    const addressData = {location: '',locality: '',state: '',postal_code: '',country: ''};
    const getComponentName = (componentType: string) => {
      for (const component of place.address_components || []) {
        if (component.types.includes(componentType)) {
          return ['street_number', 'state', 'postal_code'].includes(componentType) ?
            component.short_name : component.long_name;
        }
      }
      return '';
    };

    const streetNumber = getComponentName('street_number');
    const route = getComponentName('route');
    addressData.location = `${streetNumber} ${route}`.trim();
    addressData.locality = getComponentName('locality');
    addressData.state = getComponentName('state');
    addressData.postal_code = getComponentName('postal_code');
    addressData.country = getComponentName('country');

    setFormData(prevData => ({
      ...prevData,
      ...addressData
    }));
  };
  
  useEffect(() => {
    const chargerServices = async () => {
      const type = formData.role === 'part' ? 'particuliers' : formData.role === 'pro' ? 'professionnels' : null;
      const data = await fetchAllServices(type);
      setServices(data);
    };
  
    if (etape === 4 && formData.role === 'pro') {
      chargerServices();
    }
  }, [etape, formData.role]);
  
  const optionsPrestation = services.map((service: any) => ({
    value: service.nom,
    label: service.nom,
  }));
  // Options de prestation selon le type d'utilisateur
  // const optionsPrestation = formData.role === 'part' 
  /*   ? [{ value: 'livraison', label: 'Livraison de colis' }]*/
  //   : [
  //       { value: 'livraison', label: 'Livraison de colis' },
  //       { value: 'transport', label: 'Transport de personnes' },
  //       { value: 'restauration', label: 'Service de restauration' },
  //       { value: 'bricolage', label: 'Services de bricolage' },
  //       { value: 'autre', label: 'Autre prestation' }
  //     ];

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
                      className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-emerald-500 focus:border-emerald-500 ${errors.first_name && "border-red-500 dark:border-red-900"}`}
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
                        className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-emerald-500 focus:border-emerald-500 ${errors.last_name && "border-red-500 dark:border-red-900"}`}
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
                      className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-emerald-500 focus:border-emerald-500 ${errors.company_name && "border-red-500 dark:border-red-900"}`}
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
                        className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-emerald-500 focus:border-emerald-500 ${errors.siret && "border-red-500 dark:border-red-900"}`}
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
                    className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-emerald-500 focus:border-emerald-500 ${errors.email && "border-red-500 dark:border-red-900"}`}
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
                    className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-emerald-500 focus:border-emerald-500 ${errors.phone_number && "border-red-500 dark:border-red-900"}`}
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
                    className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-emerald-500 focus:border-emerald-500 ${errors.password && "border-red-500 dark:border-red-900"}`}
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
                    className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-emerald-500 focus:border-emerald-500 ${errors.password_confirm && "border-red-500 dark:border-red-900"}`}
                    required
                  />
                  {errors.password_confirm && <span className="mt-1 text-sm text-red-600 dark:text-red-400">{errors.password_confirm}</span>}
                </div>
              </div>
            )}
            {/* Étape 2: Informations complémentaires */}
            {etape === 3 && (
              <div className="space-y-4">

                {/* Adresse */}
                  {formData.role === 'pro' ? (
                    <div className="bg-emerald-50 p-2 rounded-md mt-10">
                      <p className="text-xs text-center text-emerald-700 font-medium italic">
                        L'adresse de l'entreprise est obligatoire pour valider votre inscription et garantir la transparence auprès des clients.
                      </p>
                    </div>
                  ) : (
                      <div className="bg-emerald-50 p-2 rounded-md mt-10">
                        <p className="text-xs text-center text-emerald-700 font-medium italic">
                        Fournir votre adresse vous permettras de recevoir des recommandations plus pertinentes !
                      </p>
                    </div>
                  )}            
                  <div>
                    <label htmlFor="location" className="block text-sm font-medium text-gray-700">
                      Adresse
                    </label>
                    <input
                      ref={locationInputRef}
                      type="text"
                      name="location"
                      id="location"
                      value={formData.location}
                      onChange={change}
                      placeholder=''
                      className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-emerald-500 focus:border-emerald-500 ${errors.location && "border-red-500 dark:border-red-900"}`}
                      required={formData.role === 'pro'}
                    />
                    {errors.location && <span className="mt-1 text-sm text-red-600 dark:text-red-400">{errors.location}</span>}
                  </div>
                  <div>
                    <label htmlFor="suite" className="block text-sm font-medium text-gray-700">
                      Informations complémentaires
                    </label>
                    <input
                      type="text"
                      name="suite"
                      id="suite"
                      value={formData.suite}
                      onChange={change}
                      className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-emerald-500 focus:border-emerald-500 ${errors.suite && "border-red-500 dark:border-red-900"}`}
                    />
                  </div>
                  <div>
                    <label htmlFor="locality" className="block text-sm font-medium text-gray-700">
                      Ville
                    </label>
                    <input
                      type="text"
                      name="locality"
                      id="locality"
                      value={formData.locality}
                      onChange={change}
                      className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-emerald-500 focus:border-emerald-500 ${errors.locality && "border-red-500 dark:border-red-900"}`}
                      required={formData.role === 'pro'}
                    />
                    {errors.locality && <span className="mt-1 text-sm text-red-600 dark:text-red-400">{errors.locality}</span>}
                  </div>           
                  <div className="grid grid-cols-1 gap-4 sm:grid-cols-2">
                    <div>
                      <label htmlFor="state" className="block text-sm font-medium text-gray-700">
                        État
                      </label>
                      <input
                        type="text"
                        name="state"
                        id="state"
                        value={formData.state}
                        onChange={change}
                        className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-emerald-500 focus:border-emerald-500 ${errors.state && "border-red-500 dark:border-red-900"}`}
                        required={formData.role === 'pro'}
                      />
                      {errors.state && <span className="mt-1 text-sm text-red-600 dark:text-red-400">{errors.state}</span>}
                    </div>
                    <div>
                      <label htmlFor="postal_code" className="block text-sm font-medium text-gray-700">
                        Code postal
                      </label>
                      <input
                        type="text"
                        name="postal_code"
                        id="postal_code"
                        value={formData.postal_code}
                        maxLength={5}
                        onChange={change}
                        className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-emerald-500 focus:border-emerald-500 ${errors.postal_code && "border-red-500 dark:border-red-900"}`}
                        required={formData.role === 'pro'}
                      />
                      {errors.postal_code && <span className="mt-1 text-sm text-red-600 dark:text-red-400">{errors.postal_code}</span>}
                    </div>
                  </div>
                  <div className="mt-4">
                    <label htmlFor="country" className="block text-sm font-medium text-gray-700">
                      Pays
                    </label>
                    <input
                      name="country"
                      id="country"
                      value={formData.country}
                      onChange={change}
                      className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-emerald-500 focus:border-emerald-500 ${errors.country && "border-red-500 dark:border-red-900"}`}
                      required={formData.role === 'pro'}
                    />
                    {errors.country && <span className="mt-1 text-sm text-red-600 dark:text-red-400">{errors.country}</span>}
                  </div>
                  
                  {/* Photo de profil */}
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

                  {/* Biographie */}
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
                      className="mt-4 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-emerald-500 focus:border-emerald-500 placeholder:text-sm"
                    />
                  </div>
              </div>
            )}          
            {/* Étape 4: Choix des services et finalisation */}
            {etape === 4 && (
              <div className="space-y-6">
                <div>
                  <div className="flex items-center">
                    <input
                      type="checkbox"
                      name="estPrestataire"
                      id="estPrestataire"
                      checked={formData.estPrestataire}
                      onChange={change}
                      className="h-4 w-4 text-indigo-600 focus:ring-indigo-500 border-gray-300 rounded"
                    />
                    <label htmlFor="estPrestataire" className="ml-2 block text-sm font-medium text-gray-700">
                      Je souhaite également proposer des prestations de services
                    </label>
                  </div>
                  <p className="mt-1 text-xs text-gray-500">Ce choix peut être modifié ultérieurement.</p>
                </div>
                
                {formData.estPrestataire && (
                  <div className="space-y-4">
                    <div>
                      <label htmlFor="typePrestation" className="block text-sm font-medium text-gray-700">
                        Type de prestation
                      </label>
                      <select
                        name="typePrestation"
                        id="typePrestation"
                        value={formData.typePrestation}
                        onChange={change}
                        className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                      >
                        <option value="">Sélectionnez un type de prestation</option>
                        {optionsPrestation.map((option) => (
                          <option key={option.value} value={option.value}>
                            {option.label}
                          </option>
                        ))}
                      </select>
                      {errors.typePrestation && <p className="mt-1 text-sm text-red-600 dark:text-red-400">{errors.typePrestation}</p>}
                    </div>
                    
                    <div>
                      <label className="block text-sm font-medium text-gray-700">
                        Pièce d'identité (obligatoire pour les prestataires)
                      </label>
                      <div className="mt-1 flex justify-center px-6 pt-5 pb-6 border-2 border-gray-300 border-dashed rounded-md">
                        <div className="space-y-1 text-center">
                          <svg
                            className="mx-auto h-12 w-12 text-gray-400"
                            stroke="currentColor"
                            fill="none"
                            viewBox="0 0 48 48"
                            aria-hidden="true"
                          >
                            <path
                              d="M28 8H12a4 4 0 00-4 4v20m32-12v8m0 0v8a4 4 0 01-4 4H12a4 4 0 01-4-4v-4m32-4l-3.172-3.172a4 4 0 00-5.656 0L28 28M8 32l9.172-9.172a4 4 0 015.656 0L28 28m0 0l4 4m4-24h8m-4-4v8m-12 4h.02"
                              strokeWidth={2}
                              strokeLinecap="round"
                              strokeLinejoin="round"
                            />
                          </svg>
                          <div className="flex text-sm text-gray-600">
                            <label
                              htmlFor="documentIdentite"
                              className="relative cursor-pointer bg-white rounded-md font-medium text-indigo-600 hover:text-indigo-500 focus-within:outline-none focus-within:ring-2 focus-within:ring-offset-2 focus-within:ring-indigo-500"
                            >
                              <span>Télécharger un fichier</span>
                              <input
                                id="documentIdentite"
                                name="documentIdentite"
                                type="file"
                                className="sr-only"
                                onChange={change}
                              />
                            </label>
                            <p className="pl-1">ou glisser-déposer</p>
                          </div>
                          <p className="text-xs text-gray-500">
                            PNG, JPG, PDF jusqu'à 10MB
                          </p>
                        </div>
                      </div>
                      {formData.documentIdentite && (
                        <p className="mt-2 text-sm text-green-600">
                          Fichier sélectionné: {formData.documentIdentite.name}
                        </p>
                      )}
                      {errors.documentIdentite && (
                        <p className="mt-1 text-sm text-red-600 dark:text-red-400">{errors.documentIdentite}</p>
                      )}
                    </div>
                    
                    {formData.role === 'pro' && (
                      <div>
                        <label className="block text-sm font-medium text-gray-700">
                          Document de qualification (obligatoire pour les professionnels)
                        </label>
                        <div className="mt-1 flex justify-center px-6 pt-5 pb-6 border-2 border-gray-300 border-dashed rounded-md">
                          <div className="space-y-1 text-center">
                            <svg
                              className="mx-auto h-12 w-12 text-gray-400"
                              stroke="currentColor"
                              fill="none"
                              viewBox="0 0 48 48"
                              aria-hidden="true"
                            >
                              <path
                                d="M28 8H12a4 4 0 00-4 4v20m32-12v8m0 0v8a4 4 0 01-4 4H12a4 4 0 01-4-4v-4m32-4l-3.172-3.172a4 4 0 00-5.656 0L28 28M8 32l9.172-9.172a4 4 0 015.656 0L28 28m0 0l4 4m4-24h8m-4-4v8m-12 4h.02"
                                strokeWidth={2}
                                strokeLinecap="round"
                                strokeLinejoin="round"
                              />
                            </svg>
                            <div className="flex text-sm text-gray-600">
                              <label
                                htmlFor="documentQualification"
                                className="relative cursor-pointer bg-white rounded-md font-medium text-indigo-600 hover:text-indigo-500 focus-within:outline-none focus-within:ring-2 focus-within:ring-offset-2 focus-within:ring-indigo-500"
                              >
                                <span>Télécharger un fichier</span>
                                <input
                                  id="documentQualification"
                                  name="documentQualification"
                                  type="file"
                                  className="sr-only"
                                  onChange={change}
                                />
                              </label>
                              <p className="pl-1">ou glisser-déposer</p>
                            </div>
                            <p className="text-xs text-gray-500">
                              PNG, JPG, PDF jusqu'à 10MB
                            </p>
                          </div>
                        </div>
                        {formData.documentQualification && (
                          <p className="mt-2 text-sm text-green-600">
                            Fichier sélectionné: {formData.documentQualification.name}
                          </p>
                        )}
                        {errors.documentQualification && (
                          <p className="mt-1 text-sm text-red-600 dark:text-red-400">{errors.documentQualification}</p>
                        )}
                      </div>
                    )}
                  </div>
                )}
                
                {/* <div className="mt-6">
                      <label className="block text-sm font-medium text-gray-700 mb-2">
                        Pièce d'identité
                        <span className="text-indigo-600 ml-1">*</span>
                      </label>
                      <div className="mt-1 flex justify-center px-6 pt-5 pb-6 border-2 border-gray-300 border-dashed rounded-md">
                        <div className="space-y-1 text-center">
                          {formData.documentIdentite ? (
                            <div>
                              <p className="text-sm text-green-600 font-medium">
                                ✓ Document sélectionné
                              </p>
                              <button
                                type="button"
                                onClick={() => setFormData({...formData, documentIdentite: null})}
                                className="mt-2 inline-flex items-center px-2.5 py-1.5 border border-transparent text-xs font-medium rounded text-red-700 bg-red-100 hover:bg-red-200 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-red-500"
                              >
                                Supprimer
                              </button>
                            </div>
                          ) : (
                            <>
                              <svg
                                className="mx-auto h-12 w-12 text-gray-400"
                                stroke="currentColor"
                                fill="none"
                                viewBox="0 0 48 48"
                                aria-hidden="true"
                              >
                                <path
                                  d="M28 8H12a4 4 0 00-4 4v20m32-12v8m0 0v8a4 4 0 01-4 4H12a4 4 0 01-4-4v-4m32-4l-3.172-3.172a4 4 0 00-5.656 0L28 28M8 32l9.172-9.172a4 4 0 015.656 0L28 28m0 0l4 4m4-24h8m-4-4v8m-12 4h.02"
                                  strokeWidth={2}
                                  strokeLinecap="round"
                                  strokeLinejoin="round"
                                />
                              </svg>
                              <div className="flex text-sm text-gray-600 justify-center">
                                <label
                                  htmlFor="documentIdentite"
                                  className="relative cursor-pointer bg-white rounded-md font-medium text-indigo-600 hover:text-indigo-500 focus-within:outline-none focus-within:ring-2 focus-within:ring-offset-2 focus-within:ring-indigo-500"
                                >
                                  <span>Télécharger un fichier</span>
                                  <input
                                    id="documentIdentite"
                                    name="documentIdentite"
                                    type="file"
                                    className="sr-only"
                                    onChange={change}
                                    accept=".jpg,.jpeg,.png,.pdf"
                                    required
                                  />
                                </label>
                                <p className="pl-1">ou glisser-déposer</p>
                              </div>
                              <p className="text-xs text-gray-500">
                                PNG, JPG, PDF jusqu'à 10MB
                              </p>
                            </>
                          )}
                        </div>
                      </div>
                      {errors.documentIdentite && (
                        <p className="mt-1 text-sm text-red-600 dark:text-red-400">{errors.documentIdentite}</p>
                      )}
                    </div> */}

                <div className="flex items-center">
                  <input
                    id="acceptCGU"
                    name="acceptCGU"
                    type="checkbox"
                    checked={formData.acceptCGU}
                    onChange={change}
                    className="h-4 w-4 text-indigo-600 focus:ring-indigo-500 border-gray-300 rounded"
                  />
                  <label htmlFor="acceptCGU" className="ml-2 block text-sm text-gray-900">
                    J'accepte les <a href="/cgu" className="text-indigo-600 hover:text-indigo-500">conditions générales d'utilisation</a>
                  </label>
                </div>
                {errors.acceptCGU && <p className="mt-1 text-sm text-red-600 dark:text-red-400">{errors.acceptCGU}</p>}
                
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
                  <p className="text-center text-sm pt-5">
                    <Link href="./" className="text-[#49cb5c] hover:underline">Retour à la connexion</Link>
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