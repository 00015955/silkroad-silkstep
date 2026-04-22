import type { Translations } from "./uz";

export const en: Translations = {
  // Navbar
  nav: {
    home: "Home",
    destinations: "Destinations",
    events: "Events",
    aiGuide: "AI Guide",
    signIn: "Sign In",
    joinFree: "Join Free",
  },
  contact: {
  title: "Contact Us",
  phone: "Phone",
  email: "Email",
  hours: "Working Hours",
},

  // Hero Section
  hero: {
    badge: "Discover Central Asia's Hidden Gems",
    title: "Travel the ancient",
    titleHighlight: "Silk Road",
    titleSuffix: "",
    subtitle:
      "Explore vibrant bazaars, breathtaking architecture, and centuries of history across Uzbekistan's most iconic cities.",
    exploreBtn: "Explore destinations",
    aiGuideBtn: "Ask AI guide",
    stats: {
      unescoCities: "UNESCO Cities",
      yearsOfHistory: "Years of History",
      culturalEvents: "Cultural Events",
      travelerRating: "Traveler Rating",
    },
  },

  // Destinations Section
  destinations: {
    badge: "Destinations",
    title: "Iconic Cities of Uzbekistan",
    subtitle: "Three UNESCO World Heritage Sites waiting to be explored",
    unesco: "UNESCO",
    recommended: "Recommended",
    explore: "Explore",
    cities: {
      samarkand: {
        name: "Samarkand",
        description:
          "The crown jewel of the Silk Road, home to Registan Square and Shah-i-Zinda.",
        highlights: ["Registan Square", "Shah-i-Zinda", "Gur-e-Amir"],
        duration: "3-4 days",
      },
      bukhara: {
        name: "Bukhara",
        description:
          "A living museum with over 140 architectural monuments and ancient trading domes.",
        highlights: ["Kalyan Minaret", "Ark Fortress", "Lyab-i-Hauz"],
        duration: "2-3 days",
      },
      khiva: {
        name: "Khiva",
        description:
          "Step into a time capsule at the perfectly preserved Itchan Kala inner city.",
        highlights: ["Itchan Kala", "Kalta Minor", "Tosh-Hovli Palace"],
        duration: "1-2 days",
      },
    },
  },

  // Events Section
  events: {
    badge: "Events",
    title: "Cultural Experiences",
    subtitle: "Immerse yourself in Uzbekistan's vibrant festivals and traditions",
    viewCalendar: "View full events calendar",
    eventsList: {
      navruz: {
        name: "Navruz Festival",
        location: "Samarkand",
        description:
          "Celebrate the Persian New Year with traditional music, dance, and sumalak cooking.",
        tags: ["festival", "culture"],
      },
      silkSpices: {
        name: "Silk & Spices Festival",
        location: "Bukhara",
        description:
          "Experience the revival of ancient Silk Road traditions with artisans and merchants.",
        tags: ["festival", "market"],
      },
      plov: {
        name: "Plov Championship",
        location: "Tashkent",
        description:
          "Watch master chefs compete to create the perfect plov in giant cauldrons.",
        tags: ["food", "competition"],
      },
      asrlar: {
        name: "Asrlar Sadosi",
        location: "Samarkand",
        description:
          "A grand music festival celebrating the echoes of centuries of Central Asian heritage.",
        tags: ["music", "culture"],
      },
    },
    tagNames: {
      festival: "festival",
      culture: "culture",
      market: "market",
      food: "food",
      competition: "competition",
      music: "music",
    },
  },

  // Essentials Section
  essentials: {
    badge: "Travel Tips",
    title: "Travel Essentials",
    subtitle: "Everything you need to know before your journey",
    items: {
      bestTime: {
        title: "Best Time to Visit",
        value: "Spring & Autumn",
        description:
          "March-May and September-November offer pleasant temperatures and vibrant landscapes.",
      },
      money: {
        title: "Money & Payments",
        value: "UZS / Cash",
        description:
          "Uzbekistani Som is the currency. Cash is preferred; cards accepted in major hotels.",
      },
      transport: {
        title: "Getting Around",
        value: "Afrosiyob Train",
        description:
          "High-speed train connects Tashkent, Samarkand, and Bukhara in comfort.",
      },
      etiquette: {
        title: "Cultural Etiquette",
        value: "Modest Dress",
        description:
          "Cover shoulders and knees when visiting mosques and religious sites.",
      },
      food: {
        title: "Must-Try Food",
        value: "Plov & Samsa",
        description:
          "National rice dish and savory pastries are essential Uzbek culinary experiences.",
      },
      connectivity: {
        title: "Connectivity",
        value: "Ucell / Beeline",
        description:
          "Local SIM cards offer affordable data. Available at airports and shops.",
      },
    },
  },

  // Footer
  footer: {
    description:
      "Your gateway to exploring the ancient wonders of Uzbekistan and the Silk Road.",
    destinations: "Destinations",
    resources: "Resources",
    company: "Company",
    links: {
      samarkand: "Samarkand",
      bukhara: "Bukhara",
      khiva: "Khiva",
      tashkent: "Tashkent",
      travelGuide: "Travel Guide",
      eventsCalendar: "Events Calendar",
      aiAssistant: "AI Assistant",
      blog: "Blog",
      aboutUs: "About Us",
      contact: "Contact",
      privacyPolicy: "Privacy Policy",
      termsOfService: "Terms of Service",
    },
    copyright: "SilkStep. All rights reserved.",
    madeWith: "Made with care for travelers exploring Uzbekistan",
  },
};
