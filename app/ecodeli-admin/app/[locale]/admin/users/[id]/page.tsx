"use client";

import { useEffect, useState } from "react";
import { useParams } from "next/navigation";
import Link from "next/link";
import { fetchUserByID } from "@/lib/users/fetchUserByID";
import {useTranslations} from 'next-intl';

export default function UserDetailPage() {
  const t = useTranslations('Admin.UserManagement');
  const params = useParams();
  const [user, setUser] = useState<any>(null);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const userId = Array.isArray(params.id) ? params.id[0] : params.id;

    if (!userId) return;

    setLoading(true);
    fetchUserByID(userId)
      .then((data) => {
        setUser(data);
        setLoading(false);
      })
      .catch(() => setLoading(false));
  }, [params?.id]);

  return (
    <div className="">
      <h2 className="text-2xl font-semibold mb-4">{t('userDetails')}</h2>
      {loading ? (
        <p>{t('loading')}</p>
      ) : user ? (
        <div className="bg-white dark:bg-gray-800 p-4 rounded-lg shadow-md">
          <div className="mb-4">
            <img
              src={`/images/${user.photo_user}`} // Adapté en fonction de ton dossier d'images
              alt={`${user.first_name} ${user.last_name}`}
              className="w-32 h-32 rounded-full object-cover"
            />
          </div>
          
          {/* Général */}
          <p><strong>{t('name')} :</strong> {user.first_name} {user.last_name}</p>
          <p><strong>{t('email')} :</strong> {user.email}</p>
          <p><strong>{t('role')} :</strong> {user.role}</p>
          <p><strong>{t('accountStatus')}:</strong> {user.account_status}</p>
          <p><strong>{t('registrationDate')} :</strong> {new Date(user.date_registration).toLocaleDateString()}</p>
          <p><strong>{t('statusDate')} :</strong> {new Date(user.date_status).toLocaleDateString()}</p>
          <p><strong>{t('company')} :</strong> {user.company_name}</p>
          <p><strong>{t('bio')} :</strong> {user.bio}</p>
          <p><strong>{t('siret')} :</strong> {user.siret}</p>
          <p><strong>{t('address')} :</strong> {user.street}, {user.postal_code} {user.country}</p>
          <p><strong>{t('paymentCode')} :</strong> {user.code_payment}</p>
          <p><strong>{t('paymentExpiration')} :</strong> {user.expiration_payment}</p>
          <p><strong>{t('iban')} :</strong> {user.iban}</p>

          {/* Abonnement */}
          <div className="mt-4">
            <p><strong>{t('subscription')} :</strong> {user.id_subscription?.name_sub}</p>
            <p><strong>{t('subscriptionDescription')} :</strong> {user.id_subscription?.description_sub}</p>
          </div>

          {/* Langue */}
          <div className="mt-4">
            <p><strong>{t('language')} :</strong> {user.id_langue?.langue}</p>
          </div>

          <Link
            href="/admin/users"
            className="mt-4 inline-block px-4 py-2 bg-gray-500 text-white rounded-md hover:bg-gray-600"
          >
            {t('back')}
          </Link>
        </div>
      ) : (
        <p>{t('unknownUser')}</p>
      )}
    </div>
  );
}