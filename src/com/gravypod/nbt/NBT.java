package com.gravypod.nbt;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.naming.spi.DirStateFactory.Result;

import com.gravypod.nbt.types.NBTByte;
import com.gravypod.nbt.types.NBTInt;
import com.gravypod.nbt.types.NBTList;
import com.gravypod.nbt.types.NBTMap;

/**
 * NBT object
 *
 * @param <T>
 */
public abstract class NBT<T> {

	/**
	 * Version constant
	 */
	public static final byte VERSION = 0; 
	
	/**
	 * Datatype constant for {@link Byte}
	 */
	public static final byte BYTE = 0;

	/**
	 * Datatype constant for {@link Short}
	 */
	public static final byte SHORT = 1;
	/**
	 * Datatype constant for {@link Integer}
	 */
	public static final byte INT = 2;
	/**
	 * Datatype constant for {@link Long}
	 */
	public static final byte LONG = 3;
	/**
	 * Datatype constant for {@link Double}
	 */
	public static final byte DOUBLE = 4;
	/**
	 * Datatype constant for {@link Float}
	 */
	public static final byte FLOAT = 5;
	/**
	 * Datatype constant for {@link Boolean}
	 */
	public static final byte BOOLEAN = 6;
	/**
	 * Datatype constant for {@link NBTList}
	 */
	public static final byte COLLECTION = 7;
	/**
	 * Datatype constant for {@link NBTMap}
	 */
	public static final byte MAP = 8;

	private T value;
	private final byte type;
	
	/**
	 * Construct an {@link NBT} and read it's payload from a channel
	 * @param type         - Type of object
	 * @param channel      - {@link RandomAccessFile} that will be read from (Make sure it is at the beginning of the object)
	 * @throws IOException - Thrown if there is an error reading
	 */
	public NBT(byte type, RandomAccessFile channel) throws IOException {
		this(type);
		loadPayload(channel);
	}

	public NBT(byte type) {
		this.type = type;
	}

	/**
	 * Read an {@link NBT} from a {@link File}
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static <T> NBT<T> readNBTFile(File file) throws IOException {

		RandomAccessFile channel = new RandomAccessFile(file, "r");

		/*byte version = */channel.readByte(); // Version. We want to be safe, don't we?
		
		NBT<T> result = null;

		try {
			result = readNBTChannel(channel);
		} finally {
			channel.close();
		}

		return result;
	}

	/**
	 * Read an {@link NBT} from a {@link RandomAccessFile}. 
	 * NOTE: This will read of the "type" byte
	 * @param channel
	 * @return 
	 * @throws IOException
	 */
	public static <T> NBT<T> readNBTChannel(RandomAccessFile channel) throws IOException, IllegalStateException {

		final byte type = channel.readByte();

		switch (type) {
		case BYTE: {
			@SuppressWarnings("unchecked")
			NBT<T> result = (NBT<T>) new NBTByte(channel);
			return result;
		}
		case INT: {
			@SuppressWarnings("unchecked")
			NBT<T> result = (NBT<T>) new NBTInt(channel);
			return result;
		}
		case COLLECTION: {
			@SuppressWarnings("unchecked")
			NBT<T> result = (NBT<T>) new NBTList<>(channel);
			return result;
		}
		case MAP: {
			@SuppressWarnings("unchecked")
			NBT<T> result = (NBT<T>) new NBTMap(channel);
			return result;
		}
		default:
			throw new IllegalStateException("Data in RandomAccessFile corrupt. No type found.");
		}
		
	}
	
	public static void writeNBTFile(File file, NBT<?> nbt) throws IOException {
		RandomAccessFile channel = new RandomAccessFile(file, "rw");
		writeNBTChannel(channel, nbt);
		channel.close();
	}
	
	public static void writeNBTChannel(RandomAccessFile channel, NBT<?> nbt) throws IOException {
		channel.writeByte(nbt.getType());
		nbt.savePayload(channel);
	}
	

	protected abstract void loadPayload(RandomAccessFile channel)
			throws IOException;

	protected abstract void savePayload(RandomAccessFile channel)
			throws IOException;

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public byte getType() {
		return type;
	}

}
