"use client";

import { useState } from "react";
import { CalendarIcon, ClockIcon, MapPinIcon, CurrencyEuroIcon, UserIcon, XMarkIcon, StarIcon, } from "@heroicons/react/24/outline";

// Types
export const SERVICE_STATUS = {
  AVAILABLE: "available",
  REQUESTED: "requested",
  ACCEPTED: "accepted",
  COMPLETED: "completed",
  CANCELED: "canceled",
};

export const SERVICE_STATUS_CONFIG = {
  [SERVICE_STATUS.AVAILABLE]: {
    color: "green",
    label: "Disponible",
  },
  [SERVICE_STATUS.REQUESTED]: {
    color: "yellow",
    label: "Demandée",
  },
  [SERVICE_STATUS.ACCEPTED]: {
    color: "blue",
    label: "Acceptée",
  },
  [SERVICE_STATUS.COMPLETED]: {
    color: "green",
    label: "Terminée",
  },
  [SERVICE_STATUS.CANCELED]: {
    color: "red",
    label: "Annulée",
  },
};

export const ServiceCard = ({ service, onRequest }) => {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [showDetails, setShowDetails] = useState(false);

  const status = SERVICE_STATUS_CONFIG[service.status];

  const handleRequest = (e) => {
    e.stopPropagation();
    onRequest(service.id);
  };

  const handleCardClick = () => {
    setShowDetails(!showDetails);
  };

  // Rendu des étoiles pour l'affichage de la note
  const renderRating = (rating) => {
    const stars = [];
    const fullStars = Math.floor(rating);
    const hasHalfStar = rating % 1 >= 0.5;

    for (let i = 1; i <= 5; i++) {
      if (i <= fullStars) {
        stars.push(<StarIcon key={i} className="w-4 h-4 text-amber-500" />);
      } else if (i === fullStars + 1 && hasHalfStar) {
        stars.push(<StarIcon key={i} className="w-4 h-4 text-amber-500 opacity-50" />);
      } else {
        stars.push(<StarIcon key={i} className="w-4 h-4 text-amber-500" />);
      }
    }

    return (
      <div className="flex items-center space-x-1">
        {stars}
        <span className="ml-1 text-sm font-medium text-gray-700">{rating.toFixed(1)}</span>
      </div>
    );
  };

  return (
    <>
      <div 
        className={`bg-white dark:bg-gray-800 rounded-lg shadow-md overflow-hidden border 
                   transition-all duration-300 cursor-pointer hover:shadow-lg
                   ${showDetails ? 'border-emerald-500 ring-1 ring-emerald-500' : 'border-gray-200 hover:border-emerald-500'}`}
        onClick={handleCardClick}
      >
        {/* Image du service (si disponible) */}
        {service.image && (
          <div className="h-40 bg-gray-200 dark:bg-gray-700 overflow-hidden">
            <img 
              src={service.image} 
              alt={service.title} 
              className="w-full h-full object-cover" 
            />
          </div>
        )}

        <div className="p-4">
          {/* En-tête avec catégorie et statut */}
          <div className="flex items-center justify-between mb-2">
            <span className={`bg-${status.color}-100 text-${status.color}-800 text-xs font-medium px-2 py-1 rounded-full`}>
              {service.category}
            </span>
            <span className={`text-${status.color}-500 text-sm font-medium`}>
              {status.label}
            </span>
          </div>

          {/* Titre et description */}
          <h4 className="text-lg font-semibold text-gray-900 dark:text-white mb-2">{service.title}</h4>
          <p className="text-sm text-gray-600 dark:text-gray-300 mb-3 line-clamp-2">{service.description}</p>

          {/* Informations basiques */}
          <div className="space-y-1 mb-3">
            {service.provider && (
              <div className="flex items-center text-sm text-gray-500 dark:text-gray-400">
                <UserIcon className="w-4 h-4 mr-2" />
                <span>{service.provider}</span>
              </div>
            )}
            {service.location && (
              <div className="flex items-center text-sm text-gray-500 dark:text-gray-400">
                <MapPinIcon className="w-4 h-4 mr-2" />
                <span>{service.location}</span>
              </div>
            )}
            {service.price && (
              <div className="flex items-center text-sm font-medium text-emerald-600 dark:text-emerald-500">
                <CurrencyEuroIcon className="w-4 h-4 mr-2" />
                <span>{service.price}</span>
              </div>
            )}
          </div>

          {/* Note (si disponible) */}
          {service.rating && (
            <div className="mb-3">
              {renderRating(service.rating)}
            </div>
          )}

          {/* Action principale si le service est disponible */}
          {service.status === SERVICE_STATUS.AVAILABLE && (
            <div className="mt-4">
              <button
                onClick={handleRequest}
                className="w-full py-2 bg-emerald-500 text-white rounded-md hover:bg-emerald-600 transition-colors"
              >
                Demander cette prestation
              </button>
            </div>
          )}
        </div>

        {/* Détails étendus (apparaissent lorsque la carte est cliquée) */}
        {showDetails && (
          <div className="px-4 pb-4 border-t border-gray-200 dark:border-gray-700 mt-2 pt-3">
            <h5 className="font-medium text-gray-900 dark:text-white mb-2">Détails supplémentaires</h5>
            
            <div className="space-y-2">
              {service.date && (
                <div className="flex items-center text-sm text-gray-600 dark:text-gray-300">
                  <CalendarIcon className="w-4 h-4 mr-2" />
                  <span>Date: {service.date}</span>
                </div>
              )}
              {service.duration && (
                <div className="flex items-center text-sm text-gray-600 dark:text-gray-300">
                  <ClockIcon className="w-4 h-4 mr-2" />
                  <span>Durée: {service.duration}</span>
                </div>
              )}
              {service.reviews && (
                <div className="text-sm text-gray-600 dark:text-gray-300">
                  <span>{service.reviews} avis</span>
                </div>
              )}
            </div>
          </div>
        )}
      </div>
    </>
  );
};

