package de.bitbrain.clouboy.graphics;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquation;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import de.bitbrain.clouboy.assets.Assets;
import de.bitbrain.clouboy.assets.SharedAssetManager;
import de.bitbrain.clouboy.tweens.SpriteTween;

public final class FX {

  private static final FX INTANCE = new FX();

  private TweenManager tweenManager;

  private OrthographicCamera camera;

  private Sprite flash;

  private ScreenShake screenShake;

  private AssetManager assets = SharedAssetManager.getInstance();

  private FX() {
    flash = new Sprite(GraphicsFactory.createTexture(2, 2, Color.WHITE));
    flash.setAlpha(0f);
  }

  public static FX getInstance() {
    return INTANCE;
  }

  public void init(TweenManager tweenManager, OrthographicCamera camera, ScreenShake shake) {
    this.tweenManager = tweenManager;
    this.camera = camera;
    screenShake = shake;
  }

  public void render(Batch batch, float delta) {
    flash.setPosition(camera.position.x - (camera.zoom * camera.viewportWidth) / 2, camera.position.y
        - (camera.zoom * camera.viewportHeight) / 2);
    flash.setSize(camera.viewportWidth * camera.zoom, camera.viewportHeight * camera.zoom);
    flash.draw(batch, 1f);
  }

  public void thunder() {
    shake(80f, 1.5f);
    flash(1.5f, TweenEquations.easeInOutBounce);
    assets.get(Assets.SND_THUNDER, Sound.class).play(1f, (float) (1f - Math.random() * 0.3f), 1f);
  }

  public void shake(float intensity, float duration) {
    screenShake.shake(intensity, duration);
  }

  public void flash(float duration, TweenEquation equation) {
    flash.setAlpha(1f);
    Tween.to(flash, SpriteTween.ALPHA, duration).target(0f).ease(equation).start(tweenManager);
  }

  public void flash(float duration) {
    flash(duration, TweenEquations.easeInQuad);
  }
}
