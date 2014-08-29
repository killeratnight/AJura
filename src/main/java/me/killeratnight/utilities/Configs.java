package me.killeratnight.utilities;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import me.killeratnight.ajura.Ajura;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 *
 * @author Nicolas
 */
public class Configs {
  public Ajura plugin;
  public Configs(Ajura plugin){
    this.plugin = plugin;
  }
  
  private FileConfiguration PlayerStrikes = null;
  private File PlayerStrikesFile = null;
  
  public void reloadPS(){
    if(PlayerStrikesFile == null){
      PlayerStrikesFile = new File(plugin.getDataFolder(), "PlayerStrikes.yml");
    }
    PlayerStrikes = YamlConfiguration.loadConfiguration(PlayerStrikesFile);
    InputStream defConfigStream = plugin.getResource("PlayerStrikes.yml");
    if(defConfigStream != null){
      YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
      PlayerStrikes.setDefaults(defConfig);
    }
  }
  public FileConfiguration getPS(){
    if(PlayerStrikes == null){
      reloadPS();
    }
    return PlayerStrikes;
  }
  public void savePS(){
    if(PlayerStrikes == null || PlayerStrikesFile == null){
      return;
    }
    try{
      getPS().save(PlayerStrikesFile);
    }
    catch(IOException ex){
      plugin.getLogger().log(Level.SEVERE, "Could not save config to "+PlayerStrikesFile, ex);
    }
  }
  public void saveDefaultPS(){
    if(PlayerStrikesFile == null){
      PlayerStrikesFile = new File(plugin.getDataFolder(), "PlayerStrikes.yml");
    }
    if(!PlayerStrikesFile.exists()){
      plugin.saveResource("PlayerStrikes.yml", false);
    }
  }
}
