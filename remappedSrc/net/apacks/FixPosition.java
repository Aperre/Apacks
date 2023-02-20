package net.apacks;

import net.minecraft.client.MinecraftClient;

public class FixPosition {
    static MinecraftClient client = MinecraftClient.getInstance();
    public static double FixPositionX() {
        
        return (EasyRounding.round(client.player.getPos().getX()*100))/100;
    }
    public static double FixPositionZ() {
        return (EasyRounding.round(client.player.getPos().getX()*100))/100;
    }

    public static void Fix(){
        PacketHelper.sendExactPosition(FixPositionX(),client.player.getPos().getY(),FixPositionZ());
    }
}
