import { Link } from 'react-router-dom'
import { destinations } from '../assets/data/destinations.js'
import { events } from '../assets/data/events.js'
import DestinationCard from '../components/DestinationCard/DestinationCard.jsx'
import EventCard from '../components/EventCard/EventCard.jsx'

function HomePage() {
  const featuredDestinations = destinations.slice(0, 3)
  const upcomingEvents = events.slice(0, 4)

  const travelTips = [
    {
      title: 'Best season',
      text: 'Spring and autumn are usually the most comfortable times to explore Uzbekistan.',
    },
    {
      title: 'Currency',
      text: 'The local currency is the Uzbek soʻm. Carry some cash for bazaars and smaller shops.',
    },
    {
      title: 'Dress code',
      text: 'Dress modestly at religious sites and keep a light layer for evenings.',
    },
    {
      title: 'Transport',
      text: 'High-speed rail is a convenient way to travel between Tashkent, Samarkand, and Bukhara.',
    },
    {
      title: 'Food',
      text: 'Try plov, samsa, lagman, shashlik, and regional breads in each city.',
    },
    {
      title: 'Language',
      text: 'Uzbek is the state language, and Russian is also widely understood in many places.',
    },
  ]

  return (
    <div className="page-enter">
      {/* Hero section */}
      <section className="section">
        <div
          style={{
            display: 'grid',
            gridTemplateColumns: 'repeat(auto-fit, minmax(280px, 1fr))',
            gap: '2rem',
            alignItems: 'center',
          }}
        >
          <div>
            <p
              style={{
                color: 'var(--accent)',
                fontWeight: 600,
                marginBottom: '0.75rem',
              }}
            >
              Discover Uzbekistan
            </p>

            <h1 style={{ fontSize: 'clamp(2.5rem, 5vw, 4.5rem)', marginBottom: '1rem' }}>
              Journey through the heart of the Silk Road
            </h1>

            <p
              style={{
                color: 'var(--muted)',
                maxWidth: '620px',
                marginBottom: '1.5rem',
                fontSize: '1.05rem',
              }}
            >
              Explore legendary cities, vibrant culture, unforgettable food, and
              timeless architecture across one of Central Asia’s most fascinating destinations.
            </p>

            <div style={{ display: 'flex', gap: '0.75rem', flexWrap: 'wrap' }}>
              <Link to="/destinations" className="btn btn-accent">
                Explore destinations
              </Link>
              <Link to="/ai-guide" className="btn btn-ghost">
                Ask AI guide
              </Link>
            </div>
          </div>

          {/* Simple SVG illustration */}
          <div
            style={{
              background: 'linear-gradient(180deg, #f5ede0 0%, #fff 100%)',
              border: '1px solid var(--border)',
              borderRadius: 'var(--radius-lg)',
              padding: '1.5rem',
              boxShadow: 'var(--shadow-sm)',
            }}
          >
            <svg viewBox="0 0 500 320" width="100%" height="100%" aria-label="Silk Road city illustration">
              <rect x="0" y="0" width="500" height="320" rx="24" fill="#faf9f7" />
              <circle cx="390" cy="70" r="28" fill="#f5d7a1" />
              <rect x="40" y="220" width="420" height="40" fill="#e8d7bc" />
              <rect x="80" y="140" width="90" height="80" fill="#c17b3a" />
              <rect x="200" y="120" width="110" height="100" fill="#2d7d6b" />
              <rect x="340" y="155" width="80" height="65" fill="#b8860b" />
              <polygon points="125,95 70,140 180,140" fill="#8c5420" />
              <polygon points="255,70 190,120 320,120" fill="#23685a" />
              <polygon points="380,120 330,155 430,155" fill="#8b4513" />
              <rect x="242" y="145" width="26" height="75" fill="#f3e8d4" />
              <rect x="110" y="165" width="18" height="55" fill="#f3e8d4" />
              <rect x="370" y="180" width="16" height="40" fill="#f3e8d4" />
            </svg>
          </div>
        </div>
      </section>

      {/* Featured destinations */}
      <section className="section">
        <div className="section-header">
          <h2 className="section-title">Featured destinations</h2>
          <Link to="/destinations" className="section-link">
            View all
          </Link>
        </div>

        <div className="cards-grid">
          {featuredDestinations.map((destination) => (
            <DestinationCard key={destination.id} destination={destination} />
          ))}
        </div>
      </section>

      {/* Upcoming events */}
      <section className="section">
        <div className="section-header">
          <h2 className="section-title">Upcoming events</h2>
          <Link to="/events" className="section-link">
            View all
          </Link>
        </div>

        <div className="cards-grid">
          {upcomingEvents.map((event) => (
            <EventCard key={event.id} event={event} compact />
          ))}
        </div>
      </section>

      {/* Travel essentials */}
      <section className="section">
        <div className="section-header">
          <h2 className="section-title">Travel essentials</h2>
        </div>

        <div className="cards-grid">
          {travelTips.map((tip) => (
            <article
              key={tip.title}
              style={{
                background: 'var(--surface)',
                border: '1px solid var(--border)',
                borderRadius: 'var(--radius)',
                padding: '1.25rem',
                boxShadow: 'var(--shadow-sm)',
              }}
            >
              <h3 style={{ marginBottom: '0.5rem' }}>{tip.title}</h3>
              <p style={{ color: 'var(--muted)' }}>{tip.text}</p>
            </article>
          ))}
        </div>
      </section>
    </div>
  )
}

export default HomePage