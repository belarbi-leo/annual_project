"use client";

import { useState, useEffect } from "react";
import { useTranslations } from "next-intl";
import Image from "next/image";
import Link from "next/link";
import { ArrowLeftCircle, Home, RefreshCw } from "lucide-react";

export default function NotFound() {
  const [animate, setAnimate] = useState(false);
  const [rotation, setRotation] = useState(0);

  // Uncomment this if you have translations set up
  // const t = useTranslations("Error");

  useEffect(() => {
    setAnimate(true);
    
    // Create a gentle rotation animation for the icon
    const interval = setInterval(() => {
      setRotation(prev => (prev + 1) % 360);
    }, 50);
    
    return () => clearInterval(interval);
  }, []);

  return (
    <main className="flex min-h-screen flex-col items-center justify-center py-12 px-4 sm:px-6 lg:px-8 bg-gradient-to-b from-white to-emerald-50 dark:from-gray-900 dark:to-gray-800">
      <div className="w-full max-w-4xl mx-auto text-center">
        <div className={`transform transition-all duration-1000 ${animate ? 'scale-100 opacity-100' : 'scale-90 opacity-0'}`}>
          <div className="relative mx-auto mb-8 w-40 h-40">
            <Image
              src="/favicon.ico"
              alt="Logo EcoDeli"
              height={160}
              width={160}
              className="transition-all duration-500"
              style={{ transform: `rotate(${rotation}deg)` }}
            />
          </div>
          
          <h1 className="mb-4 text-6xl font-bold tracking-tight text-emerald-500 dark:text-emerald-300 [font-family:var(--font-title)]">
            {/* {t("title") || "Page introuvable"} */}
            Page introuvable
          </h1>
          
          <div className="text-9xl font-bold text-emerald-200 dark:text-emerald-800 my-4">
            404
          </div>
          
          <p className="mt-6 text-xl font-medium text-gray-600 dark:text-gray-300 max-w-2xl mx-auto">
            {/* {t("description") || "La page que vous recherchez n'existe pas ou a été déplacée."} */}
            La page que vous recherchez n'existe pas ou a été déplacée.
          </p>
          
          <div className="mt-12 flex flex-col sm:flex-row justify-center items-center gap-4 sm:gap-x-6">
            <Link
              href="/"
              className="w-full sm:w-auto flex items-center justify-center rounded-lg bg-emerald-600 px-5 py-3 text-base font-semibold text-white shadow-lg hover:bg-emerald-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-500 transition-all duration-300 hover:scale-105"
            >
              <Home className="mr-2 h-5 w-5" />
              {/* {t("home") || "Retour à l'accueil"} */}
              Retour à l'accueil
            </Link>
            <button
              onClick={() => window.history.back()}
              className="w-full sm:w-auto flex items-center justify-center rounded-lg border border-emerald-200 dark:border-emerald-800 px-5 py-3 text-base font-semibold text-gray-900 dark:text-white hover:bg-emerald-50 dark:hover:bg-emerald-900/30 transition-all duration-300"
            >
              <ArrowLeftCircle className="mr-2 h-5 w-5" />
              {/* {t("back") || "Page précédente"} */}
              Page précédente
            </button>
            <button
              onClick={() => window.location.reload()}
              className="w-full sm:w-auto flex items-center justify-center rounded-lg border border-emerald-200 dark:border-emerald-800 px-5 py-3 text-base font-semibold text-gray-900 dark:text-white hover:bg-emerald-50 dark:hover:bg-emerald-900/30 transition-all duration-300"
            >
              <RefreshCw className="mr-2 h-5 w-5" />
              {/* {t("refresh") || "Rafraîchir"} */}
              Rafraîchir
            </button>
          </div>
        </div>
      </div>
    </main>
  );
}