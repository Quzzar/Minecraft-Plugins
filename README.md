# MC Plugin Archive

From 2012 to 2019, I built many, many Minecraft plugins for servers. Some were contracted (and, therefore, aren't included here) but most were either private plugins or plugins for the public.
Two of these plugins, Innovative Mechanics and Dominions, I actually sold as premium plugins for about $5. Made a couple thousand dollars back in high school from those! I also still have some plugins up for download on [Spigot](https://www.spigotmc.org/resources/authors/quzzar.447691/). My most popular plugin was my first public one, still around to this day on [Bukkit](https://dev.bukkit.org/projects/custom-armors)!
<br />
<br />
I might go back and rework some of these one day, plugin development is a lot of fun!

---

<details>

<summary><h2> AnvilAPI </b></summary>

## Introduction
Anvil API is a useful library plugin which facilitates text editing using the Anvil interface. It addresses the constraints of Minecraft's base UI, especially in terms of text input options, by repurposing the Anvil tool - since, with plugins, you're only limited to the assets provided by the base game.

## Key Features
- **Anvil-Based Text Editing**: Employs the Anvil interface for text input and editing, a creative solution to the limited text input options in Minecraft's UI.
- **Simplified Text Input**: Provides a user-friendly method for entering and editing text without using the command-line, crucial for various in-game interactions and plugin functionalities.
- **Great for RP Plugins**: Using the command-line is an RP killer and is heavily avoided in most RP plugins. However, many developers feel like they don't have a choice when it comes to text input. The AnvilAPI provides an easy to use, RP-friendly alternative to traditional text input.

</details>

<details>

<summary><h2> BetterRecipesLib </b></summary>

## Introduction
BetterRecipesLib is a sophisticated library plugin, designed as a wrapper around Spigot's recipe API. It enhances recipe creation in Minecraft plugin development by focusing on the items in a given recipe rather than just their material type - that way it takes into account all the item metadata that's traditionally ignored.

## Key Features
- **Advanced Recipe Customization**: Allows defining recipes based on specific items and their metadata, rather than just the material.
- **Metadata Inclusion**: Recognizes and incorporates additional item attributes like player head UUID, durability, flavor text, enchantments, and more into the recipe creation process.

## Additional Functionalities
- **Enhanced Specificity in Recipes**: Facilitates the creation of more precise and intricate recipes by considering the unique aspects of each item, such as custom swords or player head blocks.
- **Support for Custom Items**: Ideal for plugins that use custom items, enabling developers to create recipes that recognize these items' special characteristics.

## Impact and Current Usage
- **Filling a Gap in Plugin Development**: Addresses the limitations of Minecraft's standard recipe system, which only considers the basic material of an item.
- **Versatility for Custom Content**: Particularly beneficial for plugins involving head blocks or any custom content, as it allows for the crafting of unique items with specific attributes.
- **Essential Tool for Head Block Development**: Has become a crucial resource for all my plugin developement that involves player heads to create custom content. As of 01/18/24, there's still not an established alternative publicly available that addresses this issue.

</details>

<details>

<summary><h2> CraftMeta </b></summary>

## Introduction
CraftMeta is a versatile library plugin creates a very easy way for developers to store metadata in entities, blocks entities, and items. It simplifies and enhances the previously cumbersome and inconsistent process of embedding additional data into game elements.

## Key Features
- **Enhanced Metadata Storage**: Enables the storage of extra data in entities, block entities, and items, overcoming the limitations of Minecraft's standard methods.
- **Developer-Friendly API**: Offers a straightforward, HashMap-like approach for storing key-value pairs in various game elements, significantly simplifying the process.

## Additional Functionalities
- **Flexible Data Handling**: CraftMeta allows for the storage of any type of custom data, providing immense flexibility and control to the developer.
- **Data Retrieval Efficiency**: Ensures that stored data can be easily retrieved as needed, without unnecessary complications or data loss that can occur using traditional methods.

</details>


<details>

<summary><h2> Dominions </b></summary>

## Introduction
Dominions is an expansive plugin offering a really interesting alternative to popular plugins like Towny or Factions. Designed for use on a wide range of various servers, it provides a comprehensive system for territory management and customization. Even to this day (01/18/24), this plugin fills a void between Towny and Factions that you can't find elsewhere. That being that Towny only really works for peaceful servers where you want to build a community with friends and nothing else (and large towns require in-depth knowledge of the very complex command system that Towny employs to allow for player-specific chunk permissions). On the other side of the spectrum, Factions only works in high-intesity PvP servers - the idea of a chill town existing in peace in Factions basically is impossible mechanicially. Dominions provides a really innovative mix of both. It allows you to organically be at war with other dominions while also still having times of peace. No commands or anything, just the inherent nature of the systems it employs.

## Key Features
- **Territory Claims and Upkeep**: Allows players to claim territories by chunks and manage them with an upkeep cost system, similar to Towny.
- **Bank and Upgrades System**: Features a bank system for financial management and the ability to purchase upgrades for a player's Dominion.
- **User-Friendly UI**: Offers an intuitive and robust user interface for managing the Dominion, contrasting with traditional command-line interfaces.

## Additional Functionalities
- **Customizable Permissions**: Enables detailed customization of permissions within a Dominion, allowing different ranks to have specific abilities and roles.
- **Territory Acquisition and Loss**: Territories can be lost if the required funds for upkeep are not maintained, similar to the mechanics in Factions.
- **War Zones and Safe Zones**: Includes the concept of war zones and safe zones, which are typically set by the server administrators.

## Impact and Current Usage
- **Advanced Alternative to Existing Plugins**: Represents a sophisticated and more feature-rich option compared to existing territory management plugins.
- **Historical Popularity and Monetization**: Previously a paid plugin due to its extensive features and popularity, it is now available as an archive on the internet.
- **Continued Relevance and Potential Updates**: Although currently archived, the plugin holds significant potential for updates and continued use on modern servers.

## Added Note
This is one of the coolest plugins I ever built. For sure want to go back and update it one day. Maybe include some pictures of the interface.

</details>


<details>

<summary><h2>GrandExchange Revised</b></summary>

## Introduction
The GrandExchange is a really neat Minecraft plugin, primarily used in the Atlas MC server. As of time of writing (01/18/24), a version of it is still in active use on their server.

## Key Features
- **Player-Driven Economy**: Enables players to independently manage their economy by setting their own prices for buying and selling items.
- **Inspiration from RuneScape**: The concept is derived from RuneScape's system where players have the flexibility to trade items at various rates.
- **Dynamic Pricing**: Players are free to set item prices. A minor fee may be applicable for transactions.
- **Market Visibility**: Items for sale are visible to other players, promoting a dynamic market environment.
- **Price Control**: The plugin gives players full control over the pricing of items, leading to a diverse and player-centric economy.

## Additional Functionalities
- **AI Trades**: Incorporates automated trades to balance the economy.
  - **Item Rate Adjustment**: Based on the current market rates of certain items, additional basic items are introduced into the market.
  - **AI-Generated Items**: Items similar in price to those in the current market are generated to enrich the economy.

## Impact and Current Usage
- The plugin represents an innovative approach to in-game economies, offering a unique player experience.
- A variant of this plugin continues to be a key feature in the Atlas MC server, demonstrating its enduring relevance and popularity.

</details>

<details>

<summary><h2> Innovative Mechanics </b></summary>

## Introduction
Innovative Mechanics is an incredibly innovative plugin for Minecraft. It utilizes head blocks with custom textures to create an array of unique items and blocks, functioning as a classic tech mod within the game.

## Key Features
- **Custom Items and Blocks Creation**: Employs head blocks with custom textures to craft unique, tech-mod-like items and machinery.
- **Technology-Inspired Gameplay**: Offers features akin to classic tech mods like Buildcraft, with elements such as quarries, macerators, and pipes.
- **Plugin-Based Modding**: Looks like a mod but is actually a plugin - which continually blows people in the MC server space away (since plugins "can't" use custom textures).

## Additional Functionalities
- **Revised Version Enhancements**: The updated version includes new elements like airships, expanding the plugin's capabilities and appeal.
- **Visual Innovation**: The use of head blocks creates visually striking and seemingly impossible structures and mechanics within the game.

## Impact and Current Usage
- **Professional Opportunities**: The plugin's innovation led to contracting job offers and is still in use today in legacy servers.
- **Redefining Minecraft's Possibilities**: Has changed perceptions of what's achievable in Minecraft, challenging previously understood limitations.

## Showcase Video (TODO: needs to be updated to revised version)
[![Plugin Showcase Video](https://img.youtube.com/vi/2Pd-XVPhjVA/hqdefault.jpg)](https://www.youtube.com/embed/2Pd-XVPhjVA)


</details>


<details>

<summary><h2> OreCreator </b></summary>

## Introduction
Ore Creator is a neat plugin designed to counteract the common issue of X-Raying in the game. It offers a unique solution to ore generation, making it dynamically responsive to player mining activities.

## Key Features
- **Dynamic Ore Generation**: Transforms ore generation from a pre-defined system to a real-time process, occurring as players mine.
- **Countermeasure to X-Raying**: Specifically designed to address the challenge of players using X-Ray Texture Packs or mods to locate ores.

## Additional Functionalities
- **Random Ore Vein Creation**: When a player breaks a stone block at a certain depth, there is a random chance for a vein of ore to be generated behind the block.
- **Enhanced Fairness in Mining**: Prevents players from gaining unfair advantages through X-Raying, ensuring a more balanced and fair gaming experience.

## Impact and Current Usage
- **Role-Play Server Implementation**: Originally developed as a contracted plugin for a role-playing server, it has since been released for broader use.
- **Safeguarding Game Integrity**: By altering the traditional ore generation mechanics, Ore Creator helps maintain the integrity of gameplay in Minecraft environments where fair resource acquisition is crucial.

</details>


<details>

<summary><h2> And More... </b></summary>

As you can see in the repo, there's even more plugins I've built. _And these are only the plugins that I still have and am allowed to post publically!!_ If I was to guess, I think I've made about 80 plugins over the years. For me, they've been such a fun creative outlet and something I'd love to revisit. Feel free to check out the other smaller plugins I don't have a summary for, they're mainly utility plugins or so oddly niche it's not even worth writing a summary 😅

</details>




