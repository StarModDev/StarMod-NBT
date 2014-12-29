package com.gravypod.nbt.types;

import com.gravypod.nbt.NBT;
import com.gravypod.nbt.NBTInputStream;
import com.gravypod.nbt.NBTOutputStream;

import java.io.IOException;

public class NBTByte implements NBT<Byte> {

	private Byte value;

	public NBTByte(Byte value) {
		this.value = value;
	}

	public NBTByte(NBTInputStream in) throws IOException {
		this.value = in.readByte();
	}

	@Override
	public byte getId() {
		return TagType.BYTE.getId();
	}

	@Override
	public Byte getValue() {
		return value;
	}

	@Override
	public void setValue(Byte value) {
		this.value = value;
	}

	@Override
	public void write(NBTOutputStream out) throws IOException {
		out.writeByte(value);
	}

}