package de.bitbrain.clouboy.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import de.bitbrain.clouboy.core.World.Behavior;

public class PlayerBehavior implements Behavior {

  private static final int MAX_SPEED = 70;

  @Override
  public void update(GameObject object, float delta) {
    if (Gdx.input.isKeyPressed(Keys.A)) {
      object.accellerate(-MAX_SPEED * delta, 0);
    } else if (Gdx.input.isKeyPressed(Keys.D)) {
      object.accellerate(MAX_SPEED * delta, 0);
    }
    if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
      object.accellerate(0, 30f);
    }
  }

}
