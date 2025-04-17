'use client';

import { useEffect, useState } from 'react';
import Link from 'next/link';
import { fetchAllSubscriptions } from '@/lib/subscriptions/fetchAllSubscriptions';
import { useTranslations } from 'next-intl';
import SearchBar from '@/components/searchbar';

export default function SubscriptionsPage() {
  const t = useTranslations('Admin.SubscriptionsManagement');
  const [subscriptions, setSubscriptions] = useState<any[]>([]);
  const [filtered, setFiltered] = useState<any[]>([]);
  const [loading, setLoading] = useState(false);
  const [searchTerm, setSearchTerm] = useState('');

  useEffect(() => {
    setLoading(true);
    fetchAllSubscriptions().then((data) => {
      setSubscriptions(data);
      setFiltered(data);
      setLoading(false);
    });
  }, []);

  useEffect(() => {
    const lowerSearch = searchTerm.toLowerCase();
    const result = subscriptions.filter(
      (sub) =>
        sub.name_sub.toLowerCase().includes(lowerSearch) ||
        sub.description_sub.toLowerCase().includes(lowerSearch)
    );
    setFiltered(result);
  }, [searchTerm, subscriptions]);

  return (
    <div className="space-y-6 min-h-screen">
      {/* Title */}
      <div className="text-center max-w-3xl mx-auto">
        <h2 className="text-3xl font-bold text-gray-800 my-5 dark:text-white">
          {t("subscriptionsManagement")}
        </h2>
      </div>

      {/* Séparateur */}
      <div className="w-full h-[2px] bg-gray-300 dark:bg-gray-600"></div>

      {/* SearchBar */}
      <div className="mx-auto">
        <SearchBar
          value={searchTerm}
          onChange={setSearchTerm}
          placeholder={t('searchSubscription')}
        />
      </div>

      {loading ? (
        <p>{t('loading')}</p>
      ) : (
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
          {filtered.length > 0 ? (
            filtered.map((sub) => (
              <div key={sub.id_sub} className="bg-white dark:bg-gray-800 p-4 rounded-lg shadow-md">
                <h3 className="text-lg font-semibold">{sub.name_sub}</h3>
                <p className="text-gray-600 dark:text-gray-300">{sub.description_sub}</p>
                <Link
                  href={`/admin/subscriptions/${sub.id_sub}`}
                  className="mt-4 inline-block px-4 py-2 bg-green-500 text-white rounded-md hover:bg-green-600"
                >
                  {t('seeMore')}
                </Link>
              </div>
            ))
          ) : (
            <p>{t('noSubscriptionFound')}</p>
          )}
        </div>
      )}
    </div>
  );
}