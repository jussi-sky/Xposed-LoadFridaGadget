package com.jussi.loadfridagadget;

import android.annotation.SuppressLint;
import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookMain implements IXposedHookLoadPackage {

    //被HOOK的程序的包名和类名
//    String packName = "com.android.bankabc";
    String className = "android.app.Application";
    String TAG = "XposedFridaGadget";

    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {

        if(loadPackageParam == null) return;
//        if(!packName.equals(loadPackageParam.packageName)) return;

        XposedBridge.log("Loaded app: " + loadPackageParam.packageName);

        XposedHelpers.findAndHookMethod(className, // 类名
                loadPackageParam.classLoader, // 类加载器
                "onCreate", // 方法名
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        Log.d(TAG, "Hook android.app.Application.onCreate Successful");
                    }

                    @SuppressLint("UnsafeDynamicallyLoadedCode")
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        Log.d(TAG, "**************** Jussi ****************");
                        try {
//                            Log.d(TAG, "package name:" + packName);
//                            System.load("/data/data/com.android.bankabc/libminitool.so");
                            System.load("/system/lib64/libminitool.so");
                            Log.d(TAG, "Loading gadget arm64 finish !");
                        } catch (Throwable e) {
                            Log.e(TAG, String.valueOf(e));

                            try{
                                System.load("/system/lib/libminitool.so");
                                Log.d(TAG, "Loading gadget arm finish !");
                            } catch (Throwable ex){
                                Log.e(TAG, String.valueOf(ex));
                            }
                        }
                    }
                });
    }
}
