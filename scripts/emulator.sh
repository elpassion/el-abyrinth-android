echo no | android create avd --force -n test -t android-21 --abi armeabi-v7a
emulator -avd test -no-skin -no-audio -no-window &
android-wait-for-emulator
sleep 30
adb shell input keyevent 82