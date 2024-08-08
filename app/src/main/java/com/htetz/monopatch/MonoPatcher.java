package com.htetz.monopatch;

import android.content.Context;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class MonoPatcher implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("com.android.settings")) return;
        XposedHelpers.findAndHookMethod(
                "com.samsung.android.settings.display.SecDisplayUtils",
                lpparam.classLoader,
                "isInvalidFont",
                Context.class,
                String.class,
                new XC_MethodReplacement() {
                    @Override
                    protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                        return false;
                    }
                }
        );
    }
}
