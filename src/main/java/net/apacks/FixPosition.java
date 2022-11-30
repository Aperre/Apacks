package net.apacks;

import net.minecraft.client.MinecraftClient;

public class FixPosition {
    public static double FixPositionX() {
        return (EasyRounding.round(MinecraftClient.getInstance().player.getPos().getX()*100))/100;
    }
    public static double FixPositionZ() {
        return (EasyRounding.round(MinecraftClient.getInstance().player.getPos().getX()*100))/100;
    }

    public static void Fix(){
        PacketHelper.sendExactPosition(FixPositionX(),MinecraftClient.getInstance().player.getPos().getY(),FixPositionZ());
    }
}
