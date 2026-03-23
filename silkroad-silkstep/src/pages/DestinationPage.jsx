import { useState } from 'react'
import { destinations } from '../assets/data/destinations.js'
import DestinationCard from '../components/DestinationCard/DestinationCard.jsx'
import FilterBar from '../components/FilterBar/FilterBar.jsx'

function DestinationsPage() {
  const [activeFilter, setActiveFilter] = useState('All')

  const filters = ['All', 'UNESCO', 'Culture', 'History', 'Nature']

  const filteredDestinations =
    activeFilter === 'All'
      ? destinations
      : destinations.filter((destination) => destination.tag === activeFilter)

  return (
    <section className="section page-enter">
      <div className="section-header" style={{ alignItems: 'flex-start', flexDirection: 'column', gap: '1rem' }}>
        <div>
          <h1 className="section-title">Destinations</h1>
          <p style={{ color: 'var(--muted)', marginTop: '0.5rem' }}>
            Explore iconic cities, cultural centers, desert escapes, and historic landmarks across Uzbekistan.
          </p>
        </div>

        <FilterBar
          filters={filters}
          active={activeFilter}
          onChange={setActiveFilter}
        />
      </div>

      <div className="cards-grid">
        {filteredDestinations.map((destination) => (
          <DestinationCard key={destination.id} destination={destination} />
        ))}
      </div>
    </section>
  )
}

export default DestinationsPage