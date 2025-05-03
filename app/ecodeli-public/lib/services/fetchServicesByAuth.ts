import { API_BASE_URL, API_HEADERS } from "@/lib/config";
import type { Services } from '@/lib/types';
import { notFound } from "next/navigation";

export async function fetchServicesByAuth(auth: ("pro" | "all" | "part")[]): Promise<{ status: number; data: Services[]; error?: string }> {
  try {
    const params = auth.map(a => `auth=${a}`).join("&"); 
    const res = await fetch(`${API_BASE_URL}/services/read?${params}`, {
      method: "GET",
      headers: API_HEADERS,
    });
    if (!res.ok) notFound();
    const res_data = await res.json();
    return { status: res.status, data: res_data };
  } catch (error) {
    notFound();
  }
}
