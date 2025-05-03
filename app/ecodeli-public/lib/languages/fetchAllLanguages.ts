import { API_BASE_URL, API_HEADERS } from "@/lib/config";
import type { Languages } from '@/lib/types';
import { notFound } from "next/navigation";

export async function fetchAllLanguages(): Promise<{ status: number; data: Languages[]; error?: string }> {
  try {
    const res = await fetch(`${API_BASE_URL}/languages/read`, {
      method: "GET",
      headers: API_HEADERS,
    });
    if (!res.ok) notFound();
    const res_data = await res.json();
    return res_data;
  } catch (error) {
    notFound();
  }
}
