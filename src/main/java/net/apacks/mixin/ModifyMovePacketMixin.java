package net.apacks.mixin;

import net.apacks.EasyRounding;
import net.apacks.Main;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.network.packet.c2s.play.VehicleMoveC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


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

// Replace VEHICLE_MOVE_PACKET with the actual packet class for the version of Minecraft Fabric you are using
@Mixin(VehicleMoveC2SPacket.class)
abstract class modifyVehicleMovePacketMixin {
    @Shadow private double x;
    @Shadow private double y;
    @Shadow private double z;

    @Inject(method = "<init>(Lnet/minecraft/entity/Entity;)V", at = @At("RETURN"))
    private void onConstruct(Entity entity, CallbackInfo ci) {
        // Multiply the entity's position and rotation by 100 and round to the nearest integer
        this.x = EasyRounding.round(entity.getX() * 100.0) / 100.0;
        this.y = EasyRounding.round(entity.getY() * 100.0) / 100.0;
        this.z = EasyRounding.round(entity.getZ() * 100.0) / 100.0;
    }
}


