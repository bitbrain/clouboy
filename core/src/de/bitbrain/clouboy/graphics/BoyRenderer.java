package de.bitbrain.clouboy.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import de.bitbrain.clouboy.assets.Assets;
import de.bitbrain.clouboy.assets.SharedAssetManager;
import de.bitbrain.clouboy.core.GameObject;

public class BoyRenderer extends SpriteRenderer {

  private Sprite eye, eyeBall;

  private AssetManager assets = SharedAssetManager.getInstance();

  private Vector2 direction = new Vector2();

  public BoyRenderer() {
    super(Assets.TEX_BOY);
    eye = new Sprite(assets.get(Assets.TEX_EYE, Texture.class));
    eyeBall = new Sprite(assets.get(Assets.TEX_EYE_BALL, Texture.class));
  }

  @Override
  public void render(GameObject object, Batch batch) {
    super.render(object, batch);
    float centerX = object.getLeft() + object.getWidth() / 2f - eye.getWidth() / 2f;
    float centerY = object.getTop() + object.getHeight() / 2f - eye.getHeight() / 2f;
    final float radius = 10f;
    for (float angle = -180f; angle <= 0f; angle += 180f) {
      // Draw eye
      float eyeX = (float) (centerX + Math.cos(Math.toRadians(angle)) * radius);
      float eyeY = (float) (centerY + Math.sin(Math.toRadians(angle)) * radius);
      eye.setPosition(eyeX, eyeY);
      eye.setSize(16f, 16f);
      eye.draw(batch);
      // Draw eyeball
      float mouseX = Gdx.input.getX();
      float mouseY = Gdx.input.getY();
      float eyeBallCenterX = eyeX + eye.getWidth() / 4f;
      float eyeBallCenterY = eyeY + eye.getHeight() / 4f;
      direction.x = mouseX - eyeBallCenterX;
      direction.y = mouseY - eyeBallCenterY;
      if (direction.angle() < 0) {
        direction.setAngle(direction.angle() + 360f);
      }
      float offsetX = (float) (Math.cos(Math.toRadians(direction.angle())) * 2);
      float offsetY = (float) (Math.sin(Math.toRadians(direction.angle())) * 2);
      eyeBall.setSize(8f, 8f);
      eyeBall.setPosition(eyeBallCenterX + offsetX, eyeBallCenterY + offsetY);
      eyeBall.draw(batch);
    }
  }
}
