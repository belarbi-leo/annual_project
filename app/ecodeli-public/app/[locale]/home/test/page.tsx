"use client";

import { useState, useEffect } from "react";
import { ServiceCard, ServiceRequestModal, SERVICE_STATUS } from "@/components/cardAd";
import { FilmIcon, StarIcon } from "@heroicons/react/24/outline";

// Données simulées pour l'exemple
const sampleServices = [
  { 
    id: 1, 
    title: "Cours de guitare débutant", 
    status: SERVICE_STATUS.AVAILABLE,
    description: "Cours de guitare pour débutants - 1h par semaine. Apprentissage des bases, accords et premiers morceaux.",
    location: "Centre culturel, 75001 Paris",
    date: "Tous les mercredis à 18h",
    provider: "Lucas Martin",
    duration: "1h",
    price: "20€/h",
    category: "Musique",
    rating: 4.8,
    reviews: 37,
    image: "/api/placeholder/400/250"
  },
  { 
    id: 2, 
    title: "Aide aux devoirs niveau collège", 
    status: SERVICE_STATUS.AVAILABLE,
    description: "Soutien scolaire pour collégiens en mathématiques et français",
    location: "Bibliothèque municipale, 75016 Paris",
    date: "Samedi et mercredi après-midi",
    provider: "Marie Lefort",
    duration: "1h30",
    price: "15€/h",
    category: "Éducation",
    rating: 4.7,
    reviews: 22,
    image: "/api/placeholder/400/250"
  },
  { 
    id: 3, 
    title: "Réparation de petit électroménager", 
    status: SERVICE_STATUS.AVAILABLE,
    description: "Réparation de cafetières, mixeurs, grille-pains, etc.",
    location: "À domicile",
    date: "Sur rendez-vous",
    provider: "Thomas Dubois",
    duration: "Variable selon la réparation",
    price: "25€/h + pièces",
    category: "Bricolage",
    rating: 4.5,
    reviews: 45,
    image: "/api/placeholder/400/250"
  },
  { 
    id: 4, 
    title: "Cours de cuisine végétarienne", 
    status: SERVICE_STATUS.AVAILABLE,
    description: "Apprentissage de recettes végétariennes faciles et équilibrées pour toute la famille",
    location: "Maison des associations, 75020 Paris",
    date: "Un samedi sur deux, 10h-12h",
    provider: "Sophie Roux",
    duration: "2h",
    price: "30€ par session",
    category: "Cuisine",
    rating: 4.9,
    reviews: 56,
    image: "/api/placeholder/400/250"
  },
  { 
    id: 5, 
    title: "Jardinage et entretien de plantes", 
    status: SERVICE_STATUS.AVAILABLE,
    description: "Entretien de jardins, conseils pour plantes d'intérieur et aménagement d'espaces verts",
    location: "À domicile",
    date: "Flexible",
    provider: "Julien Mercier",
    duration: "2-3h",
    price: "22€/h",
    category: "Jardinage",
    rating: 4.6,
    reviews: 29,
    image: "/api/placeholder/400/250"
  },
  { 
    id: 6, 
    title: "Initiation à la photographie", 
    status: SERVICE_STATUS.AVAILABLE,
    description: "Cours d'initiation à la photographie numérique en extérieur",
    location: "Parc de la Villette, 75019 Paris",
    date: "Dimanche 10h-12h",
    provider: "Claire Petit",
    duration: "2h",
    price: "35€ par session",
    category: "Art",
    rating: 4.8,
    reviews: 38,
    image: "/api/placeholder/400/250"
  },
];

// Extraction des catégories uniques
const categories = ["Toutes les catégories", ...new Set(sampleServices.map(service => service.category))];

