package jiekie.joinkit.command;

import jiekie.joinkit.util.ChatUtil;
import jiekie.joinkit.util.ItemUtil;
import jiekie.joinkit.util.SoundUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class JoinKitCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player player)) {
            ChatUtil.notPlayer(sender);
            return true;
        }

        if(!player.isOp()) {
            ChatUtil.notOp(sender);
            return true;
        }

        if(args == null || args.length == 0) {
            ChatUtil.commandHelper(player);
            return true;
        }

        switch(args[0]) {
            case "받기":
                getJoinKit(player);
                break;

            case "나침반":
                getCompass(player);
                break;

            case "도움말":
                ChatUtil.commandList(player);
                break;

            default:
                ChatUtil.commandHelper(player);
                break;
        }

        return true;
    }

    private void getJoinKit(Player player) {
        PlayerInventory inventory = player.getInventory();
        if(inventory.firstEmpty() == -1) {
            ChatUtil.showMessage(player, ChatUtil.INVENTORY_FULL);
            return;
        }

        ItemStack joinKit = ItemUtil.getJoinKit();
        player.getInventory().addItem(joinKit);

        ChatUtil.showMessage(player, ChatUtil.GET_JOIN_KIT);
        SoundUtil.playNoteBlockBell(player);
    }

    private void getCompass(Player player) {
        PlayerInventory inventory = player.getInventory();
        if(inventory.firstEmpty() == -1) {
            ChatUtil.showMessage(player, ChatUtil.INVENTORY_FULL);
            return;
        }

        ItemStack compass = ItemUtil.getCompass();
        player.getInventory().addItem(compass);

        ChatUtil.showMessage(player, ChatUtil.GET_COMPASS);
        SoundUtil.playNoteBlockBell(player);
    }
}
