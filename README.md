# Pico 4 VR - HeliBoard

Modified [HeliBoard](https://github.com/HeliBorg/HeliBoard) for **Pico 4 VR** is a privacy-conscious and customizable open-source keyboard, based on AOSP / OpenBoard.
Does not use internet permission, and thus is 100% offline.

## 👓 Screenshot

<image src="./src/Screenshot.jpeg" width="400"/>

## 🖥️ Features

- Add dictionaries for suggestions and spell check
  - build your own, or get them [here](https://codeberg.org/Helium314/aosp-dictionaries#dictionaries) (quality may vary)
  - additional dictionaries for emojis or scientific symbols can be used to provide suggestions (similar to "emoji search")
  - note that for Korean layouts, suggestions only work using <a href="https://github.com/openboard-team/openboard/commit/83fca9533c03b9fecc009fc632577226bbd6301f">this dictionary, the tools in the dictionary repository are not able to create working dictionaries
- Customize keyboard themes (style, colors and background image)
- Emoji search (inline and separate, requires [emoji dictionary](https://codeberg.org/Helium314/aosp-dictionaries))
  - can follow the system's day/night setting on Android 10+ (and on some versions of Android 9)
  - can follow dynamic colors for Android 12+
- Customize keyboard [layouts](https://github.com/HeliBorg/HeliBoard/blob/main/layouts.md) (only available when disabling *use system languages*)
- Customize special layouts, like symbols, number,  or functional key layout
- Multilingual typing
- Glide typing (*only with closed source library* ☹️)
  - library not included in the app, as there is no compatible open source library available
  - can be extracted from GApps packages ("*swypelibs*"), or downloaded [here](https://github.com/erkserkserks/openboard/tree/46fdf2b550035ca69299ce312fa158e7ade36967/app/src/main/jniLibs) (click on the file and then "raw" or the tiny download button)
- Clipboard history
- One-handed mode
- Split keyboard
- Number pad
- Backup and restore your settings and learned word / history data

## ⛏️ Prerequisites

- **Device:** Pico 4 Headset (Phoenix/China firmware supported).
- **Superuser:** **[Root Access](https://github.com/chaixshot/more-picohaxx)** is required to apply changes to system files.
- **Environment:** **[LSPosed Framework](https://github.com/JingMatrix/Vector/releases/tag/v2.0)** must be installed and active.
- **Permission:** Grant root access when prompted by the app.
- **LSPosed Scope:** Ensure `System (android)`, `NativeShell (com.picovr.systemext)` is selected in the LSPosed module scope.

## 📐 How to use?

1. **Install** the `HeliBoard.Pico` APK on your headset.
2. **Enable** the **HeliBoard** module in the **LSPosed Manager**.
3. **Select Scope:** Make sure `System (android)`, `NativeShell (com.picovr.systemext)` is checked in the module's scope settings.
4. **Reboot** your device to activate the hooks.
5. **Open HeliBoard:** Launch **HeliBoard** app and have fun.

## 🙏 Special thanks to

- [HeliBoard](https://github.com/HeliBorg/HeliBoard)
- [Pico-4-IME-Unlock](https://github.com/Skyrimus/Pico-4-IME-Unlock)
