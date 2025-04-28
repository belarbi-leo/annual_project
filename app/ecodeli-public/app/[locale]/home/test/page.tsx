"use client";

import { useEffect, useState } from "react";
import type { Services } from '@/lib/types';
import { fetchServicesByAuth } from "@/lib/services/fetchServicesByAuth"; 

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

// Données des statistiques client
const clientStats = {
  totalCommandes: 24,
  enCours: 3,
  enAttente: 1,
  completees: 20,
  economieCarbone: "15.3kg",
  depensesMois: "245.50€",
  depensesTotal: "1,450.80€",
};

// Données d'abonnements
const subscriptions = [
  {
    type: "Standard",
    icon: "🌱",
    price: "0€/mois",
    forProfessionals: false,
    features: [
      "Accès aux prestations de base",
      "3 demandes par mois",
      "Support par email"
    ],
    recommended: false,
    color: "blue"
  },
  {
    type: "Premium",
    icon: "⭐",
    price: "9.99€/mois",
    forProfessionals: false,
    features: [
      "Accès à toutes les prestations",
      "Demandes illimitées",
      "Support prioritaire",
      "Réductions exclusives de 5%"
    ],
    recommended: true,
    color: "emerald"
  },
  {
    type: "Pro",
    icon: "👔",
    price: "24.99€/mois",
    forProfessionals: true,
    features: [
      "Tout Premium inclus",
      "Visibilité accrue pour vos services",
      "Recommandations prioritaires",
      "Outils d'analyse et statistiques",
      "Support dédié 7j/7"
    ],
    recommended: false,
    color: "purple"
  }
];

// Données des prestations en tendance
const trendingPrestations = [
  { 
    id: 1, 
    name: "Nettoyage Premium", 
    category: "Services à la personne", 
    rating: 4.9,
    price: "35€/h",
    provider: "CleanExpert",
    reviews: 124,
    image: "/api/placeholder/400/200"
  },
  { 
    id: 2, 
    name: "Dépannage informatique", 
    category: "Informatique & Digital", 
    rating: 4.8,
    price: "45€/h",
    provider: "TechRescue",
    reviews: 89,
    image: "/api/placeholder/400/200"
  },
  { 
    id: 3, 
    name: "Livraison Express", 
    category: "Transport & Livraison", 
    rating: 4.7,
    price: "15€",
    provider: "SpeedyGo",
    reviews: 156,
    image: "/api/placeholder/400/200"
  },
];

