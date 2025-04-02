"use client";

import { useState } from "react";
import NewAdsDelivery from "@/components/new-ads-delivery";
import {
  ClockIcon,
  ArrowPathIcon,
  ExclamationCircleIcon,
  CheckCircleIcon,
} from "@heroicons/react/24/outline";

const people = [
  { title: "La multiprise de Bryan", status: "pending" },
  { title: "Télévision", status: "pending" },
  { title: "Canapé", status: "in-progress" },
  { title: "Ordinateur", status: "completed" },
  { title: "La gamelle d'Enzo", status: "expired" },
  { title: "Lot de 5 sneakers", status: "completed" },
];

export default function LivraisonPage() {
  const [statusFilter, setStatusFilter] = useState("all");

  const handleStatusChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setStatusFilter(event.target.value);
  };

  const filters = [
    { value: "all", label: "Toutes", icon: null },
    { value: "pending", label: "En attente", icon: ClockIcon },
    { value: "in-progress", label: "En cours", icon: ArrowPathIcon },
    { value: "expired", label: "Expiré", icon: ExclamationCircleIcon },
    { value: "completed", label: "Terminé", icon: CheckCircleIcon },
  ];

  const getStatusIcon = (status: string) => {
    switch (status) {
      case "pending":
        return ClockIcon;
      case "in-progress":
        return ArrowPathIcon;
      case "expired":
        return ExclamationCircleIcon;
      case "completed":
        return CheckCircleIcon;
      default:
        return null;
    }
  };

  return (
    <div className="space-y-8 p-6 bg-gray-50 dark:bg-gray-900 min-h-screen">
      <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4 sm:gap-0 mt-5">
        <h2 className="text-3xl font-bold text-gray-800 dark:text-white">
          Mes livraisons solidaires
        </h2>
        <NewAdsDelivery />
      </div>

      <div className="flex flex-wrap gap-4 justify-center mt-15">
        {filters.map((filter) => (
          <label
            key={filter.value}
            className="cursor-pointer flex items-center gap-2"
          >
            <input
              type="radio"
              name="status"
              value={filter.value}
              checked={statusFilter === filter.value}
              onChange={handleStatusChange}
              className="hidden"
            />
            {filter.icon && (
              <filter.icon
                className={`w-5 h-5 ${
                  statusFilter === filter.value
                    ? "text-lime-600"
                    : "text-gray-400"
                }`}
              />
            )}
            <span
              className={`text-gray-700 text-sm font-medium transition-all duration-300
              ${
                statusFilter === filter.value
                  ? "text-lime-600 font-semibold border-b-2 border-lime-600"
                  : "hover:text-lime-500 hover:border-b-2 hover:border-lime-500"
              }`}
            >
              {filter.label}
            </span>
          </label>
        ))}
      </div>
      <hr className="my-6 border-t border-gray-300 dark:border-gray-600" />

      <ul role="list" className="divide-y divide-gray-100">
        {people
          .filter((person) => {
            if (statusFilter === "all") return true;
            return person.status === statusFilter;
          })
          .map((person) => {
            const Icon = getStatusIcon(person.status);
            return (
              <li
                key={person.title}
                className="flex justify-between items-center gap-x-6 py-5"
              >
                <div className="flex min-w-0 gap-x-4 items-center">
                  {Icon && (
                    <Icon className="w-10 h-10 text-lime-700 dark:text-gray-300" />
                  )}
                  <div className="min-w-0 flex-auto">
                    <p className="font-semibold text-gray-900">
                      Colis : {person.title}
                    </p>
                  </div>
                </div>
                <div className="hidden shrink-0 sm:flex sm:flex-col sm:items-end">
                  <button className="px-4 py-2 bg-green-700 text-white rounded-md shadow-md hover:bg-lime-600 transition">
                    Consulter
                  </button>
                </div>
              </li>
            );
          })}
      </ul>
    </div>
  );
}
