"use client";

import { useState, useRef, useEffect } from "react";
import { Send, X, Sparkles, Loader2, Minimize2, Maximize2 } from "lucide-react";
import { sendAIChat } from "@/lib/baseurl";
import { useI18n } from "@/lib/i18n";

interface Message {
  id: string;
  text: string;
  isUser: boolean;
  timestamp: Date;
}

interface AIChatProps {
  isOpen: boolean;
  onClose: () => void;
  destinationSlug?: string;
}


const formatMessage = (text: string): string => {
  if (!text) return "";
  
  // to make HTML presentation better, we can replace some tags with Tailwind classes:
  // <h3> -> <div class="font-bold">
  // <p> -> <div>
  // <ul>, <li> -> <ul class="list-disc ml-4">
  // <strong> -> <span class="font-semibold">
  
  let formatted = text
    // Headings
    .replace(/<h3>(.*?)<\/h3>/g, '<div class="font-bold text-base mb-2">$1</div>')
    .replace(/<h4>(.*?)<\/h4>/g, '<div class="font-semibold text-sm mb-1">$1</div>')
    // Paragraphs
    .replace(/<p>(.*?)<\/p>/g, '<div class="mb-2">$1</div>')
    // Bold
    .replace(/<strong>(.*?)<\/strong>/g, '<span class="font-semibold">$1</span>')
    // Lists
    .replace(/<ul>(.*?)<\/ul>/gs, '<ul class="list-disc ml-4 space-y-1 mb-2">$1</ul>')
    .replace(/<li>(.*?)<\/li>/g, '<li class="text-sm">$1</li>')
    // Line breaks
    .replace(/<br\s*\/?>/g, '<br />');
  
  return formatted;
};

// Plain text version 
const stripHtmlTags = (text: string): string => {
  if (!text) return "";
  return text.replace(/<[^>]*>/g, '');
};

