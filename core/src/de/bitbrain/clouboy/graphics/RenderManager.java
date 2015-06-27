package de.bitbrain.clouboy.graphics;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.Batch;

import de.bitbrain.clouboy.core.GameObject;
import de.bitbrain.clouboy.core.GameObjectType;

public class RenderManager {

  private static Map<GameObjectType, Renderer> rendererMap = new HashMap<GameObjectType, Renderer>();

  static {
    rendererMap.put(GameObjectType.BOY, new BoyRenderer());
  }

  public void render(GameObject object, Batch batch) {
    Renderer renderer = rendererMap.get(object.getType());
    if (renderer != null) {
      renderer.render(object, batch);
    }
  }

  static interface Renderer {

    void render(GameObject object, Batch batch);
  }
}
