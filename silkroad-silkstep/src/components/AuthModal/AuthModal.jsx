import { useState } from 'react'
import { useAuth } from '../../context/AuthContext.jsx'

function AuthModal() {
  const { showModal, modalMode, closeModal, login, register } = useAuth()

  const [name, setName] = useState('')
  const [email, setEmail] = useState('')
  const [error, setError] = useState('')

  if (!showModal) return null

  const handleSubmit = (e) => {
    e.preventDefault()

    if (!email.trim()) {
      setError('Email is required.')
      return
    }

    if (modalMode === 'register' && !name.trim()) {
      setError('Name is required.')
      return
    }

    setError('')

    const userData = {
      name: name || 'Guest User',
      email,
    }

    if (modalMode === 'register') {
      register(userData)
    } else {
      login(userData)
    }

    setName('')
    setEmail('')
  }

  return (
    <div
      onClick={closeModal}
      style={{
        position: 'fixed',
        inset: 0,
        background: 'rgba(0,0,0,0.45)',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        zIndex: 2000,
        padding: '1rem',
      }}
    >
      <div
        onClick={(e) => e.stopPropagation()}
        style={{
          background: 'white',
          width: '100%',
          maxWidth: '420px',
          borderRadius: 'var(--radius-lg)',
          padding: '1.5rem',
          boxShadow: 'var(--shadow-lg)',
        }}
      >
        <h2>{modalMode === 'register' ? 'Create account' : 'Login'}</h2>

        <form onSubmit={handleSubmit} style={{ marginTop: '1rem' }}>
          {modalMode === 'register' && (
            <div style={{ marginBottom: '1rem' }}>
              <label>Name</label>
              <input
                value={name}
                onChange={(e) => setName(e.target.value)}
                style={{ width: '100%', marginTop: '0.4rem', padding: '0.75rem' }}
              />
            </div>
          )}

          <div style={{ marginBottom: '1rem' }}>
            <label>Email</label>
            <input
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              style={{ width: '100%', marginTop: '0.4rem', padding: '0.75rem' }}
            />
          </div>

          {error && (
            <p style={{ color: 'crimson', marginBottom: '1rem' }}>{error}</p>
          )}

          <div style={{ display: 'flex', gap: '0.75rem' }}>
            <button type="submit" className="btn btn-accent">
              {modalMode === 'register' ? 'Register' : 'Login'}
            </button>
            <button type="button" className="btn btn-ghost" onClick={closeModal}>
              Cancel
            </button>
          </div>
        </form>
      </div>
    </div>
  )
}

export default AuthModal 