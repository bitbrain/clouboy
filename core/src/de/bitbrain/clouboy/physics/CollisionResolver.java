package de.bitbrain.clouboy.physics;

import de.bitbrain.clouboy.core.GameObject;

public interface CollisionResolver {

  void resolve(GameObject source, GameObject target);
}
