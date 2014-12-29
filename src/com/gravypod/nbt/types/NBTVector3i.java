package com.gravypod.nbt.types;

import com.gravypod.nbt.NBT;
import org.starmod.util.Vector3i;

import java.io.IOException;
import java.io.RandomAccessFile;

public class NBTVector3i extends NBT<Vector3i> {

	public NBTVector3i() {
		this(new Vector3i(0, 0, 0));
	}

	public NBTVector3i(RandomAccessFile channel) throws IOException {
		super(VECTOR3F, channel);
	}

	public NBTVector3i(Vector3i value) {
		super(VECTOR3F);
		setValue(value);
	}

	@Override
	protected void loadPayload(RandomAccessFile channel) throws IOException {
		int x = channel.readInt();
		int y = channel.readInt();
		int z = channel.readInt();
		setValue(new Vector3i(x, y, z));
	}

	@Override
	protected void savePayload(RandomAccessFile channel) throws IOException {
		channel.writeFloat(getValue().x);
		channel.writeFloat(getValue().y);
		channel.writeFloat(getValue().z);
	}
}
