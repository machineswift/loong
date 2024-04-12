//package com.machine.test.hbase.config;
//
//import org.apache.hadoop.hbase.HBaseConfiguration;
//import org.apache.hadoop.hbase.client.Connection;
//import org.apache.hadoop.hbase.client.ConnectionFactory;
//import org.apache.phoenix.jdbc.PhoenixDriver;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class HBaseConfig {
//
//    @Bean
//    public org.apache.hadoop.conf.Configuration hbaseConfig() {
//        org.apache.hadoop.conf.Configuration config = HBaseConfiguration.create();
//        config.set("hbase.zookeeper.quorum", "localhost"); // 替换为你的ZooKeeper集群地址
//        config.set("hbase.zookeeper.property.clientPort", "2181"); // 替换为你的ZooKeeper客户端端口
//        // 添加任何其他所需的HBase配置属性
//
//        return config;
//    }
//
//    @Bean
//    public Connection hbaseConnection(Configuration config) throws Exception {
//        PhoenixDriver.registerDriver();
//        return ConnectionFactory.createConnection(config);
//    }
//}