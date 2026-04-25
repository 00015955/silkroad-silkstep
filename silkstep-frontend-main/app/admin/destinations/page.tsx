"use client"

import { useState } from "react"
import Image from "next/image"
import { Plus, Search, MoreHorizontal, Pencil, Trash2, Star, Shield } from "lucide-react"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu"
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog"
import { Label } from "@/components/ui/label"
import { Textarea } from "@/components/ui/textarea"
import { Switch } from "@/components/ui/switch"

const initialDestinations = [
  {
    id: "1",
    name: "Samarkand",
    description: "The crown jewel of the Silk Road, home to Registan Square and Shah-i-Zinda.",
    image: "/destinations/samarkand.jpg",
    rating: 4.9,
    duration: "3-4 days",
    unesco: true,
    highlights: ["Registan Square", "Shah-i-Zinda", "Gur-e-Amir"],
    published: true,
  },
  {
    id: "2",
    name: "Bukhara",
    description: "A living museum with over 140 architectural monuments and ancient trading domes.",
    image: "/destinations/bukhara.jpg",
    rating: 4.8,
    duration: "2-3 days",
    unesco: true,
    highlights: ["Kalyan Minaret", "Ark Fortress", "Lyab-i-Hauz"],
    published: true,
  },
  {
    id: "3",
    name: "Khiva",
    description: "Step into a time capsule at the perfectly preserved Itchan Kala inner city.",
    image: "/destinations/khiva.jpg",
    rating: 4.9,
    duration: "1-2 days",
    unesco: true,
    highlights: ["Itchan Kala", "Kalta Minor", "Tosh-Hovli Palace"],
    published: true,
  },
]

