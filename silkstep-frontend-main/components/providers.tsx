"use client"

import { I18nProvider } from "@/lib/i18n"
import type { ReactNode } from "react"

export function Providers({ children }: { children: ReactNode }) {
  return (
    <I18nProvider>
      {children}
    </I18nProvider>
  )
}

// This component is used in the root layout to wrap the entire application with the I18nProvider, ensuring that translations are available throughout the app.