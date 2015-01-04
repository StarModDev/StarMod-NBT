package com.gravypod.nbt.types;

import com.gravypod.nbt.NBT;
import com.gravypod.nbt.NBTInputStream;
import com.gravypod.nbt.NBTOutputStream;
import org.starmod.api.world.Sector;
import org.starmod.api.world.StarSystem;

import java.io.IOException;

public class NBTSector implements NBT<Sector> {

	private Sector value;

	public NBTSector(Sector value) {
		this.value = value;
	}

	public NBTSector(NBTInputStream in) throws IOException {
		StarSystem system = new NBTStarSystem(in).getValue();
		int x = in.readInt();
		int y = in.readInt();
		int z = in.readInt();
		this.value = new Sector(system, x, y, z);
	}

	@Override
	public byte getId() {
		return TagType.SECTOR.getId();
	}

	@Override
	public Sector getValue() {
		return value;
	}

	@Override
	public void setValue(Sector value) {
		this.value = value;
	}

	@Override
	public void write(NBTOutputStream out) throws IOException {
		new NBTStarSystem(value.getSystem()).write(out);
		out.writeInt(value.getX());
		out.writeInt(value.getY());
		out.writeInt(value.getZ());
	}

}
