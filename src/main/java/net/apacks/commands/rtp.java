package net.apacks.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.apacks.PacketHelper;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.argument.PosArgument;
import net.minecraft.command.argument.Vec3ArgumentType;
import net.minecraft.server.command.ServerCommandSource;

public class rtp {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher,boolean dedicated) {

        dispatcher.register(ClientCommandManager.literal("teleport").then(
                        ClientCommandManager.argument("location", Vec3ArgumentType.vec3()).executes(
                                context -> PacketHelper.sendRelativePosition(
                                        context.getArgument("location", PosArgument.class).toAbsolutePos((ServerCommandSource) context.getSource()).x,
                                        context.getArgument("location", PosArgument.class).toAbsolutePos((ServerCommandSource) context.getSource()).y,
                                        context.getArgument("location", PosArgument.class).toAbsolutePos((ServerCommandSource) context.getSource()).z))));

    }
}
