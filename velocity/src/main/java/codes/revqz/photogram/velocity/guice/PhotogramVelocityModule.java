package codes.revqz.photogram.velocity.guice;

import codes.revqz.photogram.platform.ServerPlatform;
import codes.revqz.photogram.velocity.PhotogramVelocityPlugin;
import com.google.inject.AbstractModule;
import com.velocitypowered.api.proxy.ProxyServer;

public class PhotogramVelocityModule extends AbstractModule {

    private final PhotogramVelocityPlugin plugin;
    private final ProxyServer proxy;

    public PhotogramVelocityModule(PhotogramVelocityPlugin plugin, ProxyServer proxy) {
        this.plugin = plugin;
        this.proxy = proxy;
    }

    @Override
    protected void configure() {
        bind(PhotogramVelocityPlugin.class).toInstance(plugin);
        bind(ServerPlatform.class).toInstance(plugin);
        bind(ProxyServer.class).toInstance(proxy);
    }
}
