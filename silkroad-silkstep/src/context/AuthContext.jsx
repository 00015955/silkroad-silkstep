import { createContext, useContext, useState } from 'react'


const AuthContext = createContext(null)

// Wrapping the app
export function AuthProvider({ children }) {
  // Logged-in user object; null means not logged in
  const [user, setUser] = useState(null)

  // Controls whether the auth modal is visible
  const [showModal, setShowModal] = useState(false)

  // Can be 'login' or 'register'
  const [modalMode, setModalMode] = useState('login')

  // Log in a user and close modal
  const login = (userData) => {
    setUser(userData)
    setShowModal(false)
  }

  // Register a user and close modal
  // For now, this behaves like login
  const register = (userData) => {
    setUser(userData)
    setShowModal(false)
  }

  // Log out the user
  const logout = () => {
    setUser(null)
  }

  // Open modal in login mode
  const openLogin = () => {
    setModalMode('login')
    setShowModal(true)
  }

  // Open modal in register mode
  const openRegister = () => {
    setModalMode('register')
    setShowModal(true)
  }

  // Generic open modal
  const openModal = () => {
    setShowModal(true)
  }

  // Close modal
  const closeModal = () => {
    setShowModal(false)
  }

  return (
    <AuthContext.Provider
      value={{
        user,
        showModal,
        modalMode,
        login,
        register,
        logout,
        openModal,
        openLogin,
        openRegister,
        closeModal,
      }}
    >
      {children}
    </AuthContext.Provider>
  )
}

// Custom hook for easier usage
export function useAuth() {
  return useContext(AuthContext)
}