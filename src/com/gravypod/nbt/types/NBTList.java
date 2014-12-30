package com.gravypod.nbt.types;

import com.gravypod.nbt.NBT;
import com.gravypod.nbt.NBTInputStream;
import com.gravypod.nbt.NBTOutputStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NBTList implements NBT<List<Object>> {

	private byte type;
	private List<Object> value;

	public NBTList(List<Object> value) {
		this.value = value;
		try {
			if (!value.isEmpty())
				this.type = TagType.getTag(value.get(0)).getId();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public NBTList(NBTInputStream in) throws IOException {
		this.value = new ArrayList<>();
		int size = in.readInt();
		this.type = in.readByte();
		TagType tagType = TagType.valueOf(type);
		for (int i = 0; i < size; i++) {
			Object obj = in.readTag(tagType).getValue();
			add(obj);
		}
	}

	public void add(Object tag) {
		value.add(tag);
	}

	public Object get(int index) {
		return value.get(index);
	}

	public void remove(Object tag) {
		value.remove(tag);
	}

	@Override
	public byte getId() {
		return TagType.LIST.getId();
	}

	@Override
	public List<Object> getValue() {
		return value;
	}

	@Override
	public void setValue(List<Object> value) {
		this.value = value;
	}

	@Override
	public void write(NBTOutputStream out) throws IOException {
		out.writeInt(value.size());
		out.writeByte(type);
		for (Object obj : value) {
			out.writeTag(TagType.getTag(obj), false);
		}
	}

}