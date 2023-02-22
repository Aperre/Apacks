package net.apacks;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import jdk.jshell.JShell;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.argument.PosArgument;
import net.minecraft.command.argument.Vec3ArgumentType;
import net.minecraft.util.math.Vec3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;


public class Main implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("apacks");
    public static boolean flyIsEnabled = false;
    public static boolean noFallIsEnabled = false;
    public static boolean loPosBypass = true;
    public static MinecraftClient client = MinecraftClient.getInstance();

    @Override
    public void onInitialize() {
        LOGGER.info("Initialized Apacks");
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            // Register the "teleport" command
            dispatcher.register(ClientCommandManager.literal("teleport").then(
                    ClientCommandManager.argument("location", Vec3ArgumentType.vec3()).executes(
                            context -> {

                                // Get the position argument
                                Vec3d location = context.getArgument("location", PosArgument.class).toAbsolutePos(context.getSource().getEntity().getCommandSource());


                                // Send the relative position packet
                                assert client.player != null;
                                client.player.setPos(location.x, location.y, location.z);
                                return 1;
                            }
                    )
            ));
            dispatcher.register(ClientCommandManager.literal("speed")
                            .then(ClientCommandManager.argument("speed", IntegerArgumentType.integer())
                                    .executes(context -> {

                                        // Get the radius argument
                                        int speed = IntegerArgumentType.getInteger(context, "speed");

                                        // Hit all entities within the given radius
                                        assert client.player != null;
                                        client.player.getAbilities().setWalkSpeed(speed);
                                        return 1;
                                    })
                            )
                    );
            dispatcher.register(ClientCommandManager.literal("speed")
                    .then(ClientCommandManager.argument("speed", IntegerArgumentType.integer())
                            .executes(context -> {

                                // Get the radius argument
                                int speed = IntegerArgumentType.getInteger(context, "speed");

                                // Hit all entities within the given radius
                                assert client.player != null;
                                client.player.getAbilities().setWalkSpeed(speed);
                                return 1;
                            })
                    )
            );
            dispatcher.register(ClientCommandManager.literal("auth")
                    .then(ClientCommandManager.argument("token", StringArgumentType.string())
                            .executes(context -> {

                                String token = StringArgumentType.getString(context, "token");


                                client.inGameHud.getChatHud().addToMessageHistory(Objects.requireNonNull(client.getSession().getSessionId()));
                                return 1;
                            })
                    )
            );
            JShell js = JShell.create();
            dispatcher.register(ClientCommandManager.literal("eval")
                    .then(ClientCommandManager.argument("code", StringArgumentType.string())
                            .executes(context -> {

                                String code = StringArgumentType.getString(context, "code");

                                js.eval(code);

                                return 1;
                            })
                    )
            );


                }
        );

    }
}

