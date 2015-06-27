package de.bitbrain.clouboy.graphics;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import de.bitbrain.clouboy.assets.Assets;
import de.bitbrain.clouboy.assets.SharedAssetManager;
import de.bitbrain.clouboy.core.GameObject;
import de.bitbrain.clouboy.graphics.RenderManager.Renderer;

public class BoyRenderer implements Renderer {

  private Sprite sprite;

  private AssetManager assets = SharedAssetManager.getInstance();

  @Override
  public void init() {
    sprite = new Sprite(assets.get(Assets.TEX_BOY, Texture.class));
    sprite.flip(false, true);
  }

  @Override
  public void render(GameObject object, Batch batch) {
    sprite.setPosition(object.getLeft(), object.getTop());
    sprite.setSize(object.getWidth(), object.getHeight());
    sprite.setCenter(object.getWidth() / 2f, object.getHeight() / 2f);
    sprite.draw(batch, 1f);
  }

}
