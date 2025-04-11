import { useTranslations } from "next-intl";
import { usePathname } from "next/navigation";
import Link from "next/link";
import clsx from "clsx";
import {
  HomeIcon,
  UserIcon,
  ExclamationCircleIcon,
  BriefcaseIcon,
  ClipboardIcon,
  BuildingOfficeIcon,
  TruckIcon,
  CreditCardIcon,
  NewspaperIcon,
  ChatBubbleLeftRightIcon,
  ArrowLeftStartOnRectangleIcon,
  Cog6ToothIcon
} from "@heroicons/react/24/outline";

const links = [
  { name: "dashboard", href: "/admin", icon: HomeIcon },
  { name: "Separator", href: "", icon: null },
  { name: "userManagement", href: "/admin/users", icon: UserIcon },
  { name: "disputesManagement", href: "/admin/disputes", icon: ExclamationCircleIcon },
  { name: "servicesManagement", href: "/admin/services", icon: BriefcaseIcon },
  { name: "deliveriesManagement", href: "/admin/deliveries", icon: ClipboardIcon },
  { name: "warehousesManagement", href: "/admin/warehouses", icon: BuildingOfficeIcon },
  { name: "trackingManagement", href: "/admin/tracking", icon: TruckIcon },
  { name: "paymentsManagement", href: "/admin/payments", icon: CreditCardIcon },
  { name: "subscriptionsManagement", href: "/admin/subscriptions", icon: NewspaperIcon },
  { name: "messages", href: "/admin/messages", icon: ChatBubbleLeftRightIcon },
  { name: "websiteSettings", href: "/admin/settings", icon: Cog6ToothIcon },
  { name: "Separator", href: "", icon: null },
  { name: "logout", href: "/logout", icon: ArrowLeftStartOnRectangleIcon, isLogout: true },
];

interface NavLinksProps {
  isCollapsed: boolean;
  onNavigate?: () => void;
}

export default function AdminNavLinks({ isCollapsed, onNavigate }: NavLinksProps) {
  const pathname = usePathname();
  const t = useTranslations("Admin.Menu");

  // Extraire la langue de l'URL (ex: "/fr/admin" → "fr")
  const lang = pathname.split("/")[1];

  return (
    <nav className="mt-4 space-y-2">
      {links.map((link, index) => {
        if (link.name === "Separator") {
          return <hr key={`separator-${index}`} className="my-2 border-gray-300 dark:border-gray-700" />;
        }

        const LinkIcon = link.icon as React.ElementType;
        const linkHrefWithLang = `/${lang}${link.href}`; // Ajouter la langue à l'URL
        const isActive = pathname === linkHrefWithLang;

        return (
          <Link
            key={link.href}
            href={linkHrefWithLang}
            className={clsx(
              "text-sm flex items-center gap-2 px-4 py-2 rounded-md",
              "text-gray-900 dark:text-white hover:bg-gray-200 dark:hover:bg-gray-700",
              link.isLogout
                ? "text-red-600 dark:text-red-400 hover:bg-red-100 dark:hover:bg-red-800"
                : "text-gray-700 dark:text-gray-300",
              isActive && "bg-sky-100 text-green-600 dark:bg-green-900 dark:text-green-300"
            )}
            onClick={onNavigate}
          >
            {LinkIcon && <LinkIcon className="w-6 h-6" />}
            {!isCollapsed && <span>{t(link.name)}</span>}
          </Link>
        );
      })}
    </nav>
  );
}