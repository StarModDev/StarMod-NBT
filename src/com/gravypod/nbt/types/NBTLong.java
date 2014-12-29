package com.gravypod.nbt.types;

import com.gravypod.nbt.NBT;

import java.io.IOException;
import java.io.RandomAccessFile;

public class NBTLong extends NBT<Long> {

	public NBTLong() {
		this(0L);
	}

	public NBTLong(RandomAccessFile channel) throws IOException {
		super(LONG, channel);
	}

	public NBTLong(long value) {
		super(LONG);
		setValue(value);
	}

	@Override
	protected void loadPayload(RandomAccessFile channel) throws IOException {
		setValue(channel.readLong());
	}

	@Override
	protected void savePayload(RandomAccessFile channel) throws IOException {
		channel.writeLong(getValue());
	}

}
