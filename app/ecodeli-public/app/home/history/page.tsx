"use client";

import { useState } from "react";
import Image from "next/image";
import clsx from "clsx";

const data = [
  {
    id: 1,
    image: "/deliveryman.jpg", // Image de la prestation
    title: "Livraison 1",
    description: "Livraison d'articles électroniques",
    realizator: "Jean Dupont",
    price: "29,99€",
    status: "En cours",
    type: "livraison", // Livraison ou prestation
  },
  {
    id: 2,
    image: "/deliveryman.jpg", // Image de la prestation
    title: "Prestation 1",
    description: "Installation d'appareils électroménagers",
    realizator: "Marie Dubois",
    price: "99,99€",
    status: "Terminée",
    type: "prestation",
  },
  // Ajoutez d'autres éléments ici...
];

export default function HistoriquePage() {
  const [viewType, setViewType] = useState<"livraison" | "prestation">("livraison");

  // Filtrer les données en fonction du type sélectionné
  const filteredData = data.filter(item => item.type === viewType);

  return (
    <div className="">
      {/* En-tête avec le switch Livraison / Prestation */}
      <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between mb-6">
        <h2 className="text-2xl font-semibold text-gray-900 dark:text-white mb-4 sm:mb-0">
          Historique des {viewType === "livraison" ? "Livraisons" : "Prestations"}
        </h2>

        {/* Switch entre Livraison et Prestation */}
        <div className="flex space-x-4">
          <button
            onClick={() => setViewType("livraison")}
            className={`px-4 py-2 rounded-md ${viewType === "livraison" ? "bg-blue-500 text-white" : "bg-gray-200 text-gray-900"}`}
          >
            Livraison
          </button>
          <button
            onClick={() => setViewType("prestation")}
            className={`px-4 py-2 rounded-md ${viewType === "prestation" ? "bg-blue-500 text-white" : "bg-gray-200 text-gray-900"}`}
          >
            Prestation
          </button>
        </div>
      </div>

      {/* Tableau des historiques avec gestion du scroll */}
      <div className="flex-grow overflow-x-auto max-h-[calc(100vh-120px)] overflow-y-auto">
        <table className="min-w-full bg-white dark:bg-gray-800 rounded-lg shadow-md">
          <thead className="bg-gray-200 dark:bg-gray-700">
            <tr>
              <th className="py-3 px-4 text-left text-sm font-semibold text-gray-900 dark:text-white">Image</th>
              <th className="py-3 px-4 text-left text-sm font-semibold text-gray-900 dark:text-white">Titre</th>
              <th className="py-3 px-4 text-left text-sm font-semibold text-gray-900 dark:text-white">Description</th>
              <th className="py-3 px-4 text-left text-sm font-semibold text-gray-900 dark:text-white">Réalisateur</th>
              <th className="py-3 px-4 text-left text-sm font-semibold text-gray-900 dark:text-white">Prix</th>
              <th className="py-3 px-4 text-left text-sm font-semibold text-gray-900 dark:text-white">Status</th>
            </tr>
          </thead>
          <tbody>
            {filteredData.map(item => (
              <tr key={item.id} className="border-t border-gray-200 dark:border-gray-700">
                <td className="py-4 px-4 text-sm text-gray-900 dark:text-white">
                  <Image src={item.image} alt={item.title} width={50} height={50} className="rounded-full" />
                </td>
                <td className="py-4 px-4 text-sm text-gray-900 dark:text-white">{item.title}</td>
                <td className="py-4 px-4 text-sm text-gray-600 dark:text-gray-300">{item.description}</td>
                <td className="py-4 px-4 text-sm text-gray-900 dark:text-white">{item.realizator}</td>
                <td className="py-4 px-4 text-sm text-gray-900 dark:text-white">{item.price}</td>
                <td className="py-4 px-4 text-sm text-gray-900 dark:text-white items-center space-x-2">
                  {/* Rond coloré à côté du status */}
                  <span
                    className={clsx(
                      "inline-flex items-center justify-center w-3 h-3 rounded-full",
                      {
                        "bg-gray-500": item.status === "En cours",
                        "bg-green-500": item.status === "Terminée",
                        "bg-red-500": item.status === "Annulée",
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