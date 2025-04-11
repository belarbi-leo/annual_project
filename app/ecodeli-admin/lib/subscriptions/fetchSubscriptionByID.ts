import { API_BASE_URL, API_HEADERS } from "@/lib/config";
import type { Subscription } from "@/lib/types";

export async function fetchSubscriptionByID(id: string): Promise<Subscription | null> {
  try {
    const response = await fetch(`${API_BASE_URL}/subscriptions/read/${id}`, {
      method: "GET",
      headers: API_HEADERS,
    });

    if (!response.ok) {
      throw new Error("Erreur lors de la récupération de l'abonnement");
    }

    const data: Subscription = await response.json();
    return data;
  } catch (error) {
    console.error(error);
    return null;
  }
}