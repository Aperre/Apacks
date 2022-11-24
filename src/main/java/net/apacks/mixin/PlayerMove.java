package net.apacks.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;


@Mixin(PlayerMoveC2SPacket.Full.class)
public class PlayerMove {
    private MinecraftClient mc;
    @ModifyArgs(method="Full",at=@At(value = "INVOKE"))
    private void mixin(@NotNull Args args) {
        args.set(0, Math.floor(mc.player.getX()*10)/10);
        args.set(2, Math.floor(mc.player.getZ()*10)/10);
    }



    // PositionAndOnGround

}