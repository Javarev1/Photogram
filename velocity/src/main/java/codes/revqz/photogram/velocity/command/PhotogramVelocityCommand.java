package codes.revqz.photogram.velocity.command;

import codes.revqz.photogram.command.PhotogramCommandHandler;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.google.inject.Inject;
import com.velocitypowered.api.command.CommandSource;

@CommandAlias("photogram")
public class PhotogramVelocityCommand extends BaseCommand {

    private final PhotogramCommandHandler handler;

    @Inject
    public PhotogramVelocityCommand(PhotogramCommandHandler handler) {
        this.handler = handler;
    }

    @Default
    public void onDefault(CommandSource sender) {
        handler.info(sender);
    }

    @Subcommand("reload")
    @CommandPermission("photogram.admin")
    public void onReload(CommandSource sender) {
        handler.reload(sender);
    }

    @Subcommand("view")
    @CommandPermission("photogram.admin")
    @CommandCompletion("@images")
    public void onView(CommandSource sender, String name) {
        handler.view(sender, name);
    }
}
