import { Link, NavLink } from 'react-router-dom'
import { useEffect, useState } from 'react'
import { useAuth } from '../../context/AuthContext.jsx'

function Navbar() {
  const [scrolled, setScrolled] = useState(false)
  const [menuOpen, setMenuOpen] = useState(false)

  const { user, logout, openLogin } = useAuth()

  useEffect(() => {
    const handleScroll = () => {
      setScrolled(window.scrollY > 10)
    }

    window.addEventListener('scroll', handleScroll)
    return () => window.removeEventListener('scroll', handleScroll)
  }, [])

  return (
    <header
      style={{
        position: 'fixed',
        top: 0,
        left: 0,
        width: '100%',
        zIndex: 1000,
        background: scrolled ? 'rgba(255,255,255,0.85)' : 'transparent',
        backdropFilter: scrolled ? 'blur(10px)' : 'none',
        borderBottom: scrolled ? '1px solid var(--border)' : 'none',
      }}
    >
      <div
        className="section"
        style={{
          paddingTop: '1rem',
          paddingBottom: '1rem',
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'space-between',
        }}
      >
        <Link to="/">
          <strong style={{ fontFamily: 'var(--font-display)', fontSize: '1.5rem' }}>
            Silkroad
          </strong>
        </Link>

        <nav style={{ display: 'flex', gap: '1rem', alignItems: 'center' }}>
          <NavLink to="/">Home</NavLink>
          <NavLink to="/destinations">Destinations</NavLink>
          <NavLink to="/events">Events</NavLink>
          <NavLink to="/ai-guide">AI Guide</NavLink>

          {user ? (
            <button className="btn btn-ghost" onClick={logout}>
              Logout
            </button>
          ) : (
            <button className="btn btn-accent" onClick={openLogin}>
              Login
            </button>
          )}

          <button
            className="btn btn-ghost"
            onClick={() => setMenuOpen(!menuOpen)}
            style={{ display: 'none' }}
          >
            ☰
          </button>
        </nav>
      </div>

      {menuOpen && (
        <div style={{ padding: '1rem 2rem', background: 'white', borderTop: '1px solid var(--border)' }}>
          <div style={{ display: 'flex', flexDirection: 'column', gap: '1rem' }}>
            <NavLink to="/" onClick={() => setMenuOpen(false)}>Home</NavLink>
            <NavLink to="/destinations" onClick={() => setMenuOpen(false)}>Destinations</NavLink>
            <NavLink to="/events" onClick={() => setMenuOpen(false)}>Events</NavLink>
            <NavLink to="/ai-guide" onClick={() => setMenuOpen(false)}>AI Guide</NavLink>
          </div>
        </div>
      )}
    </header>
  )
}

export default Navbar