/*******************************************************************************
 * Copyright 2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.init;

import static biomesoplenty.api.achievement.BOPAchievements.craft_ambrosia;
import static biomesoplenty.api.achievement.BOPAchievements.craft_amethyst_sword;
import static biomesoplenty.api.achievement.BOPAchievements.craft_dart_blower;
import static biomesoplenty.api.achievement.BOPAchievements.craft_flax_string;
import static biomesoplenty.api.achievement.BOPAchievements.craft_ornamental_artifact;
import static biomesoplenty.api.achievement.BOPAchievements.craft_poison_jar;
import static biomesoplenty.api.achievement.BOPAchievements.eat_shroom_powder;
import static biomesoplenty.api.achievement.BOPAchievements.explore_all_biomes;
import static biomesoplenty.api.achievement.BOPAchievements.grow_sacred_oak;
import static biomesoplenty.api.achievement.BOPAchievements.obtain_berry;
import static biomesoplenty.api.achievement.BOPAchievements.obtain_celestial_crystal;
import static biomesoplenty.api.achievement.BOPAchievements.obtain_deathbloom;
import static biomesoplenty.api.achievement.BOPAchievements.obtain_flowers;
import static biomesoplenty.api.achievement.BOPAchievements.obtain_ghastly_soul;
import static biomesoplenty.api.achievement.BOPAchievements.obtain_honeycomb;
import static biomesoplenty.api.achievement.BOPAchievements.obtain_pixie_dust;
import static biomesoplenty.api.achievement.BOPAchievements.obtain_thorn;
import static biomesoplenty.api.achievement.BOPAchievements.obtain_turnip;
import static biomesoplenty.api.achievement.BOPAchievements.use_enderporter;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.block.BlockBOPFlower;
import biomesoplenty.common.block.BlockBOPPlant;
import biomesoplenty.common.block.BlockBOPSapling;
import biomesoplenty.common.enums.BOPFlowers;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.item.ItemJarFilled;

public class ModAchievements 
{
    public static final AchievementPage achievementPage = new AchievementPage("Biomes O\' Plenty");
    
    public static void init()
    {
        AchievementPage.registerAchievementPage(achievementPage);
        
        addAchievements();
    }
    
    private static void addAchievements()
    {
        obtain_flowers = addAchievement("achievement.obtain_flowers", "obtain_flowers", 0, 0, new ItemStack(Blocks.red_flower), null);
        obtain_berry = addAchievement("achievement.obtain_berry", "obtain_berry", 2, 1, new ItemStack(BOPItems.berries), obtain_flowers);
        eat_shroom_powder = addAchievement("achievement.eat_shroom_powder", "eat_shroom_powder", 1, -2, new ItemStack(BOPItems.shroompowder), obtain_berry);
        obtain_thorn = addAchievement("achievement.obtain_thorn", "obtain_thorn", -2, -2, BlockBOPPlant.paging.getVariantItem(BOPPlants.THORN), eat_shroom_powder);
        craft_poison_jar = addAchievement("achievement.craft_poison_jar", "craft_poison_jar", -3, 1, new ItemStack(BOPItems.jar_filled, 1, ItemJarFilled.JarContents.POISON.ordinal()), obtain_thorn);
        craft_flax_string = addAchievement("achievement.craft_flax_string", "craft_flax_string", -4, -4, new ItemStack(BOPItems.flax_string), eat_shroom_powder);
        craft_dart_blower = addAchievement("achievement.craft_dart_blower", "craft_dart_blower", -6, -2, new ItemStack(BOPItems.dart_blower), craft_flax_string);
        craft_amethyst_sword = addAchievement("achievement.craft_amethyst_sword", "craft_amethyst_sword", -7, 0, new ItemStack(BOPItems.amethyst_sword), craft_dart_blower).setSpecial();
        obtain_deathbloom = addAchievement("achievement.obtain_deathbloom", "obtain_deathbloom", -6, 2, BlockBOPFlower.paging.getVariantItem(BOPFlowers.DEATHBLOOM), craft_poison_jar);
        obtain_turnip = addAchievement("achievement.obtain_turnip", "obtain_turnip", -1, -5, new ItemStack(BOPItems.turnip), eat_shroom_powder);
        grow_sacred_oak = addAchievement("achievement.grow_sacred_oak", "grow_sacred_oak", -3, -6, BlockBOPSapling.paging.getVariantItem(BOPTrees.SACRED_OAK), obtain_turnip).setSpecial();
        obtain_honeycomb = addAchievement("achievement.obtain_honeycomb", "obtain_honeycomb", 3, -3, new ItemStack(BOPItems.filled_honeycomb), eat_shroom_powder);
        craft_ornamental_artifact = addAchievement("achievement.craft_ornamental_artifact", "craft_ornamental_artifact", 5, -4, new ItemStack(BOPItems.ornamental_artifact), obtain_honeycomb);
        obtain_pixie_dust = addAchievement("achievement.obtain_pixie_dust", "obtain_pixie_dust", 7, -2, new ItemStack(BOPItems.pixie_dust), craft_ornamental_artifact);
        obtain_ghastly_soul = addAchievement("achievement.obtain_ghastly_soul", "obtain_ghastly_soul", 4, 2, new ItemStack(BOPItems.ghastly_soul), obtain_honeycomb);
        obtain_celestial_crystal = addAchievement("achievement.obtain_celestial_crystal", "obtain_celestial_crystal", 6, 0, new ItemStack(BOPItems.crystal_shard), obtain_honeycomb);
        craft_ambrosia = addAchievement("achievement.craft_ambrosia", "craft_ambrosia", 8, 4, new ItemStack(BOPItems.ambrosia), obtain_celestial_crystal).setSpecial();
        explore_all_biomes = addAchievement("achievement.explore_all_biomes", "explore_all_biomes", 2, -7, new ItemStack(BOPItems.earth), craft_ornamental_artifact).setSpecial();
        use_enderporter = addAchievement("achievement.use_enderporter", "use_enderporter", 1, 3, new ItemStack(BOPItems.enderporter), obtain_ghastly_soul).setSpecial();
    }
    
    private static Achievement addAchievement(String unlocalizedName, String identifier, int column, int row, ItemStack iconStack, Achievement parent)
    {
        Achievement achievement = new Achievement(unlocalizedName, identifier, column, row, iconStack, parent);
        achievement.registerStat();
        achievementPage.getAchievements().add(achievement);
        return achievement;
    }
}