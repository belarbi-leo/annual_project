import { useTranslations } from "next-intl";
import Header from "@/components/header";
import Background from "@/components/background";

export default function CGV() {
  const t = useTranslations("CGV");
  
  const art_struct = [
    {
      key: "art1",
      hasSubArt: false
    },
    {
      key: "art2",
      hasSubArt: false
    },
    {
      key: "art3",
      hasSubArt: false
    },
    {
      key: "art4",
      hasSubArt: true,
      subArt: ["art4.free", "art4.fees", "art4.payment", "art4.invoice"]
    },
    {
      key: "art5",
      hasSubArt: true,
      subArt: ["art5.client", "art5.provider", "art5.ecodeli"]
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
    {
      key: "art10",
      hasSubArt: false
    }
  ];

  const renderContent = (artkey: string, contentKey: string) => {
    if (contentKey.includes("service") || contentKey.includes("obligation") || contentKey.includes("limit")) {
      return (
        <li key={contentKey}>{t(`${artkey}.${contentKey}`)}</li>
      );
    }
    
    return (
      <p key={contentKey} className={`text-gray-700 dark:text-gray-300 ${!contentKey.endsWith("1") ? "mt-2" : ""}`}>
        {t(`${artkey}.${contentKey}`)}
      </p>
    );
  };

  const renderArticle = (artkey: string, isSubArt = false) => {
    try {
      const allKeys = Object.keys(t.raw(`${artkey}`));
      const contentKeys = allKeys.filter(key => 
        key !== "title" && 
        key !== "intro" && 
        !key.includes(".") && 
        typeof t.raw(`${artkey}.${key}`) !== "object"
      );
      const listPrefixes = ["service", "obligation", "limit"];
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
          
          {allKeys.includes("intro") && (
            <p className="text-gray-700 dark:text-gray-300 mb-2">
              {t(`${artkey}.intro`)}
            </p>
          )}
          
          {normalContentKeys.map(key => renderContent(artkey, key))}
          {Object.entries(listGroups).map(([prefix, keys]) => (
            <ul key={`${artkey}-${prefix}-list`} className="list-disc pl-5 mt-2 space-y-1">
              {keys.sort().map(key => renderContent(artkey, key))}
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
    <div className="min-h-screen w-full overflow-hidden relative px-6 py-24 sm:py-32 lg:overflow-visible lg:px-0">
      <Background />
      <Header />
      <div className="mx-auto max-w-7xl md:px-6 lg:px-8 mb-10">
        <div className="mx-auto max-w-3xl mb-12 text-center">
          <h1 className="mb-4 text-4xl font-semibold tracking-tight text-emerald-500 dark:text-emerald-300 sm:text-5xl">{t("title")}</h1>
          <p className="text-sm text-gray-500">{t("effective")}</p>
        </div>

        <div className="prose prose-lg prose-emerald mx-auto dark:prose-invert">
          <p className="text-gray-700 text-center dark:text-gray-300 mb-8">{t("introduction")}</p>
          
          {art_struct.map(art => (
            <div key={art.key}>
              {renderArticle(art.key)}              
              {art.hasSubArt && art.subArt && art.subArt.map(subArt => (
                <div key={subArt} className="mt-6">
                  {renderArticle(subArt, true)}
                </div>
              ))}
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}