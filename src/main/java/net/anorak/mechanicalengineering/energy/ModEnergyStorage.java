package net.anorak.mechanicalengineering.energy;

import net.minecraftforge.energy.EnergyStorage;
import net.minecraft.nbt.CompoundTag;

public class ModEnergyStorage extends EnergyStorage {
    public ModEnergyStorage(int capacity, int maxReceive) {
        super(capacity, maxReceive);
    }

    public void addEnergy(int energy) {
        this.energy = Math.min(this.energy + energy, getMaxEnergyStored());
    }



    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("energy", energy);
        return tag;
    }

    public void deserializeNBT(Object nbt) {
        if (nbt instanceof CompoundTag tag) {
            this.energy = tag.getInt("energy");
        }
    }
}
