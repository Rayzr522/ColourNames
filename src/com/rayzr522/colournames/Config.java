
package com.rayzr522.colournames;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Config {

	public static String					PERMISSION;
	public static String					COLOR_MALE;
	public static String					COLOR_FEMALE;
	public static String					COLOR_NORMAL;

	private static ColourNamesPlugin		plugin;
	private static FileConfiguration		config;

	public static HashMap<UUID, PlayerData>	players;

	public static void init(ColourNamesPlugin plugin) {

		Config.plugin = plugin;

		reload();

	}

	public static void reload() {

		config = plugin.getConfig();

		PERMISSION = config.getString("permission");

		COLOR_MALE = config.getString("color-male");
		COLOR_FEMALE = config.getString("color-female");
		COLOR_NORMAL = config.getString("color-normal");

		loadPlayers();

	}

	public static void loadPlayers() {

		players = new HashMap<UUID, PlayerData>();

		YamlConfiguration config = getConfig("players.yml");

		for (String key : config.getKeys(false)) {

			if (!config.isConfigurationSection(key)) {
				config.set(key, null);
				continue;
			}

			ConfigurationSection section = config.getConfigurationSection(key);

			UUID id = UUID.fromString(key);
			String lastName = section.getString("lastName");
			String color = section.getString("color");

			players.put(id, new PlayerData(id, lastName, color));

		}

	}

	public static void savePlayers() {

		YamlConfiguration config = getConfig("players.yml");

		for (PlayerData data : players.values()) {

			String key = data.getId().toString();

			ConfigurationSection section;

			if (config.isConfigurationSection(key)) {
				section = config.getConfigurationSection(key);
			} else {
				section = config.createSection(key);
			}

			section.set("lastName", data.getLastName());
			section.set("color", data.getColor());

		}

		File file = getFile("players.yml");
		try {
			file.createNewFile();
			config.save(file);
		} catch (IOException e) {
			System.err.println("Failed to save players.yml");
			e.printStackTrace();
		}

	}

	public static String msg(String label, String... params) {

		if (!config.contains("messages." + label.toLowerCase())) { return null; }

		String output = config.getString("messages." + label.toLowerCase());
		output = ChatColor.translateAlternateColorCodes('&', output);

		if (params == null || params.length < 1) { return output; }

		for (int i = 0; i < params.length; i++) {

			output = output.replaceAll("\\{" + i + "\\}", params[i]);

		}

		return output;

	}

	public static YamlConfiguration getConfig(String path) {

		File file = getFile(path);

		if (file.exists()) {
			return YamlConfiguration.loadConfiguration(file);
		} else {
			return new YamlConfiguration();
		}

	}

	public static File getFile(String path) {

		return new File(plugin.getDataFolder() + File.separator + path);

	}

	public static PlayerData getPlayer(Player player) {

		return players.containsKey(player.getUniqueId()) ? players.get(player.getUniqueId()) : null;

	}

	public static void setPlayer(Player player, PlayerData data) {

		players.put(player.getUniqueId(), data);

	}

}
