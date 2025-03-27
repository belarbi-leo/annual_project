"use client";
  
import { useEffect, useState } from "react";
import { fetchUsers } from "@/lib/users/fetchUsers";
import clsx from "clsx";

export default function UsersManagementPage() {
  const [users, setUsers] = useState<any[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    async function loadUsers() {
      const data = await fetchUsers();
      setUsers(data);
      setLoading(false);
    }
    loadUsers();
  }, []);

  return (
    <div className="p-6">
      <h2 className="text-2xl font-semibold text-gray-900 dark:text-white mb-6">
        Gestion des Utilisateurs
      </h2>
      {loading ? (
        <p className="text-gray-600 dark:text-gray-300">Chargement...</p>
      ) : (
        <div className="table w-full border border-gray-200 dark:border-gray-700 rounded-lg shadow-md overflow-hidden">
          <div className="table-header-group bg-gray-200 dark:bg-gray-700">
            <div className="table-row">
              <div className="table-cell p-3 text-left font-semibold text-gray-900 dark:text-white">
                Nom
              </div>
              <div className="table-cell p-3 text-left font-semibold text-gray-900 dark:text-white">
                Email
              </div>
              <div className="table-cell p-3 text-left font-semibold text-gray-900 dark:text-white">
                Rôle
              </div>
            </div>
          </div>
          <div className="table-row-group">
            {users.map(user => (
              <div key={user.id} className="table-row border-t border-gray-200 dark:border-gray-700">
                <div className="table-cell p-3 text-gray-900 dark:text-white">{user.name}</div>
                <div className="table-cell p-3 text-gray-900 dark:text-white">{user.email}</div>
                <div className="table-cell p-3 text-gray-900 dark:text-white">
                  <span
                    className={clsx("px-3 py-1 rounded-full text-sm", {
                      "bg-blue-500 text-white": user.role === "Admin",
                      "bg-green-500 text-white": user.role === "Utilisateur",
                    })}
                  >
                    {user.role}
                  </span>
                </div>
              </div>
            ))}
          </div>
        </div>
      )}
    </div>
  );
}  