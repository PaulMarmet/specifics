package net.pm.specifics.mixin;

import net.minecraft.block.EndPortalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.EndPlatformFeature;
import net.pm.specifics.SpecificsConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EndPortalBlock.class)
public class EndPortalMixin {
    /**
     * @author Paul Marmet
     * @reason I'm lazy, and anyway, probably no one will see this code.
     */
    @Overwrite
    public TeleportTarget createTeleportTarget(ServerWorld world, Entity entity, BlockPos pos) {
        RegistryKey<World> registryKey = world.getRegistryKey() == World.END ? World.OVERWORLD : World.END;
        ServerWorld serverWorld = world.getServer().getWorld(registryKey);
        if (serverWorld == null) {
            return null;
        } else {
            boolean bl = SpecificsConfig.endPortal && registryKey == World.END;
            BlockPos blockPos = bl ? ServerWorld.END_SPAWN_POS : serverWorld.getSpawnPos();
            Vec3d vec3d = blockPos.toBottomCenterPos();
            float f = entity.getYaw();
            if (bl) {
                EndPlatformFeature.generate(serverWorld, BlockPos.ofFloored(vec3d).down(), true);
                f = Direction.WEST.asRotation();
                if (entity instanceof ServerPlayerEntity) {
                    vec3d = vec3d.subtract(0.0, 1.0, 0.0);
                }
            } else {
                if (entity instanceof ServerPlayerEntity) {
                    ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)entity;
                    return serverPlayerEntity.getRespawnTarget(false, TeleportTarget.NO_OP);
                }

                vec3d = entity.getWorldSpawnPos(serverWorld, blockPos).toBottomCenterPos();
            }

            return new TeleportTarget(serverWorld, vec3d, entity.getVelocity(), f, entity.getPitch(), TeleportTarget.SEND_TRAVEL_THROUGH_PORTAL_PACKET.then(TeleportTarget.ADD_PORTAL_CHUNK_TICKET));
        }
    }
}
