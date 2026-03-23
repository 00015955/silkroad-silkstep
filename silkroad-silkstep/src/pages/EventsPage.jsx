import { useState } from 'react'
import { events } from '../assets/data/events.js'
import EventCard from '../components/EventCard/EventCard.jsx'
import FilterBar from '../components/FilterBar/FilterBar.jsx'

function EventsPage() {
  const [activeFilter, setActiveFilter] = useState('All')

  const filters = ['All', 'Festival', 'Cultural', 'Music', 'Food', 'Craft']

  const filteredEvents =
    activeFilter === 'All'
      ? events
      : events.filter((event) => event.type === activeFilter)

  return (
    <section className="section page-enter">
      <div className="section-header" style={{ alignItems: 'flex-start', flexDirection: 'column', gap: '1rem' }}>
        <div>
          <h1 className="section-title">Events</h1>
          <p style={{ color: 'var(--muted)', marginTop: '0.5rem' }}>
            Browse festivals, music gatherings, food experiences, and cultural celebrations.
          </p>
        </div>

        <FilterBar
          filters={filters}
          active={activeFilter}
          onChange={setActiveFilter}
        />
      </div>

      <div className="cards-grid">
        {filteredEvents.map((event) => (
          <EventCard key={event.id} event={event} />
        ))}
      </div>
    </section>
  )
}

export default EventsPage