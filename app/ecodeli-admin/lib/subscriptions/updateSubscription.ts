import { API_BASE_URL, API_HEADERS } from "@/lib/config";

export async function updateSubscription(id: string, data: { name_sub: string; description_sub: string }) {
  try {
    const response = await fetch(`${API_BASE_URL}/subscriptions/update/${id}`, {
      method: "PUT",
      headers: {
        ...API_HEADERS,
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    });

    console.log("Response complète:", response);

    const responseData = await response.json();
    console.log("Response JSON:", responseData);

    if (!response.ok) {
      throw new Error("Erreur lors de la mise à jour de l'abonnement");
    }

    return { status: response.status, data: responseData }; // ✅ Retourne le statut + les données
  } catch (error) {
    console.error(error);
    return { status: 500, data: null }; // ✅ En cas d'erreur, renvoie 500 et null
  }
}