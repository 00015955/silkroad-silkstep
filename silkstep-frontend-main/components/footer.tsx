"use client";

import Link from "next/link";
import {
  Phone,
  Mail,
  Clock,
  Facebook,
  Twitter,
  Instagram,
  Linkedin,
  MapPin,
} from "lucide-react";
import { useTranslations } from "@/lib/i18n";

export function Footer() {
  const t = useTranslations();

  const footerLinks = {
    destinations: [
      { href: "/destinations/samarkand", label: t.footer.links.samarkand },
      { href: "/destinations/bukhara", label: t.footer.links.bukhara },
      { href: "/destinations/khiva", label: t.footer.links.khiva },
      { href: "/destinations/tashkent", label: t.footer.links.tashkent },
    ],
    resources: [
      { href: "#travel-guide", label: t.footer.links.travelGuide },
      { href: "#events", label: t.footer.links.eventsCalendar },
      { href: "#ai-guide", label: t.footer.links.aiAssistant },
      { href: "#blog", label: t.footer.links.blog },
    ],
    company: [
      { href: "#about", label: t.footer.links.aboutUs },
      { href: "#contact", label: t.footer.links.contact },
      { href: "#privacy", label: t.footer.links.privacyPolicy },
      { href: "#terms", label: t.footer.links.termsOfService },
    ],
  };

  const socialLinks = [
    { icon: Facebook, href: "#", label: "Facebook" },
    { icon: Twitter, href: "#", label: "Twitter" },
    { icon: Instagram, href: "#", label: "Instagram" },
    { icon: Linkedin, href: "#", label: "LinkedIn" },
  ];

  // Contact info with translations
  const contactInfo = {
    phone: "+998 90 906 23 53",
    email: "00015955wiut@gmail.com",
    hours: t.footer.contact?.hours || "Mon - Fri: 9:00 - 18:00",
  };

  return (
    <footer className="border-t border-gray-200 bg-gray-50">
      <div className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8 py-12 md:py-16">
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-5 gap-8">
          {/* Brand */}
          <div className="lg:col-span-1">
            <Link href="/" className="flex items-center gap-2 mb-4">
              <div className="h-9 w-9 rounded-full bg-gradient-to-r from-[#2F6F7E] to-[#1a4a55] flex items-center justify-center shadow-md">
                <span className="text-white font-serif font-bold text-sm">
                  S
                </span>
              </div>
              <span className="font-serif text-xl font-semibold text-gray-900 whitespace-nowrap">
                Silkroad
              </span>
            </Link>
            <p className="text-sm text-gray-500 leading-relaxed mb-4">
              {t.footer.description}
            </p>
            {/* Social Links */}
            <div className="flex gap-3">
              {socialLinks.map((social) => {
                const Icon = social.icon;
                return (
                  <a
                    key={social.label}
                    href={social.href}
                    target="_blank"
                    rel="noopener noreferrer"
                    className="w-9 h-9 rounded-full bg-white border border-gray-200 flex items-center justify-center text-gray-500 hover:text-[#2F6F7E] hover:border-[#2F6F7E] transition-all duration-300"
                    aria-label={social.label}
                  >
                    <Icon className="w-4 h-4" />
                  </a>
                );
              })}
            </div>
          </div>

          {/* Destinations */}
          <div>
            <h4 className="font-semibold text-gray-900 mb-4 whitespace-nowrap">
              {t.footer.destinations}
            </h4>
            <ul className="space-y-3">
              {footerLinks.destinations.map((link) => (
                <li key={link.label}>
                  <Link
                    href={link.href}
                    className="text-sm text-gray-500 hover:text-[#2F6F7E] transition-colors whitespace-nowrap"
                  >
                    {link.label}
                  </Link>
                </li>
              ))}
            </ul>
          </div>

          {/* Resources */}
          <div>
            <h4 className="font-semibold text-gray-900 mb-4 whitespace-nowrap">
              {t.footer.resources}
            </h4>
            <ul className="space-y-3">
              {footerLinks.resources.map((link) => (
                <li key={link.label}>
                  <Link
                    href={link.href}
                    className="text-sm text-gray-500 hover:text-[#2F6F7E] transition-colors whitespace-nowrap"
                  >
                    {link.label}
                  </Link>
                </li>
              ))}
            </ul>
          </div>

          {/* Company */}
          <div>
            <h4 className="font-semibold text-gray-900 mb-4 whitespace-nowrap">
              {t.footer.company}
            </h4>
            <ul className="space-y-3">
              {footerLinks.company.map((link) => (
                <li key={link.label}>
                  <Link
                    href={link.href}
                    className="text-sm text-gray-500 hover:text-[#2F6F7E] transition-colors whitespace-nowrap"
                  >
                    {link.label}
                  </Link>
                </li>
              ))}
            </ul>
          </div>

          {/* Contact Info */}
          <div>
            <h4 className="font-semibold text-gray-900 mb-4 whitespace-nowrap"></h4>
            <ul className="space-y-4">
              <li className="flex items-center gap-3">
                <div className="w-10 h-10 rounded-full bg-[#2F6F7E]/10 flex items-center justify-center flex-shrink-0">
                  <Phone className="w-5 h-5 text-[#2F6F7E]" />
                </div>
                <a
                  href="tel:+998909062353"
                  className="text-sm text-gray-700 hover:text-[#2F6F7E] transition-colors"
                >
                  +998 90 906 23 53
                </a>
              </li>
             <li className="flex items-center gap-3">
  <div className="w-10 h-10 rounded-full bg-[#2F6F7E]/10 flex items-center justify-center flex-shrink-0">
    <Mail className="w-5 h-5 text-[#2F6F7E]" />
  </div>

  <a
    href="mailto:00015955wiut@gmail.com"
    className="text-sm text-gray-700 hover:text-[#2F6F7E] transition-colors whitespace-nowrap"
  >
    00015955wiut@gmail.com
  </a>
</li>
            </ul>
          </div>
        </div>

        {/* Bottom Bar */}
        <div className="mt-12 pt-8 border-t border-gray-200 flex flex-col md:flex-row items-center justify-between gap-4">
          <p className="text-sm text-gray-400 whitespace-nowrap">
            &copy; {new Date().getFullYear()} {t.footer.copyright}
          </p>
          <div className="flex items-center gap-4 flex-wrap justify-center">
            <Link
              href="#"
              className="text-xs text-gray-400 hover:text-[#2F6F7E] transition-colors whitespace-nowrap"
            >
              Privacy Policy
            </Link>
            <span className="text-gray-300">|</span>
            <Link
              href="#"
              className="text-xs text-gray-400 hover:text-[#2F6F7E] transition-colors whitespace-nowrap"
            >
              Terms of Service
            </Link>
          </div>
          <p className="text-sm text-gray-400 whitespace-nowrap">
            {t.footer.madeWith}
          </p>
        </div>
      </div>
    </footer>
  );
}

// This component is designed to be a comprehensive footer for the Silkroad website, incorporating multiple sections such as brand information, navigation links, social media icons, and contact details. 
//It uses Tailwind CSS for styling and is fully responsive, ensuring it looks great on all screen sizes. 
//The content is also internationalized using the `useTranslations` hook, allowing for easy localization in different languages.