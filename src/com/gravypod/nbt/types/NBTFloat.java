package com.gravypod.nbt.types;

import com.gravypod.nbt.NBT;

import java.io.IOException;
import java.io.RandomAccessFile;

public class NBTFloat extends NBT<Float> {

	public NBTFloat() {
		this(0F);
	}

	public NBTFloat(RandomAccessFile channel) throws IOException {
		super(FLOAT, channel);
	}

	public NBTFloat(float value) {
		super(FLOAT);
		setValue(value);
	}

	@Override
	protected void loadPayload(RandomAccessFile channel) throws IOException {
		setValue(channel.readFloat());
	}

	@Override
	protected void savePayload(RandomAccessFile channel) throws IOException {
		channel.writeFloat(getValue());
	}

}