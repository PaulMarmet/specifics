package net.pm.specifics.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.pm.specifics.Specifics;
import net.pm.specifics.block.Blocks;

public class ItemGroups {

    public static final ItemGroup SPECIFICS_GROUP = Registry.register(Registries.ITEM_GROUP, Identifier.of(Specifics.MOD_ID, "oak_chest"), FabricItemGroup.builder().displayName(Text.translatable("itemgroup.specifics")).icon(() -> new ItemStack((Blocks.OAK_CHEST))).entries(((displayContext, entries) -> {
        entries.add(Blocks.OAK_BOOKSHELF);
        entries.add(Blocks.OAK_CHEST);
        entries.add(Blocks.OAK_TRAPPED_CHEST);
        entries.add(Blocks.OAK_LADDER);

        entries.add(Blocks.SPRUCE_BOOKSHELF);
        entries.add(Blocks.SPRUCE_CHEST);
        entries.add(Blocks.SPRUCE_TRAPPED_CHEST);
        entries.add(Blocks.SPRUCE_LADDER);

        entries.add(Blocks.BIRCH_BOOKSHELF);
        entries.add(Blocks.BIRCH_CHEST);
        entries.add(Blocks.BIRCH_TRAPPED_CHEST);
        entries.add(Blocks.BIRCH_LADDER);

        entries.add(Blocks.JUNGLE_BOOKSHELF);
        entries.add(Blocks.JUNGLE_CHEST);
        entries.add(Blocks.JUNGLE_TRAPPED_CHEST);
        entries.add(Blocks.JUNGLE_LADDER);

        entries.add(Blocks.ACACIA_BOOKSHELF);
        entries.add(Blocks.ACACIA_CHEST);
        entries.add(Blocks.ACACIA_TRAPPED_CHEST);
        entries.add(Blocks.ACACIA_LADDER);

        entries.add(Blocks.DARK_OAK_BOOKSHELF);
        entries.add(Blocks.DARK_OAK_CHEST);
        entries.add(Blocks.DARK_OAK_TRAPPED_CHEST);
        entries.add(Blocks.DARK_OAK_LADDER);

        entries.add(Blocks.MANGROVE_BOOKSHELF);
        entries.add(Blocks.MANGROVE_CHEST);
        entries.add(Blocks.MANGROVE_TRAPPED_CHEST);
        entries.add(Blocks.MANGROVE_LADDER);

        entries.add(Blocks.CHERRY_BOOKSHELF);
        entries.add(Blocks.CHERRY_CHEST);
        entries.add(Blocks.CHERRY_TRAPPED_CHEST);
        entries.add(Blocks.CHERRY_LADDER);

        entries.add(Blocks.BAMBOO_BOOKSHELF);
        entries.add(Blocks.BAMBOO_CHEST);
        entries.add(Blocks.BAMBOO_TRAPPED_CHEST);
        entries.add(Blocks.BAMBOO_LADDER);

        entries.add(Blocks.CRIMSON_BOOKSHELF);
        entries.add(Blocks.CRIMSON_CHEST);
        entries.add(Blocks.CRIMSON_TRAPPED_CHEST);
        entries.add(Blocks.CRIMSON_LADDER);

        entries.add(Blocks.WARPED_BOOKSHELF);
        entries.add(Blocks.WARPED_CHEST);
        entries.add(Blocks.WARPED_TRAPPED_CHEST);
        entries.add(Blocks.WARPED_LADDER);
    })).build());

    public static void init() {

    }
}
