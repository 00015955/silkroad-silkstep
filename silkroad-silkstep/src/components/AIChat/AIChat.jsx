import { useEffect, useState } from 'react'

function AIChat({ initialMessage = '' }) {
  const [messages, setMessages] = useState([
    { role: 'assistant', content: 'Hi! Ask me about destinations, culture, or travel in Uzbekistan.' },
  ])
  const [input, setInput] = useState('')
  const [loading, setLoading] = useState(false)

  const sendMessage = async (text) => {
    if (!text.trim()) return

    setMessages((prev) => [...prev, { role: 'user', content: text }])
    setInput('')
    setLoading(true)

    // Temporary fake assistant response
    setTimeout(() => {
      setMessages((prev) => [
        ...prev,
        {
          role: 'assistant',
          content: `This is a placeholder AI reply for: "${text}". Later you can connect this to a real backend or API.`,
        },
      ])
      setLoading(false)
    }, 800)
  }

  useEffect(() => {
    if (initialMessage) {
      sendMessage(initialMessage)
    }
  }, [initialMessage])

  return (
    <div
      style={{
        background: 'var(--surface)',
        border: '1px solid var(--border)',
        borderRadius: 'var(--radius)',
        padding: '1rem',
      }}
    >
      <div style={{ display: 'flex', gap: '0.5rem', flexWrap: 'wrap', marginBottom: '1rem' }}>
        <button className="btn btn-ghost" onClick={() => sendMessage('Tell me about Samarkand')}>
          Samarkand
        </button>
        <button className="btn btn-ghost" onClick={() => sendMessage('Best time to visit Uzbekistan')}>
          Best time to visit
        </button>
        <button className="btn btn-ghost" onClick={() => sendMessage('What to do in Bukhara')}>
          Bukhara
        </button>
      </div>

      <div style={{ display: 'flex', flexDirection: 'column', gap: '0.75rem', marginBottom: '1rem' }}>
        {messages.map((message, index) => (
          <div
            key={index}
            style={{
              padding: '0.75rem 1rem',
              borderRadius: '12px',
              background: message.role === 'user' ? 'var(--accent-light)' : '#f5f5f5',
              alignSelf: message.role === 'user' ? 'flex-end' : 'flex-start',
              maxWidth: '80%',
            }}
          >
            {message.content}
          </div>
        ))}

        {loading && <div style={{ color: 'var(--muted)' }}>AI is typing...</div>}
      </div>

      <form
        onSubmit={(e) => {
          e.preventDefault()
          sendMessage(input)
        }}
        style={{ display: 'flex', gap: '0.75rem' }}
      >
        <input
          value={input}
          onChange={(e) => setInput(e.target.value)}
          placeholder="Ask about travel in Uzbekistan..."
          style={{ flex: 1, padding: '0.8rem' }}
        />
        <button type="submit" className="btn btn-accent">
          Send
        </button>
      </form>
    </div>
  )
}

export default AIChat