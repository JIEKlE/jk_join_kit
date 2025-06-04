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

        PlayerInventory inventory = player.getInventory();
        if(inventory.firstEmpty() == -1) {
            ChatUtil.showMessage(player, ChatUtil.INVENTORY_FULL);
            return true;
        }

        ItemStack joinKit = ItemUtil.getJoinKit();
        player.getInventory().addItem(joinKit);

        ChatUtil.showMessage(player, ChatUtil.GET_JOIN_KIT);
        SoundUtil.playNoteBlockBell(player);

        return true;
    }
}
