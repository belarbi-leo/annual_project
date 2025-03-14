"use client";

import { useState, useEffect } from "react";
import Image from "next/image";
import NavLinks from "../ui/dashboard/nav-links";
import { ChevronLeftIcon, ChevronRightIcon } from "@heroicons/react/24/outline";
import clsx from "clsx";

export default function DashboardLayout({ children }: { children: React.ReactNode }) {
  const [isCollapsed, setIsCollapsed] = useState<boolean | null>(null); // Utilisation de `null` comme valeur par défaut

  // Détection automatique de la taille de l’écran uniquement côté client
  useEffect(() => {
    const handleResize = () => setIsCollapsed(window.innerWidth < 768);
    handleResize(); // Vérifie la taille de l'écran au moment du montage
    window.addEventListener("resize", handleResize);
    return () => window.removeEventListener("resize", handleResize);
  }, []);

  // Si `isCollapsed` est `null`, on rend une valeur par défaut avant d'être défini (en attendant le client)
  if (isCollapsed === null) {
    return true;
  }

  return (
    <div className="flex min-h-screen bg-gray-100 dark:bg-gray-900">
      {/* Sidebar */}
      <aside className={clsx(
        "bg-white dark:bg-gray-800 sticky top-0 h-screen p-5 shadow-md transition-all duration-300 flex flex-col",
        isCollapsed ? "w-16 items-center" : "w-64"
      )}>
        {/* Logo et bouton */}
        <div className="flex items-center justify-between">
          {!isCollapsed && (
            <div className="flex items-center space-x-3">
              <Image src="/logo.png" alt="Logo" width={40} height={40} className="h-10 w-10" />
              <span className="text-xl font-semibold text-gray-900 dark:text-white">EcoDeli</span>
            </div>
          )}
          <button 
            onClick={() => setIsCollapsed(!isCollapsed)} 
            className="p-2 rounded-lg bg-gray-200 dark:bg-gray-700 hover:bg-gray-300 dark:hover:bg-gray-600"
          >
            {isCollapsed ? <ChevronRightIcon className="w-6 h-4 text-gray-900 dark:text-white" /> 
                         : <ChevronLeftIcon className="w-6 h-4 text-gray-900 dark:text-white" />}
          </button>
        </div>

        {/* Navigation */}
        <NavLinks isCollapsed={isCollapsed} />
      </aside>

      {/* Contenu principal */}
      <div className="flex-1 flex flex-col">
        {/* Navbar */}
        <header className="h-16 bg-white dark:bg-gray-800 shadow-md flex items-center px-6">
          <h1 className="text-xl font-semibold text-gray-900 dark:text-white">Tableau de bord</h1>
        </header>

        {/* Contenu dynamique */}
        <main className="flex-1 p-6 bg-gray-100 dark:bg-gray-900">{children}</main>
      </div>
    </div>
  );
}