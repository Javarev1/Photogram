package codes.revqz.photogram.paper.command;

import codes.revqz.photogram.command.PhotogramCommandHandler;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.google.inject.Inject;
import org.bukkit.command.CommandSender;

@CommandAlias("photogram")
public class PhotogramCommand extends BaseCommand {

    private final PhotogramCommandHandler handler;

    @Inject
    public PhotogramCommand(PhotogramCommandHandler handler) {
        this.handler = handler;
    }

    @Default
    public void onDefault(CommandSender sender) {
        handler.info(sender);
    }

    @Subcommand("reload")
    @CommandPermission("photogram.admin")
    public void onReload(CommandSender sender) {
        handler.reload(sender);
    }

    @Subcommand("view")
    @CommandPermission("photogram.admin")
    @CommandCompletion("@images")
    public void onView(CommandSender sender, String name) {
        handler.view(sender, name);
    }
}
