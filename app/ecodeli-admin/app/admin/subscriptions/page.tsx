"use client";

import { useEffect, useState } from "react";
import Link from "next/link";
import { fetchAllSubscriptions } from "@/lib/subscriptions/fetchAllSubscriptions";

export default function SubscriptionsPage() {
  const [subscriptions, setSubscriptions] = useState<any[]>([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);
    fetchAllSubscriptions().then((data) => {
      setSubscriptions(data);
      setLoading(false);
    });
  }, []);

  return (
    <div className="">
      <h2 className="text-2xl font-semibold mb-4">Gestion des Abonnements</h2>
      {loading ? (
        <p>Chargement...</p>
      ) : (
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
          {subscriptions.map((sub) => (
            <div key={sub.id_sub} className="bg-white dark:bg-gray-800 p-4 rounded-lg shadow-md">
              <h3 className="text-lg font-semibold">{sub.name_sub}</h3>
              <p className="text-gray-600 dark:text-gray-300">{sub.description_sub}</p>
              <Link 
                href={`/admin/subscriptions/${sub.id_sub}`} 
                className="mt-4 inline-block px-4 py-2 bg-green-500 text-white rounded-md hover:bg-green-600"
              >
                Voir Détails
              </Link>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}