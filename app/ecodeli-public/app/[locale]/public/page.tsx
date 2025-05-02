"use client";

import { useState, useEffect } from "react";
import { useTranslations } from "next-intl";
import Image from "next/image";
import Link from "next/link";
import { ArrowRight, Leaf, ShoppingBag, Heart } from "lucide-react";

export default function Index() {
  const t = useTranslations("Index");
  const [animate, setAnimate] = useState(false);
  const [scrollY, setScrollY] = useState(0);

  useEffect(() => {
    setAnimate(true);

    const handleScroll = () => {
      setScrollY(window.scrollY);
    };
    
    window.addEventListener("scroll", handleScroll);
    return () => window.removeEventListener("scroll", handleScroll);
  }, []);

  const features = [
    { icon: <Leaf className="h-6 w-6 text-emerald-500" />, text: t("feature1") },
    { icon: <ShoppingBag className="h-6 w-6 text-emerald-500" />, text: t("feature2") },
    { icon: <Heart className="h-6 w-6 text-emerald-500" />, text: t("feature3") }
  ];

  return (
    <main className="py-12 px-4 sm:px-6 lg:px-8">
      <div className="flex flex-col xl:flex-row items-center justify-between max-w-7xl mx-auto px-6 sm:px-16 xl:px-24 min-h-screen">
        <div className={`w-full xl:w-1/2 text-center xl:text-left transform transition-all duration-1000 ${animate ? 'translate-x-0 opacity-100' : '-translate-x-20 opacity-0'}`}>            
          <h1 className="mb-4 text-5xl font-bold tracking-tight text-emerald-500 dark:text-emerald-300 sm:text-7xl [font-family:var(--font-title)]">
            {t("title")}
          </h1>
          <h2 className="text-3xl font-semibold tracking-tight text-emerald-500 dark:text-emerald-300 sm:text-5xl mb-6">
            {t("subtitle")}
          </h2>
          <p className="mt-10 text-lg font-medium text-gray-600 dark:text-gray-300 sm:text-xl -xl:max-w-xl">
            {t("description")}
          </p>
          <div className="mt-10 flex flex-col sm:flex-row xl:justify-start justify-center items-center gap-4 sm:gap-x-6">
            <Link
              href="public/auth"
              className="w-full sm:w-auto flex items-center justify-center rounded-lg bg-emerald-600 px-5 py-3 text-base font-semibold text-white shadow-lg hover:bg-emerald-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-500 transition-all duration-300 hover:scale-105"
            >
              {t("auth")} <ArrowRight className="ml-2 h-4 w-4" />
            </Link>
            <Link
              href="public/about"
              className="w-full sm:w-auto flex items-center justify-center rounded-lg border border-emerald-200 dark:border-emerald-800 px-5 py-3 text-base font-semibold text-gray-900 dark:text-white hover:bg-emerald-50 dark:hover:bg-emerald-900/30 transition-all duration-300"
            >
              {t("learnMore")}
            </Link>
          </div>
          <div className="mt-12 grid grid-cols-1 sm:grid-cols-3 gap-6">
            {features.map((feature, index) => (
              <div key={index} className="flex items-center p-4 rounded-lg bg-white/80 dark:bg-gray-800/50 backdrop-blur-sm shadow-sm">
                <div className="flex-shrink-0 mr-3">{feature.icon}</div>
                <p className="text-sm text-gray-700 dark:text-gray-300">{feature.text}</p>
              </div>
            ))}
          </div>
        </div>
        <div className={`flex w-full lg:w-1/2 mt-12 xl:mt-10 justify-center items-center transform transition-all duration-1000 ${animate ? 'translate-x-0 opacity-100' : 'translate-x-20 opacity-0'}`}>
          <div className="relative">
              <Image 
                src="/favicon.ico" 
                alt="Logo EcoDeli" 
                height={300} 
                width={300} 
                className="h-64 w-auto transition-all duration-500 hover:scale-110 hover:rotate-12"
                style={{ transform: `rotate(${scrollY * 0.05}deg)` }}
              />
          </div>
        </div>
      </div>
    </main>
  );
}