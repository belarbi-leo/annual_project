"use client";

import { useState, useEffect, useRef } from "react";
import { usePathname, useRouter } from "next/navigation";
import { clsx } from "clsx";
import { LanguageIcon } from "@heroicons/react/24/outline";
import { fetchAllLanguages } from "@/lib/languages/fetch-all-languages";
import { Language } from "@/lib/types";

export default function LanguageSelector() {
  const [isOpen, setIsOpen] = useState(false);
  const [languages, setLanguages] = useState<Language[]>([]);

  const pathname = usePathname();
  const router = useRouter();
  const dropdownRef = useRef<HTMLDivElement>(null);

  const currentLang = pathname.split("/")[1];

  useEffect(() => {
    fetchAllLanguages().then(setLanguages);

    const handleClickOutside = (event: MouseEvent) => {
      if (dropdownRef.current && !dropdownRef.current.contains(event.target as Node)) {
        setIsOpen(false);
      }
    };

    document.addEventListener("mousedown", handleClickOutside);
    return () => document.removeEventListener("mousedown", handleClickOutside);
  }, []);

  const changeLanguage = (langIso: string) => {
    setIsOpen(false);
    if (currentLang !== langIso) {
      const newPath = `/${langIso}${pathname.substring(currentLang.length + 1)}`;
      router.push(newPath);
    }
  };

  return (
    <div className="relative">
      <button onClick={() => setIsOpen(!isOpen)} className="p-2 rounded-lg hover:bg-gray-300 dark:hover:bg-gray-600">
        <LanguageIcon className="w-6 h-6 text-gray-900 dark:text-white" />
      </button>

      {isOpen && (
        <div ref={dropdownRef} className="absolute z-50 right-0 mt-2 w-36 bg-white dark:bg-gray-800 shadow-lg rounded-md overflow-hidden border border-gray-300 dark:border-gray-600" >
          {languages.map((lang) => (
            <button
              key={lang.id_language}
              onClick={() => changeLanguage(lang.iso)}
              className={clsx(
                "w-full px-4 py-2 text-left text-gray-900 dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700",
                { "font-semibold bg-gray-100 dark:bg-gray-700": currentLang.toLowerCase() === lang.iso.toLowerCase() }
              )}
            >
              {lang.language}
            </button>
          ))}
        </div>
      )}
    </div>
  );
}
