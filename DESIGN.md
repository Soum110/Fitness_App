# FitQuest RPG — Vercel Design System Guidelines

This document outlines the core layout, styling, and design system tokens for FitQuest, adapted from the Vercel (Geist) Design System.

## 1. Color Palette

- **Background**: `#000000` (Pure Black). There are no background gradients.
- **Surface / Cards**: `#0A0A0A` (Soft Dark Gray).
- **Surface Hover**: `#121212` (Slightly lighter black/gray).
- **Border**: `#1F1F1F` (Dark/subtle borders) or `#2E2E2E` (Higher contrast borders).
- **Text Primary**: `#FAFAFA` (Pure off-white).
- **Text Secondary**: `#888888` (Medium Gray).
- **Text Tertiary / Muted**: `#666666` (Dark Gray).
- **Accent (Primary Brand)**: `#FFFFFF` (Pure White).
- **Accent (Secondary)**: `#0070F3` (Vercel Blue - used sparingly).

## 2. Corner Radii

- Standard Radius: `8.dp` (Used for cards, buttons, input fields).
- Large Radius: `12.dp` (Used for banner sections, top header layers).
- Small/Badge Radius: `4.dp` or `6.dp`.
- Circle: `50%` (For profile avatars, checkboxes).

## 3. Typography

- Headings: Tight letter-spacing (e.g. `-1.sp`), thick weight (`Bold` / `Black`).
- Numbers / Stats: Monospace-style geometric alignment (e.g. tracking for XP levels).
- Body: Geometric sans-serif, normal weights, clean line-height.

## 4. Components

### Cards (`RpgCard`)
- Flat background (`#0A0A0A`).
- 1px crisp outline border (`#1F1F1F` or `#2E2E2E`).
- No shadows or diffuse glows unless specifically triggered by progression level tiers.

### Inputs
- Flat `#000000` background.
- Outline border of `#1F1F1F`. On focus, transitions to solid `#FFFFFF` border.
- Placeholder text: `#666666`.

### Buttons
- Primary: Solid `#FFFFFF` background with `#000000` text, `8.dp` corner radius.
- Secondary: `#000000` background with `#1F1F1F` outline border, `#FFFFFF` text.

### Badges & Chips
- Flat backgrounds with fine thin borders matching the category, minimal padding.

## 5. RPG Progression Tiers (Glowing Levels)

To balance the Vercel minimalist style with the requested gamification system:
- **Base (Level 1 - 9)**: Outline is `#1F1F1F` (standard design border). No glow.
- **Iron Tier (Level 10 - 19)**: Outline is `#B0BEC5` (Silver/Iron). Extremely faint outer silver border.
- **Steel Tier (Level 20 - 29)**: Outline is `#66BB6A` (Green/Steel).
- **Crystal Tier (Level 30 - 39)**: Outline is `#42A5F5` (Blue/Crystal).
- **Diamond Tier (Level 40 - 49)**: Outline is `#AB47BC` (Purple/Diamond).
- **Gold Shadow (Level 50 - 69)**: Outline is `#FFD700` (Gold). Subtle golden outer glow.
- **Violet Monarch (Level 70 - 89)**: Outline is `#7E57C2` (Monarch Purple). Purple shimmer.
- **Radiant Arise (Level 90+)**: Moving/shimmering golden/white border brush with active gold glow.
