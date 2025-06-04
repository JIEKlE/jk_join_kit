package jiekie.joinkit.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ChatUtil {
    /* error */
    public static final String INVENTORY_FULL = getXPrefix() + "인벤토리가 가득 찼습니다. 인벤토리를 1칸 이상 비워주시기 바랍니다.";
    public static final String INVENTORY_NOT_ENOUGH = getXPrefix() + "인벤토리 내 슬롯이 부족합니다. 인벤토리를 6칸 이상 비워주시기 바랍니다.";
    public static final String ALREADY_GET_JOIN_KIT = getXPrefix() + "이미 기본 아이템을 지급받았습니다.";
    public static final String COORDINATES_NOT_EXIST = getXPrefix() + "설정된 좌표가 없습니다.";
    public static final String WORLD_NOT_EXIST = getXPrefix() + "존재하지 않는 월드입니다.";

    /* feedback */
    public static final String GET_JOIN_KIT = getCheckPrefix() + "스타터팩을 지급받았습니다.";
    public static final String GET_COMPASS = getCheckPrefix() + "나침반(스타터팩 아이템)을 지급받았습니다.";
    public static final String SET_COORDINATES = getCheckPrefix() + "좌표를 설정했습니다.";
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

    /* command */
    public static void commandHelper(CommandSender sender) {
        sender.sendMessage(getWarnPrefix() + "/스타터팩 도움말" + ChatColor.GRAY + " : 사용 가능한 명령어를 확인할 수 있습니다.");
    }

    public static void commandList(CommandSender sender) {
        sender.sendMessage("");
        sender.sendMessage(getWarnPrefix() + "스타터팩 명령어 목록");
        sender.sendMessage("　　　① /스타터팩 받기");
        sender.sendMessage(ChatColor.GRAY + "　　　　　: 스타터팩 상자를 받습니다.");
        sender.sendMessage("　　　② /스타터팩 나침반");
        sender.sendMessage(ChatColor.GRAY + "　　　　　: 나침반(스타터팩 아이템)을 받습니다.");
        sender.sendMessage("　　　③ /스타터팩 도움말");
        sender.sendMessage(ChatColor.GRAY + "　　　　　: 사용 가능한 명령어를 확인할 수 있습니다.");
        sender.sendMessage("");
    }
}
