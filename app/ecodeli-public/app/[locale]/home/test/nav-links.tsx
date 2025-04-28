// components/nav-links.tsx
"use client";

import { useState } from "react";
import Link from "next/link";
import { 
  HomeIcon, 
  ShoppingCartIcon, 
  UserGroupIcon, 
  DocumentTextIcon,
  CogIcon
} from "@heroicons/react/24/outline";
import clsx from "clsx";

interface NavLinksProps {
  isCollapsed: boolean;
  onNavigate?: () => void;
}

export default function NavLinks({ isCollapsed, onNavigate }: NavLinksProps) {
  const [activeItem, setActiveItem] = useState("dashboard");

  const navItems = [
    { name: "dashboard", href: "/dashboard", icon: HomeIcon, label: "Tableau de bord" },
    { name: "orders", href: "/orders", icon: ShoppingCartIcon, label: "Commandes" },
    { name: "customers", href: "/customers", icon: UserGroupIcon, label: "Clients" },
    { name: "reports", href: "/reports", icon: DocumentTextIcon, label: "Rapports" },
    { name: "settings", href: "/settings", icon: CogIcon, label: "Paramètres" },
  ];

  const handleNavigation = (itemName: string) => {
    setActiveItem(itemName);
    if (onNavigate) onNavigate();
  };

  return (
    <nav className="mt-8 flex-1">
      <ul className="space-y-2">
        {navItems.map((item) => (
          <li key={item.name}>
            <Link 
              href={item.href}
              onClick={() => handleNavigation(item.name)}
              className={clsx(
                "flex items-center p-3 rounded-lg transition-colors",
                activeItem === item.name 
                  ? "bg-blue-500 text-white" 
                  : "text-gray-700 dark:text-gray-200 hover:bg-gray-200 dark:hover:bg-gray-700",
                isCollapsed ? "justify-center" : "space-x-3"
              )}
            >
              <item.icon className={clsx("w-6 h-6", isCollapsed ? "mx-auto" : "")} />
              {!isCollapsed && <span>{item.label}</span>}
            </Link>
          </li>
        ))}
      </ul>
    </nav>
  );
}