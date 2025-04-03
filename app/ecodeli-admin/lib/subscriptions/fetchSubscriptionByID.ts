import { API_BASE_URL, API_HEADERS } from "@/lib/config";

export async function fetchSubscriptionByID(id: string) {
  try {
    const response = await fetch(`${API_BASE_URL}/subscriptions/read/${id}`, {
      method: "GET",
      headers: API_HEADERS,
    });

    if (!response.ok) {
      throw new Error("Erreur lors de la récupération de l'abonnement");
    }

    return await response.json();
  } catch (error) {
    console.error(error);
    return null;
  }
}