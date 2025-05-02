"use client";

import Image from "next/image";
import Link from "next/link";
import { ArrowLeftCircle, Home, RefreshCw } from "lucide-react";

export default function NotFound() {
  return (
    <main className="flex min-h-screen flex-col items-center justify-center py-12 px-4 sm:px-6 lg:px-8 bg-gradient-to-b from-white to-emerald-50 dark:from-gray-900 dark:to-gray-800">
      <div className="w-full max-w-4xl mx-auto text-center">
        <div className="transform transition-all duration-1000">
          <div className="relative mx-auto mb-8 w-40 h-40">
            <Image
              src="/favicon.ico"
              alt="Logo EcoDeli"
              height={160}
              width={160}
              className="transition-all duration-500 animate-bounce"
            />
          </div>
          
          <h1 className="mb-4 text-6xl font-bold tracking-tight text-emerald-500 dark:text-emerald-300 [font-family:var(--font-title)]">
            Error
          </h1>
          
          <p className="mt-6 text-xl font-medium text-gray-600 dark:text-gray-300 max-w-2xl mx-auto">
            Server error or the page you're looking for doesn't exist or has been moved.
          </p>
          
          <div className="mt-12 flex flex-col sm:flex-row justify-center items-center gap-4 sm:gap-x-6">
            <Link
              href="/public"
              className="w-[120px] flex items-center justify-center rounded-lg bg-emerald-600 px-5 py-3 text-base font-semibold text-white shadow-lg hover:bg-emerald-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-500 transition-all duration-300 hover:scale-105"
            >
              <Home className="mr-2 h-5 w-5" />
              Home
            </Link>
            <button
              onClick={() => window.history.back()}
              className="w-[120px] flex items-center justify-center rounded-lg border border-emerald-200 dark:border-emerald-800 px-5 py-3 text-base font-semibold text-gray-900 dark:text-white hover:bg-emerald-50 dark:hover:bg-emerald-900/30 transition-all duration-300"
            >
              <ArrowLeftCircle className="mr-2 h-5 w-5" />
              Return
            </button>
            <button
              onClick={() => window.location.reload()}
              className="w-[120px] flex items-center justify-center rounded-lg border border-emerald-200 dark:border-emerald-800 px-5 py-3 text-base font-semibold text-gray-900 dark:text-white hover:bg-emerald-50 dark:hover:bg-emerald-900/30 transition-all duration-300"
            >
              <RefreshCw className="mr-2 h-5 w-5" />
              Refresh
            </button>
          </div>
        </div>
      </div>
    </main>
  );
}