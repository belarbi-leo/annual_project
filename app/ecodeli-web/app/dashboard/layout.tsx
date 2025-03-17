"use client";

import { useState, useEffect } from "react";
import Image from "next/image";
import NavLinks from "../ui/dashboard/nav-links";
import LanguageSelector from "../ui/dashboard/languages";
import Notifications from "../ui/dashboard/notifications";
import { 
  ChevronLeftIcon, 
  ChevronRightIcon, 
  BellIcon, 
  MagnifyingGlassIcon 
} from "@heroicons/react/24/outline";
import clsx from "clsx";

export default function DashboardLayout({ children }: { children: React.ReactNode }) {
  const [isCollapsed, setIsCollapsed] = useState<boolean | null>(null);

  useEffect(() => {
    const handleResize = () => setIsCollapsed(window.innerWidth < 768);
    handleResize();
    window.addEventListener("resize", handleResize);
    return () => window.removeEventListener("resize", handleResize);
  }, []);

  if (isCollapsed === null) {
    return true;
  }

  return (
    <div className="flex min-h-screen bg-gray-100 dark:bg-gray-900">
      {/* Sidebar */}
      <aside
        className={clsx(
          "bg-white dark:bg-gray-800 sticky top-0 h-screen p-5 shadow-md transition-all duration-300 flex flex-col",
          isCollapsed ? "w-16 items-center" : "w-64"
        )}
      >
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
            {isCollapsed ? (
              <ChevronRightIcon className="w-6 h-4 text-gray-900 dark:text-white" />
            ) : (
              <ChevronLeftIcon className="w-6 h-4 text-gray-900 dark:text-white" />
            )}
          </button>
        </div>

        {/* Navigation */}
        <NavLinks isCollapsed={isCollapsed} />
      </aside>

      {/* Contenu principal */}
      <div className="flex-1 flex flex-col">
        {/* Navbar */}
        <header className="h-16 bg-white dark:bg-gray-800 shadow-md flex items-center px-6 justify-between">
          {/* Barre de recherche (visible sur écrans md et +) */}
          <div className="hidden sm:block flex-1 max-w-lg">
            <input
              type="text"
              placeholder="Rechercher..."
              className="w-full px-4 py-2 rounded-lg bg-gray-100 dark:bg-gray-700 border border-gray-300 dark:border-gray-600 text-gray-900 dark:text-white focus:outline-none focus:ring-2 focus:ring-[#49cb5c]"
            />
          </div>

          {/* Bouton recherche (visible uniquement sur petits écrans sm) */}
          <button className="sm:hidden p-2 rounded-lg bg-gray-200 dark:bg-gray-700 hover:bg-gray-300 dark:hover:bg-gray-600">
            <MagnifyingGlassIcon className="w-6 h-6 text-gray-900 dark:text-white" />
          </button>

          {/* Boutons notifications et langue */}
          <div className="flex items-center space-x-4">
            <Notifications />
            <LanguageSelector />
          </div>
        </header>

        {/* Contenu dynamique */}
        <main className="flex-1 p-6 bg-gray-100 dark:bg-gray-900">{children}</main>
      </div>
    </div>
  );
}