package org.stopmodreposts.stopmodreposts;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.CustomValue;
import net.minecraft.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class SMR implements ClientModInitializer {
    public static final Gson GSON = new GsonBuilder().create();
    public static boolean isModMenuLoaded = false;
    public static Settings settings;
    private static Path settingsFile;

    public SMR() {
        AtomicInteger voteOnToast = new AtomicInteger();
        AtomicInteger voteOnButton = new AtomicInteger();
        FabricLoader.getInstance().getAllMods().stream()
            .map(ModContainer::getMetadata)
            .forEach(modMetadata -> {
                if (modMetadata.containsCustomValue("stopmodreposts:showToast")
                    && modMetadata.getCustomValue("stopmodreposts:showToast").getType() == CustomValue.CvType.BOOLEAN) {
                    voteOnToast.addAndGet(modMetadata.getCustomValue("stopmodreposts:showToast").getAsBoolean() ? 1 : -1);
                }
                if (modMetadata.containsCustomValue("stopmodreposts:showButton")
                    && modMetadata.getCustomValue("stopmodreposts:showButton").getType() == CustomValue.CvType.BOOLEAN) {
                    voteOnButton.addAndGet(modMetadata.getCustomValue("stopmodreposts:showButton").getAsBoolean() ? 1 : -1);
                }
            });
        settings = new Settings();
        settings.showToast = voteOnToast.get() >= 0;
        settings.showButton = voteOnButton.get() >= 0;
    }

    public static void saveSettings(Settings newSettings) {
        settings = newSettings;
        try {
            String settingsString = GSON.toJson(newSettings, Settings.class);
            Files.write(settingsFile, Arrays.asList(settingsString.split("\\r?\\n")));
        } catch (IOException e) {
            System.out.println("Unable to save StopModReposts config. Please, fix this issue!");
            e.printStackTrace();
        }

    }

    @Override
    public void onInitializeClient() {
        isModMenuLoaded = FabricLoader.getInstance().isModLoaded("modmenu");
        settingsFile = FabricLoader.getInstance().getConfigDir().resolve("./stopmodreposts.json");
        try {
            if (!settingsFile.toFile().exists()) {
                saveSettings(settings);
            }
            String settingsString = String.join("", Files.readAllLines(settingsFile));
            settings = GSON.fromJson(settingsString, Settings.class);
        } catch (IOException e) {
            System.out.println("Unable to load StopModReposts config. Please, fix this issue!");
            e.printStackTrace();
        }
    }

    public static final class Settings {
        public boolean showToast = true;
        public boolean showButton = true;
    }
}
