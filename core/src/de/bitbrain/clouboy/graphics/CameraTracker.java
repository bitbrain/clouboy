package de.bitbrain.clouboy.graphics;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import de.bitbrain.clouboy.core.GameObject;

public class CameraTracker {

  private OrthographicCamera camera;

  private Vector2 velocity;

  private GameObject player;

  public CameraTracker(GameObject player, OrthographicCamera camera) {
    this.camera = camera;
    this.player = player;
    velocity = new Vector2();
  }

  public void update(float delta) {
    velocity.x = (float) (player.getLeft() + Math.floor(player.getWidth() / 2.0f) - (camera.position.x));
    velocity.y = (float) (player.getTop() + Math.floor(player.getHeight() / 2.0f) - (camera.position.y));

    float distance = velocity.len();
    velocity.nor();
    double speed = distance * 2.2f;

    // Round it up to prevent camera shaking
    camera.position.x = (float) (camera.position.x + (velocity.x * speed * delta));
    camera.position.y = (float) (camera.position.y + (velocity.y * speed * delta));
    camera.zoom = 0.8f + 0.003f * distance;

  }

  public void focus() {
    camera.position.x = player.getLeft();
    camera.position.y = player.getTop();
  }
}
