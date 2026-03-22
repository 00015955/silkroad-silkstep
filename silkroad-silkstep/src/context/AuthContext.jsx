import { createContext, useContext, useState } from 'react'


const AuthContext = createContext(null)

// Provider component.
// It wraps the app and gives all child components access to auth state.
export function AuthProvider({ children }) {
  // Stores the current logged-in user.

  const [user, setUser] = useState(null)

  // Controls whether the auth modal is visible.
  const [showModal, setShowModal] = useState(false)

  // Logs the user in by saving their data.
  // Also closes the modal after successful login.
  const login = (userData) => {
    setUser(userData)
    setShowModal(false)
  }

  // Logs the user out by clearing the saved user.
  const logout = () => {
    setUser(null)
  }

  // Opens the login/signup modal.
  const openModal = () => {
    setShowModal(true)
  }

  // Closes the login/signup modal.
  const closeModal = () => {
    setShowModal(false)
  }

  
  return (
    <AuthContext.Provider
      value={{
        user,
        showModal,
        login,
        logout,
        openModal,
        closeModal,
      }}
    >
      {children}
    </AuthContext.Provider>
  )
}


export function useAuth() {
  return useContext(AuthContext)
}