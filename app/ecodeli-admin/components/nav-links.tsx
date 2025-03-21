"use client";

import { usePathname } from "next/navigation";
import Link from "next/link";
import {
  HomeIcon,
  UserIcon,
  TruckIcon,
  ClipboardIcon,
  BriefcaseIcon,
  ExclamationCircleIcon,
  CreditCardIcon,
  NewspaperIcon, 
  BuildingOfficeIcon,
  ArrowLeftStartOnRectangleIcon,
  ChatBubbleLeftRightIcon,
} from "@heroicons/react/24/outline";
import clsx from "clsx";

const links = [
{ name: "Tableau de bord", href: "/admin/home", icon: HomeIcon },//user langues notif add admin
{ name: "Gestion des utilisateurs", href: "/admin/users", icon: UserIcon },
{ name: "Gestion des litiges", href: "/admin/disputes", icon: ExclamationCircleIcon },
{ name: "Gestion des prestations", href: "/admin/services", icon: BriefcaseIcon },//crée services
{ name: "Gestion des livraisons", href: "/admin/deliveries", icon: ClipboardIcon },
{ name: "Gestion des entrepôts", href: "/admin/warehouses", icon: BuildingOfficeIcon },
{ name: "Suivi des colis", href: "/admin/track", icon: TruckIcon },
{ name: "Paiements", href: "/admin/payments", icon: CreditCardIcon },
{ name: "Abonnements", href: "/admin/subscriptions", icon: NewspaperIcon },
{ name: "Messagerie", href: "/admin/messages", icon: ChatBubbleLeftRightIcon },
{ name: 'Déconnexion', href: '/logout', icon: ArrowLeftStartOnRectangleIcon, isLogout: true },
];

interface NavLinksProps {
  isCollapsed: boolean;
  onNavigate?: () => void;
}

export default function AdminNavLinks({ isCollapsed, onNavigate }: NavLinksProps) {
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
                ? 'text-sm text-red-600 dark:text-red-400 hover:bg-red-100 dark:hover:bg-red-800'
                : 'text-sm text-gray-700 dark:text-gray-300',
              "text-sm flex items-center gap-2 px-4 py-2 rounded-md text-gray-900 dark:text-white hover:bg-gray-200 dark:hover:bg-gray-700",
              pathname === link.href && "bg-sky-100 text-blue-600 dark:bg-blue-900 dark:text-blue-300"
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