
package com.rayzr522.colournames;

import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ColourNamesPlugin extends JavaPlugin implements Listener {

	private Logger logger;

	@Override
	public void onEnable() {

		logger = getLogger();

		saveDefaultConfig();

		Config.init(this);

		for (Player player : Bukkit.getOnlinePlayers()) {

			UUID id = player.getUniqueId();

			if (!Config.players.containsKey(id)) {

				Config.players.put(id, new PlayerData(id, player.getName(), Config.COLOR_NORMAL));

			}

		}

		getCommand("name").setExecutor(new CommandName());
		getCommand("male").setExecutor(new CommandMale());
		getCommand("female").setExecutor(new CommandFemale());
		getCommand("normal").setExecutor(new CommandNormal());
		getCommand("colorhelp").setExecutor(new CommandColorHelp());

		getServer().getPluginManager().registerEvents(this, this);

		logger.info(getDescription().getName() + " v" + getDescription().getVersion() + " has been enabled");

	}

	@Override
	public void onDisable() {

		Config.savePlayers();

		logger.info(getDescription().getName() + " v" + getDescription().getVersion() + " has been disabled");

	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {

		Player p = e.getPlayer();
		UUID id = p.getUniqueId();

		PlayerData data;

		if (!Config.players.containsKey(id)) {

			data = new PlayerData(id, p.getName(), Config.COLOR_NORMAL);
			Config.players.put(id, data);

		} else {

			data = Config.players.get(id);

		}

		data.setLastName(p.getName());
		data.updateName(p);

		Config.players.put(id, data);

	}

}
