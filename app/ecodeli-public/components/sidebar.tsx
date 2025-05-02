// components/sidebar.tsx
"use client";

import { useState, useEffect } from "react";
import Image from "next/image";
import { ChevronLeftIcon, ChevronRightIcon, XMarkIcon } from "@heroicons/react/24/outline";
import NavLinks from "@/components/nav-links";
import clsx from "clsx";

interface SidebarProps {
  isMobile?: boolean;
  isVisible?: boolean;
  onClose?: () => void;
}

export default function Sidebar({ isMobile = false, isVisible = false, onClose }: SidebarProps) {
  const [isCollapsed, setIsCollapsed] = useState<boolean>(false);

  useEffect(() => {
    // Si on est sur desktop, on vérifie la largeur initiale
    if (!isMobile) {
      setIsCollapsed(window.innerWidth < 1024);
    }
  }, [isMobile]);

  if (isMobile) {
    return (
      <>
        {isVisible && ( 
          <div className="fixed inset-0 bg-black bg-opacity-50 z-40" onClick={onClose}></div>
        )}
        <aside className={clsx(
          "fixed inset-y-0 left-0 bg-white dark:bg-gray-800 z-50 p-5 flex flex-col w-64 transition-transform duration-300 ease-in-out",
          isVisible ? "translate-x-0" : "-translate-x-full"
        )}>
          {/* En-tête avec logo et bouton de fermeture */}
          <div className="flex items-center justify-between mb-6">
            <div className="flex space-x-3">
              <Image src="/favicon.ico" alt="Logo EcoDeli" width={40} height={40} className="h-10 w-10"/>
              <h1 className="text-xl my-auto">EcoDeli</h1>
            </div>
            <button 
              onClick={onClose} 
              className="p-2 rounded-full bg-gray-200 dark:bg-gray-700 hover:bg-gray-300 dark:hover:bg-gray-600"
            >
              <XMarkIcon className="w-6 h-6 text-gray-900 dark:text-white"/>
            </button>
          </div>
          
          {/* Navigation */}
          <NavLinks isCollapsed={false} onNavigate={onClose} />
          
          {/* Footer */}
          <div className="mt-auto pt-6 border-t border-gray-200 dark:border-gray-700">
            <div className="flex items-center px-3 py-2">
              <div className="w-10 h-10 rounded-full bg-gray-300 flex items-center justify-center mr-3">
                <span className="text-gray-600 font-medium">JD</span>
              </div>
              <div>
                <p className="text-sm font-medium">John Doe</p>
                <p className="text-xs text-gray-500 dark:text-gray-400">john.doe@example.com</p>
              </div>
            </div>
          </div>
        </aside>
      </>
    );
  }

  return (
    <aside className={clsx(
      "hidden sm:flex bg-white dark:bg-gray-800 sticky top-0 h-screen shadow-md transition-all duration-300 flex-col",
      isCollapsed ? "w-16 items-center" : "w-64"
    )}>
      {/* Logo et bouton */}
      <div className={clsx("flex w-full p-5 mb-1", isCollapsed ? "justify-center" : "items-center justify-between")}>
        {!isCollapsed && (
          <div className="flex space-x-3">
            <Image src="/favicon.ico" alt="Logo EcoDeli" width={40} height={40} className="h-10 w-10"/>
            <h1 className="text-xl my-auto">EcoDeli</h1>
          </div>
        )}
        <button 
          onClick={() => setIsCollapsed(!isCollapsed)} 
          className="p-2 rounded-lg bg-gray-200 dark:bg-gray-700 hover:bg-gray-300 dark:hover:bg-gray-600"
        >
          {isCollapsed ? (
            <ChevronRightIcon className="w-6 h-4 text-gray-900 dark:text-white"/>
          ) : (
            <ChevronLeftIcon className="w-6 h-4 text-gray-900 dark:text-white"/>
          )}
        </button>
      </div>
      
      {/* Navigation */}
      <div className="flex-1 px-5">
        <NavLinks isCollapsed={isCollapsed} />
      </div>
      
      {/* Footer (utilisateur connecté) */}
      {!isCollapsed && (
        <div className="p-5 border-t border-gray-200 dark:border-gray-700">
          <div className="flex items-center">
            <div className="w-10 h-10 rounded-full bg-gray-300 flex items-center justify-center mr-3">
              <span className="text-gray-600 font-medium">JD</span>
            </div>
            <div>
              <p className="text-sm font-medium">John Doe</p>
              <p className="text-xs text-gray-500 dark:text-gray-400">john.doe@example.com</p>
            </div>
          </div>
        </div>
      )}
    </aside>
  );
}