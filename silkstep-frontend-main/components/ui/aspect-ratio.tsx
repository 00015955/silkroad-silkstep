'use client'

import * as AspectRatioPrimitive from '@radix-ui/react-aspect-ratio'

function AspectRatio({
  ...props
}: React.ComponentProps<typeof AspectRatioPrimitive.Root>) {
  return <AspectRatioPrimitive.Root data-slot="aspect-ratio" {...props} />
}
// The AspectRatio component is a wrapper around the Radix UI AspectRatio primitive. 
//It allows you to maintain a specific aspect ratio for its children, which is useful for embedding media like videos or images that need to fit within a certain space without distortion.
export { AspectRatio }
