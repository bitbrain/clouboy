package de.bitbrain.clouboy.core;

import java.util.HashSet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;

import de.bitbrain.clouboy.assets.Assets;
import de.bitbrain.clouboy.assets.SharedAssetManager;
import de.bitbrain.clouboy.core.World.Behavior;

public class PlayerBehavior implements Behavior {

  private static final int MAX_SPEED = 80;
  public static final int MAX_JUMPS = 5;

  private boolean justTouched = false;

  private int jumps = MAX_JUMPS;

  private HashSet<PlayerListener> listeners = new HashSet<PlayerListener>();

  private AssetManager assets = SharedAssetManager.getInstance();

  private boolean initial = true;

  public void addListener(PlayerListener listener) {
    listeners.add(listener);
  }

  public int getJumps() {
    return jumps;
  }

  public int getJumpMax() {
    return MAX_JUMPS;
  }

  @Override
  public void update(GameObject object, float delta) {
    if (initial) {
      for (PlayerListener l : listeners) {
        l.onJump(object, jumps, MAX_JUMPS);
      }
      initial = false;
    }
    boolean cloudCollision =
        object.getVelocity().y == 0 && object.getLastCollision() != null
            && object.getLastCollision().getType().equals(GameObjectType.CLOUD);
    if (cloudCollision) {
      jumps = MAX_JUMPS;
    }
    if (Gdx.input.isTouched() && canJump()) {
      if (!cloudCollision) {
        jumps--;
      }
      for (PlayerListener l : listeners) {
        l.onJump(object, jumps, MAX_JUMPS);
      }
      object.accellerate((MAX_SPEED + 2f * jumps) * delta, 300f * delta);
      playSound(object);
    }
    justTouched = Gdx.input.isTouched();
  }

  private boolean canJump() {
    return !justTouched && jumps > 0;
  }

  private void playSound(GameObject object) {

    Sound sound = assets.get(Assets.SND_JUMP, Sound.class);
    float pitch = 1.5f * ((float) (MAX_JUMPS) / (float) (jumps + 1));
    sound.play(0.15f, pitch, 1f);
    if (jumps == MAX_JUMPS - 2) {
      sound = assets.get(Assets.SND_WOW, Sound.class);
      sound.play(0.25f, (float) (1.0f + Math.random() * 0.4f), 1f);
    }
  }

  public static interface PlayerListener {
    void onJump(GameObject player, int jumps, int maxJumps);
  }

}
