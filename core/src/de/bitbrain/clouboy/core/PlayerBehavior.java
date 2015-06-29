package de.bitbrain.clouboy.core;

import java.util.HashSet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;

import de.bitbrain.clouboy.assets.Assets;
import de.bitbrain.clouboy.assets.SharedAssetManager;
import de.bitbrain.clouboy.core.World.Behavior;

public class PlayerBehavior implements Behavior {

  private static final int MAX_SPEED = 200;

  private boolean justTouched = false;

  private HashSet<PlayerListener> listeners = new HashSet<PlayerListener>();

  private AssetManager assets = SharedAssetManager.getInstance();

  public void addListener(PlayerListener listener) {
    listeners.add(listener);
  }

  @Override
  public void update(GameObject object, float delta) {
    if (Gdx.input.isTouched() && !justTouched) {
      object.accellerate(MAX_SPEED * delta, 900f * delta);
      playSound();
      for (PlayerListener l : listeners) {
        l.onJump(object);
      }
    }
    justTouched = Gdx.input.isTouched();
    if (object.getVelocity().y == 0) {
      object.setVelocity(5f, 0);
    }
  }

  private void playSound() {
    Sound sound = assets.get(Assets.SND_JUMP, Sound.class);
    sound.play(0.25f, 1.5f, 1f);
  }

  public static interface PlayerListener {
    void onJump(GameObject player);
  }

}
