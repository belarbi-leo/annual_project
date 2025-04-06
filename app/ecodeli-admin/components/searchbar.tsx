import { XMarkIcon } from '@heroicons/react/24/solid';

interface SearchBarProps {
  value: string;
  onChange: (value: string) => void;
  placeholder?: string;
}

export default function SearchBar({ value, onChange, placeholder }: SearchBarProps) {
  return (
    <div className="w-full">
      <div className="w-full flex items-center border border-gray-300 dark:border-gray-700 rounded bg-white dark:bg-gray-800">
        <input
          type="text"
          value={value}
          onChange={(e) => onChange(e.target.value)}
          placeholder={placeholder || 'Rechercher...'}
          className="flex-grow px-4 py-2 bg-transparent text-gray-900 dark:text-white outline-none"
        />
        {value && (
          <button
            onClick={() => onChange('')}
            className="px-3 text-gray-500 hover:text-gray-700 dark:hover:text-white"
          >
            <XMarkIcon className="w-5 h-5" />
          </button>
        )}
      </div>
    </div>
  );
}