"use client";

import { useState } from "react";
import { Sparkles, ArrowRight } from "lucide-react";
import { Button } from "@/components/ui/button";
import { useTranslations } from "@/lib/i18n";
import { AIChat } from "./ai-chat";

export function HeroSection() {
  const t = useTranslations();
  const [isChatOpen, setIsChatOpen] = useState(false);

  const stats = [
    { value: "3", label: t.hero.stats.unescoCities },
    { value: "2000+", label: t.hero.stats.yearsOfHistory },
    { value: "50+", label: t.hero.stats.culturalEvents },
    { value: "4.9", label: t.hero.stats.travelerRating },
  ];

  const scrollToDestinations = () => {
    const destinationsSection = document.getElementById("destinations");
    if (destinationsSection) {
      destinationsSection.scrollIntoView({ behavior: "smooth" });
    }
  };

  const openAIChat = () => {
    setIsChatOpen(true);
  };

  return (
    <>
      <section className="relative min-h-screen flex items-center justify-center pt-16 overflow-hidden">
        {/* Background Pattern */}
        <div className="absolute inset-0 bg-[url('/pattern.svg')] opacity-5" />
        
        {/* Content */}
        <div className="relative z-10 mx-auto max-w-7xl px-4 sm:px-6 lg:px-8 py-20 text-center">
          {/* Badge */}
          <div className="inline-flex items-center gap-2 rounded-full bg-sand-light px-4 py-1.5 text-sm text-muted-foreground mb-8">
            <span className="h-2 w-2 rounded-full bg-azure animate-pulse" />
            {t.hero.badge}
          </div>

          {/* Headline */}
          <h1 className="font-serif text-4xl sm:text-5xl md:text-6xl lg:text-7xl font-medium text-foreground leading-tight text-balance max-w-4xl mx-auto">
            {t.hero.title}{" "}
            <span className="text-azure">{t.hero.titleHighlight}</span>
            {t.hero.titleSuffix && ` ${t.hero.titleSuffix}`}
          </h1>

          {/* Subtext */}
          <p className="mt-6 text-lg sm:text-xl text-muted-foreground max-w-2xl mx-auto leading-relaxed text-pretty">
            {t.hero.subtitle}
          </p>

          {/* CTA Buttons */}
          <div className="mt-10 flex flex-col sm:flex-row items-center justify-center gap-4">
            <Button 
              size="lg" 
              onClick={scrollToDestinations}
              className="bg-azure hover:bg-azure-light text-primary-foreground rounded-full px-8 h-12 text-base font-medium gap-2 group cursor-pointer"
            >
              {t.hero.exploreBtn}
              <ArrowRight className="h-4 w-4 transition-transform group-hover:translate-x-1" />
            </Button>
            <Button 
              variant="outline" 
              size="lg" 
              onClick={openAIChat}
              className="rounded-full px-8 h-12 text-base font-medium gap-2 border-border hover:bg-sand-light cursor-pointer"
            >
              <Sparkles className="h-4 w-4 text-gold" />
              {t.hero.aiGuideBtn}
            </Button>
          </div>

          {/* Stats */}
          <div className="mt-20 grid grid-cols-2 md:grid-cols-4 gap-8 max-w-3xl mx-auto">
            {stats.map((stat) => (
              <div key={stat.label} className="text-center">
                <div className="font-serif text-3xl md:text-4xl font-semibold text-azure">{stat.value}</div>
                <div className="mt-1 text-sm text-muted-foreground">{stat.label}</div>
              </div>
            ))}
          </div>
        </div>

        {/* Decorative Elements */}
        <div className="absolute bottom-0 left-0 right-0 h-32 bg-gradient-to-t from-background to-transparent" />
      </section>

      {/* AI Chat Component */}
      <AIChat 
        isOpen={isChatOpen} 
        onClose={() => setIsChatOpen(false)} 
      />
    </>
  );
}