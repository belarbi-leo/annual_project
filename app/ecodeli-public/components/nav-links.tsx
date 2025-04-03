"use client";

import { usePathname } from "next/navigation";
import Link from "next/link";
import {
  HomeIcon,
  TruckIcon,
  ExclamationCircleIcon,
  CreditCardIcon,
  ChatBubbleLeftRightIcon,
  CogIcon,
  MapIcon,
  ArrowLeftStartOnRectangleIcon,
  ArchiveBoxIcon,
  WrenchScrewdriverIcon,
} from "@heroicons/react/24/outline";
import clsx from "clsx";

const links = [
  { name: "Accueil", href: "/home", icon: HomeIcon },
  {
    name: "Prestations",
    href: "/home/prestations",
    icon: WrenchScrewdriverIcon,
  },
  { name: "Mes livraisons", href: "/home/packages", icon: TruckIcon },
  { name: "Mes litiges", href: "/home/dispute", icon: ExclamationCircleIcon },
  { name: "Mes paiements", href: "/home/payments", icon: CreditCardIcon },
  { name: "Messagerie", href: "/home/messages", icon: ChatBubbleLeftRightIcon },
  { name: "Compte", href: "/home/account", icon: CogIcon },
  { name: "Separator", href: "", icon: null },
  { name: "Espace livreur", href: "/home/trips", icon: MapIcon },
  {
    name: "Espace prestataire",
    href: "/home/deliveries",
    icon: ArchiveBoxIcon,
  },
  { name: "Separator", href: "", icon: null },
  {
    name: "Déconnexion",
    href: "/logout",
    icon: ArrowLeftStartOnRectangleIcon,
    isLogout: true,
  },
];

interface NavLinksProps {
  isCollapsed: boolean;
  onNavigate?: () => void;
}

export default function NavLinks({ isCollapsed, onNavigate }: NavLinksProps) {
  const pathname = usePathname();

  return (
    <nav className="mt-4 space-y-2">
      {links.map((link, index) => {
        if (link.name === "Separator") {
          return (
            <hr
              key={index}
              className="my-2 border-gray-300 dark:border-gray-700"
            />
          );
        }
        const LinkIcon = link.icon as React.ElementType;
        return (
          <Link
            key={link.name}
            href={link.href}
            className={clsx(
              link.isLogout
                ? "text-sm text-red-600 dark:text-red-400 hover:bg-red-100 dark:hover:bg-red-800"
                : "text-sm text-gray-700 dark:text-gray-300",
              "text-sm flex items-center gap-2 px-4 py-2 rounded-md text-gray-900 dark:text-white hover:bg-gray-200 dark:hover:bg-gray-700",
              pathname === link.href &&
                "bg-sky-100 text-green-600 dark:bg-green-900 dark:text-green-300"
            )}
            onClick={() => {
              if (onNavigate) onNavigate();
            }}
          >
            <LinkIcon className="w-6 h-6" />
            {!isCollapsed && <span>{link.name}</span>}
          </Link>
        );
      })}
    </nav>
  );
}
