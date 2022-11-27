package net.apacks.mixin;

import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.math.BigDecimal;
import java.math.MathContext;


@Mixin(PlayerMoveC2SPacket.class)
class PlayerMove {

    @ModifyVariable(method="Lnet/minecraft/network/packet/c2s/play/PlayerMoveC2SPacket;<init>(DDDFFZZZ)V",at = @At("HEAD"),ordinal = 0)
    private static double injectedx(double x) {
        BigDecimal newx = BigDecimal.valueOf(x);
        double newdx = newx.round(new MathContext(3)).doubleValue();
        return newdx;
    }

    @ModifyVariable(method="Lnet/minecraft/network/packet/c2s/play/PlayerMoveC2SPacket;<init>(DDDFFZZZ)V",at = @At("HEAD"),ordinal = 2)
    private static double injectedz(double z) {
        BigDecimal newz = BigDecimal.valueOf(z);
        double newdz = newz.round(new MathContext(3)).doubleValue();
        return newdz;
    }
}

