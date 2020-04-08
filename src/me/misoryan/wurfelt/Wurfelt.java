package me.misoryan.wurfelt;

import me.misoryan.wurfelt.commands.*;
import me.misoryan.wurfelt.commands.ReportCommand.ReportCommand;
import me.misoryan.wurfelt.commands.ReportCommand.ReportsCommand;
import me.misoryan.wurfelt.libs.Lib;
import me.misoryan.wurfelt.listener.FreezeListener;
import me.misoryan.wurfelt.utils.ReportUtils;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class Wurfelt extends JavaPlugin {
    public static Map<String, Boolean> SoftDepend;
    public static Wurfelt ins;

    public Wurfelt() {
       SoftDepend = new HashMap<String, Boolean>();
    }

    @Override
    public void onLoad() {
        this.saveDefaultConfig();
    }

    public String sendErrorMessage(String type) {
        if (type.equalsIgnoreCase("pex")) {
            return Lib.getCurrentText(Wurfelt.ins.getConfig().getString("Commands.permission-denied"));
        }
        if (type.equalsIgnoreCase("usage")) {
            return Lib.getCurrentText(Wurfelt.ins.getConfig().getString("Commands.current-usage"));
        }
        if (type.equalsIgnoreCase("offline")) {
            return Lib.getCurrentText(Wurfelt.ins.getConfig().getString("Commands.player-offline"));
        }
        return null;
    }

    public void register() {
        if (getConfig().getBoolean("freeze.enable")) {
            Bukkit.getPluginCommand("freeze").setExecutor(new FreezeCommand());
            Bukkit.getPluginManager().registerEvents(new FreezeListener(), this);
            getLogger().info(Lib.getCurrentText("Now Loading Module: &3Freeze"));
        }
    }

    @Override
    public void onEnable() {
        ins = this;
        reloadConfig();
        register();
        Bukkit.getPluginCommand("wurfelt").setExecutor(new WurfeltCommand());
        Bukkit.getPluginCommand("report").setExecutor(new ReportCommand());
        Bukkit.getPluginCommand("reports").setExecutor(new ReportsCommand());
    }
}
