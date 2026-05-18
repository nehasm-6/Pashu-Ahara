PASHU-AAHAR 

Complete Guide & Standard Operating Procedures (SOP) 

Project Version: 1.0 

Last Updated: May 2026 

Document Type: Project Implementation Guide & Operations Manual 

 

TABLE OF CONTENTS 

Project Overview 

Problem Statement & Solution 

Architecture Overview 

System Requirements 

User Roles & Permissions 

Setup & Installation Guide 

Administrator Guide 

Farmer/User Guide 

Technical Specifications 

Data Management & Security 

Troubleshooting Guide 

Maintenance & Support 

 

PROJECT OVERVIEW 

Vision 

To digitally empower dairy farmers by providing an intelligent cattle nutrition platform that generates balanced and cost-effective feed recipes using locally available ingredients. 

Mission 

To improve cattle health, milk production, and farmer profitability through a low-cost, easy-to-use, offline-capable mobile application. 

Key Objectives 

Generate balanced cattle feed recipes dynamically 

Reduce dependency on expensive branded cattle feed 

Improve milk yield and animal health 

Track feed costs and savings 

Support rural farmers with bilingual access 

Provide offline functionality for remote villages 

Target Users 

Dairy Farmers 

Maintain cattle profiles 

Generate feed recipes 

Track milk production 

Compare feed costs 

Veterinary Advisors 

Share nutrition guidance 

Monitor cattle health recommendations 

Provide feed management tips 

Agricultural Organizations 

Promote scientific dairy farming 

Analyze feed usage patterns 

Support rural development programs 

 

PROBLEM STATEMENT & SOLUTION 

The Problem 

Small dairy farmers often purchase expensive industrial cattle feed without understanding how to prepare nutritionally balanced feed at home using local grains and fodder. 

This creates several issues: 

High feeding costs 

Reduced profitability 

Lack of nutritional awareness 

Poor cattle health management 

Inconsistent milk production 

Dependence on commercial feed brands 

The Solution: Pashu-Aahar Platform 

Problem 

Solution 

Expensive branded feed 

Balanced homemade feed recipes 

Lack of nutritional knowledge 

Intelligent feed calculator 

Poor feed planning 

Dynamic recipe generation 

No cost comparison 

Feed cost tracker 

Rural accessibility issues 

Offline-capable Android app 

Language barriers 

Bilingual support 

Key Features 

✅ Cow profile management 

✅ AI-based feed recipe generation 

✅ Milk yield-based nutrition calculation 

✅ Cost comparison system 

✅ Veterinary guidance 

✅ Offline support 

✅ Bilingual interface (English & Kannada) 

✅ Feed analytics and charts 

 

ARCHITECTURE OVERVIEW 

Technology Stack 

Layer 

Technology 

Purpose 

Frontend 

Android (Kotlin/XML) 

Mobile application 

Backend 

Firebase Realtime Database 

Data synchronization 

Authentication 

Firebase Authentication 

User login 

Storage 

Firebase Cloud Storage 

User and cattle data 

Analytics 

Firebase Analytics 

Usage tracking 

Charts 

MPAndroidChart 

Visualization 

AI Logic 

GenAI APIs / Kotlin Logic 

Feed recommendation engine 

System Architecture Diagram 

┌───────────────────────────────────────────────┐ 
│                 FARMER USER                  │ 
│                                               │ 
│  ┌─────────────────────────────────────────┐  │ 
│  │   Cow Profile Management Screen         │  │ 
│  │   - Breed                               │  │ 
│  │   - Weight                              │  │ 
│  │   - Milk Yield                          │  │ 
│  └─────────────────┬──────────────────────┘  │ 
└────────────────────┼─────────────────────────┘ 
                    │ 
                    ▼ 
┌───────────────────────────────────────────────┐ 
│          FEED CALCULATION ENGINE             │ 
│                                               │ 
│  - Nutrition formulas                         │ 
│  - Protein calculations                       │ 
│  - Energy calculations                        │ 
│  - Cost optimization                          │ 
└────────────────────┬─────────────────────────┘ 
                    │ 
                    ▼ 
