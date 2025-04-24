"use client";

import { useState } from "react";
import { useTranslations } from "next-intl";
import Link from "next/link";
import Header from "@/components/header";
import Background from "@/components/background";

export default function Auth() {
  const t = useTranslations("Auth");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errors, setErrors] = useState<Record<string, string>>({});
  const [isSubmitting, setIsSubmitting] = useState(false);

  const validate = () => {
    let erreurs: Record<string, string> = {};
    
    if (!email.trim()) {
      erreurs.email = t("errors.emailRequired");
    } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
      erreurs.email = t("errors.emailInvalid");
    }
    
    if (!password) {
      erreurs.password = t("errors.passwordRequired");
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
        console.log("Tentative de connexion avec:", email, password);
      } catch (error) {
        setErrors({ form: t("errors.connectionError") });
      }
    }
    
    setIsSubmitting(false);
  };

  return (
    <div className="min-h-screen flex items-center justify-center px-6 overflow-hidden relative">
      <Background />
      <Header />
      
      <div className="w-full max-w-md p-8 space-y-6 rounded-2xl shadow-lg bg-gray-100 text-gray-900 dark:bg-gray-800 dark:text-white">
        {errors.form && (
          <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative dark:bg-red-900 dark:border-red-800 dark:text-red-100" role="alert">
            <span className="block sm:inline">{errors.form}</span>
          </div>
        )}
        
        <form className="space-y-4" onSubmit={handleSubmit}>
          <div>
            <label htmlFor="email" className="block text-sm font-medium">{t("email")}</label>
            <input
              type="email"
              id="email"
              className={`mt-1 block w-full px-4 py-2 border rounded-md focus:outline-emerald-500 bg-white text-black dark:bg-gray-700 dark:text-white ${errors.email ? "border-red-500 dark:border-red-900" : "border-gray-300 dark:border-gray-600"}`}
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
            {errors.email && <p className="mt-1 text-sm text-red-600 dark:text-red-400">{errors.email}</p>}
          </div>
          <div>
            <label htmlFor="password" className="block text-sm font-medium">{t("password")}</label>
            <input
              type="password"
              id="password"
              className={`mt-1 block w-full px-4 py-2 border rounded-md focus:outline-emerald-500 bg-white text-black dark:bg-gray-700 dark:text-white ${errors.password ? "border-red-500 dark:border-red-900" : "border-gray-300 dark:border-gray-600"}`}
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            {errors.password && <p className="mt-1 text-sm text-red-600 dark:text-red-400">{errors.password}</p>}
          </div>
          <button 
            type="submit" 
            className="w-full text-center rounded-md bg-emerald-600 px-3.5 py-2.5 text-sm font-semibold text-white shadow-xs hover:bg-emerald-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-500 disabled:opacity-50"
            disabled={isSubmitting}
          >
            {isSubmitting ? t("submitting") : t("submit") }
          </button>
        </form>
        <p className="text-center text-sm m-0">
          {t("noAccount")}<Link href="/auth/sign" className="text-emerald-500 hover:underline">{t("signin")}</Link>
        </p>
        <p className="text-center text-sm mt-1">
          {t("forgot")}<Link href="/auth/forgot" className="text-emerald-500 hover:underline">{t("reset")}</Link>
        </p>
      </div>
    </div>
  );
}