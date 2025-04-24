"use client";

import { useEffect, useState } from "react";
import { useParams } from "next/navigation";
import Link from "next/link";
import { fetchUserByID } from "@/lib/users/fetchUserByID";
import { updateUser } from "@/lib/users/updateUser";
import { fetchAllLanguages } from "@/lib/languages/fetchAllLanguages";
import { fetchAllSubscriptions } from "@/lib/subscriptions/fetchAllSubscriptions";
import { useTranslations } from "next-intl";
import type { Language, Subscription, User } from "@/lib/types";
import Dropdown from "@/components/ui/dropdown";
import ImageUpload from "@/components/imageUpload"; // Import du composant ImageUpload

type UserFormData = {
  id_langue?: number;
  id_subscription?: number;
  photo_user?: File | string;
  first_name?: string;
  last_name?: string;
  email?: string;
  role?: string;
  date_registration?: string;
  date_status?: string;
  company_name?: string;
  bio?: string;
  siret?: string;
  street?: string;
  postal_code?: string;
  country?: string;
  code_payment?: string;
  expiration_payment?: string;
  iban?: string;
};

export default function UserDetailPage() {
  const t = useTranslations("Admin.UserManagement");
  const params = useParams();
  const [user, setUser] = useState<User | null>(null);
  const [loading, setLoading] = useState(false);
  const [editing, setEditing] = useState(false);
  const [languages, setLanguages] = useState<Language[]>([]);
  const [subscriptions, setSubscriptions] = useState<Subscription[]>([]);
  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const [formData, setFormData] = useState<UserFormData>({});

  useEffect(() => {
    const userId = Array.isArray(params.id) ? params.id[0] : params.id;

    if (!userId) return;

    setLoading(true);
    fetchUserByID(userId)
      .then((data) => {
        if (data) {
          setUser(data);
          setFormData({
            id_langue: data.id_langue?.id_langue,
            id_subscription: data.id_subscription?.id_sub,
            photo_user: data.photo_user || "",
            first_name: data.first_name,
            last_name: data.last_name,
            email: data.email,
            role: data.role,
            date_registration: data.date_registration?.split("T")[0],
            date_status: data.date_status?.split("T")[0],
            company_name: data.company_name,
            bio: data.bio,
            siret: data.siret,
            street: data.street,
            postal_code: data.postal_code,
            country: data.country,
            code_payment: data.code_payment,
            expiration_payment: data.expiration_payment,
            iban: data.iban,
          });
        }
        setLoading(false);
      })
      .catch(() => setLoading(false));

    fetchAllLanguages().then(setLanguages);
    fetchAllSubscriptions().then(setSubscriptions);
  }, [params?.id]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value, files } = e.target as HTMLInputElement;

    setFormData((prev) => ({
      ...prev,
      [name]: name === "photo_user" ? files?.[0] : value,
    }));
  };

  const handleFileChange = (file: File | null) => {
    setFormData((prev) => ({
      ...prev,
      photo_user: file || "", // Utilisez une chaîne vide si le fichier est null
    }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!params?.id || !formData.id_langue || !formData.id_subscription) {
      setErrorMessage(t("fillAllFields"));
      return;
    }

    const subscription = subscriptions.find((sub) => sub.id_sub === Number(formData.id_subscription));
    const language = languages.find((lang) => lang.id_langue === Number(formData.id_langue));
    const photo_user = formData.photo_user instanceof File ? formData.photo_user.name : formData.photo_user;

    const dataToUpdate = {
      ...formData,
      photo_user,
      id_subscription: subscription || undefined,
      id_langue: language || undefined,
    };

    try {
      const { status, data } = await updateUser(params.id as string, dataToUpdate as Partial<User>);

      if (status === 200) {
        setEditing(false);
        setUser(data);
        setSuccessMessage(t("updateSuccess"));
        setErrorMessage("");

        setTimeout(() => setSuccessMessage(""), 3000);
      } else {
        throw new Error("API returned unexpected status");
      }
    } catch (error) {
      console.error(error);
      setErrorMessage(t("updateError"));
    }
  };

  const handleCancel = () => {
    setEditing(false);
    if (!user) return;
    setFormData({
      id_langue: user.id_language?.id_langue,
      id_subscription: user.id_subscription?.id_sub,
      photo_user: user.photo_user || "",
      first_name: user.first_name,
      last_name: user.last_name,
      email: user.email,
      role: user.role,
      date_registration: user.date_registration?.split("T")[0],
      date_status: user.date_status?.split("T")[0],
      company_name: user.company_name,
      bio: user.bio,
      siret: user.siret,
      street: user.street,
      postal_code: user.postal_code,
      country: user.country,
      code_payment: user.code_payment,
      expiration_payment: user.expiration_payment,
      iban: user.iban,
    });
  };

  const roleOptions = [
    { id: 1, name: "part" },
    { id: 2, name: "pro" },
    { id: 3, name: "admin" },
  ];

  return (
    <div>
      <h2 className="text-2xl font-semibold mb-4">{t("userDetails")}</h2>

      {successMessage && (
        <div className="mb-4 p-4 bg-green-100 border border-green-400 text-green-700 rounded relative">
          {successMessage}
          <button
            onClick={() => setSuccessMessage("")}
            className="absolute top-2 right-2 text-green-800 hover:text-green-900"
          >
            ✕
          </button>
        </div>
      )}

      {errorMessage && (
        <div className="mb-4 p-4 bg-red-100 border border-red-400 text-red-700 rounded relative">
          {errorMessage}
          <button
            onClick={() => setErrorMessage("")}
            className="absolute top-2 right-2 text-red-800 hover:text-red-900"
          >
            ✕
          </button>
        </div>
      )}

      {loading ? (
        <p>{t("loading")}</p>
      ) : user ? (
        <div className="bg-white dark:bg-gray-800 p-4 rounded-lg shadow-md">
          {editing ? (
            <form onSubmit={handleSubmit}>
              <label className="block mb-2">{t("firstName")}</label>
              <input
                type="text"
                name="first_name"
                value={formData.first_name || ""}
                onChange={handleChange}
                className="w-full p-2 mb-4 border border-gray-200 dark:border-gray-600 rounded"
              />

              <label className="block mb-2">{t("lastName")}</label>
              <input
                type="text"
                name="last_name"
                value={formData.last_name || ""}
                onChange={handleChange}
                className="w-full p-2 mb-4 border border-gray-200 dark:border-gray-600 rounded"
              />

              <label className="block mb-2">{t("email")}</label>
              <input
                type="email"
                name="email"
                value={formData.email || ""}
                onChange={handleChange}
                className="w-full p-2 mb-4 border border-gray-200 dark:border-gray-600 rounded"
              />

              <Dropdown
                label={t("role")}
                options={roleOptions}
                selected={formData.role === "part" ? 1 : formData.role === "pro" ? 2 : formData.role === "admin" ? 3 : undefined}
                onChange={(value) => setFormData((prev) => ({ ...prev, role: roleOptions.find(role => role.id === value)?.name }))}
                buttonClassName="border border-gray-300 dark:border-gray-600"
              />

              <Dropdown
                label={t("subscription")}
                options={subscriptions.map((sub) => ({ id: sub.id_sub, name: sub.name_sub }))}
                selected={formData.id_subscription}
                onChange={(value) => setFormData((prev) => ({ ...prev, id_subscription: value }))}
              />

              <Dropdown
                label={t("language")}
                options={languages.map((lang) => ({ id: lang.id_langue, name: lang.langue }))}
                selected={formData.id_langue}
                onChange={(value) => setFormData((prev) => ({ ...prev, id_langue: value }))}
              />

              <ImageUpload
                label={t("photo")}
                onFileChange={handleFileChange}
              />

              <div className="flex gap-4">
                <button
                  type="submit"
                  className="px-4 py-2 bg-green-500 text-white rounded-md hover:bg-green-600"
                >
                  {t("save")}
                </button>
                <button
                  type="button"
                  onClick={handleCancel}
                  className="px-4 py-2 bg-gray-500 text-white rounded-md hover:bg-gray-600"
                >
                  {t("cancel")}
                </button>
              </div>
            </form>
          ) : (
            <>
              <div className="mb-4">
                <img
                  src={`/images/${user.photo_user}`}
                  alt={`${user.first_name} ${user.last_name}`}
                  className="w-32 h-32 rounded-full object-cover"
                />
              </div>
              <p><strong>{t("firstName")} :</strong> {user.first_name}</p>
              <p><strong>{t("lastName")} :</strong> {user.last_name}</p>
              <p><strong>{t("email")} :</strong> {user.email}</p>
              <p><strong>{t("role")} :</strong> {user.role}</p>
              <p><strong>{t("accountStatus")} :</strong> {user.account_status}</p>
              <p><strong>{t("registrationDate")} :</strong> {new Date(user.date_registration).toLocaleDateString()}</p>
              <p><strong>{t("statusDate")} :</strong> {new Date(user.date_status).toLocaleDateString()}</p>
              <p><strong>{t("company")} :</strong> {user.company_name}</p>
              <p><strong>{t("bio")} :</strong> {user.bio}</p>
              <p><strong>{t("siret")} :</strong> {user.siret}</p>
              <p><strong>{t("address")} :</strong> {user.street}, {user.postal_code} {user.country}</p>
              <p><strong>{t("paymentCode")} :</strong> {user.code_payment}</p>
              <p><strong>{t("paymentExpiration")} :</strong> {user.expiration_payment}</p>
              <p><strong>{t("iban")} :</strong> {user.iban}</p>

              <div className="mt-4">
                <p><strong>{t("subscription")} :</strong> {user.id_subscription?.name_sub}</p>
                <p><strong>{t("subscriptionDescription")} :</strong> {user.id_subscription?.description_sub}</p>
              </div>

              <div className="mt-4">
                <p><strong>{t("language")} :</strong> {user.id_langue?.langue}</p>
              </div>

              <div className="mt-4 flex gap-4">
                <button
                  onClick={() => setEditing(true)}
                  className="px-4 py-2 bg-green-500 text-white rounded-md hover:bg-green-600"
                >
                  {t("modify")}
                </button>
                <Link
                  href="/admin/users"
                  className="px-4 py-2 bg-gray-500 text-white rounded-md hover:bg-gray-600"
                >
                  {t("back")}
                </Link>
              </div>
            </>
          )}
        </div>
      ) : (
        <div>
          <p className="mb-4">{t("unknownUser")}</p>
          <Link
            href="/admin/users"
            className="px-4 py-2 bg-gray-500 text-white rounded-md hover:bg-gray-600"
          >
            {t("back")}
          </Link>
        </div>
      )}
    </div>
  );
}