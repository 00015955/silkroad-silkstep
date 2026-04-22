import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Calendar, MapPin, Users, TrendingUp } from "lucide-react"

const stats = [
  {
    title: "Total Destinations",
    value: "3",
    change: "+0 this month",
    icon: MapPin,
    color: "text-azure",
    bgColor: "bg-azure/10",
  },
  {
    title: "Active Events",
    value: "4",
    change: "+2 this month",
    icon: Calendar,
    color: "text-gold",
    bgColor: "bg-gold/10",
  },
  {
    title: "Page Views",
    value: "12.5k",
    change: "+18% vs last month",
    icon: TrendingUp,
    color: "text-terracotta",
    bgColor: "bg-terracotta/10",
  },
  {
    title: "Newsletter Subscribers",
    value: "856",
    change: "+43 this week",
    icon: Users,
    color: "text-azure",
    bgColor: "bg-azure/10",
  },
]

const recentActivity = [
  { action: "Updated Navruz Festival event", time: "2 hours ago" },
  { action: "Added new Samarkand highlights", time: "5 hours ago" },
  { action: "Synced with iTicket data", time: "1 day ago" },
  { action: "Published Plov Championship event", time: "2 days ago" },
]

export default function AdminDashboard() {
  return (
    <div className="space-y-8">
      {/* Header */}
      <div>
        <h1 className="font-serif text-3xl font-semibold text-foreground">Dashboard</h1>
        <p className="text-muted-foreground mt-1">
          Welcome back! Here&apos;s an overview of your travel platform.
        </p>
      </div>

      {/* Stats Grid */}
      <div className="grid gap-4 md:grid-cols-2 lg:grid-cols-4">
        {stats.map((stat) => (
          <Card key={stat.title} className="rounded-2xl border-border">
            <CardContent className="p-6">
              <div className="flex items-center justify-between">
                <div>
                  <p className="text-sm text-muted-foreground">{stat.title}</p>
                  <p className="font-serif text-3xl font-semibold text-foreground mt-1">
                    {stat.value}
                  </p>
                  <p className="text-xs text-muted-foreground mt-1">{stat.change}</p>
                </div>
                <div className={`h-12 w-12 rounded-2xl ${stat.bgColor} flex items-center justify-center`}>
                  <stat.icon className={`h-6 w-6 ${stat.color}`} />
                </div>
              </div>
            </CardContent>
          </Card>
        ))}
      </div>

      {/* Recent Activity & Quick Actions */}
      <div className="grid gap-6 lg:grid-cols-2">
        {/* Recent Activity */}
        <Card className="rounded-2xl border-border">
          <CardHeader>
            <CardTitle className="font-serif text-xl">Recent Activity</CardTitle>
          </CardHeader>
          <CardContent>
            <ul className="space-y-4">
              {recentActivity.map((activity, index) => (
                <li key={index} className="flex items-start gap-3">
                  <div className="h-2 w-2 rounded-full bg-azure mt-2" />
                  <div className="flex-1">
                    <p className="text-sm text-foreground">{activity.action}</p>
                    <p className="text-xs text-muted-foreground mt-0.5">{activity.time}</p>
                  </div>
                </li>
              ))}
            </ul>
          </CardContent>
        </Card>

        {/* Quick Actions */}
        <Card className="rounded-2xl border-border">
          <CardHeader>
            <CardTitle className="font-serif text-xl">Quick Actions</CardTitle>
          </CardHeader>
          <CardContent>
            <div className="grid gap-3">
              <button className="flex items-center gap-3 p-4 rounded-xl bg-sand-light hover:bg-sand-light/80 transition-colors text-left">
                <Calendar className="h-5 w-5 text-azure" />
                <div>
                  <p className="text-sm font-medium text-foreground">Add New Event</p>
                  <p className="text-xs text-muted-foreground">Create a cultural event or festival</p>
                </div>
              </button>
              <button className="flex items-center gap-3 p-4 rounded-xl bg-sand-light hover:bg-sand-light/80 transition-colors text-left">
                <MapPin className="h-5 w-5 text-gold" />
                <div>
                  <p className="text-sm font-medium text-foreground">Update Destination</p>
                  <p className="text-xs text-muted-foreground">Edit city information and highlights</p>
                </div>
              </button>
              <button className="flex items-center gap-3 p-4 rounded-xl bg-sand-light hover:bg-sand-light/80 transition-colors text-left">
                <TrendingUp className="h-5 w-5 text-terracotta" />
                <div>
                  <p className="text-sm font-medium text-foreground">Sync iTicket Data</p>
                  <p className="text-xs text-muted-foreground">Import latest event information</p>
                </div>
              </button>
            </div>
          </CardContent>
        </Card>
      </div>
    </div>
  )
}