export default function ServicesPage() {
  const [services, setServices] = useState(sampleServices);
  const [filteredServices, setFilteredServices] = useState(sampleServices);
  
  const [selectedService, setSelectedService] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  
  const [categoryFilter, setCategoryFilter] = useState("Toutes les catégories");
  const [searchQuery, setSearchQuery] = useState("");
  const [showFilters, setShowFilters] = useState(false);
  
  // Filtre les services en fonction de la catégorie et de la recherche
  useEffect(() => {
    let results = services;
    
    // Filtre par catégorie
    if (categoryFilter !== "Toutes les catégories") {
      results = results.filter(service => service.category === categoryFilter);
    }
    
    // Filtre par recherche
    if (searchQuery.trim() !== "") {
      const query = searchQuery.toLowerCase();
      results = results.filter(service => 
        service.title.toLowerCase().includes(query) || 
        service.description.toLowerCase().includes(query) ||
        service.provider.toLowerCase().includes(query) ||
        service.category.toLowerCase().includes(query)
      );
    }
    
    setFilteredServices(results);
  }, [categoryFilter, searchQuery, services]);
  
  // Gestion des demandes de prestation
  const handleRequestService = (serviceId) => {
    const service = services.find(s => s.id === serviceId);
    setSelectedService(service);
    setIsModalOpen(true);
  };
  
  // Soumission du formulaire de demande
  const handleSubmitRequest = (data) => {
    // Ici vous implémenteriez la logique pour envoyer la demande à votre API
    console.log("Demande soumise:", data);
    
    // Simulation de mise à jour du statut
    const updatedServices = services.map(service => 
      service.id === data.serviceId 
        ? { ...service, status: SERVICE_STATUS.REQUESTED } 
        : service
    );
    
    setServices(updatedServices);
    alert(`Votre demande a été envoyée avec succès pour "${selectedService.title}"`);
  };
  
  return (
    <div className="container mx-auto px-4 py-8 bg-gray-50 dark:bg-gray-900 min-h-screen">
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-gray-900 dark:text-white mb-2">
          Prestations de services
        </h1>
        <p className="text-gray-600 dark:text-gray-300">
          Trouvez des experts qualifiés pour tous vos besoins
        </p>
      </div>
      
      {/* Barre de recherche et filtres */}
      <div className="bg-white dark:bg-gray-800 rounded-lg shadow-md p-4 mb-8">
        <div className="flex flex-col lg:flex-row gap-4">
          <div className="relative flex-grow">
            <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
              <StarIcon className="h-5 w-5 text-gray-400" />
            </div>
            <input
              type="text"
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
              className="pl-10 pr-4 py-2 w-full border border-gray-300 dark:border-gray-600 
                     rounded-md focus:ring-emerald-500 focus:border-emerald-500 
                     bg-white dark:bg-gray-800"
              placeholder="Rechercher une prestation..."
            />
          </div>
          
          <div className="lg:w-60">
            <select
              value={categoryFilter}
              onChange={(e) => setCategoryFilter(e.target.value)}
              className="w-full p-2 border border-gray-300 dark:border-gray-600 
                     rounded-md focus:ring-emerald-500 focus:border-emerald-500 
                     bg-white dark:bg-gray-800"
            >
              {categories.map((category) => (
                <option key={category} value={category}>
                  {category}
                </option>
              ))}
            </select>
          </div>
          
          <button
            onClick={() => setShowFilters(!showFilters)}
            className="flex items-center justify-center gap-2 px-4 py-2 bg-gray-200 
                     dark:bg-gray-700 text-gray-800 dark:text-white rounded-md 
                     hover:bg-gray-300 dark:hover:bg-gray-600 lg:w-auto"
          >
            <FilmIcon className="h-5 w-5" />
            <span>Filtres</span>
          </button>
        </div>
        
        {/* Filtres supplémentaires (affichés/masqués) */}
        {showFilters && (
          <div className="mt-4 pt-4 border-t border-gray-200 dark:border-gray-700 grid grid-cols-1 md:grid-cols-3 gap-4">
            <div>
              <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                Prix
              </label>
              <select 
                className="w-full p-2 border border-gray-300 dark:border-gray-600 
                         rounded-md focus:ring-emerald-500 focus:border-emerald-500 
                         bg-white dark:bg-gray-800"
              >
                <option value="">Tous les prix</option>
                <option value="1">Moins de 15€/h</option>
                <option value="2">15€/h - 25€/h</option>
                <option value="3">Plus de 25€/h</option>
              </select>
            </div>
            
            <div>
              <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                Note minimale
              </label>
              <select 
                className="w-full p-2 border border-gray-300 dark:border-gray-600 
                         rounded-md focus:ring-emerald-500 focus:border-emerald-500 
                         bg-white dark:bg-gray-800"
              >
                <option value="">Toutes les notes</option>
                <option value="3">3 étoiles et plus</option>
                <option value="4">4 étoiles et plus</option>
                <option value="4.5">4.5 étoiles et plus</option>
              </select>
            </div>
            
            <div>
              <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
                Lieu
              </label>
              <select 
                className="w-full p-2 border border-gray-300 dark:border-gray-600 
                         rounded-md focus:ring-emerald-500 focus:border-emerald-500 
                         bg-white dark:bg-gray-800"
              >
                <option value="">Tous les lieux</option>
                <option value="domicile">À domicile</option>
                <option value="exterieur">En extérieur</option>
                <option value="centre">En centre</option>
              </select>
            </div>
          </div>
        )}
      </div>
      
      {/* Résultats de recherche */}
      <div className="mb-6">
        <h2 className="text-xl font-semibold text-gray-800 dark:text-white mb-4">
          {filteredServices.length} prestation{filteredServices.length !== 1 ? 's' : ''} disponible{filteredServices.length !== 1 ? 's' : ''}
        </h2>
        
        {filteredServices.length === 0 ? (
          <div className="bg-white dark:bg-gray-800 rounded-lg shadow-md p-6 text-center">
            <p className="text-gray-600 dark:text-gray-300">
              Aucune prestation ne correspond à vos critères de recherche.
            </p>
          </div>
        ) : (
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
            {filteredServices.map((service) => (
              <ServiceCard
                key={service.id}
                service={service}
                onRequest={handleRequestService}
              />
            ))}
          </div>
        )}
      </div>
      
      {/* Modal de demande de prestation */}
      {selectedService && (
        <ServiceRequestModal
          isOpen={isModalOpen}
          service={selectedService}
          onClose={() => setIsModalOpen(false)}
          onSubmit={handleSubmitRequest}
        />
      )}
    </div>
  );
}