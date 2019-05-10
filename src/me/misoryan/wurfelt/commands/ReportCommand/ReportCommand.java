package me.misoryan.wurfelt.commands.ReportCommand;

import me.misoryan.wurfelt.Wurfelt;
import me.misoryan.wurfelt.libs.Lib;
import me.misoryan.wurfelt.utils.ReportUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ReportCommand implements CommandExecutor {

    public static Map<Long, String> ReportData;

    public ReportCommand() {
        ReportData = new HashMap<Long, String>();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("wdr") || command.getName().equalsIgnoreCase("report")) {
            if (strings[0] == null || strings[1] == null) {
                commandSender.sendMessage(Lib.getCurrentText(Wurfelt.ins.sendErrorMessage("usage") + "/report <ID> <Reason>"));
                return true;
            }
        }
        return true;
    }
}
