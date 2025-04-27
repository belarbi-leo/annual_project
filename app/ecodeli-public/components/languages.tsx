"use client";

import { useState, useEffect, useRef } from "react";
import { usePathname, useRouter } from "next/navigation";
import { clsx } from "clsx";
import type { Languages } from "@/lib/types";
import { fetchAllLanguages } from "@/lib/languages/fetchAllLanguages";
import { LanguageIcon } from "@heroicons/react/24/outline";
import { notFound } from 'next/navigation';

export default function LanguageSelector() {
  const [isOpen, setIsOpen] = useState(false);
  const [languages, setLanguages] = useState<Languages[]>([]);
  const [error, setError] = useState<string | null>(null);

  const pathname = usePathname();
  const router = useRouter();
  const dropdownRef = useRef<HTMLDivElement>(null);
  const buttonRef = useRef<HTMLButtonElement>(null);

  const currentLang = pathname.split("/")[1];

  useEffect(() => {
    const fetchLanguages = async () => {
      try {
        const response = await fetchAllLanguages();

        // Vérifier le code de réponse
        if (!response || response.status !== 201) {
          const errorMsg = response?.data?.message || "Erreur lors du chargement des langues";
          setError(errorMsg);
          // Rediriger vers la page 404 avec le message d'erreur
          router.push(`/404?error=${encodeURIComponent(errorMsg)}`);
          return;
        }
        
        setLanguages(response.data || []);
      } catch (err) {
        const errorMsg = "Erreur serveur, veuillez réessayer plus tard.";
        setError(errorMsg);
        // Rediriger vers la page 404 avec le message d'erreur
        router.push(`/404?error=${encodeURIComponent(errorMsg)}`);
      }
    };

    fetchLanguages();

    const handleClickOutside = (event: MouseEvent) => {
      const target = event.target as Node;
      
      if (
        dropdownRef.current && !dropdownRef.current.contains(target) &&
        buttonRef.current && !buttonRef.current.contains(target)
      ) {
        setIsOpen(false);
      }
    };
    
    document.addEventListener("mousedown", handleClickOutside);
    return () => document.removeEventListener("mousedown", handleClickOutside);
  }, [router]);

  const changeLanguage = (langIso: string) => {
    setIsOpen(false);
    if (currentLang !== langIso) {
      const newPath = `/${langIso}${pathname.substring(currentLang.length + 1)}`;
      router.push(newPath);
    }
  };

  // Si une erreur a été définie, ne pas rendre le composant
  if (error) return null;

  return (
    <div className="relative">
      <button
        ref={buttonRef}
        onClick={() => setIsOpen(prev => !prev)}
        className="p-2 rounded-lg hover:bg-gray-300 dark:hover:bg-gray-600"
      >
        <LanguageIcon className="w-6 h-6 text-gray-900 dark:text-white" />
      </button>
      
      {isOpen && (
        <div
          ref={dropdownRef}
          className="absolute z-50 right-0 mt-2 w-36 bg-white dark:bg-gray-800 shadow-lg rounded-md overflow-hidden border border-gray-300 dark:border-gray-600"
        >
          {languages.map((lang) => (
            <button
              key={lang.idLanguage}
              onClick={() => changeLanguage(lang.iso)}
              className={clsx(
                "w-full px-4 py-2 text-left text-gray-900 dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700",
                { "font-semibold bg-gray-100 dark:bg-gray-700": currentLang.toLowerCase() === lang.iso.toLowerCase() }
              )}
            >
              {lang.name}
            </button>
          ))}
        </div>
      )}
    </div>
  );
}