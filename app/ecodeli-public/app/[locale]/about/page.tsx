"use client";

import { useState } from "react";
import { useTranslations } from "next-intl";
import Image from "next/image";
import Link from "next/link";
import Header from "@/components/header";
import Background from "@/components/background";
import SearchBar from "@/components/search-services";
import { ArrowPathIcon, CloudArrowUpIcon, FingerPrintIcon, LockClosedIcon } from "@heroicons/react/24/outline";

export default function About() {
  const t = useTranslations("About");
  const [searchQuery, setSearchQuery] = useState("");
  const [locationQuery, setLocationQuery] = useState("");

  const features = [
    {
      name: t("features.trust.name"),
      description: t("features.trust.description"),
      icon: LockClosedIcon,
    },
    {
      name: t("features.optimized.name"),
      description: t("features.optimized.description"),
      icon: CloudArrowUpIcon,
    },
    {
      name: t("features.services.name"),
      description: t("features.services.description"),
      icon: ArrowPathIcon,
    },
    {
      name: t("features.environmental.name"),
      description: t("features.environmental.description"),
      icon: FingerPrintIcon,
    },
  ];

  return (
    <main className="px-6 py-24 sm:py-32 lg:px-0">
      <div className="mx-auto max-w-7xl px-4 md:px-6 lg:px-8">
        <div className="mx-auto max-w-4xl text-center">
          <h1 className="text-4xl md:text-5xl font-bold tracking-tight text-emerald-500 dark:text-emerald-300 mt-10">{t("title")}</h1>
          <p className="mt-10 text-lg md:text-xl text-gray-600 dark:text-gray-300">{t("intro")}</p>
        </div>
      </div>
      <div className="py-5 sm:py-10">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="md:mt-10">
            <div className="grid grid-cols-1 gap-8 sm:grid-cols-2 lg:grid-cols-2">
              {features.map((feature) => (
                <div key={feature.name} className="pt-6">
                  <div className="flow-root rounded-lg bg-emerald-50 px-6 pb-8 h-full hover:shadow-lg transition-all">
                    <div className="-mt-6">
                      <div>
                        <span className="inline-flex items-center justify-center rounded-md bg-[#49cb5c] p-3 shadow-lg">
                          <feature.icon className="h-6 w-6 text-white" aria-hidden="true" />
                        </span>
                      </div>
                      <h3 className="mt-8 text-lg font-semibold leading-8 tracking-tight text-gray-900 text-center md:text-left">
                        {feature.name}
                      </h3>
                      <p className="mt-5 text-base leading-7 text-gray-600 text-center md:text-left">
                        {feature.description}
                      </p>
                    </div>
                  </div>
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>
      <div className="py-10">
        <div className="max-w-7xl mx-auto sm:px-4 lg:px-8">
          <div className="text-center">
            <p className="mt-10 max-w-4xl mx-auto text-lg text-gray-600 dark:text-white">{t("connectionTitle")}</p>
            <p className="mt-5 max-w-4xl mx-auto text-lg text-gray-600 dark:text-white">{t("connectionText")}</p>
          </div>

          <div className="mt-25 sm:md-20 grid grid-cols-1 gap-6 sm:grid-cols-3">
            {[1, 2, 3].map((value) => (
              <div key={value} className="bg-emerald-50 rounded-lg shadow-sm p-6 hover:shadow-lg transition-all">
                <h3 className="text-lg font-semibold text-center text-gray-900">{t(`values.${value}.title`)}</h3>
                <p className="mt-4 text-center text-gray-600">{t(`values.${value}.text`)}</p>
              </div>
            ))}
          </div>
        </div>
      </div>
      <SearchBar useImpactTitle={true} t={t} />
      <div className="max-w-7xl mx-auto sm:px-4 lg:px-8">
        <div className="flex justify-center">
          <Image src="/favicon.ico" alt="Logo EcoDeli" height={240} width={240} className="h-22 w-22"/>
        </div>
        <h2 className="mt-15 text-3xl font-bold text-center text-emerald-500 dark:text-emerald-300">{t("communityTitle")}</h2>
        <p className="mt-10 max-w-4xl mx-auto text-lg text-center text-gray-600 dark:text-gray-300 dark:text-white">{t("communityText")}</p>
        <div className="mt-15 flex justify-center">
          <Link
            href="auth"
            className="w-full sm:w-auto text-center rounded-md bg-emerald-600 px-3.5 py-2.5 text-sm font-semibold text-white shadow-xs hover:bg-emerald-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-500"
          >
            {t("auth")}
          </Link>
        </div>
      </div>
    </main>
  );
}

// import { useTranslations } from "next-intl";
// import Image from "next/image";
// import Link from "next/link";
// import { useState } from "react";
// import Header from "@/components/header";
// import Background from "@/components/background";
// import { ArrowPathIcon, CloudArrowUpIcon, FingerPrintIcon, LockClosedIcon } from "@heroicons/react/24/outline";

// export default function About() {
//   const t = useTranslations("About");
//   const [searchQuery, setSearchQuery] = useState("");
//   const [locationQuery, setLocationQuery] = useState("");

//   const features = [
//     {
//       name: t("features.trust.name"),
//       description: t("features.trust.description"),
//       icon: LockClosedIcon,
//     },
//     {
//       name: t("features.optimized.name"),
//       description: t("features.optimized.description"),
//       icon: CloudArrowUpIcon,
//     },
//     {
//       name: t("features.services.name"),
//       description: t("features.services.description"),
//       icon: ArrowPathIcon,
//     },
//     {
//       name: t("features.environmental.name"),
//       description: t("features.environmental.description"),
//       icon: FingerPrintIcon,
//     },
//   ];

//   return (
//     <div className="min-h-screen w-full overflow-hidden relative px-6 py-16 sm:py-24 lg:overflow-visible lg:px-0">
//       <Background />
//       <Header />
      
//       {/* Hero Section */}
//       <div className="mx-auto max-w-7xl px-4 md:px-6 lg:px-8">
//         <div className="mx-auto max-w-4xl text-center">
//           <h1 className="text-4xl md:text-5xl font-bold tracking-tight text-emerald-500 dark:text-emerald-300 mt-10">{t("title")}</h1>
//           <p className="mt-6 text-lg md:text-xl text-gray-600 dark:text-gray-300">{t("intro")}</p>
//         </div>
//       </div>

//       {/* Search Bar */}
//       <div className="container mx-auto px-4 py-8">
//         <div className="bg-white dark:bg-gray-800 rounded-xl shadow-xl p-6 mb-8 max-w-5xl mx-auto">
//           <h3 className="text-xl font-semibold mb-4 text-gray-800 dark:text-white">Rechercher un service</h3>
//           <div className="flex flex-col md:flex-row gap-4">
//             <div className="flex-1">
//               <label className="block text-sm font-medium text-gray-600 dark:text-gray-300 mb-1">Service</label>
//               <input
//                 type="text"
//                 placeholder="Que recherchez-vous ?"
//                 className="w-full px-4 py-3 rounded-lg bg-gray-50 dark:bg-gray-700 border border-gray-200 dark:border-gray-600 text-gray-900 dark:text-white focus:outline-none focus:ring-2 focus:ring-emerald-500"
//                 value={searchQuery}
//                 onChange={(e) => setSearchQuery(e.target.value)}
//               />
//             </div>
//             <div className="flex-1">
//               <label className="block text-sm font-medium text-gray-600 dark:text-gray-300 mb-1">Localisation</label>
//               <input
//                 type="text"
//                 placeholder="Où ?"
//                 className="w-full px-4 py-3 rounded-lg bg-gray-50 dark:bg-gray-700 border border-gray-200 dark:border-gray-600 text-gray-900 dark:text-white focus:outline-none focus:ring-2 focus:ring-emerald-500"
//                 value={locationQuery}
//                 onChange={(e) => setLocationQuery(e.target.value)}
//               />
//             </div>
//             <div className="md:self-end">
//               <button className="w-full px-6 py-3 bg-emerald-500 hover:bg-emerald-600 text-white rounded-lg font-semibold shadow-md transition-colors">
//                 Rechercher
//               </button>
//             </div>
//           </div>
//         </div>
//       </div>

//       {/* Features Section */}
//       <div className="py-12">
//         <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
//           <h2 className="text-2xl md:text-3xl font-bold text-center text-emerald-500 dark:text-emerald-300 mb-12">Nos avantages</h2>
//           <div className="grid grid-cols-1 gap-8 sm:grid-cols-2 lg:grid-cols-2">
//             {features.map((feature) => (
//               <div key={feature.name} className="relative group">
//                 <div className="flow-root rounded-lg bg-white dark:bg-gray-800 shadow-md hover:shadow-lg transition-all p-6 h-full border-l-4 border-emerald-500">
//                   <div>
//                     <div className="flex items-center">
//                       <span className="p-3 bg-emerald-100 dark:bg-emerald-900 rounded-full">
//                         <feature.icon className="h-6 w-6 text-emerald-600 dark:text-emerald-300" aria-hidden="true" />
//                       </span>
//                       <h3 className="ml-4 text-lg font-semibold leading-8 tracking-tight text-gray-900 dark:text-white">
//                         {feature.name}
//                       </h3>
//                     </div>
//                     <p className="mt-5 text-base leading-7 text-gray-600 dark:text-gray-300">
//                       {feature.description}
//                     </p>
//                   </div>
//                 </div>
//               </div>
//             ))}
//           </div>
//           <h2 className="text-2xl md:text-3xl text-center font-bold text-emerald-500 dark:text-emerald-300 mt-16">{t("impact")}</h2>
//         </div>
//       </div>

//       {/* Statistics Section */}
//       <div className="py-12 bg-gray-50 dark:bg-gray-900">
//         <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
//           <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
//             <div className="bg-white dark:bg-gray-800 p-6 rounded-xl shadow-md hover:shadow-lg transition-shadow border-l-4 border-emerald-500">
//               <div className="flex items-center">
//                 <div className="p-3 bg-emerald-100 dark:bg-emerald-900 rounded-full">
//                   <span className="text-2xl">🚚</span>
//                 </div>
//                 <div className="ml-4">
//                   <p className="text-sm text-gray-500 dark:text-gray-400">Livraisons effectuées</p>
//                   <p className="text-2xl font-bold text-gray-800 dark:text-white">1,200+</p>
//                 </div>
//               </div>
//             </div>
//             <div className="bg-white dark:bg-gray-800 p-6 rounded-xl shadow-md hover:shadow-lg transition-shadow border-l-4 border-blue-500">
//               <div className="flex items-center">
//                 <div className="p-3 bg-blue-100 dark:bg-blue-900 rounded-full">
//                   <span className="text-2xl">🛠️</span>
//                 </div>
//                 <div className="ml-4">
//                   <p className="text-sm text-gray-500 dark:text-gray-400">Prestations réalisées</p>
//                   <p className="text-2xl font-bold text-gray-800 dark:text-white">850+</p>
//                 </div>
//               </div>
//             </div>
//             <div className="bg-white dark:bg-gray-800 p-6 rounded-xl shadow-md hover:shadow-lg transition-shadow border-l-4 border-amber-500">
//               <div className="flex items-center">
//                 <div className="p-3 bg-amber-100 dark:bg-amber-900 rounded-full">
//                   <span className="text-2xl">⭐</span>
//                 </div>
//                 <div className="ml-4">
//                   <p className="text-sm text-gray-500 dark:text-gray-400">Note moyenne</p>
//                   <p className="text-2xl font-bold text-gray-800 dark:text-white">4.7</p>
//                 </div>
//               </div>
//             </div>
//           </div>
//         </div>
//       </div>

//       {/* Values Section */}
//       <div className="py-16">
//         <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
//           <div className="text-center mb-12">
//             <div className="flex justify-center mb-6">
//               <Image src="/favicon.ico" alt="Logo EcoDeli" height={120} width={120} className="h-20 w-20" />
//             </div>
//             <h2 className="text-2xl md:text-3xl font-bold text-emerald-500 dark:text-emerald-300">{t("connectionTitle")}</h2>
//             <p className="mt-4 max-w-3xl mx-auto text-lg text-gray-600 dark:text-gray-300">{t("connectionText")}</p>
//           </div>

//           <div className="grid grid-cols-1 gap-8 sm:grid-cols-3">
//             {[1, 2, 3].map((value) => (
//               <div key={value} className="bg-white dark:bg-gray-800 rounded-lg shadow-md hover:shadow-lg transition-all p-6">
//                 <h3 className="text-xl font-semibold text-center text-gray-900 dark:text-white mb-4">{t(`values.${value}.title`)}</h3>
//                 <p className="text-center text-gray-600 dark:text-gray-300">{t(`values.${value}.text`)}</p>
//               </div>
//             ))}
//           </div>
//         </div>
//       </div>

//       {/* Community Section */}
//       <div className="py-16 bg-emerald-50 dark:bg-emerald-900/20">
//         <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
//           <div className="text-center mb-12">
//             <div className="flex justify-center mb-6">
//               <Image src="/favicon.ico" alt="Logo EcoDeli" height={120} width={120} className="h-20 w-20" />
//             </div>
//             <h2 className="text-2xl md:text-3xl font-bold text-emerald-500 dark:text-emerald-300">{t("communityTitle")}</h2>
//             <p className="mt-4 max-w-3xl mx-auto text-lg text-gray-600 dark:text-white">{t("communityText")}</p>
//           </div>
          
//           {/* Quick Action Buttons */}
//           <div className="flex flex-col md:flex-row gap-6 justify-center mt-10">
//             <Link
//               href="/auth"
//               className="flex-1 max-w-xs bg-emerald-600 hover:bg-emerald-700 text-white py-4 px-6 rounded-xl text-center font-semibold shadow-md hover:shadow-lg transition-all flex items-center justify-center gap-2"
//             >
//               {t("auth")}
//             </Link>
//             <Link
//               href="/services"
//               className="flex-1 max-w-xs bg-white dark:bg-gray-800 text-emerald-600 dark:text-emerald-300 border-2 border-emerald-500 py-4 px-6 rounded-xl text-center font-semibold hover:bg-emerald-50 dark:hover:bg-gray-700 transition-all flex items-center justify-center gap-2"
//             >
//               Découvrir nos services
//             </Link>
//           </div>
//         </div>
//       </div>
//     </div>
//   );
// }