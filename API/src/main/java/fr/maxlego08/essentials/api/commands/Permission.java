package fr.maxlego08.essentials.api.commands;

/**
 * Represents permissions related to commands.
 * This enum provides methods to generate permission strings based on its constants.
 */
public enum Permission {

    ESSENTIALS_USE,
    ESSENTIALS_RELOAD,
    ESSENTIALS_CONVERT,
    ESSENTIALS_GAMEMODE,
    ESSENTIALS_GAMEMODE_OTHER,
    ESSENTIALS_GAMEMODE_CREATIVE,
    ESSENTIALS_GAMEMODE_SURVIVAL,
    ESSENTIALS_GAMEMODE_SPECTATOR,
    ESSENTIALS_GAMEMODE_ADVENTURE,
    ESSENTIALS_DAY,
    ESSENTIALS_NIGHT,
    ESSENTIALS_SUN,
    ESSENTIALS_ENDERCHEST,
    ESSENTIALS_ENDERSEE,
    ESSENTIALS_ENDERSEE_OFFLINE,
    ESSENTIALS_TOP,
    ESSENTIALS_GOD,
    ESSENTIALS_HEAL,
    ESSENTIALS_SPEED,
    ESSENTIALS_TELEPORT_BYPASS,
    ESSENTIALS_TPA,
    ESSENTIALS_TPA_ACCEPT,
    ESSENTIALS_TPA_DENY,
    ESSENTIALS_TPA_CANCEL,
    ESSENTIALS_BYPASS_COOLDOWN,
    ESSENTIALS_MORE,
    ESSENTIALS_TP_WORLD,
    ESSENTIALS_TP_WORLD_OTHER,
    ESSENTIALS_TRASH,
    ESSENTIALS_FEED,
    ESSENTIALS_INVSEE,
    ESSENTIALS_INVSEE_OFFLINE,
    ESSENTIALS_FEED_OTHER,
    ESSENTIALS_HEAL_OTHER,
    ESSENTIALS_CRAFT,
    ESSENTIALS_ENCHANTING, ESSENTIALS_INVSEE_INTERACT,
    ESSENTIALS_COMPACT, ESSENTIALS_HAT,
    ESSENTIALS_PLAYER_WEATHER, ESSENTIALS_PLAYER_TIME,
    ESSENTIALS_TP, ESSENTIALS_ECO_USE,
    ESSENTIALS_ECO_GIVE, ESSENTIALS_ECO_TAKE,
    ESSENTIALS_ECO_GIVE_ALL, ESSENTIALS_MONEY, ESSENTIALS_MONEY_OTHER,
    ESSENTIALS_ECO_SET, ESSENTIALS_ECO_RESET,
    ESSENTIALS_ECO_SHOW, ESSENTIALS_PAY,
    ESSENTIALS_TP_HERE, ESSENTIALS_FLY,
    ESSENTIALS_SILENT_QUIT,
    ESSENTIALS_SILENT_JOIN, ESSENTIALS_ANVIL,
    ESSENTIALS_CARTOGRAPHYTABLE, ESSENTIALS_GRINDSTONE,
    ESSENTIALS_LOOM, ESSENTIALS_STONECUTTER,
    ESSENTIALS_SMITHINGTABLE, ESSENTIALS_BACK,
    ESSENTIALS_SET_SPAWN, ESSENTIALS_SPAWN,
    ESSENTIALS_WARP_SET, ESSENTIALS_WARP,
    ESSENTIALS_WARPS, ESSENTIALS_WARP_DEL,
    ESSENTIALS_TP_RANDOM,
    ESSENTIALS_SET_HOME,
    ESSENTIALS_SET_HOME_CONFIRM,
    ESSENTIALS_SET_HOME_OTHER, ESSENTIALS_HOME,
    ESSENTIALS_HOME_OTHER,
    ESSENTIALS_DEL_HOME,
    ESSENTIALS_DEL_HOME_CONFIRM,
    ESSENTIALS_FREEZE,
    ESSENTIALS_KICK,
    ESSENTIALS_KICK_NOTIFY,
    ESSENTIALS_KICK_ALL,
    ESSENTIALS_KICK_BYPASS_ALL,
    ESSENTIALS_KITTY_CANNON, ESSENTIALS_MUTE,
    ESSENTIALS_BAN, ESSENTIALS_UNBAN,
    ESSENTIALS_UNMUTE, ESSENTIALS_WARN,
    ESSENTIALS_BAN_NOTIFY, ESSENTIALS_MUTE_NOTIFY,
    ESSENTIALS_UNMUTE_NOTIFY, ESSENTIALS_UN_MUTE,
    ESSENTIALS_UNBAN_NOTIFY, ESSENTIALS_UN_BAN, ESSENTIALS_SANCTION,
    ESSENTIALS_CHAT_BYPASS_ALPHANUMERIC, ESSENTIALS_CHAT_BYPASS_DYNAMIC_COOLDOWN,
    ESSENTIALS_CHAT_BYPASS_LINK,
    ESSENTIALS_CHAT_BYPASS_SAME_MESSAGE,
    ESSENTIALS_CHAT_BYPASS_CAPS,
    ESSENTIALS_CHAT_BYPASS_FLOOD,
    ESSENTIALS_CHAT_BYPASS_DISABLE, ESSENTIALS_CHAT_MODERATOR,
    ESSENTIALS_CHAT_COLOR, ESSENTIALS_CHAT_DECORATION,
    ESSENTIALS_CHAT_RAINBOW, ESSENTIALS_CHAT_GRADIENT,
    ESSENTIALS_CHAT_CLICK, ESSENTIALS_CHAT_HOVER,
    ESSENTIALS_CHAT_NEWLINE, ESSENTIALS_CHAT_RESET,
    ESSENTIALS_CHAT_FONT, ESSENTIALS_CHAT_KEYBIND,
    ESSENTIALS_CHAT_LINK, ESSENTIALS_CHAT_HISTORY,
    ESSENTIALS_CHAT_CLEAR, ESSENTIALS_CHAT_ENABLE,
    ESSENTIALS_CHAT_DISABLE, ESSENTIALS_CHAT_BROADCAST,
    ESSENTIALS_MESSAGE, ESSENTIALS_REPLY,
    ESSENTIALS_MESSAGE_TOGGLE, ESSENTIALS_SOCIALSPY,
    ESSENTIALS_COOLDOWN_COMMAND_BYPASS,
    ESSENTIALS_COMPACT_ALL, ESSENTIALS_FURNACE,
    ESSENTIALS_SKULL, ESSENTIALS_BOTTOM,
    ESSENTIALS_REPAIR, ESSENTIALS_REPAIR_ALL,
    ESSENTIALS_EXT, ESSENTIALS_NEAR,
    ESSENTIALS_PLAY_TIME, ESSENTIALS_KILL_ALL,
    ESSENTIALS_SEEN, ESSENTIALS_SEEN_IP,
    ESSENTIALS_KIT, ESSENTIALS_KIT_,
    ESSENTIALS_KIT_BYPASS_COOLDOWN, ESSENTIALS_KIT_EDITOR,
    ESSENTIALS_KIT_CREATE, ESSENTIALS_COOLDOWN,
    ESSENTIALS_KIT_DELETE, ESSENTIALS_COOLDOWN_DELETE,
    ESSENTIALS_COOLDOWN_CREATE, ESSENTIALS_ITEM_NAME,
    ESSENTIALS_ITEM_LORE, ESSENTIALS_ITEM_LORE_ADD,
    ESSENTIALS_ITEM_LORE_SET, ESSENTIALS_PAY_TOGGLE,
    ESSENTIALS_PAY_TOGGLE_OTHER, DESCRIPTION_MESSAGE_TOGGLE_OTHER,
    ESSENTIALS_GIVE, ESSENTIALS_GIVE_ALL,
    ESSENTIALS_ITEM_LORE_CLEAR, ESSENTIALS_POWER_TOOLS,
    ESSENTIALS_POWER_TOOLS_TOGGLE, DESCRIPTION_POWER_TOOLS_TOGGLE_OTHER,
    ESSENTIALS_KIT_SHOW, ESSENTIALS_MAIL,
    ESSENTIALS_TP_ALL, ESSENTIALS_RULES,
    ESSENTIALS_HOLOGRAM, ESSENTIALS_HOLOGRAM_CREATE,
    ESSENTIALS_HOLOGRAM_DELETE, ESSENTIALS_HOLOGRAM_ADD_LINE,
    ESSENTIALS_HOLOGRAM_SET_LINE, ESSENTIALS_HOLOGRAM_REMOVE_LINE,

