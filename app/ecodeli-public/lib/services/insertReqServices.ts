import { API_BASE_URL, API_HEADERS } from "@/lib/config";
import type { ReqServices } from '@/lib/types';

export async function insertReqService(data: ReqServices): Promise<{ status: number; data: any; error?: string }> {
  try {
    const res = await fetch(`${API_BASE_URL}/requestsServices/create`, {
      method: "POST",
      headers: API_HEADERS,
      body: JSON.stringify(data),
    });
    const res_data = await res.json();
    return { status: res.status, data: res_data };
  } catch (error) {
    return { 
      status: 500, 
      data: 'Erreur serveur, veuillez réessayer plus tard.',
    };
  }
}