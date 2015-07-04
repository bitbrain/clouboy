package de.bitbrain.clouboy.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;

public class GraphicsFactory {

  public static Texture createTexture(int width, int height, Color color) {
    Pixmap map = new Pixmap(width, height, Format.RGBA8888);
    map.setColor(color);
    map.fill();
    Texture texture = new Texture(map);
    map.dispose();
    return texture;
  }
}
