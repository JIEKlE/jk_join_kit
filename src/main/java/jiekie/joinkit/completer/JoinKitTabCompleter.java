package jiekie.joinkit.completer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JoinKitTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("jk.joinkit.command")) return Collections.emptyList();
        if(!(sender instanceof Player)) return Collections.emptyList();

        int length = args.length;
        if(length == 1) {
            return Arrays.asList("받기", "나침반", "도움말");
        }

        return Collections.emptyList();
    }
}
