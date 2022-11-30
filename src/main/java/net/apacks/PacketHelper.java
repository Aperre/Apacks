package net.apacks;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class PacketHelper {
    public static void sendExactPosition(double x,double y,double z){
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayNetworkHandler networkHandler = client.getNetworkHandler();
        networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(x,y,z, client.player.isOnGround()));
    }
    public static void sendRelativePosition(double rx, double ry, double rz){
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayNetworkHandler networkHandler = client.getNetworkHandler();
        double x = client.player.getPos().getX()+rx;
        double y = client.player.getPos().getY()+ry;
        double z = client.player.getPos().getZ()+rz;

        networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(x,y,z, client.player.isOnGround()));
    }
}
