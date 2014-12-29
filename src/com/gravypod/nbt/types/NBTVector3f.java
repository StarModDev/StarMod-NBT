package com.gravypod.nbt.types;

import com.gravypod.nbt.NBT;

import javax.vecmath.Vector3f;
import java.io.IOException;
import java.io.RandomAccessFile;

public class NBTVector3f extends NBT<Vector3f> {

	public NBTVector3f() {
		this(new Vector3f(0F, 0F, 0F));
	}

	public NBTVector3f(RandomAccessFile channel) throws IOException {
		super(VECTOR3F, channel);
	}

	public NBTVector3f(Vector3f value) {
		super(VECTOR3F);
		setValue(value);
	}

	@Override
	protected void loadPayload(RandomAccessFile channel) throws IOException {
		float x = channel.readFloat();
		float y = channel.readFloat();
		float z = channel.readFloat();
		setValue(new Vector3f(x, y, z));
	}

	@Override
	protected void savePayload(RandomAccessFile channel) throws IOException {
		channel.writeFloat(getValue().x);
		channel.writeFloat(getValue().y);
		channel.writeFloat(getValue().z);
	}
}
