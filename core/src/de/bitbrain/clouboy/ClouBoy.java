package de.bitbrain.clouboy;

import com.badlogic.gdx.Game;

import de.bitbrain.clouboy.assets.AssetReflector;
import de.bitbrain.clouboy.assets.SharedAssetManager;
import de.bitbrain.clouboy.screens.IngameScreen;

public class ClouBoy extends Game {

  @Override
  public void create() {
    AssetReflector assetDeflector = new AssetReflector();
    assetDeflector.load();
    setScreen(new IngameScreen(this));
  }

  @Override
  public void dispose() {
    super.dispose();
    SharedAssetManager.dispose();
  }

}
