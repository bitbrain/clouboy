package de.bitbrain.clouboy.tweens;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public final class Animator {

  private TweenManager tweenManager;

  static {
    Tween.registerAccessor(Sprite.class, new SpriteTween());
    Tween.registerAccessor(Actor.class, new ActorTween());
  }

  public Animator(TweenManager tweenManager) {
    this.tweenManager = tweenManager;
  }

  public void fadeIn(Sprite sprite, float interval) {
    fadeIn(sprite, interval, 1f);
  }

  public void fadeIn(Sprite sprite, float interval, float target) {
    tweenManager.killTarget(sprite);
    sprite.setAlpha(0f);
    Tween.to(sprite, SpriteTween.ALPHA, interval).target(target).start(tweenManager);
  }

  public void fadeIn(Actor actor, float interval) {
    fadeIn(actor, interval, 1f);
  }

  public void fadeIn(Actor actor, float interval, float target) {
    tweenManager.killTarget(actor);
    actor.getColor().a = 0f;
    Tween.to(actor, ActorTween.ALPHA, interval).target(target).start(tweenManager);
  }
}
