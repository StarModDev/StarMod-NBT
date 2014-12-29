package com.gravypod.nbt.types;

import com.gravypod.nbt.NBT;
import com.gravypod.nbt.NBTInputStream;
import com.gravypod.nbt.NBTOutputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NBTList implements NBT<List<NBT>> {

	private List<NBT> value;

	public NBTList(List<NBT> value) {
		this.value = value;
	}

	public NBTList(NBTInputStream in) throws IOException {
		this.value = new ArrayList<>();
		int size = in.readInt();
		for (int i = 0; i < size; i++) {
			NBT tag = in.readTag();
			addTag(tag);
		}
	}

	public void addTag(NBT tag) {
		value.add(tag);
	}

	public NBT getTag(int index) {
		return value.get(index);
	}

	public void removeTag(NBT tag) {
		value.remove(tag);
	}

	@Override
	public byte getId() {
		return TagType.LIST.getId();
	}

	@Override
	public List<NBT> getValue() {
		return value;
	}

	@Override
	public void setValue(List<NBT> value) {
		this.value = value;
	}

	@Override
	public void write(NBTOutputStream out) throws IOException {
		out.writeInt(value.size());
		for (NBT tag : value) {
			out.writeTag(tag);
		}
	}

}