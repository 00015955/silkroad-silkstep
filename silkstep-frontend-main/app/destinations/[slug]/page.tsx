"use client";

import { useState, useEffect } from "react";
import { useParams } from "next/navigation";
import Image from "next/image";
import Link from "next/link";
import {
  ArrowLeft,
  Star,
  Clock,
  MapPin,
  Shield,
  Calendar,
  Thermometer,
  Users,
  ChevronDown,
  ChevronUp,
  Camera,
  Utensils,
  Hotel,
  Plane,
  ChevronRight,
  Send,
  User,
  Mail,
  MessageSquare,
  AlertCircle,
  Lightbulb,
  Coins,
  Car,
  Sun,
  Moon,
  Cloud,
  Umbrella,
  Compass,
  Smartphone,
  Wifi,
  Battery,
  Plug,
  Heart,
  Award,
  Trophy,
  Medal,
  Crown,
  Gem,
  Diamond,
  Train,
  Bus,
  Bike,
  Ship,
  Truck,
  Rocket,
  Home,
  Building,
  Castle,
  Church,
  Landmark,
  Coffee,
  Beer,
  Wine,
  Apple,
  Cake,
  Pizza,
  IceCream,
  Music,
  Film,
  Book,
  Paintbrush,
  Palette,
  Theater,
  Mic,
  ShoppingBag,
  Gift,
  Watch,
  DollarSign,
  CreditCard,
  Banknote,
  Phone,
  Mail as MailIcon,
  MessageCircle,
  Globe,
  Lock,
  Key,
  Activity,
  Zap,
  Flame,
  Droplet,
  Wind,
  Leaf,
  AlarmClock,
  Timer,
  Hourglass,
  User as UserIcon,
  UserPlus,
  UserCheck,
  UserX,
  Briefcase,
  Settings,
  Wrench,
  Scissors,
  Clipboard,
  FileText,
  Search,
  Eye,
  EyeOff,
  Bell,
  BellRing,
  MailOpen,
  ThumbsUp,
  ThumbsDown,
  Smile,
  Frown,
  Meh,
  Laugh,
  ArrowRight,

  ArrowUp,
  ArrowDown,
  Check,
  X,
  Plus,
  Minus,
  Divide,
  Equal,
  Percent,
} from "lucide-react";
import { Button } from "@/components/ui/button";
import { Card, CardContent } from "@/components/ui/card";
import { Badge } from "@/components/ui/badge";
import { useI18n } from "@/lib/i18n";
import {
  getImageUrl,
  fetchWithLang,
  fetchDestinationAttractions,
  fetchDestinationFacts,
  fetchDestinationStatItems,
  fetchTravelTrips,
  submitReview,
  fetchReviews,
} from "@/lib/baseurl";

interface Destination {
  id: string;
  slug: string;
  name: string;
  description: string;
  imageUrl: string;
  rating: number;
  recommendedDays: string;
}

interface Attraction {
  id: string;
  name: string;
  description: string;
  imageUrl: string;
}

interface DestinationFact {
  id: string;
  icon: string;
  label: string;
  value: string;
}

interface StatItem {
  value: string;
  label: string;
}

interface TravelTrip {
  id: string;
  icon: string;
  title: string;
  subtitle: string;
  description: string;
}

interface Review {
  id: string;
  rating: number;
  description: string;
  fullName: string;
  email: string;
  createdAt: string;
}

// Star Rating Component
const StarRating = ({
  rating,
  onRatingChange,
  size = "md",
}: {
  rating: number;
  onRatingChange?: (rating: number) => void;
  size?: "sm" | "md" | "lg";
}) => {
  const [hoverRating, setHoverRating] = useState(0);
  const starSizes = { sm: "w-4 h-4", md: "w-5 h-5", lg: "w-6 h-6" };
  const starSize = starSizes[size];

  return (
    <div className="flex gap-1">
      {[1, 2, 3, 4, 5].map((star) => (
        <button
          key={star}
          type="button"
          onClick={() => onRatingChange?.(star)}
          onMouseEnter={() => setHoverRating(star)}
          onMouseLeave={() => setHoverRating(0)}
          className="focus:outline-none transition-transform hover:scale-110"
        >
          <Star
            className={`${starSize} ${
              (hoverRating || rating) >= star
                ? "fill-amber-500 text-amber-500"
                : "text-gray-300"
            } transition-colors`}
          />
        </button>
      ))}
    </div>
  );
};

