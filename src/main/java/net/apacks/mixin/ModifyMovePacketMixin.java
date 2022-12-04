package net.apacks.mixin;

import net.apacks.EasyRounding;
import net.apacks.Main;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(PlayerMoveC2SPacket.class)
class modifyPlayerMovePacketMixin {

    @ModifyVariable(method= "<init>(DDDFFZZZ)V",at = @At("HEAD"),ordinal = 0, argsOnly = true)
    private static double roundx(double x) {
        return (EasyRounding.round(x*100))/100;
    }

    @ModifyVariable(method= "<init>(DDDFFZZZ)V",at = @At("HEAD"),ordinal = 2, argsOnly = true)
    private static double roundz(double z) {
        return (EasyRounding.round(z*100))/100;
    }

    @ModifyVariable(method= "<init>(DDDFFZZZ)V",at = @At("HEAD"),ordinal = 0, argsOnly = true)
    private static boolean noFallToggling(boolean onGround) {
        if (Main.noFallIsEnabled)
            return true;
        else
            return onGround;
    }
}

@Mixin(Entity.class)
class modifyVehicleMovePacketMixin {

    @Inject(method= "getX()D",at = @At("HEAD"))
    private static void roundx(CallbackInfoReturnable<Double> cir) {
    }

    @Inject(method= "getX()D",at = @At("HEAD"))
    private static void roundz(CallbackInfoReturnable<Double> cir) {
    }
}



