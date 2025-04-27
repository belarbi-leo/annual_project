import "../globals.css";
import type { Metadata } from "next";
import { notFound } from "next/navigation";
import { NextIntlClientProvider, hasLocale } from "next-intl";
import { routing } from "@/i18n/routing";
import { Roboto } from "next/font/google";
import { Roboto_Slab } from "next/font/google";
import Header from "@/components/header";
import Background from "@/components/background";

const roboto = Roboto({ subsets: ["latin"], weight: ["400"], variable: '--font-body' });

const robotoSlab = Roboto_Slab({ subsets: ['latin'], weight: ['800'], variable: '--font-title' });

export const metadata: Metadata = {
  title: "EcoDeli",
  description: "La livraison repensée, solidaire et responsable.",
};

export default async function App({ children, params } : { children: React.ReactNode; params: Promise<{ locale: string }> }) {
  const { locale } = await params;
  if (!hasLocale(routing.locales, locale)) notFound();

  return (
    <html lang={locale} className={`${roboto.variable} ${robotoSlab.variable}`}>
      <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      </head>
      <body>
        <NextIntlClientProvider locale={locale}>
          <div className="min-h-screen w-full overflow-x-hidden relative">
            <Background />
            <Header />
            {children}
          </div>
        </NextIntlClientProvider>
      </body>
    </html>
  );
}

// 

// Anglais
// "feature1": "Greener delivery",
// "feature2": "Supports businesses",
// "feature3": "Reduces carbon impact"
// Italien 🇮🇹

// json
// Copier
// Modifier
// "feature1": "Consegna più ecologica",
// "feature2": "Supporta le imprese",
// "feature3": "Riduce l'impatto di carbonio"
// Allemand 🇩🇪

// json
// Copier
// Modifier
// "feature1": "Umweltfreundlichere Lieferung",
// "feature2": "Unterstützt Unternehmen",
// "feature3": "Reduziert den CO₂-Ausstoß"
// Espagnol 🇪🇸

// json
// Copier
// Modifier
// "feature1": "Entrega más ecológica",
// "feature2": "Apoya a las empresas",
// "feature3": "Reduce el impacto de carbono"