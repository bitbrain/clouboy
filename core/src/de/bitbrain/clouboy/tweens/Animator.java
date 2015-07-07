package de.bitbrain.clouboy.tweens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import de.bitbrain.clouboy.ui.GameInfoWidget;

public final class Animator {

  private TweenManager tweenManager;

  private Tween currentTween;

  static {
    Tween.registerAccessor(Sprite.class, new SpriteTween());
    Tween.registerAccessor(Actor.class, new ActorTween());
  }

  public Animator(TweenManager tweenManager) {
    this.tweenManager = tweenManager;
  }

  public Animator fadeIn(Sprite sprite, float interval) {
    return fadeIn(sprite, interval, 1f);
  }

  public Animator fadeIn(Sprite sprite, float interval, float target) {
    tweenManager.killTarget(sprite);
    sprite.setAlpha(0f);
    currentTween =
        Tween.to(sprite, SpriteTween.ALPHA, interval).setCallbackTriggers(TweenCallback.COMPLETE).target(target)
            .start(tweenManager);
    return this;
  }

  public Animator fadeIn(Actor actor, float interval) {
    return fadeIn(actor, interval, 1f);
  }

  public Animator fadeIn(Actor actor, float interval, float target) {
    tweenManager.killTarget(actor);
    actor.getColor().a = 0f;
    currentTween =
        Tween.to(actor, ActorTween.ALPHA, interval).setCallbackTriggers(TweenCallback.COMPLETE).target(target)
            .start(tweenManager);
    return this;
  }

  public Animator fadeOut(GameInfoWidget actor, float interval, float target) {
    tweenManager.killTarget(actor);
    actor.getColor().a = 1f;
    currentTween =
        Tween.to(actor, ActorTween.ALPHA, interval).setCallbackTriggers(TweenCallback.COMPLETE).target(target)
            .start(tweenManager);
    return this;
  }

  public Animator after(final AnimatorCallback callback) {
    if (currentTween != null) {
      currentTween.setCallback(new TweenCallback() {
        @Override
        public void onEvent(int type, BaseTween<?> source) {
          callback.action();
        }
      });
    }
    return this;
  }

  public static interface AnimatorCallback {
    void action();
  }
}
