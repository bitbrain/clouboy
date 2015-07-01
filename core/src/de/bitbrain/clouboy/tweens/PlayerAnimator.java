package de.bitbrain.clouboy.tweens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;
import de.bitbrain.clouboy.core.GameObject;
import de.bitbrain.clouboy.core.PlayerBehavior.PlayerListener;

public class PlayerAnimator implements PlayerListener {

  private TweenManager tweenManager;

  public PlayerAnimator(TweenManager tweenManager) {
    this.tweenManager = tweenManager;
  }

  @Override
  public void onJump(final GameObject player) {
    tweenManager.killTarget(player);
    Tween.to(player, GameObjectTween.SCALE_X, 0.05f).target(1f).ease(TweenEquations.easeInOutQuad)
        .setCallbackTriggers(TweenCallback.COMPLETE).setCallback(new TweenCallback() {
          @Override
          public void onEvent(int type, BaseTween<?> source) {
            Tween.to(player, GameObjectTween.SCALE_X, 0.1f).target(1.7f).ease(TweenEquations.easeInOutQuad)
                .repeatYoyo(1, 0f).start(tweenManager);
          }
        }).start(tweenManager);
    Tween.to(player, GameObjectTween.SCALE_Y, 0.05f).target(1f).ease(TweenEquations.easeInOutQuad)
        .setCallbackTriggers(TweenCallback.COMPLETE).setCallback(new TweenCallback() {
          @Override
          public void onEvent(int type, BaseTween<?> source) {
            Tween.to(player, GameObjectTween.SCALE_Y, 0.2f).target(1.8f).ease(TweenEquations.easeInOutQuad)
                .repeatYoyo(1, 0f).start(tweenManager);
          }
        }).start(tweenManager);
  }
}
