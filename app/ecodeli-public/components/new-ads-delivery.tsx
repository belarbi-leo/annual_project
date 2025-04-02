"use client";

import { useState, useEffect, useRef } from "react";
import { XMarkIcon, TrashIcon, CameraIcon } from "@heroicons/react/24/outline";

export default function NewDeliveryRequest() {
  const [isOpen, setIsOpen] = useState(false);
  const [currentStep, setCurrentStep] = useState(1);
  const [knowsExactDimensions, setKnowsExactDimensions] = useState(false);
  const [items, setItems] = useState([
    { id: 1, name: "", quantity: "", format: "", weight: "", dimensions: { length: "", width: "", height: "" }, photo: null },
  ]);
  const [shipmentDetails, setShipmentDetails] = useState({
    senderAddress: "",
    recipientAddress: "",
    price: "",
  });

  const modalRef = useRef<HTMLDivElement>(null);

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
      setIsOpen(false);
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
  }, [isOpen]);

  // Gérer le changement des champs
  const handleInputChange = (id: number, field: string, value: string) => {
    setItems((prevItems) =>
      prevItems.map((item) =>
        item.id === id ? { ...item, [field]: value } : item
      )
    );
  };

  const handleShipmentChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setShipmentDetails((prev) => ({ ...prev, [name]: value }));
  };

  // Ajouter un nouvel objet
  const addItem = () => {
    setItems([
      ...items,
      {
        id: Date.now(),
        name: "",
        quantity: "",
        format: "",
        weight: "",
        photo: null,
      },
    ]);
  };

  // Supprimer un objet
  const removeItem = (id: number) => {
    setItems(items.filter((item) => item.id !== id));
  };

  // Gérer l'upload de photo
  const handlePhotoUpload = (id: number, file: File | null) => {
    const reader = new FileReader();
    reader.onload = (event) => {
      const imageUrl = event.target?.result as string;
      setItems((prevItems) =>
        prevItems.map((item) =>
          item.id === id ? { ...item, photo: imageUrl } : item
        )
      );
    };
    if (file) {
      reader.readAsDataURL(file);
    }
  };

  const nextStep = () => setCurrentStep((prev) => prev + 1);
  const prevStep = () => setCurrentStep((prev) => prev - 1);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    console.log("Données envoyées:", { items, shipmentDetails });
    setIsOpen(false);
  };

  return (
    <div>
      <button onClick={() => setIsOpen(true)} className="px-6 py-2 bg-green-700 text-white font-medium rounded-lg shadow-md hover:bg-lime-600">Nouvelle annonce</button>

      {isOpen && (
        <div className="fixed inset-0 flex justify-center items-center z-50" style={{ background: "#00000091" }}>
          <div ref={modalRef} className="bg-white dark:bg-gray-800 w-[90%] md:w-[50%] h-[80%] rounded-lg shadow-lg flex flex-col relative">
            <div className="flex items-center justify-between mx-6 mt-6 border-b-2 border-black pb-4">
              {["Colis", "Départ", "Arrivée", "Prix"].map((step, index) => (
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
                  >
                    {index + 1}
                  </div>
                </div>
              ))}
              <button onClick={() => setIsOpen(false)} className="top-4 right-4 p-1 rounded-full bg-gray-200 dark:bg-gray-700 hover:bg-gray-300">
                <XMarkIcon className="w-6 h-6 text-gray-900 dark:text-white" />
              </button>
            </div>

            <div className="overflow-y-auto flex-1 px-6 pb-6">
              <form onSubmit={handleSubmit} className="space-y-4">
                {currentStep === 1 && (
                  <>
                    <h2 className="text-2xl text-center font-semibold text-gray-900 dark:text-white my-4">
                      Quels objets envoyez-vous ?
                    </h2>
                    {items.map((item) => (
                      <div key={item.id} className="bg-gray-100 dark:bg-gray-700 p-4 rounded-lg flex flex-col gap-2">
                        <div className="flex justify-between">
                          <div className="flex flex-col w-[14%]">
                            <label className="mb-1 text-sm font-medium">Quantité</label>
                            <input
                              type="text"
                              placeholder="1"
                              value={item.quantity}
                              onChange={(e) =>
                                handleInputChange(
                                  item.id,
                                  "quantity",
                                  e.target.value
                                )
                              }
                              className="p-2 border rounded-lg"
                              required
                            />
                          </div>

                          <div className="flex flex-col w-[84%]">
                            <label className="mb-1 text-sm font-medium">Objet</label>
                            <input
                              type="text"
                              placeholder="Ex : Canapé, fauteuil, scooter ..."
                              value={item.name}
                              onChange={(e) =>
                                handleInputChange(
                                  item.id,
                                  "name",
                                  e.target.value
                                )
                              }
                              className="p-2 border rounded-lg"
                              required
                            />
                          </div>
                        </div>

                        <div className="flex items-center gap-2">
                            <label className="relative inline-flex items-center cursor-pointer">
                                <input
                                    type="checkbox"
                                    className="sr-only peer"
                                    checked={knowsExactDimensions}
                                    onChange={() => setKnowsExactDimensions(!knowsExactDimensions)}
                                />
                                <div className="w-11 h-6 bg-gray-300 peer-focus:ring-2 peer-focus:ring-blue-300 rounded-full peer dark:bg-gray-600 peer-checked:bg-green-600 peer-checked:after:translate-x-5 after:content-[''] after:absolute after:top-0.5 after:left-[2px] after:bg-white after:border after:rounded-full after:h-5 after:w-5 after:transition-all"></div>
                                <span className="text-sm font-medium">Je connaît les dimensions exactes de mon colis</span>
                            </label>
                        </div>

                        <div className="flex gap-4 mt-2">
                          <select
                            value={item.format}
                            onChange={(e) =>
                              handleInputChange(
                                item.id,
                                "format",
                                e.target.value
                              )
                            }
                            className="w-1/2 p-2 border rounded-lg"
                            required
                          >
                            <option value="">Choisir une taille</option>
                            <option value="Petit">Petit</option>
                            <option value="Moyen">Moyen</option>
                            <option value="Grand">Grand</option>
                          </select>
                          <select
                            value={item.weight}
                            onChange={(e) =>
                              handleInputChange(
                                item.id,
                                "weight",
                                e.target.value
                              )
                            }
                            className="w-1/2 p-2 border rounded-lg"
                            required
                          >
                            <option value="">
                              Choisir un intervalle de poids
                            </option>
                            <option value="0-5kg">0-5kg</option>
                            <option value="5-10kg">5-10kg</option>
                            <option value="10kg+">10kg+</option>
                          </select>
                        </div>

                        <button onClick={() => removeItem(item.id)} type="button" className="p-2 bg-red-500 text-white rounded-lg hover:bg-red-600">
                          <TrashIcon className="w-5 h-5" />
                        </button>

                        <label className="flex items-center gap-2 cursor-pointer">
                          <CameraIcon className="w-6 h-6" />
                          <span>Ajouter une photo</span>
                          <input
                            type="file"
                            accept="image/*"
                            className="hidden"
                            onChange={(e) =>
                              handlePhotoUpload(
                                item.id,
                                e.target.files?.[0] || null
                              )
                            }
                          />
                        </label>
                        {item.photo && (
                          <img
                            src={item.photo}
                            alt="Aperçu"
                            className="w-50 h-50 object-cover rounded-lg m-auto"
                          />
                        )}
                      </div>
                    ))}
                    <button type="button" onClick={addItem} className="w-full py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600">Ajouter un objet</button>
                  </>
                )}

                {currentStep === 2 && (
                  <>
                    <h2 className="text-2xl text-center font-semibold text-gray-900 dark:text-white my-4">Adresse de départ</h2>
                    <input
                      type="text"
                      name="senderAddress"
                      placeholder="Adresse de départ"
                      value={shipmentDetails.senderAddress}
                      onChange={handleShipmentChange}
                      className="w-full p-2 border rounded-lg"
                      required
                    />
                  </>
                )}

                {currentStep === 3 && (
                  <>
                    <h2 className="text-2xl text-center font-semibold text-gray-900 dark:text-white my-4">Adresse d’arrivée</h2>
                    <input
                      type="text"
                      name="recipientAddress"
                      placeholder="Adresse d’arrivée"
                      value={shipmentDetails.recipientAddress}
                      onChange={handleShipmentChange}
                      className="w-full p-2 border rounded-lg"
                      required
                    />
                  </>
                )}

                {currentStep === 4 && (
                  <>
                    <h2 className="text-2xl text-center font-semibold text-gray-900 dark:text-white my-4">Prix</h2>
                    <input
                      type="text"
                      name="price"
                      placeholder="Prix estimé (€)"
                      value={shipmentDetails.price}
                      onChange={handleShipmentChange}
                      className="w-full p-2 border rounded-lg"
                      required
                    />
                  </>
                )}

                <div className="flex justify-between">
                  {currentStep > 1 && (
                    <button onClick={prevStep} className="py-2 px-4 bg-gray-400 text-white rounded-lg">Retour</button>
                  )}
                  {currentStep < 4 ? (
                    <button onClick={nextStep} className="py-2 px-4 bg-green-600 text-white rounded-lg">Suivant</button>
                  ) : (
                    <button type="submit" className="py-2 px-4 bg-blue-600 text-white rounded-lg">Valider</button>
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