┌───────────────────────────────────────────────┐ 
│           FIREBASE DATABASE                  │ 
│                                               │ 
│ /users                                        │ 
│ /cattleProfiles                               │ 
│ /feedRecipes                                  │ 
│ /costTracking                                 │ 
│ /healthTips                                   │ 
└───────────────────────────────────────────────┘ 
 

Data Flow 

Farmer enters cattle information 

App processes nutrition requirements 

Feed recipe is generated dynamically 

Cost comparison is displayed 

Data stored locally and synced with Firebase 

Offline-First Architecture 

Data cached locally on device 

Feed generation works offline 

Sync occurs automatically when internet returns 

Farmer can access old recipes without internet 

 

SYSTEM REQUIREMENTS 

Hardware Requirements 

Minimum 

Android 7.0+ 

2GB RAM 

100MB storage 

Internet connection for sync 

Recommended 

Android 10+ 

4GB RAM 

500MB storage 

4G/WiFi connection 

Development Requirements 

# Android Development 
- Android Studio Hedgehog+ 
- Kotlin 1.9+ 
- Gradle 8+ 
 
# Firebase 
- Firebase CLI 
- Firebase Realtime Database 
- Firebase Authentication 
 
# Additional Libraries 
- MPAndroidChart 
- Retrofit 
- Glide/Picasso 
 

 

USER ROLES & PERMISSIONS 

1. Administrator 

Responsibilities 

Manage farmers 

Monitor usage analytics 

Update feed formulas 

Maintain veterinary tips 

Generate reports 

Permissions 

✅ Manage users 

✅ Update nutritional formulas 

✅ Access analytics 

✅ Export reports 

❌ Cannot modify user private data without permission 

Sample Workflow 

1. Login as Admin 
2. Open dashboard 
3. Monitor farmer activities 
4. Update feed recommendation logic 
5. Generate monthly reports 
 

 

2. Farmer/User 

Responsibilities 

Add cattle profiles 

Generate feed recipes 

Track feed costs 

Monitor milk production 

Permissions 

✅ Create cattle profiles 

✅ Generate recipes 

✅ View reports 

✅ Save feed history 

❌ Cannot modify admin settings 

Sample Workflow 

1. Login to app 
2. Add cow details 
3. Enter milk yield 
4. Generate feed recipe 
5. Compare feed costs 
6. Save report 
 

 

SETUP & INSTALLATION GUIDE 

Phase 1: Firebase Setup 

Step 1: Create Firebase Project 

1. Open firebase.google.com 
2. Click Create Project 
3. Project Name: Pashu-Aahar 
4. Enable Firebase services 
 

Step 2: Enable Services 

- Realtime Database 
- Firebase Authentication 
- Cloud Storage 
- Analytics 
 

Step 3: Download Configuration Files 

Android: 
- Download google-services.json 
 
iOS: 
- Download GoogleService-Info.plist 
 

 

Phase 2: Android Studio Setup 

1. Clone repository 
2. Open in Android Studio 
3. Sync Gradle 
4. Connect Firebase 
5. Run application 
 

 

ADMINISTRATOR GUIDE 

Dashboard Features 

Quick Stats 

Total Users 

Active Farmers 

Feed Recipes Generated 

Cost Savings Analytics 

Main Actions 

Manage users 

Update formulas 

Add veterinary tips 

Generate reports 

Export analytics 

Common Admin Tasks 

Task 1: Update Feed Formula 

1. Login as Admin 
2. Open Formula Management 
3. Select cattle category 
4. Update nutrition values 
5. Save changes 
 

Task 2: View Analytics 

1. Dashboard → Analytics 
2. View feed usage reports 
3. Analyze milk production trends 
4. Export data if needed 
 

 

FARMER/USER GUIDE 

User Interface Overview 

Home Screen 

Cow Profiles 

Generate Feed Recipe 

Cost Tracker 

Veterinary Tips 

Saved Reports 

How to Generate Feed Recipe 

Step 1: Open app 
Step 2: Tap Generate Recipe 
Step 3: Enter: 
  - Breed 
  - Age 
  - Weight 
  - Milk Yield 
Step 4: Tap Generate 
Step 5: App displays balanced feed recipe 
 

