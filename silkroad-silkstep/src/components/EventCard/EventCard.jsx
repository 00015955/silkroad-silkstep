import { eventTypeColors } from '../../assets/data/events.js'

function EventCard({ event, compact = false }) {
  const colors = eventTypeColors[event.type] || {
    bg: '#f3f4f6',
    color: '#111827',
  }

  return (
    <article
      style={{
        background: 'var(--surface)',
        border: '1px solid var(--border)',
        borderRadius: 'var(--radius)',
        padding: '1rem',
        boxShadow: 'var(--shadow-sm)',
      }}
    >
      <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'start', gap: '1rem' }}>
        <div>
          <div style={{ color: 'var(--muted)', fontSize: '0.9rem' }}>
            {event.month} {event.day}, {event.year}
          </div>
          <h3 style={{ marginTop: '0.25rem' }}>{event.title}</h3>
          <div style={{ color: 'var(--muted)', marginTop: '0.25rem' }}>
            {event.location}
          </div>
        </div>

        <span
          style={{
            background: colors.bg,
            color: colors.color,
            padding: '0.35rem 0.6rem',
            borderRadius: '999px',
            fontSize: '0.8rem',
            fontWeight: 600,
            whiteSpace: 'nowrap',
          }}
        >
          {event.type}
        </span>
      </div>

      {!compact && (
        <p style={{ marginTop: '1rem', color: 'var(--muted)' }}>
          {event.description}
        </p>
      )}
    </article>
  )
}

export default EventCard 