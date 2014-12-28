package com.gravypod.nbt.types;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.gravypod.nbt.NBT;

public class NBTList<T extends NBT<?>> extends NBT<List<T>> {
	
	public NBTList() {
		// Linked list is used to make loading fast.
		// .add(T o) will be faster on a linked list type.
		this(new LinkedList<T>());
	}
	
	public NBTList(RandomAccessFile channel) throws IOException {
		super(COLLECTION, channel);
	}
	
	public NBTList(List<T> value) {
		super(COLLECTION);
		setValue(value);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void loadPayload(RandomAccessFile channel) throws IOException {
		
		if (getValue() == null) {
			setValue(new LinkedList<T>());
		}
		
		int length = channel.readInt();
		
		for (int i = 0; i < length; i++) {
			getValue().add((T) NBT.readNBTChannel(channel));
		}
		
	}

	@Override
	protected void savePayload(RandomAccessFile channel) throws IOException {
		
		channel.writeInt(getValue().size());
		
		Iterator<T> iterator = getValue().iterator();
		
		for (T nbt = iterator.next(); iterator.hasNext(); nbt = iterator.next()) {
			NBT.writeNBTChannel(channel, nbt);
		}
		
	}
	
}
