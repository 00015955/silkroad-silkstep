'use client'

import { useTheme } from 'next-themes'
import { Toaster as Sonner, ToasterProps } from 'sonner'

const Toaster = ({ ...props }: ToasterProps) => {
  const { theme = 'system' } = useTheme()

  return (
    <Sonner
      theme={theme as ToasterProps['theme']}
      className="toaster group"
      style={
        {
          '--normal-bg': 'var(--popover)',
          '--normal-text': 'var(--popover-foreground)',
          '--normal-border': 'var(--border)',
        } as React.CSSProperties
      }
      {...props}
    />
  )
}
// the toaster is used to display notifications to the user. 
//It can be used to display success messages, error messages, or any other type of message that you want to display to the user. 
//The toaster can be customized to fit the design of your application, and it can be used in any part of your application where you want to display a notification.
export { Toaster }
