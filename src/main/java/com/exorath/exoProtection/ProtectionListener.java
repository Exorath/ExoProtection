/*
 * Copyright 2017 Exorath
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.exorath.exoProtection;

import com.exorath.exoProtection.config.ProtectionConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.event.world.WorldLoadEvent;

/**
 * Created by toonsev on 5/27/2017.
 */
public class ProtectionListener implements Listener {
    private ProtectionConfiguration configuration;

    public ProtectionListener(ProtectionConfiguration configuration) {
        this.configuration = configuration;
        Bukkit.getWorlds().forEach(world -> initWorld(world));
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onWorldLoad(WorldLoadEvent event) {
        initWorld(event.getWorld());
    }


    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent event) {
        if (configuration.getDefaultGamemode() != null)
            event.getPlayer().setGameMode(configuration.getDefaultGamemode());
    }

    private void initWorld(World world) {
        if (configuration.isAlwaysRaining() != null) {
            world.setWeatherDuration(Integer.MAX_VALUE);
            world.setStorm(configuration.isAlwaysRaining());
        }
        world.setTime(configuration.getInitialTime());
        world.setGameRuleValue("doDaylightCycle", String.valueOf(configuration.doNightCycle()));
        world.setGameRuleValue("doMobSpawning", String.valueOf(configuration.canSpawnCreatures()));
        world.setGameRuleValue("showDeathMessages", "false");
        world.setSpawnFlags(configuration.canSpawnCreatures(), configuration.canSpawnCreatures());
        if (!configuration.canSpawnCreatures()) {
            world.setAmbientSpawnLimit(0);
            world.setMonsterSpawnLimit(0);
            world.setAnimalSpawnLimit(0);
            world.setWaterAnimalSpawnLimit(0);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player)
            event.setCancelled(!configuration.canTakeDamage());
        else
            event.setCancelled(!configuration.entitiesCanDamage());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onWorldLoad(FoodLevelChangeEvent event) {
        event.setCancelled(!configuration.canHunger());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockBreak(BlockBreakEvent event) {
        event.setCancelled(!configuration.canBreakBlocks());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockPlace(BlockCanBuildEvent event) {
        event.setBuildable(configuration.canPlaceBlocks());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockPlace(BlockPlaceEvent event) {
        event.setCancelled(!configuration.canPlaceBlocks());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInteract(PlayerInteractEvent event) {
        event.setCancelled(!configuration.canInteract());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onItemDamage(PlayerItemDamageEvent event) {
        event.setCancelled(!configuration.canDamageItems());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onItemPickup(PlayerPickupItemEvent event) {
        event.setCancelled(!configuration.canPickupItems());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onArrowPickup(PlayerPickupArrowEvent event) {
        event.setCancelled(!configuration.canPickupArrows());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onUsePortal(PlayerPortalEvent event) {
        event.setCancelled(!configuration.canUsePortal());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onShear(PlayerShearEntityEvent event) {
        event.setCancelled(!configuration.canShear());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onSwapHandsItem(PlayerSwapHandItemsEvent event) {
        event.setCancelled(!configuration.canSwapHandItems());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onUnleashEntity(PlayerUnleashEntityEvent event) {
        event.setCancelled(!configuration.canUnleashEntity());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onDamageBlock(BlockDamageEvent event) {
        event.setCancelled(!configuration.canDamageBlock());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onInventoryInteract(InventoryInteractEvent event) {
        event.setCancelled(!configuration.canInteractInventory());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onStructureGrow(StructureGrowEvent event) {
        event.setCancelled(!configuration.canStructuresGrow());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockBurn(BlockBurnEvent event) {
        event.setCancelled(!configuration.canBlockBurn());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockDispenseItem(BlockDispenseEvent event) {
        event.setCancelled(!configuration.canBlockDispenseItems());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockFade(BlockFadeEvent event) {
        event.setCancelled(!configuration.canBlockFade());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockForm(BlockFormEvent event) {
        event.setCancelled(!configuration.canBlockForm());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockGrow(BlockGrowEvent event) {
        event.setCancelled(!configuration.canBlockGrow());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockFlow(BlockFromToEvent event) {
        event.setCancelled(!configuration.canBlockFlow());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockIgnite(BlockIgniteEvent event) {
        event.setCancelled(!configuration.canBlockIgnite());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPistonEvent(BlockPistonRetractEvent event) {
        event.setCancelled(!configuration.canPistonWork());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPistonEvent(BlockPistonExtendEvent event) {
        event.setCancelled(!configuration.canPistonWork());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockSpread(BlockSpreadEvent event) {
        event.setCancelled(!configuration.canBlockSpread());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLeavesDecay(LeavesDecayEvent event) {
        event.setCancelled(!configuration.canLeavesDecay());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onSignChange(SignChangeEvent event) {
        event.setCancelled(!configuration.canPlayersChangeSign());
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onSpawnCreature(CreatureSpawnEvent event) {
        event.setCancelled(!configuration.canSpawnCreatures());
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onEntitySpawn(EntitySpawnEvent event) {
        event.setCancelled(!configuration.canSpawnCreatures());
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onSpawnItem(ItemSpawnEvent event) {
        event.setCancelled(!configuration.canItemSpawn());
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onSpawnerSpawn(SpawnerSpawnEvent event) {
        event.setCancelled(!configuration.canSpawnersSpawn());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onWeatherChange(WeatherChangeEvent event) {
        event.setCancelled(configuration.isAlwaysRaining() != null);
        if (configuration.isAlwaysRaining() != null) {
            event.getWorld().setWeatherDuration(Integer.MAX_VALUE);
        }
    }
}
