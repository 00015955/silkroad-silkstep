import { Routes, Route } from 'react-router-dom'

// Temporary placeholder Navbar
function Navbar() {
  return (
    <header style={{ padding: '1rem 2rem', borderBottom: '1px solid #e8e4dd' }}>
      <strong>Silkroad</strong>
    </header>
  )
}

// Temporary placeholder AuthModal
function AuthModal() {
  return null
}

// Temporary page components
function HomePage() {
  return <h1 style={{ padding: '2rem' }}>Home Page</h1>
}

function DestinationsPage() {
  return <h1 style={{ padding: '2rem' }}>Destinations Page</h1>
}

function EventsPage() {
  return <h1 style={{ padding: '2rem' }}>Events Page</h1>
}

function AIGuidePage() {
  return <h1 style={{ padding: '2rem' }}>AI Guide Page</h1>
}

function App() {
  return (
    <div className="app">
      {/* Shows on every page */}
      <Navbar />

      <main>
        {/* Switches pages based on URL */}
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/destinations" element={<DestinationsPage />} />
          <Route path="/events" element={<EventsPage />} />
          <Route path="/ai-guide" element={<AIGuidePage />} />
        </Routes>
      </main>

      {/* Will later become the real auth popup */}
      <AuthModal />
    </div>
  )
}

export default App 