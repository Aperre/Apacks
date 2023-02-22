package net.apacks.mixin;

import net.apacks.EasyRounding;
import net.apacks.Main;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.VehicleMoveC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(PlayerMoveC2SPacket.class)
class modifyPlayerMovePacketMixin {

    @ModifyVariable(method= "<init>(DDDFFZZZ)V",at = @At("HEAD"),ordinal = 0, argsOnly = true)
    private static double roundx(double x) {
        if (!Main.loPosBypass)
        return (EasyRounding.round(x*100))/100;
        return x;
    }

    @ModifyVariable(method= "<init>(DDDFFZZZ)V",at = @At("HEAD"),ordinal = 2, argsOnly = true)
    private static double roundz(double z) {
        if (!Main.loPosBypass)
        return (EasyRounding.round(z*100))/100;
        return z;
    }

    @ModifyVariable(method= "<init>(DDDFFZZZ)V",at = @At("HEAD"),ordinal = 0, argsOnly = true)
    private static boolean noFallToggling(boolean onGround) {
        if (Main.noFallIsEnabled)
            return true;
        return onGround;
    }
}


@Mixin(VehicleMoveC2SPacket.class)
abstract class modifyVehicleMovePacketMixin {
    @Mutable
    @Shadow private double x;
    @Mutable
    @Shadow
    private double y;
    @Mutable
    @Shadow
    private double z;

    @Inject(method = "<init>(Lnet/minecraft/entity/Entity;)V", at = @At("RETURN"))
    private void onConstruct(Entity entity, CallbackInfo ci) {
        if (!Main.loPosBypass) {
            // Multiply the entity's position and rotation by 100 and round to the nearest integer
            this.x = EasyRounding.round(entity.getX() * 100.0) / 100.0;
            this.y = EasyRounding.round(entity.getY() * 100.0) / 100.0;
            this.z = EasyRounding.round(entity.getZ() * 100.0) / 100.0;
        }
    }
}


