import Image from "next/image";
import Link from "next/link";
import LanguageSelector from "@/components/languages";
import { useTranslations } from "next-intl";

export default function Home() {
  const t = useTranslations("Home");

  return (
    <div className="min-h-screen w-full overflow-hidden relative">
      {/* Effet de fond supérieur gauche */}
      <div
        aria-hidden="true"
        className="absolute top-[-10%] left-[-10%] -z-10 transform-gpu blur-3xl opacity-50"
      >
        <div
          className="h-100 w-100 bg-gradient-to-tr from-[#89c8fd] to-[#60b6ff] dark:from-[#245b90] dark:to-[#1a426a] rounded-full"
          style={{ filter: "blur(100px)" }}
        />
      </div>

      {/* Effet de fond inférieur droit */}
      <div
        aria-hidden="true"
        className="absolute bottom-[-10%] right-[-10%] -z-10 transform-gpu blur-3xl opacity-50"
      >
        <div
          className="h-100 w-100 bg-gradient-to-tr from-[#96d629] to-[#baeb6c] dark:from-[#3f7d1c] dark:to-[#67a731] rounded-full"
          style={{ filter: "blur(100px)" }}
        />
      </div>

      <header className="absolute inset-x-0 top-0 z-50">
        <nav
          aria-label="Global"
          className="flex items-center justify-between p-6 lg:px-8"
        >
          <div className="flex justify-between lg:flex-1">
            <Link href="#" className="-m-1.5 p-1.5">
              <Image
                src="/favicon.ico"
                alt="Logo EcoDeli"
                height={240}
                width={240}
                className="h-8 w-auto"
              />
            </Link>
            <LanguageSelector />
          </div>
        </nav>
      </header>

      <div className="flex items-center justify-center h-screen px-6 sm:px-16 lg:px-24">
        <div className="text-center">
          <h1 className="mb-4 text-5xl font-semibold tracking-tight text-[#49cb5c] dark:text-[#36a84b] sm:text-7xl">
          {t("title")}
          </h1>
          <h1 className="text-3xl font-semibold tracking-tight text-[#49cb5c] dark:text-[#36a84b] sm:text-5xl">
          {t("subtitle")}
          </h1>
          <p className="mt-8 text-lg font-medium text-gray-500 dark:text-gray-300 sm:text-xl">
          {t("description")}
          </p>
          <div className="mt-10 flex flex-col items-center justify-center gap-4 sm:flex-row sm:gap-x-6 w-full">
            <Link
              href="auth"
              className="w-full sm:w-auto text-center rounded-md bg-[#49cb5c] dark:bg-[#36a84b] px-3.5 py-2.5 text-sm font-semibold text-white shadow-xs hover:bg-[#07b128] dark:hover:bg-[#2e8c40] focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-[#09a66d]"
            >
              {t("login")}
            </Link>
            <Link
              href="public/about"
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
