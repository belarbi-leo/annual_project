import { useState } from "react";
import { useTranslations } from "next-intl";
import Error from "./error";
import { UserCircleIcon } from "@heroicons/react/24/solid";

interface PhotoUploadProps {
  currentPhoto: string | File | null; // URL de la photo actuelle, objet File, ou null/vide
  onChange: (event: { target: { name: string; value: File | string; type: string; files?: File[] } }) => void; // Fonction appelée lors du changement de photo
  useDefaultIcon?: boolean; // Utiliser une icône par défaut au lieu d'une image
  defaultPhotoUrl?: string; // URL de la photo par défaut si useDefaultIcon=false (optionnel)
  error?: string; // Message d'erreur (optionnel)
  label?: string; // Texte du label (optionnel)
  size?: "sm" | "md" | "lg"; // Taille du composant ("sm", "md", "lg") (optionnel, par défaut "md")
  readOnly?: boolean; // Mode lecture seule, désactive les boutons d'édition (optionnel)
  className?: string; // Classes CSS additionnelles (optionnel)
}

export default function PhotoUpload({
  currentPhoto,
  onChange,
  useDefaultIcon = true,
  defaultPhotoUrl = "/default-user.jpg",
  error,
  label,
  size = "md",
  readOnly = false,
  className = ""
}: PhotoUploadProps) {
  const t = useTranslations('Signin');
  const hasPhoto = currentPhoto !== null && currentPhoto !== undefined && currentPhoto !== "";
  
  const [previewUrl, setPreviewUrl] = useState(() => {
    if (currentPhoto instanceof File) {
      return URL.createObjectURL(currentPhoto);
    } else if (hasPhoto) {
      return currentPhoto;
    } else {
      return useDefaultIcon ? null : defaultPhotoUrl;
    }
  });

  const sizeClasses = { sm: "w-20 h-20", md: "w-32 h-32", lg: "w-40 h-40" };
  const defaultIconSizes = { sm: "w-20 h-20", md: "w-32 h-32", lg: "w-40 h-40" };

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (readOnly) return;
    const file = e.target.files?.[0];
    if (!file) return;
    // Libérer l'URL précédente si nécessaire
    if (previewUrl && previewUrl.startsWith('blob:')) URL.revokeObjectURL(previewUrl);
    // Créer une URL temporaire pour l'aperçu
    const objectUrl = URL.createObjectURL(file);
    setPreviewUrl(objectUrl);
    onChange?.({ target: { name: e.target.name, value: file, type: "file", files: [file] }});
  };

  const remove = () => {
    if (readOnly) return;
    // Libérer l'URL précédente si nécessaire
    if (previewUrl && previewUrl.startsWith('blob:')) URL.revokeObjectURL(previewUrl);
    setPreviewUrl(useDefaultIcon ? null : defaultPhotoUrl);
    onChange?.({ target: { name: "photoUser", value: "", type: "file" }});
  };

  return (
    <div className={`mb-6 ${className}`}>
      {label && (
        <label className="block text-sm font-medium text-gray-700 mb-2 dark:text-white">
          {label} {error && <Error message={error} />}
        </label>
      )}
      <div className="mt-2 flex flex-col items-center">
        <div className="relative">
          <label htmlFor="photoUser" className="cursor-pointer">
            {previewUrl ? (
              <img src={previewUrl} alt={`${t("detailsInfo.photoLabel")}`} className={`${sizeClasses[size]} flex m-auto rounded-full object-cover border-2 border-solid border-gray-200`}/>
            ) : (
              <div className={`${defaultIconSizes[size]} flex m-auto items-center justify-center text-gray-400 bg-gray-100 dark:bg-gray-700 rounded-full`}>
                <UserCircleIcon className={sizeClasses[size]} />
              </div>
            )}
          </label>
          <input id="photoUser" name="photoUser" type="file" accept="image/*" className="sr-only" onChange={handleFileChange}/>
          
          {!readOnly && (
            <>
              {previewUrl && (
                <button type="button" onClick={remove} className="absolute -top-2 -right-2 bg-red-500 text-white rounded-full p-1 hover:bg-red-600 transition-colors">
                  <svg xmlns="http://www.w3.org/2000/svg" className="h-4 w-4" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M6 18L18 6M6 6l12 12" />
                  </svg>
                </button>
              )}
              <p className="text-xs text-gray-500 mt-2">{t("detailsInfo.photoRequirements")}</p>
            </>
          )}
        </div>
      </div>
    </div>
  );
}