
package com.rayzr522.colournames;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMale implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission(Config.PERMISSION)) {
            p.sendMessage(Config.msg("no-permission"));
            return true;
        }

        PlayerData data = Config.getPlayer(p);

        data.setColor(Config.COLOR_MALE);
        data.updateName(p);

        Config.setPlayer(p, data);

        p.sendMessage(Config.msg("name-male"));

        return true;

    }

}
