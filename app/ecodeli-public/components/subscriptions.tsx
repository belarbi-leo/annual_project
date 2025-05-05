"use client";
import { useState, useEffect } from "react";
import { notFound } from "next/navigation";
import type { Subscriptions } from '@/lib/types';
import { fetchAllSubscriptions } from "@/lib/subscriptions/fetchSubByAudience"; 

export default function SubscriptionDisplay() {
  const [subscriptions, setSubscriptions] = useState<Subscriptions[]>([]);
  const [activeTab, setActiveTab] = useState("particuliers");

  // Filtrer les abonnements selon le public cible et l'état actif
  const filteredSubscriptions = subscriptions
    .filter(sub => sub.targetAudience?.toLowerCase() === activeTab);

  useEffect(() => {
    const fetchSubscriptions = async () => {
      try {
        const res = await fetchAllSubscriptions();
        if (res.status !== 200) notFound();
        setSubscriptions(res.data); // Assurez-vous que res.data contient les données
      } catch (error) {
        notFound();
      }
    };
    
    fetchSubscriptions();
  }, []);

  // Fonction pour générer les caractéristiques à partir des données d'abonnement
  const generateFeatures = (subscription: Subscriptions) => {
    const features: string[] = [];
    
    if (subscription.shippingReduction > 0) {
      features.push(`${subscription.shippingReduction}% de réduction sur l'envoi de colis`);
    }
    
    if (subscription.permanentReduction > 0) {
      features.push(`${subscription.permanentReduction}% de réduction permanente`);
    }
    
    if (subscription.insurance > 0) {
      features.push(`Assurance jusqu'à ${subscription.insurance.toLocaleString('fr-FR')}€`);
    } else {
      features.push("Sans assurance");
    }
    
    if (subscription.sendPriority > 0) {
      features.push(`${subscription.sendPriority} envois prioritaires par mois`);
    } else if (subscription.nameSub.includes("Plus")) {
      features.push("Envois prioritaires illimités");
    }
    
    return features;
  };

  // Détermine les types d'audiences disponibles pour l'UI
  const audienceTypes = [...new Set(
    subscriptions
      .map(sub => sub.targetAudience)
  )];

  // Détermine les abonnements mis en avant (Premium pour particuliers, Pro Plus pour pros)
  const isHighlighted = (subscription: Subscriptions) => {
    return (
      (activeTab === "particuliers" && subscription.nameSub === "Premium") ||
      (activeTab === "professionnels" && subscription.nameSub === "Pro Plus")
    );
  };

  return (
    <div className="mb-12">
      <div className="relative mb-10">
        <h2 className="text-3xl font-bold text-gray-800 dark:text-white my-5 relative z-10">
          Choisissez l'abonnement qui correspond à vos besoins et profitez de tous nos services
        </h2>
      </div>

      {/* Sélecteur d'audience */}
      {audienceTypes.length > 1 && (
        <div className="flex justify-center mb-8">
          <div className="inline-flex bg-gray-200 dark:bg-gray-700 rounded-lg p-1">
            {audienceTypes.map((audience) => (
              audience && (
                <button
                  key={audience}
                  onClick={() => setActiveTab(audience.toLowerCase())}
                  className={`px-6 py-2 rounded-lg font-medium text-sm transition-all ${
                    activeTab === audience.toLowerCase()
                      ? "bg-white dark:bg-gray-800 text-emerald-600 dark:text-emerald-500 shadow-sm"
                      : "text-gray-600 dark:text-gray-300 hover:text-gray-800 dark:hover:text-white"
                  }`}
                >
                  {audience.charAt(0).toUpperCase() + audience.slice(1)}
                </button>
              )
            ))}
          </div>
        </div>
      )}

      {/* Affichage des abonnements */}
      {filteredSubscriptions.length > 0 ? (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mt-8">
          {filteredSubscriptions.map((subscription) => {
            const features = generateFeatures(subscription);
            
            return (
              <div
                key={subscription.id}
                className={`relative bg-white dark:bg-gray-800 rounded-xl shadow-md hover:shadow-lg transition-shadow p-6 border-2 ${
                  isHighlighted(subscription)
                    ? "border-emerald-500"
                    : "border-transparent"
                }`}
              >
                {isHighlighted(subscription) && (
                  <div className="absolute -top-3 left-1/2 transform -translate-x-1/2 bg-emerald-500 text-white text-xs font-bold py-1 px-4 rounded-full">
                    Recommandé
                  </div>
                )}
                
                <h3 className="text-xl font-bold text-gray-800 dark:text-white mb-2">
                  {subscription.nameSub}
                </h3>
                
                <p className="text-gray-600 dark:text-gray-300 text-sm mb-4">
                  {subscription.descriptionSub}
                </p>
                
                <div className="mb-6">
                  <span className="text-3xl font-bold text-emerald-600 dark:text-emerald-500">
                    {subscription.price > 0 ? `${subscription.price.toFixed(2)}€` : "Gratuit"}
                  </span>
                  <span className="text-gray-500 dark:text-gray-400 text-sm ml-1">
                    {subscription.price > 0 ? "/mois" : ""}
                  </span>
                </div>
              
                <ul className="space-y-3 mb-8">
                  {features.map((feature, index) => (
                    <li key={index} className="flex items-start text-sm">
                      <span className="text-emerald-500 mr-2">✓</span>
                      <span className="text-gray-600 dark:text-gray-300">{feature}</span>
                    </li>
                  ))}
                </ul>
                
                <button 
                  className={`w-full py-3 rounded-lg font-semibold transition-colors ${
                    isHighlighted(subscription)
                      ? "bg-emerald-500 hover:bg-emerald-600 text-white"
                      : "bg-gray-100 dark:bg-gray-700 hover:bg-gray-200 dark:hover:bg-gray-600 text-gray-800 dark:text-white"
                  }`}
                >
                  {subscription.price > 0 ? "Souscrire maintenant" : "Commencer gratuitement"}
                </button>
              </div>
            );
          })}
        </div>
      ) : (
        <div className="text-center py-12 text-gray-500 dark:text-gray-400">
          Aucun abonnement disponible pour ce type de public actuellement.
        </div>
      )}
    </div>
  );
}

