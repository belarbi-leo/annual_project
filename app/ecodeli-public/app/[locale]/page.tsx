import { useTranslations } from "next-intl";
import Image from "next/image";
import Link from "next/link";
import Header from "@/components/header";
import Background from "@/components/background";

export default function Index() {
  const t = useTranslations("Index");

  return (
    <div className="min-h-screen w-full overflow-hidden relative">
      <Background />
      <Header />

      <div className="flex items-center justify-center h-screen px-6 sm:px-16 lg:px-24">
        <div className="text-center">
          <h1 className="mb-4 text-4xl font-semibold tracking-tight text-emerald-500 dark:text-emerald-300 sm:text-7xl [font-family:var(--font-title)]">
            {t("title")}
          </h1>
          <Image src="/favicon.ico" alt="Logo EcoDeli" height={240} width={240} className="h-50 w-auto m-auto mb-5 transition-transform duration-300 hover:scale-110 hover:rotate-6"/>
          <h1 className="text-3xl font-semibold tracking-tight text-emerald-500 dark:text-emerald-300 sm:text-5xl">
            {t("subtitle")}
          </h1>
          <p className="mt-8 text-lg font-medium text-gray-500 dark:text-white sm:text-xl">
          {t("description")}
          </p>
          <div className="mt-10 flex flex-col items-center justify-center gap-4 sm:flex-row sm:gap-x-6 w-full">
            <Link
              href="auth"
              className="w-full sm:w-auto text-center rounded-md bg-emerald-600 px-3.5 py-2.5 text-sm font-semibold text-white shadow-xs hover:bg-emerald-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-emerald-500"
            >
              {t("auth")}
            </Link>
            <Link
              href="about"
              className="w-full sm:w-auto text-center text-sm font-semibold text-gray-900 dark:text-white"
            >
              {t("learnMore")}
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
}
