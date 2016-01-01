package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class ClearInventoryCommand extends ToolProCommand {

    private ToolsPro plugin;

    public ClearInventoryCommand(ToolsPro plugin) {
        super("clearinventory", Message.CMD_CI_DESC, Message.CMD_CI_DESC2.toString());
        this.setPermission("toolspro.commands.clearinventory");
        this.setAliases(new String[]{"ci"});
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        }else if (args.length != 0) {
            if (sender.hasPermission("toolspro.clearinventory.other")){
                Player p = this.plugin.getServer().getPlayer(args[0]);
                if (p !=null){
                    if (p.getGamemode() != 0){
                        Message.NOT_SURV.print(sender,"prefix:&7[&aClearInv&7]",'c','b');
                        //sender.sendMessage(TextFormat.colorize("&7[&aClearInv&7] &cИгровой режим игрока &b" + p.getName() + " &cне выживание!"));

                    } else {
                        p.getInventory().clearAll();
                        //sender.sendMessage(TextFormat.colorize("&7[&aClearInv&7] &aИнвентарь &b" + p.getName() + " &aочищен!"));
                        Message.CMD_CI_INVCLEAR.print(sender,"prefix:&7[&aClearInv&7]",'a','b');
                    }
                }else{
                    Message.UNKNOWNPLAYER.print(sender,"prefix:&7[&aClearInv&7]", 'c');
                    //sender.sendMessage(TextFormat.colorize("&7[&aClearInv&7] &cТакого игрока нет на сервере"));
                }
            }else{
                sender.sendMessage(this.getPermissionMessage());
            }
        }else if (sender instanceof Player){
            if(((Player) sender).getGamemode() != 0){
                Message.YOU_NOT_SURV.print(sender,"prefix:&7[&aClearInv&7]",'c');
                //sender.sendMessage(TextFormat.colorize("&7[&aClearInv&7] &cВаш игрокой режим не выживание!"));

            }else{
                ((Player) sender).getInventory().clearAll();
                Message.CMD_CI_YINVCLEAR.print(sender,"prefix:&7[&aClearInv&7]", 'a');
                //sender.sendMessage(TextFormat.colorize("&7[&aClearInv&7] &aВаш инвентарь был успешно очищен!"));
            }
        }else{
            Message.NEEDPLAYER.print(sender,"prefix:&7[&aClearInv&7]",'c');
            //sender.sendMessage(TextFormat.colorize("&7[&aClearInv&7] &cПожалуйста, используйте эту команду только в игре!"));
        }
        return true;
    }
}