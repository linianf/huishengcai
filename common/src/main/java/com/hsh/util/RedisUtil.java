package com.hsh.util;



import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

/**
 * Redis工具类
 * 
 * @author lnf
 * 
 */
public class RedisUtil {

	private static Log log = LogFactory.getLog(RedisUtil.class);

	private JedisPool pool;

	public void hset(String key, String field, String value) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.hset(key, field, value);
		} catch (Exception e) {
			log.error(e);
			if (jedis != null) {
				pool.returnBrokenResource(jedis);
			}
			throw new JedisException(e);

		} finally {
			if (pool != null) {
				pool.returnResource(jedis);
			}
		}
	}

	public void del(String... key) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.del(key);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.toString());
			if (jedis != null) {
				pool.returnBrokenResource(jedis);
			}
			throw new JedisException(e);
		} finally {
			if (pool != null) {
				pool.returnResource(jedis);
			}
		}
	}

	
	public void lpush(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.lpush(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.toString());
			if (jedis != null) {
				pool.returnBrokenResource(jedis);
			}
			throw new JedisException(e);
		} finally {
			if (pool != null) {
				pool.returnResource(jedis);
			}
		}
	}
	
	public String rpop(String key) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.rpop(key);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.toString());
			if (jedis != null) {
				pool.returnBrokenResource(jedis);
			}
			throw new JedisException(e);
		} finally {
			if (pool != null) {
				pool.returnResource(jedis);
			}
		}
	}


	public long incr(String key, int count) {
		Jedis jedis = null;
		long sequnce = 0;
		try {
			jedis = pool.getResource();
			for (int i=0;i<count;i++) {
				sequnce = jedis.incr(key);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.toString());
			if (jedis != null) {
				pool.returnBrokenResource(jedis);
			}
			throw new JedisException(e);
		} finally {
			if (pool != null) {
				pool.returnResource(jedis);
			}
		}
		return sequnce;
	}

	public void hdel(String key, String... fields) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.hdel(key, fields);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.toString());
			if (jedis != null) {
				pool.returnBrokenResource(jedis);
			}
			throw new JedisException(e);
		} finally {
			if (pool != null) {
				pool.returnResource(jedis);
			}
		}
	}

	public JedisPool getPool() {
		return pool;
	}

	public void setPool(JedisPool pool) {
		this.pool = pool;
	}

	/**
	 * 向key赋值
	 *
	 * @param key
	 * @param value
	 */
	public void set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.set(key, value);

		} catch (Exception e) {
			log.error(e);
			if (jedis != null) {
				pool.returnBrokenResource(jedis);
			}
			throw new JedisException(e);
		} finally {
			if (pool != null) {
				pool.returnResource(jedis);
			}
		}
	}

	/**
	 * 获取key的值
	 *
	 * @param key
	 * @return
	 */

	public String get(String key) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			String value = jedis.get(key);
			return value;
		} catch (Exception e) {
			log.error(e);
			if (jedis != null) {
				pool.returnBrokenResource(jedis);
			}
			throw new JedisException(e);
		} finally {
			if (pool != null) {
				pool.returnResource(jedis);
			}
		}
	}

	/**
	 * 获取key的值
	 * 
	 * @param key
	 * @return
	 */
	/*
	 * public byte[] getBytes(byte[] key) { try { shardedJedis =
	 * pool.getResource(); byte[] value = shardedJedis.get(key);
	 * pool.returnResource(shardedJedis); return value; } catch (Exception e) {
	 * e.printStackTrace(); if (shardedJedis != null) {
	 * pool.returnBrokenResource(shardedJedis); } throw new JedisException(e); }
	 * }
	 */

	/**
	 * 将多个field - value(域-值)对设置到哈希表key中。
	 * 
	 * @param key
	 * @param map
	 */
	public void hmset(String key, Map<String, String> map) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.hmset(key, map);
		} catch (Exception e) {
			log.error(e);
			if (jedis != null) {
				pool.returnBrokenResource(jedis);
			}
			throw new JedisException(e);
		} finally {
			if (pool != null) {
				pool.returnResource(jedis);
			}
		}
	}

	/**
	 * 将多个field - value(域-值)对设置到哈希表key中。
	 * 
	 * @param key
	 * @param map
	 */
	public List<String> hmget(String key, String... fields) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.hmget(key, fields);
		} catch (Exception e) {
			log.error(e);
			if (jedis != null) {
				pool.returnBrokenResource(jedis);
			}
			throw new JedisException(e);
		} finally {
			if (pool != null) {
				pool.returnResource(jedis);
			}
		}
	}

	/**
	 * 给key赋值，并生命周期设置为seconds
	 * 
	 * @param key
	 * @param seconds
	 *            生命周期 秒为单位
	 * @param value
	 */

	public void setex(String key, int seconds, String value) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.setex(key, seconds, value);
		} catch (Exception e) {
			log.error(e);
			if (jedis != null) {
				pool.returnBrokenResource(jedis);
			}
			throw new JedisException(e);
		} finally {
			if (pool != null) {
				pool.returnResource(jedis);
			}
		}
	}

	/**
	 * 为给定key设置生命周期
	 * 
	 * @param key
	 * @param seconds
	 *            生命周期 秒为单位
	 */
	public void expire(String key, int seconds) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.expire(key, seconds);
		} catch (Exception e) {
			log.error(e);
			if (jedis != null) {
				pool.returnBrokenResource(jedis);
			}
			throw new JedisException(e);
		} finally {
			if (pool != null) {
				pool.returnResource(jedis);
			}
		}
	}

	/**
	 * 从哈希表key中获取field的value
	 * 
	 * @param key
	 * @param field
	 */

	public String hget(String key, String field) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			String value = jedis.hget(key, field);
			return value;
		} catch (Exception e) {
			log.error(e);
			if (jedis != null) {
				pool.returnBrokenResource(jedis);
			}
			throw new JedisException(e);
		} finally {
			if (pool != null) {
				pool.returnResource(jedis);
			}
		}
	}

	/**
	 * 
	 * @param key
	 * @param field
	 * @param dbIndex
	 * @return
	 */
	public String hget(String key, String field, int dbIndex) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.select(dbIndex);
			String value = jedis.hget(key, field);
			return value;
		} catch (Exception e) {
			log.error(e);
			if (jedis != null) {
				pool.returnBrokenResource(jedis);
			}
			throw new JedisException(e);
		} finally {
			if (pool != null) {
				pool.returnResource(jedis);
			}
		}
	}
	
	/**
	 * 从哈希表key中获取field的value MAP
	 * 
	 * @param key
	 */

	public Map<String,String> hget(String key) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			Map<String,String> value = jedis.hgetAll(key);
			return value;
		} catch (Exception e) {
			log.error(e);
			if (jedis != null) {
				pool.returnBrokenResource(jedis);
			}
			throw new JedisException(e);
		} finally {
			if (pool != null) {
				pool.returnResource(jedis);
			}
		}
	}
	
}