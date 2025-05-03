"use client";

import { useTranslations } from "next-intl";
import Image from "next/image";
import Link from "next/link";
import SearchBar from "@/components/searchService";
import { ArrowPathIcon, CloudArrowUpIcon, FingerPrintIcon, LockClosedIcon, ChatBubbleLeftRightIcon } from "@heroicons/react/24/outline";

export default function About() {
  const t = useTranslations("About");

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
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-center">
          <Image 
            src="/favicon.ico" 
            alt="Logo EcoDeli" 
            height={240} 
            width={240} 
            className="h-20 w-20 sm:h-22 sm:w-22"
          />
        </div>
        <h2 className="mt-8 sm:mt-10 text-2xl sm:text-3xl font-bold text-center text-emerald-500 dark:text-emerald-300">{t("communityTitle")}</h2>
        <p className="mt-4 sm:mt-6 max-w-4xl mx-auto text-base sm:text-lg text-center text-gray-600 dark:text-gray-300 dark:text-white">{t("communityText")}</p>
        <div className="mt-8 sm:mt-10 flex flex-col sm:flex-row justify-center items-center sm:space-x-4 space-y-4 sm:space-y-0">
          <Link href="auth" className="w-full sm:w-auto text-center rounded-md bg-emerald-600 px-3.5 py-2.5 text-sm font-semibold text-white shadow-xs hover:bg-emerald-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-500">
            {t("auth")}
          </Link>
          <Link href="contact" className="w-full sm:w-auto text-center rounded-md bg-emerald-600 px-3.5 py-2.5 text-sm font-semibold text-white shadow-xs hover:bg-emerald-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-500 flex items-center justify-center">
            <ChatBubbleLeftRightIcon className="h-5 w-5 mr-2" aria-hidden="true" />
            {t("contact")}
          </Link>
        </div>
      </div>
    </main>
  );
}