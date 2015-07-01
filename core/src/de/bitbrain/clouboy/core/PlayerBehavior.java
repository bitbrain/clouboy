package de.bitbrain.clouboy.core;

import java.util.HashSet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;

import de.bitbrain.clouboy.assets.Assets;
import de.bitbrain.clouboy.assets.SharedAssetManager;
import de.bitbrain.clouboy.core.World.Behavior;

public class PlayerBehavior implements Behavior {

  private static final int MAX_SPEED = 120;
  private static final int MAX_JUMPS = 4;

  private boolean justTouched = false;

  private int jumps = 0;

  private HashSet<PlayerListener> listeners = new HashSet<PlayerListener>();

  private AssetManager assets = SharedAssetManager.getInstance();

  public void addListener(PlayerListener listener) {
    listeners.add(listener);
  }

  @Override
  public void update(GameObject object, float delta) {
    if (Gdx.input.isTouched() && canJump()) {
      object.accellerate((MAX_SPEED + 5f * jumps) * delta, 200f * delta);
      playSound(object);
      for (PlayerListener l : listeners) {
        l.onJump(object);
      }
      jumps++;
    }
    justTouched = Gdx.input.isTouched();
    if (object.getVelocity().y == 0) {
      if (object.getVelocity().x < 2f) {
        object.getVelocity().x = 0;
      }
      object.setVelocity(object.getVelocity().x, 0);
      jumps = 0;
    }
  }

  private boolean canJump() {
    return !justTouched && jumps < MAX_JUMPS - 1;
  }

  private void playSound(GameObject object) {

    Sound sound = assets.get(Assets.SND_JUMP, Sound.class);
    float pitch = 1.2f + object.getTop() * 0.001f;
    sound.play(0.15f, pitch, 1f);
    if (jumps > 1) {
      sound = assets.get(Assets.SND_WOW, Sound.class);
      sound.play(0.25f, (float) (1.0f + Math.random() * 0.4f), 1f);
    }
  }

  public static interface PlayerListener {
    void onJump(GameObject player);
  }

}
