import { useEffect, useRef, useState } from "react";
import { ChevronDownIcon } from "@heroicons/react/24/outline";
import clsx from "clsx";
import { ReactNode } from "react";

type DropdownProps = {
  label: string;
  options: { id: number; name: string }[];
  selected: number | undefined;
  onChange: (value: number) => void;
  renderButtonContent?: () => ReactNode;
  showChevron?: boolean;
  buttonClassName?: string;
  menuClassName?: string;
  optionClassName?: string;
  closeOnOutsideClick?: boolean; 
};

const Dropdown = ({
  label,
  options,
  selected,
  onChange,
  renderButtonContent,
  showChevron = true,
  buttonClassName = "",
  menuClassName = "",
  optionClassName = "",
  closeOnOutsideClick = true,
}: DropdownProps) => {
  const [isOpen, setIsOpen] = useState(false);
  const dropdownRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const handleClickOutside = (event: MouseEvent) => {
      if (closeOnOutsideClick && dropdownRef.current && !dropdownRef.current.contains(event.target as Node)) {
        setIsOpen(false);
      }
    };
    document.addEventListener("mousedown", handleClickOutside);
    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, [closeOnOutsideClick]);

  const handleButtonClick = (e: React.MouseEvent<HTMLButtonElement>) => {
    e.stopPropagation();
    setIsOpen(!isOpen);
  };

  return (
    <div className="relative w-full mb-4" ref={dropdownRef}>
      {label && <label className="block text-sm font-medium text-gray-700 mb-2">{label}</label>}
      <button
        type="button"
        onClick={handleButtonClick}
        className={clsx("w-full p-2 rounded flex justify-between items-center border border-gray-300 dark:border-gray-600", buttonClassName)}
      >
        {renderButtonContent
          ? renderButtonContent()
          : selected !== undefined
            ? options.find((option) => option.id === selected)?.name ?? "-"
            : "-"}
            {showChevron && <ChevronDownIcon className="w-5 h-5" />}
      </button>
      {isOpen && (
        <div className={clsx("absolute z-50 right-0 mt-2 w-full max-h-60 overflow-y-auto border border-gray-300 dark:border-gray-600 bg-white dark:bg-gray-800 shadow-lg rounded-md", menuClassName)}>
          {options.map((option) => (
            <button
              key={option.id}
              onClick={() => {
                onChange(option.id);
                setIsOpen(false);
              }}
              className={clsx(
                "w-full px-4 py-2 text-left text-gray-900 dark:text-white hover:bg-gray-100 dark:hover:bg-gray-700",
                { "font-semibold bg-gray-100 dark:bg-gray-700": option.id === selected },
                optionClassName
              )}
            >
              {option.name}
            </button>
          ))}
        </div>
      )}
    </div>
  );
};

export default Dropdown;