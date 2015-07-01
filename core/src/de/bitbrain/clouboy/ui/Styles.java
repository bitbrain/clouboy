package de.bitbrain.clouboy.ui;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import de.bitbrain.clouboy.assets.Assets;
import de.bitbrain.clouboy.assets.SharedAssetManager;

public final class Styles {

  public static final LabelStyle LABEL_STYLE_TEXT = new LabelStyle();

  private static final AssetManager assets = SharedAssetManager.getInstance();

  public static void loadStyles() {
    LABEL_STYLE_TEXT.font = assets.get(Assets.FNT_WENDY, BitmapFont.class);
    LABEL_STYLE_TEXT.fontColor = Color.WHITE.cpy();
  }
}
