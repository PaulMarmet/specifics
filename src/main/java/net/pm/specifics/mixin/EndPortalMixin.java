package net.pm.specifics.mixin;

import net.minecraft.block.EndPortalBlock;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.pm.specifics.SpecificsConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

// When SpecificsConfig.endPortal is true, end portals have vanilla
// functionality.
// When SpecificsConfig.endPortal is false, all end portals behave like end
// portals in the end dimension - sending players to their spawn points and
// entities to the world spawn point.
@Mixin(EndPortalBlock.class)
public class EndPortalMixin {
    @ModifyVariable(method = "createTeleportTarget", at = @At(value = "STORE"), ordinal = 0)
    private RegistryKey<World> injected(RegistryKey<World> registryKey) {
        return SpecificsConfig.endPortal ? registryKey : registryKey == World.END ? World.OVERWORLD : World.END;
    }

    @ModifyVariable(method = "createTeleportTarget", at = @At(value = "STORE"), ordinal = 0)
    private boolean injected(boolean bl) {
        return bl && SpecificsConfig.endPortal;
    }

    @ModifyArg(method = "createTeleportTarget", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/TeleportTarget;<init>(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/util/math/Vec3d;FFLnet/minecraft/world/TeleportTarget$PostDimensionTransition;)V"), index = 5)
    private TeleportTarget.PostDimensionTransition injected(TeleportTarget.PostDimensionTransition postDimensionTransition) {
        return SpecificsConfig.endPortal ? postDimensionTransition : TeleportTarget.NO_OP;
    }
}
