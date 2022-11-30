package net.apacks.mixin;

import net.apacks.Main;
import net.apacks.PacketHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class TickMixin {
    MinecraftClient client=MinecraftClient.getInstance();
    private double oldY = 0;
    private int floatingTickCount = 0;
    @Inject(at=@At("HEAD"),method = "Lnet/minecraft/client/render/WorldRenderer;tick()V")
    public void tick(CallbackInfo ci) {
        if (client.player==null) return;
        //Flight
        if (client.player.getAbilities().flying) {
            if (client.player.getPos().getY() >= oldY - 0.0433D) {
                floatingTickCount += 1;
            }
            oldY = client.player.getPos().getY();
            if ((floatingTickCount > 20) && client.player.world.getBlockState(new BlockPos(client.player.getPos().subtract(0, 0.05D, 0))).isAir()) {
                PacketHelper.sendRelativePosition(0, -0.05D, 0);
                Main.LOGGER.info("(Flight) Bypass Flight detection");
            }
        }


    }
}
