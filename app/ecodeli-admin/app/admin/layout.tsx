"use client";

import { useState, useEffect } from "react";
import Image from "next/image";
import AdminNavLinks from "../../components/nav-links";
import LanguageSelector from "../../components/languages";
import { ChevronLeftIcon, ChevronRightIcon, Bars3Icon, XMarkIcon } from "@heroicons/react/24/outline";
import clsx from "clsx";

export default function DashboardLayout({ children }: { children: React.ReactNode }) {
  const [isCollapsed, setIsCollapsed] = useState<boolean | null>(null);
  const [isSidebarVisible, setIsSidebarVisible] = useState<boolean>(false);

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
      {/* Sidebar (écrans md+)*/}
      <aside
        className={clsx(
          "hidden sm:flex bg-white dark:bg-gray-800 sticky top-0 h-screen p-5 shadow-md transition-all duration-300 flex-col",
          isCollapsed ? "w-16 items-center" : "w-64"
        )}
      >
        {/* Logo et bouton */}
        <div className={clsx("flex w-full mb-1", isCollapsed ? "justify-center" : "items-center justify-between")}>
          {!isCollapsed && (
            <div className="flex items-center space-x-3 m-auto">
              <Image src="/favicon.ico" alt="Logo EcoDeli" width={40} height={40} className="h-10 w-10"/>
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
        <AdminNavLinks isCollapsed={isCollapsed} />
      </aside>

      {/* Sidebar mobile (plein écran) */}
      {isSidebarVisible && (
        <div className="sm:hidden fixed inset-0 bg-black bg-opacity-50 z-40" onClick={() => setIsSidebarVisible(false)}></div>
      )}
      <aside
        className={clsx(
          "sm:hidden fixed inset-0 bg-white dark:bg-gray-800 z-50 p-5 flex flex-col transition-transform duration-300 ease-in-out",
          isSidebarVisible ? "translate-x-0" : "-translate-x-full"
        )}
      >
        {/* Entête avec logo et bouton de fermeture */}
        <div className="flex items-center justify-between">
          <div className="flex items-center space-x-3 m-auto">
            <Image src="/favicon.ico" alt="Logo EcoDeli" width={40} height={40} className="h-10 w-10" />
          </div>
          <button
            onClick={() => setIsSidebarVisible(false)}
            className="p-2 rounded-full bg-gray-200 dark:bg-gray-700 hover:bg-gray-300 dark:hover:bg-gray-600"
          >
            <XMarkIcon className="w-6 h-6 text-gray-900 dark:text-white" />
          </button>
        </div>

        {/* Navigation */}
        <AdminNavLinks 
        isCollapsed={false} 
        onNavigate={() => setIsSidebarVisible(false)}
        />
      </aside>

      <div className="flex-1 flex flex-col">
        <header className="h-16 bg-white dark:bg-gray-800 sticky top-0 shadow-md flex items-center px-6 justify-between">
          <div className="flex items-center">
            {/* Bouton hamburger pour ouvrir sidebar mobile */}
            <button
              onClick={() => setIsSidebarVisible(true)}
              className="mr-4 p-2 rounded-lg bg-gray-200 dark:bg-gray-700 hover:bg-gray-300 dark:hover:bg-gray-600 sm:hidden"
            >
              <Bars3Icon className="w-6 h-6 text-gray-900 dark:text-white" />
            </button>
            
          </div>
          <div className="flex items-center space-x-4">
            <LanguageSelector/>
          </div>
        </header>
        <main className="flex-1 p-6 bg-gray-100 dark:bg-gray-900">{children}</main>
      </div>
    </div>
  );
}