import { useState } from 'react';

export default function SearchService({ useImpactTitle = false, t = (key) => key }) {
  const [searchQuery, setSearchQuery] = useState('');
  const [locationQuery, setLocationQuery] = useState('');
  
  const handleSearch = () => {
    // Ici, vous pouvez implémenter la logique de recherche
    console.log('Recherche:', searchQuery, 'Lieu:', locationQuery);
  };
  
  return (
    <div className="container mx-auto px-4 py-8">
      <div className="bg-white dark:bg-gray-800 rounded-xl shadow-xl p-6 mb-8 max-w-5xl mx-auto">
        <h3 className="text-xl font-semibold mb-4 text-gray-800 dark:text-white">Rechercher un service</h3>
        {useImpactTitle && (
            <div className="bg-emerald-50 p-2 rounded-md mb-5">
                <p className="text-xs text-emerald-700 font-medium italic">{t("impact")}</p>
            </div>
        )}
        <div className="flex flex-col md:flex-row gap-4">
          <div className="flex-1">
            <label className="block text-sm font-medium text-gray-600 dark:text-gray-300 mb-1">Service</label>
            <input
              type="text"
              placeholder="Que recherchez-vous ?"
              className="w-full px-4 py-3 rounded-lg bg-gray-50 dark:bg-gray-700 border border-gray-200 dark:border-gray-600 text-gray-900 dark:text-white focus:outline-none focus:ring-2 focus:ring-emerald-500"
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
            />
          </div>
          <div className="flex-1">
            <label className="block text-sm font-medium text-gray-600 dark:text-gray-300 mb-1">Localisation</label>
            <input
              type="text"
              placeholder="Où ?"
              className="w-full px-4 py-3 rounded-lg bg-gray-50 dark:bg-gray-700 border border-gray-200 dark:border-gray-600 text-gray-900 dark:text-white focus:outline-none focus:ring-2 focus:ring-emerald-500"
              value={locationQuery}
              onChange={(e) => setLocationQuery(e.target.value)}
            />
          </div>
          <div className="md:self-end">
            <button 
              className="w-full px-6 py-3 bg-emerald-500 hover:bg-emerald-600 text-white rounded-lg font-semibold shadow-md transition-colors"
              onClick={handleSearch}
            >
              Rechercher
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}