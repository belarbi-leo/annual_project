"use client";

import { useState, useEffect, useRef } from "react";
import { XMarkIcon, TrashIcon, CameraIcon } from "@heroicons/react/24/outline";

export default function NewDeliveryRequest() {
  const [isOpen, setIsOpen] = useState(false);
  const [currentStep, setCurrentStep] = useState(1);
  const [isSubmitting, setIsSubmitting] = useState(false);
  const [showSuccessMessage, setShowSuccessMessage] = useState(false);
  const [hasUnsavedChanges, setHasUnsavedChanges] = useState(false);
  const [showExitConfirmation, setShowExitConfirmation] = useState(false);
  const [errors, setErrors] = useState<Record<string, string>>({});
  
  const [items, setItems] = useState([
    {
      id: 1,
      name: "",
      quantity: "",
      format: "",
      weight: "",
      knowsExactDimensions: false,
      dimensions: { longueur: "", largeur: "", hauteur: "", poids: "" },
      photo: null as string | null,
    },
  ]);
  
  const [shipmentDetails, setShipmentDetails] = useState({
    senderAddress: "",
    recipientAddress: "",
    price: "",
    description: "",
  });

  const modalRef = useRef<HTMLDivElement>(null);
  
  // Marquer les changements non sauvegardés
  useEffect(() => {
    if (isOpen) {
      setHasUnsavedChanges(true);
    }
  }, [items, shipmentDetails]);

  // Désactiver le scroll du body quand la modal est ouverte
  useEffect(() => {
    document.body.style.overflow = isOpen ? "hidden" : "auto";
    return () => {
      document.body.style.overflow = "auto";
    };
  }, [isOpen]);

  // Fermer la modal en cliquant en dehors
  const handleOutsideClick = (e: MouseEvent) => {
    if (modalRef.current && !modalRef.current.contains(e.target as Node)) {
      if (hasUnsavedChanges) {
        setShowExitConfirmation(true);
      } else {
        setIsOpen(false);
      }
    }
  };

  useEffect(() => {
    if (isOpen) {
      document.addEventListener("mousedown", handleOutsideClick);
    } else {
      document.removeEventListener("mousedown", handleOutsideClick);
    }
    return () => {
      document.removeEventListener("mousedown", handleOutsideClick);
    };
  }, [isOpen, hasUnsavedChanges]);

  // Gérer le changement des champs
  const handleInputChange = (id: number, field: string, value: string) => {
    setItems((prevItems) =>
      prevItems.map((item) =>
        item.id === id ? { ...item, [field]: value } : item
      )
    );
    setErrors({...errors, [`item_${id}_${field}`]: ""});
  };

  // Gérer les changements pour les dimensions
  const handleDimensionChange = (
    id: number,
    dimensionField: string,
    value: string
  ) => {
    setItems((prevItems) =>
      prevItems.map((item) =>
        item.id === id
          ? {
              ...item,
              dimensions: { ...item.dimensions, [dimensionField]: value },
            }
          : item
      )
    );
    setErrors({...errors, [`item_${id}_dimension_${dimensionField}`]: ""});
  };
  
  // Gérer le toggle knowsExactDimensions par objet
  const toggleDimensions = (id: number) => {
    setItems((prevItems) =>
      prevItems.map((item) =>
        item.id === id
          ? { 
              ...item, 
              knowsExactDimensions: !item.knowsExactDimensions,
              // Réinitialiser les valeurs appropriées selon le mode
              format: !item.knowsExactDimensions ? "" : item.format,
              weight: !item.knowsExactDimensions ? "" : item.weight,
              dimensions: !item.knowsExactDimensions 
                ? { longueur: "", largeur: "", hauteur: "", poids: "" } 
                : item.dimensions
            }
          : item
      )
    );
  };
  
  const handleShipmentChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setShipmentDetails((prev) => ({ ...prev, [name]: value }));
    setErrors({...errors, [name]: ""});
  };

  // Ajouter un nouvel objet (max 5)
  const addItem = () => {
    if (items.length < 5) {
      setItems([
        ...items,
        {
          id: Date.now(),
          name: "",
          quantity: "",
          format: "",
          weight: "",
          knowsExactDimensions: false,
          dimensions: { longueur: "", largeur: "", hauteur: "", poids: "" },
          photo: null,
        },
      ]);
    }
  };

  // Supprimer un objet (interdit pour le premier objet)
  const removeItem = (id: number) => {
    if (items.length > 1) {
      setItems(items.filter((item) => item.id !== id));
    }
  };

  // Gérer l'upload de photo
  const handlePhotoUpload = (id: number, file: File | null) => {
    if (!file) return;
    
    // Vérification du type de fichier
    const acceptedTypes = ['image/jpeg', 'image/png', 'image/gif', 'image/webp'];
    if (!acceptedTypes.includes(file.type)) {
      setErrors({...errors, [`item_${id}_photo`]: "Format d'image non pris en charge. Utilisez JPG, PNG, GIF ou WebP."});
      return;
    }
    
    // Vérification de la taille (max 5MB)
    if (file.size > 5 * 1024 * 1024) {
      setErrors({...errors, [`item_${id}_photo`]: "L'image est trop volumineuse (max 5MB)."});
      return;
    }
    
    const reader = new FileReader();
    reader.onload = (event) => {
      const imageUrl = event.target?.result as string;
      setItems((prevItems) =>
        prevItems.map((item) =>
          item.id === id ? { ...item, photo: imageUrl } : item
        )
      );
      setErrors({...errors, [`item_${id}_photo`]: ""});
    };
    
    reader.readAsDataURL(file);
  };

  const nextStep = () => {
    if (validateCurrentStep()) {
      setCurrentStep((prev) => prev + 1);
    }
  };
  
  const prevStep = () => setCurrentStep((prev) => prev - 1);

  // Validation des données
  const validateCurrentStep = () => {
    const newErrors: Record<string, string> = {};
    
    if (currentStep === 1) {
      items.forEach((item) => {
        // Validation de la quantité (nombre positif)
        if (!item.quantity) {
          newErrors[`item_${item.id}_quantity`] = "La quantité est requise";
        } else if (!/^\d+$/.test(item.quantity) || parseInt(item.quantity) <= 0) {
          newErrors[`item_${item.id}_quantity`] = "Doit être un nombre entier positif";
        }
        
        // Validation du nom
        if (!item.name.trim()) {
          newErrors[`item_${item.id}_name`] = "Le nom de l'objet est requis";
        }
        
        // Validation des dimensions ou format selon le mode
        if (item.knowsExactDimensions) {
          for (const dim of ['longueur', 'largeur', 'hauteur', 'poids']) {
            const value = item.dimensions[dim as keyof typeof item.dimensions];
            if (!value) {
              newErrors[`item_${item.id}_dimension_${dim}`] = `La ${dim} est requise`;
            } else if (!/^\d+(\.\d+)?$/.test(value) || parseFloat(value) <= 0) {
              newErrors[`item_${item.id}_dimension_${dim}`] = "Doit être un nombre positif";
            }
          }
        } else {
          if (!item.format) {
            newErrors[`item_${item.id}_format`] = "Veuillez sélectionner une taille";
          }
          if (!item.weight) {
            newErrors[`item_${item.id}_weight`] = "Veuillez sélectionner un poids";
          }
        }
      });
    } else if (currentStep === 2) {
      if (!shipmentDetails.senderAddress.trim()) {
        newErrors.senderAddress = "L'adresse de départ est requise";
      }
    } else if (currentStep === 3) {
      if (!shipmentDetails.recipientAddress.trim()) {
        newErrors.recipientAddress = "L'adresse d'arrivée est requise";
      }
    } else if (currentStep === 4) {
      if (!shipmentDetails.price.trim()) {
        newErrors.price = "Le prix estimé est requis";
      } else if (!/^\d+(\.\d+)?$/.test(shipmentDetails.price) || parseFloat(shipmentDetails.price) <= 0) {
        newErrors.price = "Le prix doit être un nombre positif";
      }
      
      if (!shipmentDetails.description.trim()) {
        newErrors.description = "La description est requise";
      }
    }
    
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    
    if (!validateCurrentStep()) {
      return;
    }
    
    setIsSubmitting(true);
    
    try {
      // Simuler l'envoi des données à l'API
      console.log("Données envoyées:", { items, shipmentDetails });
      await new Promise(resolve => setTimeout(resolve, 1000)); // Simulation délai réseau
      
      setShowSuccessMessage(true);
      setHasUnsavedChanges(false);
      
      // Fermer après un délai
      setTimeout(() => {
        setShowSuccessMessage(false);
        setIsOpen(false);
        
        // Réinitialiser le formulaire
        setItems([{
          id: 1,
          name: "",
          quantity: "",
          format: "",
          weight: "",
          knowsExactDimensions: false,
          dimensions: { longueur: "", largeur: "", hauteur: "", poids: "" },
          photo: null,
        }]);
        setShipmentDetails({
          senderAddress: "",
          recipientAddress: "",
          price: "",
          description: "",
        });
        setCurrentStep(1);
      }, 2000);
    } catch (error) {
      console.error("Erreur lors de l'envoi:", error);
      setErrors({...errors, submit: "Une erreur est survenue lors de l'envoi du formulaire."});
    } finally {
      setIsSubmitting(false);
    }
  };

  // Fonction pour fermer avec confirmation
  const closeWithConfirmation = () => {
    if (hasUnsavedChanges) {
      setShowExitConfirmation(true);
    } else {
      setIsOpen(false);
    }
  };

  // Gestion de l'échap pour fermer la modale
  useEffect(() => {
    const handleEsc = (e: KeyboardEvent) => {
      if (e.key === 'Escape' && isOpen) {
        closeWithConfirmation();
      }
    };

    window.addEventListener('keydown', handleEsc);
    return () => window.removeEventListener('keydown', handleEsc);
  }, [isOpen, hasUnsavedChanges]);

  return (
    <div>
      <button 
        onClick={() => setIsOpen(true)} 
        className="px-6 py-2 bg-green-700 text-white font-medium rounded-lg shadow-md hover:bg-lime-600 transition-colors"
        aria-label="Créer une nouvelle annonce de livraison"
      >
        Nouvelle annonce
      </button>

      {isOpen && (
        <div 
          className="fixed inset-0 flex justify-center items-center z-50"
          style={{ background: "#00000091" }} 
          role="dialog" 
          aria-modal="true" 
          aria-labelledby="modal-title"
        >
          <div 
            ref={modalRef} 
            className="bg-white dark:bg-gray-800 w-[90%] md:w-[50%] h-[80%] rounded-lg shadow-lg flex flex-col relative"
            aria-describedby="modal-description"
          >
            <div className="flex items-center justify-between mx-6 mt-6 border-b-2 border-black pb-4">
              {["Colis", "Départ", "Arrivée", "Description"].map((step, index) => (
                <div key={index} className="flex items-center">
                  <div
                    className={`w-8 h-8 flex items-center justify-center rounded-full text-white font-bold 
                            ${
                              currentStep > index + 1
                                ? "bg-green-700"
                                : currentStep === index + 1
                                ? "bg-blue-700"
                                : "bg-gray-300"
                            }`}
                    aria-label={`Étape ${index + 1}: ${step}`}
                    aria-current={currentStep === index + 1 ? "step" : undefined}
                  >
                    {index + 1}
                  </div>
                  <span className="ml-2 hidden md:inline">{step}</span>
                </div>
              ))}
              <button 
                onClick={closeWithConfirmation} 
                className="top-4 right-4 p-1 rounded-full bg-gray-200 dark:bg-gray-700 hover:bg-gray-300 transition-colors"
                aria-label="Fermer"
              >
                <XMarkIcon className="w-6 h-6 text-gray-500 dark:text-white" />
              </button>
            </div>

            <div className="overflow-y-auto flex-1 px-6 pb-6">
              <form onSubmit={handleSubmit} className="space-y-4" noValidate>
                {currentStep === 1 && (
                  <>
                    <h2 id="modal-title" className="text-2xl text-center font-semibold text-gray-900 dark:text-white my-10">Quels objets envoyez-vous ?</h2>
                    
                    {errors.submit && (
                      <div className="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative" role="alert">
                        <span className="block sm:inline">{errors.submit}</span>
                      </div>
                    )}
                    
                    {items.map((item, index) => (
                      <div key={item.id} className="bg-gray-100 dark:bg-gray-700 p-4 rounded-lg flex flex-col gap-2">
                        <div className="flex justify-between items-center">
                          <h3 className="font-medium">Objet {index + 1}</h3>
                          {index > 0 && (
                            <button 
                              onClick={() => removeItem(item.id)} 
                              type="button" 
                              className="w-10 p-2 bg-red-500 text-white rounded-full hover:bg-red-600 transition-colors"
                              aria-label="Supprimer cet objet"
                            >
                              <TrashIcon className="w-5 h-5" />
                            </button>
                          )}
                        </div>

                        <div className="flex justify-between">
                          <div className="flex flex-col w-[14%]">
                            <label htmlFor={`quantity-${item.id}`} className="mb-1 text-sm font-medium">Quantité</label>
                            <input
                              id={`quantity-${item.id}`}
                              type="number"
                              min="1"
                              placeholder="1"
                              value={item.quantity}
                              onChange={(e) => handleInputChange(item.id, "quantity", e.target.value)}
                              className={`p-2 border rounded-lg ${errors[`item_${item.id}_quantity`] ? 'border-red-500' : ''}`}
                              aria-invalid={!!errors[`item_${item.id}_quantity`]}
                              aria-describedby={errors[`item_${item.id}_quantity`] ? `quantity-error-${item.id}` : undefined}
                            />
                            {errors[`item_${item.id}_quantity`] && (
                              <p id={`quantity-error-${item.id}`} className="text-red-500 text-xs mt-1">
                                {errors[`item_${item.id}_quantity`]}
                              </p>
                            )}
                          </div>

                          <div className="flex flex-col w-[84%]">
                            <label htmlFor={`name-${item.id}`} className="mb-1 text-sm font-medium">Objet</label>
                            <input
                              id={`name-${item.id}`}
                              type="text"
                              placeholder="Ex : Canapé, fauteuil, scooter ..."
                              value={item.name}
                              onChange={(e) => handleInputChange(item.id, "name", e.target.value)}
                              className={`p-2 border rounded-lg ${errors[`item_${item.id}_name`] ? 'border-red-500' : ''}`}
                              aria-invalid={!!errors[`item_${item.id}_name`]}
                              aria-describedby={errors[`item_${item.id}_name`] ? `name-error-${item.id}` : undefined}
                            />
                            {errors[`item_${item.id}_name`] && (
                              <p id={`name-error-${item.id}`} className="text-red-500 text-xs mt-1">
                                {errors[`item_${item.id}_name`]}
                              </p>
                            )}
                          </div>
                        </div>

                        <div className="flex items-center gap-2 my-5">
                          <label className="relative inline-flex items-center cursor-pointer">
                            <input
                              type="checkbox"
                              className="sr-only peer"
                              checked={item.knowsExactDimensions}
                              onChange={() => toggleDimensions(item.id)}
                              aria-label="Activer les dimensions exactes"
                            />
                            <div className="w-11 h-6 bg-gray-300 peer-focus:ring-2 peer-focus:ring-blue-300 rounded-full peer dark:bg-gray-600 peer-checked:bg-green-600 peer-checked:after:translate-x-5 after:content-[''] after:absolute after:top-0.5 after:left-[2px] after:bg-white after:border after:rounded-full after:h-5 after:w-5 after:transition-all"></div>
                            <span className="ml-5">Je connais les dimensions exactes de mon colis</span>
                          </label>
                        </div>

                        {item.knowsExactDimensions ? (
                          <div className="grid grid-cols-2 gap-4">
                            {['longueur', 'largeur', 'hauteur', 'poids'].map((dim) => (
                              <div className="flex flex-col" key={dim}>
                                <label 
                                  htmlFor={`${dim}-${item.id}`} 
                                  className="mb-1 text-sm font-medium"
                                >
                                  {dim.charAt(0).toUpperCase() + dim.slice(1)} {dim === "poids" ? "(kg)" : "(cm)"}
                                </label>
                                <input
                                  id={`${dim}-${item.id}`}
                                  type="number"
                                  step={dim === "poids" ? "0.1" : "1"}
                                  min="0"
                                  placeholder={`${dim === "poids" ? "75" : "100"}`}
                                  value={item.dimensions[dim as keyof typeof item.dimensions]}
                                  onChange={(e) => handleDimensionChange(item.id, dim, e.target.value)}
                                  className={`p-2 border rounded-lg w-full ${errors[`item_${item.id}_dimension_${dim}`] ? 'border-red-500' : ''}`}
                                  aria-invalid={!!errors[`item_${item.id}_dimension_${dim}`]}
                                  aria-describedby={errors[`item_${item.id}_dimension_${dim}`] ? `${dim}-error-${item.id}` : undefined}
                                />
                                {errors[`item_${item.id}_dimension_${dim}`] && (
                                  <p id={`${dim}-error-${item.id}`} className="text-red-500 text-xs mt-1">
                                    {errors[`item_${item.id}_dimension_${dim}`]}
                                  </p>
                                )}
                              </div>
                            ))}
                          </div>
                        ) : (
                          <div className="grid grid-cols-2 gap-4">
                            <div className="flex flex-col">
                              <label htmlFor={`format-${item.id}`} className="mb-1 text-sm font-medium">Choisir une taille</label>
                              <select
                                id={`format-${item.id}`}
                                value={item.format}
                                onChange={(e) => handleInputChange(item.id, "format", e.target.value)}
                                className={`p-2 border rounded-lg w-full ${errors[`item_${item.id}_format`] ? 'border-red-500' : ''}`}
                                aria-invalid={!!errors[`item_${item.id}_format`]}
                                aria-describedby={errors[`item_${item.id}_format`] ? `format-error-${item.id}` : undefined}
                              >
                                <option value="">-</option>
                                <option value="S">Petit</option>
                                <option value="M">Moyen</option>
                                <option value="L">Grand</option>
                                <option value="XL">Très grand</option>
                              </select>
                              {errors[`item_${item.id}_format`] && (
                                <p id={`format-error-${item.id}`} className="text-red-500 text-xs mt-1">
                                  {errors[`item_${item.id}_format`]}
                                </p>
                              )}
                            </div>
                            <div className="flex flex-col">
                              <label htmlFor={`weight-${item.id}`} className="mb-1 text-sm font-medium">Choisir un intervalle de poids</label>
                              <select
                                id={`weight-${item.id}`}
                                value={item.weight}
                                onChange={(e) => handleInputChange(item.id, "weight", e.target.value)}
                                className={`p-2 border rounded-lg w-full ${errors[`item_${item.id}_weight`] ? 'border-red-500' : ''}`}
                                aria-invalid={!!errors[`item_${item.id}_weight`]}
                                aria-describedby={errors[`item_${item.id}_weight`] ? `weight-error-${item.id}` : undefined}
                              >
                                <option value="">-</option>
                                <option value="0-2kg">0-2kg</option>
                                <option value="2-5kg">2-5kg</option>
                                <option value="5-10kg">5-10kg</option>
                                <option value="10-20kg">10-20kg</option>
                                <option value="20-50kg">20-50kg</option>
                                <option value="50kg+">50kg+</option>
                              </select>
                              {errors[`item_${item.id}_weight`] && (
                                <p id={`weight-error-${item.id}`} className="text-red-500 text-xs mt-1">
                                  {errors[`item_${item.id}_weight`]}
                                </p>
                              )}
                            </div>
                          </div>
                        )}
                        
                        <div className="flex flex-col items-center justify-center">
                          <label htmlFor={`photo-${item.id}`} className="flex items-center gap-2 cursor-pointer my-5">
                            <CameraIcon className="w-6 h-6" />
                            <span>Ajouter une photo</span>
                            <input
                              id={`photo-${item.id}`}
                              type="file"
                              accept="image/jpeg, image/png, image/gif, image/webp"
                              className="hidden"
                              onChange={(e) => handlePhotoUpload(item.id, e.target.files?.[0] || null)}
                              aria-label="Télécharger une photo de l'objet"
                            />
                          </label>
                          {errors[`item_${item.id}_photo`] && (
                            <p className="text-red-500 text-xs text-center">
                              {errors[`item_${item.id}_photo`]}
                            </p>
                          )}
                        </div>
                        {item.photo && (
                          <div className="relative">
                            <img
                              src={item.photo}
                              alt={`Photo de ${item.name || 'l\'objet'}`}
                              className="w-50 h-50 object-cover rounded-lg m-auto"
                            />
                            <button
                              type="button"
                              onClick={() => handleInputChange(item.id, "photo", "")}
                              className="absolute top-2 right-2 bg-red-500 text-white rounded-full p-1 hover:bg-red-600 transition-colors"
                              aria-label="Supprimer la photo"
                            >
                              <XMarkIcon className="w-5 h-5" />
                            </button>
                          </div>
                        )}
                      </div>
                    ))}
                  </>
                )}

                {currentStep === 2 && (
                  <>
                    <h2 id="modal-title" className="text-2xl text-center font-semibold text-gray-900 dark:text-white my-10">Adresse de départ</h2>
                    <div className="flex flex-col">
                      <label htmlFor="senderAddress" className="mb-1 text-sm font-medium">Adresse de départ</label>
                      <input
                        id="senderAddress"
                        type="text"
                        name="senderAddress"
                        placeholder="Adresse de départ"
                        value={shipmentDetails.senderAddress}
                        onChange={handleShipmentChange}
                        className={`w-full p-2 border rounded-lg ${errors.senderAddress ? 'border-red-500' : ''}`}
                        aria-invalid={!!errors.senderAddress}
                        aria-describedby={errors.senderAddress ? "senderAddress-error" : undefined}
                      />
                      {errors.senderAddress && (
                        <p id="senderAddress-error" className="text-red-500 text-xs mt-1">
                          {errors.senderAddress}
                        </p>
                      )}
                    </div>
                  </>
                )}

                {currentStep === 3 && (
                  <>
                    <h2 id="modal-title" className="text-2xl text-center font-semibold text-gray-900 dark:text-white my-10">Adresse d'arrivée</h2>
                    <div className="flex flex-col">
                      <label htmlFor="recipientAddress" className="mb-1 text-sm font-medium">Adresse d'arrivée</label>
                      <input
                        id="recipientAddress"
                        type="text"
                        name="recipientAddress"
                        placeholder="Adresse d'arrivée"
                        value={shipmentDetails.recipientAddress}
                        onChange={handleShipmentChange}
                        className={`w-full p-2 border rounded-lg ${errors.recipientAddress ? 'border-red-500' : ''}`}
                        aria-invalid={!!errors.recipientAddress}
                        aria-describedby={errors.recipientAddress ? "recipientAddress-error" : undefined}
                      />
                      {errors.recipientAddress && (
                        <p id="recipientAddress-error" className="text-red-500 text-xs mt-1">
                          {errors.recipientAddress}
                        </p>
                      )}
                    </div>
                  </>
                )}

                {currentStep === 4 && (
                  <>
                    <h2 id="modal-title" className="text-2xl text-center font-semibold text-gray-900 dark:text-white my-10">Informations complémentaires</h2>

                    <div className="flex flex-col mb-4">
                      <label htmlFor="price" className="mb-1 text-sm font-medium">Prix estimé (€)</label>
                      <input
                        id="price"
                        type="number"
                        min="0"
                        step="0.01"
                        name="price"
                        placeholder="Ex: 150"
                        value={shipmentDetails.price}
                        onChange={handleShipmentChange}
                        className={`w-full p-2 border rounded-lg ${errors.price ? 'border-red-500' : ''}`}
                        aria-invalid={!!errors.price}
                        aria-describedby={errors.price ? "price-error" : undefined}
                      />
                      {errors.price && (
                        <p id="price-error" className="text-red-500 text-xs mt-1">
                          {errors.price}
                        </p>
                      )}
                    </div>

                    <div className="flex flex-col mb-2">
                      <label htmlFor="description" className="mb-1 text-sm font-medium">Description du colis</label>
                      <textarea
                        id="description"
                        name="description"
                        placeholder="Ex: Le carton le plus long fait 2m15, le plus lourd est un canapé"
                        value={shipmentDetails.description}
                        onChange={handleShipmentChange}
                        className="w-full p-2 border rounded-lg h-24 resize-none"
                        required
                      />
                    </div>

                    <p className="text-xs text-gray-500">
                      Ces informations sont publiques. Pour préserver votre vie privée, n’indiquez pas vos coordonnées (adresse e-mail, téléphone ou adresse).
                    </p>
                  </>
                )}

                <div className="flex justify-between">
                  {currentStep == 1 && (
                    <button type="button" onClick={addItem} className="w-[48%] mt-10 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600">Ajouter un objet</button>
                  )}
                  {currentStep > 1 && (
                    <button onClick={prevStep} className="w-[48%] mt-10 py-2 px-4 bg-gray-400 hover:bg-gray-500 text-white rounded-lg">Retour</button>
                  )}
                  {currentStep < 4 ? (
                    <button onClick={nextStep} className="w-[48%] mt-10 py-2 px-4 bg-green-600 hover:bg-green-700 text-white rounded-lg">Suivant</button>
                  ) : (
                    <button type="submit" className="w-[48%] mt-10 py-2 px-4 bg-blue-600 hover:bg-blue-700 text-white rounded-lg">Valider</button>
                  )}
                </div>
              </form>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
