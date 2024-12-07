package net.pm.specifics.block;

import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;

import java.util.function.Supplier;

public class VariantChestBlock extends ChestBlock {
    public String type;

    public VariantChestBlock(Settings settings, Supplier<BlockEntityType<? extends ChestBlockEntity>> supplier, String type) {
        super(settings, supplier);
        this.type = type;
    }
}
