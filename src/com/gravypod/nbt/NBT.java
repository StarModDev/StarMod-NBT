package com.gravypod.nbt;

import java.io.IOException;

public interface NBT<T> {

	byte getId();

	T getValue();

	void setValue(T value);

	void write(NBTOutputStream out) throws IOException;

}