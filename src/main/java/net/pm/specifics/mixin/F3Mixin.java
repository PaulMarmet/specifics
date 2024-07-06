package net.pm.specifics.mixin;

import com.google.common.collect.Lists;
import com.mojang.blaze3d.platform.GlDebugInfo;
import it.unimi.dsi.fastutil.longs.LongSet;
import it.unimi.dsi.fastutil.longs.LongSets;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.SharedConstants;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.ServerTickManager;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.tick.TickManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mixin(DebugHud.class)
public class F3Mixin {

    @Shadow
    private WorldChunk getChunk() {return null;}
    @Shadow
    private WorldChunk getClientChunk() {return null;}
    @Shadow
    private ServerWorld getServerWorld() {return null;}
    @Shadow
    private String getServerWorldDebugString() {return null;}
    @Shadow
    private World getWorld() {return null;}
    @Shadow
    public void resetChunk() {}
    @Shadow
    public static long toMiB(long bytes) {return 0;}
    @Final
    @Shadow
    public DebugHud.AllocationRateCalculator allocationRateCalculator;
    @Final
    @Shadow
    private MinecraftClient client;
    @Shadow
    private ChunkPos pos;

    @Redirect(method = "drawLeftText(Lnet/minecraft/client/gui/DrawContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/DebugHud;getLeftText()Ljava/util/List;"))
    protected List<String> getLeftText(DebugHud instance) {
        IntegratedServer integratedServer = client.getServer();
        ClientPlayNetworkHandler clientPlayNetworkHandler = client.getNetworkHandler();
        ClientConnection clientConnection = clientPlayNetworkHandler.getConnection();
        float f = clientConnection.getAveragePacketsSent();
        float g = clientConnection.getAveragePacketsReceived();
        TickManager tickManager = getWorld().getTickManager();
        String string;
        if (tickManager.isStepping()) {
            string = " (frozen - stepping)";
        } else if (tickManager.isFrozen()) {
            string = " (frozen)";
        } else {
            string = "";
        }

        String string3;
        if (integratedServer != null) {
            ServerTickManager serverTickManager = integratedServer.getTickManager();
            boolean bl = serverTickManager.isSprinting();
            if (bl) {
                string = " (sprinting)";
            }

            String string2 = bl ? "-" : String.format(Locale.ROOT, "%.1f", tickManager.getMillisPerTick());
            string3 = String.format(Locale.ROOT, "Integrated server @ %.1f/%s ms%s, %.0f tx, %.0f rx", integratedServer.getAverageTickTime(), string2, string, f, g);
        } else {
            string3 = String.format(Locale.ROOT, "\"%s\" server%s, %.0f tx, %.0f rx", clientPlayNetworkHandler.getBrand(), string, f, g);
        }

        BlockPos blockPos = client.getCameraEntity().getBlockPos();
        if (client.hasReducedDebugInfo()) {
            return Lists.<String>newArrayList(
                    "Minecraft " + SharedConstants.getGameVersion().getName() + " (" + client.getGameVersion() + "/" + ClientBrandRetriever.getClientModName() + ")",
                    client.fpsDebugString,
                    string3,
                    client.worldRenderer.getChunksDebugString(),
                    client.worldRenderer.getEntitiesDebugString(),
                    "P: " + client.particleManager.getDebugString() + ". T: " + client.world.getRegularEntityCount(),
                    client.world.asString()
            );
        } else {
            Entity entity = client.getCameraEntity();
            Direction direction = entity.getHorizontalFacing();

            String string4 = switch (direction) {
                case NORTH -> "Towards negative Z";
                case SOUTH -> "Towards positive Z";
                case WEST -> "Towards negative X";
                case EAST -> "Towards positive X";
                default -> "Invalid";
            };
            ChunkPos chunkPos = new ChunkPos(blockPos);
            if (!Objects.equals(pos, chunkPos)) {
                pos = chunkPos;
                resetChunk();
            }

            World world = getWorld();
            LongSet longSet = (LongSet)(world instanceof ServerWorld ? ((ServerWorld)world).getForcedChunks() : LongSets.EMPTY_SET);
            List<String> list = Lists.<String>newArrayList(
                    "Minecraft "
                            + SharedConstants.getGameVersion().getName()
                            + " ("
                            + client.getGameVersion()
                            + "/"
                            + ClientBrandRetriever.getClientModName()
                            + ("release".equalsIgnoreCase(client.getVersionType()) ? "" : "/" + client.getVersionType())
                            + ")",
                    client.fpsDebugString,
                    string3,
                    client.worldRenderer.getChunksDebugString(),
                    client.worldRenderer.getEntitiesDebugString(),
                    "P: " + client.particleManager.getDebugString() + ". T: " + client.world.getRegularEntityCount(),
                    client.world.asString()
            );
            String string5 = getServerWorldDebugString();
            if (string5 != null) {
                list.add(string5);
            }

            list.add(client.world.getRegistryKey().getValue() + " FC: " + longSet.size());
            list.add("");
            WorldChunk worldChunk = getClientChunk();
            if (worldChunk.isEmpty()) {
                list.add("Waiting for chunk...");
            } else {
                WorldChunk worldChunk2 = getChunk();
                if (blockPos.getY() >= client.world.getBottomY() && blockPos.getY() < client.world.getTopY()) {
                    if (worldChunk2 != null) {
                        float h = world.getMoonSize();
                        long l = worldChunk2.getInhabitedTime();
                        LocalDifficulty localDifficulty = new LocalDifficulty(world.getDifficulty(), world.getTimeOfDay(), l, h);
                        list.add(
                                String.format(
                                        Locale.ROOT,
                                        "Local Difficulty: %.2f // %.2f (Day %d)",
                                        localDifficulty.getLocalDifficulty(),
                                        localDifficulty.getClampedLocalDifficulty(),
                                        client.world.getTimeOfDay() / 24000L
                                )
                        );
                    } else {
                        list.add("Local Difficulty: ??");
                    }
                }

                if (worldChunk2 != null && worldChunk2.usesOldNoise()) {
                    list.add("Blending: Old");
                }
            }

            ServerWorld serverWorld = getServerWorld();
            if (serverWorld != null) {
                ServerChunkManager serverChunkManager = serverWorld.getChunkManager();
                SpawnHelper.Info info = serverChunkManager.getSpawnInfo();
                if (info != null) {
                    Object2IntMap<SpawnGroup> object2IntMap = info.getGroupToCount();
                    int m = info.getSpawningChunkCount();
                    list.add(
                            "SC: "
                                    + m
                                    + ", "
                                    + (String) Stream.of(SpawnGroup.values())
                                    .map(group -> Character.toUpperCase(group.getName().charAt(0)) + ": " + object2IntMap.getInt(group))
                                    .collect(Collectors.joining(", "))
                    );
                } else {
                    list.add("SC: N/A");
                }
            }

            PostEffectProcessor postEffectProcessor = client.gameRenderer.getPostProcessor();
            if (postEffectProcessor != null) {
                list.add("Shader: " + postEffectProcessor.getName());
            }

            list.add(
                    client.getSoundManager().getDebugString() + String.format(Locale.ROOT, " (Mood %d%%)", Math.round(client.player.getMoodPercentage() * 100.0F))
            );
            return list;
        }
    }

    @Redirect(method = "drawRightText(Lnet/minecraft/client/gui/DrawContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/DebugHud;getRightText()Ljava/util/List;"))
    protected List<String> getRightText(DebugHud instance) {
        long l = Runtime.getRuntime().maxMemory();
        long m = Runtime.getRuntime().totalMemory();
        long n = Runtime.getRuntime().freeMemory();
        long o = m - n;
        List<String> list = Lists.<String>newArrayList(
                String.format(Locale.ROOT, "Java: %s", System.getProperty("java.version")),
                String.format(Locale.ROOT, "Mem: %2d%% %03d/%03dMB", o * 100L / l, toMiB(o), toMiB(l)),
                String.format(Locale.ROOT, "Allocation rate: %03dMB/s", toMiB(allocationRateCalculator.get(o))),
                String.format(Locale.ROOT, "Allocated: %2d%% %03dMB", m * 100L / l, toMiB(m)),
                "",
                String.format(Locale.ROOT, "CPU: %s", GlDebugInfo.getCpuInfo()),
                "",
                String.format(
                        Locale.ROOT,
                        "Display: %dx%d (%s)",
                        MinecraftClient.getInstance().getWindow().getFramebufferWidth(),
                        MinecraftClient.getInstance().getWindow().getFramebufferHeight(),
                        GlDebugInfo.getVendor()
                ),
                GlDebugInfo.getRenderer(),
                GlDebugInfo.getVersion()
        );
        return list;
    }
}
