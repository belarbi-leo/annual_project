export default function DashboardPage() {
    return (
      <div className="bg-white dark:bg-gray-800 p-6 rounded-lg shadow-md">
        <h2 className="text-2xl font-semibold text-gray-900 dark:text-white">Bienvenue sur votre tableau de bord !</h2>
        <p className="mt-4 text-gray-600 dark:text-gray-300">
          Repartition abonnements.
        </p>
        <p className="mt-4 text-gray-600 dark:text-gray-300">
          Top préstations préscrites.
        </p>
        <p className="mt-4 text-gray-600 dark:text-gray-300">
          Nombres d'utilisateurs connectés.
        </p>
      </div>
    );
  }  