package com.sgm.liteapp.commons.utils;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class CacheUtils {

	private static RedisTemplate redis;

	@Autowired
	@Qualifier("redisTemplate")
	public void setRedisTemplate(RedisTemplate redis) {
		redis.setKeySerializer(new StringRedisSerializer());// Key只能为String类型
		redis.setValueSerializer(new ObjectRedisSerializer());
		CacheUtils.redis = redis;
	}

	public static void set(String key, Object value) {
		redis.opsForValue().set(key, value);
	}

	public static void set(String key, Object value, long expires) {
		redis.opsForValue().set(key, value, expires, TimeUnit.SECONDS);
	}

	public static Object get(String key) {
		return redis.opsForValue().get(key);
	}

	public static void expire(String key, long expires) {
		redis.expire(key, expires, TimeUnit.SECONDS);
	}

	public static void remove(String key) {
		redis.delete(key);
	}

	private static final class ObjectRedisSerializer implements RedisSerializer<Serializable> {

		@Override
		public byte[] serialize(Serializable serializable) throws SerializationException {
			if (serializable == null) {
				return null;
			}
			return org.springframework.util.SerializationUtils.serialize(serializable);
		}

		@Override
		public Serializable deserialize(byte[] bytes) throws SerializationException {
			if (bytes == null) {
				return null;
			}
			return (Serializable) org.springframework.util.SerializationUtils.deserialize(bytes);
		}

	}
}
