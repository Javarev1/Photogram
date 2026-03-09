package codes.revqz.photogram.guice;

import codes.revqz.photogram.PhotogramOrchestrator;
import codes.revqz.photogram.command.PhotogramCommandHandler;
import codes.revqz.photogram.pipeline.SkinUploader;
import codes.revqz.photogram.storage.ConfigProvider;
import codes.revqz.photogram.storage.TextureStore;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import java.nio.file.Path;
import java.util.logging.Logger;

public class PhotogramCoreModule extends AbstractModule {

    private final Path dataDirectory;
    private final Logger logger;

    public PhotogramCoreModule(Path dataDirectory, Logger logger) {
        this.dataDirectory = dataDirectory;
        this.logger = logger;
    }

    @Override
    protected void configure() {
        bind(Path.class).annotatedWith(Names.named("dataDirectory")).toInstance(dataDirectory);
        bind(Logger.class).annotatedWith(Names.named("photogram")).toInstance(logger);

        bind(ConfigProvider.class).asEagerSingleton();
        bind(TextureStore.class).asEagerSingleton();
        bind(SkinUploader.class).asEagerSingleton();
        bind(PhotogramOrchestrator.class).asEagerSingleton();
        bind(PhotogramCommandHandler.class);
    }
}
