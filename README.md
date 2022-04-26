# Xposed-LoadFridaGadget

通过 Xposed Hook `android.app.Application.onCreate` 加载 frida-gadget

```
adb logcat | grep Frida
netstat -tunlp | grep 26000

adb forword tcp:27042 tcp:26000
frida -R gadget
objection -g gadget explore
```