export default function DestinationsPage() {
  const [destinations, setDestinations] = useState(initialDestinations)
  const [searchQuery, setSearchQuery] = useState("")
  const [dialogOpen, setDialogOpen] = useState(false)
  const [editingDestination, setEditingDestination] = useState<typeof initialDestinations[0] | null>(null)

  const filteredDestinations = destinations.filter((dest) =>
    dest.name.toLowerCase().includes(searchQuery.toLowerCase())
  )

  const handleDelete = (id: string) => {
    setDestinations(destinations.filter((d) => d.id !== id))
  }

  const handleEdit = (destination: typeof initialDestinations[0]) => {
    setEditingDestination(destination)
    setDialogOpen(true)
  }

  const handleSave = (formData: FormData) => {
    const newDestination = {
      id: editingDestination?.id || String(Date.now()),
      name: formData.get("name") as string,
      description: formData.get("description") as string,
      image: editingDestination?.image || "/destinations/placeholder.jpg",
      rating: parseFloat(formData.get("rating") as string) || 4.5,
      duration: formData.get("duration") as string,
      unesco: formData.get("unesco") === "on",
      highlights: (formData.get("highlights") as string).split(",").map((h) => h.trim()),
      published: formData.get("published") === "on",
    }

    if (editingDestination) {
      setDestinations(destinations.map((d) => (d.id === editingDestination.id ? newDestination : d)))
    } else {
      setDestinations([...destinations, newDestination])
    }
    setDialogOpen(false)
    setEditingDestination(null)
  }

  return (
    <div className="space-y-8">
      {/* Header */}
      <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">
        <div>
          <h1 className="font-serif text-3xl font-semibold text-foreground">Destinations</h1>
          <p className="text-muted-foreground mt-1">
            Manage city information and highlights
          </p>
        </div>
        <Dialog open={dialogOpen} onOpenChange={(open) => {
          setDialogOpen(open)
          if (!open) setEditingDestination(null)
        }}>
          <DialogTrigger asChild>
            <Button size="sm" className="gap-2 bg-azure hover:bg-azure-light text-primary-foreground rounded-full">
              <Plus className="h-4 w-4" />
              Add Destination
            </Button>
          </DialogTrigger>
          <DialogContent className="sm:max-w-md rounded-2xl">
            <DialogHeader>
              <DialogTitle className="font-serif text-xl">
                {editingDestination ? "Edit Destination" : "Add New Destination"}
              </DialogTitle>
            </DialogHeader>
            <form
              className="space-y-4"
              onSubmit={(e) => {
                e.preventDefault()
                handleSave(new FormData(e.currentTarget))
              }}
            >
              <div className="space-y-2">
                <Label htmlFor="name">City Name</Label>
                <Input
                  id="name"
                  name="name"
                  defaultValue={editingDestination?.name}
                  placeholder="Enter city name"
                  className="rounded-xl"
                  required
                />
              </div>
              <div className="space-y-2">
                <Label htmlFor="description">Description</Label>
                <Textarea
                  id="description"
                  name="description"
                  defaultValue={editingDestination?.description}
                  placeholder="Describe the destination..."
                  className="rounded-xl"
                  rows={3}
                />
              </div>
              <div className="grid grid-cols-2 gap-4">
                <div className="space-y-2">
                  <Label htmlFor="duration">Recommended Duration</Label>
                  <Input
                    id="duration"
                    name="duration"
                    defaultValue={editingDestination?.duration}
                    placeholder="e.g., 2-3 days"
                    className="rounded-xl"
                  />
                </div>
                <div className="space-y-2">
                  <Label htmlFor="rating">Rating</Label>
                  <Input
                    id="rating"
                    name="rating"
                    type="number"
                    step="0.1"
                    min="0"
                    max="5"
                    defaultValue={editingDestination?.rating}
                    placeholder="4.5"
                    className="rounded-xl"
                  />
                </div>
              </div>
              <div className="space-y-2">
                <Label htmlFor="highlights">Highlights (comma-separated)</Label>
                <Input
                  id="highlights"
                  name="highlights"
                  defaultValue={editingDestination?.highlights.join(", ")}
                  placeholder="Landmark 1, Landmark 2"
                  className="rounded-xl"
                />
              </div>
              <div className="flex items-center justify-between py-2">
                <div className="flex items-center gap-3">
                  <Switch
                    id="unesco"
                    name="unesco"
                    defaultChecked={editingDestination?.unesco}
                  />
                  <Label htmlFor="unesco" className="cursor-pointer">UNESCO World Heritage Site</Label>
                </div>
              </div>
              <div className="flex items-center justify-between py-2">
                <div className="flex items-center gap-3">
                  <Switch
                    id="published"
                    name="published"
                    defaultChecked={editingDestination?.published ?? true}
                  />
                  <Label htmlFor="published" className="cursor-pointer">Published</Label>
                </div>
              </div>
              <div className="flex justify-end gap-3 pt-4">
                <Button type="button" variant="ghost" onClick={() => setDialogOpen(false)}>
                  Cancel
                </Button>
                <Button type="submit" className="bg-azure hover:bg-azure-light text-primary-foreground rounded-full px-6">
                  {editingDestination ? "Save Changes" : "Create Destination"}
                </Button>
              </div>
            </form>
          </DialogContent>
        </Dialog>
      </div>

      {/* Search */}
      <div className="relative max-w-md">
        <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-muted-foreground" />
        <Input
          placeholder="Search destinations..."
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
          className="pl-9 rounded-full"
        />
      </div>

      {/* Destinations Grid */}
      <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
        {filteredDestinations.map((destination) => (
          <Card key={destination.id} className="rounded-2xl border-border overflow-hidden group">
            {/* Image */}
            <div className="relative h-40 bg-sand overflow-hidden">
              <Image
                src={destination.image}
                alt={destination.name}
                fill
                className="object-cover"
              />
              {destination.unesco && (
                <div className="absolute top-3 left-3 flex items-center gap-1.5 bg-card/90 backdrop-blur-sm rounded-full px-2.5 py-1">
                  <Shield className="h-3 w-3 text-azure" />
                  <span className="text-xs font-medium text-foreground">UNESCO</span>
                </div>
              )}
              {!destination.published && (
                <div className="absolute top-3 right-3 bg-muted text-muted-foreground text-xs font-medium px-2 py-1 rounded-full">
                  Draft
                </div>
              )}
            </div>

            <CardContent className="p-5">
              <div className="flex items-start justify-between mb-3">
                <div>
                  <h3 className="font-serif text-xl font-semibold text-foreground">
                    {destination.name}
                  </h3>
                  <div className="flex items-center gap-1 mt-1 text-sm text-muted-foreground">
                    <Star className="h-3.5 w-3.5 fill-gold text-gold" />
                    <span>{destination.rating}</span>
                    <span className="mx-1">·</span>
                    <span>{destination.duration}</span>
                  </div>
                </div>
                <DropdownMenu>
                  <DropdownMenuTrigger asChild>
                    <Button variant="ghost" size="icon" className="h-8 w-8">
                      <MoreHorizontal className="h-4 w-4" />
                      <span className="sr-only">Actions</span>
                    </Button>
                  </DropdownMenuTrigger>
                  <DropdownMenuContent align="end">
                    <DropdownMenuItem onClick={() => handleEdit(destination)}>
                      <Pencil className="h-4 w-4 mr-2" />
                      Edit
                    </DropdownMenuItem>
                    <DropdownMenuItem
                      className="text-destructive"
                      onClick={() => handleDelete(destination.id)}
                    >
                      <Trash2 className="h-4 w-4 mr-2" />
                      Delete
                    </DropdownMenuItem>
                  </DropdownMenuContent>
                </DropdownMenu>
              </div>

              <p className="text-sm text-muted-foreground line-clamp-2 mb-4">
                {destination.description}
              </p>

              <div className="flex flex-wrap gap-1.5">
                {destination.highlights.slice(0, 3).map((highlight) => (
                  <span
                    key={highlight}
                    className="text-xs bg-sand-light text-muted-foreground px-2 py-0.5 rounded-full"
                  >
                    {highlight}
                  </span>
                ))}
              </div>
            </CardContent>
          </Card>
        ))}
      </div>
    </div>
  )
}

// This page provides a comprehensive interface for managing the destinations featured on the SilkStep website. 
//Admins can view, search, add, edit, and delete destination entries, each of which includes details like name, description, image, rating, duration, UNESCO status, highlights, and publication status. 
//The design emphasizes usability and clarity while maintaining a visually appealing layout consistent with the overall admin dashboard.