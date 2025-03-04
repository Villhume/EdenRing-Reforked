package paulevs.edenring.registries;

import com.google.common.collect.Maps;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.TillableBlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import org.betterx.bclib.api.v2.ComposterAPI;
import org.betterx.bclib.api.v2.ShovelAPI;
import org.betterx.bclib.blocks.BaseBlock;
import org.betterx.bclib.blocks.BaseLeavesBlock;
import org.betterx.bclib.blocks.BaseVineBlock;
import org.betterx.bclib.blocks.FeatureSaplingBlock;
import org.betterx.bclib.complexmaterials.ComplexMaterial;
import org.betterx.bclib.config.PathConfig;
import org.betterx.bclib.registry.BlockRegistry;
import org.betterx.worlds.together.tag.v3.CommonBlockTags;
import org.betterx.worlds.together.tag.v3.CommonItemTags;
import org.betterx.worlds.together.tag.v3.MineableTags;
import org.betterx.worlds.together.tag.v3.TagManager;
import org.jetbrains.annotations.NotNull;
import paulevs.edenring.EdenRing;
import paulevs.edenring.blocks.*;
import paulevs.edenring.blocks.complex.BrainTreeComplexMaterial;
import paulevs.edenring.blocks.complex.EdenWoodenComplexMaterial;
import paulevs.edenring.config.Configs;

import java.util.Map;

public class EdenBlocks {
	public static final BlockRegistry REGISTRY = new BlockRegistry(new PathConfig(EdenRing.MOD_ID, "blocks"));
	
	public static final Block EDEN_GRASS_BLOCK = register(
			"eden_grass",
			new EdenGrassBlock(),
			BlockTags.NYLIUM
	);
	public static final Block EDEN_MYCELIUM = register("eden_mycelium",
			new TexturedTerrainBlock(),
			BlockTags.NYLIUM
	);
	public static final Block MOSSY_STONE = register("mossy_stone", new MossyStoneBlock());

	public static final Block AURITIS_SAPLING = register(
			"auritis_sapling",
			new FeatureSaplingBlock<>((state) -> EdenFeatures.AURITIS_TREE.configuredFeature)
	);
	public static final Block AURITIS_LEAVES = register(
			"auritis_leaves",
			new AuritisLeavesBlock()
	);

	public static final ComplexMaterial AURITIS_MATERIAL = new EdenWoodenComplexMaterial(EdenRing.MOD_ID, "auritis", "eden", MapColor.COLOR_BROWN, MapColor.GOLD).init(REGISTRY, EdenItems.REGISTRY);
	
	public static final Block BALLOON_MUSHROOM_SMALL = register("balloon_mushroom_small", new BalloonMushroomSmallBlock());
	public static final Block BALLOON_MUSHROOM_BLOCK = register("balloon_mushroom_block", new BalloonMushroomBlock());
	public static final Block BALLOON_MUSHROOM_STEM = register("balloon_mushroom_stem", new BalloonMushroomStemBlock());
	public static final Block BALLOON_MUSHROOM_BRANCH = register("balloon_mushroom_branch", new BranchBlock(BALLOON_MUSHROOM_STEM));
	public static final ComplexMaterial BALLOON_MUSHROOM_MATERIAL = new EdenWoodenComplexMaterial(EdenRing.MOD_ID, "balloon_mushroom", "eden", MapColor.COLOR_PURPLE, MapColor.COLOR_PURPLE).init(REGISTRY, EdenItems.REGISTRY);
	public static final Block BALLOON_MUSHROOM_HYMENOPHORE = register("balloon_mushroom_hymenophore", new ShadedVineBlock());
	public static final Map<DyeColor, Block> MYCOTIC_LANTERN_COLORED = Maps.newEnumMap(DyeColor.class);
	public static final Map<DyeColor, Block> BALLOON_MUSHROOM_SPOROCARP_COLORED = Maps.newEnumMap(DyeColor.class);
	
