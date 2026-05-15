# Project Plan

Sante-Price Index is a "Vendor’s Intelligence" tool. It aggregates city Mandi prices and adds a "Transport + Profit" margin to suggest a "Recommended Retail Price" (RRP) for the village, ensuring the vendor stays competitive and profitable.

Features:
- Price Watch: See today's Mandi prices for Onion, Tomato, etc.
- Profit Calc: Enter your transport cost -> Get suggested selling price.
- Price Board: A full-screen "Digital Slate" to show prices to customers.
- Trends: See if prices are likely to rise or fall (Simulated).

Technical:
- Math: Implement cost-plus pricing algorithms in Kotlin.
- UI: "Digital Slate" mode should be high-contrast (Yellow on Black).
- Mocking daily prices.
- Material Design 3, Jetpack Compose, Full Edge-to-Edge.

## Project Brief

# Project Brief: Sante
PriceIndex

SantePriceIndex is a "Vendor’s Intelligence" tool designed to empower small vendors in weekly markets (
Santes). By aggregating city Mandi prices and calculating fair margins, the app ensures vendors remain competitive and profitable while offering
 fair deals to rural consumers.

#### Features
1.  **Live Mandi Price Dashboard**: Displays current market prices for
 essential commodities like onions and tomatoes, providing a reliable baseline for vendors.
2.  **Dynamic Profit & RRP Calculator**: An easy-to-use tool where vendors input transport costs and expected wastage to generate a Recommended Retail Price (R
RP) using a cost-plus pricing algorithm.
3.  **High-Contrast Digital Price Board**: A full
-screen "Digital Slate" mode featuring high-contrast yellow text on a black background, ensuring prices are easily readable by customers from a
 distance in bright outdoor settings.
4.  **Market Trends Indicator**: A simplified visualization showing price trajectories (rising or falling
) to assist vendors with inventory planning.

#### High-Level Technical Stack
-   **Language**: Kotlin
-   **
UI Framework**: Jetpack Compose with **Material Design 3**
-   **Navigation**: **Jetpack Navigation 
3** (State-driven architecture)
-   **Adaptive Strategy**: **Compose Material Adaptive** library for seamless layouts across smartphones
, foldables, and tablets.
-   **Concurrency**: Kotlin Coroutines and Flow for reactive data handling.
-   
**Networking**: Retrofit and OkHttp for fetching daily price data from central sources.
-   **Architecture**: ViewModel
-based architecture to manage UI state and business logic (Profit calculations).

## Implementation Steps

### Task_1_Foundation_and_Dashboard: Initialize Material 3 theme with vibrant colors, implement Edge-to-Edge, and set up Navigation 3. Create the Mandi Price Dashboard with mocked data and simulated trend indicators.
- **Status:** COMPLETED
- **Updates:** Material 3 theme implemented with vibrant colors. Edge-to-edge display enabled. Navigation 3 structure set up with type-safe routes. Mandi Price Dashboard created with mocked data for commodities and simulated trend indicators. Compilation error in NavDisplay call fixed.
- **Acceptance Criteria:**
  - Material 3 theme applied with light/dark support
  - Edge-to-edge display enabled
  - Navigation 3 structure functional
  - Dashboard displays list of commodity prices and trends

### Task_2_Pricing_Logic_and_Digital_Slate: Develop the Profit & RRP Calculator using a cost-plus pricing algorithm. Implement the full-screen 'Digital Slate' mode with high-contrast yellow-on-black UI.
- **Status:** COMPLETED
- **Updates:** Profit & RRP Calculator implemented with cost-plus pricing algorithm. Digital Slate mode created with high-contrast yellow-on-black UI for better readability. PricingViewModel handles the business logic and state management. Integration with Navigation 3 completed.
- **Acceptance Criteria:**
  - RRP Calculator correctly computes selling price based on transport and profit
  - Digital Slate mode is high-contrast and readable
  - State management via ViewModel implemented
- **Duration:** N/A

### Task_3_Adaptive_UI_and_Branding: Implement adaptive layouts using Compose Material Adaptive for various screen sizes. Create an adaptive app icon reflecting the 'Sante' theme and finalize Material 3 UI polish.
- **Status:** IN_PROGRESS
- **Acceptance Criteria:**
  - Adaptive layouts work on phone and tablet/foldable simulators
  - Adaptive app icon created and functional
  - Vibrant, energetic color scheme integrated throughout
- **StartTime:** 2026-05-14 22:54:53 IST

### Task_4_Run_and_Verify: Perform a final build and run of the application to verify stability and adherence to the project brief. Ensure all features work as expected and the UI is consistent.
- **Status:** PENDING
- **Acceptance Criteria:**
  - Project builds successfully
  - App does not crash on startup or navigation
  - All features (Dashboard, Calculator, Slate) are functional
  - Alignment with Material Design 3 and project brief confirmed
  - Build pass

