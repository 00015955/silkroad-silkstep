"use client";

import { useState, useEffect, useCallback } from "react";
import Image from "next/image";
import Link from "next/link";
import { Star, Clock, Shield, ArrowRight, ChevronDown, ChevronUp } from "lucide-react";
import { Card, CardContent } from "@/components/ui/card";
import { Badge } from "@/components/ui/badge";
import { useI18n } from "@/lib/i18n";
import { getImageUrl, fetchDestinations } from "@/lib/baseurl";

interface Destination {
  id: string;
  slug: string;
  name: string;
  description: string;
  imageUrl: string;
  rating: number;
  recommendedDays: string;
}

export function DestinationsSection() {
  const { locale, t } = useI18n();
  const [destinations, setDestinations] = useState<Destination[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [showAll, setShowAll] = useState(false);
  const INITIAL_VISIBLE = 3;

  const loadDestinations = useCallback(async () => {
    setLoading(true);
    setError("");
    try {
      const data = await fetchDestinations(locale);
      console.log("Fetched destinations:", data);
      setDestinations(data || []);
    } catch (err) {
      console.error("Failed to fetch destinations:", err);
      setError("Unable to load destinations");
    } finally {
      setLoading(false);
    }
  }, [locale]);

  useEffect(() => {
    loadDestinations();
  }, [loadDestinations]);

  const destT = t.destinations;

  // Filter invalid destinations
  const validDestinations = destinations.filter(
    (dest) => dest.name && dest.name !== "." && dest.name.trim() !== ""
  );

  const visibleDestinations = showAll ? validDestinations : validDestinations.slice(0, INITIAL_VISIBLE);
  const hasMoreDestinations = validDestinations.length > INITIAL_VISIBLE;

  if (loading) {
    return (
      <section className="py-20 md:py-32 bg-sand-light/30">
        <div className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-16">
            <Badge variant="secondary" className="mb-4 rounded-full px-4 py-1 bg-sand-light text-muted-foreground">
              {destT.badge}
            </Badge>
            <h2 className="font-serif text-3xl sm:text-4xl md:text-5xl font-medium text-foreground text-balance">
              {destT.title}
            </h2>
            <p className="mt-4 text-lg text-muted-foreground max-w-2xl mx-auto">
              {destT.subtitle}
            </p>
          </div>
          <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
            {[1, 2, 3].map((i) => (
              <div key={i} className="bg-white rounded-3xl overflow-hidden animate-pulse shadow-sm">
                <div className="h-56 bg-gray-200" />
                <div className="p-6 space-y-3">
                  <div className="h-6 bg-gray-200 rounded w-3/4" />
                  <div className="h-4 bg-gray-200 rounded w-full" />
                  <div className="h-4 bg-gray-200 rounded w-2/3" />
                </div>
              </div>
            ))}
          </div>
        </div>
      </section>
    );
  }

  if (error) {
    return (
      <section className="py-20 md:py-32 bg-sand-light/30">
        <div className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8 text-center">
          <p className="text-red-500">{error}</p>
          <button
            onClick={() => loadDestinations()}
            className="mt-4 px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 transition"
          >
            Try Again
          </button>
        </div>
      </section>
    );
  }

  return (
    <section id="destinations" className="py-20 md:py-32 bg-sand-light/30">
      <div className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
        {/* Section Header */}
        <div className="text-center mb-16">
          <Badge variant="secondary" className="mb-4 rounded-full px-4 py-1 bg-sand-light text-muted-foreground">
            {destT.badge}
          </Badge>
          <h2 className="font-serif text-3xl sm:text-4xl md:text-5xl font-medium text-foreground text-balance">
            {destT.title}
          </h2>
          <p className="mt-4 text-lg text-muted-foreground max-w-2xl mx-auto">
            {destT.subtitle}
          </p>
        </div>

        {/* Destinations Grid */}
        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
          {visibleDestinations.map((destination) => {
            const imageUrl = getImageUrl(destination.imageUrl);
            const rating = destination.rating || 0;
            const recommendedDays = destination.recommendedDays || "";
            const isUnesco = rating >= 4.7 || 
              destination.name === "Bukhara" || 
              destination.name === "Samarkand" || 
              destination.name === "Khiva" ||
              destination.name === "Khorezm";

            let slug = destination.slug;
            if (!slug || slug === "") {
              slug = destination.name.toLowerCase().replace(/\s+/g, "-");
            }
            const formattedSlug = slug.toLowerCase().replace(/\s+/g, "-");

            return (
              <Link 
                key={destination.id} 
                href={`/destinations/${formattedSlug}`}
                prefetch={true}
              >
                <Card className="group overflow-hidden bg-white border-gray-200 hover:shadow-xl transition-all duration-300 rounded-3xl cursor-pointer h-full">
                  {/* Image */}
                  <div className="relative h-56 overflow-hidden bg-gray-100">
                    <div className="absolute inset-0 bg-gradient-to-t from-black/50 via-transparent to-transparent z-10" />
                    
                    {isUnesco && (
                      <div className="absolute top-4 left-4 z-20 flex items-center gap-1.5 bg-white/90 backdrop-blur-sm rounded-full px-3 py-1.5">
                        <Shield className="h-3.5 w-3.5 text-blue-600" />
                        <span className="text-xs font-medium text-gray-900">{destT.unesco}</span>
                      </div>
                    )}
                    
                    <Image
                      src={imageUrl}
                      alt={destination.name}
                      fill
                      sizes="(max-width: 768px) 100vw, (max-width: 1200px) 50vw, 33vw"
                      className="object-cover group-hover:scale-105 transition-transform duration-500"
                      priority={false}
                      onError={(e) => {
                        const target = e.target as HTMLImageElement;
                        target.src = "https://via.placeholder.com/400x300?text=No+Image";
                      }}
                    />
                  </div>

                  <CardContent className="p-6">
                    <div className="flex items-start justify-between mb-3">
                      <h3 className="font-serif text-xl md:text-2xl font-semibold text-gray-900 group-hover:text-blue-600 transition-colors line-clamp-1">
                        {destination.name}
                      </h3>
                      {rating > 0 && (
                        <div className="flex items-center gap-1 text-sm flex-shrink-0 ml-2">
                          <Star className="h-4 w-4 fill-amber-500 text-amber-500" />
                          <span className="font-medium text-gray-900">{rating.toFixed(1)}</span>
                        </div>
                      )}
                    </div>

                    <p className="text-gray-500 text-sm leading-relaxed mb-4 line-clamp-2">
                      {destination.description}
                    </p>

                    {recommendedDays && (
                      <div className="flex items-center justify-between pt-4 border-t border-gray-100">
                        <div className="flex items-center gap-2 text-sm text-gray-500">
                          <Clock className="h-4 w-4" />
                          <span>{destT.recommended}: {recommendedDays} {destT.days}</span>
                        </div>
                        <span className="flex items-center gap-1 text-sm font-medium text-blue-600 group-hover:gap-2 transition-all">
                          {destT.explore}
                          <ArrowRight className="h-4 w-4" />
                        </span>
                      </div>
                    )}
                  </CardContent>
                </Card>
              </Link>
            );
          })}
        </div>

        {/* Show More / Show Less Button */}
        {hasMoreDestinations && (
          <div className="text-center mt-10">
            <button
              onClick={() => setShowAll(!showAll)}
              className="inline-flex items-center gap-2 px-6 py-2.5 bg-white border border-gray-300 rounded-full text-sm font-medium text-gray-700 hover:bg-gray-50 hover:border-[#2F6F7E] hover:text-[#2F6F7E] transition-all duration-300"
            >
              {showAll ? (
                <>
                  <ChevronUp className="w-4 h-4" />
                  Show Less
                </>
              ) : (
                <>
                  <ChevronDown className="w-4 h-4" />
                  Show More ({validDestinations.length - INITIAL_VISIBLE} more)
                </>
              )}
            </button>
          </div>
        )}
      </div>
    </section>
  );
}