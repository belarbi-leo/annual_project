import { API_BASE_URL, API_HEADERS } from "@/lib/config";
import type { Language } from '@/lib/types';


export async function fetchAllLanguages(): Promise<Language[]> {
  try {
    const res = await fetch(`${API_BASE_URL}/languages/read`, {
      method: "GET",
      headers: API_HEADERS,
    });

    if (!res.ok) {
      throw new Error("Erreur lors de la récupération des langues");
    }

    return await res.json(); // Retourne le JSON complet
  } catch (error) {
    console.error(error);
    return []; // Retourne un tableau vide en cas d'erreur
  }
}