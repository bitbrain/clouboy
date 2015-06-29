package de.bitbrain.clouboy.graphics;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import de.bitbrain.clouboy.assets.SharedAssetManager;
import de.bitbrain.clouboy.core.GameObject;
import de.bitbrain.clouboy.graphics.RenderManager.Renderer;

public class SpriteRenderer implements Renderer {

  protected Sprite sprite;

  private AssetManager assets = SharedAssetManager.getInstance();

  private String textureId;

  public SpriteRenderer(String textureId) {
    this.textureId = textureId;
  }

  @Override
  public void init() {
    sprite = new Sprite(assets.get(textureId, Texture.class));
  }

  @Override
  public void render(GameObject object, Batch batch, float delta) {
    sprite.setPosition(object.getLeft(), object.getTop());
    sprite.setSize(object.getWidth(), object.getHeight());
    sprite.setColor(object.getColor());
    sprite.setScale(object.getScale().x, object.getScale().y);
    sprite.draw(batch, 1f);
  }

}
