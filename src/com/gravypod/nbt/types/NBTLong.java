package com.gravypod.nbt.types;

import com.gravypod.nbt.NBT;
import com.gravypod.nbt.NBTInputStream;
import com.gravypod.nbt.NBTOutputStream;

import java.io.IOException;

public class NBTLong implements NBT<Long> {

	private Long value;

	public NBTLong(Long value) {
		this.value = value;
	}

	public NBTLong(NBTInputStream in) throws IOException {
		this.value = in.readLong();
	}

	@Override
	public byte getId() {
		return TagType.LONG.getId();
	}

	@Override
	public Long getValue() {
		return value;
	}

	@Override
	public void setValue(Long value) {
		this.value = value;
	}

	@Override
	public void write(NBTOutputStream out) throws IOException {
		out.writeLong(value);
	}

}