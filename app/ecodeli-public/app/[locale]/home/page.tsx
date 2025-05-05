"use client";
import { useEffect, useState } from "react";
import { useTranslations } from "next-intl";
import Link from "next/link";
import { notFound } from "next/navigation";
import type { Services, Subscriptions } from '@/lib/types';
import SubscriptionDisplay from "@/components/subscriptions"
import { fetchServicesByAuth } from "@/lib/services/fetchServicesByAuth"; 
import { fetchAllSubscriptions } from "@/lib/subscriptions/fetchSubByAudience"; // modif quand bryan add new attribut 

const categories: Record<string, { name: string; icon: string }> = {
  "sp": { name: "Services à la personne", icon: "👩‍🔧" },
  "tl": { name: "Transport & Livraison", icon: "🚚" },
  "tr": { name: "Travaux & Réparations", icon: "🔧" },
  "id": { name: "Informatique & Digital", icon: "💻" },
  "el": { name: "Événementiel & Loisirs", icon: "🎉" },
};

// Données des prestations en tendance
const trendingPrestations = [
  { 
    id: 1, 
    name: "Nettoyage Premium", 
    category: "Services à la personne", 
    rating: 4.9,
    price: "35€/h",
    provider: "CleanExpert",
    reviews: 124,
    image: "/Users/leombelarbi/Desktop/annual_project/app/ecodeli-public/public/deliveryman.jpg"
  },
  { 
    id: 2, 
    name: "Dépannage informatique", 
    category: "Informatique & Digital", 
    rating: 4.8,
    price: "45€/h",
    provider: "TechRescue",
    reviews: 89,
    image: "../../public/deliveryman.jpg"
  },
  { 
    id: 3, 
    name: "Livraison Express", 
    category: "Transport & Livraison", 
    rating: 4.7,
    price: "15€",
    provider: "SpeedyGo",
    reviews: 156,
    image: "/Users/leombelarbi/Desktop/annual_project/app/ecodeli-public/public/deliveryman.jpg"
  },
];

