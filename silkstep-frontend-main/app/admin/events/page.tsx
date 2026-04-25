"use client"

import { useState } from "react"
import { Plus, Search, MoreHorizontal, Pencil, Trash2, RefreshCw } from "lucide-react"
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
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select"

const initialEvents = [
  {
    id: "1",
    name: "Navruz Festival",
    date: "2025-03-21",
    location: "Samarkand",
    description: "Celebrate the Persian New Year with traditional music, dance, and sumalak cooking.",
    tags: ["festival", "culture"],
    status: "published",
  },
  {
    id: "2",
    name: "Silk & Spices Festival",
    date: "2025-04-15",
    location: "Bukhara",
    description: "Experience the revival of ancient Silk Road traditions with artisans and merchants.",
    tags: ["festival", "market"],
    status: "published",
  },
  {
    id: "3",
    name: "Plov Championship",
    date: "2025-05-08",
    location: "Tashkent",
    description: "Watch master chefs compete to create the perfect plov in giant cauldrons.",
    tags: ["food", "competition"],
    status: "draft",
  },
  {
    id: "4",
    name: "Asrlar Sadosi",
    date: "2025-06-12",
    location: "Samarkand",
    description: "A grand music festival celebrating the echoes of centuries of Central Asian heritage.",
    tags: ["music", "culture"],
    status: "published",
  },
]

const tagColors: Record<string, string> = {
  festival: "bg-terracotta/20 text-terracotta",
  culture: "bg-azure/20 text-azure",
  market: "bg-gold/20 text-gold",
  food: "bg-gold/20 text-gold",
  competition: "bg-azure/20 text-azure",
  music: "bg-terracotta/20 text-terracotta",
}

