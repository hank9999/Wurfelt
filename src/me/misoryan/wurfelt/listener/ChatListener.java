package me.misoryan.wurfelt.listener;

import me.misoryan.wurfelt.Wurfelt;
import me.misoryan.wurfelt.libs.Lib;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        if (e.getPlayer().isOp()) {
            e.setFormat(Lib.getCurrentText(Wurfelt.ins.getConfig().getString("Chat.op").replace("%player%",e.getPlayer().getName()).replace("%message%",e.getMessage())));
        }
        else {
            e.setFormat(Lib.getCurrentText(Wurfelt.ins.getConfig().getString("Chat.not-op").replace("%player%",e.getPlayer().getName()).replace("%message%",e.getMessage())));
        }
    }
}