export default function Home() {
  const t = useTranslations("Home");
  const [services, setServices] = useState<Services[]>([]);
  const [subscriptions, setSubscriptions] = useState<Subscriptions[]>([]);
  const [selectedCategory, setSelectedCategory] = useState<string | null>(null);
  const [selectedServices, setSelectedServices] = useState<string | null>(null);
  const filteredServices = selectedCategory ? services.filter(service => service.category === selectedCategory) : [];
  // jusqu'a la modif de bryan 
  const [activeTab, setActiveTab] = useState("part");
  const partSubscriptions = subscriptions.filter(sub => ["Free", "Starter", "Premium"].includes(sub.nameSub));
  const proSubscriptions = subscriptions.filter(sub => ["Pro Basic", "Pro Plus"].includes(sub.nameSub));  
  const displaySubscriptions = activeTab === "part" ? partSubscriptions : proSubscriptions;
  
  useEffect(() => {
    const loadData = async () => {
      try {
        const resSvc = await fetchServicesByAuth(['part', 'pro', 'all']);
        if (resSvc.status !== 200) return notFound();

        const services = resSvc.data.map((service) => {
          const categoryKey = service.category;
          return {
            nameSvc: service.nameSvc,
            category: categories[categoryKey]?.name,
            categoryKey: categoryKey, 
            icon: categories[categoryKey]?.icon,
            auth: service.auth,
          };
        });
        setServices(services);

        const resSub = await fetchAllSubscriptions();
        if (resSub.status !== 200) return notFound();

        const subscriptions = resSub.data.map((subscription) => {
          return {
            nameSub: subscription.nameSub, 
            descriptionSub: subscription.descriptionSub, 
            price: subscription.price, 
            insurance: subscription.insurance, 
            shippingReduction: subscription.shippingReduction, 
            sendPriority: subscription.sendPriority,
            targetAudience: subscription.targetAudience,
            active: subscription.active
          };
        });
        setSubscriptions(subscriptions);
      } catch (error) {
        notFound();
      }
    }; loadData(); }, []);

  return (
    <div className="min-h-screen bg-gradient-to-b from-gray-50 to-gray-100 dark:from-gray-900 dark:to-gray-800">
      
      {/* Hero Section avec appel à l'action */}
      <div className="bg-gradient-to-r from-emerald-500 to-teal-500 text-white">
        <div className="container mx-auto px-4 py-16 flex flex-col md:flex-row items-center justify-between">
          <div className="md:w-3/4 mb-8 md:mb-0">
            <h2 className="text-4xl font-bold mb-4">Trouvez le service qu'il vous faut</h2>
            <p className="text-xl mb-6">La livraison repensée, solidaire et responsable ainsi que des experts qualifiés à votre service pour tous vos besoins quotidiens</p>
            <div className="flex flex-col sm:flex-row gap-4">
              <Link href="home/deliveries" className="bg-white text-emerald-600 px-6 py-3 rounded-lg font-semibold hover:bg-gray-100 transition-colors shadow-lg">
                Nouvelle livraison
              </Link>
              <Link href="home/presta" className="bg-transparent border-2 border-white px-6 py-3 rounded-lg font-semibold hover:bg-white/10 transition-colors">
                Nouvelle prestation
              </Link>
            </div>
          </div>
        </div>
      </div>
      <div className="container mx-auto px-4 py-12">
        {/* Recherche par catgégorie */}
        <div className="bg-white dark:bg-gray-800 rounded-xl shadow-xl p-6 mb-12 -mt-20">
          <h2 className="text-2xl font-semibold mb-4 text-gray-800 dark:text-white">Explorez nos catégories</h2>

          <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-5 gap-6">
            {Object.entries(categories).map(([key, cat]) => (
              <button
                key={key}
                onClick={() => {
                  setSelectedCategory(cat.name);
                  setSelectedServices(null);
                }}
                className={`p-6 rounded-xl flex flex-col items-center text-center transition-all ${
                  selectedCategory === cat.name
                    ? "bg-emerald-500 text-white shadow-lg"
                    : "bg-white dark:bg-gray-800 text-gray-800 dark:text-white hover:shadow-md"
                }`}
              >
                <span className="text-4xl mb-3">{cat.icon}</span>
                <h3 className="font-medium">{cat.name}</h3>
              </button>
            ))}
          </div>

          {selectedCategory && filteredServices.length > 0 && (
            <div className="mt-10">
              <h3 className="text-xl font-semibold text-gray-800 dark:text-white mb-4">
                Services disponibles dans {selectedCategory}
              </h3>
              <div className="flex flex-wrap gap-3">
                {filteredServices.map((service) => (
                  <Link
                    href="home/presta"
                    key={service.nameSvc}
                    onClick={() => setSelectedServices(service.nameSvc)}
                    className={`px-4 py-2 rounded-full transition-colors border ${
                      selectedServices === service.nameSvc
                        ? "bg-emerald-500 text-white"
                        : "bg-white dark:bg-gray-800 text-gray-700 dark:text-gray-200 border-gray-200 dark:border-gray-700 hover:border-emerald-500"
                    }`}
                  >
                    {service.nameSvc}
                  </Link>
                ))}
              </div>
            </div>
          )}
        </div>
        {/* Prestations du moment relier opinion a services et user et afficher les services par user avec le plus d'opinion positif et donc afficher les utilisateurs */}
        <div className="mb-12">
          <div className="relative mb-10">
            {/* <div className="w-[75%] h-1 bg-emerald-500 mx-auto my-5 rounded-full"></div> */}
            <h2 className="text-3xl font-bold text-gray-800 dark:text-white my-5 relative z-10">Découvrez nos services les plus appréciés</h2>
          </div>
          <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
            {trendingPrestations.map((prestation) => (
              <div key={prestation.id} className="bg-white dark:bg-gray-800 rounded-xl shadow-md overflow-hidden hover:shadow-lg transition-shadow">
                <div className="h-40 bg-gray-200 dark:bg-gray-700 overflow-hidden">
                  <img src={prestation.image} alt={prestation.name} className="w-full h-full object-cover" />
                </div>
                <div className="p-6">
                  <div className="flex justify-between items-start mb-2">
                    <h3 className="font-semibold text-lg text-gray-800 dark:text-white">{prestation.name}</h3>
                    <span className="flex items-center bg-amber-100 dark:bg-amber-900 text-amber-600 dark:text-amber-400 px-2 py-1 rounded-md text-sm">
                      ⭐ {prestation.rating.toFixed(1)}
                    </span>
                  </div>
                  <p className="text-gray-500 dark:text-gray-400 text-sm mb-3">{prestation.category} • {prestation.reviews} avis</p>
                  <div className="flex justify-between items-center">
                    <p className="font-bold text-emerald-600 dark:text-emerald-500">{prestation.price}</p>
                    <button className="px-4 py-2 bg-emerald-500 hover:bg-emerald-600 text-white rounded-lg text-sm font-medium transition-colors">
                      Réserver
                    </button>
                  </div>
                </div>
              </div>
            ))}
          </div>
          <div className="mt-8 text-center">
            <button className="px-6 py-3 bg-white dark:bg-gray-800 border border-emerald-500 text-emerald-500 rounded-lg font-medium hover:bg-emerald-50 dark:hover:bg-emerald-900/20 transition-colors">
              Voir toutes les prestations
            </button>
          </div>
        </div>
        
        {/* Abonnements */}

        < SubscriptionDisplay />
        
        <div className="mb-12">
          <div className="relative mb-10">
            <h2 className="text-3xl font-bold text-gray-800 dark:text-white my-5 relative z-10">Choisissez l'abonnement qui correspond à vos besoins et profitez de tous nos services</h2>
          </div>

          {/* test */}
          <div className="flex justify-center mb-8">
            <div className="inline-flex bg-gray-200 dark:bg-gray-700 rounded-lg p-1">
              <button
                onClick={() => setActiveTab("part")}
                className={`px-6 py-2 rounded-lg font-medium text-sm transition-all ${
                  activeTab === "part"
                    ? "bg-white dark:bg-gray-800 text-emerald-600 dark:text-emerald-500 shadow-sm"
                    : "text-gray-600 dark:text-gray-300 hover:text-gray-800 dark:hover:text-white"
                }`}
              >
                Particuliers
              </button>
              <button
                onClick={() => setActiveTab("pro")}
                className={`px-6 py-2 rounded-lg font-medium text-sm transition-all ${
                  activeTab === "pro"
                    ? "bg-white dark:bg-gray-800 text-emerald-600 dark:text-emerald-500 shadow-sm"
                    : "text-gray-600 dark:text-gray-300 hover:text-gray-800 dark:hover:text-white"
                }`}
              >
                Professionnels
              </button>
            </div>
          </div>

          {/* test : Affichage des abonnements */}
          <div className="grid grid-cols-1 lg:grid-cols-3 gap-6 mt-8">
            {displaySubscriptions.map((subscription) => (
              <div
                key={subscription.nameSub}
                className={`relative bg-white dark:bg-gray-800 rounded-xl shadow-md hover:shadow-lg transition-shadow p-6 border-2 ${
                  (activeTab === "part" && subscription.nameSub === "Premium") || 
                  (activeTab === "pro" && subscription.nameSub === "Pro Plus")
                    ? "border-emerald-500"
                    : "border-transparent"
                }`}
              >
                
                <h3 className="text-xl font-bold text-gray-800 dark:text-white mb-2">{subscription.nameSub}</h3>
                <p className="text-gray-600 dark:text-gray-300 text-sm mb-4">{subscription.descriptionSub}</p>
                
                <div className="mb-6">
                  <span className="text-3xl font-bold text-emerald-600 dark:text-emerald-500">
                    {subscription.price > 0 ? `${subscription.price.toFixed(2)}€` : "Gratuit"}
                  </span>
                  <span className="text-gray-500 dark:text-gray-400 text-sm ml-1">
                    {subscription.price > 0 ? "/mois" : ""}
                  </span>
                </div>
                
                {/* 
                // PAS OPTIMAL !!! Créer feature apres avoir recupe les données comme pour categories 
                // const features: string[] = [];
                // if (subscription.shippingReduction > 0) features.push(`${subscription.shippingReduction}% de réduction sur l'envoi de colis`);
                // if (subscription.permanentReduction > 0) features.push(`${subscription.permanentReduction}% de réduction permanente`);
                // if (subscription.insurance > 0) {
                //   features.push(`Assurance jusqu'à ${subscription.insurance.toLocaleString('fr-FR')}€`);
                // } else {
                //   features.push("Sans assurance");
                // }
                // if (subscription.sendPriority > 0) {
                //   features.push(`${subscription.sendPriority} envois prioritaires par mois`);
                // } else if (subscription.nameSub.includes("Plus")) {
                //   features.push("Envois prioritaires illimités");
                // }
                */}
                <ul className="space-y-3 mb-8"> 
                    <li className="flex items-start text-sm">
                      <span className="text-emerald-500 mr-2">✓</span>
                      <span className="text-gray-600 dark:text-gray-300">{subscription.insurance}</span>
                    </li>
                    <li className="flex items-start text-sm">
                      <span className="text-emerald-500 mr-2">✓</span>
                      <span className="text-gray-600 dark:text-gray-300">{subscription.shippingReduction}</span>
                    </li>
                    <li className="flex items-start text-sm">
                      <span className="text-emerald-500 mr-2">✓</span>
                      <span className="text-gray-600 dark:text-gray-300">{subscription.permanentReduction}</span>
                    </li>
                    <li className="flex items-start text-sm">
                      <span className="text-emerald-500 mr-2">✓</span>
                      <span className="text-gray-600 dark:text-gray-300">{subscription.sendPriority}</span>
                    </li>
                </ul>
                
                <button className={`w-full py-3 rounded-lg font-semibold transition-colors ${
                  (activeTab === "part" && subscription.nameSub === "Premium") || 
                  (activeTab === "pro" && subscription.nameSub === "Pro Plus")
                    ? "bg-emerald-500 hover:bg-emerald-600 text-white"
                    : "bg-gray-100 dark:bg-gray-700 hover:bg-gray-200 dark:hover:bg-gray-600 text-gray-800 dark:text-white"
                }`}>
                  {subscription.price > 0 ? "Souscrire maintenant" : "Commencer gratuitement"}
                </button>
              </div>
            ))}
          </div>
        </div>
        <p className="mt-2">Des questions ? <a href="#" className="text-emerald-500 hover:underline">Contactez notre équipe</a></p>
      </div>
    </div>
  );
}
