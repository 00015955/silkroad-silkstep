import { Routes, Route } from 'react-router-dom'
import Navbar from './components/Navbar/Navbar.jsx'
import AuthModal from './components/AuthModal/AuthModal.jsx'

// Temporary page placeholders until real page files are created
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
      <Navbar />

      <main>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/destinations" element={<DestinationsPage />} />
          <Route path="/events" element={<EventsPage />} />
          <Route path="/ai-guide" element={<AIGuidePage />} />
        </Routes>
      </main>

      <AuthModal />
    </div>
  )
}

export default App