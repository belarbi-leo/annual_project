import { API_BASE_URL, API_HEADERS } from "@/lib/config";
import type { Languages } from '@/lib/types';
import { notFound } from "next/navigation";

export async function fetchAllLanguages(): Promise<Languages[]> {
  try {
    const res = await fetch(`${API_BASE_URL}/languages/read`, {
      method: "GET",
      headers: API_HEADERS,
    });
    if (!res.ok) notFound();
    const data = await res.json();
    return data;
  } catch (error) {
    notFound();
  }
}
