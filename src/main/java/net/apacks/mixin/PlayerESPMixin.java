package net.apacks.mixin;

import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/*
@Mixin(LivingEntityRenderer.class)
public class ESPMixin {
    MinecraftClient client = MinecraftClient.getInstance();
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void makePlayerInvisible(
        LivingEntity entity,
        float yaw,
        float tickDelta,
        MatrixStack matrices,
        float alpha,
        CallbackInfo ci
        ) {
            // Check if the entity being rendered is the player
        if (entity == client.player) {
                // Modify the player's rendering properties to make them invisible
        matrices.translate(0, -MathHelper.sin(tickDelta * 0.2f) * 0.5f, 0);
        matrices.scale(1f, 0.01f, 1f);
        ci.cancel();
            }
        }
}
*/
@Mixin(EntityRenderer.class)
public class PlayerESPMixin<T extends Entity>{
    @Inject(method = "shouldRender", at = @At("RETURN"), cancellable = true)
    private void MakeVisible(T entity, Frustum frustum, double x, double y, double z, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof PlayerEntity) {
            cir.setReturnValue(true);
        }
    }
}

