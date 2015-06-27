package de.bitbrain.clouboy.core;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Pool;

import de.bitbrain.clouboy.graphics.RenderManager;

public class World {

  private RenderManager renderer = new RenderManager();

  private final List<GameObject> objects = new ArrayList<GameObject>();

  private final Pool<GameObject> pool = new Pool<GameObject>() {
    @Override
    protected GameObject newObject() {
      return new GameObject();
    }
  };

  public GameObject addObject() {
    GameObject object = pool.obtain();
    objects.add(object);
    return object;
  }

  public void remove(GameObject object) {
    pool.free(object);
    objects.remove(object);
  }

  public void updateAndRender(Batch batch, float delta) {
    for (GameObject object : objects) {
      renderer.render(object, batch);
    }
  }

}
