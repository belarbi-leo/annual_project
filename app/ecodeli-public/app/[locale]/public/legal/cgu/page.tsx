import { useTranslations } from "next-intl";
import Header from "@/components/headerNotCo";
import Background from "@/components/background";

export default function CGU() {
  const t = useTranslations("CGU");
  
  const art_struct = [
    { 
      key: "preamble", 
      hasSubArt: false 
    },
    { 
      key: "art1", 
      hasSubArt: false 
    },
    { 
      key: "art2", 
      hasSubArt: true,
      subArt: ["art2_1", "art2_2", "art2_3"]
    },
    { 
      key: "art3", 
      hasSubArt: false 
    },
    { 
      key: "art4", 
      hasSubArt: false 
    },
    { 
      key: "art5", 
      hasSubArt: false 
    },
    { 
      key: "art6", 
      hasSubArt: false 
    },
    { 
      key: "art7", 
      hasSubArt: false 
    },
    { 
      key: "art8", 
      hasSubArt: false 
    },
    { 
      key: "art9", 
      hasSubArt: false 
    },
  ];

  const renderContent = (artkey: string, contentKey: string) => {
    if (contentKey.includes("info") || contentKey.includes("obligation")) {
      return (
        <li key={contentKey}>{t(`${artkey}.${contentKey}`)}</li>
      );
    }
    
    return (
      <p key={contentKey} className={`text-gray-700 dark:text-gray-300 ${!contentKey.endsWith("1") ? "mt-2" : ""}`}>{t(`${artkey}.${contentKey}`)}</p>
    );
  };

  const renderArticle = (artkey: string, isSubArt = false) => {
    try {
      const allKeys = Object.keys(t.raw(`${artkey}`));
      const contentKeys = allKeys.filter(key => key !== "title");
      const listPrefixes = ["info", "obligation"];
      const listGroups: Record<string, string[]> = {};
      
      listPrefixes.forEach(prefix => {
        const listKeys = contentKeys.filter(key => key.startsWith(prefix));
        if (listKeys.length > 0) {
          listGroups[prefix] = listKeys;
        }
      });
      
      const normalContentKeys = contentKeys.filter(key => 
        !listPrefixes.some(prefix => key.startsWith(prefix))
      );

      return (
        <section key={artkey} className="mb-8">
          <h2 className={`${isSubArt ? "text-xl font-semibold text-dark dark:text-white mt-4 mb-2" : "text-2xl font-semibold text-dark dark:text-white mt-8 mb-4"}`}>
            {t(`${artkey}.title`)}
          </h2>
          
          {normalContentKeys.map(key => renderContent(artkey, key))}
          
          {Object.entries(listGroups).map(([prefix, keys]) => (
            <ul key={`${artkey}-${prefix}-list`} className="list-disc pl-5 mt-2 space-y-1">
              {keys.map(key => renderContent(artkey, key))}
            </ul>
          ))}
        </section>
      );
    } catch (error) {
      console.error(`Erreur lors du rendu de l'article ${artkey}:`, error);
      return null;
    }
  };

  return (
    <main className="px-6 py-24 sm:py-32 lg:px-0">
      <div className="mx-auto max-w-7xl md:px-6 lg:px-8 mb-10">
        <div className="mx-auto max-w-3xl mb-12 text-center">
          <h1 className="mb-4 text-4xl font-semibold tracking-tight text-emerald-500 dark:text-emerald-300 sm:text-5xl">
            {t("title")}
          </h1>
          <p className="text-sm text-gray-500">
            {t("effective")}
          </p>
        </div>

        <div className="prose prose-lg prose-emerald mx-auto dark:prose-invert">
          {art_struct.map(art => (
            <div key={art.key}>
              {renderArticle(art.key)}              
              {art.hasSubArt && art.subArt.map(subArt => (
                <div key={subArt} className="mt-6">
                    {renderArticle(subArt, true)}
                </div>
              ))}
            </div>
          ))}
        </div>
      </div>
    </main>
  );
}