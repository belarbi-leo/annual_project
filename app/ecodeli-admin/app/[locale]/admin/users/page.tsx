"use client";

import { useEffect, useState } from "react";
import { useSearchParams, useRouter, usePathname } from "next/navigation";
import Link from "next/link";
import { fetchUsers } from "@/lib/users/fetchAllUsers";
import { useTranslations } from "next-intl";
import { ArrowRightCircleIcon } from "@heroicons/react/24/outline";
import SearchBar from "@/components/searchbar"; // Assure-toi du bon chemin

const userTypes = [
  { label: "allUsers", value: "" },
  { label: "privateUser", value: "clients_particuliers" },
  { label: "proUser", value: "clients_professionnels" },
  { label: "privateProvider", value: "prestataires_particuliers" },
  { label: "proProvider", value: "prestataires_professionnels" },
  { label: "administrators", value: "admins" },
  { label: "accountToValidate", value: "account_validation_required" },
  { label: "serviceToValidate", value: "service_validation_required" },
];

export default function UsersManagementPage() {
  const t = useTranslations("Admin.UserManagement");
  const searchParams = useSearchParams();
  const router = useRouter();
  const pathname = usePathname();

  const [users, setUsers] = useState([]);
  const [search, setSearch] = useState('');
  const [loading, setLoading] = useState(false);
  const [selectedType, setSelectedType] = useState<string | null>(searchParams.get("type"));

  useEffect(() => {
    setLoading(true);
    fetchUsers(selectedType).then((data) => {
      setUsers(data);
      setLoading(false);
    });
  }, [selectedType]);

  const handleFilterChange = (value: string) => {
    setSelectedType(value);
    if (value === "") {
      // Si "allUsers" est sélectionné, on enlève le paramètre "type"
      router.replace(pathname, { scroll: false });
    } else {
      // Applique le filtre "type"
      router.replace(`${pathname}?type=${value}`, { scroll: false });
    }
  };

  const filteredUsers = users.filter((user: any) =>
    `${user.first_name} ${user.last_name} ${user.email}`
      .toLowerCase()
      .includes(search.toLowerCase())
  );

  return (
    <div className="space-y-6 min-h-screen">
      <div className="text-center max-w-3xl mx-auto">
        <h2 className="text-3xl font-bold text-gray-800 my-5 dark:text-white">
          {t('userManagement')}
        </h2>
      </div>

      <div className="w-full h-[2px] bg-gray-300 dark:bg-gray-600"></div>

      <div className="flex flex-wrap gap-6 justify-center my-6">
        {userTypes.map(({ label, value }) => (
          <button
            key={value}
            onClick={() => handleFilterChange(value)}
            className={`cursor-pointer text-sm font-medium transition-all duration-300
              ${selectedType === value
                ? "text-green-600 font-semibold border-b-2 border-green-600"
                : "text-gray-700 dark:text-gray-300 hover:text-green-500 hover:border-b-2 hover:border-green-500"
              }
            `}
          >
            {t(label)}
          </button>
        ))}
      </div>

      {/* SearchBar */}
      <div className="mx-auto">
        <SearchBar
          value={search}
          onChange={setSearch}
          placeholder={t('searchUser')}
        />
      </div>

      <div className="bg-white dark:bg-gray-800 p-6 rounded-lg shadow-md">
        {loading ? (
          <p>{t("loading")}</p>
        ) : filteredUsers.length > 0 ? (
          <ul>
            {filteredUsers.map((user: any) => (
              <li
                key={user.id_user}
                className="py-2 border-b border-gray-200 dark:border-gray-700 flex justify-between items-center"
              >
                <span>
                  {user.first_name} {user.last_name} - {user.email}
                </span>
                <Link
                  href={`/admin/users/${user.id_user}`}
                  className="px-3 py-1 text-gray-900 dark:text-white rounded-md hover:text-green-500"
                >
                  <ArrowRightCircleIcon className="w-6 h-6" />
                </Link>
              </li>
            ))}
          </ul>
        ) : (
          <p>{t("noUserFound")}</p>
        )}
      </div>
    </div>
  );
}