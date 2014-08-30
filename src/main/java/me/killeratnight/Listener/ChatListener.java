package me.killeratnight.Listener;

import me.killeratnight.ajura.Ajura;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 *
 * @author Nicolas
 */
public class ChatListener implements Listener{
  Ajura plugin;
  public ChatListener(Ajura plugin){
    this.plugin = plugin;
  }
  @EventHandler
  public void onPlayerChat(AsyncPlayerChatEvent e){
    String pName;
    String pMsg;
    
    if(plugin.enabled){
      pName = e.getPlayer().getName();
      pMsg = e.getMessage();
      
      if(!e.getPlayer().hasPermission("ajura.evade")){
        if(plugin.func.checkBadWurds()){
          for(String wurd : plugin.func.getBadWurds()){
            if(pMsg.toLowerCase().contains(wurd.toLowerCase())){
              if(plugin.func.checkFlag(wurd, "ReplaceWith")){
                pMsg = pMsg.replaceAll(wurd, plugin.func.getFlag(wurd).getString("ReplaceWith"));
                e.setMessage(pMsg);
              }
              if(plugin.func.checkFlag(wurd, "CancelMsg")){
                e.setCancelled(true);
              }
              if(plugin.func.checkFlag(wurd, "RunCmd")){
                String cmd = plugin.func.getFlag(wurd).getString("RunCmd");
                cmd = cmd.replaceAll("/", null);
                cmd = cmd.replaceAll("@player",pName);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
              }
            }
          }
        }
      }
    }
  }
}
