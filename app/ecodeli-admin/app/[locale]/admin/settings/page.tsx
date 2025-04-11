"use client";

import Link from "next/link";
import { useTranslations } from "next-intl";
import { LanguageIcon } from "@heroicons/react/24/outline";

export default function AdminSettingsPage() {
  const t = useTranslations("Admin.WebsiteSettings");

  return (
    <div className="space-y-6 min-h-screen">
      {/* Titre */}
      <div className="text-center max-w-3xl mx-auto">
        <h2 className="text-3xl font-bold text-gray-800 my-5 dark:text-white">
          {t("websiteSettings")}
        </h2>
      </div>

      {/* Séparateur */}
      <div className="w-full h-[2px] bg-gray-300 dark:bg-gray-600"></div>

      {/* Bouton pour accéder aux paramètres des langues */}
      <div className="flex justify-center">
        <Link href="/admin/settings/languages">
          <button className="px-6 py-3 inline-flex text-lg font-semibold bg-green-600 text-white rounded-lg shadow-md hover:bg-green-700 transition-all">
            <LanguageIcon className="w-6 h-6 text-white" />
            {t("manageLanguages")}
          </button>
        </Link>
      </div>
    </div>
  );
}