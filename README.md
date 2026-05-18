

🐄 Pashu-Aahar (Balanced Feed Calculator)

An Android application built with Kotlin to help small farmers create cost-effective, balanced cattle feed at home. By utilizing locally available grains, **Pashu-Aahar** empowers farmers to maximize milk yield while significantly reducing expenses associated with expensive branded feed.

📱 Project Overview

This project was undertaken as part of the **MindMatrix VTU Internship Program**. It addresses the critical problem of high cattle feed costs faced by small-scale dairy farmers. The app acts as an intelligent "Cattle Nutrition Calculator," suggesting personalized feed recipes based on the cow's breed and desired milk production targets.

 ✨ Features & User Flow

*   **🐮 Cow Profile:**
    *   Input key details about your livestock using an easy-to-use stepper UI: Breed (e.g., Jersey, Desi), Age, and Weight.

*   **🥗 Recipe Generator:**
    *   Get a scientifically calculated feed recipe (e.g., "Mix 2kg Maize + 1kg Cottonseed Cake") tailored to your cow's nutritional needs and milk yield target.
    *   Recipes dynamically adjust based on your input.

*   **💰 Cost Tracker:**
    *   Easily compare the cost of creating "Home-made" feed versus buying from the market.
    *   Visualize your "Cost Savings" over time with intuitive charts.

*   **🩺 Veterinary Tips:**
    *   Access short, helpful videos on essential topics like cow hygiene and proper fodder storage practices.

 💻 Technical Implementation

This section outlines the core technologies and libraries used to bring Pashu-Aahar to life.

| Technology | Purpose |
| :--- | :--- |
| **Language** | **Kotlin**: The primary language for all application logic. |
| **UI Framework**| **Jetpack Compose / XML Views**: Used to build the user interface, featuring a Stepper for data entry. |
| **Charting** | **MPAndroidChart**: Utilized to render beautiful and informative charts for tracking cost savings. |
| **Core Logic** | Custom algorithms in Kotlin to calculate optimal protein/energy ratios for cattle nutrition. |
| **Offline First**| The app stores formulas locally, ensuring functionality even without an internet connection after initial load. |

### 🎯 Impact Goals

The primary goals of this project are to:
*   **Promote Dairy Prosperity:** Increase the profitability for small milk producers.
*   **Encourage Self-Reliance:** Reduce dependence on costly industrial feed by enabling the use of local resources.
*   **Advance Scientific Farming:** Make veterinary science accessible and applicable to every village cowshed.

### 🚀 Success Criteria

To ensure the project's success and value to its users, we focused on these key criteria:
*   ✅ **Dynamic Calculations:** Feed recipes change accurately based on the user's "Milk Yield Target."
*   ✅ **Fully Offline-Capable:** The app functions without an internet connection once the core formulas are loaded.
*   ✅ **User-Friendly UI:** The interface uses clear icons to represent different types of fodder, making it accessible for all users.
