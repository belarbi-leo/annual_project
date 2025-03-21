import {
  ArrowPathIcon,
  CloudArrowUpIcon,
  FingerPrintIcon,
  LockClosedIcon,
} from "@heroicons/react/24/outline";
import { ServerIcon } from "@heroicons/react/20/solid";

const features = [
  {
    name: "Trajets optimisés",
    description:
      "EcoDeli exploite les trajets déjà prévus par des particuliers et professionnels pour assurer vos livraisons, réduisant coûts et impact écologique.",
    icon: CloudArrowUpIcon,
  },
  {
    name: "Réseau de confiance",
    description:
      "Tous nos prestataires sont vérifiés et notés, garantissant un service fiable et sécurisé pour chaque mission.",
    icon: LockClosedIcon,
  },
  {
    name: "Services diversifiés",
    description:
      "Au-delà des livraisons, accédez à notre réseau pour le transport, l'installation, l'entretien et bien d'autres services du quotidien.",
    icon: ArrowPathIcon,
  },
  {
    name: "Impact positif",
    description:
      "En utilisant des trajets existants, chaque service via EcoDeli contribue activement à réduire les émissions de CO2 inutiles.",
    icon: FingerPrintIcon,
  },
];

export default function About() {
  return (
    <div className="relative isolate overflow-hidden bg-white px-6 py-24 sm:py-32 lg:overflow-visible lg:px-0">

      {/* Effet de fond supérieur gauche */}
      <div
        aria-hidden="true"
        className="absolute top-[-10%] left-[-10%] -z-10 transform-gpu blur-3xl opacity-50"
      >
        <div
          className="h-100 w-100 bg-gradient-to-tr from-[#89c8fd] to-[#60b6ff] dark:from-[#245b90] dark:to-[#1a426a] rounded-full"
          style={{ filter: "blur(100px)" }}
        />
      </div>

      {/* Effet de fond inférieur droit */}
      <div
        aria-hidden="true"
        className="absolute bottom-[-10%] right-[-10%] -z-10 transform-gpu blur-3xl opacity-50"
      >
        <div
          className="h-100 w-100 bg-gradient-to-tr from-[#96d629] to-[#baeb6c] dark:from-[#3f7d1c] dark:to-[#67a731] rounded-full"
          style={{ filter: "blur(100px)" }}
        />
      </div>

      <div className="mx-auto max-w-7xl px-6 lg:px-8 mb-10">
        <div className="mx-auto max-w-2xl lg:text-center">
          <h1 className="mt-2 text-3xl font-semibold tracking-tight text-[#49cb5c] dark:text-[#36a84b] sm:text-5xl">
            Connecter besoins et services pour un avenir durable
          </h1>
          <p className="mt-6 text-lg/8 text-gray-600">
            EcoDeli met en relation clients et prestataires pour optimiser les
            livraisons et services en exploitant les trajets déjà planifiés,
            pour une économie plus collaborative et écologique.
          </p>
        </div>
        <div className="mx-auto mt-16 max-w-2xl sm:mt-20 lg:mt-24 lg:max-w-4xl">
          <dl className="grid max-w-xl grid-cols-1 gap-x-8 gap-y-10 lg:max-w-none lg:grid-cols-2 lg:gap-y-16">
            {features.map((feature) => (
              <div key={feature.name} className="relative pl-16">
                <dt className="text-base/7 font-semibold text-gray-900">
                  <div className="absolute top-0 left-0 flex size-10 items-center justify-center rounded-lg bg-[#49cb5c]">
                    <feature.icon
                      aria-hidden="true"
                      className="size-6 text-white"
                    />
                  </div>
                  {feature.name}
                </dt>
                <dd className="mt-2 text-base/7 text-gray-600">
                  {feature.description}
                </dd>
              </div>
            ))}
          </dl>
        </div>
      </div>
      <div className="mx-auto grid max-w-2xl grid-cols-1 gap-x-8 gap-y-16 lg:mx-0 lg:max-w-none lg:grid-cols-2 lg:items-start lg:gap-y-10">
        <div className="lg:col-span-2 lg:col-start-1 lg:row-start-1 lg:mx-auto lg:grid lg:w-full lg:max-w-7xl lg:grid-cols-2 lg:gap-x-8 lg:px-8">
          <div className="lg:pr-4">
            <div className="lg:max-w-lg">
              <p className="text-base/7 font-semibold text-green-600">
                Économie collaborative
              </p>
              <h1 className="mt-2 text-4xl font-semibold tracking-tight text-pretty text-gray-900 sm:text-5xl">
                Une mise en relation intelligente
              </h1>
              <p className="mt-6 text-xl/8 text-gray-700">
                Notre plateforme connecte ceux qui ont besoin d'un service avec
                ceux qui effectuent déjà le trajet, créant un réseau d'entraide
                efficace qui profite à tous et à la planète.
              </p>
            </div>
          </div>
        </div>
        <div className="-mt-12 -ml-12 p-12 lg:sticky lg:top-4 lg:col-start-2 lg:row-span-2 lg:row-start-1 lg:overflow-hidden">
          <img
            alt="Capture d'écran de l'application EcoDeli"
            src="https://tailwindcss.com/plus-assets/img/component-images/dark-project-app-screenshot.png"
            className="w-[48rem] max-w-none rounded-xl bg-gray-900 ring-1 shadow-xl ring-gray-400/10 sm:w-[57rem]"
          />
        </div>
        <div className="lg:col-span-2 lg:col-start-1 lg:row-start-2 lg:mx-auto lg:grid lg:w-full lg:max-w-7xl lg:grid-cols-2 lg:gap-x-8 lg:px-8">
          <div className="lg:pr-4">
            <div className="max-w-xl text-base/7 text-gray-700 lg:max-w-lg">
              <p>
                EcoDeli repense les services de proximité en tirant parti des
                trajets déjà prévus. Notre plateforme identifie les prestataires
                qui passent près de chez vous et peuvent effectuer votre
                livraison ou service pendant leur déplacement habituel.
              </p>
              <ul role="list" className="mt-8 space-y-8 text-gray-600">
                <li className="flex gap-x-3">
                  <CloudArrowUpIcon
                    aria-hidden="true"
                    className="mt-1 size-5 flex-none text-green-600"
                  />
                  <span>
                    <strong className="font-semibold text-gray-900">
                      Économies substantielles.
                    </strong>{" "}
                    En exploitant les trajets existants, nous réduisons les
                    coûts de 25 à 40% par rapport aux services traditionnels.
                  </span>
                </li>
                <li className="flex gap-x-3">
                  <LockClosedIcon
                    aria-hidden="true"
                    className="mt-1 size-5 flex-none text-green-600"
                  />
                  <span>
                    <strong className="font-semibold text-gray-900">
                      Multi-services.
                    </strong>{" "}
                    Des livraisons aux installations, en passant par les
                    services d'entretien et le transport, trouvez le prestataire
                    idéal pour chaque besoin.
                  </span>
                </li>
                <li className="flex gap-x-3">
                  <ServerIcon
                    aria-hidden="true"
                    className="mt-1 size-5 flex-none text-green-600"
                  />
                  <span>
                    <strong className="font-semibold text-gray-900">
                      Impact environnemental réduit.
                    </strong>{" "}
                    Chaque service via EcoDeli évite un déplacement
                    supplémentaire, contribuant directement à la réduction des
                    émissions de CO2.
                  </span>
                </li>
              </ul>
              <p className="mt-8">
                Que vous soyez client à la recherche d'un service ou prestataire
                souhaitant rentabiliser vos déplacements, EcoDeli vous connecte
                au sein d'un écosystème vertueux et responsable.
              </p>
              <h2 className="mt-16 text-2xl font-bold tracking-tight text-gray-900">
                Rejoignez la communauté EcoDeli
              </h2>
              <p className="mt-6">
                Inscrivez-vous en quelques minutes et découvrez comment notre
                réseau peut vous faire économiser du temps, de l'argent et
                participer à la construction d'un modèle de services plus
                durable et plus humain.
              </p>
            </div>
          </div>
        </div>
      </div>
      <div className="mt-8 flex justify-center">
        <a
        href="/signup"
        className="inline-flex items-center justify-center rounded-md border border-transparent bg-[#49cb5c] px-6 py-3 text-base font-medium text-white shadow-sm hover:bg-[#36a84b]"
        >
        S'inscrire
        </a>
        <a
        href="/login"
        className="ml-4 inline-flex items-center justify-center rounded-md border border-transparent bg-gray-600 px-6 py-3 text-base font-medium text-white shadow-sm hover:bg-gray-700"
        >
        Se connecter
        </a>
      </div>
    </div>
  );
}

