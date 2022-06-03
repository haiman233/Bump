package org.slimefunguguproject.bump.implementation.items.food;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemConsumptionHandler;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.slimefunguguproject.bump.implementation.Bump;
import org.slimefunguguproject.bump.implementation.setup.BumpItemGroups;

import javax.annotation.Nonnull;
import java.util.Locale;

/**
 * A {@link ConsumableFood} is a {@link SlimefunItem} that is based on vanilla food or potion.
 * Player can get effects after consuming.
 *
 * @author ybw0014
 */
public abstract class ConsumableFood extends SimpleSlimefunItem<ItemConsumptionHandler> {

    protected ConsumableFood(SlimefunItemStack itemStack, RecipeType recipeType, ItemStack[] recipe) {
        super(BumpItemGroups.FOOD, itemStack, recipeType, recipe);
    }

    @Nonnull
    @Override
    public ItemConsumptionHandler getItemHandler() {
        return (e, p, item) -> {
            String messageKey = this.getId().toLowerCase(Locale.ROOT);
            Bump.getLocalization().sendActionbarMessage(p, "food." + messageKey);

            if (p.getGameMode() != GameMode.CREATIVE) {
                ItemUtils.consumeItem(e.getItem(), false);
            }

            p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EAT, 1, 1);
            this.applyFoodEffects(p);
        };
    }

    public abstract void applyFoodEffects(Player p);
}
