package de.bitbrain.clouboy.social;

import java.util.HashMap;
import java.util.Map;

import de.bitbrain.clouboy.core.GameObject;
import de.bitbrain.clouboy.core.GameObjectType;
import de.bitbrain.clouboy.core.PlayerBehavior;

public class AchievementManager implements PlayerBehavior.PlayerListener {

    private SocialManager socialManager;

    private Map<String, Integer> progress, goals;

    public AchievementManager(SocialManager socialManager) {
        this.socialManager = socialManager;
        progress = new HashMap<String, Integer>();
        goals = new HashMap<String, Integer>();
    }

    @Override
    public void onJump(GameObject player, int jumps, int maxJumps) {

    }

    @Override
    public void onLand(GameObject player, int jumps, int maxJumps) {
        GameObjectType type = player.getLastCollision().getType();
        if (type == GameObjectType.CLOUD) {
            socialManager.incrementAchievement(Achievement.CLOUD_INVADER, 1);
            socialManager.incrementAchievement(Achievement.CLOUD_MASTER, 1);
            socialManager.incrementAchievement(Achievement.CLOU_BOY, 1);

            if (jumps == PlayerBehavior.MAX_JUMPS - 1) {
                increment(Achievement.CLOUD_PRESSURE);
            } else {
                reset(Achievement.CLOUD_PRESSURE);
            }
            if (jumps <= 1) {
                increment(Achievement.DATE_WITH_THE_DEVIL);
            } else {
                reset(Achievement.DATE_WITH_THE_DEVIL);
            }
        } else if (type == GameObjectType.DARK_CLOUD) {
            increment(Achievement.MICHAEL_BAY);
        }
    }

    public void reset() {
        progress.put(Achievement.DATE_WITH_THE_DEVIL, 0);
        progress.put(Achievement.MICHAEL_BAY, 0);
        progress.put(Achievement.CLOUD_PRESSURE, 0);
        goals.put(Achievement.DATE_WITH_THE_DEVIL, 10);
        goals.put(Achievement.MICHAEL_BAY, 10);
        goals.put(Achievement.CLOUD_PRESSURE, 5);
    }

    private void reset(String achievement) {
        if (progress.containsKey(achievement)) {
            progress.put(achievement, 0);
        }
    }

    private void increment(String achievement) {
        if (progress.containsKey(achievement)) {
            int value = progress.get(achievement) + 1;
            progress.put(achievement, value);
            if (value == goals.get(achievement)) {
                socialManager.submitAchievement(achievement);
            }
        }
    }
}