export default function EventsPage() {
  const [events, setEvents] = useState(initialEvents)
  const [searchQuery, setSearchQuery] = useState("")
  const [dialogOpen, setDialogOpen] = useState(false)
  const [editingEvent, setEditingEvent] = useState<typeof initialEvents[0] | null>(null)

  const filteredEvents = events.filter(
    (event) =>
      event.name.toLowerCase().includes(searchQuery.toLowerCase()) ||
      event.location.toLowerCase().includes(searchQuery.toLowerCase())
  )

  const handleDelete = (id: string) => {
    setEvents(events.filter((e) => e.id !== id))
  }

  const handleEdit = (event: typeof initialEvents[0]) => {
    setEditingEvent(event)
    setDialogOpen(true)
  }

  const handleSave = (formData: FormData) => {
    const newEvent = {
      id: editingEvent?.id || String(Date.now()),
      name: formData.get("name") as string,
      date: formData.get("date") as string,
      location: formData.get("location") as string,
      description: formData.get("description") as string,
      tags: (formData.get("tags") as string).split(",").map((t) => t.trim()),
      status: formData.get("status") as string,
    }

    if (editingEvent) {
      setEvents(events.map((e) => (e.id === editingEvent.id ? newEvent : e)))
    } else {
      setEvents([...events, newEvent])
    }
    setDialogOpen(false)
    setEditingEvent(null)
  }

  return (
    <div className="space-y-8">
      {/* Header */}
      <div className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">
        <div>
          <h1 className="font-serif text-3xl font-semibold text-foreground">Events</h1>
          <p className="text-muted-foreground mt-1">
            Manage cultural events and festivals
          </p>
        </div>
        <div className="flex items-center gap-3">
          <Button variant="outline" size="sm" className="gap-2 rounded-full">
            <RefreshCw className="h-4 w-4" />
            Sync iTicket
          </Button>
          <Dialog open={dialogOpen} onOpenChange={(open) => {
            setDialogOpen(open)
            if (!open) setEditingEvent(null)
          }}>
            <DialogTrigger asChild>
              <Button size="sm" className="gap-2 bg-azure hover:bg-azure-light text-primary-foreground rounded-full">
                <Plus className="h-4 w-4" />
                Add Event
              </Button>
            </DialogTrigger>
            <DialogContent className="sm:max-w-md rounded-2xl">
              <DialogHeader>
                <DialogTitle className="font-serif text-xl">
                  {editingEvent ? "Edit Event" : "Add New Event"}
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
                  <Label htmlFor="name">Event Name</Label>
                  <Input
                    id="name"
                    name="name"
                    defaultValue={editingEvent?.name}
                    placeholder="Enter event name"
                    className="rounded-xl"
                    required
                  />
                </div>
                <div className="grid grid-cols-2 gap-4">
                  <div className="space-y-2">
                    <Label htmlFor="date">Date</Label>
                    <Input
                      id="date"
                      name="date"
                      type="date"
                      defaultValue={editingEvent?.date}
                      className="rounded-xl"
                      required
                    />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="location">Location</Label>
                    <Select name="location" defaultValue={editingEvent?.location || "Samarkand"}>
                      <SelectTrigger className="rounded-xl">
                        <SelectValue placeholder="Select location" />
                      </SelectTrigger>
                      <SelectContent>
                        <SelectItem value="Samarkand">Samarkand</SelectItem>
                        <SelectItem value="Bukhara">Bukhara</SelectItem>
                        <SelectItem value="Khiva">Khiva</SelectItem>
                        <SelectItem value="Tashkent">Tashkent</SelectItem>
                      </SelectContent>
                    </Select>
                  </div>
                </div>
                <div className="space-y-2">
                  <Label htmlFor="description">Description</Label>
                  <Textarea
                    id="description"
                    name="description"
                    defaultValue={editingEvent?.description}
                    placeholder="Describe the event..."
                    className="rounded-xl"
                    rows={3}
                  />
                </div>
                <div className="grid grid-cols-2 gap-4">
                  <div className="space-y-2">
                    <Label htmlFor="tags">Tags (comma-separated)</Label>
                    <Input
                      id="tags"
                      name="tags"
                      defaultValue={editingEvent?.tags.join(", ")}
                      placeholder="festival, music"
                      className="rounded-xl"
                    />
                  </div>
                  <div className="space-y-2">
                    <Label htmlFor="status">Status</Label>
                    <Select name="status" defaultValue={editingEvent?.status || "draft"}>
                      <SelectTrigger className="rounded-xl">
                        <SelectValue placeholder="Select status" />
                      </SelectTrigger>
                      <SelectContent>
                        <SelectItem value="draft">Draft</SelectItem>
                        <SelectItem value="published">Published</SelectItem>
                      </SelectContent>
                    </Select>
                  </div>
                </div>
                <div className="flex justify-end gap-3 pt-4">
                  <Button type="button" variant="ghost" onClick={() => setDialogOpen(false)}>
                    Cancel
                  </Button>
                  <Button type="submit" className="bg-azure hover:bg-azure-light text-primary-foreground rounded-full px-6">
                    {editingEvent ? "Save Changes" : "Create Event"}
                  </Button>
                </div>
              </form>
            </DialogContent>
          </Dialog>
        </div>
      </div>

      {/* Search */}
      <div className="relative max-w-md">
        <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-muted-foreground" />
        <Input
          placeholder="Search events..."
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
          className="pl-9 rounded-full"
        />
      </div>

      {/* Events Table */}
      <Card className="rounded-2xl border-border overflow-hidden">
        <CardHeader className="bg-sand-light/50">
          <CardTitle className="font-serif text-lg">All Events ({filteredEvents.length})</CardTitle>
        </CardHeader>
        <CardContent className="p-0">
          <div className="overflow-x-auto">
            <table className="w-full">
              <thead>
                <tr className="border-b border-border">
                  <th className="text-left text-sm font-medium text-muted-foreground px-6 py-4">Event</th>
                  <th className="text-left text-sm font-medium text-muted-foreground px-6 py-4">Date</th>
                  <th className="text-left text-sm font-medium text-muted-foreground px-6 py-4">Location</th>
                  <th className="text-left text-sm font-medium text-muted-foreground px-6 py-4">Tags</th>
                  <th className="text-left text-sm font-medium text-muted-foreground px-6 py-4">Status</th>
                  <th className="text-right text-sm font-medium text-muted-foreground px-6 py-4">Actions</th>
                </tr>
              </thead>
              <tbody>
                {filteredEvents.map((event) => (
                  <tr key={event.id} className="border-b border-border last:border-0 hover:bg-sand-light/30 transition-colors">
                    <td className="px-6 py-4">
                      <div>
                        <p className="font-medium text-foreground">{event.name}</p>
                        <p className="text-sm text-muted-foreground line-clamp-1">{event.description}</p>
                      </div>
                    </td>
                    <td className="px-6 py-4 text-sm text-foreground">
                      {new Date(event.date).toLocaleDateString("en-US", {
                        month: "short",
                        day: "numeric",
                        year: "numeric",
                      })}
                    </td>
                    <td className="px-6 py-4 text-sm text-foreground">{event.location}</td>
                    <td className="px-6 py-4">
                      <div className="flex flex-wrap gap-1">
                        {event.tags.map((tag) => (
                          <span
                            key={tag}
                            className={`text-xs font-medium px-2 py-0.5 rounded-full ${tagColors[tag] || "bg-muted text-muted-foreground"}`}
                          >
                            {tag}
                          </span>
                        ))}
                      </div>
                    </td>
                    <td className="px-6 py-4">
                      <span
                        className={`text-xs font-medium px-2.5 py-1 rounded-full ${
                          event.status === "published"
                            ? "bg-azure/20 text-azure"
                            : "bg-muted text-muted-foreground"
                        }`}
                      >
                        {event.status}
                      </span>
                    </td>
                    <td className="px-6 py-4 text-right">
                      <DropdownMenu>
                        <DropdownMenuTrigger asChild>
                          <Button variant="ghost" size="icon" className="h-8 w-8">
                            <MoreHorizontal className="h-4 w-4" />
                            <span className="sr-only">Actions</span>
                          </Button>
                        </DropdownMenuTrigger>
                        <DropdownMenuContent align="end">
                          <DropdownMenuItem onClick={() => handleEdit(event)}>
                            <Pencil className="h-4 w-4 mr-2" />
                            Edit
                          </DropdownMenuItem>
                          <DropdownMenuItem
                            className="text-destructive"
                            onClick={() => handleDelete(event.id)}
                          >
                            <Trash2 className="h-4 w-4 mr-2" />
                            Delete
                          </DropdownMenuItem>
                        </DropdownMenuContent>
                      </DropdownMenu>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </CardContent>
      </Card>
    </div>
  )
}


// This page provides a comprehensive interface for managing cultural events. 
//It includes features for adding, editing, and deleting events, as well as searching and filtering through the list of events. 
//The design is clean and user-friendly, making it easy for administrators to keep the event information up-to-date and organized.