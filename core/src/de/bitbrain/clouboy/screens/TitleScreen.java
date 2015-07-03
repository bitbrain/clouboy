package de.bitbrain.clouboy.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import de.bitbrain.clouboy.ClouBoy;
import de.bitbrain.clouboy.assets.Assets;

public class TitleScreen extends AbstractScreen {

  private Sprite background;

  private Sprite logo;

  public TitleScreen(ClouBoy game) {
    super(game);
  }

  @Override
  protected void onShow() {
    background = new Sprite(assets.get(Assets.TEX_BACKGROUND, Texture.class));
    logo = new Sprite(assets.get(Assets.TEX_LOGO, Texture.class));
  }

  @Override
  protected void onRender(Batch batch, float delta) {
    background.setPosition(camera.position.x - (camera.zoom * camera.viewportWidth) / 2, camera.position.y
        - (camera.zoom * camera.viewportHeight) / 2);
    background.setSize(camera.viewportWidth * camera.zoom, camera.viewportHeight * camera.zoom);
    background.draw(batch, 1f);
    logo.setPosition(camera.position.x - logo.getWidth() / 2f, camera.viewportHeight / 1.8f);
    logo.draw(batch, 1f);

  }

}
