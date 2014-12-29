package com.gravypod.nbt.types;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.gravypod.nbt.NBT;

public class NBTMap extends NBT<Map<String, NBT<?>>> {

	public NBTMap() {
		this(new HashMap<String, NBT<?>>());
	}

	public NBTMap(RandomAccessFile channel) throws IOException {
		super(MAP, channel);
	}

	public NBTMap(Map<String, NBT<?>> value) {
		super(MAP);
		setValue(value);
	}

	@Override
	protected void loadPayload(RandomAccessFile channel) throws IOException {

		if (getValue() == null) {
			setValue(new HashMap<String, NBT<?>>());
		}

		int length = channel.readInt();

		for (int i = 0; i < length; i++) {
			String key = channel.readUTF();
			NBT<?> val = NBT.readNBTChannel(channel);
			getValue().put(key, val);
		}

	}

	@Override
	protected void savePayload(RandomAccessFile channel) throws IOException {

		int length = getValue().size();

		channel.writeInt(length);

		for (Entry<String, NBT<?>> entry : getValue().entrySet()) {
            System.out.println(entry.getKey());
            channel.writeUTF(entry.getKey());
			NBT.writeNBTChannel(channel, entry.getValue());
		}

	}
	
	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		return (T) getValue().get(key);
	}
	public void put(String key, NBT<?> value) {
		getValue().put(key, value);
	}
}
