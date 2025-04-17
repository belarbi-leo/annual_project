"use client";

import Image from "next/image";
import Link from "next/link";
import { usePathname } from "next/navigation";
import LanguageSelector from "@/components/languages";

export default function Header() {
  const pathname = usePathname();
  const pathWithoutLang = pathname.replace(/^\/[a-z]{2}(\/|$)/, "/");
  const isHome = pathWithoutLang === "/";

  return (
    <header className="absolute inset-x-0 top-0 z-50">
      <nav aria-label="Global" className="flex items-center justify-between p-6 lg:px-8">
        <div className="flex justify-between flex-1">
          {isHome ? ( <div /> ) : (
            <Link href="/" className="-m-1.5 p-1.5">
              <Image src="/favicon.ico" alt="Logo EcoDeli" height={240} width={240} className="h-8 w-auto" />
            </Link>
          )}
          <LanguageSelector />
        </div>
      </nav>
    </header>
  );
}