package com.gravypod.nbt.types;

import com.gravypod.nbt.NBT;
import com.gravypod.nbt.NBTInputStream;
import com.gravypod.nbt.NBTOutputStream;

import java.io.IOException;

public class NBTShort implements NBT<Short> {

	private Short value;

	public NBTShort(Short value) {
		this.value = value;
	}

	public NBTShort(NBTInputStream in) throws IOException {
		this.value = in.readShort();
	}

	@Override
	public byte getId() {
		return TagType.SHORT.getId();
	}

	@Override
	public Short getValue() {
		return value;
	}

	@Override
	public void setValue(Short value) {
		this.value = value;
	}

	@Override
	public void write(NBTOutputStream out) throws IOException {
		out.writeShort(value);
	}

}