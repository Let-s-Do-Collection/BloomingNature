package net.satisfy.bloomingnature.client;

import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;

public final class Wind {
    private static float x;
    private static float z;
    private static float strength;

    private Wind() {
    }

    static void tick(Level level) {
        float time = (float) level.getGameTime();
        float direction = time * 0.00021F + Mth.sin(time * 0.00093F) * 1.3F;
        float gust = Mth.sin(time * 0.0131F) * 0.5F + Mth.sin(time * 0.0347F + 1.7F) * 0.3F + Mth.sin(time * 0.0791F + 4.2F) * 0.2F;
        float weather = 1.0F + level.getRainLevel(1.0F) * 0.9F + level.getThunderLevel(1.0F) * 1.2F;
        float target = Math.max(0.0F, 0.35F + gust * 0.55F) * weather;
        strength += (target - strength) * 0.05F;
        x = Mth.cos(direction) * strength;
        z = Mth.sin(direction) * strength;
    }

    public static float x() {
        return x * 0.045F;
    }

    public static float z() {
        return z * 0.045F;
    }

    public static float strength() {
        return strength;
    }
}
