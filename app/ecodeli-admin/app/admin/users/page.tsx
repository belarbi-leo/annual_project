"use client";

import { useEffect, useState } from "react";
import { useSearchParams, useRouter } from "next/navigation";
import { fetchUsers } from "@/lib/users/fetchUsers";

const userTypes = [
  { label: "Clients Particuliers", value: "clients_particuliers" },
  { label: "Clients Professionnels", value: "clients_professionnels" },
  { label: "Prestataires Particuliers", value: "prestataires_particuliers" },
  { label: "Prestataires Professionnels", value: "prestataires_professionnels" },
  { label: "Administrateurs", value: "admins" },
  { label: "En attente de validation", value: "validation_required" },
];

export default function UsersManagementPage() {
  const searchParams = useSearchParams();
  const router = useRouter();
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(false);

  const selectedType = searchParams.get("type");

  useEffect(() => {
    setLoading(true);
    fetchUsers(selectedType).then((data) => {
      setUsers(data);
      setLoading(false);
    });
  }, [selectedType]);

  return (
    <div className="p-6">
      <h2 className="text-2xl font-semibold mb-4">Gestion des Utilisateurs</h2>
      
      {/* Boutons de sélection */}
      <div className="mb-4 grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-4 w-full">
        {userTypes.map(({ label, value }) => (
          <button
            key={value}
            onClick={() => router.push(`/admin/users?type=${value}`)}
            className={`px-4 py-2 w-full rounded-md ${selectedType === value ? "bg-blue-500 text-white" : "bg-gray-200 dark:bg-gray-700 text-gray-900 dark:text-white hover:bg-gray-300 dark:hover:bg-gray-600"}`}
          >
            {label}
          </button>
        ))}
      </div>

      {/* Liste des utilisateurs */}
      <div className="bg-white dark:bg-gray-800 p-4 rounded-lg shadow-md">
        {loading ? (
          <p>Chargement...</p>
        ) : users.length > 0 ? (
          <ul>
            {users.map((user: any) => (
              <li key={user.id_user} className="py-2 border-b border-gray-200 dark:border-gray-700">
                {user.first_name}  {user.last_name} - {user.email}
              </li>
            ))}
          </ul>
        ) : (
          <p>Aucun utilisateur trouvé.</p>
        )}
      </div>
    </div>
  );
}