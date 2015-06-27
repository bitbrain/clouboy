package de.bitbrain.clouboy.screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import de.bitbrain.clouboy.ClouBoy;
import de.bitbrain.clouboy.assets.Assets;
import de.bitbrain.clouboy.assets.SharedAssetManager;

public class IngameScreen extends AbstractScreen {

  private Sprite background;

  private AssetManager assets;

  public IngameScreen(ClouBoy game) {
    super(game);
  }

  @Override
  protected void onShow() {
    assets = SharedAssetManager.getInstance();
    background = new Sprite(assets.get(Assets.TEX_BACKGROUND, Texture.class));
    background.flip(false, true);
  }

  @Override
  protected void onRender(Batch batch, float delta) {
    background.draw(batch, 1f);
  }

}
