import {defineRouting} from 'next-intl/routing';
import { fetchAllLanguages } from "@/lib/languages/fetch-all-languages";

async function getLocales() {
  const languages = await fetchAllLanguages();
  return languages.map((lang: { iso: string }) => lang.iso.toLowerCase());
}
 
export const routing = defineRouting({
  locales: await getLocales(),
  defaultLocale: 'fr',
});