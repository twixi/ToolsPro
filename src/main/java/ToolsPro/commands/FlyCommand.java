package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class FlyCommand extends Commands {

    private ToolsPro plugin;

    public FlyCommand(ToolsPro plugin) {
        super("fly", Message.CMD_FLY_DESCRIPTION, Message.CMD_FLY_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.fly.use");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            Message.YOU_DONT_HAVE_PERMISSION.print(sender, 'c');
        } else {
            if (args.length != 0) {
                if (sender.hasPermission("toolspro.commands.fly.other")) {
                    Player p = this.plugin.getServer().getPlayer(args[0]);
                    if (p == null) {
                        p = this.plugin.sortedListPlayers(args[0]);
                        if (p == null) {
                            Message.UNKNOWN_PLAYER.print(sender, "prefix:&7[&aFly&7]", 'c');
                            return true;
                        }
                    }
                    if (p.getGamemode() == 1 || p.getGamemode() == 3) {
                        Message.PLAYER_NOT_SURVIVAL_OR_ADVENTURE.print(sender, "prefix:&7[&aFly&7]", 'c', 'b', p.getName());
                    } else if (this.plugin.getPlayerFly(p)) {
                        this.plugin.removePlayerFly(p);
                        Message.CMD_FLY_PLAYER_DISABLE.print(sender, "prefix:&7[&aFly&7]", 'a', 'b', p.getName());
                        Message.CMD_FLY_PLAYER_DISABLE_MESSAGE.print(p, "prefix:&7[&aFly&7]", 'a');
                        this.plugin.info(sender, Message.CMD_FLY_PLAYER_DISABLE_INFO.getText("prefix:&7[Fly]", '7', '7', sender.getName(), p.getName()));
                    } else {
                        this.plugin.setPlayerFly(p);
                        Message.CMD_FLY_PLAYER_ENABLE.print(sender, "prefix:&7[&aFly&7]", 'a', 'b', p.getName());
                        Message.CMD_FLY_PLAYER_ENABLE_MESSAGE.print(p, "prefix:&7[&aFly&7]", 'a');
                        this.plugin.info(sender, Message.CMD_FLY_PLAYER_ENABLE_INFO.getText("prefix:&7[Fly]", '7', '7', sender.getName(), p.getName()));
                    }
                } else {
                    return Message.YOU_DONT_HAVE_PERMISSION.print(sender, 'c');
                }
            } else {
                if (sender instanceof Player) {
                    if (((Player) sender).getGamemode() == 1 || ((Player) sender).getGamemode() == 3) {
                        Message.YOU_NOT_SURVIVAL_OR_ADVENTURE.print(sender, "prefix:&7[&aFly&7]", 'c');
                    } else if (this.plugin.getPlayerFly((Player) sender)) {
                        this.plugin.removePlayerFly((Player) sender);
                        Message.CMD_FLY_SENDER_DISABLE.print(sender, "prefix:&7[&aFly&7]", 'a');
                        this.plugin.info(sender, Message.CMD_FLY_SENDER_DISABLE_INFO.getText("prefix:&7[Fly]", '7', '7', sender.getName()));
                    } else {
                        this.plugin.setPlayerFly((Player) sender);
                        Message.CMD_FLY_SENDER_ENABLE.print(sender, "prefix:&7[&aFly&7]", 'a');
                        this.plugin.info(sender, Message.CMD_FLY_SENDER_ENABLE_INFO.getText("prefix:&7[Fly]", '7', '7', sender.getName()));
                    }
                } else {
                    return Message.NEED_PLAYER.print(sender, "prefix:&7[&aFly&7]", 'c');
                }
            }
        }
        return true;
    }
}

