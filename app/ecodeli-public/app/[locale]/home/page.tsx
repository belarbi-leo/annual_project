"use client";

import { useState } from "react";

// Données
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

export default function Connected() {
  // États pour les prestations
  const [selectedCategory, setSelectedCategory] = useState(null);
  const [selectedSubCategory, setSelectedSubCategory] = useState(null);
  const [searchQuery, setSearchQuery] = useState("");
  const [locationQuery, setLocationQuery] = useState("");
  const [filter, setFilter] = useState("all");

  // États pour le tableau de bord
  const [livraisons, setLivraisons] = useState([
    { id: 1, status: "En cours", client: "Jean Dupont", date: "31/03/2025" },
    { id: 2, status: "Livré", client: "Sophie Martin", date: "30/03/2025" },
  ]);

  const [prestationsActives, setPrestationsActives] = useState([
    { id: 1, type: "Nettoyage", status: "Confirmé" },
    { id: 2, type: "Réparation vélo", status: "En attente" },
  ]);

  const [topPrestations, setTopPrestations] = useState([
    { id: 1, category: "Nettoyage", name: "Nettoyage complet", rating: 4.8 },
    { id: 2, category: "Réparation", name: "Réparation de vélo", rating: 4.6 },
    { id: 3, category: "Entretien", name: "Jardinage", rating: 4.5 },
  ]);

  const currentCategory = categories.find(
    (cat) => cat.name === selectedCategory
  );
  
  const filteredPrestations = selectedSubCategory
    ? prestations[selectedSubCategory]?.filter((p) =>
        p.name.toLowerCase().includes(searchQuery.toLowerCase())
      ) || []
    : [];

  return (
    <div className="min-h-screen bg-gradient-to-b from-gray-50 to-gray-100 dark:from-gray-900 dark:to-gray-800">
      {/* Hero Section avec appel à l'action */}
      <div className="bg-gradient-to-r from-emerald-500 to-teal-500 text-white">
        <div className="container mx-auto px-4 py-16 flex flex-col md:flex-row items-center justify-between">
          <div className="md:w-3/4 mb-8 md:mb-0">
            <h2 className="text-4xl font-bold mb-4">Trouvez le service qu'il vous faut</h2>
            <p className="text-xl mb-6">La livraison repensée, solidaire et responsable ainsi que des experts qualifiés à votre service pour tous vos besoins quotidiens</p>
            <div className="flex flex-col sm:flex-row gap-4">
              <button className="bg-white text-emerald-600 px-6 py-3 rounded-lg font-semibold hover:bg-gray-100 transition-colors shadow-lg">
                Nouvelle livraison
              </button>
              <button className="bg-transparent border-2 border-white px-6 py-3 rounded-lg font-semibold hover:bg-white/10 transition-colors">
                Devenir prestataire
              </button>
            </div>
          </div>
        </div>
      </div>

      <div className="container mx-auto px-4 py-12">
        {/* Barre de recherche principale */}
        <div className="bg-white dark:bg-gray-800 rounded-xl shadow-xl p-6 mb-12 -mt-20">
          <h3 className="text-xl font-semibold mb-4 text-gray-800 dark:text-white">Rechercher un service</h3>
          <div className="flex flex-col md:flex-row gap-4">
            <div className="flex-1">
              <label className="block text-sm font-medium text-gray-600 dark:text-gray-300 mb-1">Service</label>
              <input
                type="text"
                placeholder="Que recherchez-vous ?"
                className="w-full px-4 py-3 rounded-lg bg-gray-50 dark:bg-gray-700 border border-gray-200 dark:border-gray-600 text-gray-900 dark:text-white focus:outline-none focus:ring-2 focus:ring-emerald-500"
                value={searchQuery}
                onChange={(e) => setSearchQuery(e.target.value)}
              />
            </div>
            <div className="flex-1">
              <label className="block text-sm font-medium text-gray-600 dark:text-gray-300 mb-1">Localisation</label>
              <input
                type="text"
                placeholder="Où ?"
                className="w-full px-4 py-3 rounded-lg bg-gray-50 dark:bg-gray-700 border border-gray-200 dark:border-gray-600 text-gray-900 dark:text-white focus:outline-none focus:ring-2 focus:ring-emerald-500"
                value={locationQuery}
                onChange={(e) => setLocationQuery(e.target.value)}
              />
            </div>
            <div className="md:self-end">
              <button className="w-full px-6 py-3 bg-emerald-500 hover:bg-emerald-600 text-white rounded-lg font-semibold shadow-md transition-colors">
                Rechercher
              </button>
            </div>
          </div>
        </div>

        {/* Statistiques */}
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-12">
          <div className="bg-white dark:bg-gray-800 p-6 rounded-xl shadow-md hover:shadow-lg transition-shadow border-l-4 border-emerald-500">
            <div className="flex items-center">
              <div className="p-3 bg-emerald-100 dark:bg-emerald-900 rounded-full">
                <span className="text-2xl">🚚</span>
              </div>
              <div className="ml-4">
                <p className="text-sm text-gray-500 dark:text-gray-400">Livraisons en cours</p>
                <p className="text-2xl font-bold text-gray-800 dark:text-white">{livraisons.length}</p>
              </div>
            </div>
          </div>
          <div className="bg-white dark:bg-gray-800 p-6 rounded-xl shadow-md hover:shadow-lg transition-shadow border-l-4 border-blue-500">
            <div className="flex items-center">
              <div className="p-3 bg-blue-100 dark:bg-blue-900 rounded-full">
                <span className="text-2xl">🛠️</span>
              </div>
              <div className="ml-4">
                <p className="text-sm text-gray-500 dark:text-gray-400">Prestations actives</p>
                <p className="text-2xl font-bold text-gray-800 dark:text-white">{prestationsActives.length}</p>
              </div>
            </div>
          </div>
          <div className="bg-white dark:bg-gray-800 p-6 rounded-xl shadow-md hover:shadow-lg transition-shadow border-l-4 border-amber-500">
            <div className="flex items-center">
              <div className="p-3 bg-amber-100 dark:bg-amber-900 rounded-full">
                <span className="text-2xl">⭐</span>
              </div>
              <div className="ml-4">
                <p className="text-sm text-gray-500 dark:text-gray-400">Note moyenne</p>
                <p className="text-2xl font-bold text-gray-800 dark:text-white">4.7</p>
              </div>
            </div>
          </div>
        </div>

        {/* Catégories */}
        <div className="mb-12">
          <h2 className="text-2xl font-bold text-gray-800 dark:text-white mb-6">Explorez nos catégories</h2>
          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-5 gap-6">
            {categories.map((cat) => (
              <button
                key={cat.name}
                onClick={() => {
                  setSelectedCategory(cat.name);
                  setSelectedSubCategory(null);
                }}
                className={`p-6 rounded-xl flex flex-col items-center text-center transition-all
                  ${selectedCategory === cat.name 
                    ? "bg-emerald-500 text-white shadow-lg" 
                    : "bg-white dark:bg-gray-800 text-gray-800 dark:text-white hover:shadow-md"
                  }`}
              >
                <span className="text-4xl mb-3">{cat.icon}</span>
                <h3 className="font-medium">{cat.name}</h3>
              </button>
            ))}
          </div>
        </div>

        {/* Sous-catégories */}
        {selectedCategory && currentCategory?.subcategories.length > 0 && (
          <div className="mb-12">
            <h3 className="text-xl font-semibold text-gray-800 dark:text-white mb-4">
              Choisissez une spécialité
            </h3>
            <div className="flex flex-wrap gap-3">
              {currentCategory.subcategories.map((subCat) => (
                <button
                  key={subCat}
                  onClick={() => setSelectedSubCategory(subCat)}
                  className={`px-4 py-2 rounded-full transition-colors
                    ${selectedSubCategory === subCat
                      ? "bg-emerald-500 text-white"
                      : "bg-white dark:bg-gray-800 text-gray-700 dark:text-gray-200 border border-gray-200 dark:border-gray-700 hover:border-emerald-500"
                    }`}
                >
                  {subCat}
                </button>
              ))}
            </div>
          </div>
        )}

        {/* Prestations */}
        {selectedSubCategory && (
          <div className="mb-12">
            <div className="flex justify-between items-center mb-6">
              <h3 className="text-2xl font-bold text-gray-800 dark:text-white">
                Prestations disponibles
              </h3>
              <select
                className="px-4 py-2 rounded-lg bg-white dark:bg-gray-800 border border-gray-200 dark:border-gray-700 text-gray-800 dark:text-white"
                value={filter}
                onChange={(e) => setFilter(e.target.value)}
              >
                <option value="all">Trier par</option>
                <option value="price">Prix ↑</option>
                <option value="rating">Note ↓</option>
              </select>
            </div>
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
              {filteredPrestations.length > 0
                ? filteredPrestations.map((prestation, index) => (
                    <div
                      key={index}
                      className="bg-white dark:bg-gray-800 rounded-xl shadow-md hover:shadow-lg transition-all overflow-hidden group"
                    >
                      <div className="h-40 bg-gradient-to-r from-emerald-400 to-teal-500 flex items-center justify-center">
                        <span className="text-5xl">{currentCategory.icon}</span>
                      </div>
                      <div className="p-6">
                        <h4 className="text-xl font-semibold text-gray-800 dark:text-white mb-2">
                          {prestation.name}
                        </h4>
                        <p className="text-gray-600 dark:text-gray-300 mb-3">
                          {selectedSubCategory}
                        </p>
                        <div className="flex justify-between items-center">
                          <span className="text-lg font-bold text-emerald-600 dark:text-emerald-400">
                            {prestation.price}
                          </span>
                          <span className="flex items-center text-amber-500">
                            <span className="mr-1">{prestation.rating}</span>
                            <span>⭐</span>
                          </span>
                        </div>
                        <button className="w-full mt-4 py-2 bg-emerald-500 text-white rounded-lg font-medium opacity-0 group-hover:opacity-100 transition-opacity">
                          Réserver
                        </button>
                      </div>
                    </div>
                  ))
                : (
                  <div className="col-span-full flex flex-col items-center py-12 text-gray-500 dark:text-gray-400">
                    <span className="text-5xl mb-4">🔍</span>
                    <p className="text-xl">Aucune prestation trouvée</p>
                  </div>
                )}
            </div>
          </div>
        )}

        {/* Top Prestations */}
        <div className="mb-12">
          <h2 className="text-2xl font-bold text-gray-800 dark:text-white mb-6">Meilleures prestations</h2>
          <div className="bg-white dark:bg-gray-800 rounded-xl shadow-md overflow-hidden">
            {topPrestations.map((prestation, index) => (
              <div 
                key={prestation.id}
                className={`p-4 flex justify-between items-center ${
                  index < topPrestations.length - 1 ? "border-b border-gray-200 dark:border-gray-700" : ""
                }`}
              >
                <div className="flex items-center">
                  <div className="p-3 bg-amber-100 dark:bg-amber-900 rounded-full mr-4">
                    <span className="text-xl">⭐</span>
                  </div>
                  <div>
                    <h4 className="font-semibold text-gray-800 dark:text-white">{prestation.name}</h4>
                    <p className="text-gray-500 dark:text-gray-400 text-sm">{prestation.category}</p>
                  </div>
                </div>
                <div className="flex items-center">
                  <span className="text-xl font-bold text-amber-500 mr-2">{prestation.rating.toFixed(1)}</span>
                  <button className="ml-4 px-4 py-2 bg-emerald-100 dark:bg-emerald-900 text-emerald-600 dark:text-emerald-400 rounded-lg text-sm font-medium">
                    Voir détails
                  </button>
                </div>
              </div>
            ))}
          </div>
        </div>

        {/* Activité récente */}
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6 mb-12">
          {/* Livraisons récentes */}
          <div className="bg-white dark:bg-gray-800 rounded-xl shadow-md p-6">
            <div className="flex items-center justify-between mb-4">
              <h3 className="text-xl font-semibold text-gray-800 dark:text-white">Livraisons récentes</h3>
              <span className="text-2xl">📦</span>
            </div>
            {livraisons.map((livraison) => (
              <div 
                key={livraison.id} 
                className="mb-4 p-4 border border-gray-100 dark:border-gray-700 rounded-lg bg-gray-50 dark:bg-gray-900"
              >
                <div className="flex justify-between">
                  <p className="font-medium text-gray-800 dark:text-white">{livraison.client}</p>
                  <span className={`text-sm px-2 py-1 rounded-full ${
                    livraison.status === "En cours" 
                      ? "bg-blue-100 text-blue-600 dark:bg-blue-900 dark:text-blue-300" 
                      : "bg-green-100 text-green-600 dark:bg-green-900 dark:text-green-300"
                  }`}>
                    {livraison.status}
                  </span>
                </div>
                <p className="text-sm text-gray-500 dark:text-gray-400 mt-1">{livraison.date}</p>
              </div>
            ))}
          </div>

          {/* Prestations actives */}
          <div className="bg-white dark:bg-gray-800 rounded-xl shadow-md p-6">
            <div className="flex items-center justify-between mb-4">
              <h3 className="text-xl font-semibold text-gray-800 dark:text-white">Prestations à venir</h3>
              <span className="text-2xl">🛠️</span>
            </div>
            {prestationsActives.map((prestation) => (
              <div 
                key={prestation.id} 
                className="mb-4 p-4 border border-gray-100 dark:border-gray-700 rounded-lg bg-gray-50 dark:bg-gray-900"
              >
                <div className="flex justify-between">
                  <p className="font-medium text-gray-800 dark:text-white">{prestation.type}</p>
                  <span className={`text-sm px-2 py-1 rounded-full ${
                    prestation.status === "Confirmé" 
                      ? "bg-green-100 text-green-600 dark:bg-green-900 dark:text-green-300" 
                      : "bg-yellow-100 text-yellow-600 dark:bg-yellow-900 dark:text-yellow-300"
                  }`}>
                    {prestation.status}
                  </span>
                </div>
              </div>
            ))}
          </div>
        </div>

        {/* Boutons d'action rapide */}
        <div className="flex flex-col md:flex-row gap-4 mb-12">
          <button className="flex-1 bg-gradient-to-r from-blue-500 to-blue-600 text-white p-6 rounded-xl flex items-center justify-center gap-3 shadow-md hover:shadow-lg transition-shadow">
            <span className="text-3xl">📦</span>
            <span className="text-xl font-semibold">Nouvelle livraison</span>
          </button>
          <button className="flex-1 bg-gradient-to-r from-emerald-500 to-emerald-600 text-white p-6 rounded-xl flex items-center justify-center gap-3 shadow-md hover:shadow-lg transition-shadow">
            <span className="text-3xl">🛠️</span>
            <span className="text-xl font-semibold">Nouvelle prestation</span>
          </button>
        </div>
      </div>

      {/* Footer */}
      <div className="bg-gray-800 text-white py-12">
        <div className="container mx-auto px-4">
          <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
            <div>
              <h4 className="text-xl font-bold mb-4">PrestServ</h4>
              <p className="text-gray-400">Votre plateforme de services à la demande pour tous vos besoins quotidiens.</p>
            </div>
            <div>
              <h4 className="text-lg font-semibold mb-4">Liens rapides</h4>
              <ul className="space-y-2 text-gray-400">
                <li><a href="#" className="hover:text-white transition-colors">À propos</a></li>
                <li><a href="#" className="hover:text-white transition-colors">Services</a></li>
                <li><a href="#" className="hover:text-white transition-colors">Devenir prestataire</a></li>
                <li><a href="#" className="hover:text-white transition-colors">Contact</a></li>
              </ul>
            </div>
            <div>
              <h4 className="text-lg font-semibold mb-4">Contact</h4>
              <p className="text-gray-400 mb-2">info@prestserv.com</p>
              <p className="text-gray-400">+33 1 23 45 67 89</p>
            </div>
          </div>
          <div className="border-t border-gray-700 mt-8 pt-8 text-center text-gray-500">
            <p>© 2025 PrestServ. Tous droits réservés.</p>
          </div>
        </div>
      </div>
    </div>
  );
}