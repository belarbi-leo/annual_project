"use client";

import { useState } from "react";
import NewAdsDelivery from "@/components/new-ads-delivery";
import {
  ClockIcon,
  ArrowPathIcon,
  ExclamationCircleIcon,
  CheckCircleIcon,
  PencilIcon,
  TrashIcon,
  ChatBubbleBottomCenterTextIcon,
  EyeIcon,
  ExclamationTriangleIcon,
  StarIcon,
  DevicePhoneMobileIcon,
  XMarkIcon,
} from "@heroicons/react/24/outline";
  
type DeliveryStatus = "pending" | "in-progress" | "completed" | "expired";
interface Delivery {
  id: number;
  title: string;
  status: DeliveryStatus;
  description?: string;
  address?: string;
  date?: string;
  trackingNumber?: string;
  contactName?: string;
  contactPhone?: string;
}

// Données de livraisons enrichies
const deliveries: Delivery[] = [
  { 
    id: 1, 
    title: "La multiprise de Bryan", 
    status: "pending",
    description: "Multiprise 6 ports avec protection contre les surtensions",
    address: "12 rue des Fleurs, 75001 Paris",
    date: "15/04/2025",
    contactName: "Bryan Dupont",
    contactPhone: "06 12 34 56 78"
  },
  { 
    id: 2, 
    title: "Télévision", 
    status: "pending",
    description: "TV LED 55 pouces, marque Samsung",
    address: "25 avenue Victor Hugo, 75016 Paris",
    date: "18/04/2025",
    contactName: "Marie Lefort",
    contactPhone: "06 98 76 54 32"
  },
  { 
    id: 3, 
    title: "Canapé", 
    status: "in-progress",
    description: "Canapé 3 places en tissu gris",
    address: "5 boulevard Haussmann, 75009 Paris",
    date: "10/04/2025",
    trackingNumber: "FR7654321",
    contactName: "Lucas Martin",
    contactPhone: "07 45 67 89 01"
  },
  { 
    id: 4, 
    title: "Ordinateur", 
    status: "completed",
    description: "Ordinateur portable HP Pavilion",
    address: "18 rue de la Paix, 75002 Paris",
    date: "01/04/2025",
    trackingNumber: "FR1234567",
    contactName: "Sophie Dubois",
    contactPhone: "06 23 45 67 89"
  },
  { 
    id: 5, 
    title: "La gamelle d'Enzo", 
    status: "expired",
    description: "Gamelle pour chien en inox avec support surélevé",
    address: "7 rue des Lilas, 75020 Paris",
    date: "20/03/2025",
    contactName: "Enzo Moreau",
    contactPhone: "07 89 01 23 45"
  },
  { 
    id: 6, 
    title: "Lot de 5 sneakers", 
    status: "completed",
    description: "Lot de 5 paires de sneakers taille 42-43",
    address: "30 rue du Commerce, 75015 Paris",
    date: "25/03/2025",
    trackingNumber: "FR9876543",
    contactName: "Thomas Petit",
    contactPhone: "06 34 56 78 90"
  },
];