export function AIChat({ isOpen, onClose, destinationSlug }: AIChatProps) {
  const { locale } = useI18n();
  const [messages, setMessages] = useState<Message[]>([
    {
      id: "1",
      text: "Hello! I'm your AI travel assistant. Ask me anything about traveling in Uzbekistan!",
      isUser: false,
      timestamp: new Date(),
    },
  ]);
  const [inputValue, setInputValue] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const [isMinimized, setIsMinimized] = useState(false);
  const messagesEndRef = useRef<HTMLDivElement>(null);
  const inputRef = useRef<HTMLInputElement>(null);

  useEffect(() => {
    if (isOpen && !isMinimized) {
      setTimeout(() => {
        inputRef.current?.focus();
      }, 100);
    }
  }, [isOpen, isMinimized]);

  useEffect(() => {
    messagesEndRef.current?.scrollIntoView({ behavior: "smooth" });
  }, [messages]);

  const sendMessage = async () => {
    if (!inputValue.trim() || isLoading) return;

    const userMessage: Message = {
      id: Date.now().toString(),
      text: inputValue.trim(),
      isUser: true,
      timestamp: new Date(),
    };

    setMessages((prev) => [...prev, userMessage]);
    setInputValue("");
    setIsLoading(true);

    try {
      const data = await sendAIChat(userMessage.text, destinationSlug, locale);

      // AI answers in HTML format, but we can also keep a plain text version for better presentation if needed
      const aiMessage: Message = {
        id: (Date.now() + 1).toString(),
        text: data.message || "I'm here to help! What would you like to know?",
        isUser: false,
        timestamp: new Date(),
      };

      setMessages((prev) => [...prev, aiMessage]);
    } catch (error) {
      console.error("AI Chat error:", error);
      const errorMessage: Message = {
        id: (Date.now() + 1).toString(),
        text: "Sorry, I'm having trouble connecting. Please try again later.",
        isUser: false,
        timestamp: new Date(),
      };
      setMessages((prev) => [...prev, errorMessage]);
    } finally {
      setIsLoading(false);
    }
  };

  const handleKeyPress = (e: React.KeyboardEvent) => {
    if (e.key === "Enter" && !e.shiftKey) {
      e.preventDefault();
      sendMessage();
    }
  };

  if (!isOpen) return null;

  return (
    <>
      {/* Overlay */}
      <div
        className="fixed inset-0 bg-black/50 z-50 backdrop-blur-sm"
        onClick={onClose}
      />
      
      {/* Chat Window */}
      <div
        className={`fixed bottom-20 right-6 z-50 bg-white rounded-2xl shadow-2xl transition-all duration-300 ${
          isMinimized ? "w-80" : "w-[90vw] max-w-md"
        }`}
        style={{ height: isMinimized ? "auto" : "560px", maxHeight: "80vh" }}
      >
        {/* Header */}
        <div className="flex items-center justify-between p-4 border-b border-gray-200 bg-gradient-to-r from-[#2F6F7E] to-[#1a4a55] rounded-t-2xl">
          <div className="flex items-center gap-2">
            <div className="w-8 h-8 rounded-full bg-white/20 flex items-center justify-center">
              <Sparkles className="w-4 h-4 text-white" />
            </div>
            <div>
              <h3 className="font-semibold text-white">AI Travel Assistant</h3>
              <p className="text-xs text-white/70">Powered by Silkroad AI</p>
            </div>
          </div>
          <div className="flex items-center gap-1">
            {/* <button
              onClick={() => setIsMinimized(!isMinimized)}
              className="p-1.5 rounded-lg hover:bg-white/20 transition-colors text-white"
            >
              {isMinimized ? <Maximize2 className="w-4 h-4" /> : <Minimize2 className="w-4 h-4" />}
            </button> */}
            <button
              onClick={onClose}
              className="p-1.5 rounded-lg hover:bg-white/20 transition-colors text-white"
            >
              <X className="w-4 h-4" />
            </button>
          </div>
        </div>

        {!isMinimized && (
          <>
            {/* Messages */}
            <div className="flex-1 overflow-y-auto p-4 space-y-3" style={{ height: "calc(100% - 130px)" }}>
              {messages.map((message) => (
                <div
                  key={message.id}
                  className={`flex ${message.isUser ? "justify-end" : "justify-start"}`}
                >
                  <div
                    className={`max-w-[85%] rounded-2xl px-4 py-2.5 ${
                      message.isUser
                        ? "bg-[#2F6F7E] text-white rounded-br-sm"
                        : "bg-gray-100 text-gray-800 rounded-bl-sm"
                    }`}
                  >
                    {message.isUser ? (
                      <p className="text-sm break-words">{message.text}</p>
                    ) : (
                      <div 
                        className="text-sm break-words prose prose-sm max-w-none"
                        dangerouslySetInnerHTML={{ 
                          __html: formatMessage(message.text) 
                        }}
                        style={{
                          '& p': { marginBottom: '0.5rem' },
                          '& ul': { marginLeft: '1rem', marginBottom: '0.5rem' },
                          '& li': { marginBottom: '0.25rem' },
                        }}
                      />
                    )}
                    <p className="text-xs opacity-70 mt-1">
                      {message.timestamp.toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" })}
                    </p>
                  </div>
                </div>
              ))}
              {isLoading && (
                <div className="flex justify-start">
                  <div className="bg-gray-100 rounded-2xl rounded-bl-sm px-4 py-2.5">
                    <Loader2 className="w-4 h-4 animate-spin text-[#2F6F7E]" />
                  </div>
                </div>
              )}
              <div ref={messagesEndRef} />
            </div>

            {/* Input */}
            <div className="p-4 border-t border-gray-200 bg-white rounded-b-2xl">
              <div className="flex gap-2">
                <input
                  ref={inputRef}
                  type="text"
                  value={inputValue}
                  onChange={(e) => setInputValue(e.target.value)}
                  onKeyPress={handleKeyPress}
                  placeholder="Ask me anything..."
                  className="flex-1 px-4 py-2.5 border border-gray-200 rounded-full focus:outline-none focus:ring-2 focus:ring-[#2F6F7E] focus:border-transparent text-sm bg-white"
                  disabled={isLoading}
                />
                <button
                  onClick={sendMessage}
                  disabled={!inputValue.trim() || isLoading}
                  className="p-2.5 bg-[#2F6F7E] text-white rounded-full hover:bg-[#2F6F7E]/90 transition-colors disabled:opacity-50 disabled:cursor-not-allowed flex-shrink-0"
                >
                  <Send className="w-5 h-5" />
                </button>
              </div>
              {destinationSlug && (
                <p className="text-xs text-gray-400 mt-2 truncate">
                  Asking about: {destinationSlug}
                </p>
              )}
            </div>
          </>
        )}
      </div>
    </>
  );
}
// AIChat component provides an interactive chat interface for users to ask questions about traveling in Uzbekistan. 
//It handles user input, displays messages from both the user and the AI assistant, and manages the chat window's open/close state. 
//The AI responses are formatted to support basic HTML tags for better presentation.