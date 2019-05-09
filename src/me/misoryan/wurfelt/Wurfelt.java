package me.misoryan.wurfelt;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import me.misoryan.wurfelt.commands.*;
import me.misoryan.wurfelt.libs.Lib;
import me.misoryan.wurfelt.listener.ChatListener;
import me.misoryan.wurfelt.listener.FreezeListener;
import me.misoryan.wurfelt.listener.VanishListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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

    public Wurfelt getInstance() {
        return ins;
    }

    @Override
    public void onEnable() {
        ins = this;
        if (getConfig().getBoolean("Vanish.enable")) {
            Bukkit.getPluginCommand("v").setExecutor(new VanishCommand());
            Bukkit.getPluginManager().registerEvents(new VanishListener(), this);
        }
        if (getConfig().getBoolean("Chat.enable")) {
            Bukkit.getPluginManager().registerEvents(new ChatListener(),this);
        }
        if (getConfig().getBoolean("Teleport.enable")) {
            Bukkit.getPluginCommand("tpa").setExecutor(new TpaCommand());
            Bukkit.getPluginCommand("tpaccept").setExecutor(new TpacceptCommand());
            Bukkit.getPluginCommand("tpdeny").setExecutor(new TpDenyCommand());
        }
        if (getConfig().getBoolean("Broadcast.enable")) {
            Bukkit.getPluginCommand("bc").setExecutor(new BroadcastCommand());
        }
        if (getConfig().getBoolean("freeze.enable")) {
            Bukkit.getPluginCommand("freeze").setExecutor(new FreezeCommand());
            Bukkit.getPluginManager().registerEvents(new FreezeListener(), this);
        }
        Bukkit.getPluginCommand("wurfelt").setExecutor(new WurfeltCommand());
        /*
        VanishListener所需要的check
         */
        if (getConfig().getBoolean("Vanish.enable")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (VanishCommand.VanishData.get(p) != null) {
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                player.hidePlayer(p);
                            }
                            ActionBarAPI.sendActionBar(p, Lib.getCurrentText("&f你目前处于&c隐身&f状态中"));
                        } else {
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                p.showPlayer(player);
                                player.showPlayer(p);
                            }
                        }
                    }
                }
            }.runTaskTimer(this, 0L, 20L);
        }
    }
}
