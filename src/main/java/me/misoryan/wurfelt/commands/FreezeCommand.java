package me.misoryan.wurfelt.commands;

import me.misoryan.wurfelt.Wurfelt;
import me.misoryan.wurfelt.libs.Lib;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FreezeCommand implements CommandExecutor {

    public static Map<String, String> freezeing;

    public FreezeCommand() {
        freezeing = new HashMap<>();
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (command.getName().equalsIgnoreCase("freeze")) {
            if (!sender.hasPermission("Wurfelt.SS")) {
                sender.sendMessage(Lib.getCurrentText(Wurfelt.ins.getConfig().getString("Commands.permission-denied")));
                return true;
            }
            if (args.length != 1) {
                sender.sendMessage(Lib.getCurrentText(Wurfelt.ins.getConfig().getString("Commands.current-usage") + "/freeze <ID>"));
                return true;
            }
            if (Bukkit.getPlayerExact(args[0]) == null) {
                sender.sendMessage(Lib.getCurrentText(Wurfelt.ins.getConfig().getString("Commands.player-offline")));
                return true;
            }
            if (Objects.requireNonNull(Bukkit.getPlayerExact(args[0])).hasPermission("Wurfelt.SS") || Objects.requireNonNull(Bukkit.getPlayerExact(args[0])).hasPermission("Wurfelt.Admin")) {
                sender.sendMessage(Lib.getCurrentText(Wurfelt.ins.getConfig().getString("Commands.permission-denied")));
                return true;
            } else if (freezeing.get(args[0]) == null) {
                freezeing.put(Objects.requireNonNull(Bukkit.getPlayer(args[0])).getName(), sender.getName());
                final Inventory inventory = Bukkit.createInventory(null, 9, Lib.getCurrentText(Wurfelt.ins.getConfig().getString("freeze.title")));
                final ItemStack itemStack = new ItemStack(Material.STONE);
                final ItemMeta itemMeta = itemStack.getItemMeta();
                int i = 0;
                for (final String l : Wurfelt.ins.getConfig().getStringList("freeze.lore")) {
                    ++i;
                }
                final ArrayList<String> lore = new ArrayList<>();
                i = 0;
                for (String j : Wurfelt.ins.getConfig().getStringList("freeze.lore")) {
                    j = Lib.getCurrentText(j);
                    if (freezeing.get(args[0]) == null) {
                        j = j.replace("[OPERATOR]", "Null");
                    } else {
                        j = j.replace("[OPERATOR]", freezeing.get(args[0]));
                    }
                    lore.add(j);
                    ++i;
                }
                assert itemMeta != null;
                itemMeta.setLore(lore);
                itemMeta.setDisplayName(Lib.getCurrentText(Wurfelt.ins.getConfig().getString("freeze.title")));
                itemStack.setItemMeta(itemMeta);
                inventory.setItem(4, itemStack);
                Objects.requireNonNull(Bukkit.getPlayerExact(args[0])).openInventory(inventory);
                Objects.requireNonNull(Bukkit.getPlayerExact(args[0])).sendMessage(Lib.getCurrentText(Objects.requireNonNull(Wurfelt.ins.getConfig().getString("freeze.freeze-message")).replace("[PLAYER]", args[0])));
                sender.sendMessage(Lib.getCurrentText(Objects.requireNonNull(Wurfelt.ins.getConfig().getString("freeze.freeze-other-message")).replace("[PLAYER]", args[0])));
            } else {
                freezeing.remove(Objects.requireNonNull(Bukkit.getPlayer(args[0])).getName());
                Objects.requireNonNull(Bukkit.getPlayerExact(args[0])).closeInventory();
                Objects.requireNonNull(Bukkit.getPlayerExact(args[0])).sendMessage(Lib.getCurrentText(Objects.requireNonNull(Wurfelt.ins.getConfig().getString("freeze.unfreeze-message")).replace("[PLAYER]", args[0])));
                sender.sendMessage(Lib.getCurrentText(Objects.requireNonNull(Wurfelt.ins.getConfig().getString("freeze.unfreeze-other-message")).replace("[PLAYER]", args[0])));
            }
        }
        return true;
    }
}
