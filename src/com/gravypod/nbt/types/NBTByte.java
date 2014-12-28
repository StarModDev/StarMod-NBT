package com.gravypod.nbt.types;

import java.io.IOException;
import java.io.RandomAccessFile;

import com.gravypod.nbt.NBT;

public class NBTByte extends NBT<Byte> {
	
	private static final byte zero = 0;
	
	public NBTByte() {
		this(zero);
	}
	
	public NBTByte(RandomAccessFile channel) throws IOException {
		super(BYTE, channel);
	}
	
	public NBTByte(byte value) {
		super(BYTE);
		setValue(value);
	}
	
	@Override
	protected void loadPayload(RandomAccessFile channel) throws IOException {
		setValue(channel.readByte());
	}

	@Override
	protected void savePayload(RandomAccessFile channel) throws IOException {
		channel.writeByte(getValue());
	}

}
