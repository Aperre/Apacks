package net.apacks.mixin;

import net.apacks.EasyRounding;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;


@Mixin(PlayerMoveC2SPacket.class)
class PlayerMove {

    @ModifyVariable(method="Lnet/minecraft/network/packet/c2s/play/PlayerMoveC2SPacket;<init>(DDDFFZZZ)V",at = @At("HEAD"),ordinal = 0)
    private static double injectedx(double x) {
        return (EasyRounding.round(x*100))/100;
    }

    @ModifyVariable(method="Lnet/minecraft/network/packet/c2s/play/PlayerMoveC2SPacket;<init>(DDDFFZZZ)V",at = @At("HEAD"),ordinal = 2)
    private static double injectedz(double z) {
        return (EasyRounding.round(z*100))/100;
    }
    /*
    @ModifyVariable(method="Lnet/minecraft/network/packet/c2s/play/PlayerMoveC2SPacket;<init>(DDDFFZZZ)V",at = @At("HEAD"),ordinal = 5)
    private static boolean injectedog(boolean onGround) {
        return onGround;
    }
    */
}





