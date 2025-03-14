"use client";

import { useState } from "react";
import Image from "next/image";
import clsx from "clsx";

const data = [
  {
    id: 1,
    colis: "Colis 1",
    origine: "Paris",
    destination: "Lyon",
    realizator: "Jean Dupont",
    price: "29,99€",
    status: "En cours",
  },
  {
    id: 2,
    colis: "Colis 2",
    origine: "Marseille",
    destination: "Nice",
    realizator: "Marie Dubois",
    price: "39,99€",
    status: "Livré",
  },
  // Ajoutez d'autres livraisons ici...
];

export default function LivraisonPage() {
  const [viewType, setViewType] = useState<"enCours" | "livré">("enCours");

  // Filtrer les données en fonction du type sélectionné
  const filteredData = data.filter(item => {
    if (viewType === "enCours") {
      return item.status === "En cours";
    }
    return item.status === "Livré";
  });

  const handleNouvelleLivraison = () => {
    // Cette fonction pourrait ouvrir un formulaire ou rediriger vers une page d'ajout de nouvelle livraison
    console.log("Ajouter une nouvelle livraison");
  };

  return (
    <div className="">
      {/* En-tête avec le bouton Nouvelle livraison */}
      <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between mb-6">
        <h2 className="text-2xl font-semibold text-gray-900 dark:text-white mb-4 sm:mb-0">
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

      {/* Sélecteur de type de livraison */}
      <div className="flex space-x-4 mb-4">
        <button
          onClick={() => setViewType("enCours")}
          className={`px-4 py-2 rounded-md ${viewType === "enCours" ? "bg-blue-500 text-white" : "bg-gray-200 text-gray-900"}`}
        >
          En cours
        </button>
        <button
          onClick={() => setViewType("livré")}
          className={`px-4 py-2 rounded-md ${viewType === "livré" ? "bg-blue-500 text-white" : "bg-gray-200 text-gray-900"}`}
        >
          Livré
        </button>
      </div>

      {/* Tableau des livraisons avec gestion du scroll */}
      <div className="flex-grow overflow-x-auto max-h-[calc(100vh-120px)] overflow-y-auto">
        <table className="min-w-full bg-white dark:bg-gray-800 rounded-lg shadow-md">
          <thead className="bg-gray-200 dark:bg-gray-700">
            <tr>
              <th className="py-3 px-4 text-left text-sm font-semibold text-gray-900 dark:text-white">Colis</th>
              <th className="py-3 px-4 text-left text-sm font-semibold text-gray-900 dark:text-white">Origine</th>
              <th className="py-3 px-4 text-left text-sm font-semibold text-gray-900 dark:text-white">Destination</th>
              <th className="py-3 px-4 text-left text-sm font-semibold text-gray-900 dark:text-white">Réalisateur</th>
              <th className="py-3 px-4 text-left text-sm font-semibold text-gray-900 dark:text-white">Prix</th>
              <th className="py-3 px-4 text-left text-sm font-semibold text-gray-900 dark:text-white">Status</th>
            </tr>
          </thead>
          <tbody>
            {filteredData.map(item => (
              <tr key={item.id} className="border-t border-gray-200 dark:border-gray-700">
                <td className="py-4 px-4 text-sm text-gray-900 dark:text-white">{item.colis}</td>
                <td className="py-4 px-4 text-sm text-gray-900 dark:text-white">{item.origine}</td>
                <td className="py-4 px-4 text-sm text-gray-900 dark:text-white">{item.destination}</td>
                <td className="py-4 px-4 text-sm text-gray-900 dark:text-white">{item.realizator}</td>
                <td className="py-4 px-4 text-sm text-gray-900 dark:text-white">{item.price}</td>
                <td className="py-4 px-4 text-sm text-gray-900 dark:text-white items-center space-x-2">
                  {/* Rond coloré à côté du status */}
                  <span
                    className={clsx(
                      "inline-flex items-center justify-center w-3 h-3 rounded-full",
                      {
                        "bg-gray-500": item.status === "En cours",
                        "bg-green-500": item.status === "Livré",
                      }
                    )}></span>
                  <span>{item.status}</span>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
