package de.bitbrain.clouboy.i18n;

import java.util.Locale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.I18NBundle;

import de.bitbrain.clouboy.assets.Assets;

public final class Bundle {

  public static I18NBundle general, items, recipes, itemDescriptions;

  private static FileHandle generalHandle;

  public static void load() {
    Gdx.app.log("LOAD", "Loading bundles...");
    generalHandle = Gdx.files.internal(Assets.BDL_GENERAL);
    Locale locale = Locale.getDefault();
    setLocale(locale);
    Gdx.app.log("INFO", "Done loading bundles.");
  }

  public static void setLocale(Locale locale) {
    Gdx.app.log("INFO", "Set locale to '" + locale + "'");
    general = I18NBundle.createBundle(generalHandle, locale);
  }
}