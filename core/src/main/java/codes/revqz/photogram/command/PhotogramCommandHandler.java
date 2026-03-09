package codes.revqz.photogram.command;

import codes.revqz.photogram.PhotogramOrchestrator;
import codes.revqz.photogram.pipeline.ManagedImage;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

@Singleton
public final class PhotogramCommandHandler {

    private static final MiniMessage MM = MiniMessage.miniMessage();
    private static final String TAG = "<gold>[<yellow>Photogram<gold>]</gold> ";
    private static final String VERSION = loadVersion();

    private final PhotogramOrchestrator orchestrator;

    @Inject
    public PhotogramCommandHandler(PhotogramOrchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    public void reload(Audience sender) {
        orchestrator.reload();
        sender.sendMessage(MM.deserialize(TAG + "<green>Reloaded."));
    }

    public void view(Audience sender, String name) {
        ManagedImage img = orchestrator.images().get(name);
        if (img == null) {
            sender.sendMessage(MM.deserialize(TAG + "<red>Unknown image: <white>" + name));
            return;
        }

        sender.sendMessage(MM.deserialize(
                TAG + "<yellow>" + img.id()
                + " <gray>| <white>" + img.state().name()
                + " <gray>(" + img.completedUploads() + "/" + img.totalTiles() + ")"
        ));

        Component rendered = img.renderedComponent();
        if (rendered != null) {
            sender.sendMessage(rendered);
        }
    }

    public void info(Audience sender) {
        sender.sendMessage(MM.deserialize(
                "<gray>Running <gold><click:open_url:'https://github.com/revqz/Photogram'><hover:show_text:'<gray>Open on GitHub'>Photogram</hover></click></gold> "
                + "<yellow>v" + VERSION + " <gray>made by "
                + "<gold><click:open_url:'https://revqz.codes'><hover:show_text:'<gray>Visit website'>revqz</hover></click></gold>"
        ));
    }

    public Set<String> imageNames() {
        return orchestrator.images().keySet();
    }

    private static String loadVersion() {
        try (InputStream in = PhotogramCommandHandler.class.getClassLoader().getResourceAsStream("photogram.properties")) {
            if (in != null) {
                Properties props = new Properties();
                props.load(in);
                return props.getProperty("version", "unknown");
            }
        } catch (IOException ignored) {}
        return "unknown";
    }
}
