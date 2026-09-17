# Pico 4 VR - HeliBoard

Modified [HeliBoard](https://github.com/HeliBorg/HeliBoard) for **Pico 4 VR** is a privacy-conscious and customizable open-source keyboard, based on AOSP / OpenBoard.
Does not use internet permission, and thus is 100% offline.

## 👓 Screenshot

<image src="./src/Screenshot.jpeg" width="400"/>

## 🖥️ Features

- **Pico VR Design:** Tailored with a custom Pico color palette and rounded key borders made specifically for Pico 4 headsets.
- **VR-Optimized:** Built for point-and-tap typing with a taller 1.8x layout, adjusted padding, wider key gaps, and larger hint fonts for better visibility.
- **Number Row & Numpad:** Dedicated top number row enabled by default, complete with long-press symbol access.
- **Auto-Correction & Spacing:** Includes touch position correction to minimize mistypes, smart auto-spacing after punctuation, and a responsive 255ms long-press delay.
- **Clipboard Manager:** Saves text and image clips up to 20MB, supports pinned snippets, and offers automatic inline paste suggestions.
- **Smart Keyboard Modes:** Automatically snaps back to the main layout after you paste content or pick an emoji.
- **Audio Feedback:** Audio keypress sounds provide clear physical-like cues while typing in VR.
- **Expandable Toolbar:** A swipeable, auto-hiding bar with quick access to Copy, Paste, Undo, Redo, and Navigation controls.
- **Privacy First:** Operates entirely offline with zero internet permissions required.

## ⌨️ Shortcuts

- **Dismiss Keyboard:** Swipe down on the top toolbar or suggestion strip to close the keyboard.
- **Cursor Control:** Drag left or right across the spacebar to move your cursor through text.
- **Toggle Numpad/D-Pad:** Swipe up or down on the spacebar to switch modes instantly.
- **Delete Words:** Swipe left from the Backspace key to erase full words at once.
- **Quick Period:** Double-tap the spacebar to insert a period and a space.
- **Fast Symbols:** Long-press any letter or number key to type symbols without changing layouts.
- **Custom Toolbar Pinning:** Pin or unpin your most-used tools (Copy, Paste, Undo, Redo, Voice) directly on the top bar.
- **Auto-Return:** Emoji selection and clipboard pasting automatically drop you back into typing mode.

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
