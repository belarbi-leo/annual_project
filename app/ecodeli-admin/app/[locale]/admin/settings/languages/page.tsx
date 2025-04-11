'use client';

import { useState, useEffect } from 'react';
import Link from "next/link";
import { usePathname } from 'next/navigation';
import { Menu, MenuButton, MenuItem, MenuItems } from '@headlessui/react';
import { ChevronDownIcon } from '@heroicons/react/24/solid';
import { fetchAllLanguages } from '@/lib/languages/fetchAllLanguages';
import { useTranslations } from "next-intl";
import SearchBar from "@/components/searchbar";

type TranslationValue = string | { [key: string]: TranslationValue };

const initialTranslations: Record<string, TranslationValue> = {
  "LoginPage": {
    "email": "E-mail",
    "password": "Mot de Passe",
    "login": "Connexion"
  },
  "Admin": {
    "Menu": {
      "dashboard": "Tableau de bord",
      "userManagement": "Gestion des utilisateurs",
      "disputesManagement": "Gestion des litiges",
    },
    "UserManagement": {
      "title": "Gestion des Utilisateurs",
      "privateUser": "Clients particuliers",
    }
  }
};

export default function LanguageSettingsPage() {
  const t = useTranslations("Admin.WebsiteSettings.ManageLanguages");
  const pathname = usePathname();
  const currentLang = pathname.split('/')[1];
  const [languages, setLanguages] = useState<{ iso: string; label: string }[]>([]);
  const [selectedLang, setSelectedLang] = useState(currentLang);
  const [openKeys, setOpenKeys] = useState<string[]>([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [editedTranslations, setEditedTranslations] = useState<Record<string, TranslationValue>>(initialTranslations);

  useEffect(() => {
    async function loadLanguages() {
      const langs = await fetchAllLanguages();
      setLanguages(langs.map((lang) => ({
        iso: lang.iso.toLowerCase(),
        label: lang.langue
      })));
    }
    loadLanguages();
  }, []);

  const toggle = (key: string) => {
    setOpenKeys((prev) =>
      prev.includes(key) ? prev.filter((k) => k !== key) : [...prev, key]
    );
  };

  const handleChange = (path: string, newValue: string) => {
    const keys = path.split('.');
    setEditedTranslations((prev: Record<string, TranslationValue>) => {
      const updated = { ...prev };
      let pointer: any = updated;
      for (let i = 0; i < keys.length - 1; i++) {
        if (!pointer[keys[i]]) pointer[keys[i]] = {};
        pointer = pointer[keys[i]];
      }
      pointer[keys[keys.length - 1]] = newValue;
      return updated;
    });
  };

  const renderAccordion = (obj: TranslationValue, parentKey = ''): React.ReactNode => {
    const children = Object.entries(obj).flatMap(([key, value]) => {
      const fullKey = parentKey ? `${parentKey}.${key}` : key;
      const isOpen = openKeys.includes(fullKey);
      const isObject = typeof value === 'object' && value !== null;

      const matchesSearch = fullKey.toLowerCase().includes(searchTerm.toLowerCase());

      if (isObject) {
        const nested = renderAccordion(value, fullKey);
        const hasNested = Array.isArray(nested) && nested.length > 0;

        if (!hasNested && searchTerm) return [];

        return (
          <div key={fullKey} className="rounded-lg my-2">
            <button
              onClick={() => toggle(fullKey)}
              className="w-full px-4 py-3 flex justify-between items-center bg-white dark:bg-gray-800 hover:bg-gray-200 dark:hover:bg-gray-700 rounded-md text-left"
            >
              <span className="text-md font-medium">{key}</span>
              <span className="text-xl">{isOpen ? '−' : '+'}</span>
            </button>
            {isOpen && <div className="pl-4 mt-2">{nested}</div>}
          </div>
        );
      }

      if (!matchesSearch && searchTerm) return [];

      return (
        <div key={fullKey} className="flex items-center gap-4 px-4 py-2 text-sm">
          <label className="font-medium min-w-[120px]">{key}:</label>
          <input
            type="text"
            value={value ?? ''}
            onChange={(e) => handleChange(fullKey, e.target.value)}
            className="flex-1 px-2 py-1 rounded border border-gray-300 dark:border-gray-700 bg-white dark:bg-gray-800 text-gray-900 dark:text-white"
          />
        </div>
      );
    });

    if (children.length === 0 && searchTerm) {
      return (
        <div className="italic text-gray-500 px-4 py-2">
          {t('noMatchFound')}
        </div>
      );
    }

    return children;
  };

  return (
    <div className="space-y-6 min-h-screen">
      <h2 className="text-2xl font-semibold">{t('languagesManagement')}</h2>

      {/* Dropdown de sélection de langue */}
      <div className="inline-block w-full text-left">
        <Menu>
          <MenuButton className="w-full px-4 py-2 bg-gray-200 dark:bg-gray-800 text-gray-900 dark:text-white border border-gray-300 dark:border-gray-700 rounded-md shadow-sm flex justify-between items-center hover:bg-gray-300 dark:hover:bg-gray-700">
            <span>
              {
                languages.find((lang) => lang.iso === selectedLang)?.label
                || t('selectInput')
              }
            </span>
            <ChevronDownIcon className="w-5 h-5" />
          </MenuButton>
          <MenuItems className="absolute z-50 mt-1 rounded-md bg-white dark:bg-gray-800 shadow-lg max-h-60 overflow-auto">
            {languages.map((lang) => (
              <MenuItem key={lang.iso}>
                {({ active }) => (
                  <button
                    onClick={() => setSelectedLang(lang.iso)}
                    className={`w-full text-left px-4 py-2 ${
                      active ? 'bg-blue-100 dark:bg-gray-700' : ''
                    }`}
                  >
                    {lang.label}
                  </button>
                )}
              </MenuItem>
            ))}
          </MenuItems>
        </Menu>
      </div>

      {/* Séparateur */}
      <div className="w-full h-[2px] bg-gray-300 dark:bg-gray-600"></div>

      {/* SearchBar intégrée */}
      <SearchBar
        value={searchTerm}
        onChange={setSearchTerm}
        placeholder={t('searchKey')}
      />

      {/* Contenu traduisible */}
      <div>{renderAccordion(editedTranslations)}</div>

      {/* Actions */}
      <div className="flex gap-4">
        <button
          onClick={() => console.log(editedTranslations)}
          className="px-6 py-2 bg-green-600 text-white rounded hover:bg-green-700"
        >
          {t('save')}
        </button>
        <Link
          href="/admin/settings"
          className="px-6 py-2 bg-gray-500 text-white rounded-md hover:bg-gray-600"
        >
          {t('back')}
        </Link>
      </div>
    </div>
  );
}