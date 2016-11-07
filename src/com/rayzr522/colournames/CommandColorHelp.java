
package com.rayzr522.colournames;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.base.Strings;

public class CommandColorHelp implements CommandExecutor {

    private static final String HORIZONTAL_BAR = ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + Strings.repeat("-", 53);

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

        p.sendMessage(HORIZONTAL_BAR);
        code(p, "0", "black");
        code(p, "1", "dark blue");
        code(p, "2", "dark green");
        code(p, "3", "teal");
        code(p, "4", "dark red");
        code(p, "5", "purple");
        code(p, "6", "gold");
        code(p, "7", "gray");
        code(p, "8", "dark gray");
        code(p, "9", "blue");
        code(p, "a", "light green");
        code(p, "b", "aqua");
        code(p, "c", "red");
        code(p, "d", "pink");
        code(p, "e", "yellow");
        code(p, "f", "white");
        p.sendMessage(HORIZONTAL_BAR);
        if (Config.ALLOW_MAGIC_COLOR) {
            code(p, "k", "magic");
        }
        code(p, "l", "bold");
        code(p, "m", "strike through");
        code(p, "n", "underline");
        code(p, "o", "italic");
        code(p, "r", "reset");
        p.sendMessage(HORIZONTAL_BAR);

        return true;

    }

    public void code(Player player, String code, String name) {

        player.sendMessage(Config.msg("color-code", "&" + code, color("&" + code) + name));

    }

    private String color(String text) {

        return ChatColor.translateAlternateColorCodes('&', text);

    }

}
