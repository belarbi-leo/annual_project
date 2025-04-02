"use client";

import { useState } from "react";
import { TruckIcon, ClipboardDocumentCheckIcon } from "@heroicons/react/24/outline";

export default function Home() {
  const [livraisons, setLivraisons] = useState([
    { id: 1, status: "En cours", client: "Jean Dupont", date: "31/03/2025" },
    { id: 2, status: "Livré", client: "Sophie Martin", date: "30/03/2025" },
  ]);

  const [prestations, setPrestations] = useState([
    { id: 1, type: "Nettoyage", status: "Confirmé" },
    { id: 2, type: "Réparation vélo", status: "En attente" },
  ]);

  return (
    
    <div className="space-y-8 p-6 bg-gray-50 dark:bg-gray-900 min-h-screen">
      {/* Header */}
      <div className="mx-auto">
        <h2 className="text-3xl font-bold text-gray-800 dark:text-white mt-5">Tableau de bord</h2>
        
        <div className="p-6 mt">
          {/* Actions rapides */}
          <div className="grid grid-cols-2 gap-4 mb-6">
            <button className="bg-blue-500 text-white p-4 rounded-lg flex items-center justify-center">📦 Nouvelle livraison</button>
            <button className="bg-green-500 text-white p-4 rounded-lg flex items-center justify-center">🛠️ Nouvelle prestation</button>
          </div>

          {/* Cartes de statistiques */}
          <div className="grid grid-cols-2 gap-4 mb-6">
            <div className="bg-white p-4 shadow rounded-lg flex items-center">
              <TruckIcon className="h-10 w-10 text-blue-500" />
              <div className="ml-4">
                <p className="text-lg font-semibold">{livraisons.length}</p>
                <p className="text-gray-500">Livraisons en cours</p>
              </div>
            </div>
            <div className="bg-white p-4 shadow rounded-lg flex items-center">
              <ClipboardDocumentCheckIcon className="h-10 w-10 text-green-500" />
              <div className="ml-4">
                <p className="text-lg font-semibold">{prestations.length}</p>
                <p className="text-gray-500">Prestations actives</p>
              </div>
            </div>
          </div>

          {/* Liste des livraisons */}
          <div className="bg-white p-4 shadow rounded-lg mb-6">
            <h2 className="text-lg font-semibold mb-3">📦 Livraisons récentes</h2>
            <ul>
              {livraisons.map((livraison) => (
                <li key={livraison.id} className="py-2 border-b last:border-none">
                  <p className="text-gray-700 font-medium">{livraison.client}</p>
                  <p className="text-sm text-gray-500">
                    {livraison.status} - {livraison.date}
                  </p>
                </li>
              ))}
            </ul>
          </div>

          {/* Liste des prestations */}
          <div className="bg-white p-4 shadow rounded-lg">
            <h2 className="text-lg font-semibold mb-3">🛠️ Prestations à venir</h2>
            <ul>
              {prestations.map((prestation) => (
                <li key={prestation.id} className="py-2 border-b last:border-none">
                  <p className="text-gray-700 font-medium">{prestation.type}</p>
                  <p className="text-sm text-gray-500">{prestation.status}</p>
                </li>
              ))}
            </ul>
          </div>
        </div>
      </div>
    </div>
  );
}

// "use client";

// import { useState } from "react";
// import { TruckIcon, ClipboardDocumentCheckIcon, StarIcon } from "@heroicons/react/24/outline";

// export default function Home() {
//   const [livraisons, setLivraisons] = useState([
//     { id: 1, status: "En cours", client: "Jean Dupont", date: "31/03/2025" },
//     { id: 2, status: "Livré", client: "Sophie Martin", date: "30/03/2025" },
//   ]);

//   const [prestations, setPrestations] = useState([
//     { id: 1, type: "Nettoyage", status: "Confirmé" },
//     { id: 2, type: "Réparation vélo", status: "En attente" },
//   ]);

