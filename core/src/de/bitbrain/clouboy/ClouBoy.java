package de.bitbrain.clouboy;

import com.badlogic.gdx.Game;

import de.bitbrain.clouboy.assets.AssetReflector;
import de.bitbrain.clouboy.assets.SharedAssetManager;
import de.bitbrain.clouboy.i18n.Bundle;
import de.bitbrain.clouboy.screens.TitleScreen;
import de.bitbrain.clouboy.social.SocialManager;
import de.bitbrain.clouboy.ui.Styles;

public class ClouBoy extends Game {

  private SocialManager socialManager;

  public ClouBoy(SocialManager socialManager) {
    this.socialManager = socialManager;
  }

  @Override
  public void create() {
    AssetReflector assetDeflector = new AssetReflector();
    assetDeflector.load();
    Bundle.load();
    Styles.loadStyles();
    setScreen(new TitleScreen(this));
  }

  @Override
  public void dispose() {
    super.dispose();
    SharedAssetManager.dispose();
  }

  public SocialManager getSocialManager() {
    return socialManager;
  }

}
