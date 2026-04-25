"use client";

import { useState, useEffect, useCallback } from "react";
import { MapPin, Calendar, ChevronDown, ChevronUp } from "lucide-react";
import { Card, CardContent } from "@/components/ui/card";
import { Badge } from "@/components/ui/badge";
import { useI18n } from "@/lib/i18n";
import { fetchWithLang } from "@/lib/baseurl";

interface Event {
  id: string;
  name: string;
  description: string;
  imageUrl: string;
  eventDate: string;
  location: string;
  nameUz?: string;
  nameRu?: string;
  nameEn?: string;
  descriptionUz?: string;
  descriptionRu?: string;
  descriptionEn?: string;
}

interface EventTag {
  id: string;
  tag: string;
}

// Tag color mapping
const getTagColor = (tag: string): string => {
  const tagLower = tag?.toLowerCase() || "";
  
  if (tagLower.includes('festival') || tagLower.includes('bayram')) {
    return "bg-terracotta/20 text-terracotta";
  }
  if (tagLower.includes('culture') || tagLower.includes('madaniyat')) {
    return "bg-azure/20 text-azure";
  }
  if (tagLower.includes('market') || tagLower.includes('bozor')) {
    return "bg-gold/20 text-gold";
  }
  if (tagLower.includes('food') || tagLower.includes('taom')) {
    return "bg-gold/20 text-gold";
  }
  if (tagLower.includes('competition') || tagLower.includes('musobaqa')) {
    return "bg-azure/20 text-azure";
  }
  if (tagLower.includes('music') || tagLower.includes('musiqa')) {
    return "bg-terracotta/20 text-terracotta";
  }
  
  return "bg-gray-100 text-gray-600";
};

