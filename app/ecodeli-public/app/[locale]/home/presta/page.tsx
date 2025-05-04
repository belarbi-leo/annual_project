"use client";
import { useState } from "react";
import SearchService from "@/components/searchService";
import {
  CalendarIcon,
  CheckCircleIcon,
  ClockIcon,
  XCircleIcon,
  PencilIcon,
  TrashIcon,
  ChatBubbleBottomCenterTextIcon,
  EyeIcon,
  StarIcon,
  XMarkIcon,
} from "@heroicons/react/24/outline";

type ServiceStatus = "available" | "requested" | "accepted" | "completed" | "canceled";
interface Service {
  id: number;
  title: string;
  status: ServiceStatus;
  description?: string;
  location?: string;
  date?: string;
  provider?: string;
  duration?: string;
  price?: string;
  category?: string;
}

// Données des prestations
const services: Service[] = [
  { 
    id: 1, 
    title: "Cours de guitare débutant", 
    status: "available",
    description: "Cours de guitare pour débutants - 1h par semaine",
    location: "Centre culturel, 75001 Paris",
    date: "Tous les mercredis à 18h",
    provider: "Lucas Martin",
    duration: "1h",
    price: "20€/h",
    category: "Musique"
  },
  { 
    id: 2, 
    title: "Aide aux devoirs niveau collège", 
    status: "available",
    description: "Soutien scolaire pour collégiens en mathématiques et français",
    location: "Bibliothèque municipale, 75016 Paris",
    date: "Samedi et mercredi après-midi",
    provider: "Marie Lefort",
    duration: "1h30",
    price: "15€/h",
    category: "Éducation"
  },
  { 
    id: 3, 
    title: "Réparation de petit électroménager", 
    status: "requested",
    description: "Réparation de cafetières, mixeurs, grille-pains, etc.",
    location: "À domicile",
    date: "Sur rendez-vous",
    provider: "Thomas Dubois",
    duration: "Variable selon la réparation",
    price: "25€/h + pièces",
    category: "Bricolage"
  },
  { 
    id: 4, 
    title: "Cours de cuisine végétarienne", 
    status: "accepted",
    description: "Apprentissage de recettes végétariennes faciles et équilibrées",
    location: "Maison des associations, 75020 Paris",
    date: "Un samedi sur deux, 10h-12h",
    provider: "Sophie Roux",
    duration: "2h",
    price: "30€ par session",
    category: "Cuisine"
  },
  { 
    id: 5, 
    title: "Jardinage et entretien de plantes", 
    status: "completed",
    description: "Entretien de jardins, conseils pour plantes d'intérieur",
    location: "À domicile",
    date: "Flexible",
    provider: "Julien Mercier",
    duration: "2-3h",
    price: "22€/h",
    category: "Jardinage"
  },
  { 
    id: 6, 
    title: "Initiation à la photographie", 
    status: "canceled",
    description: "Cours d'initiation à la photographie numérique",
    location: "Parc de la Villette, 75019 Paris",
    date: "Dimanche 10h-12h",
    provider: "Claire Petit",
    duration: "2h",
    price: "35€ par session",
    category: "Art"
  },
];