export default function Deliveries() {
  const [statusFilter, setStatusFilter] = useState("all");
  const [selectedDelivery, setSelectedDelivery] = useState<number | null>(null);
  const [viewMode, setViewMode] = useState<"view" | "edit" | null>(null);
  const [editedDelivery, setEditedDelivery] = useState<Delivery | null>(null);

  const handleStatusChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setStatusFilter(event.target.value);
  };

  const filters = [
    { value: "all", label: "Toutes", icon: null },
    { value: "pending", label: "En attente", icon: ClockIcon },
    { value: "in-progress", label: "En cours", icon: ArrowPathIcon },
    { value: "expired", label: "Expiré", icon: ExclamationCircleIcon },
    { value: "completed", label: "Terminé", icon: CheckCircleIcon },
  ];

  const getStatusIcon = (status: string) => {
    switch (status) {
      case "pending":
        return <ClockIcon className="w-6 h-6 text-yellow-500" />;
      case "in-progress":
        return <ArrowPathIcon className="w-6 h-6 text-blue-500" />;
      case "expired":
        return <ExclamationCircleIcon className="w-6 h-6 text-red-500" />;
      case "completed":
        return <CheckCircleIcon className="w-6 h-6 text-green-500" />;
      default:
        return null;
    }
  };

  const handleEdit = (id: number, event: React.MouseEvent) => {
    event.stopPropagation();
    const delivery = deliveries.find(d => d.id === id);
    if (delivery) {
      setSelectedDelivery(id);
      setEditedDelivery({...delivery});
      setViewMode("edit");
    }
  };
  
  const handleTrack = (id: number, event: React.MouseEvent) => {
    event.stopPropagation();
    const delivery = deliveries.find(d => d.id === id);
    if (delivery) {
      setSelectedDelivery(id);
      setViewMode("view");
    }
  };

  const handleDelete = (id: number, event: React.MouseEvent) => {
    event.stopPropagation();
    alert(`Supprimer l'annonce ${id}`);
  };

  const handleDispute = (id: number, event: React.MouseEvent) => {
    event.stopPropagation();
    alert(`Litige ouvert pour l'annonce ${id}`);
  };

  const handleReview = (id: number, event: React.MouseEvent) => {
    event.stopPropagation();
    alert(`Donner son avis sur le livreur pour l'annonce ${id}`);
  };

  const handleScanNFC = (event: React.MouseEvent) => {
    event.stopPropagation();
    alert("Scanner la carte NFC du livreur...");
  };

  const handleCloseDetail = () => {
    setSelectedDelivery(null);
    setViewMode(null);
    setEditedDelivery(null);
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    if (editedDelivery) {
      setEditedDelivery({
        ...editedDelivery,
        [e.target.name]: e.target.value
      });
    }
  };

  const handleSaveChanges = () => {
    alert(`Modifications enregistrées pour la livraison ${editedDelivery?.id}`);
    // Ici vous mettriez la logique pour sauvegarder les modifications dans votre backend
    handleCloseDetail();
  };

  // Obtenir les détails de la livraison sélectionnée
  const selectedDeliveryDetails = deliveries.find(d => d.id === selectedDelivery);

  return (
    <div className="space-y-8 p-6 bg-gray-50 dark:bg-gray-900 min-h-screen">
      <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4 sm:gap-0 mt-5">
        <h2 className="text-3xl font-bold text-gray-800 dark:text-white">Mes livraisons solidaires</h2>
        <NewAdsDelivery />
      </div>

      <div className="flex flex-wrap gap-4 justify-center mt-15">
        {filters.map((filter) => (
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
      
      <hr className="my-6 border-t border-gray-300 dark:border-gray-600" />

      <ul role="list" className="space-y-4">
        {deliveries
          .filter((delivery) => {
            if (statusFilter === "all") return true;
            return delivery.status === statusFilter;
          })
          .map((delivery) => (
          <div key={delivery.id} className="flex flex-col">
            <div
              className={`p-4 border rounded-lg bg-white dark:bg-gray-800 shadow-md flex items-center justify-between cursor-pointer border-gray-300 ${selectedDelivery === delivery.id ? 'border-lime-500 ring-2 ring-lime-500' : ''}`}
              onClick={() => {
                setSelectedDelivery(delivery.id === selectedDelivery ? null : delivery.id);
                setViewMode("view");
              }}
            >
              <div className="flex items-center space-x-4">
                {getStatusIcon(delivery.status)}
                <span className="text-lg font-medium text-gray-900 dark:text-white">{delivery.title}</span>
              </div>

              {/* Actions selon le statut */}
              <div className="flex space-x-2">
                {delivery.status === "pending" && (
                  <>
                    <button onClick={(e) => handleEdit(delivery.id, e)} className="p-2 bg-yellow-500 text-white rounded-full hover:bg-yellow-600">
                      <PencilIcon className="w-5 h-5" />
                    </button>
                    <button onClick={(e) => handleDelete(delivery.id, e)} className="p-2 bg-red-500 text-white rounded-full hover:bg-red-600">
                      <TrashIcon className="w-5 h-5" />
                    </button>
                  </>
                )}
                {delivery.status === "in-progress" && (
                  <>
                    <button onClick={(e) => handleDispute(delivery.id, e)} className="p-2 bg-red-500 text-white rounded-full hover:bg-red-600">
                      <ExclamationTriangleIcon className="w-5 h-5" />
                    </button>
                    <button onClick={(e) => handleScanNFC(e)} className="p-2 bg-purple-500 text-white rounded-full hover:bg-purple-600">
                        <DevicePhoneMobileIcon className="w-5 h-5" />
                    </button>
                    <button className="p-2 bg-green-500 text-white rounded-full hover:bg-green-600">
                      <ChatBubbleBottomCenterTextIcon className="w-5 h-5" />
                    </button>
                    <button onClick={(e) => handleTrack(delivery.id, e)} className="p-2 bg-blue-500 text-white rounded-full hover:bg-blue-600">
                      <EyeIcon className="w-5 h-5" />
                    </button>
                  </>
                )}
                {delivery.status === "completed" && (
                  <>
                    <button onClick={(e) => handleDispute(delivery.id, e)} className="p-2 bg-red-500 text-white rounded-full hover:bg-red-600">
                      <ExclamationTriangleIcon className="w-5 h-5" />
                    </button>
                    <button onClick={(e) => handleReview(delivery.id, e)} className="p-2 bg-yellow-500 text-white rounded-full hover:bg-yellow-600">
                      <StarIcon className="w-5 h-5" />
                    </button>
                    <button onClick={(e) => handleTrack(delivery.id, e)} className="p-2 bg-blue-500 text-white rounded-full hover:bg-blue-600">
                      <EyeIcon className="w-5 h-5" />
                    </button>
                  </>
                )}
                {delivery.status === "expired" && (
                  <>
                    <button onClick={(e) => handleTrack(delivery.id, e)} className="p-2 bg-blue-500 text-white rounded-full hover:bg-blue-600">
                      <EyeIcon className="w-5 h-5" />
                    </button>
                  </>
                )}
              </div>
            </div>

            {/* Section détaillée qui s'ouvre lorsqu'une livraison est sélectionnée */}
            {selectedDelivery === delivery.id && viewMode && selectedDeliveryDetails && (
              <div className="mt-2 p-4 border rounded-lg bg-white dark:bg-gray-800 shadow-md border-gray-300 animate-fadeIn">
                <div className="flex justify-between items-center mb-4">
                  <h3 className="text-xl font-semibold text-gray-900 dark:text-white">
                    {viewMode === "view" ? "Détails de la livraison" : "Modifier la livraison"}
                  </h3>
                  <button onClick={handleCloseDetail} className="p-1 rounded-full hover:bg-gray-200 dark:hover:bg-gray-700">
                    <XMarkIcon className="w-6 h-6 text-gray-500 dark:text-white" />
                  </button>
                </div>

                {viewMode === "view" ? (
                  // Mode vue
                  <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                    <div>
                      <p className="text-sm font-medium text-gray-500 dark:text-gray-400">Titre</p>
                      <p className="text-lg font-medium text-gray-900 dark:text-white">{selectedDeliveryDetails.title}</p>
                    </div>
                    
                    <div>
                      <p className="text-sm font-medium text-gray-500 dark:text-gray-400">Statut</p>
                      <div className="flex items-center">
                        {getStatusIcon(selectedDeliveryDetails.status)}
                        <span className="ml-2 text-lg font-medium text-gray-900 dark:text-white">
                          {filters.find(f => f.value === selectedDeliveryDetails.status)?.label}
                        </span>
                      </div>
                    </div>
                    
                    <div>
                      <p className="text-sm font-medium text-gray-500 dark:text-gray-400">Description</p>
                      <p className="text-lg font-medium text-gray-900 dark:text-white">{selectedDeliveryDetails.description || "Non spécifiée"}</p>
                    </div>
                    
                    <div>
                      <p className="text-sm font-medium text-gray-500 dark:text-gray-400">Adresse de livraison</p>
                      <p className="text-lg font-medium text-gray-900 dark:text-white">{selectedDeliveryDetails.address || "Non spécifiée"}</p>
                    </div>
                    
                    <div>
                      <p className="text-sm font-medium text-gray-500 dark:text-gray-400">Date prévue</p>
                      <p className="text-lg font-medium text-gray-900 dark:text-white">{selectedDeliveryDetails.date || "Non spécifiée"}</p>
                    </div>
                    
                    {selectedDeliveryDetails.trackingNumber && (
                      <div>
                        <p className="text-sm font-medium text-gray-500 dark:text-gray-400">Numéro de suivi</p>
                        <p className="text-lg font-medium text-gray-900 dark:text-white">{selectedDeliveryDetails.trackingNumber}</p>
                      </div>
                    )}
                    
                    <div>
                      <p className="text-sm font-medium text-gray-500 dark:text-gray-400">Contact</p>
                      <p className="text-lg font-medium text-gray-900 dark:text-white">
                        {selectedDeliveryDetails.contactName || "Non spécifié"} 
                        {selectedDeliveryDetails.contactPhone ? ` - ${selectedDeliveryDetails.contactPhone}` : ""}
                      </p>
                    </div>
                  </div>
                ) : (
                  // Mode édition
                  <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                    {editedDelivery && (
                      <>
                        <div>
                          <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">Titre</label>
                          <input
                            type="text"
                            name="title"
                            value={editedDelivery.title}
                            onChange={handleInputChange}
                            className="w-full p-2 border border-gray-300 rounded-md focus:ring-lime-500 focus:border-lime-500"
                          />
                        </div>
                        
                        <div>
                          <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">Description</label>
                          <input
                            type="text"
                            name="description"
                            value={editedDelivery.description || ""}
                            onChange={handleInputChange}
                            className="w-full p-2 border border-gray-300 rounded-md focus:ring-lime-500 focus:border-lime-500"
                          />
                        </div>
                        
                        <div>
                          <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">Adresse de livraison</label>
                          <input
                            type="text"
                            name="address"
                            value={editedDelivery.address || ""}
                            onChange={handleInputChange}
                            className="w-full p-2 border border-gray-300 rounded-md focus:ring-lime-500 focus:border-lime-500"
                          />
                        </div>
                        
                        <div>
                          <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">Date prévue</label>
                          <input
                            type="text"
                            name="date"
                            value={editedDelivery.date || ""}
                            onChange={handleInputChange}
                            className="w-full p-2 border border-gray-300 rounded-md focus:ring-lime-500 focus:border-lime-500"
                          />
                        </div>
                        
                        <div>
                          <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">Nom du contact</label>
                          <input
                            type="text"
                            name="contactName"
                            value={editedDelivery.contactName || ""}
                            onChange={handleInputChange}
                            className="w-full p-2 border border-gray-300 rounded-md focus:ring-lime-500 focus:border-lime-500"
                          />
                        </div>
                        
                        <div>
                          <label className="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-1">Téléphone du contact</label>
                          <input
                            type="text"
                            name="contactPhone"
                            value={editedDelivery.contactPhone || ""}
                            onChange={handleInputChange}
                            className="w-full p-2 border border-gray-300 rounded-md focus:ring-lime-500 focus:border-lime-500"
                          />
                        </div>
                        
                        <div className="md:col-span-2 mt-4 flex justify-end">
                          <button
                            onClick={handleCloseDetail}
                            className="mr-2 px-4 py-2 bg-gray-300 text-gray-700 rounded-md hover:bg-gray-400"
                          >
                            Annuler
                          </button>
                          <button
                            onClick={handleSaveChanges}
                            className="px-4 py-2 bg-lime-600 text-white rounded-md hover:bg-lime-700"
                          >
                            Enregistrer
                          </button>
                        </div>
                      </>
                    )}
                  </div>
                )}
              </div>
            )}
          </div>
        ))}
      </ul>
    </div>
  );
}