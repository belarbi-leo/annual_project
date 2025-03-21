export default function ForgotPassword() {
  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100 dark:bg-gray-900">
      <div className="max-w-md w-full bg-white dark:bg-gray-800 p-8 rounded-lg shadow-md">
        <h2 className="text-2xl font-semibold text-gray-900 dark:text-white mb-6">Mot de passe oublié</h2>
        <form>
          <div className="mb-4">
            <label htmlFor="email" className="block text-sm font-medium text-gray-700 dark:text-gray-300">
              Adresse e-mail
            </label>
            <input type="email" id="email" className="mt-1 block w-full px-3 py-2 bg-white dark:bg-gray-700 border border-gray-300 dark:border-gray-600 rounded-md shadow-sm focus:outline-none focus:ring-[#49cb5c] focus:border-[#49cb5c] dark:focus:ring-[#36a84b] dark:focus:border-[#36a84b] sm:text-sm"/>
          </div>
          <button type="submit" className="w-full py-2 px-4 bg-[#49cb5c] dark:bg-[#36a84b] text-white font-semibold rounded-md shadow-md hover:bg-[#07b128] dark:hover:bg-[#2e8c40] focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-[#49cb5c] dark:focus:ring-[#36a84b]">
            Envoyer le lien de réinitialisation
          </button>
        </form>
        <p className="mt-4 text-sm text-gray-600 dark:text-gray-400"></p>        
        <div className="mt-6 text-center">
            <a className="text-sm text-[#49cb5c] dark:text-[#36a84b] hover:underline">Retour à la connexion</a>
        </div>
      </div>
    </div>
  );
}