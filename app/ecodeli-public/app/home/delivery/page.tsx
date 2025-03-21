"use client";

import { useState } from "react";
import clsx from "clsx";

const data = [
  { id: 1, colis: "Colis 1", origine: "Paris", destination: "Lyon", realizator: "Jean Dupont", price: "29,99€", status: "En cours" },
  { id: 2, colis: "Colis 2", origine: "Marseille", destination: "Nice", realizator: "Marie Dubois", price: "39,99€", status: "Livré" },
  { id: 3, colis: "Colis 3", origine: "Toulouse", destination: "Bordeaux", realizator: "Paul Martin", price: "19,99€", status: "En cours" },
  { id: 4, colis: "Colis 4", origine: "Lille", destination: "Strasbourg", realizator: "Sophie Lambert", price: "24,99€", status: "Livré" },
];

export default function LivraisonPage() {
  const [viewType, setViewType] = useState<"enCours" | "livré">("enCours");
  const [searchTerm, setSearchTerm] = useState("");

  const handleNouvelleLivraison = () => {
    console.log("Ajouter une nouvelle livraison");
    // Redirection ou affichage d'un formulaire ici si nécessaire
  };

  const filteredData = data.filter((item) => {
    const searchLower = searchTerm.toLowerCase();
    // Filtrer par statut (en cours ou livré) en fonction du viewType
    const statusMatch = viewType === "enCours" ? item.status === "En cours" : item.status === "Livré";

    // Filtrage par recherche et statut
    return (
      (item.colis.toLowerCase().includes(searchLower) ||
        item.origine.toLowerCase().includes(searchLower) ||
        item.destination.toLowerCase().includes(searchLower) ||
        item.realizator.toLowerCase().includes(searchLower) ||
        item.price.toLowerCase().includes(searchLower) ||
        item.status.toLowerCase().includes(searchLower)) &&
      statusMatch
    );
  });
  
  // Changer l'état du switch
  const toggleSwitch = () => {
    setViewType(viewType === "enCours" ? "livré" : "enCours");
  };

  return (
    <div className="space-y-6">
      {/* En-tête avec le titre */}
      <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between mb-6">
        <h2 className="text-2xl font-semibold text-gray-900 dark:text-white">
          Livraisons
        </h2>
        {/* Bouton Nouvelle livraison */}
        <button
          onClick={handleNouvelleLivraison}
          className="px-4 py-2 bg-blue-500 text-white rounded-md shadow-md"
        >
          Nouvelle livraison
        </button>
      </div>
  
      {/* Barre de recherche et filtres */}
      <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-y-2 sm:gap-y-0">
        {/* Barre de recherche */}
        <input
          type="text"
          placeholder="Rechercher..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          className="px-4 py-2 border rounded-md w-full sm:w-auto"
        />
  
          {/* Switch Container */}
          <div
            id="livraison-switch"
            onClick={toggleSwitch}
            className={`relative inline-flex items-center w-16 h-8 rounded-full transition-all duration-300 ease-in-out ${viewType === "enCours" ? "bg-blue-500" : "bg-gray-200"}`}
          >
            <span
              className={`inline-block w-7 h-7 rounded-full bg-white transition-all duration-300 transform ${viewType === "enCours" ? "translate-x-0" : "translate-x-8"}`}
            ></span>
            {/* Texte pour les états */}
            <span
              className={`absolute left-1 text-xs font-semibold text-white ${viewType === "enCours" ? "opacity-100" : "opacity-0"}`}
            >
              En cours
            </span>
            <span
              className={`absolute right-1 text-xs font-semibold text-gray-700 ${viewType === "livré" ? "opacity-100" : "opacity-0"}`}
            >
              Livré
            </span>
          </div>
      </div>
  
      {/* Liste des livraisons sous forme de cartes */}
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
        {filteredData.map((item) => (
          <div key={item.id} className="bg-white dark:bg-gray-800 shadow-md rounded-lg p-6 space-y-2">
            <h3 className="text-lg font-medium text-gray-900 dark:text-white">{item.colis}</h3>
            <p className="text-sm text-gray-600 dark:text-gray-300">
              <strong>Origine :</strong> {item.origine}
            </p>
            <p className="text-sm text-gray-600 dark:text-gray-300">
              <strong>Destination :</strong> {item.destination}
            </p>
            <p className="text-sm text-gray-600 dark:text-gray-300">
              <strong>Réalisateur :</strong> {item.realizator}
            </p>
            <p className="text-sm text-gray-600 dark:text-gray-300">
              <strong>Prix :</strong> {item.price}
            </p>
            <div className="flex items-center space-x-2">
              <span
                className={`inline-block w-3 h-3 rounded-full ${item.status === "En cours" ? "bg-gray-500" : "bg-green-500"}`}
              ></span>
              <span className="text-sm text-gray-900 dark:text-white">{item.status}</span>
            </div>
          </div>
        ))}
      </div>
    </div>
  );  
}