Example Recipe 

Milk Yield: 10 Liters/day 
 
Suggested Feed: 
- Maize: 2kg 
- Cottonseed Cake: 1kg 
- Wheat Bran: 1kg 
- Green Fodder: 5kg 
 

Cost Tracking 

1. Open Cost Tracker 
2. Enter market feed price 
3. Compare homemade feed cost 
4. View total savings 
 

Veterinary Tips Section 

Includes: 

Hygiene tips 

Vaccination reminders 

Fodder storage guidance 

Water management tips 

 

TECHNICAL SPECIFICATIONS 

Database Schema 

Users Collection 

{ 
 "userId": "user_001", 
 "name": "Ravi", 
 "phone": "9876543210", 
 "language": "Kannada" 
} 
 

Cattle Profiles Collection 

{ 
 "cowId": "cow_001", 
 "breed": "Jersey", 
 "weight": 450, 
 "milkYield": 10, 
 "ownerId": "user_001" 
} 
 

Feed Recipes Collection 

{ 
 "recipeId": "recipe_001", 
 "cowId": "cow_001", 
 "maize": "2kg", 
 "cottonseedCake": "1kg", 
 "fodder": "5kg" 
} 
 

 

DATA MANAGEMENT & SECURITY 

Data Security 

Firebase Authentication used for login 

Data encrypted during transfer 

Cloud backup enabled 

Offline data protected locally 

User Privacy 

Collected Data: 

User name 

Phone number 

Cattle details 

Feed history 

Backup System 

Automatic backups: 

Firebase cloud backup 

Local device caching 

Export support (PDF/CSV) 

 

TROUBLESHOOTING GUIDE 

Issue 1: Recipe Not Generating 

Symptoms 

Generate button not working 

Recipe screen empty 

Solutions 

1. Check internet connection 
2. Verify all fields entered 
3. Restart app 
4. Clear app cache 
 

 

Issue 2: Firebase Sync Failed 

Symptoms 

Data not syncing 

Saved recipes missing 

Solutions 

1. Reconnect internet 
2. Restart application 
3. Login again 
4. Verify Firebase configuration 
 

 

Issue 3: App Crashes 

Solutions 

1. Clear storage 
2. Update application 
3. Restart device 
4. Reinstall app 
 

 

MAINTENANCE & SUPPORT 

Daily Maintenance 

1. Check Firebase status 
2. Monitor app usage 
3. Verify backups 
4. Review crash reports 
 

Weekly Maintenance 

1. Update feed formulas 
2. Review analytics 
3. Test app functionality 
4. Backup database 
 

Monthly Maintenance 

1. Review performance metrics 
2. Update dependencies 
3. Fix bugs 
4. Improve UI/UX 
 

 

PERFORMANCE METRICS 

Metric 

Target 

App startup time 

<3 seconds 

Recipe generation 

<2 seconds 

Firebase sync 

<1 second 

Crash rate 

<1% 

 

APPENDIX A: Glossary 

Term 

Meaning 

Feed Recipe 

Balanced cattle nutrition plan 

Milk Yield 

Daily milk production 

Fodder 

Animal feed material 

Firebase 

Backend cloud platform 

Offline-First 

Works without internet 

 

APPENDIX B: FAQ 

Q: Can the app work offline? 

A: Yes. Recipes and saved data work offline. 

Q: Which cattle breeds are supported? 

A: Jersey, Desi, Holstein and custom breeds. 

Q: Can I save feed history? 

A: Yes. Feed history is stored locally and on Firebase. 

Q: Does the app support Kannada? 

A: Yes. English and Kannada are supported. 

 

APPENDIX C: Contact & Support Information 

Support Channels 

Channel 

Purpose 

support@pashu-aahar.com 

Technical support 

WhatsApp Support 

Emergency assistance 

In-App Help 

FAQ and tutorials 

 

DOCUMENT VERSION HISTORY 

Version 

Date 

Changes 

1.0 

May 2026 

Initial release 

 

Disclaimer 

This document is prepared for the VTU Internship Project implementation and educational purposes. Information may change in future releases. 

 

END OF DOCUMENT 

 
