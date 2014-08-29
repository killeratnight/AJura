package me.killeratnight.utilities;

import java.util.Set;
import static me.killeratnight.ajura.Ajura.plugin;

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
  public String getReplacement(String wurd){
    return plugin.getConfig().getString("BadWurds."+wurd+".Replacement");
  }
  public void addStrike(String pUUID){
    int strikes = plugin.PlayerStrikes.getPS().getInt(pUUID+".Strikes");
    plugin.PlayerStrikes.getPS().set(pUUID+".Strikes", strikes+1);
  }
  public boolean checkFlag(String BaseWurd, String flag){
    return plugin.getConfig().isConfigurationSection("BadWurds."+BaseWurd+"."+flag);
  }
}
