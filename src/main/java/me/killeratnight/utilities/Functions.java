package me.killeratnight.utilities;

import java.util.Set;
import java.util.UUID;
import static me.killeratnight.ajura.Ajura.plugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;

/**
 *
 * @author Nicolas
 */
public class Functions {
  public boolean checkBadWurds(){
    return plugin.getConfig().isConfigurationSection("BadWurds");
  }
  public Set<String> getBadWurds(){
    return plugin.getConfig().getConfigurationSection("BadWurds").getKeys(false);
  }
  public void addStrike(UUID playerUUID){
    String pUUID = playerUUID.toString();
    int strikes = plugin.PlayerStrikes.getPS().getInt(pUUID+".Strikes");
    int maxstrikes = plugin.getConfig().getInt("MaxStrikes");
    if(strikes <= maxstrikes){
      Bukkit.getPlayer(playerUUID).sendMessage(plugin.Prefix+ChatColor.DARK_RED+"You have been given a strike for foul language.");
      Bukkit.getPlayer(playerUUID).sendMessage(plugin.Prefix+ChatColor.DARK_RED+"You now hold "+strikes+1+" strike(s) on your record.");
      plugin.PlayerStrikes.getPS().set(pUUID+".Strikes", strikes+1);
    }
    else if(strikes > maxstrikes){
      Bukkit.dispatchCommand(plugin.console, plugin.getConfig().getString("StrikePunishment"));  //Run Punishment Cmd
      plugin.PlayerStrikes.getPS().set(pUUID+".Strikes", 0);  //Reset Strikes
    }
  }
  public boolean checkFlag(String BaseWurd, String flag){
    return plugin.getConfig().isConfigurationSection("BadWurds."+BaseWurd+"."+flag);
  }
  public ConfigurationSection getFlag(String BaseWurd){
    return plugin.getConfig().getConfigurationSection("BadWurds."+BaseWurd);
  }
}
