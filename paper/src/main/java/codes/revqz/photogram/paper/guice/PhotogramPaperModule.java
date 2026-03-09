package codes.revqz.photogram.paper.guice;

import codes.revqz.photogram.paper.PhotogramPaperPlugin;
import codes.revqz.photogram.platform.ServerPlatform;
import com.google.inject.AbstractModule;
import org.bukkit.plugin.java.JavaPlugin;

public class PhotogramPaperModule extends AbstractModule {

    private final PhotogramPaperPlugin plugin;

    public PhotogramPaperModule(PhotogramPaperPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    protected void configure() {
        bind(JavaPlugin.class).toInstance(plugin);
        bind(PhotogramPaperPlugin.class).toInstance(plugin);
        bind(ServerPlatform.class).toInstance(plugin);
    }
}