export const ServiceRequestModal = ({ service, isOpen, onClose, onSubmit }) => {
  const [message, setMessage] = useState("");
  const [date, setDate] = useState("");
  const [time, setTime] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit({ 
      serviceId: service.id, 
      message, 
      date, 
      time,
    });
    onClose();
  };

  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50 p-4">
      <div className="bg-white dark:bg-gray-800 rounded-lg shadow-xl p-6 w-full max-w-md">
        <div className="flex justify-between items-center mb-4">
          <h3 className="text-xl font-semibold text-gray-900 dark:text-white">Demander cette prestation</h3>
          <button 
            onClick={onClose} 
            className="p-1 rounded-full hover:bg-gray-200 dark:hover:bg-gray-700"
          >
            <XMarkIcon className="w-6 h-6 text-gray-500 dark:text-gray-400" />
          </button>
        </div>
        
        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">Prestation</label>
            <div className="p-3 bg-gray-100 dark:bg-gray-700 rounded-md">
              <p className="font-medium text-gray-900 dark:text-white">{service.title}</p>
              <p className="text-sm text-gray-600 dark:text-gray-400 mt-1">{service.description}</p>
            </div>
          </div>
          
          <div className="mb-4">
            <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
              Date souhaitée *
            </label>
            <input
              type="date"
              value={date}
              onChange={(e) => setDate(e.target.value)}
              className="w-full p-2 border border-gray-300 dark:border-gray-600 rounded-md 
                       focus:ring-emerald-500 focus:border-emerald-500 bg-white dark:bg-gray-800"
              required
            />
          </div>
          
          <div className="mb-4">
            <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
              Heure souhaitée *
            </label>
            <input
              type="time"
              value={time}
              onChange={(e) => setTime(e.target.value)}
              className="w-full p-2 border border-gray-300 dark:border-gray-600 rounded-md 
                       focus:ring-emerald-500 focus:border-emerald-500 bg-white dark:bg-gray-800"
              required
            />
          </div>
          
          <div className="mb-6">
            <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">
              Message (facultatif)
            </label>
            <textarea
              value={message}
              onChange={(e) => setMessage(e.target.value)}
              rows={3}
              className="w-full p-2 border border-gray-300 dark:border-gray-600 rounded-md 
                       focus:ring-emerald-500 focus:border-emerald-500 bg-white dark:bg-gray-800"
              placeholder="Précisez vos besoins ou posez des questions..."
            />
          </div>
          
          <div className="flex justify-end space-x-3">
            <button
              type="button"
              onClick={onClose}
              className="px-4 py-2 bg-gray-200 dark:bg-gray-700 text-gray-800 dark:text-white 
                       rounded-md hover:bg-gray-300 dark:hover:bg-gray-600 transition-colors"
            >
              Annuler
            </button>
            <button
              type="submit"
              className="px-4 py-2 bg-emerald-500 text-white rounded-md 
                       hover:bg-emerald-600 transition-colors"
            >
              Envoyer la demande
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};