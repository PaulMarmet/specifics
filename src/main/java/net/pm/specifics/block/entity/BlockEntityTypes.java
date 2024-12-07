package net.pm.specifics.block.entity;

import com.mojang.datafixers.types.Type;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Util;
import net.pm.specifics.block.Blocks;

public class BlockEntityTypes {

    public static final BlockEntityType<VariantChestBlockEntity> OAK_CHEST = createChest("oak_chest", Blocks.OAK_CHEST);
    public static final BlockEntityType<VariantChestBlockEntity> SPRUCE_CHEST = createChest("spruce_chest", Blocks.SPRUCE_CHEST);
    public static final BlockEntityType<VariantChestBlockEntity> BIRCH_CHEST = createChest("birch_chest", Blocks.BIRCH_CHEST);
    public static final BlockEntityType<VariantChestBlockEntity> JUNGLE_CHEST = createChest("jungle_chest", Blocks.JUNGLE_CHEST);
    public static final BlockEntityType<VariantChestBlockEntity> ACACIA_CHEST = createChest("acacia_chest", Blocks.ACACIA_CHEST);
    public static final BlockEntityType<VariantChestBlockEntity> DARK_OAK_CHEST = createChest("dark_oak_chest", Blocks.DARK_OAK_CHEST);
    public static final BlockEntityType<VariantChestBlockEntity> MANGROVE_CHEST = createChest("mangrove_chest", Blocks.MANGROVE_CHEST);
    public static final BlockEntityType<VariantChestBlockEntity> CHERRY_CHEST = createChest("cherry_chest", Blocks.CHERRY_CHEST);
    public static final BlockEntityType<VariantChestBlockEntity> BAMBOO_CHEST = createChest("bamboo_chest", Blocks.BAMBOO_CHEST);
    public static final BlockEntityType<VariantChestBlockEntity> CRIMSON_CHEST = createChest("crimson_chest", Blocks.CRIMSON_CHEST);
    public static final BlockEntityType<VariantChestBlockEntity> WARPED_CHEST = createChest("warped_chest", Blocks.WARPED_CHEST);

    public static <T extends BlockEntity> BlockEntityType<T> create(String id, BlockEntityType.Builder<T> builder) {
        Type<?> type = Util.getChoiceType(TypeReferences.BLOCK_ENTITY, id);
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, id, builder.build(type));
    }

    public static BlockEntityType<VariantChestBlockEntity> createChest(String id, Block block) {
        return create(id, BlockEntityType.Builder.create(VariantChestBlockEntity::new, block));
    }

    public static void init() {

    }
}
