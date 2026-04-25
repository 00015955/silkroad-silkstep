import { Navbar } from "@/components/navbar"
import { HeroSection } from "@/components/hero-section"
import { DestinationsSection } from "@/components/destinations-section"
import { EventsSection } from "@/components/events-section"
import { EssentialsSection } from "@/components/essentials-section"
import { Footer } from "@/components/footer"

export default function HomePage() {
  return (
    <main className="min-h-screen">
      <Navbar />
      <HeroSection />
      <DestinationsSection />
      <EventsSection />
      <EssentialsSection />
      <Footer />
    </main>
  )
}

// The HomePage component is the main component of the application. 
//It is responsible for rendering the different sections of the homepage, such as the navbar, hero section, destinations section, events section, essentials section, and footer.