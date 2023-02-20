package net.apacks;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

public class PacketHelper {
    static MinecraftClient client = MinecraftClient.getInstance();
    static ClientPlayNetworkHandler networkHandler = client.getNetworkHandler();
    public static void sendExactPosition(double x,double y,double z){
        assert client.player != null;
        networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(x,y,z, client.player.isOnGround()));
    }
    public static int sendRelativePosition(double rx, double ry, double rz){
        assert client.player != null;
        double x = client.player.getPos().getX()+rx;
        double y = client.player.getPos().getY()+ry;
        double z = client.player.getPos().getZ()+rz;

        networkHandler.sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(x,y,z, client.player.isOnGround()));
        return 0;
    }
}