// Icon mapping for facts (unchanged)
const getFactIcon = (iconName: string) => {
  const icon = iconName?.toLowerCase() || "";

  if (icon.includes("lightbulb"))
    return <Lightbulb className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("coin"))
    return <Coins className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("car"))
    return <Car className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("utensil"))
    return <Utensils className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("sun"))
    return <Sun className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("moon"))
    return <Moon className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("cloud"))
    return <Cloud className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("umbrella"))
    return <Umbrella className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("map") || icon.includes("pin"))
    return <MapPin className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("compass"))
    return <Compass className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("camera"))
    return <Camera className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("smartphone"))
    return <Smartphone className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("wifi"))
    return <Wifi className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("battery"))
    return <Battery className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("plug"))
    return <Plug className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("heart"))
    return <Heart className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("star"))
    return <Star className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (
    icon.includes("award") ||
    icon.includes("trophy") ||
    icon.includes("medal")
  )
    return <Award className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("crown"))
    return <Crown className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("gem") || icon.includes("diamond"))
    return <Gem className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("plane"))
    return <Plane className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("train"))
    return <Train className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("bus"))
    return <Bus className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("bike"))
    return <Bike className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("ship"))
    return <Ship className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("truck"))
    return <Truck className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("rocket"))
    return <Rocket className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("hotel"))
    return <Hotel className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("home"))
    return <Home className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("building"))
    return <Building className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("castle"))
    return <Castle className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("church"))
    return <Church className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("landmark"))
    return <Landmark className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("coffee"))
    return <Coffee className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("beer"))
    return <Beer className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("wine"))
    return <Wine className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("apple"))
    return <Apple className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("cake"))
    return <Cake className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("pizza"))
    return <Pizza className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("icecream"))
    return <IceCream className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("music"))
    return <Music className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("film"))
    return <Film className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("book"))
    return <Book className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("paint") || icon.includes("palette"))
    return <Paintbrush className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("theater") || icon.includes("mic"))
    return <Mic className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("shopping") || icon.includes("gift"))
    return <ShoppingBag className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("watch"))
    return <Watch className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (
    icon.includes("dollar") ||
    icon.includes("credit") ||
    icon.includes("banknote")
  )
    return <DollarSign className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("phone"))
    return <Phone className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("mail") || icon.includes("message"))
    return <Mail className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("globe"))
    return <Globe className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("lock"))
    return <Lock className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("shield"))
    return <Shield className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("key"))
    return <Key className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("clock"))
    return <Clock className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("calendar"))
    return <Calendar className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("user"))
    return <Users className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("briefcase"))
    return <Briefcase className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (
    icon.includes("settings") ||
    icon.includes("tool") ||
    icon.includes("wrench")
  )
    return <Settings className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("clipboard") || icon.includes("file"))
    return <Clipboard className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("activity") || icon.includes("zap"))
    return <Activity className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("flame"))
    return <Flame className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("droplet"))
    return <Droplet className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("wind"))
    return <Wind className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("leaf") || icon.includes("tree"))
    return <Leaf className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("thumbsup"))
    return <ThumbsUp className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("smile"))
    return <Smile className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("arrow"))
    return <ArrowRight className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("chevron"))
    return <ChevronRight className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("check"))
    return <Check className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("x"))
    return <X className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("plus"))
    return <Plus className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (icon.includes("minus"))
    return <Minus className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  if (
    icon.includes("thermometer") ||
    icon.includes("climate") ||
    icon.includes("weather")
  ) {
    return <Thermometer className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
  }

  return <Users className="h-5 w-5 text-[#2F6F7E] mt-0.5" />;
};

