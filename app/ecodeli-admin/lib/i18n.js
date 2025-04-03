import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';
import Backend from 'i18next-http-backend';
import LanguageDetector from 'i18next-browser-languagedetector';

// Initialize i18next
i18n
  .use(Backend) // Use the backend plugin to load translation files
  .use(LanguageDetector) // Automatically detect language
  .use(initReactI18next) // Initialize react-i18next
  .init({
    fallbackLng: 'fr', // Default language
    debug: true, // Enable debug logging (optional)
    interpolation: {
      escapeValue: false, // React already does escaping
    },
    react: {
      useSuspense: false, // Avoid suspense if you don’t use it
    },
    backend: {
      loadPath: '/locales/{{lng}}/{{ns}}.json', // Path to load translation files
    },
  });

export default i18n;