	static {
		for (int i = 0; i < 16; i++) {
			DyeColor color = DyeColor.byId(i);
			Block lantern = register("mycotic_lantern_" + color.getName(), new MycoticLanternBlock());
			MYCOTIC_LANTERN_COLORED.put(color, lantern);
		}
		
		for (int i = 0; i < 16; i++) {
			DyeColor color = DyeColor.byId(i);
			Block lantern = register(
				"balloon_mushroom_sporocarp_" + color.getName(),
				new BaseBlock(FabricBlockSettings.copyOf(Blocks.MUSHROOM_STEM).sounds(SoundType.WOOL))
			);
			BALLOON_MUSHROOM_SPOROCARP_COLORED.put(color, lantern);
		}
	}
	// Pulse Tree //
	public static final Block PULSE_TREE_SAPLING = register("pulse_tree_sapling", new FeatureSaplingBlock<>((state) -> EdenFeatures.PULSE_TREE.configuredFeature));
	public static final Block PULSE_TREE = register("pulse_tree", new PulseTreeBlock());
	public static final ComplexMaterial PULSE_TREE_MATERIAL = new EdenWoodenComplexMaterial(EdenRing.MOD_ID, "pulse_tree", "eden", MapColor.COLOR_CYAN, MapColor.COLOR_CYAN).init(REGISTRY, EdenItems.REGISTRY);
	// Brain Tree //
	public static final Block BRAIN_TREE_BLOCK_IRON = register("brain_tree_block_iron", new BrainTreeBlock(MapColor.COLOR_LIGHT_GRAY));
	public static final Block BRAIN_TREE_BLOCK_COPPER = register("brain_tree_block_copper", new BrainTreeBlock(MapColor.COLOR_ORANGE));
	public static final Block BRAIN_TREE_BLOCK_GOLD = register("brain_tree_block_gold", new BrainTreeBlock(MapColor.GOLD));
	public static final ComplexMaterial BRAIN_TREE_MATERIAL = new BrainTreeComplexMaterial("brain_tree").init(REGISTRY, EdenItems.REGISTRY);
	public static final Block COPPER_FRAMED_BRAIN_TREE_LOG = register("copper_framed_brain_tree_log", new BrainTreeLogBlock());
	public static final Block IRON_FRAMED_BRAIN_TREE_LOG = register("iron_framed_brain_tree_log", new BrainTreeLogBlock());
	public static final Block GOLD_FRAMED_BRAIN_TREE_LOG = register("gold_framed_brain_tree_log", new BrainTreeLogBlock());
	// Volvox //
	public static final Block VOLVOX_BLOCK = register("volvox_block", new VolvoxBlock());
	public static final Block VOLVOX_BLOCK_DENSE = register("volvox_block_dense", new VolvoxBlockDense());
	// Aquatus //
	public static final Block AQUATUS_SAPLING = register("aquatus_sapling", new AquatusSapling());
	public static final Block AQUATUS_BLOCK = register("aquatus_block", new AquatusBlock());
	public static final Block AQUATUS_ROOTS = registerBlockOnly("aquatus_roots", new AquatusRootsBlock());
	// Mosses //
	public static final Block EDEN_MOSS = register("eden_moss", new EdenMossBlock());
	public static final Block PARIGNUM = register("parignum", new Parignum());
	// Grasses //
	public static final Block MYCOTIC_GRASS = register("mycotic_grass", new MycoticGrass());
	public static final Block GOLDEN_GRASS = register("golden_grass", new OverlayPlantBlock(true));
	public static final Block COPPER_GRASS = register("copper_grass", new OverlayPlantBlock(true));
	public static final Block IRON_GRASS = register("iron_grass", new OverlayPlantBlock(true));
	public static final Block GOLD_GRASS = register("gold_grass", new OverlayPlantBlock(true));
	public static final Block TALL_COPPER_GRASS = register("tall_copper_grass", new OverlayDoublePlantBlock());
	public static final Block TALL_IRON_GRASS = register("tall_iron_grass", new OverlayDoublePlantBlock());
	public static final Block TALL_GOLD_GRASS = register("tall_gold_grass", new OverlayDoublePlantBlock());
	// Plants //
	public static final Block LONLIX = register("lonlix", new OverlayPlantBlock(true));
	
	public static final Block VIOLUM = register("violum", new OverlayDoublePlantBlock());
	public static final Block TALL_BALLOON_MUSHROOM = register("tall_balloon_mushroom", new TallBalloonMushroom());
	public static final Block TALL_MYCOTIC_GRASS = register("tall_mycotic_grass", new EdenDoublePlantBlock());
	public static final Block LIMPHIUM_SAPLING = register("limphium_sapling", new LimphiumSapling());
	public static final Block LIMPHIUM = registerBlockOnly("limphium", new LimphiumBlock());
	// public static final Block ALAESPES = register("alaespes", new AlaespesBlock());
	