const getTravelTripIcon = (iconName: string) => {
  const icon = iconName?.toLowerCase() || "";

  if (icon.includes("lightbulb"))
    return <Lightbulb className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("coin"))
    return <Coins className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("plane"))
    return <Plane className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("train"))
    return <Train className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("car")) return <Car className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("bus")) return <Bus className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("bike")) return <Bike className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("ship")) return <Ship className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("rocket"))
    return <Rocket className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("hotel"))
    return <Hotel className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("home")) return <Home className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("building"))
    return <Building className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("castle"))
    return <Castle className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("landmark"))
    return <Landmark className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("utensils"))
    return <Utensils className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("coffee"))
    return <Coffee className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("beer")) return <Beer className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("cake")) return <Cake className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("pizza"))
    return <Pizza className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("calendar"))
    return <Calendar className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("clock"))
    return <Clock className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("music"))
    return <Music className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("film")) return <Film className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("mic")) return <Mic className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("dollar") || icon.includes("credit"))
    return <DollarSign className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("shopping"))
    return <ShoppingBag className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("gift")) return <Gift className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("phone"))
    return <Phone className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("mail")) return <Mail className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("globe"))
    return <Globe className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("shield"))
    return <Shield className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("users") || icon.includes("user"))
    return <Users className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("sun")) return <Sun className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("cloud"))
    return <Cloud className="h-6 w-6 text-[#2F6F7E]" />;
  if (icon.includes("umbrella"))
    return <Umbrella className="h-6 w-6 text-[#2F6F7E]" />;

  return <Plane className="h-6 w-6 text-[#2F6F7E]" />;
};

