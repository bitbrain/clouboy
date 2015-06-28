package de.bitbrain.clouboy.core;

import java.util.HashSet;

import com.badlogic.gdx.Gdx;

import de.bitbrain.clouboy.core.World.Behavior;

public class PlayerBehavior implements Behavior {

  private static final int MAX_SPEED = 200;

  private boolean justTouched = false;

  private HashSet<PlayerListener> listeners = new HashSet<PlayerListener>();

  public void addListener(PlayerListener listener) {
    listeners.add(listener);
  }

  @Override
  public void update(GameObject object, float delta) {
    if (Gdx.input.isTouched() && !justTouched) {
      object.accellerate(MAX_SPEED * delta, 900f * delta);
      for (PlayerListener l : listeners) {
        l.onJump(object);
      }
    }
    justTouched = Gdx.input.isTouched();
  }

  public static interface PlayerListener {
    void onJump(GameObject player);
  }

}
