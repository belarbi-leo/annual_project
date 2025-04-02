"use client";

import { useState } from "react";

const categories = [
  {
    name: "Services à la personne",
    icon: "👩‍🔧",
    subcategories: ["Garde d'enfants", "Aide à domicile", "Coach personnel"],
  },
  {
    name: "Transport & Livraison",
    icon: "🚚",
    subcategories: ["Déménagement", "Livraison de colis", "Taxi privé"],
  },
  {
    name: "Travaux & Réparations",
    icon: "🔧",
    subcategories: ["Plomberie", "Électricité", "Menuiserie"],
  },
  {
    name: "Informatique & Digital",
    icon: "💻",
    subcategories: ["Développement Web", "Support IT", "Montage vidéo"],
  },
  {
    name: "Événementiel & Loisirs",
    icon: "🎉",
    subcategories: ["DJ", "Photographe", "Organisateur d'événements"],
  },
];

const prestations = {
  "Garde d'enfants": [
    { name: "BabyCare+", price: "15€/h", rating: 4.8 },
    { name: "BabyBaby+", price: "25€/h", rating: 4.3 },
  ],
  "Aide à domicile": [
    { name: "HomeHelp", price: "12€/h", rating: 4.6 },
    { name: "HomeSweatHome", price: "24€/h", rating: 3.6 },
  ],
  "Coach personnel": [{ name: "FitCoach", price: "30€/h", rating: 4.9 }],
  "Déménagement": [{ name: "Démélogistics", price: "50€", rating: 4.5 }],
  "Livraison de colis": [{ name: "Speedy Express", price: "20€", rating: 4.7 }],
  "Taxi privé": [{ name: "UrbanCab", price: "30€", rating: 4.2 }],
  "Plomberie": [{ name: "AquaFix", price: "40€", rating: 4.4 }],
  "Électricité": [{ name: "VoltSafe", price: "45€", rating: 4.3 }],
  "Menuiserie": [{ name: "BoisExpert", price: "50€", rating: 4.1 }],
  "Développement Web": [{ name: "WebSolutions", price: "500€", rating: 4.9 }],
  "Support IT": [{ name: "ITAssist", price: "40€/h", rating: 4.5 }],
  "Montage vidéo": [{ name: "VidéoPro", price: "100€", rating: 4.6 }],
  "DJ": [{ name: "SoundMax", price: "200€", rating: 4.7 }],
  "Photographe": [{ name: "PhotoArt", price: "150€", rating: 4.8 }],
  "Organisateur d'événements": [{ name: "EventMaster", price: "500€", rating: 4.9 }],
};

export default function Prestations() {
  const [selectedCategory, setSelectedCategory] = useState(null);
  const [selectedSubCategory, setSelectedSubCategory] = useState(null);
  const [searchQuery, setSearchQuery] = useState("");
  const [filter, setFilter] = useState("all");

  const currentCategory = categories.find(
    (cat) => cat.name === selectedCategory
  );
  const filteredPrestations = selectedSubCategory
    ? prestations[selectedSubCategory]?.filter((p) =>
        p.name.toLowerCase().includes(searchQuery.toLowerCase())
      ) || []
    : [];

  return (
    <div className="space-y-8 p-6 bg-gray-50 dark:bg-gray-900 min-h-screen">
      {/* Texte d'introduction */}
      <div className="text-center max-w-3xl mx-auto">
        <h2 className="text-3xl font-bold text-gray-800 my-5 dark:text-white">
          Nos Prestations
        </h2>
        <p className="text-gray-600 dark:text-gray-300 my-10 text-lg">
          Nous sélectionnons soigneusement nos prestataires pour vous offrir des
          services de qualité. Trouvez des experts qualifiés, notés et approuvés
          par notre communauté. Que ce soit pour des travaux, du transport, ou
          du digital, nous avons la prestation qu'il vous faut.
        </p>
      </div>
      <div className="w-full h-[2px] bg-gray-300 dark:bg-gray-600 mt-15"></div>

      {/* Barre de recherche */}
      <div className="flex justify-between items-center mt-10">
        <input
          type="text"
          placeholder="Rechercher une prestation..."
          className="w-full px-4 py-2 rounded-lg bg-gray-100 dark:bg-gray-700 border border-gray-300 dark:border-gray-600 text-gray-900 dark:text-white focus:outline-none focus:ring-2 focus:ring-[#49cb5c]"
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
        />
      </div>

      {/* Catégories */}
      <div className="flex flex-wrap gap-4 justify-around my-10">
        {categories.map((cat) => (
          <label
            key={cat.name}
            className="cursor-pointer flex items-center gap-2"
          >
            <input
              type="radio"
              name="category"
              value={cat.name}
              checked={selectedCategory === cat.name}
              onChange={() => {
                setSelectedCategory(cat.name);
                setSelectedSubCategory(null);
              }}
              className="hidden"
            />
            <span
              className={`w-5 h-5 ${
                selectedCategory === cat.name
                  ? "text-lime-600"
                  : "text-gray-400"
              }`}
            >
              {cat.icon}
            </span>
            <span
              className={`text-gray-700 text-sm font-medium transition-all duration-300
              ${
                selectedCategory === cat.name
                  ? "text-lime-600 font-semibold border-b-2 border-lime-600"
                  : "hover:text-lime-500 hover:border-b-2 hover:border-lime-500"
              }`}
            >
              {cat.name}
            </span>
          </label>
        ))}
      </div>

      {/* Sous-catégories */}
      {selectedCategory && currentCategory?.subcategories.length > 0 && (
        <div className="flex flex-wrap gap-4 justify-around mb-5">
          {currentCategory.subcategories.map((subCat) => (
            <label
              key={subCat}
              className="cursor-pointer flex items-center gap-2"
            >
              <input
                type="radio"
                name="subcategory"
                value={subCat}
                checked={selectedSubCategory === subCat}
                onChange={() => setSelectedSubCategory(subCat)}
                className="hidden"
              />
              <span
                className={`text-gray-700 text-sm font-medium transition-all duration-300
                ${
                  selectedSubCategory === subCat
                    ? "text-green-600 font-semibold border-b-2 border-green-600"
                    : "hover:text-green-500 hover:border-b-2 hover:border-green-500"
                }`}
              >
                {subCat}
              </span>
            </label>
          ))}
        </div>
      )}

      <hr className="my-6 border-t border-gray-300 dark:border-gray-600" />

      {/* Filtres - Affichés seulement si une sous-catégorie est sélectionnée */}
      {selectedSubCategory && (
        <div className="flex justify-end items-center my-5">
          <select
            className="border py-2 px-3 rounded-md"
            value={filter}
            onChange={(e) => setFilter(e.target.value)}
          >
            <option value="all">Trier par</option>
            <option value="price">Prix</option>
            <option value="rating">Note</option>
          </select>
        </div>
      )}

      {/* Liste des prestations - S'affiche seulement après avoir choisi une sous-catégorie */}
      {selectedSubCategory && (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
          {filteredPrestations.length > 0
            ? filteredPrestations.map((prestation, index) => (
                <div
                  key={index}
                  className="p-4 border rounded-md shadow-md hover:shadow-lg bg-white"
                >
                  <h3 className="text-lg font-semibold">{prestation.name}</h3>
                  <p className="text-gray-500">{prestation.price}</p>
                  <p className="text-yellow-500">⭐ {prestation.rating}</p>
                </div>
              ))
            : null}
        </div>
      )}
    </div>
  );
}
