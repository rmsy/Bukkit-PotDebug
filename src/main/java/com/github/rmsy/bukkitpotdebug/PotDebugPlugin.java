package com.github.rmsy.bukkitpotdebug;

import org.bukkit.Bukkit;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;

import java.util.Collection;

public class PotDebugPlugin extends JavaPlugin implements Listener {
    @Override
    public void onDisable() {
        HandlerList.unregisterAll((JavaPlugin) this);
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPotionSplash(final PotionSplashEvent event) {
        ThrownPotion potion = event.getPotion();
        Collection<PotionEffect> effects = ((PotionMeta) potion.getItem().getItemMeta()).getCustomEffects();
        Bukkit.broadcastMessage("A " + event.getEntity().getType().getName() + " splashed a potion with " + effects.size() + " effects attached:");
        for (PotionEffect effect : effects) {
            Bukkit.broadcastMessage("Type: " + effect.getType().getName() + ", Level: " + (effect.getAmplifier() + 1) + ", Duration: " + effect.getDuration());
        }
    }
}
