import { useNavigate } from 'react-router-dom'

function DestinationCard({ destination }) {
  const navigate = useNavigate()

  const handleClick = () => {
    navigate(`/ai-guide?ask=${encodeURIComponent(`Tell me about visiting ${destination.name}`)}`)
  }

  return (
    <article
      onClick={handleClick}
      style={{
        background: 'var(--surface)',
        border: '1px solid var(--border)',
        borderRadius: 'var(--radius)',
        overflow: 'hidden',
        cursor: 'pointer',
        boxShadow: 'var(--shadow-sm)',
      }}
    >
      <div
        style={{
          background: destination.bgColor,
          minHeight: '140px',
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
          fontSize: '3rem',
        }}
      >
        {destination.emoji}
      </div>

      <div style={{ padding: '1rem' }}>
        <div style={{ marginBottom: '0.5rem', color: 'var(--accent)', fontSize: '0.85rem' }}>
          {destination.tag}
        </div>
        <h3>{destination.name}</h3>
        <p style={{ color: 'var(--muted)', marginTop: '0.5rem' }}>
          {destination.description}
        </p>
      </div>
    </article>
  )
}

export default DestinationCard 