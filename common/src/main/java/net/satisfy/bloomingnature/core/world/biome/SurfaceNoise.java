package net.satisfy.bloomingnature.core.world.biome;

public final class SurfaceNoise {
    private static final int PATCH_SIZE = 3;

    private SurfaceNoise() {
    }

    public static int patchIndex(int x, int y, int z) {
        int cellX = Math.floorDiv(x, PATCH_SIZE);
        int cellZ = Math.floorDiv(z, PATCH_SIZE);
        long best = Long.MAX_VALUE;
        int value = 0;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                int cx = cellX + dx;
                int cz = cellZ + dz;
                long hash = hash(cx, y, cz);
                long pointX = (long) cx * PATCH_SIZE * 256 + (hash & 0xFF) * PATCH_SIZE;
                long pointZ = (long) cz * PATCH_SIZE * 256 + ((hash >>> 8) & 0xFF) * PATCH_SIZE;
                long distX = (long) x * 256 + 128 - pointX;
                long distZ = (long) z * 256 + 128 - pointZ;
                long distance = distX * distX + distZ * distZ;
                if (distance < best) {
                    best = distance;
                    value = (int) Long.remainderUnsigned(hash >>> 16, 100);
                }
            }
        }
        return value;
    }

    public static float smooth(int x, int z, float scale) {
        float xf = x * scale;
        float zf = z * scale;
        int xi = (int) Math.floor(xf);
        int zi = (int) Math.floor(zf);
        float tx = fade(xf - xi);
        float tz = fade(zf - zi);
        float top = lerp(unit(xi, zi), unit(xi + 1, zi), tx);
        float bottom = lerp(unit(xi, zi + 1), unit(xi + 1, zi + 1), tx);
        return lerp(top, bottom, tz);
    }

    private static float unit(int x, int z) {
        return (hash(x, 0, z) >>> 40) / (float) (1L << 24);
    }

    private static float fade(float t) {
        return t * t * (3.0F - 2.0F * t);
    }

    private static float lerp(float a, float b, float t) {
        return a + (b - a) * t;
    }

    private static long hash(int x, int y, int z) {
        long h = x * 0x9E3779B97F4A7C15L ^ y * 0xC2B2AE3D27D4EB4FL ^ z * 0x165667B19E3779F9L;
        h ^= h >>> 33;
        h *= 0xFF51AFD7ED558CCDL;
        h ^= h >>> 33;
        h *= 0xC4CEB9FE1A85EC53L;
        h ^= h >>> 33;
        return h;
    }
}
