package jiekie.joinkit.util;

import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class ParticleUtil {
    public static void glow(Player player) {
        player.spawnParticle(Particle.GLOW, player.getLocation(), 50, 1, 2, 1, 30);
    }
}
