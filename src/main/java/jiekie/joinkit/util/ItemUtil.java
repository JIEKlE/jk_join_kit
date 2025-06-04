package jiekie.joinkit.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemUtil {
    private static ItemStack joinKit;
    private static ItemStack compass;

    public static ItemStack getJoinKit() {
        setJoinKit();
        return joinKit;
    }

    public static boolean isJoinKit(ItemStack target) {
        if(target == null) return false;
        setJoinKit();
        return joinKit.isSimilar(target);
    }

    public static List<ItemStack> getJoinKitItems() {
        List<ItemStack> items = new ArrayList<>();

        // 나침반
        setCompass();
        items.add(compass);

        // 불사의 토템
        items.add(new ItemStack(Material.TOTEM_OF_UNDYING));

        // 빵
        ItemStack bread = new ItemStack(Material.BREAD);
        bread.setAmount(10);
        items.add(bread);

        // 도구 3종 세트
        items.add(new ItemStack(Material.STONE_PICKAXE));
        items.add(new ItemStack(Material.STONE_SWORD));
        items.add(new ItemStack(Material.STONE_AXE));

        return items;
    }

    public static ItemStack getCompass() {
        setCompass();
        return compass;
    }

    public static boolean isCompass(ItemStack target) {
        if(target == null) return false;
        setCompass();
        return compass.isSimilar(target);
    }

    private static void setJoinKit() {
        if(joinKit != null) return;
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta joinKitMeta = item.getItemMeta();
        joinKitMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "스타터팩");
        joinKitMeta.setCustomModelData(3);
        joinKitMeta.setLore(List.of(
                ""
                , ChatColor.WHITE + "입주를 환영합니다!"
                , ChatColor.WHITE + "생존에 필요한 기본 아이템을 받아보세요."
        ));
        item.setItemMeta(joinKitMeta);
        joinKit = item;
    }

    private static void setCompass() {
        if(compass != null) return;
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta compassMeta = item.getItemMeta();
        compassMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "나침반");
        compassMeta.setCustomModelData(1);
        compassMeta.setLore(List.of(
                ""
                , ChatColor.GRAY + "\uA011 + \uA012 : 좌표 저장"
                , ChatColor.GRAY + "\uA011 + \uA013 : 이동"
        ));
        item.setItemMeta(compassMeta);
        compass = item;
    }
}
