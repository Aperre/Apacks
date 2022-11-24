package net.apacks.mixin;

import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;


@Mixin(PlayerMoveC2SPacket.PositionAndOnGround.class)
class PosAndGround {
    @ModifyVariable(method="Lnet/minecraft/network/packet/c2s/play/PlayerMoveC2SPacket$PositionAndOnGround;<init>(DDDZ)V",at = @At("HEAD"),ordinal = 1)
    private double injectedx(double x) {
        return Math.floor(x*100)/100;
    }
    @ModifyVariable(method="Lnet/minecraft/network/packet/c2s/play/PlayerMoveC2SPacket$PositionAndOnGround;<init>(DDDZ)V",at = @At("HEAD"),ordinal = 1)
    private double injectedz(double z) {
        return Math.floor(z*100)/100;
    }
}
@Mixin(PlayerMoveC2SPacket.Full.class)
class PlayerMove {
    @ModifyVariable(method="Lnet/minecraft/network/packet/c2s/play/PlayerMoveC2SPacket$Full;<init>(DDDFFZ)V",at = @At("HEAD"),ordinal = 1)
    private double injectedx(double x) {
        return Math.floor(x*100)/100;
    }
    @ModifyVariable(method="Lnet/minecraft/network/packet/c2s/play/PlayerMoveC2SPacket$Full;<init>(DDDFFZ)V",at = @At("HEAD"),ordinal = 1)
    private double injectedz(double z) {
        return Math.floor(z*100)/100;
    }
}