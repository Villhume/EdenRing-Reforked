package paulevs.edenring.world.biomes.air;

import org.betterx.bclib.api.v2.levelgen.biomes.BCLBiomeBuilder;

import paulevs.edenring.registries.*;
import paulevs.edenring.world.biomes.BiomesCommonMethods;
import paulevs.edenring.world.biomes.EdenRingBiome;

public class SkyColonyBiome extends EdenRingBiome.Config {
    public SkyColonyBiome() {
        super(EdenBiomes.SKY_COLONY.location());
    }

    @Override
    protected void addCustomBuildData(BCLBiomeBuilder builder) {
        BiomesCommonMethods.addDefaultVoidFeatures(builder);
        builder
                .spawn(EdenEntities.DISKWING, 20, 3, 6)
                .fogDensity(1F)
                .fogColor(0x84d341)
                .waterColor(0x1e7d56)
                .plantsColor(0x1e7d56)
                .music(EdenSounds.MUSIC_COMMON)
                .particles(EdenParticles.YOUNG_VOLVOX, 0.0001F)
                .feature(EdenFeatures.VOLVOX)
                .feature(EdenFeatures.GIGANTIC_VOLVOX)
                .feature(EdenFeatures.PARIGNUM);
    }
}
