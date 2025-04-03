"use client";

import { useEffect, useState } from "react";
import { useParams } from "next/navigation";
import Link from "next/link";
import { fetchUserByID } from "@/lib/users/fetchUserByID";

export default function UserDetailPage() {
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
      <h2 className="text-2xl font-semibold mb-4">Détails de l'Utilisateur</h2>
      {loading ? (
        <p>Chargement...</p>
      ) : user ? (
        <div className="bg-white dark:bg-gray-800 p-4 rounded-lg shadow-md">
          <div className="mb-4">
            <img
              src={`/images/${user.photo_user}`} // Adapté en fonction de ton dossier d'images
              alt={`${user.first_name} ${user.last_name}`}
              className="w-32 h-32 rounded-full object-cover"
            />
          </div>
          <p><strong>Nom:</strong> {user.first_name} {user.last_name}</p>
          <p><strong>Email:</strong> {user.email}</p>
          <p><strong>Rôle:</strong> {user.role}</p>
          <p><strong>Statut du compte:</strong> {user.account_status}</p>
          <p><strong>Date d'enregistrement:</strong> {new Date(user.date_registration).toLocaleDateString()}</p>
          <p><strong>Date du statut:</strong> {new Date(user.date_status).toLocaleDateString()}</p>
          <p><strong>Entreprise:</strong> {user.company_name}</p>
          <p><strong>Bio:</strong> {user.bio}</p>
          <p><strong>SIRET:</strong> {user.siret}</p>
          <p><strong>Adresse:</strong> {user.street}, {user.postal_code} {user.country}</p>
          <p><strong>Code de paiement:</strong> {user.code_payment}</p>
          <p><strong>Expiration paiement:</strong> {user.expiration_payment}</p>
          <p><strong>IBAN:</strong> {user.iban}</p>

          {/* Abonnement */}
          <div className="mt-4">
            <p><strong>Abonnement:</strong> {user.id_subscription?.name_sub}</p>
            <p><strong>Description:</strong> {user.id_subscription?.description_sub}</p>
          </div>

          {/* Langue */}
          <div className="mt-4">
            <p><strong>Langue:</strong> {user.id_langue?.langue}</p>
          </div>

          <Link
            href="/admin/users"
            className="mt-4 inline-block px-4 py-2 bg-gray-500 text-white rounded-md hover:bg-gray-600"
          >
            Retour
          </Link>
        </div>
      ) : (
        <p>Utilisateur introuvable.</p>
      )}
    </div>
  );
}