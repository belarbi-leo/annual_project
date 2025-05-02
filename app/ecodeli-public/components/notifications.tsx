"use client";

import { useState, useRef, useEffect } from "react";
import { BellIcon, XMarkIcon } from "@heroicons/react/24/outline";

export default function Notifications() {
  const [isOpen, setIsOpen] = useState(false);
  const [notifications, setNotifications] = useState([
    { id: 1, text: "Nouvelle commande reçue", time: "Il y a 5 min", read: false },
    { id: 2, text: "Livraison en cours", time: "Il y a 30 min", read: false },
    { id: 3, text: "Rapport mensuel disponible", time: "Il y a 2 heures", read: true },
  ]);

  const menuRef = useRef<HTMLDivElement>(null);
  
  // État pour la taille de l'écran avec initialisation correcte
  const [windowWidth, setWindowWidth] = useState<number>(0);
  const isSmallScreen = windowWidth <= 500;

  // Initialiser la taille de l'écran au chargement
  useEffect(() => {
    // S'assurer que le code s'exécute seulement côté client
    if (typeof window !== 'undefined') {
      // Définir la largeur initiale
      setWindowWidth(window.innerWidth);
      
      // Ajouter l'écouteur d'événements pour les changements de taille
      const handleResize = () => {
        setWindowWidth(window.innerWidth);
      };
      
      window.addEventListener('resize', handleResize);
      
      // Nettoyage
      return () => {
        window.removeEventListener('resize', handleResize);
      };
    }
  }, []);

  // Détecter les clics à l'extérieur pour la version dropdown (écrans > 500px)
  useEffect(() => {
    if (!isSmallScreen && isOpen) {
      const handleClickOutside = (event: MouseEvent) => {
        if (menuRef.current && !menuRef.current.contains(event.target as Node)) {
          setIsOpen(false);
        }
      };
  
      document.addEventListener("mousedown", handleClickOutside);
      return () => document.removeEventListener("mousedown", handleClickOutside);
    }
  }, [isSmallScreen, isOpen]);

  // Gestion du défilement
  useEffect(() => {
    if (isOpen && isSmallScreen) {
      document.body.style.overflow = 'hidden';
    } else {
      document.body.style.overflow = 'auto';
    }
    
    return () => {
      document.body.style.overflow = 'auto';
    };
  }, [isOpen, isSmallScreen]);

  const unreadCount = notifications.filter(n => !n.read).length;

  const markAsRead = (id: number) => {
    setNotifications(notifications.map(n => 
      n.id === id ? { ...n, read: true } : n
    ));
  };

  const markAllAsRead = () => {
    setNotifications(notifications.map(n => ({ ...n, read: true })));
  };

  return (
    <div className="relative" ref={menuRef}>
      <button 
        onClick={() => setIsOpen(!isOpen)}
        className="relative p-2 rounded-full hover:bg-gray-200 dark:hover:bg-gray-700"
      >
        <BellIcon className="w-6 h-6 text-gray-600 dark:text-gray-300" />
        {unreadCount > 0 && (
          <span className="absolute top-0 right-0 h-4 w-4 bg-red-500 rounded-full flex items-center justify-center text-xs text-white">
            {unreadCount}
          </span>
        )}
      </button>

      {/* Version modal pour petits écrans (≤ 500px) */}
      {isOpen && isSmallScreen && (
        <div className="fixed inset-0 bg-black bg-opacity-50 z-50 flex justify-center items-start">
          <div className="bg-white dark:bg-gray-800 w-full h-full max-h-full overflow-hidden flex flex-col">
            <div className="flex justify-between items-center px-4 py-3 border-b border-gray-200 dark:border-gray-700">
              <h3 className="text-lg font-medium">Notifications</h3>
              <div className="flex space-x-4 items-center">
                {unreadCount > 0 && (
                  <button 
                    onClick={markAllAsRead}
                    className="text-sm text-blue-500 hover:text-blue-700"
                  >
                    Tout marquer
                  </button>
                )}
                <button 
                  onClick={() => setIsOpen(false)}
                  className="p-2 rounded-full hover:bg-gray-200 dark:hover:bg-gray-700"
                >
                  <XMarkIcon className="w-5 h-5 text-gray-600 dark:text-gray-300" />
                </button>
              </div>
            </div>
            
            <div className="flex-1 overflow-y-auto">
              {notifications.length > 0 ? (
                <ul>
                  {notifications.map((notification) => (
                    <li 
                      key={notification.id}
                      className={`px-4 py-4 hover:bg-gray-100 dark:hover:bg-gray-700 border-b border-gray-100 dark:border-gray-700 ${!notification.read ? 'bg-blue-50 dark:bg-blue-900/20' : ''}`}
                      onClick={() => markAsRead(notification.id)}
                    >
                      <div className="flex justify-between">
                        <p className="text-base font-medium text-gray-900 dark:text-white">{notification.text}</p>
                        {!notification.read && (
                          <span className="h-3 w-3 bg-blue-500 rounded-full"></span>
                        )}
                      </div>
                      <p className="text-sm text-gray-500 dark:text-gray-400 mt-1">{notification.time}</p>
                    </li>
                  ))}
                </ul>
              ) : (
                <div className="flex flex-col items-center justify-center h-full">
                  <p className="text-center py-4 text-gray-500 dark:text-gray-400">Aucune notification</p>
                </div>
              )}
            </div>
            
            <div className="px-4 py-3 border-t border-gray-200 dark:border-gray-700">
              <button className="w-full py-2 rounded-lg bg-blue-500 hover:bg-blue-600 text-white font-medium">
                Voir toutes les notifications
              </button>
            </div>
          </div>
        </div>
      )}

      {/* Version dropdown pour grands écrans (> 500px) */}
      {isOpen && !isSmallScreen && (
        <div className="absolute right-0 mt-2 w-80 bg-white dark:bg-gray-800 rounded-lg shadow-lg py-2 z-10">
          <div className="flex justify-between items-center px-4 py-2 border-b border-gray-200 dark:border-gray-700">
            <h3 className="font-medium">Notifications</h3>
            {unreadCount > 0 && (
              <button 
                onClick={markAllAsRead}
                className="text-sm text-blue-500 hover:text-blue-700"
              >
                Tout marquer comme lu
              </button>
            )}
          </div>
          
          <div className="max-h-80 overflow-y-auto">
            {notifications.length > 0 ? (
              <ul>
                {notifications.map((notification) => (
                  <li 
                    key={notification.id}
                    className={`px-4 py-3 hover:bg-gray-100 dark:hover:bg-gray-700 border-b border-gray-100 dark:border-gray-700 last:border-0 ${!notification.read ? 'bg-blue-50 dark:bg-blue-900/20' : ''}`}
                    onClick={() => markAsRead(notification.id)}
                  >
                    <div className="flex justify-between">
                      <p className="text-sm font-medium text-gray-900 dark:text-white">{notification.text}</p>
                      {!notification.read && (
                        <span className="h-2 w-2 bg-blue-500 rounded-full"></span>
                      )}
                    </div>
                    <p className="text-xs text-gray-500 dark:text-gray-400 mt-1">{notification.time}</p>
                  </li>
                ))}
              </ul>
            ) : (
              <p className="text-center py-4 text-gray-500 dark:text-gray-400">Aucune notification</p>
            )}
          </div>
          
          <div className="px-4 py-2 border-t border-gray-200 dark:border-gray-700">
            <button className="text-center w-full text-sm text-blue-500 hover:text-blue-700">
              Voir toutes les notifications
            </button>
          </div>
        </div>
      )}
    </div>
  );
}