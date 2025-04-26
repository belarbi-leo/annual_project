import { useState } from "react";

/**
 * Composant de téléchargement de photo de profil
 * 
 * @param {Object} props
 * @param {string|File|null} props.currentPhoto - URL de la photo actuelle, objet File, ou null/vide
 * @param {function} props.onChange - Fonction appelée lors du changement de photo
 * @param {string} props.defaultPhotoUrl - URL de la photo par défaut (optionnel)
 * @param {string} props.error - Message d'erreur (optionnel)
 * @param {string} props.label - Texte du label (optionnel)
 * @param {string} props.size - Taille du composant ("sm", "md", "lg") (optionnel, par défaut "md")
 */
export default function PhotoUpload({
  currentPhoto,
  onChange,
  defaultPhotoUrl = "/images/default-avatar.png",
  error,
  label = "Photo de profil",
  size = "md"
}) {
  // Détermine si la photo actuelle est un objet File ou une URL
  const [preview, setPreview] = useState(
    currentPhoto instanceof File 
      ? URL.createObjectURL(currentPhoto) 
      : (currentPhoto || defaultPhotoUrl)
  );

  // Tailles disponibles pour l'avatar
  const sizeClasses = {
    sm: "w-20 h-20",
    md: "w-32 h-32",
    lg: "w-40 h-40"
  };

  // Tailles des icônes et texte selon la taille du composant
  const iconSizes = {
    sm: "h-6 w-6",
    md: "h-8 w-8",
    lg: "h-10 w-10"
  };

  // Gérer le changement de fichier
  const handleFileChange = (e) => {
    const file = e.target.files[0];
    if (!file) return;
    
    // Créer une URL temporaire pour l'aperçu
    const objectUrl = URL.createObjectURL(file);
    setPreview(objectUrl);
    
    // Appeler la fonction onChange du parent
    onChange({
      target: {
        name: e.target.name,
        value: file,
        type: "file",
        files: [file]
      }
    });
  };

  // Supprimer la photo
  const removePhoto = () => {
    setPreview(defaultPhotoUrl);
    onChange({
      target: {
        name: "photoUser",
        value: "",
        type: "file"
      }
    });
  };

  return (
    <div className="mb-6">
      <label className="block text-sm font-medium text-gray-700 mb-2 dark:text-white">
        {label} {error && (
          <span className="text-red-500 text-xs font-normal ml-1">{error}</span>
        )}
      </label>
      <div className="mt-4 flex flex-col items-center">
        <div className="relative">
          <img
            src={preview}
            alt="Photo de profil"
            className={`${sizeClasses[size]} rounded-full object-cover border-2 ${
              preview === defaultPhotoUrl && currentPhoto !== defaultPhotoUrl
                ? "border-dashed border-gray-300"
                : "border-solid border-gray-200"
            }`}
          />
          
          {/* Bouton de suppression - seulement visible si une photo est sélectionnée */}
          {preview !== defaultPhotoUrl && (
            <button
              type="button"
              onClick={removePhoto}
              className="absolute -top-2 -right-2 bg-red-500 text-white rounded-full p-1 hover:bg-red-600 transition-colors"
              aria-label="Supprimer la photo"
            >
              <svg xmlns="http://www.w3.org/2000/svg" className="h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M6 18L18 6M6 6l12 12" />
              </svg>
            </button>
          )}
          
          {/* Bouton d'ajout/modification - toujours visible mais différentes positions selon qu'il y a une photo ou non */}
          <label
            htmlFor="photoUser"
            className={`cursor-pointer bg-white dark:bg-gray-700 shadow-md hover:bg-gray-50 dark:hover:bg-gray-600 rounded-full p-2 border border-gray-200 transition-colors ${
              preview === defaultPhotoUrl 
                ? "absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2" 
                : "absolute bottom-0 right-0"
            }`}
          >
            <svg 
              className={`${iconSizes[size]} text-gray-500 dark:text-gray-300`} 
              viewBox="0 0 24 24" 
              fill="none" 
              stroke="currentColor" 
              strokeWidth="2" 
              strokeLinecap="round" 
              strokeLinejoin="round"
            >
              {preview === defaultPhotoUrl ? (
                <path d="M4 16l4.586-4.586a2 2 0 012.828 0L16 16m-2-2l1.586-1.586a2 2 0 012.828 0L20 14m-6-6h.01M8 12h.01M12 12h.01M16 12h.01M20 12h.01" />
              ) : (
                <path d="M3 9l4-4 4 4m-4-4v12m5-8h6a2 2 0 012 2v8a2 2 0 01-2 2H9a2 2 0 01-2-2v-8a2 2 0 012-2h2" />
              )}
            </svg>
          </label>
        </div>
        
        {/* Input caché pour sélection de fichier */}
        <input 
          id="photoUser" 
          name="photoUser" 
          type="file" 
          accept="image/*" 
          className="sr-only" 
          onChange={handleFileChange}
        />
        
        <p className="text-xs text-gray-500 mt-2">Format JPG ou PNG, 5MB max</p>
      </div>
    </div>
  );
}