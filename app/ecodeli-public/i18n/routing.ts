import {defineRouting} from 'next-intl/routing';
import { fetchAllLanguages } from "@/lib/languages/fetchAllLanguages";

async function getLocales() {
  const languages = await fetchAllLanguages();
  return languages.map((lang: { iso: string }) => lang.iso.toLowerCase()); // Extrait et met en minuscules
}
 
export const routing = defineRouting({
  // A list of all locales that are supported
  locales: await getLocales(),
 
  // Used when no locale matches
  defaultLocale: 'fr',
  
});