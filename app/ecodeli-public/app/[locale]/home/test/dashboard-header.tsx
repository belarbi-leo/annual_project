// components/dashboard-header.tsx
"use client";

import { useState } from "react";
import { Bars3Icon, MoonIcon, SunIcon, UserCircleIcon, ChevronDownIcon } from "@heroicons/react/24/outline";
import Notifications from "../notifications";
import LanguageSelector from "../languages";

interface DashboardHeaderProps {
  onToggleSidebar: () => void;
}

export default function DashboardHeader({ onToggleSidebar }: DashboardHeaderProps) {
  const [isDarkMode, setIsDarkMode] = useState(false);
  const [isProfileMenuOpen, setIsProfileMenuOpen] = useState(false);

  const toggleDarkMode = () => {
    setIsDarkMode(!isDarkMode);
    // Ici vous pourriez implémenter la logique pour changer le thème
    document.documentElement.classList.toggle('dark');
  };

  return (
    <header className="h-16 bg-white dark:bg-gray-800 sticky top-0 shadow-md flex items-center px-6 justify-between z-10">
      <div className="flex items-center">
        <button 
          onClick={onToggleSidebar} 
          className="mr-4 p-2 rounded-lg bg-gray-200 dark:bg-gray-700 hover:bg-gray-300 dark:hover:bg-gray-600 sm:hidden"
        >
          <Bars3Icon className="w-6 h-6 text-gray-900 dark:text-white"/>
        </button>
        <div className="hidden md:block">
          <h1 className="text-lg font-medium">Tableau de bord</h1>
          <p className="text-sm text-gray-500 dark:text-gray-400">Bienvenue sur EcoDeli</p>
        </div>
      </div>
      
      <div className="flex items-center space-x-4">
        <button 
          onClick={toggleDarkMode}
          className="p-2 rounded-full hover:bg-gray-200 dark:hover:bg-gray-700"
        >
          {isDarkMode ? (
            <SunIcon className="w-6 h-6 text-gray-600 dark:text-gray-300" />
          ) : (
            <MoonIcon className="w-6 h-6 text-gray-600" />
          )}
        </button>
        
        <Notifications />
        <LanguageSelector />
        
        <div className="relative">
          <button 
            onClick={() => setIsProfileMenuOpen(!isProfileMenuOpen)}
            className="flex items-center space-x-2 p-1 rounded-full hover:bg-gray-200 dark:hover:bg-gray-700"
          >
            <div className="w-8 h-8 rounded-full bg-gray-300 flex items-center justify-center overflow-hidden">
              <UserCircleIcon className="w-8 h-8 text-gray-500" />
            </div>
            <div className="hidden sm:block text-left">
              <p className="text-sm font-medium">John Doe</p>
              <p className="text-xs text-gray-500 dark:text-gray-400">Admin</p>
            </div>
            <ChevronDownIcon className="w-4 h-4 text-gray-500 hidden sm:block" />
          </button>
          
          {isProfileMenuOpen && (
            <div className="absolute right-0 mt-2 w-48 bg-white dark:bg-gray-800 rounded-lg shadow-lg py-1 z-20">
              <a href="/profile" className="block px-4 py-2 text-sm hover:bg-gray-100 dark:hover:bg-gray-700">
                Profile
              </a>
              <a href="/settings" className="block px-4 py-2 text-sm hover:bg-gray-100 dark:hover:bg-gray-700">
                Paramètres
              </a>
              <div className="border-t border-gray-200 dark:border-gray-700 my-1"></div>
              <a href="/logout" className="block px-4 py-2 text-sm text-red-500 hover:bg-gray-100 dark:hover:bg-gray-700">
                Déconnexion
              </a>
            </div>
          )}
        </div>
      </div>
    </header>
  );
}