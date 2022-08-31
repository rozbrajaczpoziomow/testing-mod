package mod.rozbrajaczpoziomow.testing.worldgen;

import mod.rozbrajaczpoziomow.testing.blocks.BlockRegister;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class UnknownBlockGeneration {
	public static void gen(final BiomeLoadingEvent event) { // From Y = 11 to Y = 17; Max Vein = 3 ores
		OreFeatureConfig oreFeatureConfig = new OreFeatureConfig(
				OreFeatureConfig.FillerBlockType.NATURAL_STONE,
				BlockRegister.UnknownBlock.get().defaultBlockState(), 1);

		ConfiguredPlacement<TopSolidRangeConfig> configuredPlacement = Placement.RANGE.configured(
				new TopSolidRangeConfig(11, 11, 17));

		ConfiguredFeature<?, ?> oreFeature = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, BlockRegister.UnknownBlock.get().getRegistryName(),
				Feature.ORE.configured(oreFeatureConfig).decorated(configuredPlacement)
						.squared().count(3));

		event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, oreFeature);
	}
}