// "use client";
// import { useState, useEffect } from "react";
// import { notFound } from "next/navigation";
// import type { Subscriptions } from '@/lib/types';
// import { fetchAllSubscriptions } from "@/lib/subscriptions/fetchSubByAudience"; 

// export default function SubscriptionDisplay() {
//   const [subscriptions, setSubscriptions] = useState<Subscriptions[]>([]);
//   const [activeTab, setActiveTab] = useState("particuliers");
//   const [isLoading, setIsLoading] = useState(true);

//   // Filtrer les abonnements selon le public cible et l'état actif
//   const filteredSubscriptions = subscriptions
//     .filter(sub => sub.isActive)
//     .filter(sub => sub.targetAudience?.toLowerCase() === activeTab);

//   useEffect(() => {
//     const fetchSubscriptions = async () => {
//       setIsLoading(true);
//       try {
//         const res = await fetchAllSubscriptions();
//         if (res.status !== 200) notFound();
//         setSubscriptions(res.data); // Assurez-vous que res.data contient les données
//       } catch (error) {
//         console.error("Erreur lors du chargement des abonnements:", error);
//         notFound();
//       } finally {
//         setIsLoading(false);
//       }
//     };
    
//     fetchSubscriptions();
//   }, []);

//   // Fonction pour générer les caractéristiques à partir des données d'abonnement
//   const generateFeatures = (subscription: Subscriptions) => {
//     const features: string[] = [];
    
//     if (subscription.shippingReduction > 0) {
//       features.push(`${subscription.shippingReduction}% de réduction sur l'envoi de colis`);
//     }
    
//     if (subscription.permanentReduction > 0) {
//       features.push(`${subscription.permanentReduction}% de réduction permanente`);
//     }
    
//     if (subscription.insurance > 0) {
//       features.push(`Assurance jusqu'à ${subscription.insurance.toLocaleString('fr-FR')}€`);
//     } else {
//       features.push("Sans assurance");
//     }
    
//     if (subscription.sendPriority > 0) {
//       features.push(`${subscription.sendPriority} envois prioritaires par mois`);
//     } else if (subscription.nameSub.includes("Plus")) {
//       features.push("Envois prioritaires illimités");
//     }
    
//     return features;
//   };

//   // Détermine les types d'audiences disponibles pour l'UI
//   const audienceTypes = [...new Set(
//     subscriptions
//       .filter(sub => sub.isActive)
//       .map(sub => sub.targetAudience)
//   )];

//   if (isLoading) {
//     return (
//       <div className="flex justify-center items-center min-h-40">
//         <div className="animate-spin rounded-full h-12 w-12 border-t-2 border-b-2 border-emerald-500"></div>
//       </div>
//     );
//   }

//   return (
//     <div className="mb-12">
//       <div className="relative mb-10">
//         <h2 className="text-3xl font-bold text-gray-800 dark:text-white my-5 relative z-10">
//           Choisissez l'abonnement qui correspond à vos besoins et profitez de tous nos services
//         </h2>
//       </div>

//       {/* Sélecteur d'audience */}
//       {audienceTypes.length > 1 && (
//         <div className="flex justify-center mb-8">
//           <div className="inline-flex bg-gray-200 dark:bg-gray-700 round