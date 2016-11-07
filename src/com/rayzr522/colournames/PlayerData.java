
package com.rayzr522.colournames;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PlayerData {

    private UUID   id;
    private String lastName;
    private String color;

    public PlayerData(UUID id, String lastName, String color) {
        this.id = id;
        this.lastName = lastName;
        this.color = color;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void updateName(Player player) {
        String name = TextUtils.colorize(color) + player.getName() + ChatColor.RESET;
        player.setDisplayName(name);
        if (Config.CHANGE_TABLIST) {
            try {
                player.setPlayerListName(name);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
