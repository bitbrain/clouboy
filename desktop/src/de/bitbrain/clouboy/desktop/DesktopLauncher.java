package de.bitbrain.clouboy.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import de.bitbrain.clouboy.ClouBoy;

public class DesktopLauncher {
  public static void main(String[] arg) {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.width = 1000;
    config.height = 720;
    new LwjglApplication(new ClouBoy(), config);
  }
}
