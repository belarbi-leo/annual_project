"use client";

import { useState } from "react";
import { useTranslations } from "next-intl";
import Link from "next/link";
import Error from "@/components/ui/error";

export default function Contact() {
  const t = useTranslations("Contact");
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [subject, setSubject] = useState("");
  const [message, setMessage] = useState("");
  const [errors, setErrors] = useState<Record<string, string>>({});
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [success, setSuccess] = useState(false);

  const validate = () => {
    let erreurs: Record<string, string> = {};
    
    if (!name.trim()) {
      erreurs.name = t("errors.nameRequired");
    }
    
    if (!email.trim()) {
      erreurs.email = t("errors.emailRequired");
    } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
      erreurs.email = t("errors.emailInvalid");
    }
    
    if (!subject.trim()) {
      erreurs.subject = t("errors.subjectRequired");
    }
    
    if (!message.trim()) {
      erreurs.message = t("errors.messageRequired");
    }
    
    setErrors(erreurs);
    return Object.keys(erreurs).length === 0;
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setIsSubmitting(true);
    
    if (validate()) {
      try {
        // for dev
        console.log("Message envoyé:", { name, email, subject, message });
        
        // Simuler l'envoi réussi
        setTimeout(() => {
          setSuccess(true);
          setName("");
          setEmail("");
          setSubject("");
          setMessage("");
        }, 1000);
      } catch (error) {
        setErrors({ form: t("errors.sendingError") });
      }
    }
    
    setIsSubmitting(false);
  };

  return (
    <div className="min-h-screen flex items-center justify-center px-4 sm:px-6 py-8 sm:py-12 mt-15">
      <div className="w-full max-w-md lg:max-w-lg p-4 sm:p-6 md:p-8 space-y-4 sm:space-y-6 rounded-lg sm:rounded-2xl shadow-md sm:shadow-lg bg-gray-100 text-gray-900 dark:bg-gray-800 dark:text-white">
        <h1 className="text-xl sm:text-2xl md:text-3xl font-semibold text-center">{t("title")}</h1>
        
        {success && (
          <div className="bg-green-100 border border-green-400 text-green-700 px-3 sm:px-4 py-2 sm:py-3 rounded relative dark:bg-green-900 dark:border-green-800 dark:text-green-100" role="alert">
            <span className="block sm:inline">{t("successMessage")}</span>
          </div>
        )}
        
        {errors.form && (
          <div className="bg-red-100 border border-red-400 text-red-700 px-3 sm:px-4 py-2 sm:py-3 rounded relative dark:bg-red-900 dark:border-red-800 dark:text-red-100" role="alert">
            <span className="block sm:inline">{errors.form}</span>
          </div>
        )}
        
        <form className="space-y-3 sm:space-y-4" onSubmit={handleSubmit}>
          <div>
            <label htmlFor="name" className="block text-sm font-medium text-gray-700 dark:text-gray-200">{t("name")}</label>
            <input
              type="text"
              id="name"
              className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-2 focus:ring-emerald-500 focus:border-emerald-500 bg-white text-black dark:bg-gray-700 dark:text-white dark:border-gray-600 ${errors.name ? "border-red-500 dark:border-red-900" : ""}`}
              value={name}
              onChange={(e) => setName(e.target.value)}
            />
            {errors.name && <Error message={errors.name} />}
          </div>
          
          <div>
            <label htmlFor="email" className="block text-sm font-medium text-gray-700 dark:text-gray-200">{t("email")}</label>
            <input
              type="email"
              id="email"
              className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-2 focus:ring-emerald-500 focus:border-emerald-500 bg-white text-black dark:bg-gray-700 dark:text-white dark:border-gray-600 ${errors.email ? "border-red-500 dark:border-red-900" : ""}`}
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
            {errors.email && <Error message={errors.email} />}
          </div>
          
          <div>
            <label htmlFor="subject" className="block text-sm font-medium text-gray-700 dark:text-gray-200">{t("subject")}</label>
            <input
              type="text"
              id="subject"
              className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-2 focus:ring-emerald-500 focus:border-emerald-500 bg-white text-black dark:bg-gray-700 dark:text-white dark:border-gray-600 ${errors.subject ? "border-red-500 dark:border-red-900" : ""}`}
              value={subject}
              onChange={(e) => setSubject(e.target.value)}
            />
            {errors.subject && <Error message={errors.subject} />}
          </div>
          
          <div>
            <label htmlFor="message" className="block text-sm font-medium text-gray-700 dark:text-gray-200">{t("message")}</label>
            <textarea
              id="message"
              rows={4}
              className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-2 focus:ring-emerald-500 focus:border-emerald-500 bg-white text-black dark:bg-gray-700 dark:text-white dark:border-gray-600 ${errors.message ? "border-red-500 dark:border-red-900" : ""}`}
              value={message}
              onChange={(e) => setMessage(e.target.value)}
            />
            {errors.message && <Error message={errors.message} />}
          </div>
          
          <button 
            type="submit" 
            className="w-full bg-emerald-600 py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white hover:bg-emerald-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-500 transition-colors duration-200"
            disabled={isSubmitting}
          >
            {isSubmitting ? t("sending") : t("send")}
          </button>
        </form>
        
        <p className="text-center text-sm">
          <Link href="./" className="text-emerald-500 hover:text-emerald-600 hover:underline transition-colors duration-200">{t("returnHome")}</Link>
        </p>
      </div>
    </div>
  );
}