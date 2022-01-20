package moe.henry_zhr.killassetfonts;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import androidx.annotation.Keep;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

@Keep
public class MainHook implements IXposedHookLoadPackage {
  @Override
  public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
    XposedHelpers.findAndHookConstructor(
        "android.graphics.Typeface.Builder",
        lpparam.classLoader,
        AssetManager.class,
        String.class,
        new XC_MethodHook() {
          @Override
          protected void beforeHookedMethod(MethodHookParam param) {
            param.setResult(new Typeface.Builder("/system/fonts/EmptyFont.ttf"));
          }
        });
  }
}
