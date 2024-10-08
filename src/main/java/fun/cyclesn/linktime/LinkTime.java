package fun.cyclesn.linktime;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Objects;

public final class LinkTime extends JavaPlugin {
    public static LinkTime main;

    @Override
    public void onLoad() {
        main = this;
        saveConfig();
    }

    @Override
    public void onEnable() {
        Objects.requireNonNull(Bukkit.getPluginCommand("ltest")).setExecutor(new LinkCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("ltest")).setTabCompleter(new LinkCommand());
        Bukkit.getPluginManager().registerEvents(new LinkListener(),this);
        System.out.println("LinkTime loaded");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