    ESSENTIALS_HOLOGRAM_SCALE, ESSENTIALS_HOLOGRAM_TRANSLATION,
    ESSENTIALS_HOLOGRAM_MOVE_HERE, ESSENTIALS_HOLOGRAM_BILLBOARD,
    ESSENTIALS_HOLOGRAM_TEXT_ALIGNMENT, ESSENTIALS_HOLOGRAM_YAW,
    ESSENTIALS_HOLOGRAM_MOVE_TO, ESSENTIALS_HOLOGRAM_INSERT_BEFORE_LINE,
    ESSENTIALS_HOLOGRAM_INSERT_AFTER_LINE, ESSENTIALS_HOLOGRAM_TEXT_BACKGROUND,
    ESSENTIALS_HOLOGRAM_LIST, ESSENTIALS_HOLOGRAM_TELEPORT,
    ESSENTIALS_HOLOGRAM_SEE_THROUGH, ESSENTIALS_HOLOGRAM_TEXT_SHADOW,
    ESSENTIALS_HOLOGRAM_SHADOW_STRENGTH, ESSENTIALS_HOLOGRAM_SHADOW_RADIUS,
    ESSENTIALS_SCOREBOARD, ESSENTIALS_BALANCE_TOP,
    ESSENTIALS_BALANCE_TOP_REFRESH, ESSENTIALS_MAIL_OPEN,
    ESSENTIALS_VOTEPARTY_USE, ESSENTIALS_VOTEPARTY_SET,
    ESSENTIALS_VOTEPARTY_REMOVE, ESSENTIALS_VOTEPARTY_ADD,
    ESSENTIALS_VOTE_USE, ESSENTIALS_VOTE_SET,
    ESSENTIALS_VOTE_REMOVE, ESSENTIALS_VOTE_ADD,
    ESSENTIALS_VAULT_USE,
    ESSENTIALS_VAULT_SET_SLOT,
    ESSENTIALS_VAULT_ADD_SLOT,
    ESSENTIALS_VAULT_GIVE,
    ESSENTIALS_ENCHANT,
    ESSENTIALS_NIGHTVISION,
    ESSENTIALS_SUDO,
    ESSENTIALS_SUDO_BYPASS,
    ESSENTIALS_MAIL_GIVE, ESSENTIALS_MAIL_GIVEALL,
    ESSENTIALS_WORLDEDIT_USE, ESSENTIALS_WORLDEDIT_GIVE,
    ESSENTIALS_WORLDEDIT_SET, ESSENTIALS_WORLDEDIT_CONFIRM,
    ESSENTIALS_WORLDEDIT_CUT, ESSENTIALS_WORLDEDIT_FILL,
    ESSENTIALS_WORLDEDIT_STOP, ESSENTIALS_WORLDEDIT_WALLS,
    ESSENTIALS_WORLDEDIT_SPHERE,
    ESSENTIALS_WORLDEDIT_POS1,
    ESSENTIALS_WORLDEDIT_POS2,
    ESSENTIALS_WORLDEDIT_CYL, ESSENTIALS_WORLDEDIT_OPTION_INVENTORY, ESSENTIALS_WORLDEDIT_OPTION, ESSENTIALS_WORLDEDIT_OPTION_BOSSBAR, ESSENTIALS_SHOW_ITEM, ESSENTIALS_EXPERIENCE, ESSENTIALS_EXPERIENCE_GRANT, ESSENTIALS_EXPERIENCE_QUERY, ESSENTIALS_EXPERIENCE_SET;


    /**
     * Generates the permission string for this enum constant.
     * Converts the enum constant name to lowercase and replaces underscores with periods.
     *
     * @return The permission string.
     */
    public String asPermission() {
        return name().toLowerCase().replace("_", ".");
    }

    /**
     * Generates a permission string for this enum constant with an additional suffix.
     * Appends the specified suffix to the permission string generated by {@link #asPermission()}.
     *
     * @param with The suffix to append.
     * @return The permission string with the suffix.
     */
    public String asPermission(String with) {
        return String.format(asPermission() + "%s", with);
    }

}

