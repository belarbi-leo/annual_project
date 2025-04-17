"use client";

import { useState, useEffect, useRef } from 'react';

const AddressForm = () => {
  const [formData, setFormData] = useState({
    adresse: '',
    ville: '',
    codePostal: '',
    pays: 'France'
  });
  
  const [errors, setErrors] = useState({});
  const autoCompleteRef = useRef(null);
  const inputRef = useRef(null);
  
  useEffect(() => {
    // Charger le script Google Places API
    const loadGoogleMapsScript = () => {
      // Remplacez YOUR_API_KEY par votre clé API Google
      const googleMapScript = document.createElement('script');
      googleMapScript.src = `https://maps.googleapis.com/maps/api/js?key=AIzaSyACrz4KyaCvUhjFMPfbEJiYPpiKuTnR8NQ&libraries=places&language=fr`;
      googleMapScript.async = true;
      googleMapScript.defer = true;
      window.document.body.appendChild(googleMapScript);
      
      googleMapScript.addEventListener('load', () => {
        initAutocomplete();
      });
    };
    
    // Initialiser l'autocomplete
    const initAutocomplete = () => {
      if (!inputRef.current) return;
      
      autoCompleteRef.current = new window.google.maps.places.Autocomplete(
        inputRef.current,
        { 
          types: ['address'],
          componentRestrictions: { country: formData.pays.toLowerCase() }
        }
      );
      
      autoCompleteRef.current.addListener('place_changed', handlePlaceSelect);
    };
    
    loadGoogleMapsScript();
    
    return () => {
      // Nettoyer l'autocomplete si le composant est démonté
      if (autoCompleteRef.current) {
        window.google.maps.event.clearInstanceListeners(autoCompleteRef.current);
      }
    };
  }, [formData.pays]);
  
  const handlePlaceSelect = () => {
    const place = autoCompleteRef.current.getPlace();
    
    if (!place.geometry) return;
    
    // Extraire les composants d'adresse
    let streetNumber = '';
    let route = '';
    let locality = '';
    let postalCode = '';
    let country = '';
    
    for (const component of place.address_components) {
      const componentType = component.types[0];
      
      switch (componentType) {
        case 'street_number':
          streetNumber = component.long_name;
          break;
        case 'route':
          route = component.long_name;
          break;
        case 'locality':
          locality = component.long_name;
          break;
        case 'postal_code':
          postalCode = component.long_name;
          break;
        case 'country':
          country = component.long_name;
          break;
        default:
          break;
      }
    }
    
    // Mettre à jour le formulaire avec les données extraites
    setFormData({
      ...formData,
      adresse: streetNumber ? `${streetNumber} ${route}` : route,
      ville: locality,
      codePostal: postalCode,
      pays: country
    });
  };
  
  const handleChange = (e) => {
    const { name, value } = e.target;
    
    setFormData(prevData => ({
      ...prevData,
      [name]: value
    }));
    
    // Si le pays change, mettre à jour les restrictions de l'autocomplete
    if (name === 'pays' && autoCompleteRef.current) {
      autoCompleteRef.current.setComponentRestrictions({
        country: value.toLowerCase()
      });
    }
  };
  
  const validateForm = () => {
    const newErrors = {};
    
    if (!formData.adresse.trim()) {
      newErrors.adresse = "L'adresse est requise";
    }
    if (!formData.ville.trim()) {
      newErrors.ville = "La ville est requise";
    }
    if (!formData.codePostal.trim()) {
      newErrors.codePostal = "Le code postal est requis";
    }
    
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };
  
  const handleSubmit = (e) => {
    e.preventDefault();
    
    if (validateForm()) {
      // Traitez le formulaire ici
      console.log('Formulaire soumis avec succès', formData);
    }
  };
  
  return (
    <form onSubmit={handleSubmit} className="space-y-6">
      <div>
        <label htmlFor="adresse" className="block text-sm font-medium text-gray-700">
          Adresse
        </label>
        <input
          type="text"
          name="adresse"
          id="adresse"
          ref={inputRef}
          value={formData.adresse}
          onChange={handleChange}
          className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
          placeholder="Commencez à taper votre adresse"
        />
        {errors.adresse && <p className="mt-1 text-sm text-red-600">{errors.adresse}</p>}
      </div>
      
      <div className="grid grid-cols-1 gap-4 sm:grid-cols-2">
        <div>
          <label htmlFor="ville" className="block text-sm font-medium text-gray-700">
            Ville
          </label>
          <input
            type="text"
            name="ville"
            id="ville"
            value={formData.ville}
            onChange={handleChange}
            className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
          />
          {errors.ville && <p className="mt-1 text-sm text-red-600">{errors.ville}</p>}
        </div>
        
        <div>
          <label htmlFor="codePostal" className="block text-sm font-medium text-gray-700">
            Code postal
          </label>
          <input
            type="text"
            name="codePostal"
            id="codePostal"
            value={formData.codePostal}
            onChange={handleChange}
            className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
          />
          {errors.codePostal && <p className="mt-1 text-sm text-red-600">{errors.codePostal}</p>}
        </div>
      </div>
      
      <div>
        <label htmlFor="pays" className="block text-sm font-medium text-gray-700">
          Pays
        </label>
        <select
          name="pays"
          id="pays"
          value={formData.pays}
          onChange={handleChange}
          className="mt-1 block w-full border border-gray-300 rounded-md shadow-sm py-2 px-3 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500"
        >
          <option value="France">France</option>
          <option value="Belgique">Belgique</option>
          <option value="Suisse">Suisse</option>
          <option value="Canada">Canada</option>
          <option value="Autre">Autre</option>
        </select>
      </div>
      
      <div>
        <button
          type="submit"
          className="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
        >
          Valider
        </button>
      </div>
    </form>
  );
};

export default AddressForm;