//   const [topPrestations, setTopPrestations] = useState([
//     { id: 1, category: "Nettoyage", name: "Nettoyage complet", rating: 4.8 },
//     { id: 2, category: "Réparation", name: "Réparation de vélo", rating: 4.6 },
//     { id: 3, category: "Entretien", name: "Jardinage", rating: 4.5 },
//   ]);

//   return (
//     <div className="min-h-screen bg-gray-100 p-6">
//       {/* Header */}
//       <div className="bg-white shadow p-4 rounded-lg">
//         <h1 className="text-2xl font-semibold text-gray-900">Tableau de bord</h1>
//       </div>

//       <div className="p-6">
//         {/* Actions rapides */}
//         <div className="grid grid-cols-2 gap-4 mb-6">
//           <button className="bg-blue-500 text-white p-4 rounded-lg flex items-center justify-center">
//             📦 Nouvelle livraison
//           </button>
//           <button className="bg-green-500 text-white p-4 rounded-lg flex items-center justify-center">
//             🛠️ Nouvelle prestation
//           </button>
//         </div>

//         {/* Cartes de statistiques */}
//         <div className="grid grid-cols-2 gap-4 mb-6">
//           <div className="bg-white p-4 shadow rounded-lg flex items-center">
//             <TruckIcon className="h-10 w-10 text-blue-500" />
//             <div className="ml-4">
//               <p className="text-lg font-semibold">{livraisons.length}</p>
//               <p className="text-gray-500">Livraisons en cours</p>
//             </div>
//           </div>
//           <div className="bg-white p-4 shadow rounded-lg flex items-center">
//             <ClipboardDocumentCheckIcon className="h-10 w-10 text-green-500" />
//             <div className="ml-4">
//               <p className="text-lg font-semibold">{prestations.length}</p>
//               <p className="text-gray-500">Prestations actives</p>
//             </div>
//           </div>
//         </div>

//         {/* Liste des livraisons */}
//         <div className="bg-white p-4 shadow rounded-lg mb-6">
//           <h2 className="text-lg font-semibold mb-3">📦 Livraisons récentes</h2>
//           <ul>
//             {livraisons.map((livraison) => (
//               <li key={livraison.id} className="py-2 border-b last:border-none">
//                 <p className="text-gray-700 font-medium">{livraison.client}</p>
//                 <p className="text-sm text-gray-500">
//                   {livraison.status} - {livraison.date}
//                 </p>
//               </li>
//             ))}
//           </ul>
//         </div>

//         {/* Liste des prestations */}
//         <div className="bg-white p-4 shadow rounded-lg mb-6">
//           <h2 className="text-lg font-semibold mb-3">🛠️ Prestations à venir</h2>
//           <ul>
//             {prestations.map((prestation) => (
//               <li key={prestation.id} className="py-2 border-b last:border-none">
//                 <p className="text-gray-700 font-medium">{prestation.type}</p>
//                 <p className="text-sm text-gray-500">{prestation.status}</p>
//               </li>
//             ))}
//           </ul>
//         </div>

//         {/* Ligne de séparation */}
//         <hr className="my-6 border-t border-gray-300 dark:border-gray-600" />

//         {/* Top Prestations par notation */}
//         <div className="bg-white p-4 shadow rounded-lg">
//           <h2 className="text-lg font-semibold mb-3">⭐ Top Prestations</h2>
//           <ul>
//             {topPrestations.map((prestation) => (
//               <li key={prestation.id} className="py-2 border-b last:border-none flex justify-between">
//                 <div>
//                   <p className="text-gray-700 font-medium">{prestation.name}</p>
//                   <p className="text-sm text-gray-500">{prestation.category}</p>
//                 </div>
//                 <div className="flex items-center">
//                   <p className="text-lg font-semibold text-yellow-500">{prestation.rating.toFixed(1)}</p>
//                   <StarIcon className="h-5 w-5 text-yellow-500 ml-1" />
//                 </div>
//               </li>
//             ))}
//           </ul>
//         </div>

//       </div>
//     </div>
//   );
// }
