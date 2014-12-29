package com.gravypod.nbt.types;

import com.gravypod.nbt.NBT;
import com.gravypod.nbt.NBTInputStream;
import com.gravypod.nbt.NBTOutputStream;

import java.io.IOException;

public class NBTBoolean implements NBT<Boolean> {

	private Boolean value;

	public NBTBoolean(Boolean value) {
		this.value = value;
	}

	public NBTBoolean(NBTInputStream in) throws IOException {
		this.value = in.readBoolean();
	}

	@Override
	public byte getId() {
		return TagType.BOOLEAN.getId();
	}

	@Override
	public Boolean getValue() {
		return value;
	}

	@Override
	public void setValue(Boolean value) {
		this.value = value;
	}

	@Override
	public void write(NBTOutputStream out) throws IOException {
		out.writeBoolean(value);
	}

}