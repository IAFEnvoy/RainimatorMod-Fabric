package com.rainimator.rainimatormod.registry;

import com.rainimator.rainimatormod.util.ModConstants;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class ModKeybindings {
    public static final KeyBinding ABILITY = new KeyBinding("keybinding.rainimator.ability", GLFW.GLFW_KEY_C, "keybinding.rainimator.category");

    public static void register() {
        KeyBindingHelper.registerKeyBinding(ABILITY);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (ABILITY.wasPressed())
                ClientPlayNetworking.send(ModConstants.ABILITY_PACKET_ID, PacketByteBufs.create());
        });
    }
}
