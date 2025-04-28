// components/languages.tsx
"use client";

import { useState, useRef, useEffect } from "react";
import { GlobeAltIcon, ChevronDownIcon } from "@heroicons/react/24/outline";
import Image from "next/image";

export default function LanguageSelector() {
  const [isOpen, setIsOpen] = useState(false);
  const [selectedLanguage, setSelectedLanguage] = useState({ code: "fr", name: "Français", flag: "/flags/fr.png" });
  const menuRef = useRef<HTMLDivElement>(null);

  const languages = [
    { code: "fr", name: "Français", flag: "/flags/fr.png" },
    { code: "en", name: "English", flag: "/flags/en.png" },
    { code: "es", name: "Español", flag: "/flags/es.png" },
    { code: "de", name: "Deutsch", flag: "/flags/de.png" },
  ];

  useEffect(() => {
    const handleClickOutside = (event: MouseEvent) => {
      if (menuRef.current && !menuRef.current.contains(event.target as Node)) {
        setIsOpen(false);
      }
    };

    document.addEventListener("mousedown", handleClickOutside);
    return () => document.removeEventListener("mousedown", handleClickOutside);
  }, []);

  const changeLanguage = (language: typeof selectedLanguage) => {
    setSelectedLanguage(language);
    setIsOpen(false);
    // Ici vous pourriez implémenter la logique pour changer la langue dans votre application
  };

  return (
    <div className="relative" ref={menuRef}>
      <button
        onClick={() => setIsOpen(!isOpen)}
        className="flex items-center space-x-2 p-2 rounded-lg hover:bg-gray-200 dark:hover:bg-gray-700"
      >
        <div className="relative w-6 h-6 rounded-full overflow-hidden">
          <div className="absolute inset-0 flex items-center justify-center bg-gray-200 dark:bg-gray-700">
            <GlobeAltIcon className="w-4 h-4 text-gray-600 dark:text-gray-300" />
          </div>
        </div>
        <span className="hidden sm:inline text-sm text-gray-700 dark:text-gray-300">{selectedLanguage.name}</span>
        <ChevronDownIcon className="w-4 h-4 text-gray-600 dark:text-gray-300" />
      </button>

      {isOpen && (
        <div className="absolute right-0 mt-2 w-48 bg-white dark:bg-gray-800 rounded-lg shadow-lg py-1 z-10">
          {languages.map((language) => (
            <button
              key={language.code}
              className="flex items-center w-full px-4 py-2 text-left text-sm hover:bg-gray-100 dark:hover:bg-gray-700"
              onClick={() => changeLanguage(language)}
            >
              <div className="w-6 h-6 rounded-full flex items-center justify-center bg-gray-200 dark:bg-gray-700 mr-3">
                <GlobeAltIcon className="w-4 h-4 text-gray-600 dark:text-gray-300" />
              </div>
              <span className={`${selectedLanguage.code === language.code ? 'font-medium' : ''}`}>
                {language.name}
              </span>
              {selectedLanguage.code === language.code && (
                <span className="ml-auto text-blue-500">✓</span>
              )}
            </button>
          ))}
        </div>
      )}
    </div>
  );
}