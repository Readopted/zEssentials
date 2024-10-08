package fr.maxlego08.essentials.commands.commands.home;

import fr.maxlego08.essentials.api.EssentialsPlugin;
import fr.maxlego08.essentials.api.commands.CommandResultType;
import fr.maxlego08.essentials.api.commands.Permission;
import fr.maxlego08.essentials.api.messages.Message;
import fr.maxlego08.essentials.module.modules.HomeModule;
import fr.maxlego08.essentials.zutils.utils.commands.VCommand;

public class CommandSetHome extends VCommand {

    public CommandSetHome(EssentialsPlugin plugin) {
        super(plugin);
        this.setModule(HomeModule.class);
        this.setPermission(Permission.ESSENTIALS_SET_HOME);
        this.setDescription(Message.DESCRIPTION_SET_HOME);
        this.addRequireArg("name");
    }

    @Override
    protected CommandResultType perform(EssentialsPlugin plugin) {

        String homeName = this.argAsString(0);
        HomeModule homeModule = plugin.getModuleManager().getModule(HomeModule.class);

        if (homeModule.getDisableWorlds().contains(player.getWorld().getName())) {
            message(sender, Message.COMMAND_SET_HOME_WORLD);
            return CommandResultType.DEFAULT;
        }

        // For /sethome Maxlego08:<home name>
        if (homeName.contains(":") && hasPermission(sender, Permission.ESSENTIALS_SET_HOME_OTHER)) {
            String[] values = homeName.split(":", 2);
            String username = values[0];
            String home = values[1];
            homeModule.setHome(this.player, username, home);
            return CommandResultType.DEFAULT;
        }

        Message nameResult = homeModule.isValidHomeName(homeName);
        if (nameResult != null) {
            message(sender, nameResult, "%name%", homeName);
            return CommandResultType.DEFAULT;
        }

        int maxHome = plugin.getMaxHome(player);
        int current = user.countHomes();

        if (current >= maxHome && !this.user.isHomeName(homeName)) {
            message(sender, Message.COMMAND_SET_HOME_MAX, "%name%", homeName, "%max%", maxHome, "%current%", current);
            return CommandResultType.DEFAULT;
        }

        if (user.setHome(homeName, player.getLocation(), !homeModule.isHomeOverwriteConfirm())) {
            message(sender, Message.COMMAND_SET_HOME_CREATE, "%name%", homeName, "%max%", maxHome, "%current%", user.countHomes());
        }
        return CommandResultType.SUCCESS;
    }
}
