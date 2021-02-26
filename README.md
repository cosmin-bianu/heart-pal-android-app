<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

<!-- PROJECT LOGO -->
<br />
<p align="center">
  <h3 align="center">Heart Pal Android App</h3>

  <p align="center">
  The Android App used to pair with the Heart Pal-enabled devices see <a href="https://github.com/cosmin-bianu/heart-pal-ai"> Heart Pal Artificial Intelligence</a> and <a href="https://github.com/cosmin-bianu/heart-pal-reader-module"> Heart Pal ECG Reader Module</a>.
    <br />
    <strong> This application is NOT a replacement for certified medical equimpent. </strong>
    <br />
    <br />
    <a href="https://github.com/cosmin-bianu/heart-pal-android-app/issues">Report a Bug</a>
    ·
    <a href="https://github.com/cosmin-bianu/heart-pal-android-app/issues">Request Feature</a>
  </p>
</p>

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
  <ol>
    <li>
      <a href="#disclaimer">Disclaimer</a>
    </li>
    <li>
      <a href="#about-the-project">About The Project</a>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
  </ol>
</details>

<!-- Disclaimer -->
## Disclaimer

**This application is NOT a replacement for certified medical equimpent.** 

This is merely a high school project. It is a prototype and likely unstable, use at your own discretion. This has **NOT** been extensively tested and has **NOT** been certified by any authority. We are not responsible for incorrect prediction and/or any outcomes.

<!-- ABOUT THE PROJECT -->
## About The Project

The project started as a high school practice business for the Junior Achievement Business Competition. It's purpose was to use machine learning and deep neural networks to detect arrhythmia in ECG readings. Paired with a Bluetooth device that is able to send ECG readings in real-time to the Android device, it's able to analyse the ECG stream and attempts to predict/detect heart failure. 

It has been awarded 1st at the Social Innovation Relay (National Stage) in Romania 🇷🇴.

<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple steps.

### Prerequisites 

* [Android Studio](https://developer.android.com/studio)
* Android 4.2 (minimum API Level 17) with [USB debugging](https://developer.android.com/studio/debug/dev-options) enabled
* [ECG Reader Module](https://github.com/cosmin-bianu/heart-pal-reader-module) (ECGRM)

### Installation

1. Clone ther repository
   ```sh
   git clone https://github.com/cosmin-bianu/heart-pal-android-app.git heart-pal-android-app
   ```
2. Open it in Android Studio
3. Modify the MAC address of the target ECGRM Bluetooth interface to match your configuration [here](https://github.com/cosmin-bianu/heart-pal-android-app/blob/d978c650f0e7af8022e26c1ed2b2eb5faef2291d/app/src/main/java/com/oneup/cosmin/xheart/activities/MainActivity.java#L43)
4. Connect your Android device to your computer (make sure USB debugging is enabled)
5. Run directly or build an apk (Android Developers guide [here](https://developer.android.com/studio/run))

<!-- USAGE EXAMPLES -->
## Usage

1. Start the ECGRM
2. Connect your Android device to the ECGRM via Bluetooth
3. Await for data to start displaying on the screen

<!-- ROADMAP -->
## Roadmap

See the [open issues](https://github.com/cosmin-bianu/heart-pal-android-app/issues) for a list of proposed features (and known issues).

<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<!-- LICENSE -->
## License

Distributed under the Apache 2.0 License. See `LICENSE` for more information.

<!-- CONTACT -->
## Contact

Cosmin Bianu

email: kitsune.cosmin@gmail.com

Project Link: [https://github.com/cosmin-bianu/heart-pal-android-app](https://github.com/cosmin-bianu/heart-pal-android-app)

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/cosmin-bianu/heart-pal-android-app.svg?style=for-the-badge
[contributors-url]: https://github.com/cosmin-bianu/heart-pal-android-app/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/cosmin-bianu/heart-pal-android-app.svg?style=for-the-badge
[forks-url]: https://github.com/cosmin-bianu/heart-pal-android-app/network/members
[stars-shield]: https://img.shields.io/github/stars/cosmin-bianu/heart-pal-android-app.svg?style=for-the-badge
[stars-url]: https://github.com/cosmin-bianu/heart-pal-android-app/stargazers
[issues-shield]: https://img.shields.io/github/issues/cosmin-bianu/heart-pal-android-app.svg?style=for-the-badge
[issues-url]: https://github.com/cosmin-bianu/heart-pal-android-app/issues
[license-shield]: https://img.shields.io/github/license/cosmin-bianu/heart-pal-android-app.svg?style=for-the-badge
[license-url]: https://github.com/cosmin-bianu/heart-pal-android-app/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/cosmin-bianu