// Composant pour demander une prestation
function RequestServiceModal({ service, onClose, onSubmit }) {
  const [message, setMessage] = useState("");
  const [date, setDate] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit({ serviceId: service.id, message, date });
    onClose();
  };

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white dark:bg-gray-800 rounded-lg p-6 w-full max-w-md">
        <div className="flex justify-between items-center mb-4">
          <h3 className="text-xl font-semibold">Demander cette prestation</h3>
          <button onClick={onClose} className="p-1 rounded-full hover:bg-gray-200 dark:hover:bg-gray-700">
            <XMarkIcon className="w-6 h-6 text-gray-500 dark:text-white" />
          </button>
        </div>
        
        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">Prestation</label>
            <p className="text-lg font-medium text-gray-900 dark:text-white">{service.title}</p>
          </div>
          
          <div className="mb-4">
            <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">Date souhaitée</label>
            <input
              type="date"
              value={date}
              onChange={(e) => setDate(e.target.value)}
              className="w-full p-2 border border-gray-300 rounded-md focus:ring-lime-500 focus:border-lime-500"
              required
            />
          </div>
          
          <div className="mb-4">
            <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">Message (facultatif)</label>
            <textarea
              value={message}
              onChange={(e) => setMessage(e.target.value)}
              rows={3}
              className="w-full p-2 border border-gray-300 rounded-md focus:ring-lime-500 focus:border-lime-500"
              placeholder="Précisez vos besoins ou posez des questions..."
            />
          </div>
          
          <div className="flex justify-end space-x-2">
            <button
              type="button"
              onClick={onClose}
              className="px-4 py-2 bg-gray-300 text-gray-700 rounded-md hover:bg-gray-400"
            >
              Annuler
            </button>
            <button
              type="submit"
              className="px-4 py-2 bg-lime-600 text-white rounded-md hover:bg-lime-700"
            >
              Envoyer la demande
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default function Services() {
  const [statusFilter, setStatusFilter] = useState("all");
  const [categoryFilter, setCategoryFilter] = useState("all");
  const [selectedService, setSelectedService] = useState<number | null>(null);
  const [viewMode, setViewMode] = useState<"view" | "edit" | null>(null);
  const [showRequestModal, setShowRequestModal] = useState(false);
  const [searchResults, setSearchResults] = useState<Service[]>(services);

  const handleStatusChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setStatusFilter(event.target.value);
    filterServices(event.target.value, categoryFilter);
  };

  const handleCategoryChange = (category: string) => {
    setCategoryFilter(category);
    filterServices(statusFilter, category);
  };

  const filterServices = (status: string, category: string) => {
    let filtered = services;
    
    if (status !== "all") {
      filtered = filtered.filter(service => service.status === status);
    }
    
    if (category !== "all") {
      filtered = filtered.filter(service => service.category === category);
    }
    
    setSearchResults(filtered);
  };

  const handleSearch = (results) => {
    setSearchResults(results || services);
  };

  const statusFilters = [
    { value: "all", label: "Toutes", icon: null },
    { value: "available", label: "Disponibles", icon: CheckCircleIcon },
    { value: "requested", label: "Demandées", icon: ClockIcon },
    { value: "accepted", label: "Acceptées", icon: CalendarIcon },
    { value: "completed", label: "Terminées", icon: CheckCircleIcon },
    { value: "canceled", label: "Annulées", icon: XCircleIcon },
  ];

  // Catégories uniques
  const categories = ["all", ...new Set(services.map(service => service.category))];

  const getStatusIcon = (status: string) => {
    switch (status) {
      case "available":
        return <CheckCircleIcon className="w-6 h-6 text-green-500" />;
      case "requested":
        return <ClockIcon className="w-6 h-6 text-yellow-500" />;
      case "accepted":
        return <CalendarIcon className="w-6 h-6 text-blue-500" />;
      case "completed":
        return <CheckCircleIcon className="w-6 h-6 text-green-500" />;
      case "canceled":
        return <XCircleIcon className="w-6 h-6 text-red-500" />;
      default:
        return null;
    }
  };

  const handleView = (id: number) => {
    setSelectedService(id === selectedService ? null : id);
    setViewMode("view");
  };

  const handleRequest = (id: number) => {
    setSelectedService(id);
    setShowRequestModal(true);
  };

  const handleSubmitRequest = (data) => {
    // Ici vous implémenteriez la logique pour envoyer la demande
    alert(`Demande envoyée pour le service ${data.serviceId}\nDate: ${data.date}\nMessage: ${data.message}`);
  };

  const handleCancel = (id: number, event: React.MouseEvent) => {
    event.stopPropagation();
    alert(`Annuler la demande ${id}`);
  };

  const handleReview = (id: number, event: React.MouseEvent) => {
    event.stopPropagation();
    alert(`Donner un avis pour la prestation ${id}`);
  };

  const handleContact = (id: number, event: React.MouseEvent) => {
    event.stopPropagation();
    alert(`Contacter le prestataire pour le service ${id}`);
  };

  const handleCloseDetail = () => {
    setSelectedService(null);
    setViewMode(null);
  };

  // Obtenir les détails du service sélectionné
  const selectedServiceDetails = services.find(s => s.id === selectedService);

  return (
    <div className="space-y-8 p-6 bg-gray-50 dark:bg-gray-900 min-h-screen">
      <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4 sm:gap-0 mt-5">
        <h2 className="text-3xl font-bold text-gray-800 dark:text-white">Prestations de services</h2>
      </div>

      <div className="bg-white dark:bg-gray-800 rounded-lg shadow p-6">
        <h3 className="text-xl font-semibold text-gray-800 dark:text-white mb-4">Rechercher une prestation</h3>
        <SearchService onSearch={handleSearch} />
        
        <div className="mt-6">
          <h4 className="text-lg font-medium text-gray-700 dark:text-gray-300 mb-2">Filtrer par statut</h4>
          <div className="flex flex-wrap gap-4 mb-4">
            {statusFilters.map((filter) => (
              <label key={filter.value} className="cursor-pointer flex items-center gap-2">
                <input
                  type="radio"
                  name="status"
                  value={filter.value}
                  checked={statusFilter === filter.value}
                  onChange={handleStatusChange}
                  className="hidden"
                />
                {filter.icon && (
                  <filter.icon
                    className={`w-5 h-5 ${
                      statusFilter === filter.value
                        ? "text-lime-600"
                        : "text-gray-400"
                    }`}
                  />
                )}
                <span
                  className={`text-gray-700 text-sm font-medium transition-all duration-300
                  ${
                    statusFilter === filter.value
                      ? "text-lime-600 font-semibold border-b-2 border-lime-600"
                      : "hover:text-lime-500 hover:border-b-2 hover:border-lime-500"
                  }`}
                >
                  {filter.label}
                </span>
              </label>
            ))}
          </div>
          
          <h4 className="text-lg font-medium text-gray-700 dark:text-gray-300 mb-2">Filtrer par catégorie</h4>
          <div className="flex flex-wrap gap-2 mb-4">
            {categories.map((category) => (
              <button
                key={category}
                onClick={() => handleCategoryChange(category)}
                className={`px-3 py-1 rounded-full text-sm ${
                  categoryFilter === category
                    ? "bg-lime-600 text-white"
                    : "bg-gray-200 text-gray-700 hover:bg-gray-300"
                }`}
              >
                {category === "all" ? "Toutes les catégories" : category}
              </button>
            ))}
          </div>
        </div>
      </div>
      
      <div className="mt-8">
        <h3 className="text-2xl font-semibold text-gray-800 dark:text-white mb-4">Prestations disponibles</h3>
        
        {searchResults.length === 0 ? (
          <div className="bg-white dark:bg-gray-800 rounded-lg shadow p-6 text-center">
            <p className="text-gray-600 dark:text-gray-300">Aucune prestation ne correspond à vos critères.</p>
          </div>
        ) : (
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
            {searchResults
              .filter(service => service.status === "available")
              .map((service) => (
                <div
                  key={service.id}
                  className="bg-white dark:bg-gray-800 rounded-lg shadow-md overflow-hidden border border-gray-200 hover:border-lime-500 transition-all duration-300 cursor-pointer"
                  onClick={() => handleView(service.id)}
                >
                  <div className="p-4">
                    <div className="flex items-center justify-between mb-2">
                      <span className="bg-green-100 text-green-800 text-xs font-medium px-2 py-1 rounded-full">
                        {service.category}
                      </span>
                      {getStatusIcon(service.status)}
                    </div>
                    <h4 className="text-lg font-semibold text-gray-900 dark:text-white mb-2">{service.title}</h4>
                    <p className="text-sm text-gray-600 dark:text-gray-300 mb-2">{service.description}</p>
                    <div className="text-sm text-gray-500 dark:text-gray-400 mb-1">
                      <span className="font-medium">Lieu:</span> {service.location}
                    </div>
                    <div className="text-sm text-gray-500 dark:text-gray-400 mb-1">
                      <span className="font-medium">Prestataire:</span> {service.provider}
                    </div>
                    <div className="text-sm text-gray-500 dark:text-gray-400 mb-1">
                      <span className="font-medium">Prix:</span> {service.price}
                    </div>
                  </div>
                  <div className="bg-gray-50 dark:bg-gray-700 p-3 border-t border-gray-200 dark:border-gray-600">
                    <button
                      onClick={(e) => {
                        e.stopPropagation();
                        handleRequest(service.id);
                      }}
                      className="w-full py-2 bg-lime-600 text-white rounded-md hover:bg-lime-700 transition-colors"
                    >
                      Demander cette prestation
                    </button>
                  </div>
                </div>
              ))}
          </div>
        )}
      </div>
      
      <div className="mt-10">
        <h3 className="text-2xl font-semibold text-gray-800 dark:text-white mb-4">Suivi de mes demandes</h3>
        
        <div className="bg-white dark:bg-gray-800 rounded-lg shadow p-6">
          <ul role="list" className="space-y-4">
            {services
              .filter(service => ["requested", "accepted", "completed", "canceled"].includes(service.status))
              .map((service) => (
                <div key={service.id} className="flex flex-col">
                  <div
                    className={`p-4 border rounded-lg bg-white dark:bg-gray-800 shadow-md flex items-center justify-between cursor-pointer border-gray-300 ${selectedService === service.id ? 'border-lime-500 ring-2 ring-lime-500' : ''}`}
                    onClick={() => handleView(service.id)}
                  >
                    <div className="flex items-center space-x-4">
                      {getStatusIcon(service.status)}
                      <span className="text-lg font-medium text-gray-900 dark:text-white">{service.title}</span>
                    </div>

                    {/* Actions selon le statut */}
                    <div className="flex space-x-2">
                      {service.status === "requested" && (
                        <>
                          <button onClick={(e) => handleCancel(service.id, e)} className="p-2 bg-red-500 text-white rounded-full hover:bg-red-600">
                            <XCircleIcon className="w-5 h-5" />
                          </button>
                          <button onClick={(e) => handleContact(service.id, e)} className="p-2 bg-blue-500 text-white rounded-full hover:bg-blue-600">
                            <ChatBubbleBottomCenterTextIcon className="w-5 h-5" />
                          </button>
                        </>
                      )}
                      {service.status === "accepted" && (
                        <>
                          <button onClick={(e) => handleCancel(service.id, e)} className="p-2 bg-red-500 text-white rounded-full hover:bg-red-600">
                            <XCircleIcon className="w-5 h-5" />
                          </button>
                          <button onClick={(e) => handleContact(service.id, e)} className="p-2 bg-blue-500 text-white rounded-full hover:bg-blue-600">
                            <ChatBubbleBottomCenterTextIcon className="w-5 h-5" />
                          </button>
                        </>
                      )}
                      {service.status === "completed" && (
                        <>
                          <button onClick={(e) => handleReview(service.id, e)} className="p-2 bg-yellow-500 text-white rounded-full hover:bg-yellow-600">
                            <StarIcon className="w-5 h-5" />
                          </button>
                        </>
                      )}
                    </div>
                  </div>

                  {/* Section détaillée qui s'ouvre lorsqu'un service est sélectionné */}
                  {selectedService === service.id && viewMode === "view" && selectedServiceDetails && (
                    <div className="mt-2 p-4 border rounded-lg bg-white dark:bg-gray-800 shadow-md border-gray-300 animate-fadeIn">
                      <div className="flex justify-between items-center mb-4">
                        <h3 className="text-xl font-semibold text-gray-900 dark:text-white">
                          Détails de la prestation
                        </h3>
                        <button onClick={handleCloseDetail} className="p-1 rounded-full hover:bg-gray-200 dark:hover:bg-gray-700">
                          <XMarkIcon className="w-6 h-6 text-gray-500 dark:text-white" />
                        </button>
                      </div>

                      <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                        <div>
                          <p className="text-sm font-medium text-gray-500 dark:text-gray-400">Titre</p>
                          <p className="text-lg font-medium text-gray-900 dark:text-white">{selectedServiceDetails.title}</p>
                        </div>
                        
                        <div>
                          <p className="text-sm font-medium text-gray-500 dark:text-gray-400">Statut</p>
                          <div className="flex items-center">
                            {getStatusIcon(selectedServiceDetails.status)}
                            <span className="ml-2 text-lg font-medium text-gray-900 dark:text-white">
                              {statusFilters.find(f => f.value === selectedServiceDetails.status)?.label}
                            </span>
                          </div>
                        </div>
                        
                        <div>
                          <p className="text-sm font-medium text-gray-500 dark:text-gray-400">Description</p>
                          <p className="text-lg font-medium text-gray-900 dark:text-white">{selectedServiceDetails.description || "Non spécifiée"}</p>
                        </div>
                        
                        <div>
                          <p className="text-sm font-medium text-gray-500 dark:text-gray-400">Lieu</p>
                          <p className="text-lg font-medium text-gray-900 dark:text-white">{selectedServiceDetails.location || "Non spécifié"}</p>
                        </div>
                        
                        <div>
                          <p className="text-sm font-medium text-gray-500 dark:text-gray-400">Date</p>
                          <p className="text-lg font-medium text-gray-900 dark:text-white">{selectedServiceDetails.date || "Non spécifiée"}</p>
                        </div>
                        
                        <div>
                          <p className="text-sm font-medium text-gray-500 dark:text-gray-400">Durée</p>
                          <p className="text-lg font-medium text-gray-900 dark:text-white">{selectedServiceDetails.duration || "Non spécifiée"}</p>
                        </div>
                        
                        <div>
                          <p className="text-sm font-medium text-gray-500 dark:text-gray-400">Prix</p>
                          <p className="text-lg font-medium text-gray-900 dark:text-white">{selectedServiceDetails.price || "Non spécifié"}</p>
                        </div>
                        
                        <div>
                          <p className="text-sm font-medium text-gray-500 dark:text-gray-400">Prestataire</p>
                          <p className="text-lg font-medium text-gray-900 dark:text-white">{selectedServiceDetails.provider || "Non spécifié"}</p>
                        </div>
                      </div>
                    </div>
                  )}
                </div>
              ))}
          </ul>
        </div>
      </div>
      
      {/* Modal pour demander une prestation */}
      {showRequestModal && selectedService && (
        <RequestServiceModal
          service={services.find(s => s.id === selectedService)}
          onClose={() => setShowRequestModal(false)}
          onSubmit={handleSubmitRequest}
        />
      )}
    </div>
  );
}