"use client";

import { useRouter } from 'next/navigation';
import { useState } from 'react';
import type { User } from '@/lib/types';


export default function Inscription() {
  const router = useRouter();
  const [etape, setEtape] = useState(1);
  const [errors, setErrors] = useState({});
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [formData, setFormData] = useState<FormData>({
    role: '',
    first_name: '',
    last_name: '',
    email: '',
    password: '',
    phone_number: '',
    company_name: '',
    bio: '',
    siret: '',
    street: '',
    postal_code: '',
    country: '',

    adresse: '',
    ville: '',
    codePostal: '',
    pays: '',
    nomEntreprise: '',
    siret: '',
    adresseEntreprise: '',
    villeEntreprise: '',
    codePostalEntreprise: '',
    paysEntreprise: 'France',
    estPrestataire: false,
    typePrestation: '',
    documentIdentite: null,
    documentQualification: null,
    acceptCGU: false
  });

  const handleChange = (e) => {
    const { name, value, type, checked, files } = e.target;
    setFormData({
      ...formData,
      [name]: type === 'checkbox' ? checked : type === 'file' ? files[0] : value
    });
  };

  const validateEtape = (etapeActuelle: 1 | 2 | 3 | 4) => {
    let erreurs = {};
    
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
        if (formData.role === 'part') {
          if (!formData.adresse.trim()) erreurs.adresse = "L'adresse est requise";
          if (!formData.ville.trim()) erreurs.ville = "La ville est requise";
          if (!formData.codePostal.trim()) erreurs.codePostal = "Le code postal est requis";
        } else {
          if (!formData.siret.trim()) {
            erreurs.siret = "Le numéro SIRET est requis";
          } else if (formData.siret.length !== 14) {
            erreurs.siret = "Le numéro SIRET doit contenir 14 chiffres";
          }
          if (!formData.adresseEntreprise.trim()) erreurs.adresseEntreprise = "L'adresse de l'entreprise est requise";
          if (!formData.villeEntreprise.trim()) erreurs.villeEntreprise = "La ville est requise";
          if (!formData.codePostalEntreprise.trim()) erreurs.codePostalEntreprise = "Le code postal est requis";
        }
        break;
      case 4:
        if (formData.estPrestataire && !formData.typePrestation) {
          erreurs.typePrestation = "Veuillez sélectionner un type de prestation";
        }
        if (formData.estPrestataire && !formData.documentIdentite) {
          erreurs.documentIdentite = "Une pièce d'identité est requise pour les prestataires";
        }
        if (formData.estPrestataire && !formData.documentQualification && 
            formData.role === 'pro') {
          erreurs.documentQualification = "Un document de qualification est requis";
        }
        if (!formData.acceptCGU) {
          erreurs.acceptCGU = "Vous devez accepter les conditions générales d'utilisation";
        }
        break;
    }
    
    return erreurs;
  };

  const handleNext = () => {
    const erreurs = validateEtape(etape);
    setErrors(erreurs);
    
    if (Object.keys(erreurs).length === 0) {
      setEtape(etape + 1);
    }
  };

  const handlePrevious = () => {
    setEtape(etape - 1);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const erreurs = validateEtape(etape);
    setErrors(erreurs);
    
    if (Object.keys(erreurs).length === 0) {
      setIsSubmitting(true);
      
      try {
        // Création d'un objet FormData pour l'envoi des fichiers
        const formDataToSend = new FormData();
        
        // Ajout des données du formulaire
        Object.keys(formData).forEach(key => {
          if (key === 'documentIdentite' || key === 'documentQualification') {
            if (formData[key]) {
              formDataToSend.append(key, formData[key]);
            }
          } else {
            formDataToSend.append(key, formData[key]);
          }
        });
        
        // Appel à l'API Spring Boot
        const response = await fetch('/api/inscription', {
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

  // Options de prestation selon le type d'utilisateur
  const optionsPrestation = formData.role === 'part' 
    ? [{ value: 'livraison', label: 'Livraison de colis' }]
    : [
        { value: 'livraison', label: 'Livraison de colis' },
        { value: 'transport', label: 'Transport de personnes' },
        { value: 'restauration', label: 'Service de restauration' },
        { value: 'bricolage', label: 'Services de bricolage' },
        { value: 'autre', label: 'Autre prestation' }
      ];

  return (
    <>
      <div className="min-h-screen flex items-center justify-center py-12 px-4 sm:px-6 lg:px-8 overflow-hidden relative">
      
        <div aria-hidden="true" className="absolute top-[-10%] left-[-10%] -z-10 transform-gpu blur-3xl opacity-50">
            <div className="h-100 w-100 bg-gradient-to-tr from-[#89c8fd] to-[#60b6ff] dark:from-[#245b90] dark:to-[#1a426a] rounded-full" style={{ filter: 'blur(100px)' }}/>
        </div>
        <div aria-hidden="true" className="absolute bottom-[-10%] right-[-10%] -z-10 transform-gpu blur-3xl opacity-50">
            <div className="h-100 w-100 bg-gradient-to-tr from-[#96d629] to-[#baeb6c] dark:from-[#3f7d1c] dark:to-[#67a731] rounded-full" style={{ filter: 'blur(100px)' }}/>
        </div>
        <div className="max-w-md w-full mx-auto space-y-8">
          <div>
            <h2 className="mt-6 text-center text-3xl font-extrabold text-gray-900">
              {etape === 1 && "Créez votre compte"}
            </h2>
            <p className="mt-6 text-center text-xl text-gray-900">
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
          
          <form className="mt-8 space-y-6" onSubmit={handleSubmit}>
            {/* Étape 1: Choix du type d'utilisateur */}
            {etape === 1 && (
              <div className="space-y-6">
                  <label className="block text-sm text-center font-medium text-gray-700 mt-10 mb-4">
                    Je m'inscris en tant que :
                  </label>
                  
                  <div className="grid grid-cols-1 gap-4 xl:grid-cols-2">
                    <div 
                      className={`relative border rounded-lg p-4 cursor-pointer hover:border-emerald-500 ${formData.role === 'part' ? 'bg-emerald-50 border-emerald-600' : 'border-gray-300'}`}
                      onClick={() => setFormData({...formData, role: 'part'})}
                    >
                      <div className="flex items-center justify-between">
                        <div className="flex items-center">
                          <div className="text-sm">
                            <p className="font-medium text-gray-900">Particulier</p>
                            <p className="text-gray-500">Compte personnel pour accéder aux services</p>
                          </div>
                        </div>
                      </div>
                    </div>
                    
                    <div 
                      className={`relative border rounded-lg p-4 cursor-pointer hover:border-emerald-500 ${formData.role === 'pro' ? 'bg-emerald-50 border-emerald-600' : 'border-gray-300'}`}
                      onClick={() => setFormData({...formData, role: 'pro'})}
                    >
                      <div className="flex items-center justify-between">
                        <div className="flex items-center">
                          <div className="text-sm">
                            <p className="font-medium text-gray-900">Professionnel</p>
                            <p className="text-gray-500">Compte entreprise avec vérification</p>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  
                  {errors.role && (<div className="mt-5 p-2 rounded-md bg-red-100 text-red-600 flex items-center justify-center text-sm">{errors.role}</div>)}
                  <div className="text-xs text-center text-gray-500">
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
                            <label htmlFor="first_name" className="block text-sm font-medium text-gray-700">
                              Nom {errors.first_name && <span className="mt-1 text-sm text-red-600">: {errors.first_name}</span>}
                            </label>
                            <input
                            type="text"
                            name="first_name"
                            id="first_name"
                            value={formData.first_name}
                            onChange={handleChange}
                            className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-emerald-500 focus:border-emerald-500 ${errors.first_name && "border-red-500"}`}
                            />
                        </div>
                        <div>
                            <label htmlFor="last_name" className="block text-sm font-medium text-gray-700">
                              Prénom {errors.last_name && <span className="mt-1 text-sm text-red-600">: {errors.last_name}</span>}
                            </label>
                            <input
                            type="text"
                            name="last_name"
                            id="last_name"
                            value={formData.last_name}
                            onChange={handleChange}
                            className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-emerald-500 focus:border-emerald-500 ${errors.last_name && "border-red-500"}`}
                            />
                        </div>
                    </div>
                ) : (
                    <div>
                        <label htmlFor="company_name" className="block text-sm font-medium text-gray-700">
                          Nom de l'entreprise {errors.company_name && <span className="mt-1 text-sm text-red-600">: {errors.company_name}</span>}
                        </label>
                        <input
                        type="text"
                        name="company_name"
                        id="company_name"
                        value={formData.company_name}
                        onChange={handleChange}
                        className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-emerald-500 focus:border-emerald-500 ${errors.company_name && "border-red-500"}`}
                        />
                    </div>
                )}

                <div>
                  <label htmlFor="email" className="block text-sm font-medium text-gray-700">
                    Adresse e-mail {errors.email && <span className="mt-1 text-sm text-red-600">: {errors.email}</span>}
                  </label>
                  <input
                    type="email"
                    name="email"
                    id="email"
                    value={formData.email}
                    onChange={handleChange}
                    className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-emerald-500 focus:border-emerald-500 ${errors.email && "border-red-500"}`}
                  />
                </div>
                
                <div>
                  <label htmlFor="phone_number" className="block text-sm font-medium text-gray-700">
                    Numéro de téléphone {errors.phone_number && <span className="mt-1 text-sm text-red-600">: {errors.phone_number}</span>}
                  </label>
                  <input
                    type="tel"
                    name="phone_number"
                    id="phone_number"
                    value={formData.phone_number}
                    onChange={handleChange}
                    className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-emerald-500 focus:border-emerald-500 ${errors.phone_number && "border-red-500"}`}
                  />
                </div>
                
                <div>
                  <label htmlFor="password" className="block text-sm font-medium text-gray-700">
                    Mot de passe {errors.password && <span className="mt-1 text-sm text-red-600">: {errors.password}</span>}
                  </label>
                  <input
                    type="password"
                    name="password"
                    id="password"
                    value={formData.password}
                    onChange={handleChange}
                    className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-emerald-500 focus:border-emerald-500 ${errors.password && "border-red-500"}`}
                  />
                </div>
                
                <div>
                  <label htmlFor="password_confirm" className="block text-sm font-medium text-gray-700">
                    Confirmer le mot de passe {errors.password_confirm && <span className="mt-1 text-sm text-red-600">: {errors.password_confirm}</span>}
                  </label>
                  <input
                    type="password"
                    name="password_confirm"
                    id="password_confirm"
                    value={formData.password_confirm}
                    onChange={handleChange}
                    className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-emerald-500 focus:border-emerald-500 ${errors.password_confirm && "border-red-500"}`}
                  />
                </div>
              </div>
            )}
            
            {/* Étape 3: Informations spécifiques au profil */}
            {etape === 3 && (
              <div className="space-y-4">
                <div>Ajouter une photo spé limportance en temps que professionnel</div>
                <div>Ajouter un bio spé limportance en temps que professionnel</div>
                <div>
                  <label htmlFor="adresse" className="block text-sm font-medium text-gray-700">
                    Adresse
                  </label>
                  <input
                    type="text"
                    name="adresse"
                    id="adresse"
                    value={formData.adresse}
                    onChange={handleChange}
                    className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                  />
                  {errors.adresse && <p className="mt-1 text-sm text-red-600">{errors.adresse}</p>}
                </div>
                
                <div className="grid grid-cols-1 gap-4 sm:grid-cols-2">
                  <div>
                    <label htmlFor="ville" className="block text-sm font-medium text-gray-700">
                      Ville
                    </label>
                    <input
                      type="text"
                      name="ville"
                      id="ville"
                      value={formData.ville}
                      onChange={handleChange}
                      className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                    />
                    {errors.ville && <p className="mt-1 text-sm text-red-600">{errors.ville}</p>}
                  </div>
                  
                  <div>
                    <label htmlFor="codePostal" className="block text-sm font-medium text-gray-700">
                      Code postal
                    </label>
                    <input
                      type="text"
                      name="codePostal"
                      id="codePostal"
                      value={formData.codePostal}
                      onChange={handleChange}
                      className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                    />
                    {errors.codePostal && <p className="mt-1 text-sm text-red-600">{errors.codePostal}</p>}
                  </div>
                </div>
                
                <div>
                  <label htmlFor="pays" className="block text-sm font-medium text-gray-700">
                    Pays
                  </label>
                  <select
                    name="pays"
                    id="pays"
                    value={formData.pays}
                    onChange={handleChange}
                    className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                  >
                    <option value="France">France</option>
                    <option value="Belgique">Belgique</option>
                    <option value="Suisse">Suisse</option>
                    <option value="Canada">Canada</option>
                    <option value="Autre">Autre</option>
                  </select>
                </div>
              </div>
            )}
            
            {/* Étape 3: Informations spécifiques au profil professionnel */}
            {etape === 3 && (
              <div className="space-y-4">
                <div>
                  <label htmlFor="nomEntreprise" className="block text-sm font-medium text-gray-700">
                    Nom de l'entreprise
                  </label>
                  <input
                    type="text"
                    name="nomEntreprise"
                    id="nomEntreprise"
                    value={formData.nomEntreprise}
                    onChange={handleChange}
                    className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                  />
                  {errors.nomEntreprise && <p className="mt-1 text-sm text-red-600">{errors.nomEntreprise}</p>}
                </div>
                
                <div>
                  <label htmlFor="siret" className="block text-sm font-medium text-gray-700">
                    Numéro SIRET
                  </label>
                  <input
                    type="text"
                    name="siret"
                    id="siret"
                    value={formData.siret}
                    onChange={handleChange}
                    maxLength="14"
                    className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                  />
                  {errors.siret && <p className="mt-1 text-sm text-red-600">{errors.siret}</p>}
                </div>
                
                <div>
                  <label htmlFor="adresseEntreprise" className="block text-sm font-medium text-gray-700">
                    Adresse de l'entreprise
                  </label>
                  <input
                    type="text"
                    name="adresseEntreprise"
                    id="adresseEntreprise"
                    value={formData.adresseEntreprise}
                    onChange={handleChange}
                    className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                  />
                  {errors.adresseEntreprise && <p className="mt-1 text-sm text-red-600">{errors.adresseEntreprise}</p>}
                </div>
                
                <div className="grid grid-cols-1 gap-4 sm:grid-cols-2">
                  <div>
                    <label htmlFor="villeEntreprise" className="block text-sm font-medium text-gray-700">
                      Ville
                    </label>
                    <input
                      type="text"
                      name="villeEntreprise"
                      id="villeEntreprise"
                      value={formData.villeEntreprise}
                      onChange={handleChange}
                      className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                    />
                    {errors.villeEntreprise && <p className="mt-1 text-sm text-red-600">{errors.villeEntreprise}</p>}
                  </div>
                  
                  <div>
                    <label htmlFor="codePostalEntreprise" className="block text-sm font-medium text-gray-700">
                      Code postal
                    </label>
                    <input
                      type="text"
                      name="codePostalEntreprise"
                      id="codePostalEntreprise"
                      value={formData.codePostalEntreprise}
                      onChange={handleChange}
                      className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                    />
                    {errors.codePostalEntreprise && <p className="mt-1 text-sm text-red-600">{errors.codePostalEntreprise}</p>}
                  </div>
                </div>
                
                <div>
                  <label htmlFor="paysEntreprise" className="block text-sm font-medium text-gray-700">
                    Pays
                  </label>
                  <select
                    name="paysEntreprise"
                    id="paysEntreprise"
                    value={formData.paysEntreprise}
                    onChange={handleChange}
                    className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                  >
                    <option value="France">France</option>
                    <option value="Belgique">Belgique</option>
                    <option value="Suisse">Suisse</option>
                    <option value="Canada">Canada</option>
                    <option value="Autre">Autre</option>
                  </select>
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
                      onChange={handleChange}
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
                        onChange={handleChange}
                        className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
                      >
                        <option value="">Sélectionnez un type de prestation</option>
                        {optionsPrestation.map((option) => (
                          <option key={option.value} value={option.value}>
                            {option.label}
                          </option>
                        ))}
                      </select>
                      {errors.typePrestation && <p className="mt-1 text-sm text-red-600">{errors.typePrestation}</p>}
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
                                onChange={handleChange}
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
                        <p className="mt-1 text-sm text-red-600">{errors.documentIdentite}</p>
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
                                  onChange={handleChange}
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
                          <p className="mt-1 text-sm text-red-600">{errors.documentQualification}</p>
                        )}
                      </div>
                    )}
                  </div>
                )}
                
                <div className="flex items-center">
                  <input
                    id="acceptCGU"
                    name="acceptCGU"
                    type="checkbox"
                    checked={formData.acceptCGU}
                    onChange={handleChange}
                    className="h-4 w-4 text-indigo-600 focus:ring-indigo-500 border-gray-300 rounded"
                  />
                  <label htmlFor="acceptCGU" className="ml-2 block text-sm text-gray-900">
                    J'accepte les <a href="/cgu" className="text-indigo-600 hover:text-indigo-500">conditions générales d'utilisation</a>
                  </label>
                </div>
                {errors.acceptCGU && <p className="mt-1 text-sm text-red-600">{errors.acceptCGU}</p>}
                
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
            
            <div className="flex justify-between pt-5">
              {etape === 1 && (
                <button
                  type="button"
                  onClick={handleNext}
                  className="w-full ml-auto bg-emerald-600 py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white hover:bg-emerald-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-500"
                >
                  Suivant
                </button>
              )}
              {etape > 1 && (
                <button
                  type="button"
                  onClick={handlePrevious}
                  className="bg-white py-2 px-4 border border-gray-300 rounded-md shadow-sm text-sm font-medium text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                >
                  Précédent
                </button>
              )}
              {(etape === 2 || etape === 3) && (
                <button
                  type="button"
                  onClick={handleNext}
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