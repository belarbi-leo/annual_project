"use client";

import Image from "next/image";
import { useState } from "react";
import { PencilIcon, SparklesIcon } from "@heroicons/react/24/outline";

export default function AccountPage() {
  const [username, setUsername] = useState("John Doe");
  const [membership, setMembership] = useState("Membre premium"); // "Membre classique" ou "Membre premium"

  return (
    <div className="space-y-6">
      {/* Section Profil */}
      <div className="bg-white dark:bg-gray-800 shadow-md rounded-lg p-6 flex flex-col sm:flex-row items-center sm:items-center sm:justify-between">
        
        {/* Image de Profil */}
        <div className="w-24 h-24 shrink-0">
          <Image
            src="/profile-placeholder.png"
            alt="Profile"
            width={96}
            height={96}
            className="rounded-full border-4 border-gray-300 dark:border-gray-600"
          />
        </div>

        {/* Informations Profil */}
        <div className="flex flex-col items-center sm:items-start sm:ml-6 mt-4 sm:mt-0">
          {/* Nom */}
          <h2 className="text-2xl font-semibold text-gray-900 dark:text-white">{username}</h2>

          {/* Pilule de statut */}
          <div
            className={`mt-2 px-4 py-1 text-sm font-medium rounded-full border flex items-center ${
              membership === "Membre premium"
                ? "border-yellow-400 text-yellow-400"
                : "border-white text-white"
            }`}
          >
            {membership === "Membre premium" && <SparklesIcon className="w-5 h-5 mr-2" />}
            {membership}
          </div>
        </div>

        {/* Bouton Modifier */}
        <button className="mt-6 sm:mt-0 sm:ml-auto flex items-center px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition">
          <PencilIcon className="w-5 h-5 mr-2" />
          Modifier
        </button>
      </div>

      {/* Section Statistiques */}
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
        <StatCard title="Commandes passées" value="152" />
        <StatCard title="Avis donnés" value="34" />
        <StatCard title="Articles favoris" value="27" />
      </div>
    </div>
  );
}

// Composant Carte de Statistiques
function StatCard({ title, value }: { title: string; value: string }) {
  return (
    <div className="bg-white dark:bg-gray-800 shadow-md rounded-lg p-6 text-center">
      <h3 className="text-lg font-medium text-gray-600 dark:text-gray-300">{title}</h3>
      <p className="text-3xl font-bold text-gray-900 dark:text-white mt-2">{value}</p>
    </div>
  );
}