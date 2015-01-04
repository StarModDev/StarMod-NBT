package com.gravypod.nbt.types;

import com.gravypod.nbt.NBT;
import org.starmod.api.world.Location;
import org.starmod.api.world.Sector;
import org.starmod.api.world.StarSystem;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum TagType {

	BYTE(0, NBTByte.class, Byte.class),
	SHORT(1, NBTShort.class, Short.class),
	INT(2, NBTInt.class, Integer.class),
	LONG(3, NBTLong.class, Long.class),
	DOUBLE(4, NBTDouble.class, Double.class),
	FLOAT(5, NBTFloat.class, Float.class),
	BOOLEAN(6, NBTBoolean.class, Boolean.class),
	LIST(7, NBTList.class, List.class),
	MAP(8, NBTMap.class, Map.class),
	STRING(9, NBTString.class, String.class),
	LOCATION(10, NBTLocation.class, Location.class),
	SECTOR(11, NBTSector.class, Sector.class),
	STAR_SYSTEM(12, NBTStarSystem.class, StarSystem.class);

	private static final Map<Class, TagType> classTypes;
	private static final Map<Byte, TagType> types;

	private final byte id;
	private final Class<? extends NBT> type;
	private final Class classType;

	private TagType(int id, Class<? extends NBT> type, Class classType)  {
		this.id = (byte) id;
		this.type = type;
		this.classType = classType;
	}

	public byte getId() {
		return id;
	}

	public Class<? extends NBT> getType() {
		return type;
	}

	private Class getClassType() {
		return classType;
	}

	public static NBT getTag(Object obj) throws IOException {
		for (Class clazz : classTypes.keySet()) {
			if (clazz.isInstance(obj)) {
				TagType type = classTypes.get(clazz);
				Constructor<? extends NBT> constructor = null;
				try {
					constructor = type.getType().getConstructor(type.getClassType());
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
				try {
					assert constructor != null;
					return constructor.newInstance(obj);
				} catch (Exception e) {
					throw new IOException("Invalid NBT Type: " + type.getType().getSimpleName());
				}
			}
		}
		throw new IllegalArgumentException("Unknown tag for object " + obj);
	}

	public static TagType valueOf(byte id) {
		return types.get(id);
	}

	static {
		classTypes = new HashMap<>();
		types = new HashMap<>();

		for (TagType tagType : TagType.values()) {
			classTypes.put(tagType.getClassType(), tagType);
			types.put(tagType.getId(), tagType);
		}
	}

}