"use client";

import { useEffect, useState } from "react";
import { notFound } from "next/navigation";
import { useLocale, useTranslations } from "next-intl";
import Link from "next/link";
import Error from "@/components/ui/error";
import Dropdown from "@/components/ui/dropdown";
import PhotoUpload from "@/components/ui/imageUser";
import InputAddress from "@/components/inputAddress";
import type { Users, Services, ReqServices, UserForm } from '@/lib/types';
import { extractErrorMessage } from '@/lib/func';
import { insertUser } from "@/lib/users/insertUsers";
import { insertReqService } from "@/lib/services/insertReqServices";
import { fetchServicesByAuth } from "@/lib/services/fetchServicesByAuth";

export default function Sign() {
  const t = useTranslations('Signin');
  const locale = useLocale();
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
        if (!formData.role) erreurs.role = t("errors.role");"Veuillez sélectionner votre type de compte";
        break;
      case 2:
        if (formData.role === 'part') {
          if (!formData.firstName.trim()) erreurs.firstName = t("errors.firstName");
          if (!formData.lastName.trim()) erreurs.lastName = t("errors.lastName");
        } else {
          if (!formData.companyName.trim()) erreurs.companyName = t("errors.companyName");
          if (!formData.siret.trim()) {
            erreurs.siret = t("errors.siret");
          } else if (!/^\d{14}$/.test(formData.siret)) {
            erreurs.siret = t("errors.siretInvalid");
          }
        }
        if (!formData.email.trim()) {
          erreurs.email = t("errors.email");
        } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(formData.email)) {
          erreurs.email = t("errors.emailInvalid");
        }
        if (!formData.password) {
          erreurs.password = t("errors.password");
        } else if (formData.password.length < 12) {
          erreurs.password = t("errors.passwordLength");
        } else if (!/(?=.*[a-z])(?=.*[A-Z])(?=.*\d)/.test(formData.password)) {
          erreurs.password = t("errors.passwordFormat");
        }
        if (formData.password !== formData.passwordConfirm) {
          erreurs.passwordConfirm = t("errors.passwordConfirm");
        }
        if (!formData.phoneNumber.trim()) {
          erreurs.phoneNumber = t("errors.phoneNumber");
        } else if (!/^\d+$/.test(formData.phoneNumber)) {
          erreurs.phoneNumber = t("errors.rophoneNumberInvalidle");
        }
        break;
      case 3:
        if (!formData.location) {
          erreurs.address = t("errors.address");
        } else if (!formData.isValidSelection) {
          erreurs.address = t("errors.addressInvalid");
        }
        if (formData.suite && formData.suite.trim().length > 500) erreurs.suite = t("errors.suite");
        if (formData.bio && formData.bio.trim().length > 1000) erreurs.bio = t("errors.bio");
        if (formData.photoUser instanceof File) {
          const fileSize = formData.photoUser.size / 1024 / 1024;
          const fileType = formData.photoUser.type;
          if (fileSize > 5) {
            erreurs.photoUser = t("errors.photoSize");
          } else if (!['image/jpeg', 'image/jpg', 'image/png'].includes(fileType)) {
            erreurs.photoUser = t("errors.photoFormat");
          }
        } 
        break;
      case 4:
        if ((formData.role === 'pro'|| formData.wantsPresta) && !formData.idSvc) erreurs.idSvc = t("errors.idSvc");
        if (!formData.acceptCgu) erreurs.acceptCgu = t("errors.acceptCgu");
        if (!formData.acceptCgv) erreurs.acceptCgv = t("errors.acceptCgv");
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
        // si la photo est vide tjrs insert par defaut meme dans un update, si  il a supp mettre photo par defaut 
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
              setErrors({ submit: t("errors.submit") });
            }
          }
          // router.push('../home');
        } else {
          let msg = extractErrorMessage(res_user.data.errors);
          setErrors({ submit: `Application error: ${msg}` });
        }
      } catch (error) {
        setErrors({ submit: t("errors.submit") });
      } finally {
        setIsSubmitting(false);
      }
    }
  };
  
  useEffect(() => {
    const loadServices = async () => {
      const role = formData.role === 'part' || formData.role === 'pro' ? formData.role : 'pro';
      const val: ("part" | "pro" | "all")[] = role === 'part' ? ['part', 'all'] : ['pro', 'all'];
      const res_svc = await fetchServicesByAuth(val);
      if (res_svc.status === 200 && res_svc.data) {
        setServices(res_svc.data);
      } else {
        notFound();
      }
    };
    if (etape === 4) loadServices();
  }, [etape, formData.role]);

  return (
    <div className="max-w-md w-full mx-auto space-y-8 py-12 px-4 sm:px-6 lg:px-8 ">
      <div>
        <h2 className="mt-6 text-center text-3xl font-extrabold text-gray-900 dark:text-white">
          {etape === 1 && t("title")}
        </h2>
        <p className="mt-6 text-center text-xl text-gray-900 dark:text-white">
          {etape === 1 && t("step1Title")}
          {etape === 2 && t("step2Title")}
          {etape === 3 && t("step3Title")}
          {etape === 4 && t("step4Title")}
        </p>
      </div>
      <div className="flex items-center justify-between">
        {[1, 2, 3, 4].map((step) => (
          <div key={step} className="flex flex-col items-center">
            <div className={`h-8 w-8 rounded-full flex items-center justify-center ${etape >= step ? 'bg-emerald-500 text-white' : 'bg-gray-200 text-gray-500'}`}>
              {step}
            </div>
            <div className="text-xs mt-1">
              {step === 1 && t("stepLabels.step1")}
              {step === 2 && t("stepLabels.step2")}
              {step === 3 && t("stepLabels.step3")}
              {step === 4 && t("stepLabels.step4")}
            </div>
          </div>
        ))}
      </div>
      <form className="mt-8 space-y-6" onSubmit={submit}>
        {/* Étape 1: Choix du type d'utilisateur */}
        {etape === 1 && (
          <div className="space-y-6">
              <label className="block text-sm text-center font-medium text-gray-700 mt-10 mb-4 dark:text-white">
                {t("roleLabel")}
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
                        <p className="font-medium text-gray-900 dark:text-white">{t("roles.part.title")}</p>
                        <p className="text-gray-500 dark:text-white">{t("roles.part.description")}</p>
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
                        <p className="font-medium text-gray-900 dark:text-white">{t("roles.pro.title")}</p>
                        <p className="text-gray-500 dark:text-white">{t("roles.pro.description")}</p>
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
                <p>{t("roleWarning")}</p>
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
                    {t("personalInfo.firstName")}
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
                      {t("personalInfo.lastName")}
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
                    {t("personalInfo.companyName")}
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
                      {t("personalInfo.siret")}
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
                {t("personalInfo.email")}
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
                {t("personalInfo.phoneNumber")}
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
                {t("personalInfo.password")}
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
                {t("personalInfo.passwordConfirm")}
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
                    {t("detailsInfo.proPhotoTip")}
                </p>
                </div>
            )}
            <div className="mb-6">
              <PhotoUpload currentPhoto={null} onChange={change} label={t("detailsInfo.photoLabel")} error={errors.photoUser} />
            </div>
            {formData.role === 'pro' && (
              <div className="bg-emerald-50 p-2 rounded-md mt-10">
                <p className="text-xs text-center text-emerald-700 font-medium italic">{t("detailsInfo.proBioTip")}</p>
              </div>
            )}
            <div className="mb-6">
              <label htmlFor="bio" className="block text-sm font-medium text-gray-700 mb-1 dark:text-white">
                {t("detailsInfo.bioLabel")} {errors.bio && <Error message={errors.bio} />}
              </label>
              <textarea
                id="bio"
                name="bio"
                rows={3}
                value={formData.bio}
                onChange={change}
                placeholder={formData.role === 'pro' ? t("detailsInfo.bioPlaceholderPro") : t("detailsInfo.bioPlaceholderPart") }
                className={`mt-4 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-emerald-500 bg-white text-black dark:bg-gray-700 dark:text-white placeholder:text-sm ${errors.bio && "border-red-500 dark:border-red-900"}`}
              />
            </div>
            <div className="bg-emerald-50 p-2 rounded-md mt-10">
              <p className="text-xs text-center text-emerald-700 font-medium italic">
                {formData.role === 'pro' ? t("detailsInfo.addressTipPro") : t("detailsInfo.addressTipPart") }
              </p>
            </div>    
            <InputAddress value={formData} onChange={updateAddress} required={true} error={errors.address} suiteError={errors.suite} label={t("detailsInfo.addressLabel")} placeholder={t("detailsInfo.addressPlaceholder")} suiteLabel={t("detailsInfo.suiteLabel")} suitePlaceholder={t("detailsInfo.suitePlaceholder")} />
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
                    {t("services.wantsPresta")}
                  </label>
                </div>
                <p className="mt-1 text-xs text-gray-500">{t("services.wantsPrestaTip")}</p>
                  {formData.wantsPresta && (
                    <div className="mt-4">
                      <Dropdown
                        label={t("services.serviceType")}
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
                  label={t("services.serviceType")}
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
                {t.rich("legal.acceptCgu", { link: (chunks) => (<a href="../legal/cgu" target="_blank" rel="noopener noreferrer" className="text-indigo-600 hover:text-indigo-500">{chunks}</a>)})}
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
                {t.rich("legal.acceptCgv", { link: (chunks) => (<a href="../legal/cgv" target="_blank" rel="noopener noreferrer" className="text-indigo-600 hover:text-indigo-500">{chunks}</a>)})}
              </label>
            </div>
            {errors.acceptCgv && <Error message={errors.acceptCgv} />}
            {/* juste pour dev */}
            {/* {errors.submit && <Error message={errors.submit} />} */}
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
                {t("navigation.next")}
              </button>
              <p className="text-center text-sm mt-3">
                <Link href="../auth" className="text-emerald-500 hover:underline">{t("navigation.backToLogin")}</Link>
              </p>
            </div>
          )}
          {etape > 1 && (
            <button
              type="button"
              onClick={previous}
              className="bg-white py-2 px-4 border border-gray-300 rounded-md shadow-sm text-sm font-medium text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
            >
              {t("navigation.previous")}
            </button>
          )}
          {(etape === 2 || etape === 3) && (
            <button
              type="button"
              onClick={next}
              className="ml-auto bg-emerald-600 py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white hover:bg-emerald-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-500"
            >
              {t("navigation.next")}
            </button>
          )}
          {etape === 4 && (
            <button
              type="submit"
              disabled={isSubmitting}
              className="ml-auto bg-emerald-600 py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white hover:bg-emerald-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
            >
              {isSubmitting ? t("navigation.submitting") : t("navigation.finish")}
            </button>
          )}
        </div>
      </form>
    </div>
  );
}