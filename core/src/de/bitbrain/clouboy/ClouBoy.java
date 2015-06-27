package de.bitbrain.clouboy;

import com.badlogic.gdx.Game;

import de.bitbrain.clouboy.screens.IngameScreen;

public class ClouBoy extends Game {

  @Override
  public void create() {
    setScreen(new IngameScreen(this));
  }

}
