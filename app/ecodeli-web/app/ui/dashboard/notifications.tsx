"use client";

import { useState, useEffect } from "react";
import { BellIcon, XMarkIcon } from "@heroicons/react/24/outline";

export default function Notifications() {
  const [isOpen, setIsOpen] = useState(false);

  // Désactive le scroll lorsque la popup est ouverte
  useEffect(() => {
    if (isOpen) {
      document.body.style.overflow = "hidden";
    } else {
      document.body.style.overflow = "auto";
    }
    return () => {
      document.body.style.overflow = "auto";
    };
  }, [isOpen]);

  return (
    <div className="relative">
      {/* Bouton Notification */}
      <button
        onClick={() => setIsOpen(true)}
        className="p-2 rounded-lg hover:bg-gray-300 dark:hover:bg-gray-600 relative"
      >
        <BellIcon className="w-6 h-6 text-gray-900 dark:text-white" />
        {/* Badge de notification */}
        <span className="absolute -top-1 -right-1 bg-red-500 text-white text-xs font-bold w-5 h-5 flex items-center justify-center rounded-full">
          99+
        </span>
      </button>

      {/* Overlay sombre et modal */}
      {isOpen && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50">
          {/* Conteneur de la popup */}
          <div className="bg-white dark:bg-gray-800 w-[90%] md:w-[50%] h-[80%] rounded-lg shadow-lg p-6 flex flex-col relative">
            {/* Bouton de fermeture */}
            <button
              onClick={() => setIsOpen(false)}
              className="absolute top-4 right-4 p-2 rounded-full bg-gray-200 dark:bg-gray-700 hover:bg-gray-300 dark:hover:bg-gray-600"
            >
              <XMarkIcon className="w-6 h-6 text-gray-900 dark:text-white" />
            </button>

            {/* Titre */}
            <h3 className="text-gray-900 dark:text-white font-semibold text-xl mb-4">Notifications</h3>

            {/* Liste des notifications */}
            <ul className="space-y-3 overflow-y-auto flex-1">
              <li className="p-3 bg-gray-100 dark:bg-gray-700 rounded-lg text-gray-900 dark:text-white">
                🔔 Nouvelle mise à jour disponible !
              </li>
              <li className="p-3 bg-gray-100 dark:bg-gray-700 rounded-lg text-gray-900 dark:text-white">
                📢 Votre abonnement expire bientôt.
              </li>
              <li className="p-3 bg-gray-100 dark:bg-gray-700 rounded-lg text-gray-900 dark:text-white">
                ✅ Votre commande a été expédiée.
              </li>
            </ul>
          </div>
        </div>
      )}
    </div>
  );
}