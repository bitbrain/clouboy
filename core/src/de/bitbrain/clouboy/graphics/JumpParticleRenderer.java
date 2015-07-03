package de.bitbrain.clouboy.graphics;

import de.bitbrain.clouboy.assets.Assets;
import de.bitbrain.clouboy.core.GameObject;
import de.bitbrain.clouboy.core.GameObjectType;
import de.bitbrain.clouboy.core.PlayerBehavior.PlayerListener;

public class JumpParticleRenderer implements PlayerListener {

  private GameObject player;

  private ParticleRenderer particleRenderer;

  public JumpParticleRenderer(ParticleRenderer particleRenderer) {
    this.particleRenderer = particleRenderer;
  }

  @Override
  public void onJump(GameObject player, int jumps, int maxJumps) {
    this.player = player;
    if (player.getLastCollision() != null && player.getLastCollision().getType().equals(GameObjectType.CLOUD)) {
      particleRenderer.applyParticleEffect(player, Assets.PRT_CLOUDS, 0, 0);
    }
  }

}
