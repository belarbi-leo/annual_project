"use client";

import { useState, useEffect, useRef } from "react";
import { useRouter } from "next/navigation";
import { ChevronDownIcon } from "@heroicons/react/24/solid";
import Image from "next/image";

export default function ProductPage() {
  const router = useRouter();
  const [categoryOpen, setCategoryOpen] = useState(false);
  const [priceOpen, setPriceOpen] = useState(false);

  // Déclaration des références avec types explicites
  const categoryRef = useRef<HTMLDivElement | null>(null);
  const priceRef = useRef<HTMLDivElement | null>(null);

  // Fonction pour appliquer un filtre via l'URL
  const applyFilter = (type: string, value: string) => {
    const params = new URLSearchParams(window.location.search);
    params.set(type, value);
    router.push(`?${params.toString()}`, { scroll: false });
  };

  // Fermer les menus si on clique en dehors
  useEffect(() => {
    // Fonction de gestion des clics en dehors des menus
    function handleClickOutside(event: MouseEvent) {
      if (
        categoryRef.current && !categoryRef.current.contains(event.target as Node) &&
        priceRef.current && !priceRef.current.contains(event.target as Node)
      ) {
        setCategoryOpen(false);
        setPriceOpen(false);
      }
    }

    document.addEventListener("mousedown", handleClickOutside);
    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, []);

  return (
    <div>
      {/* En-tête avec filtres */}
      <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between mb-6">
        <h2 className="text-2xl font-semibold text-gray-900 dark:text-white mb-4 sm:mb-0">
          Prestations
        </h2>

        {/* Conteneur des filtres et boutons */}
        <div className="flex flex-col sm:flex-row sm:items-center gap-4 w-full sm:w-auto">
          {/* Conteneur des deux boutons dropdown */}
          <div className="flex flex-row sm:flex-row gap-4 w-full sm:w-auto justify-center">
            {/* Menu dropdown Catégories */}
            <div className="" ref={categoryRef}>
              <button
                onClick={() => {
                  setCategoryOpen(!categoryOpen);
                  setPriceOpen(false); // Ferme l'autre menu si ouvert
                }}
                className="flex items-center justify-between bg-gray-200 dark:bg-gray-700 text-gray-900 dark:text-white px-4 py-2 rounded-md shadow-md hover:bg-gray-300 dark:hover:bg-gray-600 focus:outline-none w-full sm:w-auto"
              >
                Catégories
                <ChevronDownIcon className="w-5 h-5 ml-2" />
              </button>
              {categoryOpen && (
                <div className="absolute right-0 mt-2 w-48 rounded-md shadow-lg bg-white dark:bg-gray-700 z-10">
                  <ul className="py-1">
                    {["Électronique", "Maison", "Mode"].map((cat) => (
                      <li key={cat}>
                        <button
                          onClick={() => applyFilter("category", cat)}
                          className="w-full text-left px-4 py-2 text-gray-900 dark:text-white hover:bg-gray-100 dark:hover:bg-gray-600"
                        >
                          {cat}
                        </button>
                      </li>
                    ))}
                  </ul>
                </div>
              )}
            </div>

            {/* Menu dropdown Prix */}
            <div className="" ref={priceRef}>
              <button
                onClick={() => {
                  setPriceOpen(!priceOpen);
                  setCategoryOpen(false); // Ferme l'autre menu si ouvert
                }}
                className="flex items-center justify-between bg-gray-200 dark:bg-gray-700 text-gray-900 dark:text-white px-4 py-2 rounded-md shadow-md hover:bg-gray-300 dark:hover:bg-gray-600 focus:outline-none w-full sm:w-auto"
              >
                Prix
                <ChevronDownIcon className="w-5 h-5 ml-2" />
              </button>
              {priceOpen && (
                <div className="absolute right-0 mt-2 w-48 rounded-md shadow-lg bg-white dark:bg-gray-700 z-10">
                  <ul className="py-1">
                    {["Moins de 50€", "50€ - 100€", "Plus de 100€"].map((price) => (
                      <li key={price}>
                        <button
                          onClick={() => applyFilter("price", price)}
                          className="w-full text-left px-4 py-2 text-gray-900 dark:text-white hover:bg-gray-100 dark:hover:bg-gray-600"
                        >
                          {price}
                        </button>
                      </li>
                    ))}
                  </ul>
                </div>
              )}
            </div>
          </div>

          {/* Bouton vert Ajouter une prestation */}
          <button className="bg-green-500 text-white px-6 py-2 rounded-md shadow-md hover:bg-green-600 focus:outline-none w-full sm:w-auto">
            Ajouter une prestation
          </button>
        </div>
      </div>

      {/* Liste des produits */}
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6">
        {[1, 2, 3, 4].map((id) => (
          <div key={id} className="dark:bg-gray-700 rounded-lg shadow-md overflow-hidden">
            <Image
              src="/deliveryman.jpg"
              alt={`Produit ${id}`}
              className="w-full h-48 object-cover shadow-md"
              width={240}
              height={240}
            />
            <div className="p-4">
              <h3 className="text-lg font-semibold text-gray-900 dark:text-white">
                Produit {id}
              </h3>
              <p className="mt-2 text-gray-600 dark:text-gray-300">
                Une description courte du produit {id}.
              </p>
              <div className="mt-4 flex justify-between items-center">
                <span className="text-xl font-bold text-gray-900 dark:text-white">
                  {id * 20 + 9},99€
                </span>
                <button className="bg-blue-500 text-white px-4 py-2 rounded-md shadow-md hover:bg-blue-600 focus:outline-none">
                  Acheter
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}