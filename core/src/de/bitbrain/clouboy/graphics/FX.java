package de.bitbrain.clouboy.graphics;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;

public final class FX {

  private static final FX INTANCE = new FX();

  private TweenManager tweenManager;

  private OrthographicCamera camera;

  private FX() {
  }

  public static FX getInstance() {
    return INTANCE;
  }

  public void init(TweenManager tweenManager, OrthographicCamera camera) {
    this.tweenManager = tweenManager;
    this.camera = camera;
  }

  public void render(Batch batch, float delta) {

  }

  public void shake(float intensity, float duration) {
    System.out.println("Shake screen!");
  }

  public void flash(float duration) {
    System.out.println("Do flash!");
  }
}
