package net.pm.specifics.mixin;

import com.google.common.collect.Lists;
import net.minecraft.client.gui.hud.DebugHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(DebugHud.class)
public class F3Mixin {

    @Redirect(method = "drawLeftText(Lnet/minecraft/client/gui/DrawContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/DebugHud;getLeftText()Ljava/util/List;"))
    protected List<String> getLeftText(DebugHud instance) {
        return Lists.<String>newArrayList(
                "No <3"
        );
    }

    @Redirect(method = "drawRightText(Lnet/minecraft/client/gui/DrawContext;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/DebugHud;getRightText()Ljava/util/List;"))
    protected List<String> getRightText(DebugHud instance) {
        return Lists.<String>newArrayList(
        );
    }
}
