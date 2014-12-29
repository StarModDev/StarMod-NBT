package com.gravypod.nbt.types;

import com.gravypod.nbt.NBT;

import java.io.IOException;
import java.io.RandomAccessFile;

public class NBTBoolean extends NBT<Boolean> {

	public NBTBoolean() {
		this(false);
	}

	public NBTBoolean(RandomAccessFile channel) throws IOException {
		super(BOOLEAN, channel);
	}

	public NBTBoolean(boolean value) {
		super(BOOLEAN);
		setValue(value);
	}

	@Override
	protected void loadPayload(RandomAccessFile channel) throws IOException {
		setValue(channel.readBoolean());
	}

	@Override
	protected void savePayload(RandomAccessFile channel) throws IOException {
		channel.writeBoolean(getValue());
	}

}