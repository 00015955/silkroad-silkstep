"use client"

import { Sun, Banknote, Train, Shirt, UtensilsCrossed, Wifi } from "lucide-react"
import { Card, CardContent } from "@/components/ui/card"
import { Badge } from "@/components/ui/badge"
import { useTranslations } from "@/lib/i18n"

export function EssentialsSection() {
  const t = useTranslations()

  const essentials = [
    {
      id: 1,
      icon: Sun,
      color: "text-gold",
      bgColor: "bg-gold/10",
      ...t.essentials.items.bestTime,
    },
    {
      id: 2,
      icon: Banknote,
      color: "text-azure",
      bgColor: "bg-azure/10",
      ...t.essentials.items.money,
    },
    {
      id: 3,
      icon: Train,
      color: "text-terracotta",
      bgColor: "bg-terracotta/10",
      ...t.essentials.items.transport,
    },
    {
      id: 4,
      icon: Shirt,
      color: "text-azure",
      bgColor: "bg-azure/10",
      ...t.essentials.items.etiquette,
    },
    {
      id: 5,
      icon: UtensilsCrossed,
      color: "text-gold",
      bgColor: "bg-gold/10",
      ...t.essentials.items.food,
    },
    {
      id: 6,
      icon: Wifi,
      color: "text-terracotta",
      bgColor: "bg-terracotta/10",
      ...t.essentials.items.connectivity,
    },
  ]

  return (
    <section className="py-20 md:py-32 bg-sand-light/30">
      <div className="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
        {/* Section Header */}
        <div className="text-center mb-16">
          <Badge variant="secondary" className="mb-4 rounded-full px-4 py-1 bg-sand-light text-muted-foreground">
            {t.essentials.badge}
          </Badge>
          <h2 className="font-serif text-3xl sm:text-4xl md:text-5xl font-medium text-foreground text-balance">
            {t.essentials.title}
          </h2>
          <p className="mt-4 text-lg text-muted-foreground max-w-2xl mx-auto">
            {t.essentials.subtitle}
          </p>
        </div>

        {/* Essentials Grid */}
        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
          {essentials.map((item) => (
            <Card 
              key={item.id} 
              className="group bg-card border-border hover:shadow-lg hover:border-azure/20 transition-all duration-300 rounded-3xl"
            >
              <CardContent className="p-6">
                {/* Icon */}
                <div className={`inline-flex items-center justify-center w-12 h-12 rounded-2xl ${item.bgColor} mb-4`}>
                  <item.icon className={`h-6 w-6 ${item.color}`} />
                </div>

                {/* Title */}
                <h3 className="text-sm font-medium text-muted-foreground mb-1">
                  {item.title}
                </h3>

                {/* Value */}
                <p className="font-serif text-xl font-semibold text-foreground mb-3">
                  {item.value}
                </p>

                {/* Description */}
                <p className="text-sm text-muted-foreground leading-relaxed">
                  {item.description}
                </p>
              </CardContent>
            </Card>
          ))}
        </div>
      </div>
    </section>
  )
}
