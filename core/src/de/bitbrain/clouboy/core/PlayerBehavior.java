package de.bitbrain.clouboy.core;

import com.badlogic.gdx.Gdx;

import de.bitbrain.clouboy.core.World.Behavior;

public class PlayerBehavior implements Behavior {

  private static final int MAX_SPEED = 120;

  private boolean justTouched = false;

  @Override
  public void update(GameObject object, float delta) {
    if (Gdx.input.isTouched() && !justTouched) {
      object.accellerate(MAX_SPEED * delta, 10f);
    }
    justTouched = Gdx.input.isTouched();
  }

}
