package de.bitbrain.clouboy.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Pool;

import de.bitbrain.clouboy.graphics.RenderManager;
import de.bitbrain.clouboy.physics.Physics;

public class World {

  private RenderManager renderManager = new RenderManager();

  private Physics physics;

  private Set<WorldListener> listeners = new HashSet<WorldListener>();

  private final List<GameObject> removals = new ArrayList<GameObject>();

  private final List<GameObject> objects = new ArrayList<GameObject>();

  private final Map<GameObject, Behavior> behaviors = new HashMap<GameObject, Behavior>();

  private final Pool<GameObject> pool = new Pool<GameObject>(4096) {
    @Override
    protected GameObject newObject() {
      return new GameObject();
    }
  };

  private OrthographicCamera camera;

  public World(OrthographicCamera camera) {
    this.camera = camera;
  }

  public void init() {
    renderManager.init();
    physics = new Physics(objects);
  }

  public void addListener(WorldListener listener) {
    listeners.add(listener);
  }

  public GameObject addObject() {
    GameObject object = pool.obtain();
    objects.add(object);
    return object;
  }

  public void applyBehavior(GameObject object, Behavior behavior) {
    if (objects.contains(object)) {
      behaviors.put(object, behavior);
    }
  }

  public void updateAndRender(Batch batch, float delta) {
    System.out.println(objects.size());
    for (GameObject object : objects) {
      if (object.getRight() < camera.position.x - camera.viewportWidth * camera.zoom / 2) {
        removals.add(object);
        continue;
      }

      Behavior behavior = behaviors.get(object);
      if (behavior != null) {
        behavior.update(object, delta);
      }
      physics.apply(object, delta);

      for (WorldListener listener : listeners) {
        listener.onGameObjectUpdate(object);
      }

      renderManager.render(object, batch, delta);
    }
    for (GameObject removal : removals) {
      remove(removal);
    }
    removals.clear();
  }

  public int size() {
    return objects.size();
  }

  public static interface Behavior {
    void update(GameObject object, float delta);
  }

  public static interface WorldListener {
    void onGameObjectUpdate(GameObject object);
  }

  public void remove(GameObject... objects) {
    for (GameObject object : objects) {
      removals.add(object);
    }
  }

  private void remove(GameObject object) {
    pool.free(object);
    objects.remove(object);
    behaviors.remove(object);
  }

  public void reset() {
    pool.clear();
    objects.clear();
    behaviors.clear();
    removals.clear();
    listeners.clear();
  }

}
