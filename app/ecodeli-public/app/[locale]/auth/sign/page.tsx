"use client";

import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import { useLocale, useTranslations } from "next-intl";
import Link from "next/link";
import Header from "@/components/header";
import Background from "@/components/background";
import Error from "@/components/ui/error";
import Dropdown from "@/components/ui/dropdown";
import PhotoUpload from "@/components/ui/imageUser";
import InputAddress from "@/components/input-address";
import type { Users, Services, ReqServices, UserForm } from '@/lib/types';
import extractErrorMessage from '@lib/func';
import { insertUser } from "@/lib/users/insertUsers";
import { insertReqService } from "@/lib/services/insertReqServices";
import { fetchServicesByAuth } from "@/lib/services/fetchServicesByAuth";
import { fetchAllLanguages } from "@/lib/languages/fetchAllLanguages";


export default function Sign() {
  const t = useTranslations('Sign');
  const locale = useLocale();
  const router = useRouter();
  const [etape, setEtape] = useState<1 | 2 | 3 | 4>(1);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [errors, setErrors] = useState<Record<string, string>>({});
  const [services, setServices] = useState<Services[]>([]);
  const [formData, setFormData] = useState<UserForm>({
    role: '',
    wantsPresta: false,
    firstName: '',
    lastName: '',
    companyName: '',
    siret: '',
    email: '',
    password: '',
    passwordConfirm: '',
    phoneNumber: '',
    photoUser: '',
    bio: '',
    acceptCgu: false,
    acceptCgv: false,
    dateAcceptCgu: '',
    dateAcceptCgv: '',
    location: '',
    suite: '',
    locality: '',
    state: '',
    postalCode: '',
    country: '',
    latitude: '',
    longitude: '',
    isValidSelection: false,
    idSvc: undefined,
    language: undefined
  });

  const change = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement>) => {
    const { name, value, type, files } = e.target as HTMLInputElement;
    const checked = (e.target as HTMLInputElement).checked;
    
    if (name === 'acceptCgu' && checked) {
      setFormData({
        ...formData,
        [name]: checked,
        dateAcceptCgu: new Date().toISOString()
      });
    } else if (name === 'acceptCgv' && checked) {
      setFormData({
        ...formData,
        [name]: checked,
        dateAcceptCgv: new Date().toISOString()
      });
    } else {
      setFormData({
        ...formData,
        [name]: type === 'checkbox' ? checked : type === 'file' && files ? files[0] : value
      });
    }
  };
  
  const updateAddress = (addressData: Partial<UserForm>) => {
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
          if (!formData.firstName.trim()) erreurs.firstName = "Le nom est requis";
          if (!formData.lastName.trim()) erreurs.lastName = "Le prénom est requis";
        } else {
          if (!formData.companyName.trim()) erreurs.companyName = "Le nom de l'entreprise est requis";
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
        if (formData.password !== formData.passwordConfirm) {
          erreurs.passwordConfirm = "Les mots de passe ne correspondent pas";
        }
        if (!formData.phoneNumber.trim()) {
          erreurs.phoneNumber = "Le numéro de téléphone est requis";
        } else if (!/^\d+$/.test(formData.phoneNumber)) {
          erreurs.phoneNumber = "Le numéro de téléphone doit contenir uniquement des chiffres";
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
        if (formData.photoUser instanceof File) {
          const fileSize = formData.photoUser.size / 1024 / 1024;
          const fileType = formData.photoUser.type;
          if (fileSize > 5) {
            erreurs.photoUser = "La taille de l'image ne doit pas dépasser 5MB";
          } else if (!['image/jpeg', 'image/jpg', 'image/png'].includes(fileType)) {
            erreurs.photoUser = "Seuls les formats JPG et PNG sont acceptés";
          }
        } 
        break;
      case 4:
        if ((formData.role === 'pro'|| formData.wantsPresta) && !formData.idSvc) erreurs.idSvc = "Veuillez sélectionner un type de prestation";
        if (!formData.acceptCgu) erreurs.acceptCgu = "Vous devez accepter les conditions générales d'utilisation";
        if (!formData.acceptCgv) erreurs.acceptCgv = "Vous devez accepter les conditions générales de ventes";
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
        const user: Users = {
          role: formData.role as "part" | "pro" | "admin",
          email: formData.email,
          password: formData.password,
          phoneNumber: formData.phoneNumber,
          dateAcceptCgu: formData.dateAcceptCgu,
          dateAcceptCgv: formData.dateAcceptCgv,
          location: formData.location,
          suite: formData.suite || null,
          locality: formData.locality,
          state: formData.state,
          postalCode: formData.postalCode,
          country: formData.country,
          latitude: parseFloat(formData.latitude),  
          longitude: parseFloat(formData.longitude),
          language: locale
        };

        if (formData.role === 'pro') {
          user.firstName = null;
          user.lastName = null;
          user.companyName = formData.companyName;
          user.siret = formData.siret;
          user.accountStatus = 'pro_pending';
        } else {
          user.firstName = formData.firstName;
          user.lastName = formData.lastName;
          user.companyName = null;
          user.siret = null;
          user.accountStatus = 'overlays';
        }
        if (formData.bio) user.bio = formData.bio;
        // Pour la photo, vous devrez peut-être utiliser une requête séparée ou base64

        const res_user = await insertUser(user);   
        if (res_user.status === 201) {
          if ((formData.role === 'pro' || formData.wantsPresta) && formData.idSvc) {
            try {
              const reqSvc: ReqServices = {
                userReq: res_user.data.idUser,
                svc: formData.idSvc
              };
              const res_reqSvc = await insertReqService(reqSvc);
              if (res_reqSvc.status !== 201) {  
                let msg = extractErrorMessage(res_reqSvc.data.errors);
                setErrors({ submit: `Application error: ${msg}` });
              }
            } catch (error) {
              setErrors({ submit: "Erreur serveur, veuillez réessayer plus tard." });
            }
          }
          // router.push('../home');
        } else {
          let msg = extractErrorMessage(res_user.data.errors);
          setErrors({ submit: `Application error: ${msg}` });
        }
      } catch (error) {
        setErrors({ submit: "Erreur serveur, veuillez réessayer plus tard." });
      } finally {
        setIsSubmitting(false);
      }
    }
  };
  
  useEffect(() => {
    const loadServices = async () => {
      const role = formData.role === 'part' || formData.role === 'pro' ? formData.role : 'pro';
      const val: ("part" | "pro" | "all")[] = role === 'part' ? ['part', 'all'] : ['pro', 'all'];
      const data = await fetchServicesByAuth(val);
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
                      <label htmlFor="firstName" className="block text-sm font-medium text-gray-700 dark:text-white">
                        Nom
                      </label>
                      <input
                      type="text"
                      name="firstName"
                      id="firstName"
                      value={formData.firstName}
                      onChange={change}
                      className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-emerald-500 bg-white text-black dark:bg-gray-700 dark:text-white ${errors.firstName && "border-red-500 dark:border-red-900"}`}
                      required={formData.role === 'part'}
                      />
                      {errors.firstName && <Error message={errors.firstName} />}
                    </div>
                    <div>
                        <label htmlFor="lastName" className="block text-sm font-medium text-gray-700 dark:text-white">
                          Prénom
                        </label>
                        <input
                        type="text"
                        name="lastName"
                        id="lastName"
                        value={formData.lastName}
                        onChange={change}
                        className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-emerald-500 bg-white text-black dark:bg-gray-700 dark:text-white ${errors.lastName && "border-red-500 dark:border-red-900"}`}
                        required={formData.role === 'part'}
                        />
                        {errors.lastName && <Error message={errors.lastName} />}
                    </div>
                  </div>
                ) : (
                  <div className="grid grid-cols-1 gap-4 sm:grid-cols-2">
                    <div>
                      <label htmlFor="companyName" className="block text-sm font-medium text-gray-700 dark:text-white">
                        Nom de l'entreprise
                      </label>
                      <input
                      type="text"
                      name="companyName"
                      id="companyName"
                      value={formData.companyName}
                      onChange={change}
                      className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-emerald-500 bg-white text-black dark:bg-gray-700 dark:text-white ${errors.companyName && "border-red-500 dark:border-red-900"}`}
                      required={formData.role === 'pro'}
                      />
                      {errors.companyName && <Error message={errors.companyName} />}
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
                        {errors.siret && <Error message={errors.siret} />}
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
                  {errors.email && <Error message={errors.email} />}
                </div>           
                <div>
                  <label htmlFor="phoneNumber" className="block text-sm font-medium text-gray-700 dark:text-white">
                    Numéro de téléphone
                  </label>
                  <input
                    type="tel"
                    name="phoneNumber"
                    id="phoneNumber"
                    value={formData.phoneNumber}
                    onChange={change}
                    className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-emerald-500 bg-white text-black dark:bg-gray-700 dark:text-white ${errors.phoneNumber && "border-red-500 dark:border-red-900"}`}
                    required
                  />
                  {errors.phoneNumber && <Error message={errors.phoneNumber} />}
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
                  {errors.password && <Error message={errors.password} />}
                </div>             
                <div>
                  <label htmlFor="passwordConfirm" className="block text-sm font-medium text-gray-700 dark:text-white">
                    Confirmer le mot de passe
                  </label>
                  <input
                    type="password"
                    name="passwordConfirm"
                    id="passwordConfirm"
                    value={formData.passwordConfirm}
                    onChange={change}
                    className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-emerald-500 bg-white text-black dark:bg-gray-700 dark:text-white ${errors.passwordConfirm && "border-red-500 dark:border-red-900"}`}
                    required
                  />
                  {errors.passwordConfirm && <Error message={errors.passwordConfirm} />}
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
                  <label className="block text-sm font-medium text-gray-700 mb-2 dark:text-white">
                    Photo de profil : {errors.photoUser && <Error message={errors.photoUser} />}
                  </label>
                  <PhotoUpload
                    currentPhoto={formData.photoUser}
                    onChange={change}
                    error={errors.photoUser}
                    label="Photo de profil"
                  />
                  // components pour tout les form  ? ou le miens pour les form user et un autre pour les form image services ou colis ? 
                  // drag n drop comme 
                  <div className="mt-4 flex flex-col items-center">
                    {formData.photoUser ? (
                      <div className="relative">
                        <img
                          src={formData.photoUser instanceof File ? URL.createObjectURL(formData.photoUser) : ''}
                          alt="Aperçu du profil"
                          className="h-32 w-32 rounded-full object-cover"
                        />
                        <button
                          type="button"
                          onClick={() => setFormData({...formData, photoUser: ''})}
                          className="absolute -top-2 -right-2 bg-red-500 text-white rounded-full p-1 hover:bg-red-600"
                        >
                          <svg xmlns="http://www.w3.org/2000/svg" className="h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M6 18L18 6M6 6l12 12" />
                          </svg>
                        </button>
                      </div>
                    ) : (
                      <div className="flex justify-center items-center w-32 h-32 bg-gray-100 rounded-full border-2 border-dashed border-gray-300">
                        <label htmlFor="photoUser" className="cursor-pointer text-center p-2">
                            <svg className="mx-auto h-8 w-8 text-gray-400" stroke="currentColor" fill="none" viewBox="0 0 24 24">
                              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M4 16l4.586-4.586a2 2 0 012.828 0L16 16m-2-2l1.586-1.586a2 2 0 012.828 0L20 14m-6-6h.01M8 12h.01M12 12h.01M16 12h.01M20 12h.01" />
                            </svg>
                            <p className="text-xs text-gray-500 mt-1">Cliquez pour ajouter</p>
                        </label>
                        <input id="photoUser" name="photoUser" type="file" accept="image/*" className="sr-only" onChange={change}/>
                      </div>
                    )}
                    <p className="text-xs text-gray-500 mt-2">Format JPG ou PNG, 5MB max</p>
                  </div>
                </div>
                {formData.role === 'pro' && (
                  <div className="bg-emerald-50 p-2 rounded-md mt-10">
                    <p className="text-xs text-center text-emerald-700 font-medium italic">Une biographie détaillée augmente vos chances d'être sélectionné pour des missions</p>
                  </div>
                )}
                <div className="mb-6">
                  <label htmlFor="bio" className="block text-sm font-medium text-gray-700 mb-1 dark:text-white">
                      Biographie : {errors.bio && <Error message={errors.bio} />}
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
                              id: service.idSvc,
                              name: service.nameSvc,
                            }))}
                            selected={formData.idSvc}
                            onChange={(value) => setFormData(prev => ({ ...prev, idSvc: value }))}
                          />
                          {errors.idSvc && <Error message={errors.idSvc} />}                       
                        </div>
                      )}
                  </div>
                ) : (
                  <div>
                    <Dropdown
                      label="Type de prestation"
                      options={services.map(service => ({
                        id: service.idSvc,
                        name: service.nameSvc,
                      }))}
                      selected={formData.idSvc}
                      onChange={(value) => setFormData(prev => ({ ...prev, idSvc: value }))}
                    />
                    {errors.idSvc && <Error message={errors.idSvc} />}
                  </div>
                )}

                <div className="flex items-center mb-0">
                  <input
                    id="acceptCgu"
                    name="acceptCgu"
                    type="checkbox"
                    checked={formData.acceptCgu}
                    onChange={change}
                    className="h-4 w-4 text-indigo-600 focus:ring-indigo-500 border-gray-300 rounded"
                  />
                  <label htmlFor="acceptCgu" className="ml-2 block text-sm text-gray-900">
                    J'accepte les <a href="../legal/cgu" target="_blank" rel="noopener noreferrer" className="text-indigo-600 hover:text-indigo-500">conditions générales d'utilisation</a>
                  </label>
                </div>
                {errors.acceptCgu && <Error message={errors.acceptCgu} />}

                <div className="flex items-center mb-0">
                  <input
                    id="acceptCgv"
                    name="acceptCgv"
                    type="checkbox"
                    checked={formData.acceptCgv}
                    onChange={change}
                    className="h-4 w-4 text-indigo-600 focus:ring-indigo-500 border-gray-300 rounded"
                  />
                  <label htmlFor="acceptCgv" className="ml-2 block text-sm text-gray-900">
                    J'accepte les <a href="../legal/cgu" target="_blank" rel="noopener noreferrer" className="text-indigo-600 hover:text-indigo-500">conditions générales de ventes</a>
                  </label>
                </div>
                {errors.acceptCgv && <Error message={errors.acceptCgv} />}
                {/* juste pour dev */}
                {errors.submit && <Error message={errors.submit} />}
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