"use client";

import { useState, useEffect, useRef } from "react";
import type { Address } from "@/lib/types";

interface SearchResult {
  address: {
    road?: string;
    house_number?: string;
    city?: string;
    town?: string;
    village?: string;
    hamlet?: string;
    state?: string;
    postcode?: string;
    country?: string;
  };
  lat: string;
  lon: string;
  display_name: string;
}

interface InputAddressProps {
  value?: Partial<Address>;
  onChange: (address: Address) => void;
  required?: boolean;
  error?: string;
  suiteError?: string;
  label?: string;
  placeholder?: string;
  suiteLabel?: string;
  suitePlaceholder?: string;
  className?: string;
  countryCode?: string;
}

const InputAddress: React.FC<InputAddressProps> = ({
  value = {} as Address,
  onChange,
  required = false,
  error = "",
  suiteError = "",
  label = "Adresse",
  placeholder = "Commencez à taper votre adresse",
  suiteLabel = "Complément d'adresse",
  suitePlaceholder = "Appartement, bâtiment, étage...",
  className = "",
  countryCode = "fr",
}) => {
  const [inputValue, setInputValue] = useState("");
  const [suiteValue, setSuiteValue] = useState("");
  const [searchResults, setSearchResults] = useState<SearchResult[]>([]);
  const [showResults, setShowResults] = useState(false);
  const [isValidSelection, setIsValidSelection] = useState(false);
  const [isTyping, setIsTyping] = useState(false);
  const [searchError, setSearchError] = useState<string | null>(null);
  const inputRef = useRef<HTMLInputElement>(null);
  const resultsRef = useRef<HTMLDivElement>(null);
  
  useEffect(() => {
    if (value) {
      if (value.location) {
        setInputValue(value.location);
        setIsValidSelection(Boolean(value.isValidSelection));
      }
      if (value.suite) {
        setSuiteValue(value.suite);
      }
    }
  }, [value?.location, value?.isValidSelection, value?.suite]);
  
  useEffect(() => {
    function handleClickOutside(event: MouseEvent): void {
      if (
        resultsRef.current && 
        !resultsRef.current.contains(event.target as Node) &&
        inputRef.current && !inputRef.current.contains(event.target as Node)
      ) {
        setShowResults(false);
      }
    }

    document.addEventListener("mousedown", handleClickOutside);
    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, []);
  
  const searchAddress = async (query: string): Promise<void> => {
    if (!query || query.length < 3) {
      setSearchResults([]);
      setShowResults(false);
      setSearchError(null);
      return;
    }

    setIsTyping(true);
    setSearchError(null);

    try {
      const abortController = new AbortController();
      const timeoutId = setTimeout(() => abortController.abort(), 5000);
      
      const response = await fetch(
        `https://nominatim.openstreetmap.org/search?format=json&q=${encodeURIComponent(
          query
        )}&addressdetails=1&limit=5&countrycodes=${countryCode}`
      );

      clearTimeout(timeoutId);

      if (!response.ok) {
        throw new Error(`Erreur réseau: ${response.status} - ${response.statusText}`);
      }

      const data: SearchResult[] = await response.json();
      setSearchResults(data);
      setShowResults(data.length > 0);
      
      if (data.length === 0) {
        setSearchError("Aucune adresse trouvée. Veuillez préciser votre recherche.");
      }
    } catch (error) {
      console.error("Erreur lors de la recherche d'adresse:", error);
      setSearchError("Impossible de rechercher des adresses. Veuillez réessayer plus tard.");
      setSearchResults([]);
      setShowResults(false);
    } finally {
      setIsTyping(false);
    }
  };
  
  useEffect(() => {
    const timer = setTimeout(() => {
      if (inputValue) {
        searchAddress(inputValue);
      }
    }, 300);

    return () => clearTimeout(timer);
  }, [inputValue, countryCode]);

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>): void => {
    const input: string = e.target.value;
    setInputValue(input);
    setIsValidSelection(false);
    
    // Mettre à jour l'adresse avec les nouvelles valeurs
    updateAddress({
      location: input,
      isValidSelection: false,
    });
  };
  
  const handleSuiteChange = (e: React.ChangeEvent<HTMLInputElement>): void => {
    const input: string = e.target.value;
    setSuiteValue(input);
    
    // Mettre à jour l'adresse avec la nouvelle valeur de suite
    updateAddress({
      suite: input,
    });
  };
  
  // Fonction utilitaire pour mettre à jour l'adresse
  const updateAddress = (newValues: Partial<Address>) => {
    const updatedAddress: Address = {
      location: newValues.location !== undefined ? newValues.location : value?.location || "",
      suite: newValues.suite !== undefined ? newValues.suite : value?.suite || "",
      locality: newValues.locality !== undefined ? newValues.locality : value?.locality || "",
      state: newValues.state !== undefined ? newValues.state : value?.state || "",
      postal_code: newValues.postal_code !== undefined ? newValues.postal_code : value?.postal_code || "",
      country: newValues.country !== undefined ? newValues.country : value?.country || "",
      latitude: newValues.latitude !== undefined ? newValues.latitude : value?.latitude || "",
      longitude: newValues.longitude !== undefined ? newValues.longitude : value?.longitude || "",
      isValidSelection: newValues.isValidSelection !== undefined ? newValues.isValidSelection : value?.isValidSelection || false,
    };
    
    onChange(updatedAddress);
  };

  const selectAddress = (result: SearchResult) => {
    const { address, lat, lon } = result;
    const formattedAddress = `${address.house_number || ""} ${address.road || ""}`.trim();
    
    setInputValue(formattedAddress);
    setSearchError(null);
    
    // Mettre à jour l'adresse avec les valeurs sélectionnées
    updateAddress({
      location: formattedAddress,
      locality: address.city || address.town || address.village || address.hamlet || "",
      state: address.state || "",
      postal_code: address.postcode || "",
      country: address.country || "",
      latitude: lat,
      longitude: lon,
      isValidSelection: true,
    });
    
    setShowResults(false);
    setIsValidSelection(true);
  };

  const handleKeyDown = (e: React.KeyboardEvent) => {
    if (e.key === 'Escape') {
      setShowResults(false);
    }
  };

  return (
    <div className={`space-y-4 ${className}`}>
      <div className="relative">
        {label && (
          <label
            htmlFor="address-input"
            className="block text-sm font-medium text-gray-700 dark:text-white mb-1"
          >
            {label}{required}
          </label>
        )}
        
        <div className="relative">
          <input
            ref={inputRef}
            type="text"
            id="address-input"
            value={inputValue}
            onChange={handleInputChange}
            onFocus={() => inputValue.length >= 3 && setShowResults(true)}
            onKeyDown={handleKeyDown}
            placeholder={placeholder}
            aria-invalid={!!error}
            aria-describedby={error ? "address-error" : undefined}
            className={`block w-full border rounded-md shadow-sm py-2 px-3 focus:outline-emerald-500 bg-white text-black dark:bg-gray-700 dark:text-white ${
              error ? "border-red-500 dark:border-red-900" : "border-gray-300 dark:border-gray-600"
            }`}
            required={required}
          />
          
          {isTyping && (
            <div className="absolute right-3 top-1/2 transform -translate-y-1/2">
              <svg className="animate-spin h-4 w-4 text-gray-500" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                <circle className="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" strokeWidth="4"></circle>
                <path className="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
              </svg>
            </div>
          )}
          
          {required && !isValidSelection && inputValue && !isTyping && (
            <div className="absolute right-3 top-1/2 transform -translate-y-1/2">
              <svg className="h-5 w-5 text-red-500" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z" />
              </svg>
            </div>
          )}
          
          {isValidSelection && (
            <div className="absolute right-3 top-1/2 transform -translate-y-1/2">
              <svg className="h-5 w-5 text-green-500" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M5 13l4 4L19 7" />
              </svg>
            </div>
          )}
        </div>
        
        {showResults && (
          <div 
            ref={resultsRef}
            role="listbox"
            className="absolute z-10 w-full bg-white dark:bg-gray-800 shadow-lg rounded-md mt-1 border border-gray-200 dark:border-gray-700 max-h-60 overflow-y-auto"
          >
            {searchResults.length > 0 ? (
              searchResults.map((result, index) => (
                <div
                  key={index}
                  role="option"
                  aria-selected={false}
                  className="px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-700 cursor-pointer text-sm"
                  onClick={() => selectAddress(result)}
                >
                  {result.display_name}
                </div>
              ))
            ) : (
              searchError && (
                <div className="px-4 py-3 text-sm text-red-600 dark:text-red-400">
                  {searchError}
                </div>
              )
            )}
          </div>
        )}
        
        {error && (
          <p id="address-error" className="mt-1 text-sm text-red-600 dark:text-red-400">{error}</p>
        )}
        
        {required && !isValidSelection && inputValue && !error && (
          <p className="mt-1 text-sm text-red-600 dark:text-red-400">
            Veuillez sélectionner une adresse dans la liste
          </p>
        )}
      </div>
      
      {/* Champ "Complément d'adresse" intégré */}
      <div>
        <label
          htmlFor="address-suite"
          className="block text-sm font-medium text-gray-700 dark:text-white mb-1"
        >
          {suiteLabel}
        </label>
        <input
          type="text"
          id="address-suite"
          value={suiteValue}
          onChange={handleSuiteChange}
          placeholder={suitePlaceholder}
          aria-invalid={!!suiteError}
          aria-describedby={suiteError ? "suite-error" : undefined}
          className={`block w-full border rounded-md shadow-sm py-2 px-3 focus:outline-emerald-500 bg-white text-black dark:bg-gray-700 dark:text-white ${
            suiteError ? "border-red-500 dark:border-red-900" : "border-gray-300 dark:border-gray-600"
          }`}
        />
        {suiteError && (
          <p id="suite-error" className="mt-1 text-sm text-red-600 dark:text-red-400">{suiteError}</p>
        )}
      </div>
    </div>
  );
};

export default InputAddress;