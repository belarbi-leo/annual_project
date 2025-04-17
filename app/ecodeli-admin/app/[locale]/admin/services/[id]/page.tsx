'use client';

import { useEffect, useState } from "react";
import { useParams } from "next/navigation";
import Link from "next/link";
import { fetchServiceByID } from "@/lib/services/fetchServiceByID";
import { updateService } from "@/lib/services/updateService";
import { useTranslations } from 'next-intl';
import type { Service } from "@/lib/types";
import Dropdown from "@/components/ui/dropdown";

const serviceCategories = [
  { id: 1, name: "personalServices", value: "sp" },
  { id: 2, name: "transportDelivery", value: "tl" },
  { id: 3, name: "constructionReparation", value: "tr" },
  { id: 4, name: "itDigital", value: "id" },
  { id: 5, name: "eventsActivities", value: "el" },
];

export default function ServiceDetailPage() {
  const t = useTranslations('Admin.ServicesManagement');
  const params = useParams();
  const [service, setService] = useState<Service | null>(null);
  const [loading, setLoading] = useState(false);
  const [editing, setEditing] = useState(false);
  const [formData, setFormData] = useState({
    name_svc: "",
    category: "",
  });
  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  useEffect(() => {
    if (!params?.id) return;
    setLoading(true);
    fetchServiceByID(params.id as string).then((data) => {
      if (data) {
        setService(data);
        setFormData({
          name_svc: data.name_svc,
          category: data.category,
        });
      }
      setLoading(false);
    });
  }, [params?.id]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleCategoryChange = (value: number) => {
    const selectedCategory = serviceCategories.find(cat => cat.id === value);
    if (selectedCategory) {
      setFormData({ ...formData, category: selectedCategory.value });
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!params?.id) return;

    try {
      const { status, data } = await updateService(params.id as string, formData);

      if (status === 200) {
        setEditing(false);
        setService(data);
        setSuccessMessage("Modification enregistrée avec succès !");
        setErrorMessage("");

        setTimeout(() => setSuccessMessage(""), 3000);
      } else {
        throw new Error("Réponse inattendue de l'API.");
      }
    } catch (error) {
      console.error("Erreur:", error);
      setErrorMessage("Une erreur est survenue. Veuillez réessayer.");
    }
  };

  const handleCancel = () => {
    setEditing(false);
    setFormData({
      name_svc: service?.name_svc || "",
      category: service?.category || "",
    });
  };

  const selectedCategoryId = serviceCategories.find(cat => cat.value === formData.category)?.id;

  return (
    <div className="">
      <h2 className="text-2xl font-semibold mb-4">{t('serviceDetails')}</h2>

      {/* Message de succès */}
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

      {/* Message d'erreur */}
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
        <p>{t('loading')}</p>
      ) : service ? (
        <div className="bg-white dark:bg-gray-800 p-4 rounded-lg shadow-md">
          {editing ? (
            <form onSubmit={handleSubmit}>
              <label className="block mb-2">{t('serviceName')}</label>
              <input
                type="text"
                name="name_svc"
                value={formData.name_svc}
                onChange={handleChange}
                className="w-full p-2 mb-4 border rounded"
              />
              <Dropdown
                label={t('category')}
                options={serviceCategories.map(cat => ({ id: cat.id, name: t(cat.name) }))}
                selected={selectedCategoryId}
                onChange={handleCategoryChange}
              />
              <div className="flex gap-4">
                <button
                  type="submit"
                  className="px-4 py-2 bg-green-500 text-white rounded-md hover:bg-green-600"
                >
                  {t('save')}
                </button>
                <button
                  type="button"
                  onClick={handleCancel}
                  className="px-4 py-2 bg-gray-500 text-white rounded-md hover:bg-gray-600"
                >
                  {t('cancel')}
                </button>
              </div>
            </form>
          ) : (
            <>
              <p><strong>{t('serviceName')} :</strong> {service.name_svc}</p>
              <p><strong>{t('category')} :</strong> {t(serviceCategories.find(cat => cat.value === service.category)?.name || "")}</p>
              <div className="mt-4 flex gap-4">
                <button
                  onClick={() => setEditing(true)}
                  className="px-4 py-2 bg-green-500 text-white rounded-md hover:bg-green-600"
                >
                  {t('modify')}
                </button>
                <Link
                  href="/admin/services"
                  className="px-4 py-2 bg-gray-500 text-white rounded-md hover:bg-gray-600"
                >
                  {t('back')}
                </Link>
              </div>
            </>
          )}
        </div>
      ) : (
        <div>
          <p className="mb-4">{t('noServiceFound')}</p>
          <Link
            href="/admin/services"
            className="px-4 py-2 bg-gray-500 text-white rounded-md hover:bg-gray-600"
          >
            {t('back')}
          </Link>
        </div>
      )}
    </div>
  );
}