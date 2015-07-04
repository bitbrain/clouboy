package de.bitbrain.clouboy.graphics;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import de.bitbrain.clouboy.tweens.SpriteTween;

public final class FX {

  private static final FX INTANCE = new FX();

  private TweenManager tweenManager;

  private OrthographicCamera camera;

  private Sprite flash;

  private FX() {
    flash = new Sprite(GraphicsFactory.createTexture(2, 2, Color.WHITE));
    flash.setAlpha(0f);
  }

  public static FX getInstance() {
    return INTANCE;
  }

  public void init(TweenManager tweenManager, OrthographicCamera camera) {
    this.tweenManager = tweenManager;
    this.camera = camera;
  }

  public void render(Batch batch, float delta) {
    flash.setPosition(camera.position.x - (camera.zoom * camera.viewportWidth) / 2, camera.position.y
        - (camera.zoom * camera.viewportHeight) / 2);
    flash.setSize(camera.viewportWidth * camera.zoom, camera.viewportHeight * camera.zoom);
    flash.draw(batch, 1f);
  }

  public void shake(float intensity, float duration) {
    System.out.println("Shake screen!");
  }

  public void flash(float duration) {
    flash.setAlpha(1f);
    Tween.to(flash, SpriteTween.ALPHA, duration).target(0f).ease(TweenEquations.easeInOutBounce).start(tweenManager);
  }
}
