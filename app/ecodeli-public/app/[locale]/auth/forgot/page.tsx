"use client";

import { useState } from "react";
import { useTranslations } from "next-intl";
import Link from "next/link";
import Header from "@/components/headerNotCo";
import Background from "@/components/backgroundNotCo";

export default function Forgot() {
  const t = useTranslations("Auth.Forgot");
  const [email, setEmail] = useState("");
  const [errors, setErrors] = useState<Record<string, string>>({});
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [submitted, setSubmitted] = useState(false);

  const validate = () => {
    let erreurs: Record<string, string> = {};
    
    if (!email.trim()) {
      erreurs.email = t("errors.emailRequired");
    } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
      erreurs.email = t("errors.emailInvalid");
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
        console.log("Email de réinitialisation envoyé à:", email);
        setSubmitted(true);
      } catch (error) {
        setErrors({ form: t("errors.sendError") });
      }
    }
    
    setIsSubmitting(false);
  };

  return (
    <main className=" min-h-screen flex items-center justify-center px-6">
      <div className="w-full max-w-md p-8 space-y-6 rounded-2xl shadow-lg bg-gray-100 text-gray-900 dark:bg-gray-800 dark:text-white"> 
        {errors.form && (
          <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative dark:bg-red-900 dark:border-red-800 dark:text-red-100" role="alert">
            <span className="block sm:inline">{errors.form}</span>
          </div>
        )}
        {!submitted ? (
          <>
            <form className="space-y-4" onSubmit={handleSubmit}>
              <div>
                <label htmlFor="email" className="block text-sm font-medium text-gray-700 dark:text-white">{t("email")}</label>
                <input
                  type="email"
                  id="email"
                  className={`mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-emerald-500 bg-white text-black dark:bg-gray-700 dark:text-white ${errors.email && "border-red-500 dark:border-red-900"}`}
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                />
                {errors.email && <p className="mt-1 text-sm text-red-600 dark:text-red-400">{errors.email}</p>}
              </div>
              <button 
                type="submit" 
                className="w-full ml-auto bg-emerald-600 py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white hover:bg-emerald-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
                disabled={isSubmitting}
              >
                {isSubmitting ? t("submitting") : t("submit")}
              </button>
            </form>
            <p className="text-center text-sm m-0">
              {t("noAccount")}<Link href="/auth/sign" className="text-emerald-500 hover:underline">{t("signin")}</Link>
            </p>
            <p className="text-center text-sm mt-1">
              <Link href="/auth" className="text-emerald-500 hover:underline">{t("backToLogin")}</Link>
            </p>
          </>
        ) : (
          <div className="space-y-4">
            <div className="flex justify-center">
              <div className="bg-green-100 dark:bg-green-900 p-3 rounded-full">
                <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6 text-green-600 dark:text-green-300" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M5 13l4 4L19 7" />
                </svg>
              </div>
            </div>
            <p className="block text-sm text-center font-semibold text-gray-700 dark:text-white">
              {t("successMessage", { email })}
            </p>
            <button 
              onClick={() => setSubmitted(false)} 
              className="w-full ml-auto bg-emerald-600 py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white hover:bg-emerald-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
            >
              {t("tryAnotherEmail")}
            </button>
            <p className="text-center text-sm">
              <Link href="/auth" className="text-emerald-500 hover:underline">{t("backToLogin")}</Link>
            </p>
          </div>
        )}
      </div>
    </main>
  );
}