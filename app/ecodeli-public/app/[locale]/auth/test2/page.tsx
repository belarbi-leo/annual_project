"use client";import React, { useEffect, useRef, useState } from 'react';

function AddressSelectionForm() {
  const locationInputRef = useRef(null);
  const [formData, setFormData] = useState({
    location: '',
    suite: '',
    locality: '',
    administrative_area_level_1: '',
    postal_code: '',
    country: ''
  });

  useEffect(() => {
    // Load Google Maps API script
    const loadGoogleMapsScript = () => {
      const googleMapsScript = document.createElement('script');
      googleMapsScript.src = `https://maps.googleapis.com/maps/api/js?key=AIzaSyACrz4KyaCvUhjFMPfbEJiYPpiKuTnR8NQ&libraries=places`;
      googleMapsScript.async = true;
      googleMapsScript.defer = true;
      googleMapsScript.onload = initAutocomplete;
      document.head.appendChild(googleMapsScript);
    };

    loadGoogleMapsScript();
  }, []);

  const initAutocomplete = () => {
    if (!window.google || !locationInputRef.current) return;

    const autocomplete = new window.google.maps.places.Autocomplete(locationInputRef.current, {
      fields: ['address_components', 'geometry', 'name'],
      types: ['address'],
    });

    autocomplete.addListener('place_changed', () => {
      const place = autocomplete.getPlace();
      
      if (!place.geometry) {
        alert(`No details available for input: '${place.name}'`);
        return;
      }

      fillInAddress(place);
    });
  };

  const fillInAddress = (place) => {
    const addressData = {
      location: '',
      locality: '',
      administrative_area_level_1: '',
      postal_code: '',
      country: ''
    };

    // Helper function to get component by type
    const getComponentName = (componentType) => {
      for (const component of place.address_components || []) {
        if (component.types[0] === componentType) {
          return ['street_number', 'administrative_area_level_1', 'postal_code'].includes(componentType) ?
            component.short_name : component.long_name;
        }
      }
      return '';
    };

    // Build street address (location)
    addressData.location = `${getComponentName('street_number')} ${getComponentName('route')}`.trim();
    addressData.locality = getComponentName('locality');
    addressData.administrative_area_level_1 = getComponentName('administrative_area_level_1');
    addressData.postal_code = getComponentName('postal_code');
    addressData.country = getComponentName('country');

    setFormData(prevData => ({
      ...prevData,
      ...addressData
    }));
  };

  const handleInputChange = (e) => {
    const { id, value } = e.target;
    setFormData(prevData => ({
      ...prevData,
      [id]: value
    }));
  };

  return (
    <div className="flex h-96 w-72">
      <div className="bg-white p-5 w-full h-full flex flex-col justify-between">
        <div>
          <div className="flex items-center">
            <img 
              className="relative -top-1" 
              src="https://fonts.gstatic.com/s/i/googlematerialicons/location_pin/v5/24px.svg" 
              alt="" 
            />
            <span className="relative -top-3 font-medium">Address Selection</span>
          </div>
        </div>
        
        <input
          ref={locationInputRef}
          type="text"
          id="location"
          placeholder="Address"
          value={formData.location}
          onChange={handleInputChange}
          className="border-b border-black h-8 text-sm mb-4"
        />
        
        <input
          type="text"
          id="suite"
          placeholder="Apt, Suite, etc (optional)"
          value={formData.suite}
          onChange={handleInputChange}
          className="border-b border-black h-8 text-sm mb-4"
        />
        
        <input
          type="text"
          id="locality"
          placeholder="City"
          value={formData.locality}
          onChange={handleInputChange}
          className="border-b border-black h-8 text-sm mb-4"
        />
        
        <div className="flex justify-between mb-4">
          <input
            type="text"
            id="administrative_area_level_1"
            placeholder="State/Province"
            value={formData.administrative_area_level_1}
            onChange={handleInputChange}
            className="border-b border-black h-8 text-sm w-28"
          />
          <input
            type="text"
            id="postal_code"
            placeholder="Zip/Postal code"
            value={formData.postal_code}
            onChange={handleInputChange}
            className="border-b border-black h-8 text-sm w-28"
          />
        </div>
        
        <input
          type="text"
          id="country"
          placeholder="Country"
          value={formData.country}
          onChange={handleInputChange}
          className="border-b border-black h-8 text-sm mb-4"
        />
      </div>
    </div>
  );
}

export default AddressSelectionForm;