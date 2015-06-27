package de.bitbrain.clouboy.core;

import de.bitbrain.clouboy.core.World.Behavior;

public class PlayerBehavior implements Behavior {

  @Override
  public void update(GameObject object, float delta) {
    object.move(1f, 0);
  }

}
