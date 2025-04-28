import "./globals.css";
import type { Metadata } from "next";
import { Roboto } from "next/font/google";
import Background from "@/components/backgroundNotCo";

const roboto = Roboto({ subsets: ["latin"], weight: ["400"], variable: '--font-body' });

export const metadata: Metadata = {
  title: "EcoDeli",
  description: "La livraison repensée, solidaire et responsable.",
};
export default function RootLayout({ children }: { children: React.ReactNode }) {
  return (
    <html lang="fr" className={`${roboto.variable}`}>
      <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      </head>
      <body>
        <Background />
        {children}
      </body>
    </html>
  );
}