"use client";
import { useState, useEffect, useRef } from "react";
import { notFound, usePathname, useRouter } from "next/navigation";
import { clsx } from "clsx";
import type { Languages } from "@/lib/types";
import { fetchAllLanguages } from "@/lib/languages/fetchAllLanguages";
import { LanguageIcon } from "@heroicons/react/24/outline";

export default function LanguageSelector() {
  const router = useRouter();
  const pathname = usePathname();
  const currentLang = pathname.split("/")[1];
  const [isOpen, setIsOpen] = useState(false);
  const [languages, setLanguages] = useState<Languages[]>([]);
  const dropdownRef = useRef<HTMLDivElement>(null);
  const buttonRef = useRef<HTMLButtonElement>(null);

  useEffect(() => {
    const fetchLanguages = async () => {
      try {
        const res = await fetchAllLanguages();
        if (res.status !== 200) notFound();
        setLanguages(res.data || []);
      } catch (error) {
        notFound();
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