export default function DestinationDetailPage() {
  const params = useParams();
  const slug = params.slug as string;
  const { locale } = useI18n();

  const [destination, setDestination] = useState<Destination | null>(null);
  const [attractions, setAttractions] = useState<Attraction[]>([]);
  const [facts, setFacts] = useState<DestinationFact[]>([]);
  const [statItems, setStatItems] = useState<StatItem[]>([]);
  const [travelTrips, setTravelTrips] = useState<TravelTrip[]>([]);
  const [reviews, setReviews] = useState<Review[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  // Review form state
  const [reviewRating, setReviewRating] = useState(0);
  const [reviewName, setReviewName] = useState("");
  const [reviewEmail, setReviewEmail] = useState("");
  const [reviewText, setReviewText] = useState("");
  const [submitting, setSubmitting] = useState(false);
  const [submitError, setSubmitError] = useState("");
  const [submitSuccess, setSubmitSuccess] = useState("");
  const [cooldown, setCooldown] = useState(false);
  const [isRefreshing, setIsRefreshing] = useState(false);

  const [showAllPractical, setShowAllPractical] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
      setError("");
      try {
        const destinations = await fetchWithLang<Destination[]>(
          "/v1/public/destinations",
          locale,
        );
        const normalizeSlug = (s: string) =>
          s.toLowerCase().trim().replace(/\s+/g, "-");
        const found = destinations.find((d) => {
          if (d.slug && d.slug.trim() !== "") {
            return normalizeSlug(d.slug) === normalizeSlug(slug);
          }
          return normalizeSlug(d.name) === normalizeSlug(slug);
        });

        if (found) {
          setDestination(found);

          try {
            const attractionsData = await fetchDestinationAttractions(
              found.id,
              locale,
            );
            setAttractions(attractionsData || []);
          } catch (err) {
            console.error("Failed to fetch attractions:", err);
            setAttractions([]);
          }

          try {
            const factsData = await fetchDestinationFacts(found.id, locale);
            setFacts(factsData || []);
          } catch (err) {
            console.error("Failed to fetch facts:", err);
            setFacts([]);
          }

          try {
            const statItemsData = await fetchDestinationStatItems(
              found.id,
              locale,
            );
            setStatItems(statItemsData || []);
          } catch (err) {
            console.error("Failed to fetch stat items:", err);
            setStatItems([]);
          }

          try {
            const reviewsData = await fetchReviews(found.id);
            setReviews(reviewsData || []);
          } catch (err) {
            console.error("Failed to fetch reviews:", err);
            setReviews([]);
          }
        } else {
          setError("Destination not found");
        }

        try {
          const tripsData = await fetchTravelTrips(locale);
          setTravelTrips(tripsData || []);
        } catch (err) {
          console.error("Failed to fetch travel trips:", err);
          setTravelTrips([]);
        }
      } catch (err) {
        console.error("Failed to fetch destination:", err);
        setError("Unable to load destination");
      } finally {
        setLoading(false);
      }
    };

    if (slug) {
      fetchData();
    }
  }, [slug, locale]);

  const [cooldownSeconds, setCooldownSeconds] = useState(0);

  // Cooldown timer effect
  useEffect(() => {
    let timer: NodeJS.Timeout;
    if (cooldown && cooldownSeconds > 0) {
      timer = setInterval(() => {
        setCooldownSeconds((prev) => {
          if (prev <= 1) {
            setCooldown(false);
            return 0;
          }
          return prev - 1;
        });
      }, 1000);
    }
    return () => {
      if (timer) clearInterval(timer);
    };
  }, [cooldown, cooldownSeconds]);

  const handleSubmitReview = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!destination || submitting || cooldown) return;

    setSubmitting(true);
    setSubmitError("");
    setSubmitSuccess("");

    try {
      await submitReview({
        destinationId: destination.id,
        rating: reviewRating,
        description: reviewText,
        fullName: reviewName,
        email: reviewEmail,
      });

      setSubmitSuccess("Thank you for your review!");
      setReviewRating(0);
      setReviewName("");
      setReviewEmail("");
      setReviewText("");

      setCooldown(true);
      setCooldownSeconds(10);

      setIsRefreshing(true);
      try {
        const updatedReviews = await fetchReviews(destination.id);
        setReviews(updatedReviews || []);
      } catch {
        // silently ignore refresh error
      } finally {
        setIsRefreshing(false);
      }

      setTimeout(() => setSubmitSuccess(""), 4000);
    } catch (err: unknown) {
      const message = err instanceof Error ? err.message : "Failed to submit review";
      setSubmitError(message);
      setTimeout(() => setSubmitError(""), 4000);
    } finally {
      setSubmitting(false);
    }
  };


  if (loading) {
    return (
      <div className="min-h-screen bg-white">
        <div className="flex items-center justify-center h-screen">
          <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-[#2F6F7E]"></div>
        </div>
      </div>
    );
  }

  if (error || !destination) {
    return (
      <div className="min-h-screen bg-white">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-32 text-center">
          <p className="text-red-500">{error || "Destination not found"}</p>
          <Link href="/#destinations">
            <Button className="mt-4 rounded-full">Back to Destinations</Button>
          </Link>
        </div>
      </div>
    );
  }

  const destinationImage = getImageUrl(destination.imageUrl);
  const destinationRating = destination.rating || 4.8;
  const recommendedDays = destination.recommendedDays || "2-3";

  const defaultStatItems: StatItem[] = [
    { value: "2,500+", label: "Years of History" },
    { value: "140+", label: "Historic Monuments" },
    { value: "4", label: "Trading Domes" },
    { value: "9th", label: "Century Mausoleum" },
  ];

  const displayStatItems = statItems.length > 0 ? statItems : defaultStatItems;

  const defaultTravelTrips: TravelTrip[] = [
    {
      id: "1",
      icon: "plane",
      title: "Getting There",
      subtitle: "",
      description: "High-speed train from Tashkent connects to major cities.",
    },
    {
      id: "2",
      icon: "hotel",
      title: "Where to Stay",
      subtitle: "",
      description:
        "Historic hotels and modern accommodations available in city center.",
    },
    {
      id: "3",
      icon: "utensils",
      title: "What to Eat",
      subtitle: "",
      description:
        "Traditional plov, samsa, and local bread from street vendors.",
    },
    {
      id: "4",
      icon: "calendar",
      title: "Best Time",
      subtitle: "",
      description:
        "Spring (Apr-Jun) and Autumn (Sep-Oct) for pleasant weather.",
    },
  ];

  const displayTravelTrips =
    travelTrips.length > 0 ? travelTrips : defaultTravelTrips;

  const averageRating =
    reviews.length > 0
      ? (
          reviews.reduce((sum, r) => sum + r.rating, 0) / reviews.length
        ).toFixed(1)
      : null;

  return (
    <div className="min-h-screen bg-white">
      {/* Hero Section */}
      <section className="relative h-[70vh] min-h-[500px]">
        <Image
          src={
            destinationImage ||
            "[via.placeholder.com](https://via.placeholder.com/1920x1080?text=No+Image)"
          }
          alt={destination.name}
          fill
          className="object-cover"
          priority
          onError={(e) => {
            const target = e.target as HTMLImageElement;
            target.src = "[via.placeholder.com](https://via.placeholder.com/1920x1080?text=No+Image)";
          }}
        />
        <div className="absolute inset-0 bg-gradient-to-t from-black/60 via-black/20 to-transparent" />

        <div className="absolute top-6 left-6 z-20">
          <Link href="/#destinations">
            <Button
              variant="secondary"
              size="sm"
              className="rounded-full bg-white/90 backdrop-blur-sm hover:bg-white"
            >
              <ArrowLeft className="h-4 w-4 mr-2" />
              Back to Destinations
            </Button>
          </Link>
        </div>

        <div className="absolute bottom-0 left-0 right-0 p-6 md:p-12">
          <div className="max-w-7xl mx-auto">
            <div className="flex items-center gap-3 mb-4">
              <Badge className="bg-[#2F6F7E] text-white rounded-full px-3 py-1">
                <Shield className="h-3 w-3 mr-1.5" />
                UNESCO World Heritage
              </Badge>
              <div className="flex items-center gap-1 text-white/90">
                <Star className="h-4 w-4 fill-amber-400 text-amber-400" />
                <span className="font-medium">{destinationRating}</span>
              </div>
            </div>
            <h1 className="font-serif text-4xl md:text-6xl lg:text-7xl font-medium text-white mb-4">
              {destination.name}
            </h1>
            <p className="text-lg md:text-xl text-white/80 max-w-2xl leading-relaxed">
              {destination.description.substring(0, 200)}...
            </p>
            <div className="flex flex-wrap gap-4 mt-6">
              <div className="flex items-center gap-2 text-white/70">
                <Clock className="h-4 w-4" />
                <span>
                  {recommendedDays} days recommended
                </span>
              </div>
              <div className="flex items-center gap-2 text-white/70">
                <MapPin className="h-4 w-4" />
                <span>{destination.name} Region</span>
              </div>
              <div className="flex items-center gap-2 text-white/70">
                <Thermometer className="h-4 w-4" />
                <span>Best: Mar-May, Sep-Nov</span>
              </div>
            </div>
          </div>
        </div>
      </section>

      {/* Quick Stats */}
      <section className="bg-[#2F6F7E] text-white py-6">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="grid grid-cols-2 md:grid-cols-4 gap-6 text-center">
            {displayStatItems.map((stat, index) => (
              <div key={index}>
                <div className="text-3xl font-serif font-medium">
                  {stat.value}
                </div>
                <div className="text-white/70 text-sm">{stat.label}</div>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* Main Content */}
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-16">
        {/* Overview */}
        <section className="mb-20">
          <h2 className="font-serif text-3xl md:text-4xl font-medium text-gray-900 mb-6">
            About {destination.name}
          </h2>
          <div className="grid lg:grid-cols-3 gap-8">
            <div className="lg:col-span-2 space-y-4 text-gray-600 leading-relaxed">
              {destination.description.split("\n").map((paragraph, idx) => (
                <p key={idx}>{paragraph}</p>
              ))}
            </div>

            <div className="bg-amber-50 rounded-3xl p-6">
              <h3 className="font-serif text-xl font-medium text-gray-900 mb-4">
                At a Glance
              </h3>
              <div className="space-y-4">
                {facts.length > 0 ? (
                  facts.map((fact) => (
                    <div key={fact.id} className="flex items-start gap-3">
                      {getFactIcon(fact.icon)}
                      <div>
                        <div className="font-medium text-gray-900">
                          {fact.label}
                        </div>
                        <div className="text-sm text-gray-500">
                          {fact.value}
                        </div>
                      </div>
                    </div>
                  ))
                ) : (
                  <>
                    <div className="flex items-start gap-3">
                      <Users className="h-5 w-5 text-[#2F6F7E] mt-0.5" />
                      <div>
                        <div className="font-medium text-gray-900">
                          Population
                        </div>
                        <div className="text-sm text-gray-500">280,000</div>
                      </div>
                    </div>
                    <div className="flex items-start gap-3">
                      <MapPin className="h-5 w-5 text-[#2F6F7E] mt-0.5" />
                      <div>
                        <div className="font-medium text-gray-900">
                          Location
                        </div>
                        <div className="text-sm text-gray-500">
                          450 km from Tashkent
                        </div>
                      </div>
                    </div>
                    <div className="flex items-start gap-3">
                      <Thermometer className="h-5 w-5 text-[#2F6F7E] mt-0.5" />
                      <div>
                        <div className="font-medium text-gray-900">Climate</div>
                        <div className="text-sm text-gray-500">
                          Desert, very hot summers
                        </div>
                      </div>
                    </div>
                    <div className="flex items-start gap-3">
                      <Camera className="h-5 w-5 text-[#2F6F7E] mt-0.5" />
                      <div>
                        <div className="font-medium text-gray-900">
                          Top Attractions
                        </div>
                        <div className="text-sm text-gray-500">
                          Po-i-Kalyan, Ark, Lyab-i-Hauz
                        </div>
                      </div>
                    </div>
                  </>
                )}
              </div>
            </div>
          </div>
        </section>

        {/* Must-See Attractions */}
        {attractions.length > 0 && (
          <section className="mb-20">
            <h2 className="font-serif text-3xl md:text-4xl font-medium text-gray-900 mb-8">
              Must-See Attractions
            </h2>
            <div className="space-y-8">
              {attractions.map((attraction, index) => (
                <Card
                  key={attraction.id}
                  className="overflow-hidden rounded-3xl border-gray-200 shadow-sm hover:shadow-md transition-shadow"
                >
                  <div
                    className={`grid md:grid-cols-2 ${index % 2 === 1 ? "md:flex-row-reverse" : ""}`}
                  >
                    <div
                      className={`relative h-64 md:h-auto ${index % 2 === 1 ? "md:order-2" : ""}`}
                    >
                      <Image
                        src={getImageUrl(attraction.imageUrl)}
                        alt={attraction.name}
                        fill
                        className="object-cover"
                        onError={(e) => {
                          const target = e.target as HTMLImageElement;
                          target.src =
                            "[via.placeholder.com](https://via.placeholder.com/800x600?text=No+Image)";
                        }}
                      />
                    </div>
                    <CardContent
                      className={`p-8 flex flex-col justify-center ${index % 2 === 1 ? "md:order-1" : ""}`}
                    >
                      <h3 className="font-serif text-2xl font-semibold text-gray-900 mb-3">
                        {attraction.name}
                      </h3>
                      <p className="text-gray-600 leading-relaxed mb-4">
                        {attraction.description}
                      </p>
                    </CardContent>
                  </div>
                </Card>
              ))}
            </div>
          </section>
        )}

        {/* Practical Information */}
        <section className="mb-20">
          <h2 className="font-serif text-3xl md:text-4xl font-medium text-gray-900 mb-8">
            Practical Information
          </h2>

          <div className="grid sm:grid-cols-2 lg:grid-cols-4 gap-6">
            {displayTravelTrips
              .slice(0, showAllPractical ? displayTravelTrips.length : 8)
              .map((trip) => (
                <Card
                  key={trip.id}
                  className="rounded-3xl border-gray-200 p-6 hover:shadow-md transition-shadow"
                >
                  <div className="w-12 h-12 rounded-2xl bg-[#2F6F7E]/10 flex items-center justify-center mb-4">
                    {getTravelTripIcon(trip.icon)}
                  </div>
                  <h3 className="font-serif text-lg font-semibold text-gray-900 mb-2">
                    {trip.title}
                  </h3>
                  <p className="text-sm text-gray-600 leading-relaxed">
                    {trip.description}
                  </p>
                </Card>
              ))}
          </div>

          {displayTravelTrips.length > 4 && (
            <div className="text-center mt-8">
              <button
                onClick={() => setShowAllPractical(!showAllPractical)}
                className="inline-flex items-center gap-2 px-6 py-2.5 bg-white border border-gray-300 rounded-full text-sm font-medium text-gray-700 hover:bg-gray-50 hover:border-[#2F6F7E] hover:text-[#2F6F7E] transition-all duration-300"
              >
                {showAllPractical ? (
                  <>
                    <ChevronUp className="w-4 h-4" />
                    Show Less
                  </>
                ) : (
                  <>
                    <ChevronDown className="w-4 h-4" />
                    Show More
                  </>
                )}
              </button>
            </div>
          )}
        </section>

        {/* Reviews Section */}
        <section className="mb-20">
          <h2 className="font-serif text-3xl md:text-4xl font-medium text-gray-900 mb-8">
            Traveler Reviews
          </h2>

          <div className="grid lg:grid-cols-2 gap-8">
            {/* Left Side - Write a Review Form */}
            <div className="bg-gray-50 rounded-2xl p-6 border border-gray-200">
              <h3 className="text-xl font-semibold text-gray-900 mb-4">
                Share Your Experience
              </h3>

              {submitSuccess && (
                <div className="mb-4 p-3 bg-green-50 border border-green-200 text-green-700 rounded-lg text-sm flex items-center gap-2">
                  <AlertCircle className="w-4 h-4" />
                  {submitSuccess}
                </div>
              )}

           

              {cooldown && (
                <div className="mb-4 p-3 bg-yellow-50 border border-yellow-200 text-yellow-700 rounded-lg text-sm flex items-center gap-2">
                  <AlertCircle className="w-4 h-4" />
                  Please wait {cooldownSeconds} seconds before submitting another review
                </div>
              )}

              <form onSubmit={handleSubmitReview} className="space-y-5">
                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-2">
                    Rating
                  </label>
                  <StarRating
                    rating={reviewRating}
                    onRatingChange={setReviewRating}
                    size="lg"
                  />
                </div>

                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-2">
                    <User className="w-4 h-4 inline mr-1" />
                    Full Name
                  </label>
                  <input
                    type="text"
                    value={reviewName}
                    onChange={(e) => setReviewName(e.target.value)}
                    placeholder="John Doe"
                    required
                    disabled={submitting || cooldown}
                    className="w-full px-4 py-2.5 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-[#2F6F7E] focus:border-transparent text-sm disabled:bg-gray-100 disabled:cursor-not-allowed"
                  />
                </div>

                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-2">
                    <Mail className="w-4 h-4 inline mr-1" />
                    Email
                  </label>
                  <input
                    type="email"
                    value={reviewEmail}
                    onChange={(e) => setReviewEmail(e.target.value)}
                    placeholder="john@example.com"
                    required
                    disabled={submitting || cooldown}
                    className="w-full px-4 py-2.5 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-[#2F6F7E] focus:border-transparent text-sm disabled:bg-gray-100 disabled:cursor-not-allowed"
                  />
                </div>

                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-2">
                    <MessageSquare className="w-4 h-4 inline mr-1" />
                    Your Review
                  </label>
                  <textarea
                    value={reviewText}
                    onChange={(e) => setReviewText(e.target.value)}
                    placeholder="Share your experience about this destination..."
                    rows={4}
                    required
                    disabled={submitting || cooldown}
                    className="w-full px-4 py-2.5 border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-[#2F6F7E] focus:border-transparent resize-none text-sm disabled:bg-gray-100 disabled:cursor-not-allowed"
                  />
                </div>

                <Button
                  type="submit"
                  disabled={submitting || cooldown || reviewRating === 0}
                  className="w-full bg-[#2F6F7E] hover:bg-[#2F6F7E]/90 text-white py-2.5 disabled:opacity-50 disabled:cursor-not-allowed"
                >
                  <Send className="w-4 h-4 mr-2" />
                  {submitting
                    ? "Submitting..."
                    : cooldown
                      ? `Wait ${cooldownSeconds}s`
                      : "Submit Review"}
                </Button>
              </form>
            </div>

            {/* Right Side - Reviews List */}
            <div>
              {isRefreshing && (
                <div className="flex justify-center items-center py-8">
                  <div className="animate-spin rounded-full h-6 w-6 border-b-2 border-[#2F6F7E]"></div>
                  <span className="ml-2 text-sm text-gray-500">
                    Loading reviews...
                  </span>
                </div>
              )}

              {!isRefreshing && reviews.length > 0 && averageRating && (
                <div className="bg-amber-50 rounded-2xl p-6 mb-6">
                  <div className="flex items-center gap-4 flex-wrap">
                    <div className="text-center">
                      <div className="text-4xl font-bold text-gray-900">
                        {averageRating}
                      </div>
                      <div className="flex gap-1 mt-1">
                        {[1, 2, 3, 4, 5].map((star) => (
                          <Star
                            key={star}
                            className={`w-4 h-4 ${
                              star <= Number(averageRating)
                                ? "fill-amber-500 text-amber-500"
                                : "text-gray-300"
                            }`}
                          />
                        ))}
                      </div>
                      <div className="text-xs text-gray-500 mt-1">
                        {reviews.length} reviews
                      </div>
                    </div>
                    <div className="flex-1">
                      <div className="text-gray-600 text-sm">
                        Based on {reviews.length} traveler reviews
                      </div>
                    </div>
                  </div>
                </div>
              )}

              {!isRefreshing && reviews.length > 0 ? (
                <div className="space-y-4 max-h-[500px] overflow-y-auto pr-2">
                  {reviews.map((review, index) => (
                    <div
                      key={review.id || index}
                      className="bg-white border border-gray-200 rounded-2xl p-5 hover:shadow-md transition-shadow"
                    >
                      <div className="flex items-start justify-between mb-3">
                        <div>
                          <div className="flex items-center gap-3 mb-1">
                            <div className="w-10 h-10 rounded-full bg-[#2F6F7E]/10 flex items-center justify-center">
                              <User className="w-5 h-5 text-[#2F6F7E]" />
                            </div>
                            <div>
                              <h4 className="font-semibold text-gray-900">
                                {review.fullName}
                              </h4>
                              <p className="text-xs text-gray-400">
                                {new Date(review.createdAt).toLocaleDateString()}
                              </p>
                            </div>
                          </div>
                        </div>
                        <div className="flex gap-1">
                          {[1, 2, 3, 4, 5].map((star) => (
                            <Star
                              key={star}
                              className={`w-4 h-4 ${
                                star <= review.rating
                                  ? "fill-amber-500 text-amber-500"
                                  : "text-gray-300"
                              }`}
                            />
                          ))}
                        </div>
                      </div>
                      <p className="text-gray-600 text-sm leading-relaxed">
                        {review.description}
                      </p>
                    </div>
                  ))}
                </div>
              ) : (
                !isRefreshing &&
                reviews.length === 0 && (
                  <div className="text-center py-12 bg-gray-50 rounded-2xl">
                    <MessageSquare className="w-12 h-12 text-gray-300 mx-auto mb-3" />
                    <p className="text-gray-500">
                      No reviews yet. Be the first to share your experience!
                    </p>
                  </div>
                )
              )}
            </div>
          </div>
        </section>

        {/* CTA */}
        <section className="bg-amber-50 rounded-3xl p-8 md:p-12 text-center">
          <h2 className="font-serif text-2xl md:text-3xl font-medium text-gray-900 mb-4">
            Ready to explore {destination.name}?
          </h2>
          <p className="text-gray-600 mb-6 max-w-xl mx-auto">
            Let our AI guide help you plan the perfect trip with personalized
            recommendations.
          </p>
          <div className="flex flex-col sm:flex-row gap-4 justify-center">
            <Link href="/#destinations">
              <Button
                size="lg"
                variant="outline"
                className="rounded-full px-8 w-full sm:w-auto border-gray-300"
              >
                Back to Destinations
                <ChevronRight className="h-4 w-4 ml-2" />
              </Button>
            </Link>
          </div>
        </section>
      </div>
    </div>
  );
}
