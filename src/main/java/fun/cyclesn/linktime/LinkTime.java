package fun.cyclesn.linktime;

import org.bukkit.plugin.java.JavaPlugin;

public final class LinkTime extends JavaPlugin {
    public static LinkTime main;

    @Override
    public void onLoad() {
        main = this;
        saveConfig();
        System.out.println("LinkTime loaded");
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