	public static final Block EDEN_VINE = register("eden_vine", new OverlayVineBlock());
	
	public static final Block SYMBIOTIC_MOLD = register("symbiotic_mold", new SymbioticMoldBlock(0));
	public static final Block SYMBIOTIC_MOLD_EMISSIVE = register("symbiotic_mold_emissive", new SymbioticMoldBlock(13));
	
	public static final Block GRAVILITE_BLOCK = register("gravilite_block", new GraviliteBlock());
	public static final Block GRAVILITE_SHARDS = register("gravilite_shards", new GraviliteShardsBlock());
	public static final Block GRAVILITE_LAMP = register("gravilite_lamp", new GraviliteLampBlock());
	public static final Block GRAVILITE_LANTERN = register("gravilite_lantern", new GraviliteLanternBlock());
	public static final Block GRAVILITE_LANTERN_TALL = register("gravilite_lantern_tall", new GraviliteTallLanternBlock());
	
	public static final Block GRAVITY_COMPRESSOR = register("gravity_compressor", new GravityCompressorBlock());
	
	public static final Block PORTAL_BLOCK = registerBlockOnly("portal_block", new EdenPortalBlock());
	public static final Block PORTAL_CENTER = registerBlockOnly("portal_center", new EdenPortalCenterBlock());
	
	//public static final Block INSULECTRICA_STEM = register("insulectrica_stem", new BaseBlock(FabricBlockSettings.copyOf(Blocks.STONE)));
	//public static final Block INSULECTRICA_ROD = register("insulectrica_rod", new BaseBlock(FabricBlockSettings.copyOf(Blocks.STONE)));
	//public static final block CLOUD_ANEMONE_BLOCK = register("cloud_anemone_block", new CloudAnemoneBlock());
	//public static final block ASTRALLIUM_ORE = register("astrallium_ore", new AstralliumOreBlock());


	public static void init() {
		BlockRegistry.getModBlocks(EdenRing.MOD_ID).forEach(block -> {
			if (block instanceof BaseLeavesBlock) {
				TagManager.BLOCKS.add(MineableTags.HOE, block);
				TagManager.BLOCKS.add(CommonBlockTags.LEAVES, block);
				TagManager.ITEMS.add(CommonItemTags.LEAVES, block);
				ComposterAPI.allowCompost(0.3F, block);
			}
			else if (block instanceof GrassBlock) {
				TagManager.BLOCKS.add(MineableTags.SHOVEL, block);
				ShovelAPI.addShovelBehaviour(block, Blocks.DIRT_PATH.defaultBlockState());
				TillableBlockRegistry.register(block, HoeItem::onlyIfAirAbove, Blocks.FARMLAND.defaultBlockState());
			}
			else if (block instanceof BonemealableBlock) {
				TagManager.BLOCKS.add(MineableTags.HOE, block);
				if (block.asItem() != Items.AIR) {
					ComposterAPI.allowCompost(0.1F, block);
				}
			}			
			if (block instanceof BaseVineBlock) {
				TagManager.BLOCKS.add(BlockTags.CLIMBABLE, block);
			}
		});
	}
	
	private static Block register(String name, Block block) {
		return REGISTRY.register(EdenRing.makeID(name), block);
	}

	public static Block registerBlock(ResourceLocation id, Block block, TagKey<Block>... tags) {
//		Sometimes maybe
//		if (!Configs.BLOCK_CONFIG.getBooleanRoot(id.getPath(), true)) {
//			return block;
//		}
		getBlockRegistry().register(id, block);
		TagManager.BLOCKS.add(block, tags);
		return block;
	}

	private static Block register(String name, Block block,  TagKey<Block>... tags) {
		return registerBlock(EdenRing.makeID(name), block, tags);
	}
	
	private static Block registerBlockOnly(String name, Block block) {
		return REGISTRY.registerBlockOnly(EdenRing.makeID(name), block);
	}
	
	public static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
		return false;
	}

	@NotNull
	public static BlockRegistry getBlockRegistry() {
		return REGISTRY;
	}
}
