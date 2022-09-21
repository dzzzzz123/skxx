package org.example.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author qiufen
 * @date 2022-09-19
 */
public class JedisPoolUtil {
    // 连接redis连接池
    private static JedisPool pool = null;

    static{
        //加载配置文件
        InputStream in = JedisPoolUtil.class.getClassLoader().getResourceAsStream("redis.properties");
        //空的属性列表
        Properties pro = new Properties();
        try {
            //把redis.properties文件的内容加载到pro对象中
            pro.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //java连接redis
        //获得连接池子对象
        //获得连接池配置对象，设置配置项
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(Integer.parseInt(pro.get("redis.maxIdle").toString()));//最大闲置个数
        poolConfig.setMinIdle(Integer.parseInt(pro.get("redis.minIdle").toString()));//最小闲置个数
        poolConfig.setMaxTotal(Integer.parseInt(pro.get("redis.maxTotal").toString()));//最大连接数
        pool = new JedisPool(poolConfig,pro.getProperty("redis.url") ,
                Integer.parseInt(pro.get("redis.port").toString()));
    }

    //获得jedis资源的方法
    public static Jedis getJedis(){
        return pool.getResource();
    }
}
