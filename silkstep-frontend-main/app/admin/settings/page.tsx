"use client"

import { useState } from "react"
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import { Switch } from "@/components/ui/switch"
import { Textarea } from "@/components/ui/textarea"
import { ExternalLink, Save } from "lucide-react"

export default function SettingsPage() {
  const [saved, setSaved] = useState(false)

  const handleSave = () => {
    setSaved(true)
    setTimeout(() => setSaved(false), 2000)
  }

  return (
    <div className="space-y-8 max-w-4xl">
      {/* Header */}
      <div>
        <h1 className="font-serif text-3xl font-semibold text-foreground">Settings</h1>
        <p className="text-muted-foreground mt-1">
          Configure your travel platform settings
        </p>
      </div>

      {/* Site Settings */}
      <Card className="rounded-2xl border-border">
        <CardHeader>
          <CardTitle className="font-serif text-xl">Site Settings</CardTitle>
          <CardDescription>Basic configuration for your travel platform</CardDescription>
        </CardHeader>
        <CardContent className="space-y-6">
          <div className="space-y-2">
            <Label htmlFor="siteName">Site Name</Label>
            <Input
              id="siteName"
              defaultValue="SilkStep"
              className="rounded-xl max-w-md"
            />
          </div>
          <div className="space-y-2">
            <Label htmlFor="siteDescription">Site Description</Label>
            <Textarea
              id="siteDescription"
              defaultValue="Premium travel platform for exploring the ancient Silk Road. Discover Samarkand, Bukhara, and Khiva."
              className="rounded-xl max-w-md"
              rows={3}
            />
          </div>
          <div className="space-y-2">
            <Label htmlFor="contactEmail">Contact Email</Label>
            <Input
              id="contactEmail"
              type="email"
              defaultValue="hello@silkstep.uz"
              className="rounded-xl max-w-md"
            />
          </div>
        </CardContent>
      </Card>

      {/* iTicket Integration */}
      <Card className="rounded-2xl border-border">
        <CardHeader>
          <CardTitle className="font-serif text-xl">iTicket Integration</CardTitle>
          <CardDescription>Connect to iTicket for automatic event synchronization</CardDescription>
        </CardHeader>
        <CardContent className="space-y-6">
          <div className="flex items-center justify-between">
            <div>
              <Label>Enable Auto-Sync</Label>
              <p className="text-sm text-muted-foreground mt-0.5">
                Automatically sync events from iTicket daily
              </p>
            </div>
            <Switch defaultChecked />
          </div>
          <div className="space-y-2">
            <Label htmlFor="apiKey">iTicket API Key</Label>
            <Input
              id="apiKey"
              type="password"
              defaultValue="••••••••••••••••"
              className="rounded-xl max-w-md"
            />
          </div>
          <div className="flex items-center gap-4">
            <Button variant="outline" className="rounded-full gap-2">
              <ExternalLink className="h-4 w-4" />
              View iTicket Docs
            </Button>
            <span className="text-sm text-muted-foreground">
              Last sync: 2 hours ago
            </span>
          </div>
        </CardContent>
      </Card>

      {/* Notifications */}
      <Card className="rounded-2xl border-border">
        <CardHeader>
          <CardTitle className="font-serif text-xl">Notifications</CardTitle>
          <CardDescription>Configure email and system notifications</CardDescription>
        </CardHeader>
        <CardContent className="space-y-4">
          <div className="flex items-center justify-between py-2">
            <div>
              <Label>New Subscriber Alerts</Label>
              <p className="text-sm text-muted-foreground mt-0.5">
                Get notified when someone joins the newsletter
              </p>
            </div>
            <Switch defaultChecked />
          </div>
          <div className="flex items-center justify-between py-2">
            <div>
              <Label>Event Sync Notifications</Label>
              <p className="text-sm text-muted-foreground mt-0.5">
                Get notified when events are synced from iTicket
              </p>
            </div>
            <Switch />
          </div>
          <div className="flex items-center justify-between py-2">
            <div>
              <Label>Weekly Analytics Report</Label>
              <p className="text-sm text-muted-foreground mt-0.5">
                Receive a weekly summary of site performance
              </p>
            </div>
            <Switch defaultChecked />
          </div>
        </CardContent>
      </Card>

      {/* Save Button */}
      <div className="flex items-center gap-4">
        <Button 
          onClick={handleSave}
          className="bg-azure hover:bg-azure-light text-primary-foreground rounded-full gap-2 px-6"
        >
          <Save className="h-4 w-4" />
          {saved ? "Saved!" : "Save Changes"}
        </Button>
        {saved && (
          <span className="text-sm text-azure">Settings updated successfully</span>
        )}
      </div>
    </div>
  )
}

// This page provides a settings interface for the admin to configure site settings, iTicket integration, and notifications. 
//It uses state to show a temporary "Saved!" message when the save button is clicked.