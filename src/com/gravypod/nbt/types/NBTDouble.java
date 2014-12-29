package com.gravypod.nbt.types;

import com.gravypod.nbt.NBT;

import java.io.IOException;
import java.io.RandomAccessFile;

public class NBTDouble extends NBT<Double> {

	public NBTDouble() {
		this(0D);
	}

	public NBTDouble(RandomAccessFile channel) throws IOException {
		super(DOUBLE, channel);
	}

	public NBTDouble(double value) {
		super(DOUBLE);
		setValue(value);
	}

	@Override
	protected void loadPayload(RandomAccessFile channel) throws IOException {
		setValue(channel.readDouble());
	}

	@Override
	protected void savePayload(RandomAccessFile channel) throws IOException {
		channel.writeDouble(getValue());
	}

}