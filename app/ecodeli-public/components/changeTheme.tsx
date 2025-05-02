"use client";

import { useState } from "react";
import { MoonIcon, SunIcon } from "@heroicons/react/24/outline";

export default function ChangeTheme() {
  const [isDarkMode, setIsDarkMode] = useState(false);
  const toggleDarkMode = () => {
    setIsDarkMode(!isDarkMode);
    // Ici vous pourriez implémenter la logique pour changer le thème
    document.documentElement.classList.toggle('dark');
  };
  
  return (
    <button onClick={toggleDarkMode} className="p-2 rounded-full hover:bg-gray-200 dark:hover:bg-gray-700">
    {isDarkMode ? (
      <SunIcon className="w-6 h-6 text-gray-600 dark:text-gray-300" />
    ) : (
      <MoonIcon className="w-6 h-6 text-gray-600" />
    )}
  </button>
  )
};