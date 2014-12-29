package com.gravypod.nbt.types;

import com.gravypod.nbt.NBT;
import com.gravypod.nbt.NBTInputStream;
import com.gravypod.nbt.NBTOutputStream;

import java.io.IOException;

public class NBTInt implements NBT<Integer> {

	private Integer value;

	public NBTInt(Integer value) {
		this.value = value;
	}

	public NBTInt(NBTInputStream in) throws IOException {
		this.value = in.readInt();
	}

	@Override
	public byte getId() {
		return TagType.INT.getId();
	}

	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	public void setValue(Integer value) {
		this.value = value;
	}

	@Override
	public void write(NBTOutputStream out) throws IOException {
		out.writeInt(value);
	}

}