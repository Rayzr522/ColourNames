
package com.rayzr522.colournames;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;

public class CommandColorNames implements CommandExecutor {

    private ColourNamesPlugin plugin;

    public CommandColorNames(ColourNamesPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {

        if (args.length < 1) {

            sender.sendMessage(Config.msg("version", plugin.getDescription().getName(), plugin.getDescription().getVersion()));
            return true;

        } else {

            if (!sender.hasPermission(Config.PERMISSION_ADMIN)) {
                sender.sendMessage(Config.msg("no-permission"));
                return true;
            }

            if (args[0].equalsIgnoreCase("reload")) {

                plugin.reload();
                sender.sendMessage(Config.msg("reloaded"));
                return true;

            } else if (args[0].equalsIgnoreCase("save")) {

                plugin.save();
                sender.sendMessage(Config.msg("saved"));
                return true;

            } else {

                sender.sendMessage(ChatColor.RED + "/colournames [reload|save]");
                return true;

            }

        }

    }

}
