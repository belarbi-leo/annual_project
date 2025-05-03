"use client";

import ChangeTheme from "@/components/changeTheme";
import Notifications from "@/components/notifications";
import LanguageSelector from "@/components/languagesSelector";
import { Bars3Icon } from "@heroicons/react/24/outline";

interface HeaderCoProps {
  onToggleSidebar: () => void;
}

export default function HeaderCo({ onToggleSidebar }: HeaderCoProps) {
  return (
    <header className="h-16 bg-white dark:bg-gray-800 sticky top-0 shadow-md flex items-center px-6 justify-between z-10">
      <div className="flex items-center">
        <button onClick={onToggleSidebar} className="mr-4 p-2 rounded-lg bg-gray-200 dark:bg-gray-700 hover:bg-gray-300 dark:hover:bg-gray-600 sm:hidden">
          <Bars3Icon className="w-6 h-6 text-gray-900 dark:text-white"/>
        </button>
      </div>
      <div className="flex items-center space-x-4">
        <ChangeTheme />
        <Notifications />
        <LanguageSelector />
      </div>
    </header>
  );
}