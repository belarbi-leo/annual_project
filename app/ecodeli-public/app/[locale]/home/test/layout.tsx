// app/dashboard/layout.tsx
"use client";

import { useState, useEffect } from "react";
import "../../../globals.css";
import type { Metadata } from "next";
import { notFound } from "next/navigation";
import { NextIntlClientProvider, hasLocale } from "next-intl";
import { routing } from "@/i18n/routing";
import { Roboto } from "next/font/google";
import Sidebar from "../sidebar";
import DashboardHeader from "./dashboard-header";

const roboto = Roboto({ subsets: ["latin"], weight: ["400"], variable: '--font-body' });

export const metadata: Metadata = {
  title: "EcoDeli",
  description: "La livraison repensée, solidaire et responsable.",
};

export default async function App({ children, params } : { children: React.ReactNode; params: Promise<{ locale: string }> }) {
  const [isCollapsed, setIsCollapsed] = useState<boolean | null>(null);
  const [isSidebarVisible, setIsSidebarVisible] = useState<boolean>(false);
  const [isReady, setIsReady] = useState<boolean>(false);
  const { locale } = await params;
  if (!hasLocale(routing.locales, locale)) notFound();

  useEffect(() => {
    setIsReady(true);
    const handleResize = () => setIsCollapsed(window.innerWidth < 768);
    handleResize();
    window.addEventListener("resize", handleResize);
    return () => window.removeEventListener("resize", handleResize);
  }, []);

  if (!isReady) {
    return null;
  }

  return (
        <html lang={locale} className={`${roboto.variable}`}>
          <head>
            <meta name="viewport" content="width=device-width, initial-scale=1.0" />
          </head>
          <body>
            <NextIntlClientProvider locale={locale}>
            <div className="flex min-h-screen bg-gray-100 dark:bg-gray-900">
            {/* Sidebar pour desktop */}
                <Sidebar isMobile={false} />
                
                {/* Sidebar pour mobile */}
                <Sidebar 
                    isMobile={true} 
                    isVisible={isSidebarVisible} 
                    onClose={() => setIsSidebarVisible(false)} 
                />

                <div className="flex-1 flex flex-col">
                    <DashboardHeader onToggleSidebar={() => setIsSidebarVisible(true)} />
                    <main className="flex-1 p-6">
                    {children}
                    </main>
                </div>
            </div>
            </NextIntlClientProvider>
      </body>
    </html>
  );
}