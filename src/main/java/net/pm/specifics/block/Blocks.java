package net.pm.specifics.block;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.pm.specifics.Specifics;

public class Blocks {

    public static final Block OAK_CHEST = registerChest("oak", MapColor.OAK_TAN);
    public static final Block SPRUCE_CHEST = registerChest("spruce", MapColor.SPRUCE_BROWN);
    public static final Block BIRCH_CHEST = registerChest("birch", MapColor.PALE_YELLOW);
    public static final Block JUNGLE_CHEST = registerChest("jungle", MapColor.DIRT_BROWN);
    public static final Block ACACIA_CHEST = registerChest("acacia", MapColor.ORANGE);
    public static final Block DARK_OAK_CHEST = registerChest("dark_oak", MapColor.BROWN);
    public static final Block MANGROVE_CHEST = registerChest("mangrove", MapColor.RED);
    public static final Block CHERRY_CHEST = registerChest("cherry", MapColor.TERRACOTTA_WHITE);
    public static final Block BAMBOO_CHEST = registerChest("bamboo", MapColor.YELLOW);
    public static final Block CRIMSON_CHEST = registerChest("crimson", MapColor.DULL_PINK);
    public static final Block WARPED_CHEST = registerChest("warped", MapColor.DARK_AQUA);

    public static final Block OAK_TRAPPED_CHEST = registerTrappedChest("oak", MapColor.OAK_TAN);
    public static final Block SPRUCE_TRAPPED_CHEST = registerTrappedChest("spruce", MapColor.SPRUCE_BROWN);
    public static final Block BIRCH_TRAPPED_CHEST = registerTrappedChest("birch", MapColor.PALE_YELLOW);
    public static final Block JUNGLE_TRAPPED_CHEST = registerTrappedChest("jungle", MapColor.DIRT_BROWN);
    public static final Block ACACIA_TRAPPED_CHEST = registerTrappedChest("acacia", MapColor.ORANGE);
    public static final Block DARK_OAK_TRAPPED_CHEST = registerTrappedChest("dark_oak", MapColor.BROWN);
    public static final Block MANGROVE_TRAPPED_CHEST = registerTrappedChest("mangrove", MapColor.RED);
    public static final Block CHERRY_TRAPPED_CHEST = registerTrappedChest("cherry", MapColor.TERRACOTTA_WHITE);
    public static final Block BAMBOO_TRAPPED_CHEST = registerTrappedChest("bamboo", MapColor.YELLOW);
    public static final Block CRIMSON_TRAPPED_CHEST = registerTrappedChest("crimson", MapColor.DULL_PINK);
    public static final Block WARPED_TRAPPED_CHEST = registerTrappedChest("warped", MapColor.DARK_AQUA);

    public static final Block OAK_BOOKSHELF = registerBookshelf("oak", MapColor.OAK_TAN);
    public static final Block SPRUCE_BOOKSHELF = registerBookshelf("spruce", MapColor.SPRUCE_BROWN);
    public static final Block BIRCH_BOOKSHELF = registerBookshelf("birch", MapColor.PALE_YELLOW);
    public static final Block JUNGLE_BOOKSHELF = registerBookshelf("jungle", MapColor.DIRT_BROWN);
    public static final Block ACACIA_BOOKSHELF = registerBookshelf("acacia", MapColor.ORANGE);
    public static final Block DARK_OAK_BOOKSHELF = registerBookshelf("dark_oak", MapColor.BROWN);
    public static final Block MANGROVE_BOOKSHELF = registerBookshelf("mangrove", MapColor.RED);
    public static final Block CHERRY_BOOKSHELF = registerBookshelf("cherry", MapColor.TERRACOTTA_WHITE);
    public static final Block BAMBOO_BOOKSHELF = registerBookshelf("bamboo", MapColor.YELLOW);
    public static final Block CRIMSON_BOOKSHELF = registerBookshelf("crimson", MapColor.DULL_PINK);
    public static final Block WARPED_BOOKSHELF = registerBookshelf("warped", MapColor.DARK_AQUA);

    public static final Block OAK_LADDER = registerLadder("oak");
    public static final Block SPRUCE_LADDER = registerLadder("spruce");
    public static final Block BIRCH_LADDER = registerLadder("birch");
    public static final Block JUNGLE_LADDER = registerLadder("jungle");
    public static final Block ACACIA_LADDER = registerLadder("acacia");
    public static final Block DARK_OAK_LADDER = registerLadder("dark_oak");
    public static final Block MANGROVE_LADDER = registerLadder("mangrove");
    public static final Block CHERRY_LADDER = registerLadder("cherry");
    public static final Block BAMBOO_LADDER = registerLadder("bamboo");
    public static final Block CRIMSON_LADDER = registerLadder("crimson");
    public static final Block WARPED_LADDER = registerLadder("warped");

    private static Block registerChest(String woodType, MapColor woodColor) {
        return registerBlock(woodType+"_chest", new VariantChestBlock(AbstractBlock.Settings.create().mapColor(woodColor).instrument(NoteBlockInstrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable(), () -> {
            return BlockEntityType.CHEST;
        }, woodType));
    }
    private static Block registerTrappedChest(String woodType, MapColor woodColor) {
        return registerBlock(woodType+"_trapped_chest", new TrappedChestBlock(AbstractBlock.Settings.create().mapColor(woodColor).instrument(NoteBlockInstrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));
    }
    private static Block registerBookshelf(String woodType, MapColor woodColor) {
        return registerBlock(woodType+"_bookshelf", new Block(AbstractBlock.Settings.create().mapColor(woodColor).instrument(NoteBlockInstrument.BASS).strength(1.5F).sounds(BlockSoundGroup.WOOD).burnable()));
    }
    private static Block registerLadder(String woodType) {
        return registerBlock(woodType+"_ladder", new LadderBlock(AbstractBlock.Settings.create().notSolid().strength(0.4F).sounds(BlockSoundGroup.LADDER).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)));
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Specifics.MOD_ID, name), block);
    }
    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, Identifier.of(Specifics.MOD_ID, name), new BlockItem(block, new Item.Settings()));
    }

    public static void init() {

    }
}
