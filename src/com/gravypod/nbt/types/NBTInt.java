package com.gravypod.nbt.types;

import java.io.IOException;
import java.io.RandomAccessFile;

import com.gravypod.nbt.NBT;

public class NBTInt extends NBT<Integer> {
	
	
	public NBTInt() {
		this(0);
	}
	
	public NBTInt(RandomAccessFile channel) throws IOException {
		super(INT, channel);
	}
	
	public NBTInt(int value) {
		super(NBT.INT);
		setValue(value);
	}

	@Override
	protected void loadPayload(RandomAccessFile channel) throws IOException {
		setValue(channel.readInt());
	}

	@Override
	protected void savePayload(RandomAccessFile channel) throws IOException {
		channel.writeInt(getValue());
	}
	
	
	
}
