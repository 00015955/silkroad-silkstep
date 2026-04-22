"use client";

import { useState, useEffect, useRef } from "react";
import { ChevronDown } from "lucide-react";
import { useI18n, locales, type Locale } from "@/lib/i18n";
import ReactCountryFlag from "react-country-flag";

const flags: Record<Locale, string> = {
  uz: "UZ",
  en: "GB",
  ru: "RU",
};

const localeLabels: Record<Locale, string> = {
  uz: "UZB",
  en: "ENG",
  ru: "РУС",
};

export function LanguageSwitcher() {
  const { locale, setLocale } = useI18n();
  const [isOpen, setIsOpen] = useState(false);
  const dropdownRef = useRef<HTMLDivElement>(null);

  // Tashqarini bosganda yopish
  useEffect(() => {
    const handleClickOutside = (event: MouseEvent) => {
      if (dropdownRef.current && !dropdownRef.current.contains(event.target as Node)) {
        setIsOpen(false);
      }
    };

    if (isOpen) {
      document.addEventListener("mousedown", handleClickOutside);
    }

    return () => {
      document.removeEventListener("mousedown", handleClickOutside);
    };
  }, [isOpen]);

  return (
    <div className="relative" ref={dropdownRef}>
      <button
        onClick={() => setIsOpen(!isOpen)}
        className="flex items-center gap-2 px-3 py-1.5 bg-white border border-gray-200 rounded-lg text-sm font-medium hover:bg-gray-50 transition-colors"
      >
        <ReactCountryFlag
          countryCode={flags[locale]}
          svg
          style={{
            width: "1.2em",
            height: "1.2em",
          }}
        />
        <span>{localeLabels[locale]}</span>
        <ChevronDown className={`w-4 h-4 transition-transform ${isOpen ? "rotate-180" : ""}`} />
      </button>

      {isOpen && (
        <div className="absolute right-0 mt-2 w-36 bg-white border border-gray-200 rounded-lg shadow-lg z-20 overflow-hidden">
          {locales.map((loc) => (
            <button
              key={loc}
              onClick={() => {
                setLocale(loc as Locale);
                setIsOpen(false);
              }}
              className={`w-full flex items-center gap-2 px-3 py-2 text-sm transition-colors ${
                locale === loc
                  ? "bg-[#2F6F7E] text-white"
                  : "hover:bg-gray-50 text-gray-700"
              }`}
            >
              <ReactCountryFlag
                countryCode={flags[loc as Locale]}
                svg
                style={{
                  width: "1.2em",
                  height: "1.2em",
                }}
              />
              <span>{localeLabels[loc as Locale]}</span>
            </button>
          ))}
        </div>
      )}
    </div>
  );
}