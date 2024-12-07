package net.pm.specifics.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.pm.specifics.block.VariantChestBlock;

public class VariantChestBlockEntity extends ChestBlockEntity {
    protected VariantChestBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    public VariantChestBlockEntity(BlockPos pos, BlockState state) {
        this(Registries.BLOCK_ENTITY_TYPE.get(Identifier.of(((VariantChestBlock)state.getBlock()).type)), pos, state);
    }
}
