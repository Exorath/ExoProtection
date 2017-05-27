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

package com.exorath.exoProtection.config;

import org.bukkit.GameMode;

/**
 * Created by toonsev on 5/27/2017.
 */
public interface PlayerProtection {
    GameMode getDefaultGamemode();

    boolean canBreakBlocks();

    boolean canPlaceBlocks();


    boolean canInteract();

    boolean canDamageItems();

    boolean canPickupItems();

    boolean canPickupArrows();


    boolean canUsePortal();

    boolean canShear();

    boolean canSwapHandItems();

    boolean canUnleashEntity();

    boolean canDamageBlock();
    boolean canInteractInventory();


    boolean canTakeDamage();

    boolean canHunger();
}