export function EventsSection() {
  const { locale, t } = useI18n();
  const [events, setEvents] = useState<Event[]>([]);
  const [eventTags, setEventTags] = useState<Record<string, EventTag[]>>({});
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [showAll, setShowAll] = useState(false);
  const INITIAL_VISIBLE = 3;

  const fetchEvents = useCallback(async () => {
    setLoading(true);
    setError("");
    try {
      const data = await fetchWithLang<Event[]>("/v1/public/events", locale);
      setEvents(data || []);
      
      // Fetch tags for each event
      data?.forEach((event) => {
        fetchEventTags(event.id);
      });
    } catch (err) {
      console.error("Failed to fetch events:", err);
      setError("Unable to load events");
    } finally {
      setLoading(false);
    }
  }, [locale]);

  const fetchEventTags = async (eventId: string) => {
    try {
      const tags = await fetchWithLang<EventTag[]>(`/v1/public/events/tag/${eventId}`, locale);
      setEventTags((prev) => ({ ...prev, [eventId]: tags || [] }));
    } catch (err) {
      console.error("Failed to fetch event tags:", err);
      setEventTags((prev) => ({ ...prev, [eventId]: [] }));
    }
  };

  useEffect(() => {
    fetchEvents();
  }, [fetchEvents]);

  const getEventName = (event: Event): string => {
    switch (locale) {
      case "uz": return event.nameUz || event.name;
      case "ru": return event.nameRu || event.name;
      default: return event.nameEn || event.name;
    }
  };

  const getEventDescription = (event: Event): string => {
    switch (locale) {
      case "uz": return event.descriptionUz || event.description;
      case "ru": return event.descriptionRu || event.description;
      default: return event.descriptionEn || event.description;
    }
  };

  const formatDate = (dateString: string) => {
    if (!dateString) return { month: "", day: "" };
    const date = new Date(dateString);
    const month = date.toLocaleDateString(locale === "uz" ? "uz-UZ" : locale === "ru" ? "ru-RU" : "en-US", { month: "short" }).toUpperCase();
    return {
      month: month,
      day: date.getDate(),
    };
  };

  const tEvents = t.events;
  
  // Sort events by date (nearest first)
  const sortedEvents = [...events].sort((a, b) => 
    new Date(a.eventDate).getTime() - new Date(b.eventDate).getTime()
  );
  
  const visibleEvents = showAll ? sortedEvents : sortedEvents.slice(0, INITIAL_VISIBLE);
  const hasMoreEvents = sortedEvents.length > INITIAL_VISIBLE;

  if (loading) {
    return (
      <section id="events" className="py-20 md:py-32">
        <div className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
          <div className="text-center mb-16">
            <Badge variant="secondary" className="mb-4 rounded-full px-4 py-1 bg-sand-light text-muted-foreground">
              {tEvents.badge}
            </Badge>
            <h2 className="font-serif text-3xl sm:text-4xl md:text-5xl font-medium text-foreground text-balance">
              {tEvents.title}
            </h2>
            <p className="mt-4 text-lg text-muted-foreground max-w-2xl mx-auto">
              {tEvents.subtitle}
            </p>
          </div>
          <div className="grid gap-4 max-w-4xl mx-auto">
            {[1, 2, 3].map((i) => (
              <div key={i} className="bg-white rounded-2xl overflow-hidden animate-pulse shadow-sm">
                <div className="flex">
                  <div className="w-20 md:w-24 bg-gray-200" />
                  <div className="flex-1 p-5 md:p-6 space-y-3">
                    <div className="h-6 bg-gray-200 rounded w-3/4" />
                    <div className="h-4 bg-gray-200 rounded w-1/2" />
                    <div className="h-4 bg-gray-200 rounded w-full" />
                  </div>
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
      <section id="events" className="py-20 md:py-32">
        <div className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8 text-center">
          <p className="text-red-500">{error}</p>
          <button
            onClick={() => fetchEvents()}
            className="mt-4 px-4 py-2 bg-[#2F6F7E] text-white rounded-lg hover:bg-[#2F6F7E]/90 transition"
          >
            Try Again
          </button>
        </div>
      </section>
    );
  }

  return (
    <section id="events" className="py-20 md:py-32">
      <div className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
        {/* Section Header */}
        <div className="text-center mb-16">
          <Badge variant="secondary" className="mb-4 rounded-full px-4 py-1 bg-sand-light text-muted-foreground">
            {tEvents.badge}
          </Badge>
          <h2 className="font-serif text-3xl sm:text-4xl md:text-5xl font-medium text-foreground text-balance">
            {tEvents.title}
          </h2>
          <p className="mt-4 text-lg text-muted-foreground max-w-2xl mx-auto">
            {tEvents.subtitle}
          </p>
        </div>

        {/* Events List */}
        {sortedEvents.length === 0 ? (
          <div className="text-center py-12 bg-gray-50 rounded-2xl">
            <Calendar className="w-12 h-12 text-gray-300 mx-auto mb-3" />
            <p className="text-gray-500">No upcoming events at this time</p>
          </div>
        ) : (
          <>
            <div className="grid gap-4 max-w-4xl mx-auto">
              {visibleEvents.map((event) => {
                const date = formatDate(event.eventDate);
                const tags = eventTags[event.id] || [];
                const eventName = getEventName(event);
                const eventDescription = getEventDescription(event);

                return (
                  <Card 
                    key={event.id} 
                    className="group bg-white border-gray-200 hover:shadow-lg hover:border-[#2F6F7E]/20 transition-all duration-300 rounded-2xl overflow-hidden"
                  >
                    <CardContent className="p-0">
                      <div className="flex items-stretch">
                        {/* Date Block */}
                        <div className="flex-shrink-0 w-20 md:w-24 bg-[#2F6F7E] flex flex-col items-center justify-center text-white py-6">
                          <span className="text-xs font-medium opacity-80">{date.month}</span>
                          <span className="text-3xl md:text-4xl font-serif font-bold">{date.day}</span>
                        </div>

                        {/* Content */}
                        <div className="flex-1 p-5 md:p-6">
                          <div className="flex flex-col md:flex-row md:items-start md:justify-between gap-3">
                            <div className="flex-1">
                              <h3 className="font-serif text-xl font-semibold text-gray-900 group-hover:text-[#2F6F7E] transition-colors">
                                {eventName}
                              </h3>
                              <div className="flex items-center gap-1.5 mt-1.5 text-sm text-gray-500">
                                <MapPin className="h-3.5 w-3.5" />
                                <span>{event.location}</span>
                              </div>
                              <p className="mt-3 text-sm text-gray-600 leading-relaxed hidden md:block line-clamp-2">
                                {eventDescription}
                              </p>
                            </div>

                            {/* Tags */}
                            {tags.length > 0 && (
                              <div className="flex flex-wrap gap-2">
                                {tags.slice(0, 3).map((tagItem) => (
                                  <span 
                                    key={tagItem.id} 
                                    className={`text-xs font-medium px-3 py-1 rounded-full ${getTagColor(tagItem.tag)}`}
                                  >
                                    {tagItem.tag}
                                  </span>
                                ))}
                                {tags.length > 3 && (
                                  <span className="text-xs font-medium px-3 py-1 rounded-full bg-gray-100 text-gray-600">
                                    +{tags.length - 3}
                                  </span>
                                )}
                              </div>
                            )}
                          </div>
                          
                          {/* Mobile description */}
                          <p className="mt-3 text-sm text-gray-600 leading-relaxed md:hidden line-clamp-2">
                            {eventDescription}
                          </p>
                        </div>
                      </div>
                    </CardContent>
                  </Card>
                );
              })}
            </div>

            {/* Show More / Show Less Button */}
            {hasMoreEvents && (
              <div className="text-center mt-8">
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
                      View All Events ({sortedEvents.length})
                    </>
                  )}
                </button>
              </div>
            )}
          </>
        )}

       
      </div>
    </section>
  );
}
// Note: The above code assumes that the API endpoints return data in the expected format and that the localization keys are properly defined in the translation files. Adjustments may be needed based on the actual API responses and translation structure.