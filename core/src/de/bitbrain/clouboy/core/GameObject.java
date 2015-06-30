package de.bitbrain.clouboy.core;

import java.util.UUID;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

public class GameObject implements Poolable {

  private Vector2 position, dimensions, accelleration, velocity, lastPosition;

  private GameObjectType type = GameObjectType.NONE;

  private boolean collision = true;

  private boolean staticMode = false;

  private GameObject lastCollision;

  private String id = "";

  private Color color = Color.WHITE.cpy();

  private Vector2 scale;

  private String uuid;

  public GameObject() {
    position = new Vector2();
    dimensions = new Vector2();
    lastPosition = new Vector2();
    accelleration = new Vector2();
    velocity = new Vector2();
    scale = new Vector2(1f, 1f);
    uuid = UUID.randomUUID().toString();
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
    setPosition(this.position.x + x, this.position.y + y);
  }

  public void setPosition(float x, float y) {
    this.lastPosition.x = this.position.x;
    this.lastPosition.y = this.position.y;
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

  public void accellerate(float x, float y) {
    accelleration.x += x;
    accelleration.y += y;
  }

  public void setVelocity(float x, float y) {
    velocity.x = x;
    velocity.y = y;
  }

  public Vector2 getVelocity() {
    return velocity;
  }

  public Vector2 getAccelleration() {
    return accelleration;
  }

  public void stop() {
    accelleration.x = 0;
    accelleration.y = 0;
    velocity.x = 0;
    velocity.y = 0;
  }

  public boolean hasCollisionEnabled() {
    return collision;
  }

  public void enableCollision(boolean collision) {
    this.collision = collision;
  }

  public boolean isStatic() {
    return staticMode;
  }

  public void setStatic(boolean staticMode) {
    this.staticMode = staticMode;
  }

  public Vector2 getLastPosition() {
    return lastPosition;
  }

  public GameObject getLastCollision() {
    return lastCollision;
  }

  public void setLastCollision(GameObject lastCollision) {
    this.lastCollision = lastCollision;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    setColor(color.r, color.g, color.b, color.a);
  }

  public void setColor(float r, float g, float b, float a) {
    color.set(r, g, b, a);
  }

  public Vector2 getScale() {
    return scale;
  }

  public String getUUID() {
    return uuid;
  }

  @Override
  public void reset() {
    lastPosition.x = 0;
    lastPosition.y = 0;
    position.x = 0;
    position.y = 0;
    dimensions.x = 0;
    dimensions.y = 0;
    type = GameObjectType.NONE;
    velocity.x = 0;
    velocity.y = 0;
    accelleration.x = 0;
    accelleration.y = 0;
    lastCollision = null;
    id = "";
    scale.set(1f, 1f);
    color = Color.WHITE.cpy();
    uuid = UUID.randomUUID().toString();
  }

}
