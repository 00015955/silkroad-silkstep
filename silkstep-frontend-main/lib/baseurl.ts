export const BASE_URL = "https://api.silkstep.uz";
export const MINIO_URL = "https://minio.silkstep.uz";

export const getImageUrl = (url: string): string => {
  if (!url) return "";
  if (url.startsWith("http")) {
    if (url.includes("localhost") || url.includes("127.0.0.1")) {
      const fileName = url.split("/").pop() || "";
      return `${MINIO_URL}/${fileName}`;
    }
    return url;
  }
  return `${MINIO_URL}/${url}`;
};

export const fetchWithLang = async <T>(
  endpoint: string,
  locale: string = "en"
): Promise<T> => {
  const response = await fetch(`${BASE_URL}${endpoint}`, {
    headers: {
      "Accept-Language": locale,
      "language": locale,
    },
    cache: "no-cache",
  });
  
  if (!response.ok) {
    throw new Error(`Failed to fetch ${endpoint}`);
  }
  
  return response.json();
};

// Destinations
export const fetchDestinations = async (locale: string = "en") => {
  return fetchWithLang<any[]>("/v1/public/destinations", locale);
};

// Destination Attractions
export const fetchDestinationAttractions = async (destinationId: string, locale: string = "en") => {
  return fetchWithLang<any[]>(`/v1/public/destinations/attractions/${destinationId}`, locale);
};

// Destination Facts
export const fetchDestinationFacts = async (destinationId: string, locale: string = "en") => {
  return fetchWithLang<any[]>(`/v1/public/destinations/fact/${destinationId}`, locale);
};

// Destination Stat Items
export const fetchDestinationStatItems = async (destinationId: string, locale: string = "en") => {
  return fetchWithLang<any[]>(`/v1/public/destinations/stat-items/${destinationId}`, locale);
};

// Travel Trips (Practical Information)
export const fetchTravelTrips = async (locale: string = "en") => {
  return fetchWithLang<any[]>(`/v1/public/travel-trips`, locale);
};

// Events
export const fetchEvents = async (locale: string = "en") => {
  return fetchWithLang<any[]>("/v1/public/events", locale);
};

// Event Tags
export const fetchEventTags = async (eventId: string, locale: string = "en") => {
  return fetchWithLang<any[]>(`/v1/public/events/tag/${eventId}`, locale);
};

// Submit a review
export const submitReview = async (data: {
  destinationId: string;
  rating: number;
  description: string;
  fullName: string;
  email: string;
}) => {
  const response = await fetch(`${BASE_URL}/v1/public/destinations/review`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  });

  if (!response.ok) {
    let message = "Failed to submit review";
    try {
      const error = await response.json();
      message = error.message || message;
    } catch {}
    throw new Error(message);
  }

  if (response.status === 204 || response.headers.get("content-length") === "0") {
    return {};
  }
  try {
    return await response.json();
  } catch {
    return {};
  }
};

// Fetch reviews for a destination
export const fetchReviews = async (destinationId: string) => {
  const response = await fetch(`${BASE_URL}/v1/public/destinations/review/${destinationId}`, {
    cache: "no-cache",
  });
  
  if (!response.ok) {
    throw new Error("Failed to fetch reviews");
  }
  
  return response.json();
};

// AI Chat
export const sendAIChat = async (message: string, destinationSlug?: string, locale: string = "en") => {
  const response = await fetch(`${BASE_URL}/v1/public/ai/chat`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Accept-Language": locale,
      "language": locale,
    },
    body: JSON.stringify({
      message,
      destinationSlug: destinationSlug || "",
    }),
  });
  
  if (!response.ok) {
    throw new Error("Failed to get AI response");
  }
  
  return response.json();
};