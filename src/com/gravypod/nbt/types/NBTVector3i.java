package com.gravypod.nbt.types;

import com.gravypod.nbt.NBT;
import com.gravypod.nbt.NBTInputStream;
import com.gravypod.nbt.NBTOutputStream;
import com.gravypod.nbt.Vector3i;

import java.io.IOException;

public class NBTVector3i implements NBT<Vector3i> {

	private Vector3i value;

	public NBTVector3i(Vector3i value) {
		this.value = value;
	}

	public NBTVector3i(NBTInputStream in) throws IOException {
		int x = in.readInt();
		int y = in.readInt();
		int z = in.readInt();
		this.value = new Vector3i(x, y, z);
	}

	@Override
	public byte getId() {
		return TagType.VECTOR3I.getId();
	}

	@Override
	public Vector3i getValue() {
		return value;
	}

	@Override
	public void setValue(Vector3i value) {
		this.value = value;
	}

	@Override
	public void write(NBTOutputStream out) throws IOException {
		out.writeInt(value.x);
		out.writeInt(value.y);
		out.writeInt(value.z);
	}

}