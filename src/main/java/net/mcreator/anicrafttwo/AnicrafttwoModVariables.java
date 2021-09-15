package net.mcreator.anicrafttwo;

import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.util.Timer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Direction;
import net.minecraft.network.PacketBuffer;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.Minecraft;

import java.util.function.Supplier;

public class AnicrafttwoModVariables {
	public AnicrafttwoModVariables(AnicrafttwoModElements elements) {
		elements.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new,
				PlayerVariablesSyncMessage::handler);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
	}

	private void init(FMLCommonSetupEvent event) {
		CapabilityManager.INSTANCE.register(PlayerVariables.class, new PlayerVariablesStorage(), PlayerVariables::new);
	}
	@CapabilityInject(PlayerVariables.class)
	public static Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = null;
	@SubscribeEvent
	public void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof PlayerEntity && !(event.getObject() instanceof FakePlayer))
			event.addCapability(new ResourceLocation("anicrafttwo", "player_variables"), new PlayerVariablesProvider());
	}
	private static class PlayerVariablesProvider implements ICapabilitySerializable<INBT> {
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(PLAYER_VARIABLES_CAPABILITY::getDefaultInstance);
		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public INBT serializeNBT() {
			return PLAYER_VARIABLES_CAPABILITY.getStorage().writeNBT(PLAYER_VARIABLES_CAPABILITY, this.instance.orElseThrow(RuntimeException::new),
					null);
		}

		@Override
		public void deserializeNBT(INBT nbt) {
			PLAYER_VARIABLES_CAPABILITY.getStorage().readNBT(PLAYER_VARIABLES_CAPABILITY, this.instance.orElseThrow(RuntimeException::new), null,
					nbt);
		}
	}

	private static class PlayerVariablesStorage implements Capability.IStorage<PlayerVariables> {
		@Override
		public INBT writeNBT(Capability<PlayerVariables> capability, PlayerVariables instance, Direction side) {
			CompoundNBT nbt = new CompoundNBT();
			nbt.putDouble("Defense", instance.Defense);
			nbt.putDouble("Luck", instance.Luck);
			nbt.putDouble("Speed", instance.Speed);
			nbt.putDouble("Stat_Points", instance.Stat_Points);
			nbt.putDouble("Strength", instance.Strength);
			nbt.putDouble("SXP", instance.SXP);
			nbt.putDouble("SXP_NextLevel", instance.SXP_NextLevel);
			nbt.putDouble("Timer", instance.Timer);
			nbt.putString("SkillFour", instance.SkillFour);
			nbt.putString("SkillFourSave", instance.SkillFourSave);
			nbt.putString("SkillOne", instance.SkillOne);
			nbt.putString("SkillOneSave", instance.SkillOneSave);
			nbt.putString("SkillThree", instance.SkillThree);
			nbt.putString("SkillTwo", instance.SkillTwo);
			nbt.putString("SkillTwoSave", instance.SkillTwoSave);
			nbt.putString("SkillThreeSave", instance.SkillThreeSave);
			nbt.putBoolean("FirstLoad", instance.FirstLoad);
			nbt.putBoolean("IsButtonDown", instance.IsButtonDown);
			return nbt;
		}

		@Override
		public void readNBT(Capability<PlayerVariables> capability, PlayerVariables instance, Direction side, INBT inbt) {
			CompoundNBT nbt = (CompoundNBT) inbt;
			instance.Defense = nbt.getDouble("Defense");
			instance.Luck = nbt.getDouble("Luck");
			instance.Speed = nbt.getDouble("Speed");
			instance.Stat_Points = nbt.getDouble("Stat_Points");
			instance.Strength = nbt.getDouble("Strength");
			instance.SXP = nbt.getDouble("SXP");
			instance.SXP_NextLevel = nbt.getDouble("SXP_NextLevel");
			instance.Timer = nbt.getDouble("Timer");
			instance.SkillFour = nbt.getString("SkillFour");
			instance.SkillFourSave = nbt.getString("SkillFourSave");
			instance.SkillOne = nbt.getString("SkillOne");
			instance.SkillOneSave = nbt.getString("SkillOneSave");
			instance.SkillThree = nbt.getString("SkillThree");
			instance.SkillTwo = nbt.getString("SkillTwo");
			instance.SkillTwoSave = nbt.getString("SkillTwoSave");
			instance.SkillThreeSave = nbt.getString("SkillThreeSave");
			instance.FirstLoad = nbt.getBoolean("FirstLoad");
			instance.IsButtonDown = nbt.getBoolean("IsButtonDown");
		}
	}

	public static class PlayerVariables {
		public double Defense = 0;
		public double Luck = 0;
		public double Speed = 0;
		public double Stat_Points = 0;
		public double Strength = 0;
		public double SXP = 0;
		public double SXP_NextLevel = 1.0;
		public double Timer = 60.0;
		public String SkillFour = "";
		public String SkillFourSave = "";
		public String SkillOne = "Gomu Gomu No Pistol";
		public String SkillOneSave = "Gomu Gomu No Pistol";
		public String SkillThree = "";
		public String SkillTwo = "";
		public String SkillTwoSave = "";
		public String SkillThreeSave = "";
		public boolean FirstLoad = true;
		public boolean IsButtonDown = false;
		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayerEntity)
				AnicrafttwoMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) entity),
						new PlayerVariablesSyncMessage(this));
		}
	}
	@SubscribeEvent
	public void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
		if (!event.getPlayer().world.isRemote())
			((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
					.syncPlayerVariables(event.getPlayer());
	}

	@SubscribeEvent
	public void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
		if (!event.getPlayer().world.isRemote())
			((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
					.syncPlayerVariables(event.getPlayer());
	}

	@SubscribeEvent
	public void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
		if (!event.getPlayer().world.isRemote())
			((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
					.syncPlayerVariables(event.getPlayer());
	}

	@SubscribeEvent
	public void clonePlayer(PlayerEvent.Clone event) {
		PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new PlayerVariables()));
		PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
		clone.Defense = original.Defense;
		clone.Luck = original.Luck;
		clone.Speed = original.Speed;
		clone.Stat_Points = original.Stat_Points;
		clone.Strength = original.Strength;
		clone.SXP = original.SXP;
		clone.SXP_NextLevel = original.SXP_NextLevel;
		clone.Timer = original.Timer;
		clone.SkillFour = original.SkillFour;
		clone.SkillFourSave = original.SkillFourSave;
		clone.SkillOne = original.SkillOne;
		clone.SkillOneSave = original.SkillOneSave;
		clone.SkillThree = original.SkillThree;
		clone.SkillTwo = original.SkillTwo;
		clone.SkillTwoSave = original.SkillTwoSave;
		clone.SkillThreeSave = original.SkillThreeSave;
		clone.FirstLoad = original.FirstLoad;
		if (!event.isWasDeath()) {
			clone.IsButtonDown = original.IsButtonDown;
		}
	}
	public static class PlayerVariablesSyncMessage {
		public PlayerVariables data;
		public PlayerVariablesSyncMessage(PacketBuffer buffer) {
			this.data = new PlayerVariables();
			new PlayerVariablesStorage().readNBT(null, this.data, null, buffer.readCompoundTag());
		}

		public PlayerVariablesSyncMessage(PlayerVariables data) {
			this.data = data;
		}

		public static void buffer(PlayerVariablesSyncMessage message, PacketBuffer buffer) {
			buffer.writeCompoundTag((CompoundNBT) new PlayerVariablesStorage().writeNBT(null, message.data, null));
		}

		public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new PlayerVariables()));
					variables.Defense = message.data.Defense;
					variables.Luck = message.data.Luck;
					variables.Speed = message.data.Speed;
					variables.Stat_Points = message.data.Stat_Points;
					variables.Strength = message.data.Strength;
					variables.SXP = message.data.SXP;
					variables.SXP_NextLevel = message.data.SXP_NextLevel;
					variables.Timer = message.data.Timer;
					variables.SkillFour = message.data.SkillFour;
					variables.SkillFourSave = message.data.SkillFourSave;
					variables.SkillOne = message.data.SkillOne;
					variables.SkillOneSave = message.data.SkillOneSave;
					variables.SkillThree = message.data.SkillThree;
					variables.SkillTwo = message.data.SkillTwo;
					variables.SkillTwoSave = message.data.SkillTwoSave;
					variables.SkillThreeSave = message.data.SkillThreeSave;
					variables.FirstLoad = message.data.FirstLoad;
					variables.IsButtonDown = message.data.IsButtonDown;
				}
			});
			context.setPacketHandled(true);
		}
	}
}
