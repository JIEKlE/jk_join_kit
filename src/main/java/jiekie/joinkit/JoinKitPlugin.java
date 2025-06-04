package jiekie.joinkit;

import jiekie.joinkit.command.JoinKitCommand;
import jiekie.joinkit.event.PlayerEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class JoinKitPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // config
        saveDefaultConfig();
        reloadConfig();

        // event
        getServer().getPluginManager().registerEvents(new PlayerEvent(this), this);

        // command
        getCommand("스타터팩").setExecutor(new JoinKitCommand());

        getLogger().info("스타터팩 지급 플러그인 by Jiekie");
        getLogger().info("Copyright © 2025 Jiekie. All rights reserved.");
    }

    @Override
    public void onDisable() {
    }
}
