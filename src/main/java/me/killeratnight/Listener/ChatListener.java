package me.killeratnight.Listener;

import me.killeratnight.ajura.Ajura;
import org.bukkit.configuration.file.FileConfiguration;
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
    String pUUID;
    String pName;
    String pMsg;
    FileConfiguration C;
    
    if(plugin.enabled){
      pUUID = e.getPlayer().getUniqueId().toString();
      pName = e.getPlayer().getName();
      pMsg = e.getMessage();
      C = plugin.getConfig();
      
      if(!e.getPlayer().hasPermission("ajura.evade")){
        if(plugin.func.checkBadWurds()){
          for(String wurd : plugin.func.getBadWurds()){
            if(pMsg.toLowerCase().contains(wurd.toLowerCase())){
              if(plugin.func.checkFlag(wurd, "Replace")){
                
              }
              if(plugin.func.checkFlag(wurd, "Mute") || plugin.getConfig().getBoolean("BadWurds."+wurd+".Mute")){
                
              }
            }
          }
        }
      }
    }
  }
}
