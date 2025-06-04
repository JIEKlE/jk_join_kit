package jiekie.joinkit.event;

import jiekie.joinkit.JoinKitPlugin;
import jiekie.joinkit.util.ChatUtil;
import jiekie.joinkit.util.ItemUtil;
import jiekie.joinkit.util.ParticleUtil;
import jiekie.joinkit.util.SoundUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;
import java.util.Objects;

public class PlayerEvent implements Listener {
    private final JoinKitPlugin plugin;

    public PlayerEvent(JoinKitPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        giveJoinKit(e);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        onJoinKitClick(e);
        onCompassClick(e);
    }

    private void giveJoinKit(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if(player.hasPlayedBefore()) return;

        ItemStack joinKit = ItemUtil.getJoinKit();
        player.getInventory().addItem(joinKit);
    }

    private void onJoinKitClick(PlayerInteractEvent e) {
        if(!Objects.equals(e.getHand(), EquipmentSlot.HAND)) return;
        if(e.getAction() != Action.RIGHT_CLICK_AIR  && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player player = e.getPlayer();
        PlayerInventory inventory = player.getInventory();
        ItemStack item = inventory.getItemInMainHand();

        if(!ItemUtil.isJoinKit(item)) return;

        FileConfiguration config = plugin.getConfig();
        if(config.contains("provided." + player.getUniqueId())) {
            ChatUtil.showMessage(player, ChatUtil.ALREADY_GET_JOIN_KIT);
            return;
        }

        int emptySlotCount = 0;
        for(ItemStack itemStack : inventory.getStorageContents()) {
            if(itemStack == null || itemStack.getType() == Material.AIR)
                ++emptySlotCount;

            if(emptySlotCount == 6)
                break;
        }

        if(emptySlotCount < 6) {
            ChatUtil.showMessage(player, ChatUtil.INVENTORY_NOT_ENOUGH);
            return;
        }

        item.setAmount(item.getAmount() - 1);
        List<ItemStack> joinKitItems = ItemUtil.getJoinKitItems();
        for(ItemStack joinKitItem : joinKitItems) {
            inventory.addItem(joinKitItem);
        }

        config.set("provided." + player.getUniqueId(), true);
        plugin.saveConfig();

        ChatUtil.showMessage(player, ChatUtil.GET_JOIN_KIT_ITEMS);
        SoundUtil.playRocketLaunch(player);
        ParticleUtil.glow(player);
    }

    private void onCompassClick(PlayerInteractEvent e) {
        if(!Objects.equals(e.getHand(), EquipmentSlot.HAND)) return;

        Player player = e.getPlayer();
        PlayerInventory inventory = player.getInventory();
        ItemStack item = inventory.getItemInMainHand();

        if(!ItemUtil.isCompass(item)) return;
        if(!player.isSneaking()) return;

        Action action = e.getAction();
        if(action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK)
            setCoordinates(player);
        if(action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)
            moveToLocation(player);
    }

    private void setCoordinates(Player player) {
        String path = "locations." + player.getUniqueId();
        Location location = player.getLocation();

        FileConfiguration config = plugin.getConfig();
        config.set(path + ".world", location.getWorld().getName());
        config.set(path + ".x", location.getX());
        config.set(path + ".y", location.getY());
        config.set(path + ".z", location.getZ());
        config.set(path + ".yaw", location.getYaw());
        config.set(path + ".pitch", location.getPitch());
        plugin.saveConfig();

        ChatUtil.showMessage(player, ChatUtil.SET_COORDINATES);
        SoundUtil.playNoteBlockBell(player);
    }

    private void moveToLocation(Player player) {
        String path = "locations." + player.getUniqueId();
        FileConfiguration config = plugin.getConfig();
        if(!config.contains(path + ".world")) {
            ChatUtil.showMessage(player, ChatUtil.COORDINATES_NOT_EXIST);
            return;
        }

        String worldName = config.getString(path + ".world", "");
        World world = Bukkit.getWorld(worldName);
        if(world == null) {
            ChatUtil.showMessage(player, ChatUtil.WORLD_NOT_EXIST);
            return;
        }

        double x = config.getDouble(path + ".x");
        double y = config.getDouble(path + ".y");
        double z = config.getDouble(path + ".z");
        float yaw = (float) config.getDouble(path + ".yaw");
        float pitch = (float) config.getDouble(path + ".pitch");
        Location location = new Location(world, x, y, z, yaw, pitch);

        player.teleport(location);
        SoundUtil.playTeleport(player);
    }
}
