package com.gravypod.nbt.types;

import com.gravypod.nbt.NBT;
import com.gravypod.nbt.NBTInputStream;
import com.gravypod.nbt.NBTOutputStream;
import org.starmod.api.world.Location;
import org.starmod.api.world.Sector;

import java.io.IOException;

public class NBTLocation implements NBT<Location> {

	private Location value;

	public NBTLocation(Location value) {
		this.value = value;
	}

	public NBTLocation(NBTInputStream in) throws IOException {
		Sector sector = new NBTSector(in).getValue();
		float x = in.readFloat();
		float y = in.readFloat();
		float z = in.readFloat();
		float pitch = in.readFloat();
		float yaw = in.readFloat();
		this.value = new Location(sector, x, y, z, pitch, yaw);
	}

	@Override
	public byte getId() {
		return TagType.LOCATION.getId();
	}

	@Override
	public Location getValue() {
		return value;
	}

	@Override
	public void setValue(Location value) {
		this.value = value;
	}

	@Override
	public void write(NBTOutputStream out) throws IOException {
		new NBTSector(value.getSector()).write(out);
		out.writeFloat(value.getX());
		out.writeFloat(value.getY());
		out.writeFloat(value.getZ());
		out.writeFloat(value.getPitch());
		out.writeFloat(value.getYaw());
	}

}
