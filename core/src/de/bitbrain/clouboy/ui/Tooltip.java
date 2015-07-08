package de.bitbrain.clouboy.ui;

import java.util.HashSet;
import java.util.Set;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import de.bitbrain.clouboy.tweens.ActorTween;

public class Tooltip {

  private static final Tooltip INSTANCE = new Tooltip();

  private TweenManager tweenManager;

  private Stage stage;

  private Camera camera;

  private Set<Label> tooltips = new HashSet<Label>();

  private Tooltip() {
  }

  public static Tooltip getInstance() {
    return INSTANCE;
  }

  public void create(float x, float y, String text) {
    create(x, y, text, Color.WHITE);
  }

  public void create(float x, float y, String text, Color color) {
    final Label tooltip = new Label(text, Styles.LABEL_STYLE_TEXT) {
      @Override
      public float getX() {
        return super.getX() - camera.position.x + camera.viewportWidth / 2f;
      }

      @Override
      public float getY() {
        return super.getY() - camera.position.y + camera.viewportHeight / 2f;
      }
    };
    tooltip.setColor(color);
    tooltip.setPosition(x, y);
    stage.addActor(tooltip);
    tooltips.add(tooltip);
    Tween.to(tooltip, ActorTween.ALPHA, 0.9f).target(0f).setCallbackTriggers(TweenCallback.COMPLETE)
        .setCallback(new TweenCallback() {
          @Override
          public void onEvent(int type, BaseTween<?> source) {
            stage.getActors().removeValue(tooltip, true);
          }
        }).start(tweenManager);
    Tween.to(tooltip, ActorTween.SCALE, 0.9f).target(2.5f).start(tweenManager);
  }

  public void clear() {
    for (Label l : tooltips) {
      tweenManager.killTarget(l);
      stage.getActors().removeValue(l, true);
    }
    tooltips.clear();
  }

  public void init(TweenManager tweenManager, Stage stage, Camera camera) {
    this.tweenManager = tweenManager;
    this.stage = stage;
    this.camera = camera;
  }

}
