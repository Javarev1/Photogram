package codes.revqz.photogram.storage;

import codes.revqz.photogram.config.PhotogramSettings;
import codes.revqz.photogram.config.serializer.ShadowColorTypeSerializer;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import net.kyori.adventure.text.format.ShadowColor;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.objectmapping.ObjectMapper;
import org.spongepowered.configurate.yaml.NodeStyle;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.nio.file.Path;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ConfigProvider {

    private final Logger logger;
    private final YamlConfigurationLoader loader;
    private final ObjectMapper<PhotogramSettings> mapper;

    private volatile PhotogramSettings current;

    @Inject
    public ConfigProvider(@Named("dataDirectory") Path dataDirectory, @Named("photogram") Logger logger) {
        this.logger = logger;

        Path configPath = dataDirectory.resolve("config.yml");

        this.loader = YamlConfigurationLoader.builder()
                .path(configPath)
                .nodeStyle(NodeStyle.BLOCK)
                .defaultOptions(opts -> opts.serializers(s ->
                        s.register(ShadowColor.class, ShadowColorTypeSerializer.INSTANCE)))
                .build();

        try {
            this.mapper = ObjectMapper.factory().get(PhotogramSettings.class);
        } catch (ConfigurateException e) {
            throw new IllegalStateException("Failed to create config mapper", e);
        }

        this.current = new PhotogramSettings();
    }

    public void reload() {
        try {
            CommentedConfigurationNode root = loader.load();
            this.current = mapper.load(root);
            Objects.requireNonNull(current, "Deserialized config was null");
        } catch (ConfigurateException e) {
            logger.log(Level.SEVERE, "Failed to load configuration", e);
            if (current == null) {
                this.current = new PhotogramSettings();
            }
        }
    }

    public PhotogramSettings settings() {
        return current;
    }
}
