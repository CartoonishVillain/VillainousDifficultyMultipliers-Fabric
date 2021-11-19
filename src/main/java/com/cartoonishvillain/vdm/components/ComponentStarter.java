package com.cartoonishvillain.vdm.components;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.level.LevelComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.level.LevelComponentInitializer;
import dev.onyxstudios.cca.api.v3.world.WorldComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.world.WorldComponentInitializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class ComponentStarter implements EntityComponentInitializer, WorldComponentInitializer, LevelComponentInitializer {
    public static final ComponentKey<EntityComponent> ENTITYINSTANCE =
            ComponentRegistryV3.INSTANCE.getOrCreate(new ResourceLocation("vdm:entitydata"), EntityComponent.class);
    public static final ComponentKey<WorldComponent> WORLDINSTANCE =
            ComponentRegistryV3.INSTANCE.getOrCreate(new ResourceLocation("vdm:worlddata"), WorldComponent.class);
    public static final ComponentKey<LevelComponent> LEVELINSTANCE =
            ComponentRegistryV3.INSTANCE.getOrCreate(new ResourceLocation("vdm:leveldata"), LevelComponent.class);
    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerFor(LivingEntity.class, ENTITYINSTANCE, EntityComponent::new);
    }

    @Override
    public void registerLevelComponentFactories(LevelComponentFactoryRegistry registry) {
        registry.register(LEVELINSTANCE, LevelComponent::new);
    }

    @Override
    public void registerWorldComponentFactories(WorldComponentFactoryRegistry registry) {
        registry.register(WORLDINSTANCE, WorldComponent::new);
    }
}