export default function Home() {
  const [services, setServices] = useState<Services[]>([]);
  // États pour les prestations
  const [selectedCategory, setSelectedCategory] = useState(null);
  const [selectedSubCategory, setSelectedSubCategory] = useState(null);
  const [searchQuery, setSearchQuery] = useState("");
  const [locationQuery, setLocationQuery] = useState("");
  const [filter, setFilter] = useState("all");
  const [showSubscriptionModal, setShowSubscriptionModal] = useState(false);
  const [selectedTab, setSelectedTab] = useState("tous");

  // États pour le tableau de bord
  const [livraisons, setLivraisons] = useState([
    { id: 1, status: "En cours", client: "Jean Dupont", date: "31/03/2025", icon: "📦" },
    { id: 2, status: "Livré", client: "Sophie Martin", date: "30/03/2025", icon: "📦" },
  ]);

  const [prestationsActives, setPrestationsActives] = useState([
    { id: 1, type: "Nettoyage", status: "Confirmé", date: "02/05/2025", provider: "CleanExpert", icon: "🧹" },
    { id: 2, type: "Réparation vélo", status: "En attente", date: "05/05/2025", provider: "VéloCare", icon: "🚲" },
    { id: 3, type: "Cours de cuisine", status: "Confirmé", date: "10/05/2025", provider: "ChefAcademy", icon: "👨‍🍳" },
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

  useEffect(() => {
    const loadServices = async () => {
      const val = ['part', 'pro', 'all'];
      const data = await fetchServicesByAuth(val);
      setServices(data);
    };
    loadServices();
  },);

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
                Nouvelle prestation
              </button>
            </div>
          </div>
        </div>
      </div>

      <div className="container mx-auto px-4 py-12">
        {/* Barre de recherche principale */}
        <div className="bg-white dark:bg-gray-800 rounded-xl shadow-xl p-6 mb-12 -mt-20">
          <h2 className="text-2xl font-semibold mb-4 text-gray-800 dark:text-white">Explorez nos catégories</h2>
          <div className="flex flex-col md:flex-row gap-4">
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
          {selectedCategory && currentCategory?.subcategories.length > 0 && (
          <div className="mb-10">
            <h3 className="text-xl font-semibold text-gray-800 dark:text-white my-4">
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
        </div>

        {/* Tableau de bord de l'activité client - Version améliorée */}
        <div className="mb-12">
          <div className="flex justify-between items-center mb-6">
            <h2 className="text-2xl font-bold text-gray-800 dark:text-white">Tableau de bord personnel</h2>
            <button onClick={() => setShowSubscriptionModal(true)} className="flex items-center gap-2 bg-gradient-to-r from-amber-400 to-amber-500 text-white px-4 py-2 rounded-lg font-medium hover:from-amber-500 hover:to-amber-600 transition-all">
              <span>⭐</span> Passer Premium
            </button>
          </div>
          
          {/* Statistiques globales */}
          <div className="grid grid-cols-2 md:grid-cols-4 gap-4 mb-6">
            <div className="bg-white dark:bg-gray-800 rounded-xl shadow-md p-4 flex flex-col items-center">
              <div className="text-3xl text-emerald-500 mb-2">🛒</div>
              <p className="text-gray-500 dark:text-gray-400 text-sm">Total commandes</p>
              <p className="text-2xl font-bold text-gray-800 dark:text-white">{clientStats.totalCommandes}</p>
            </div>
            <div className="bg-white dark:bg-gray-800 rounded-xl shadow-md p-4 flex flex-col items-center">
              <div className="text-3xl text-blue-500 mb-2">💰</div>
              <p className="text-gray-500 dark:text-gray-400 text-sm">Ce mois-ci</p>
              <p className="text-2xl font-bold text-gray-800 dark:text-white">{clientStats.depensesMois}</p>
            </div>
            <div className="bg-white dark:bg-gray-800 rounded-xl shadow-md p-4 flex flex-col items-center">
              <div className="text-3xl text-green-500 mb-2">🌍</div>
              <p className="text-gray-500 dark:text-gray-400 text-sm">CO₂ économisé</p>
              <p className="text-2xl font-bold text-gray-800 dark:text-white">{clientStats.economieCarbone}</p>
            </div>
            <div className="bg-white dark:bg-gray-800 rounded-xl shadow-md p-4 flex flex-col items-center">
              <div className="text-3xl text-purple-500 mb-2">⏱️</div>
              <p className="text-gray-500 dark:text-gray-400 text-sm">En cours</p>
              <p className="text-2xl font-bold text-gray-800 dark:text-white">{clientStats.enCours}</p>
            </div>
          </div>
          
          {/* Activités récentes - Onglets */}
          <div className="bg-white dark:bg-gray-800 rounded-xl shadow-md overflow-hidden">
            <div className="flex border-b border-gray-200 dark:border-gray-700">
              <button 
                onClick={() => setSelectedTab("tous")}
                className={`flex-1 py-4 px-6 text-center font-medium transition-colors ${
                  selectedTab === "tous" 
                    ? "bg-emerald-50 dark:bg-emerald-900/30 text-emerald-600 dark:text-emerald-400 border-b-2 border-emerald-500" 
                    : "text-gray-600 dark:text-gray-300 hover:bg-gray-50 dark:hover:bg-gray-700/50"
                }`}
              >
                Toutes les activités
              </button>
              <button 
                onClick={() => setSelectedTab("livraisons")}
                className={`flex-1 py-4 px-6 text-center font-medium transition-colors ${
                  selectedTab === "livraisons" 
                    ? "bg-emerald-50 dark:bg-emerald-900/30 text-emerald-600 dark:text-emerald-400 border-b-2 border-emerald-500" 
                    : "text-gray-600 dark:text-gray-300 hover:bg-gray-50 dark:hover:bg-gray-700/50"
                }`}
              >
                Livraisons
              </button>
              <button 
                onClick={() => setSelectedTab("prestations")}
                className={`flex-1 py-4 px-6 text-center font-medium transition-colors ${
                  selectedTab === "prestations" 
                    ? "bg-emerald-50 dark:bg-emerald-900/30 text-emerald-600 dark:text-emerald-400 border-b-2 border-emerald-500" 
                    : "text-gray-600 dark:text-gray-300 hover:bg-gray-50 dark:hover:bg-gray-700/50"
                }`}
              >
                Prestations
              </button>
            </div>
            
            <div className="p-6">
              {(selectedTab === "tous" || selectedTab === "prestations") && (
                <div className="mb-6">
                  <h3 className="text-lg font-semibold text-gray-800 dark:text-white mb-4">Prestations à venir</h3>
                  <div className="space-y-4">
                    {prestationsActives.map((prestation) => (
                      <div 
                        key={prestation.id} 
                        className="p-4 border border-gray-100 dark:border-gray-700 rounded-lg bg-gray-50 dark:bg-gray-900 hover:shadow-md transition-shadow"
                      >
                        <div className="flex items-center justify-between">
                          <div className="flex items-center">
                            <div className="p-3 bg-emerald-100 dark:bg-emerald-900 rounded-full mr-4 text-xl">
                              {prestation.icon}
                            </div>
                            <div>
                              <p className="font-medium text-gray-800 dark:text-white">{prestation.type}</p>
                              <p className="text-sm text-gray-500 dark:text-gray-400">
                                {prestation.provider} • {prestation.date}
                              </p>
                            </div>
                          </div>
                          <div>
                            <span className={`text-sm px-3 py-1 rounded-full ${
                              prestation.status === "Confirmé" 
                                ? "bg-green-100 text-green-600 dark:bg-green-900 dark:text-green-300" 
                                : "bg-yellow-100 text-yellow-600 dark:bg-yellow-900 dark:text-yellow-300"
                            }`}>
                              {prestation.status}
                            </span>
                          </div>
                        </div>
                      </div>
                    ))}
                  </div>
                </div>
              )}
              
              {(selectedTab === "tous" || selectedTab === "livraisons") && (
                <div>
                  <h3 className="text-lg font-semibold text-gray-800 dark:text-white mb-4">Livraisons récentes</h3>
                  <div className="space-y-4">
                    {livraisons.map((livraison) => (
                      <div 
                        key={livraison.id} 
                        className="p-4 border border-gray-100 dark:border-gray-700 rounded-lg bg-gray-50 dark:bg-gray-900 hover:shadow-md transition-shadow"
                      >
                        <div className="flex items-center justify-between">
                          <div className="flex items-center">
                            <div className="p-3 bg-blue-100 dark:bg-blue-900 rounded-full mr-4 text-xl">
                              {livraison.icon}
                            </div>
                            <div>
                              <p className="font-medium text-gray-800 dark:text-white">{livraison.client}</p>
                              <p className="text-sm text-gray-500 dark:text-gray-400">{livraison.date}</p>
                            </div>
                          </div>
                          <div>
                            <span className={`text-sm px-3 py-1 rounded-full ${
                              livraison.status === "En cours" 
                                ? "bg-blue-100 text-blue-600 dark:bg-blue-900 dark:text-blue-300" 
                                : "bg-green-100 text-green-600 dark:bg-green-900 dark:text-green-300"
                            }`}>
                              {livraison.status}
                            </span>
                          </div>
                        </div>
                      </div>
                    ))}
                  </div>
                </div>
              )}
            </div>
          </div>
        </div>

        {/* Prestations du moment - Section améliorée */}
        <div className="mb-12">
          <h2 className="text-2xl font-bold text-gray-800 dark:text-white mb-6">Prestations du moment</h2>
          <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
            {trendingPrestations.map((prestation) => (
              <div 
                key={prestation.id}
                className="bg-white dark:bg-gray-800 rounded-xl shadow-md overflow-hidden hover:shadow-lg transition-shadow"
              >
                <div className="h-40 bg-gray-200 dark:bg-gray-700 overflow-hidden">
                  <img src={prestation.image} alt={prestation.name} className="w-full h-full object-cover" />
                </div>
                <div className="p-6">
                  <div className="flex justify-between items-start mb-2">
                    <h3 className="font-semibold text-lg text-gray-800 dark:text-white">{prestation.name}</h3>
                    <span className="flex items-center bg-amber-100 dark:bg-amber-900 text-amber-600 dark:text-amber-400 px-2 py-1 rounded-md text-sm">
                      ⭐ {prestation.rating.toFixed(1)}
                    </span>
                  </div>
                  <p className="text-gray-500 dark:text-gray-400 text-sm mb-3">{prestation.category} • {prestation.reviews} avis</p>
                  <div className="flex justify-between items-center">
                    <p className="font-bold text-emerald-600 dark:text-emerald-500">{prestation.price}</p>
                    <button className="px-4 py-2 bg-emerald-500 hover:bg-emerald-600 text-white rounded-lg text-sm font-medium transition-colors">
                      Réserver
                    </button>
                  </div>
                </div>
              </div>
            ))}
          </div>
          <div className="mt-8 text-center">
            <button className="px-6 py-3 bg-white dark:bg-gray-800 border border-emerald-500 text-emerald-500 rounded-lg font-medium hover:bg-emerald-50 dark:hover:bg-emerald-900/20 transition-colors">
              Voir toutes les prestations
            </button>
          </div>
        </div>

        {/* Modal d'abonnements */}
        {showSubscriptionModal && (
          <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50 p-4">
            <div className="bg-white dark:bg-gray-800 rounded-xl shadow-2xl w-full max-w-4xl max-h-[90vh] overflow-y-auto">
              <div className="p-6 border-b border-gray-200 dark:border-gray-700 flex justify-between items-center">
                <h3 className="text-2xl font-bold text-gray-800 dark:text-white">Nos abonnements</h3>
                <button 
                  onClick={() => setShowSubscriptionModal(false)}
                  className="text-gray-500 hover:text-gray-700 dark:text-gray-400 dark:hover:text-gray-200 text-2xl"
                >
                  ✕
                </button>
              </div>
              
              <div className="p-6">
                <div className="mb-6">
                  <p className="text-gray-600 dark:text-gray-300">Choisissez l'abonnement qui correspond à vos besoins et profitez de tous nos services.</p>
                </div>
                
                <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
                  {subscriptions.map((sub) => (
                    <div 
                      key={sub.type}
                      className={`border rounded-xl p-6 relative ${
                        sub.recommended 
                          ? `border-${sub.color}-500 shadow-lg` 
                          : "border-gray-200 dark:border-gray-700"
                      }`}
                    >
                      {sub.recommended && (
                        <div className={`absolute -top-3 left-1/2 transform -translate-x-1/2 bg-${sub.color}-500 text-white px-4 py-1 rounded-full text-sm font-medium`}>
                          Recommandé
                        </div>
                      )}
                      
                      <div className="flex flex-col items-center text-center mb-6">
                        <span className="text-4xl mb-3">{sub.icon}</span>
                        <h4 className="text-xl font-bold text-gray-800 dark:text-white">{sub.type}</h4>
                        <p className={`text-2xl font-bold mt-2 text-${sub.color}-600 dark:text-${sub.color}-500`}>{sub.price}</p>
                        {sub.forProfessionals && (
                          <span className="mt-2 bg-purple-100 dark:bg-purple-900 text-purple-600 dark:text-purple-300 px-3 py-1 rounded-full text-xs">
                            Pour professionnels
                          </span>
                        )}
                      </div>
                      
                      <ul className="space-y-3 mb-6">
                        {sub.features.map((feature, index) => (
                          <li key={index} className="flex items-start">
                            <span className="text-green-500 mr-2">✓</span>
                            <span className="text-gray-600 dark:text-gray-300 text-sm">{feature}</span>
                          </li>
                        ))}
                      </ul>
                      
                      <button className={`w-full py-3 rounded-lg font-medium ${
                        sub.recommended 
                          ? `bg-${sub.color}-500 hover:bg-${sub.color}-600 text-white` 
                          : "bg-gray-100 dark:bg-gray-700 text-gray-800 dark:text-white hover:bg-gray-200 dark:hover:bg-gray-600"
                      } transition-colors`}>
                        {sub.recommended ? "Commencer maintenant" : "Choisir"}
                      </button>
                    </div>
                  ))}
                </div>
                
                <div className="mt-8 text-center text-gray-600 dark:text-gray-400 text-sm">
                  <p>Vous pouvez changer ou annuler votre abonnement à tout moment.</p>
                  <p className="mt-2">Des questions ? <a href="#" className="text-emerald-500 hover:underline">Contactez notre équipe</a></p>
                </div>
              </div>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}
// "use client";

// import { useState } from "react";

// // Données
// const categories = [
//   {
//     name: "Services à la personne",
//     icon: "👩‍🔧",
//     subcategories: ["Garde d'enfants", "Aide à domicile", "Coach personnel"],
//   },
//   {
//     name: "Transport & Livraison",
//     icon: "🚚",
//     subcategories: ["Déménagement", "Livraison de colis", "Taxi privé"],
//   },
//   {
//     name: "Travaux & Réparations",
//     icon: "🔧",
//     subcategories: ["Plomberie", "Électricité", "Menuiserie"],
//   },
//   {
//     name: "Informatique & Digital",
//     icon: "💻",
//     subcategories: ["Développement Web", "Support IT", "Montage vidéo"],
//   },
//   {
//     name: "Événementiel & Loisirs",
//     icon: "🎉",
//     subcategories: ["DJ", "Photographe", "Organisateur d'événements"],
//   },
// ];

// const prestations = {
//   "Garde d'enfants": [
//     { name: "BabyCare+", price: "15€/h", rating: 4.8 },
//     { name: "BabyBaby+", price: "25€/h", rating: 4.3 },
//   ],
//   "Aide à domicile": [
//     { name: "HomeHelp", price: "12€/h", rating: 4.6 },
//     { name: "HomeSweatHome", price: "24€/h", rating: 3.6 },
//   ],
//   "Coach personnel": [{ name: "FitCoach", price: "30€/h", rating: 4.9 }],
//   "Déménagement": [{ name: "Démélogistics", price: "50€", rating: 4.5 }],
//   "Livraison de colis": [{ name: "Speedy Express", price: "20€", rating: 4.7 }],
//   "Taxi privé": [{ name: "UrbanCab", price: "30€", rating: 4.2 }],
//   "Plomberie": [{ name: "AquaFix", price: "40€", rating: 4.4 }],
//   "Électricité": [{ name: "VoltSafe", price: "45€", rating: 4.3 }],
//   "Menuiserie": [{ name: "BoisExpert", price: "50€", rating: 4.1 }],
//   "Développement Web": [{ name: "WebSolutions", price: "500€", rating: 4.9 }],
//   "Support IT": [{ name: "ITAssist", price: "40€/h", rating: 4.5 }],
//   "Montage vidéo": [{ name: "VidéoPro", price: "100€", rating: 4.6 }],
//   "DJ": [{ name: "SoundMax", price: "200€", rating: 4.7 }],
//   "Photographe": [{ name: "PhotoArt", price: "150€", rating: 4.8 }],
//   "Organisateur d'événements": [{ name: "EventMaster", price: "500€", rating: 4.9 }],
// };

// export default function Connected() {
//   // États pour les prestations
//   const [selectedCategory, setSelectedCategory] = useState(null);
//   const [selectedSubCategory, setSelectedSubCategory] = useState(null);
//   const [searchQuery, setSearchQuery] = useState("");
//   const [locationQuery, setLocationQuery] = useState("");
//   const [filter, setFilter] = useState("all");

//   // États pour le tableau de bord
//   const [livraisons, setLivraisons] = useState([
//     { id: 1, status: "En cours", client: "Jean Dupont", date: "31/03/2025" },
//     { id: 2, status: "Livré", client: "Sophie Martin", date: "30/03/2025" },
//   ]);

//   const [prestationsActives, setPrestationsActives] = useState([
//     { id: 1, type: "Nettoyage", status: "Confirmé" },
//     { id: 2, type: "Réparation vélo", status: "En attente" },
//   ]);

//   const [topPrestations, setTopPrestations] = useState([
//     { id: 1, category: "Nettoyage", name: "Nettoyage complet", rating: 4.8 },
//     { id: 2, category: "Réparation", name: "Réparation de vélo", rating: 4.6 },
//     { id: 3, category: "Entretien", name: "Jardinage", rating: 4.5 },
//   ]);

//   const currentCategory = categories.find(
//     (cat) => cat.name === selectedCategory
//   );
  
//   const filteredPrestations = selectedSubCategory
//     ? prestations[selectedSubCategory]?.filter((p) =>
//         p.name.toLowerCase().includes(searchQuery.toLowerCase())
//       ) || []
//     : [];

//   return (
//     <div className="min-h-screen bg-gradient-to-b from-gray-50 to-gray-100 dark:from-gray-900 dark:to-gray-800">
//       {/* Hero Section avec appel à l'action */}
//       <div className="bg-gradient-to-r from-emerald-500 to-teal-500 text-white">
//         <div className="container mx-auto px-4 py-16 flex flex-col md:flex-row items-center justify-between">
//           <div className="md:w-3/4 mb-8 md:mb-0">
//             <h2 className="text-4xl font-bold mb-4">Trouvez le service qu'il vous faut</h2>
//             <p className="text-xl mb-6">La livraison repensée, solidaire et responsable ainsi que des experts qualifiés à votre service pour tous vos besoins quotidiens</p>
//             <div className="flex flex-col sm:flex-row gap-4">
//               <button className="bg-white text-emerald-600 px-6 py-3 rounded-lg font-semibold hover:bg-gray-100 transition-colors shadow-lg">
//                 Nouvelle livraison
//               </button>
//               <button className="bg-transparent border-2 border-white px-6 py-3 rounded-lg font-semibold hover:bg-white/10 transition-colors">
//                 Nouvelle prestation
//               </button>
//             </div>
//           </div>
//         </div>
//       </div>

//       <div className="container mx-auto px-4 py-12">
//         {/* Barre de recherche principale */}
//         <div className="bg-white dark:bg-gray-800 rounded-xl shadow-xl p-6 mb-12 -mt-20">
//           <h3 className="text-xl font-semibold mb-4 text-gray-800 dark:text-white">Rechercher un service</h3>
//           <div className="flex flex-col md:flex-row gap-4">
//             <div className="flex-1">
//               <label className="block text-sm font-medium text-gray-600 dark:text-gray-300 mb-1">Service</label>
//               <input
//                 type="text"
//                 placeholder="Que recherchez-vous ?"
//                 className="w-full px-4 py-3 rounded-lg bg-gray-50 dark:bg-gray-700 border border-gray-200 dark:border-gray-600 text-gray-900 dark:text-white focus:outline-none focus:ring-2 focus:ring-emerald-500"
//                 value={searchQuery}
//                 onChange={(e) => setSearchQuery(e.target.value)}
//               />
//             </div>
//             <div className="flex-1">
//               <label className="block text-sm font-medium text-gray-600 dark:text-gray-300 mb-1">Localisation</label>
//               <input
//                 type="text"
//                 placeholder="Où ?"
//                 className="w-full px-4 py-3 rounded-lg bg-gray-50 dark:bg-gray-700 border border-gray-200 dark:border-gray-600 text-gray-900 dark:text-white focus:outline-none focus:ring-2 focus:ring-emerald-500"
//                 value={locationQuery}
//                 onChange={(e) => setLocationQuery(e.target.value)}
//               />
//             </div>
//             <div className="md:self-end">
//               <button className="w-full px-6 py-3 bg-emerald-500 hover:bg-emerald-600 text-white rounded-lg font-semibold shadow-md transition-colors">
//                 Rechercher
//               </button>
//             </div>
//           </div>
//         </div>

//         {/* Statistiques */}
//         <div className="grid grid-cols-1 md:grid-cols-3 gap-6 mb-12">
//           <div className="bg-white dark:bg-gray-800 p-6 rounded-xl shadow-md hover:shadow-lg transition-shadow border-l-4 border-emerald-500">
//             <div className="flex items-center">
//               <div className="p-3 bg-emerald-100 dark:bg-emerald-900 rounded-full">
//                 <span className="text-2xl">🚚</span>
//               </div>
//               <div className="ml-4">
//                 <p className="text-sm text-gray-500 dark:text-gray-400">Livraisons en cours</p>
//                 <p className="text-2xl font-bold text-gray-800 dark:text-white">{livraisons.length}</p>
//               </div>
//             </div>
//           </div>
//           <div className="bg-white dark:bg-gray-800 p-6 rounded-xl shadow-md hover:shadow-lg transition-shadow border-l-4 border-blue-500">
//             <div className="flex items-center">
//               <div className="p-3 bg-blue-100 dark:bg-blue-900 rounded-full">
//                 <span className="text-2xl">🛠️</span>
//               </div>
//               <div className="ml-4">
//                 <p className="text-sm text-gray-500 dark:text-gray-400">Prestations actives</p>
//                 <p className="text-2xl font-bold text-gray-800 dark:text-white">{prestationsActives.length}</p>
//               </div>
//             </div>
//           </div>
//           <div className="bg-white dark:bg-gray-800 p-6 rounded-xl shadow-md hover:shadow-lg transition-shadow border-l-4 border-amber-500">
//             <div className="flex items-center">
//               <div className="p-3 bg-amber-100 dark:bg-amber-900 rounded-full">
//                 <span className="text-2xl">⭐</span>
//               </div>
//               <div className="ml-4">
//                 <p className="text-sm text-gray-500 dark:text-gray-400">Note moyenne</p>
//                 <p className="text-2xl font-bold text-gray-800 dark:text-white">4.7</p>
//               </div>
//             </div>
//           </div>
//         </div>

//         {/* Catégories */}
//         <div className="mb-12">
//           <h2 className="text-2xl font-bold text-gray-800 dark:text-white mb-6">Explorez nos catégories</h2>
//           <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-5 gap-6">
//             {categories.map((cat) => (
//               <button
//                 key={cat.name}
//                 onClick={() => {
//                   setSelectedCategory(cat.name);
//                   setSelectedSubCategory(null);
//                 }}
//                 className={`p-6 rounded-xl flex flex-col items-center text-center transition-all
//                   ${selectedCategory === cat.name 
//                     ? "bg-emerald-500 text-white shadow-lg" 
//                     : "bg-white dark:bg-gray-800 text-gray-800 dark:text-white hover:shadow-md"
//                   }`}
//               >
//                 <span className="text-4xl mb-3">{cat.icon}</span>
//                 <h3 className="font-medium">{cat.name}</h3>
//               </button>
//             ))}
//           </div>
//         </div>

//         {/* Sous-catégories */}
//         {selectedCategory && currentCategory?.subcategories.length > 0 && (
//           <div className="mb-12">
//             <h3 className="text-xl font-semibold text-gray-800 dark:text-white mb-4">
//               Choisissez une spécialité
//             </h3>
//             <div className="flex flex-wrap gap-3">
//               {currentCategory.subcategories.map((subCat) => (
//                 <button
//                   key={subCat}
//                   onClick={() => setSelectedSubCategory(subCat)}
//                   className={`px-4 py-2 rounded-full transition-colors
//                     ${selectedSubCategory === subCat
//                       ? "bg-emerald-500 text-white"
//                       : "bg-white dark:bg-gray-800 text-gray-700 dark:text-gray-200 border border-gray-200 dark:border-gray-700 hover:border-emerald-500"
//                     }`}
//                 >
//                   {subCat}
//                 </button>
//               ))}
//             </div>
//           </div>
//         )}

//         {/* Prestations */}
//         {selectedSubCategory && (
//           <div className="mb-12">
//             <div className="flex justify-between items-center mb-6">
//               <h3 className="text-2xl font-bold text-gray-800 dark:text-white">
//                 Prestations disponibles
//               </h3>
//               <select
//                 className="px-4 py-2 rounded-lg bg-white dark:bg-gray-800 border border-gray-200 dark:border-gray-700 text-gray-800 dark:text-white"
//                 value={filter}
//                 onChange={(e) => setFilter(e.target.value)}
//               >
//                 <option value="all">Trier par</option>
//                 <option value="price">Prix ↑</option>
//                 <option value="rating">Note ↓</option>
//               </select>
//             </div>
//             <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
//               {filteredPrestations.length > 0
//                 ? filteredPrestations.map((prestation, index) => (
//                     <div
//                       key={index}
//                       className="bg-white dark:bg-gray-800 rounded-xl shadow-md hover:shadow-lg transition-all overflow-hidden group"
//                     >
//                       <div className="h-40 bg-gradient-to-r from-emerald-400 to-teal-500 flex items-center justify-center">
//                         <span className="text-5xl">{currentCategory.icon}</span>
//                       </div>
//                       <div className="p-6">
//                         <h4 className="text-xl font-semibold text-gray-800 dark:text-white mb-2">
//                           {prestation.name}
//                         </h4>
//                         <p className="text-gray-600 dark:text-gray-300 mb-3">
//                           {selectedSubCategory}
//                         </p>
//                         <div className="flex justify-between items-center">
//                           <span className="text-lg font-bold text-emerald-600 dark:text-emerald-400">
//                             {prestation.price}
//                           </span>
//                           <span className="flex items-center text-amber-500">
//                             <span className="mr-1">{prestation.rating}</span>
//                             <span>⭐</span>
//                           </span>
//                         </div>
//                         <button className="w-full mt-4 py-2 bg-emerald-500 text-white rounded-lg font-medium opacity-0 group-hover:opacity-100 transition-opacity">
//                           Réserver
//                         </button>
//                       </div>
//                     </div>
//                   ))
//                 : (
//                   <div className="col-span-full flex flex-col items-center py-12 text-gray-500 dark:text-gray-400">
//                     <span className="text-5xl mb-4">🔍</span>
//                     <p className="text-xl">Aucune prestation trouvée</p>
//                   </div>
//                 )}
//             </div>
//           </div>
//         )}

//         {/* Top Prestations */}
//         <div className="mb-12">
//           <h2 className="text-2xl font-bold text-gray-800 dark:text-white mb-6">Meilleures prestations</h2>
//           <div className="bg-white dark:bg-gray-800 rounded-xl shadow-md overflow-hidden">
//             {topPrestations.map((prestation, index) => (
//               <div 
//                 key={prestation.id}
//                 className={`p-4 flex justify-between items-center ${
//                   index < topPrestations.length - 1 ? "border-b border-gray-200 dark:border-gray-700" : ""
//                 }`}
//               >
//                 <div className="flex items-center">
//                   <div className="p-3 bg-amber-100 dark:bg-amber-900 rounded-full mr-4">
//                     <span className="text-xl">⭐</span>
//                   </div>
//                   <div>
//                     <h4 className="font-semibold text-gray-800 dark:text-white">{prestation.name}</h4>
//                     <p className="text-gray-500 dark:text-gray-400 text-sm">{prestation.category}</p>
//                   </div>
//                 </div>
//                 <div className="flex items-center">
//                   <span className="text-xl font-bold text-amber-500 mr-2">{prestation.rating.toFixed(1)}</span>
//                   <button className="ml-4 px-4 py-2 bg-emerald-100 dark:bg-emerald-900 text-emerald-600 dark:text-emerald-400 rounded-lg text-sm font-medium">
//                     Voir détails
//                   </button>
//                 </div>
//               </div>
//             ))}
//           </div>
//         </div>

//         {/* Activité récente */}
//         <div className="grid grid-cols-1 md:grid-cols-2 gap-6 mb-12">
//           {/* Livraisons récentes */}
//           <div className="bg-white dark:bg-gray-800 rounded-xl shadow-md p-6">
//             <div className="flex items-center justify-between mb-4">
//               <h3 className="text-xl font-semibold text-gray-800 dark:text-white">Livraisons récentes</h3>
//               <span className="text-2xl">📦</span>
//             </div>
//             {livraisons.map((livraison) => (
//               <div 
//                 key={livraison.id} 
//                 className="mb-4 p-4 border border-gray-100 dark:border-gray-700 rounded-lg bg-gray-50 dark:bg-gray-900"
//               >
//                 <div className="flex justify-between">
//                   <p className="font-medium text-gray-800 dark:text-white">{livraison.client}</p>
//                   <span className={`text-sm px-2 py-1 rounded-full ${
//                     livraison.status === "En cours" 
//                       ? "bg-blue-100 text-blue-600 dark:bg-blue-900 dark:text-blue-300" 
//                       : "bg-green-100 text-green-600 dark:bg-green-900 dark:text-green-300"
//                   }`}>
//                     {livraison.status}
//                   </span>
//                 </div>
//                 <p className="text-sm text-gray-500 dark:text-gray-400 mt-1">{livraison.date}</p>
//               </div>
//             ))}
//           </div>

//           {/* Prestations actives */}
//           <div className="bg-white dark:bg-gray-800 rounded-xl shadow-md p-6">
//             <div className="flex items-center justify-between mb-4">
//               <h3 className="text-xl font-semibold text-gray-800 dark:text-white">Prestations à venir</h3>
//               <span className="text-2xl">🛠️</span>
//             </div>
//             {prestationsActives.map((prestation) => (
//               <div 
//                 key={prestation.id} 
//                 className="mb-4 p-4 border border-gray-100 dark:border-gray-700 rounded-lg bg-gray-50 dark:bg-gray-900"
//               >
//                 <div className="flex justify-between">
//                   <p className="font-medium text-gray-800 dark:text-white">{prestation.type}</p>
//                   <span className={`text-sm px-2 py-1 rounded-full ${
//                     prestation.status === "Confirmé" 
//                       ? "bg-green-100 text-green-600 dark:bg-green-900 dark:text-green-300" 
//                       : "bg-yellow-100 text-yellow-600 dark:bg-yellow-900 dark:text-yellow-300"
//                   }`}>
//                     {prestation.status}
//                   </span>
//                 </div>
//               </div>
//             ))}
//           </div>
//         </div>

//         {/* Boutons d'action rapide */}
//         <div className="flex flex-col md:flex-row gap-4 mb-12">
//           <button className="flex-1 bg-gradient-to-r from-blue-500 to-blue-600 text-white p-6 rounded-xl flex items-center justify-center gap-3 shadow-md hover:shadow-lg transition-shadow">
//             <span className="text-3xl">📦</span>
//             <span className="text-xl font-semibold">Nouvelle livraison</span>
//           </button>
//           <button className="flex-1 bg-gradient-to-r from-emerald-500 to-emerald-600 text-white p-6 rounded-xl flex items-center justify-center gap-3 shadow-md hover:shadow-lg transition-shadow">
//             <span className="text-3xl">🛠️</span>
//             <span className="text-xl font-semibold">Nouvelle prestation</span>
//           </button>
//         </div>
//       </div>

//       {/* Footer */}
//       <div className="bg-gray-800 text-white py-12">
//         <div className="container mx-auto px-4">
//           <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
//             <div>
//               <h4 className="text-xl font-bold mb-4">PrestServ</h4>
//               <p className="text-gray-400">Votre plateforme de services à la demande pour tous vos besoins quotidiens.</p>
//             </div>
//             <div>
//               <h4 className="text-lg font-semibold mb-4">Liens rapides</h4>
//               <ul className="space-y-2 text-gray-400">
//                 <li><a href="#" className="hover:text-white transition-colors">À propos</a></li>
//                 <li><a href="#" className="hover:text-white transition-colors">Services</a></li>
//                 <li><a href="#" className="hover:text-white transition-colors">Devenir prestataire</a></li>
//                 <li><a href="#" className="hover:text-white transition-colors">Contact</a></li>
//               </ul>
//             </div>
//             <div>
//               <h4 className="text-lg font-semibold mb-4">Contact</h4>
//               <p className="text-gray-400 mb-2">info@prestserv.com</p>
//               <p className="text-gray-400">+33 1 23 45 67 89</p>
//             </div>
//           </div>
//           <div className="border-t border-gray-700 mt-8 pt-8 text-center text-gray-500">
//             <p>© 2025 PrestServ. Tous droits réservés.</p>
//           </div>
//         </div>
//       </div>
//     </div>
//   );
// }