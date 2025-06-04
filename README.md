# :fishing_pole_and_fish: Alure

A tool that uses weather information from a specified area to recommend bass fishing lures. This tool was designed primarily for U.S. freshwater fishing and uses real-time weather to generate a water temperature estimate and recommend lures based on proven fishing strategies.

Alure is available to use at [alure.app](https://alure-iota.vercel.app/)

*Beware the backend service may require about a minute to start, please be patient.*

## :camera: Screenshots
![Alure Menu](https://github.com/user-attachments/assets/44e28c31-f824-4021-a4d8-0fbc28f6fcc4)

![Alure Search VA](https://github.com/user-attachments/assets/6e265034-8281-4bd9-bc1b-543a1fed9dcd)

![Alure Search WA](https://github.com/user-attachments/assets/4d2b42da-a33b-4650-9c98-ff0def9dc9bd)

![Alure Catalog](https://github.com/user-attachments/assets/4fa901de-83dd-4585-8e6c-296053ec5b28)

## :brain: How It Works

Lure effectiveness primarily depends on the current water temperature and other weather patterns. Thus, this tool uses the user-provided location (city, state, zipcode, country, etc.) to obtain the real-time weather of the area and computes a water temperature estimate. A match between various lure types is obtained by matching the water temperature estimate to a temperature range. Each temperature range encapsulates the varied fishing conditions caused by the water temperature and is associated with a score for each lure. 

### :fish: Lures

| Lure Type         | Description                                                                                     |
|------------------ |-------------------------------------------------------------------------------------------------|
| **Spinner Bait**  | Mimics small baitfish with metallic blades that spin and vibrate in water.                      | 
| **Swim Bait**     | Fish-shaped lures with realistic motion.                                                        |
| **Jerk Bait**     | Minnow-shaped with erratic, twitchy action.                                                     |
| **Hard Topwater** | Surface baits like poppers and spooks that cause surface commotion.                             | 
| **Crank Bait**    | Diving lures with rattles and variable depth control.                                           |
| **Spoon**         | Flashy metal lures that flutter like injured baitfish.                                          | 
| **Buzz Bait**     | Loud, splashing surface lure with spinning blade.                                               |
| **Plastic Worm**  | Soft, wiggly bait effective at all depths.                                                      |
| **Creature Bait** | Mimics crayfish, lizards, or leeches with flailing appendages.                                  |
| **Grub**          | Small, subtle baits with a tail that wiggles on retrieve.                                       |
| **Jig**           | Weighted hook with skirt, good for mimicking bottom-dwelling prey.                              |
| **Tube**          | Hollow bait with tentacles that flutter naturally.                                              |
| **Frog**          | Hollow-bodied lure skimming the surface like a real frog.                                       |

### :droplet: Water Temperature Estimate

Water temperatures are estimated using air temperatures from the past 5 days. Recent days are prioritized using a weighted averages. The weighted averages adjust for how quickly water temperatures change based on the air temperature. Temperature trends are also encapsulated, accounting for recent warming or cooling as both can alter the current water temperature. 

**Estimated Water Temp** = *(Weighted Avg. Air Temp x Temperature Coefficient)* + *(Trend Adjustment)*


## :toolbox: Tech Stack

- Frontend: React
- Backend: Java Spring Boot
- API: [Weather API](https://www.weatherapi.com/)
