'use client';

import { useEffect, useState } from 'react';
import { useSearchParams, useRouter, usePathname } from 'next/navigation';
import Link from 'next/link';
import { fetchAllServices } from '@/lib/services/fetchAllServices';
import { useTranslations } from 'next-intl';
import SearchBar from '@/components/searchbar';

const serviceCategories = [
  { label: "allServices", value: "" },
  { label: "personalServices", value: "sp" },
  { label: "transportDelivery", value: "tl" },
  { label: "constructionReparation", value: "tr" },
  { label: "itDigital", value: "id" },
  { label: "eventsActivities", value: "el" },
];

export default function ServicesPage() {
  const t = useTranslations('Admin.ServicesManagement');
  const searchParams = useSearchParams();
  const router = useRouter();
  const pathname = usePathname();

  const [services, setServices] = useState<any[]>([]);
  const [filtered, setFiltered] = useState<any[]>([]);
  const [loading, setLoading] = useState(false);
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedCategory, setSelectedCategory] = useState<string | null>(searchParams.get("category"));

  useEffect(() => {
    setLoading(true);
    fetchAllServices().then((data) => {
      setServices(data);
      setFiltered(data);
      setLoading(false);
    });
  }, []);

  useEffect(() => {
    const lowerSearch = searchTerm.toLowerCase();
    let result = services;

    if (selectedCategory) {
      result = result.filter(
        (service) => service.category.toLowerCase() === selectedCategory.toLowerCase()
      );
    }

    result = result.filter(
      (service) =>
        service.name_svc.toLowerCase().includes(lowerSearch) ||
        service.category.toLowerCase().includes(lowerSearch)
    );

    setFiltered(result);
  }, [searchTerm, services, selectedCategory]);

  const handleFilterChange = (value: string) => {
    setSelectedCategory(value);
    if (value === "") {
      // Si "allServices" est sélectionné, on enlève le paramètre "category"
      router.replace(pathname, { scroll: false });
    } else {
      // Applique le filtre "category"
      router.replace(`${pathname}?category=${value}`, { scroll: false });
    }
  };

  return (
    <div className="space-y-6 min-h-screen">
      {/* Title */}
      <div className="text-center max-w-3xl mx-auto">
        <h2 className="text-3xl font-bold text-gray-800 my-5 dark:text-white">
          {t("servicesManagement")}
        </h2>
      </div>

      {/* Séparateur */}
      <div className="w-full h-[2px] bg-gray-300 dark:bg-gray-600"></div>

      {/* Filtres par catégorie */}
      <div className="flex flex-wrap gap-6 justify-center my-6">
        {serviceCategories.map(({ label, value }) => (
          <button
            key={value}
            onClick={() => handleFilterChange(value)}
            className={`cursor-pointer text-sm font-medium transition-all duration-300
              ${selectedCategory === value
                ? "text-green-600 font-semibold border-b-2 border-green-600"
                : "text-gray-700 dark:text-gray-300 hover:text-green-500 hover:border-b-2 hover:border-green-500"
              }
            `}
          >
            {t(label)}
          </button>
        ))}
      </div>

      {/* SearchBar */}
      <div className="mx-auto">
        <SearchBar
          value={searchTerm}
          onChange={setSearchTerm}
          placeholder={t('searchService')}
        />
      </div>

      {loading ? (
        <p>{t('loading')}</p>
      ) : (
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4">
          {filtered.length > 0 ? (
            filtered.map((service) => (
              <div key={service.id_svc} className="bg-white dark:bg-gray-800 p-4 rounded-lg shadow-md">
                <h3 className="text-lg font-semibold">{service.name_svc}</h3>
                <p className="text-gray-600 dark:text-gray-300">{service.category}</p>
                <Link
                  href={`/admin/services/${service.id_svc}`}
                  className="mt-4 inline-block px-4 py-2 bg-green-500 text-white rounded-md hover:bg-green-600"
                >
                  {t('seeMore')}
                </Link>
              </div>
            ))
          ) : (
            <p>{t('noServiceFound')}</p>
          )}
        </div>
      )}
    </div>
  );
}