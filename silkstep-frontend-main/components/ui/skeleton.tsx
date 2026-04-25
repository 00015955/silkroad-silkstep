import { cn } from '@/lib/utils'

function Skeleton({ className, ...props }: React.ComponentProps<'div'>) {
  return (
    <div
      data-slot="skeleton"
      className={cn('bg-accent animate-pulse rounded-md', className)}
      {...props}
    />
  )
}
// the skeleton is used to show a loading state for a component, such as a card or a list item. 
//It can also be used to show a loading state for a page or a section of a page. 
//The skeleton is typically used when the data is being fetched from an API or when the component is being rendered for the first time. 
//It can also be used to show a loading state for a component that is being updated with new data. 
//The skeleton can be customized with different colors, shapes, and sizes to match the design of the application.
export { Skeleton }
