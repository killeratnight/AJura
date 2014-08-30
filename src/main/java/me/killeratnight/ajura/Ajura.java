package me.killeratnight.ajura;

import java.util.Set;
import me.killeratnight.Listener.ChatListener;
import me.killeratnight.utilities.Configs;
import me.killeratnight.utilities.Functions;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author Nicolas
 */
public class Ajura extends JavaPlugin{
  public static Ajura plugin;
  public ConsoleCommandSender console;
  public boolean enabled;
  public Functions func;
  public PluginManager pm;
  public PluginDescriptionFile pdfFile;
  public String Prefix;
  public Set<String> BadWurds;
  public Configs PlayerStrikes;
  
  @Override
  public void onDisable(){
    plugin.saveConfig();
    PlayerStrikes.savePS();
  }
  @Override
  public void onEnable(){
    enabled = true;
    plugin = this;
    console = getServer().getConsoleSender();
    pm = getServer().getPluginManager();
    pdfFile = getDescription();
    Prefix = ChatColor.DARK_RED+"["+ChatColor.GOLD+pdfFile.getName()+ChatColor.DARK_RED+"] "+ChatColor.WHITE;
    PlayerStrikes = new Configs(this);
    
    pm.registerEvents(new ChatListener(this), this);
    plugin.getConfig().options().copyDefaults(true);
    plugin.saveDefaultConfig();
    PlayerStrikes.getPS().options().copyDefaults(true);
    PlayerStrikes.saveDefaultPS();
    
    console.sendMessage(Prefix+pdfFile.getName()+" v"+pdfFile.getVersion()+" has been successfully enabled");
  }
}
