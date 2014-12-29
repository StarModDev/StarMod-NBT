package com.gravypod.nbt.types;

import com.gravypod.nbt.NBT;

import java.io.IOException;
import java.io.RandomAccessFile;

public class NBTShort extends NBT<Short> {

	public NBTShort() {
		this((short) 0);
	}

	public NBTShort(RandomAccessFile channel) throws IOException {
		super(SHORT, channel);
	}

	public NBTShort(short value) {
		super(SHORT);
		setValue(value);
	}

	@Override
	protected void loadPayload(RandomAccessFile channel) throws IOException {
		setValue(channel.readShort());
	}

	@Override
	protected void savePayload(RandomAccessFile channel) throws IOException {
		channel.writeShort(getValue());
	}

}