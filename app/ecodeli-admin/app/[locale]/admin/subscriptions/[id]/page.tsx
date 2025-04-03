"use client";

import { useEffect, useState } from "react";
import { useParams } from "next/navigation";
import Link from "next/link";
import { fetchSubscriptionByID } from "@/lib/subscriptions/fetchSubscriptionByID";
import { updateSubscription } from "@/lib/subscriptions/updateSubscription";

export default function SubscriptionDetailPage() {
  const params = useParams();
  const [subscription, setSubscription] = useState<any>(null);
  const [loading, setLoading] = useState(false);
  const [editing, setEditing] = useState(false);
  const [formData, setFormData] = useState({
    name_sub: "",
    description_sub: "",
  });
  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  useEffect(() => {
    if (!params?.id) return;
    setLoading(true);
    fetchSubscriptionByID(params.id as string).then((data) => {
      setSubscription(data);
      setFormData({
        name_sub: data.name_sub,
        description_sub: data.description_sub,
      });
      setLoading(false);
    });
  }, [params?.id]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!params?.id) return;
  
    try {
      const { status, data } = await updateSubscription(params.id as string, formData);
  
      if (status === 200) { // ✅ Maintenant, on vérifie bien le status 200
        setEditing(false);
        setSubscription(data); // ✅ Met à jour les données affichées sur la page
        setSuccessMessage("Modification enregistrée avec succès !");
        setErrorMessage("");
  
        setTimeout(() => setSuccessMessage(""), 3000);
      } else {
        throw new Error("Réponse inattendue de l'API.");
      }
    } catch (error) {
      console.error("Erreur:", error);
      setErrorMessage("Une erreur est survenue. Veuillez réessayer.");
    }
  };  

  const handleCancel = () => {
    setEditing(false);
    setFormData({
      name_sub: subscription?.name_sub || "",
      description_sub: subscription?.description_sub || "",
    });
  };

  return (
    <div className="">
      <h2 className="text-2xl font-semibold mb-4">Détails de l'Abonnement</h2>

      {/* Message de succès */}
      {successMessage && (
        <div className="mb-4 p-4 bg-green-100 border border-green-400 text-green-700 rounded relative">
          {successMessage}
          <button 
            onClick={() => setSuccessMessage("")} 
            className="absolute top-2 right-2 text-green-800 hover:text-green-900"
          >
            ✕
          </button>
        </div>
      )}

      {/* Message d'erreur */}
      {errorMessage && (
        <div className="mb-4 p-4 bg-red-100 border border-red-400 text-red-700 rounded relative">
          {errorMessage}
          <button 
            onClick={() => setErrorMessage("")} 
            className="absolute top-2 right-2 text-red-800 hover:text-red-900"
          >
            ✕
          </button>
        </div>
      )}

      {loading ? (
        <p>Chargement...</p>
      ) : subscription ? (
        <div className="bg-white dark:bg-gray-800 p-4 rounded-lg shadow-md">
          {editing ? (
            <form onSubmit={handleSubmit}>
              <label className="block mb-2">Nom de l'abonnement</label>
              <input 
                type="text" 
                name="name_sub" 
                value={formData.name_sub} 
                onChange={handleChange} 
                className="w-full p-2 mb-4 border rounded" 
              />
              <label className="block mb-2">Description</label>
              <textarea 
                name="description_sub" 
                value={formData.description_sub} 
                onChange={handleChange} 
                className="w-full p-2 mb-4 border rounded"
              />
              <div className="flex gap-4">
                <button 
                  type="submit" 
                  className="px-4 py-2 bg-green-500 text-white rounded-md hover:bg-green-600"
                >
                  Enregistrer
                </button>
                <button 
                  type="button"
                  onClick={handleCancel} 
                  className="px-4 py-2 bg-gray-500 text-white rounded-md hover:bg-gray-600"
                >
                  Annuler
                </button>
              </div>
            </form>
          ) : (
            <>
              <p><strong>Nom:</strong> {subscription.name_sub}</p>
              <p><strong>Description:</strong> {subscription.description_sub}</p>
              <div className="mt-4 flex gap-4">
                <Link 
                  href="/admin/subscriptions" 
                  className="px-4 py-2 bg-gray-500 text-white rounded-md hover:bg-gray-600"
                >
                  Retour
                </Link>
                <button 
                  onClick={() => setEditing(true)} 
                  className="px-4 py-2 bg-green-500 text-white rounded-md hover:bg-green-600"
                >
                  Modifier
                </button>
              </div>
            </>
          )}
        </div>
      ) : (
        <p>Abonnement introuvable.</p>
      )}
    </div>
  );
}