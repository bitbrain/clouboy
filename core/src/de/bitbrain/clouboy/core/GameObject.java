package de.bitbrain.clouboy.core;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

public class GameObject implements Poolable {

  private Vector2 position, dimensions;

  private GameObjectType type = GameObjectType.NONE;

  public GameObject() {
    position = new Vector2();
    dimensions = new Vector2();
  }

  public void setDimensions(float width, float height) {
    this.dimensions.x = width;
    this.dimensions.y = height;
  }

  public void setType(GameObjectType type) {
    this.type = type;
  }

  public boolean isType(GameObjectType type) {
    return this.type.equals(type);
  }

  public GameObjectType getType() {
    return type;
  }

  public void move(float x, float y) {
    this.position.x += x;
    this.position.y += y;
  }

  public void setPosition(float x, float y) {
    this.position.x = x;
    this.position.y = y;
  }

  public float getLeft() {
    return this.position.x;
  }

  public float getTop() {
    return this.position.y;
  }

  public float getRight() {
    return getLeft() + getWidth();
  }

  public float getBottom() {
    return getTop() + getHeight();
  }

  public float getWidth() {
    return this.dimensions.x;
  }

  public float getHeight() {
    return this.dimensions.y;
  }

  @Override
  public void reset() {
    position.x = 0;
    position.y = 0;
    dimensions.x = 0;
    dimensions.y = 0;
    type = GameObjectType.NONE;
  }

}
