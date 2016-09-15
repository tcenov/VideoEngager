adb shell input keyevent 26


::adb shell input keyevent 26 - this will turn screen on.
::adb shell input keyevent 82 - this will unlock and ask for pin.
::adb shell input text xxxx && adb shell input keyevent 66 - this will input your pin and press enter, unlocking device to home screen.
::adb shell input touchscreen swipe 240 687 430 687 - swipe unlock