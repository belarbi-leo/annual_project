"use client";

import { usePathname } from "next/navigation";
import Link from "next/link";
import {
  HomeIcon,
  ArrowsRightLeftIcon,
  RectangleStackIcon,
  ArchiveBoxIcon,
  UserIcon,
  ArrowLeftStartOnRectangleIcon,
  ChatBubbleLeftRightIcon,
} from "@heroicons/react/24/outline";
import clsx from "clsx";

const links = [
  { name: "Accueil", href: "/dashboard", icon: HomeIcon },
  { name: "Livraison", href: "/dashboard/delivery", icon: ArrowsRightLeftIcon },
  { name: "Prestations", href: "/dashboard/prestations", icon: RectangleStackIcon },
  { name: "Historique", href: "/dashboard/history", icon: ArchiveBoxIcon },
  { name: "Messages", href: "/dashboard/messages", icon: ChatBubbleLeftRightIcon },
  { name: "Compte", href: "/dashboard/account", icon: UserIcon },
  { name: 'Déconnexion', href: '/logout', icon: ArrowLeftStartOnRectangleIcon, isLogout: true },
];

interface NavLinksProps {
  isCollapsed: boolean; // Ajout du type pour isCollapsed
}

export default function NavLinks({ isCollapsed }: NavLinksProps) {
  const pathname = usePathname();

  return (
    <nav className="mt-4 space-y-2">
      {links.map((link) => {
        const LinkIcon = link.icon;
        return (
          <Link
            key={link.name}
            href={link.href}
            className={clsx(
                link.isLogout
                ? 'text-red-600 dark:text-red-400 hover:bg-red-100 dark:hover:bg-red-800'
                : 'text-gray-700 dark:text-gray-300',
              "flex items-center gap-2 px-4 py-2 rounded-md text-gray-900 dark:text-white hover:bg-gray-200 dark:hover:bg-gray-700",
              pathname === link.href && "bg-sky-100 text-blue-600 dark:bg-blue-900 dark:text-blue-300"
            )}
          >
            <LinkIcon className="w-6 h-6" />
            {!isCollapsed && <span>{link.name}</span>}
          </Link>
        );
      })}
    </nav>
  );
}