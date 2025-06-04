package jiekie.joinkit.util;

import org.bukkit.command.CommandSender;

public class ChatUtil {
    /* error */
    public static final String INVENTORY_FULL = getXPrefix() + "인벤토리가 가득 찼습니다. 인벤토리를 1칸 이상 비워주시기 바랍니다.";
    public static final String INVENTORY_NOT_ENOUGH = getXPrefix() + "인벤토리 내 슬롯이 부족합니다. 인벤토리를 6칸 이상 비워주시기 바랍니다.";
    public static final String ALREADY_GET_JOIN_KIT = getXPrefix() + "이미 기본 아이템을 지급받았습니다.";

    /* feedback */
    public static final String GET_JOIN_KIT = getCheckPrefix() + "스타터팩을 지급받았습니다.";
    public static final String GET_JOIN_KIT_ITEMS = getGiftBoxPrefix() + "기본 아이템을 지급받았습니다.";

    /* prefix */
    public static String getCheckPrefix() {
        return "\uA001 ";
    }

    public static String getXPrefix() {
        return "\uA002 ";
    }

    public static String getWarnPrefix() {
        return "\uA003 ";
    }

    public static String getGiftBoxPrefix() {
        return "\uA014 ";
    }

    public static void showMessage(CommandSender sender, String message) {
        sender.sendMessage(message);
    }

    /* validate */
    public static void notPlayer(CommandSender sender) {
        sender.sendMessage(getWarnPrefix() + "플레이어가 아닙니다.");
    }

    public static void notOp(CommandSender sender) {
        sender.sendMessage(getWarnPrefix() + "권한이 없습니다.");
    }
}
