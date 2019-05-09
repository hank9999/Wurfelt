package me.misoryan.wurfelt.commands;

import me.misoryan.wurfelt.libs.Lib;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

@Deprecated
public class HashCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("hash")) {
            String text = Lib.getCurrentArgsFormat(strings,0);
            commandSender.sendMessage("Text: " + text);
            commandSender.sendMessage("Hashed: " + Lib.getHashed(text));
            return true;
        }
        return true;
    }
}
