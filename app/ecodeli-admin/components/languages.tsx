"use client";

import { useState } from "react";
import { usePathname, useRouter } from "next/navigation";
import { LanguageIcon } from "@heroicons/react/24/outline";
import clsx from "clsx";

const languages = [
  { code: "fr", label: "Français" },
  { code: "en", label: "English" },
  { code: "es", label: "Español" },
  { code: "de", label: "Deutsch" },
];

export default function LanguageSelector() {
  const [isOpen, setIsOpen] = useState(false);
  const pathname = usePathname();
  const router = useRouter();

  // Détecter la langue actuelle depuis l'URL
  const currentLang = pathname.split("/")[1]; 

  const changeLanguage = (langCode: string) => {
    setIsOpen(false);
    if (currentLang !== langCode) {
      const newPath = `/${langCode}${pathname.substring(currentLang.length + 1)}`;
      router.push(newPath);
    }
  };

  return (
    <div className="relative">
      {/* Bouton principal */}
      <button
        onClick={() => setIsOpen(!isOpen)}
        className="p-2 rounded-lg hover:bg-gray-300 dark:hover:bg-gray-600"
      >
        <LanguageIcon className="w-6 h-6 text-gray-900 dark:text-white" />
      </button>

      {/* Dropdown */}
      {isOpen && (
        <div className="absolute z-50 right-0 mt-2 w-36 bg-white dark:bg-gray-800 shadow-lg rounded-md overflow-hidden border border-gray-300 dark:border-gray-600">
          {languages.map((lang) => (
            <button
              key={lang.code}
              onClick={() => changeLanguage(lang.code)}
              className={clsx(
                "w-full px-4 py-2 text-left text-gray-900 dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700",
                { "font-semibold bg-gray-100 dark:bg-gray-700": currentLang === lang.code }
              )}
            >
              {lang.label}
            </button>
          ))}
        </div>
      )}
    </div>
  );
}