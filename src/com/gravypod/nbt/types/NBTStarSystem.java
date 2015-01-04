package com.gravypod.nbt.types;

import com.gravypod.nbt.NBT;
import com.gravypod.nbt.NBTInputStream;
import com.gravypod.nbt.NBTOutputStream;
import org.starmod.api.world.StarSystem;

import java.io.IOException;

public class NBTStarSystem implements NBT<StarSystem> {

	private StarSystem value;

	public NBTStarSystem(StarSystem value) {
		this.value = value;
	}

	public NBTStarSystem(NBTInputStream in) throws IOException {
		int x = in.readInt();
		int y = in.readInt();
		int z = in.readInt();
		this.value = new StarSystem(x, y, z);
	}

	@Override
	public byte getId() {
		return TagType.STAR_SYSTEM.getId();
	}

	@Override
	public StarSystem getValue() {
		return value;
	}

	@Override
	public void setValue(StarSystem value) {
		this.value = value;
	}

	@Override
	public void write(NBTOutputStream out) throws IOException {
		out.writeInt(value.getX());
		out.writeInt(value.getY());
		out.writeInt(value.getZ());
	}

}