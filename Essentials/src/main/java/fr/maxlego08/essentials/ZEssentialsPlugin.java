package fr.maxlego08.essentials;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tcoded.folialib.FoliaLib;
import com.tcoded.folialib.impl.FoliaImplementation;
import com.tcoded.folialib.impl.ServerImplementation;
import fr.maxlego08.essentials.api.Configuration;
import fr.maxlego08.essentials.api.ConfigurationFile;
import fr.maxlego08.essentials.api.EssentialsPlugin;
import fr.maxlego08.essentials.api.NmsUtils;
import fr.maxlego08.essentials.api.commands.CommandManager;
import fr.maxlego08.essentials.api.economy.EconomyManager;
import fr.maxlego08.essentials.api.hologram.HologramManager;
import fr.maxlego08.essentials.api.kit.Kit;
import fr.maxlego08.essentials.api.modules.ModuleManager;
import fr.maxlego08.essentials.api.placeholders.Placeholder;
import fr.maxlego08.essentials.api.placeholders.PlaceholderRegister;
import fr.maxlego08.essentials.api.scoreboard.ScoreboardManager;
import fr.maxlego08.essentials.api.server.EssentialsServer;
import fr.maxlego08.essentials.api.server.ServerType;
import fr.maxlego08.essentials.api.storage.Persist;
import fr.maxlego08.essentials.api.storage.ServerStorage;
import fr.maxlego08.essentials.api.storage.StorageManager;
import fr.maxlego08.essentials.api.storage.adapter.LocationAdapter;
import fr.maxlego08.essentials.api.user.User;
import fr.maxlego08.essentials.api.utils.EssentialsUtils;
import fr.maxlego08.essentials.api.utils.Warp;
import fr.maxlego08.essentials.api.utils.component.ComponentMessage;
import fr.maxlego08.essentials.buttons.ButtonHomes;
import fr.maxlego08.essentials.buttons.ButtonMailBox;
import fr.maxlego08.essentials.buttons.ButtonPayConfirm;
import fr.maxlego08.essentials.buttons.ButtonTeleportationConfirm;
import fr.maxlego08.essentials.buttons.kit.ButtonKitPreview;
import fr.maxlego08.essentials.buttons.sanction.ButtonSanctionInformation;
import fr.maxlego08.essentials.buttons.sanction.ButtonSanctions;
import fr.maxlego08.essentials.commands.CommandLoader;
import fr.maxlego08.essentials.commands.ZCommandManager;
import fr.maxlego08.essentials.commands.commands.essentials.CommandEssentials;
import fr.maxlego08.essentials.database.migrations.CreateChatMessageMigration;
import fr.maxlego08.essentials.database.migrations.CreateCommandsMigration;
import fr.maxlego08.essentials.database.migrations.CreateEconomyTransactionMigration;
import fr.maxlego08.essentials.database.migrations.CreateSanctionsTableMigration;
import fr.maxlego08.essentials.database.migrations.CreateUserCooldownTableMigration;
import fr.maxlego08.essentials.database.migrations.CreateUserEconomyMigration;
import fr.maxlego08.essentials.database.migrations.CreateUserHomeTableMigration;
import fr.maxlego08.essentials.database.migrations.CreateUserMailBoxMigration;
import fr.maxlego08.essentials.database.migrations.CreateUserOptionTableMigration;
import fr.maxlego08.essentials.database.migrations.CreateUserPlayTimeTableMigration;
import fr.maxlego08.essentials.database.migrations.CreateUserPowerToolsMigration;
import fr.maxlego08.essentials.database.migrations.CreateUserTableMigration;
import fr.maxlego08.essentials.database.migrations.UpdateUserTableAddSanctionColumns;
import fr.maxlego08.essentials.economy.EconomyModule;
import fr.maxlego08.essentials.hologram.HologramModule;
import fr.maxlego08.essentials.hooks.VaultEconomy;
import fr.maxlego08.essentials.kit.KitModule;
import fr.maxlego08.essentials.listener.PlayerListener;
import fr.maxlego08.essentials.loader.ButtonKitCooldownLoader;
import fr.maxlego08.essentials.loader.ButtonKitGetLoader;
import fr.maxlego08.essentials.loader.ButtonSanctionLoader;
import fr.maxlego08.essentials.loader.ButtonWarpLoader;
import fr.maxlego08.essentials.messages.MessageLoader;
import fr.maxlego08.essentials.module.ZModuleManager;
import fr.maxlego08.essentials.module.modules.HomeModule;
import fr.maxlego08.essentials.module.modules.MailBoxModule;
import fr.maxlego08.essentials.nms.r3_1_20.NmsHelper;
import fr.maxlego08.essentials.placeholders.DistantPlaceholder;
import fr.maxlego08.essentials.placeholders.LocalPlaceholder;
import fr.maxlego08.essentials.scoreboard.ScoreboardModule;
import fr.maxlego08.essentials.server.PaperServer;
import fr.maxlego08.essentials.server.SpigotServer;
import fr.maxlego08.essentials.server.redis.RedisServer;
import fr.maxlego08.essentials.storage.ConfigStorage;
import fr.maxlego08.essentials.storage.ZStorageManager;
import fr.maxlego08.essentials.storage.adapter.UserTypeAdapter;
import fr.maxlego08.essentials.user.ZUser;
import fr.maxlego08.essentials.user.placeholders.EconomyBaltopPlaceholders;
import fr.maxlego08.essentials.user.placeholders.ReplacePlaceholders;
import fr.maxlego08.essentials.user.placeholders.UserHomePlaceholders;
import fr.maxlego08.essentials.user.placeholders.UserKitPlaceholders;
import fr.maxlego08.essentials.user.placeholders.UserPlaceholders;
import fr.maxlego08.essentials.user.placeholders.UserPlayTimePlaceholders;
import fr.maxlego08.essentials.zutils.Metrics;
import fr.maxlego08.essentials.zutils.ZPlugin;
import fr.maxlego08.essentials.zutils.utils.CommandMarkdownGenerator;
import fr.maxlego08.essentials.zutils.utils.ComponentMessageHelper;
import fr.maxlego08.essentials.zutils.utils.PlaceholderMarkdownGenerator;
import fr.maxlego08.essentials.zutils.utils.PlaceholderUtils;
import fr.maxlego08.essentials.zutils.utils.ZServerStorage;
import fr.maxlego08.essentials.zutils.utils.paper.PaperUtils;
import fr.maxlego08.essentials.zutils.utils.spigot.SpigotUtils;
import fr.maxlego08.menu.api.ButtonManager;
import fr.maxlego08.menu.api.InventoryManager;
import fr.maxlego08.menu.api.pattern.PatternManager;
import fr.maxlego08.menu.button.loader.NoneLoader;
import fr.maxlego08.sarah.MigrationManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.permissions.Permissible;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public final class ZEssentialsPlugin extends ZPlugin implements EssentialsPlugin {

    private final UUID consoleUniqueId = UUID.fromString("00000000-0000-0000-0000-000000000000");
    private final List<Material> materials = Arrays.stream(Material.values()).filter(e -> !e.name().startsWith("LEGACY_")).toList();
    private EssentialsUtils essentialsUtils;
    private ServerStorage serverStorage = new ZServerStorage();
    private InventoryManager inventoryManager;
    private ButtonManager buttonManager;
    private PatternManager patternManager;
    private EssentialsServer essentialsServer;
    private ScoreboardManager scoreboardManager;
    private HologramManager hologramManager;

    @Override
    public void onEnable() {

        this.saveDefaultConfig();
        this.saveOrUpdateConfiguration("config.yml");

        FoliaLib foliaLib = new FoliaLib(this);
        this.serverImplementation = foliaLib.getImpl();
        this.essentialsUtils = isPaperVersion() ? new PaperUtils(this) : new SpigotUtils(this);
        this.essentialsServer = isPaperVersion() ? new PaperServer(this) : new SpigotServer(this);

        this.registerMigrations();

        this.placeholder = new LocalPlaceholder(this);
        DistantPlaceholder distantPlaceholder = new DistantPlaceholder(this, this.placeholder);
        distantPlaceholder.register();

        this.economyManager = new EconomyModule(this);
        this.scoreboardManager = new ScoreboardModule(this);
        this.hologramManager = new HologramModule(this);

        this.inventoryManager = this.getProvider(InventoryManager.class);
        this.buttonManager = this.getProvider(ButtonManager.class);
        this.patternManager = this.getProvider(PatternManager.class);
        this.registerButtons();

        this.moduleManager = new ZModuleManager(this);

        this.gson = getGsonBuilder().create();
        this.persist = new Persist(this);

        // Configurations files
        this.registerConfiguration(this.configuration = new MainConfiguration(this));
        this.registerConfiguration(new MessageLoader(this));

        // Load configuration files
        this.configurationFiles.forEach(ConfigurationFile::load);
        ConfigStorage.getInstance().load(getPersist());

        // Commands
        this.commandManager = new ZCommandManager(this);
        this.registerCommand("zessentials", new CommandEssentials(this), "ess");

        CommandLoader commandLoader = new CommandLoader(this);
        commandLoader.loadCommands(this.commandManager);

        this.getLogger().info("Create " + this.commandManager.countCommands() + " commands.");

        // Essentials Server
        if (this.configuration.getServerType() == ServerType.REDIS) {
            this.essentialsServer = new RedisServer(this);
            this.getLogger().info("Using Redis server.");
        }

        this.essentialsServer.onEnable();

        // Storage
        this.storageManager = new ZStorageManager(this);
        this.registerListener(this.storageManager);
        this.storageManager.onEnable();

        this.moduleManager.loadModules();

        this.registerListener(new PlayerListener(this));
        this.registerPlaceholder(UserPlaceholders.class);
        this.registerPlaceholder(UserHomePlaceholders.class);
        this.registerPlaceholder(UserPlayTimePlaceholders.class);
        this.registerPlaceholder(UserKitPlaceholders.class);
        this.registerPlaceholder(ReplacePlaceholders.class);
        this.registerPlaceholder(EconomyBaltopPlaceholders.class);

        new Metrics(this, 21703);

        this.generateDocs();
    }

    @Override
    public void onLoad() {

        try {
            Class.forName("net.milkbowl.vault.economy.Economy");
            new VaultEconomy(this);
            getLogger().info("Register Vault Economy.");
        } catch (final ClassNotFoundException ignored) {
            ignored.printStackTrace();
        }

    }

    @Override
    public void onDisable() {

        // Storage
        if (this.storageManager != null) this.storageManager.onDisable();
        if (this.persist != null) ConfigStorage.getInstance().save(this.persist);

        this.essentialsServer.onDisable();

    }

    private void registerButtons() {

        this.buttonManager.register(new NoneLoader(this, ButtonTeleportationConfirm.class, "zessentials_teleportation_confirm"));
        this.buttonManager.register(new NoneLoader(this, ButtonPayConfirm.class, "zessentials_pay_confirm"));
        this.buttonManager.register(new NoneLoader(this, ButtonHomes.class, "zessentials_homes"));
        this.buttonManager.register(new NoneLoader(this, ButtonSanctionInformation.class, "zessentials_sanction_information"));
        this.buttonManager.register(new NoneLoader(this, ButtonSanctions.class, "zessentials_sanctions"));
        this.buttonManager.register(new NoneLoader(this, ButtonKitPreview.class, "zessentials_kit_preview"));
        this.buttonManager.register(new NoneLoader(this, ButtonMailBox.class, "zessentials_mailbox"));
        this.buttonManager.register(new ButtonWarpLoader(this));
        this.buttonManager.register(new ButtonSanctionLoader(this));
        this.buttonManager.register(new ButtonKitCooldownLoader(this));
        this.buttonManager.register(new ButtonKitGetLoader(this));

    }

    private void registerMigrations() {

        MigrationManager.setMigrationTableName("zessentials_migrations");

        // MigrationManager.registerMigration(new CreateServerStorageTableMigration());
        MigrationManager.registerMigration(new CreateUserTableMigration());
        MigrationManager.registerMigration(new CreateUserOptionTableMigration());
        MigrationManager.registerMigration(new CreateUserCooldownTableMigration());
        MigrationManager.registerMigration(new CreateUserEconomyMigration());
        MigrationManager.registerMigration(new CreateEconomyTransactionMigration());
        MigrationManager.registerMigration(new CreateUserHomeTableMigration());
        MigrationManager.registerMigration(new CreateSanctionsTableMigration());
        MigrationManager.registerMigration(new UpdateUserTableAddSanctionColumns());
        MigrationManager.registerMigration(new CreateChatMessageMigration());
        MigrationManager.registerMigration(new CreateCommandsMigration());
        MigrationManager.registerMigration(new CreateUserPlayTimeTableMigration());
        MigrationManager.registerMigration(new CreateUserPowerToolsMigration());
        MigrationManager.registerMigration(new CreateUserMailBoxMigration());
    }

    @Override
    public CommandManager getCommandManager() {
        return this.commandManager;
    }

    @Override
    public List<ConfigurationFile> getConfigurationFiles() {
        return this.configurationFiles;
    }

    @Override
    public Gson getGson() {
        return this.gson;
    }

    @Override
    public Persist getPersist() {
        return this.persist;
    }

    @Override
    public ServerImplementation getScheduler() {
        return this.serverImplementation;
    }

    @Override
    public ModuleManager getModuleManager() {
        return this.moduleManager;
    }

    @Override
    public InventoryManager getInventoryManager() {
        return this.inventoryManager;
    }

    @Override
    public ButtonManager getButtonManager() {
        return this.buttonManager;
    }

    @Override
    public PatternManager getPatternManager() {
        return this.patternManager;
    }

    @Override
    public Placeholder getPlaceholder() {
        return this.placeholder;
    }

    @Override
    public StorageManager getStorageManager() {
        return this.storageManager;
    }

    private GsonBuilder getGsonBuilder() {
        return new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().serializeNulls().excludeFieldsWithModifiers(Modifier.TRANSIENT, Modifier.VOLATILE).registerTypeAdapter(Location.class, new LocationAdapter(this)).registerTypeAdapter(User.class, new UserTypeAdapter(this)).registerTypeAdapter(ZUser.class, new UserTypeAdapter(this));
    }

    private void registerPlaceholder(Class<? extends PlaceholderRegister> placeholderClass) {
        try {
            PlaceholderRegister placeholderRegister = placeholderClass.getConstructor().newInstance();
            placeholderRegister.register(this.placeholder, this);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public Configuration getConfiguration() {
        return this.configuration;
    }

    @Override
    public boolean isEconomyEnable() {
        return this.economyManager.isEnable();
    }

    @Override
    public EconomyManager getEconomyManager() {
        return this.economyManager;
    }

    @Override
    public UUID getConsoleUniqueId() {
        return this.consoleUniqueId;
    }

    private void generateDocs() {
        CommandMarkdownGenerator commandMarkdownGenerator = new CommandMarkdownGenerator();
        PlaceholderMarkdownGenerator placeholderMarkdownGenerator = new PlaceholderMarkdownGenerator();

        File fileCommand = new File(getDataFolder(), "commands.md");
        File filePlaceholder = new File(getDataFolder(), "placeholders.md");
        try {
            commandMarkdownGenerator.generateMarkdownFile(this.commandManager.getCommands(), fileCommand.toPath());
            getLogger().info("Markdown 'commands.md' file successfully generated!");
        } catch (IOException exception) {
            getLogger().severe("Error while writing the file commands: " + exception.getMessage());
            exception.printStackTrace();
        }

        try {
            placeholderMarkdownGenerator.generateMarkdownFile(((LocalPlaceholder) this.placeholder).getAutoPlaceholders(), filePlaceholder.toPath());
            getLogger().info("Markdown 'placeholders.md' file successfully generated!");
        } catch (IOException exception) {
            getLogger().severe("Error while writing the file placeholders: " + exception.getMessage());
            exception.printStackTrace();
        }
    }

    @Override
    public ServerStorage getServerStorage() {
        return serverStorage;
    }

    @Override
    public void setServerStorage(ServerStorage serverStorage) {
        this.serverStorage = serverStorage;
    }

    @Override
    public boolean isFolia() {
        return this.serverImplementation instanceof FoliaImplementation;
    }

    @Override
    public List<Warp> getWarps() {
        return ConfigStorage.warps;
    }

    @Override
    public Optional<Warp> getWarp(String name) {
        return getWarps().stream().filter(warp -> warp.name().equalsIgnoreCase(name)).findFirst();
    }

    @Override
    public int getMaxHome(Permissible permissible) {
        return this.moduleManager.getModule(HomeModule.class).getMaxHome(permissible);
    }

    @Override
    public User getUser(UUID uniqueId) {
        return this.storageManager.getStorage().getUser(uniqueId);
    }

    @Override
    public EssentialsServer getEssentialsServer() {
        return this.essentialsServer;
    }

    @Override
    public EssentialsUtils getUtils() {
        return this.essentialsUtils;
    }

    @Override
    public void debug(String string) {
        if (this.configuration.isEnableDebug()) {
            this.getLogger().info(string);
        }
    }

    @Override
    public void openInventory(Player player, String inventoryName) {
        this.inventoryManager.getInventory(this, inventoryName).ifPresent(inventory -> {
            this.serverImplementation.runAtLocation(player.getLocation(), wrappedTask -> {
                this.inventoryManager.getCurrentPlayerInventory(player).ifPresentOrElse(oldInventory -> {
                    this.inventoryManager.openInventory(player, inventory, 1, oldInventory);
                }, () -> this.inventoryManager.openInventory(player, inventory));
            });
        });
    }


    @Override
    public void saveOrUpdateConfiguration(String resourcePath) {
        this.saveOrUpdateConfiguration(resourcePath, resourcePath);
    }

    @Override
    public void saveOrUpdateConfiguration(String resourcePath, String toPath) {

        File file = new File(getDataFolder(), toPath);
        if (!file.exists()) {
            saveResource(resourcePath, toPath, false);
            return;
        }

        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        try {

            InputStream inputStream = this.getResource(resourcePath);

            if (inputStream == null) {
                this.getLogger().severe("Cannot find file " + resourcePath);
                return;
            }

            Reader defConfigStream = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);


            Set<String> defaultKeys = defConfig.getKeys(true);

            boolean configUpdated = false;
            for (String key : defaultKeys) {
                if (!config.contains(key)) {
                    configUpdated = true;
                }
            }

            config.setDefaults(defConfig);
            config.options().copyDefaults(true);

            if (configUpdated) {
                this.getLogger().info("Update file " + toPath);
                config.save(file);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public Optional<Kit> getKit(String kitName) {
        return this.moduleManager.getModule(KitModule.class).getKit(kitName);
    }

    @Override
    public void giveKit(User user, Kit kit, boolean bypassCooldown) {
        this.moduleManager.getModule(KitModule.class).giveKit(user, kit, bypassCooldown);
    }

    @Override
    public List<Material> getMaterials() {
        return this.materials;
    }

    @Override
    public ScoreboardManager getScoreboardManager() {
        return this.scoreboardManager;
    }

    @Override
    public void give(Player player, ItemStack itemStack) {

        PlayerInventory inventory = player.getInventory();

        Map<Integer, ItemStack> result = inventory.addItem(itemStack);
        if (result.isEmpty()) return;

        MailBoxModule mailBoxModule = this.moduleManager.getModule(MailBoxModule.class);
        result.values().forEach(item -> {
            int amount = itemStack.getAmount();
            if (amount > itemStack.getMaxStackSize()) {
                while (amount > 0) {
                    int currentAmount = Math.min(itemStack.getMaxStackSize(), amount);
                    amount -= currentAmount;

                    ItemStack clonedItemStacks = item.clone();
                    clonedItemStacks.setAmount(currentAmount);

                    mailBoxModule.addItem(player.getUniqueId(), clonedItemStacks);
                }
            } else {
                mailBoxModule.addItem(player.getUniqueId(), item);
            }
        });
    }

    @Override
    public HologramManager getHologramManager() {
        return this.hologramManager;
    }

    @Override
    public ComponentMessage getComponentMessage() {
        return ComponentMessageHelper.componentMessage;
    }

    @Override
    public String papi(Player player, String string) {
        return PlaceholderUtils.PapiHelper.papi(string, player);
    }

    @Override
    public NmsUtils getNmsUtils() {
        return new NmsHelper();
    }
}
