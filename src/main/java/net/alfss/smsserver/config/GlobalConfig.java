package net.alfss.smsserver.config;

import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
/**
 * User: alfss
 * Date: 01.10.13
 * Time: 16:22
 */
public class GlobalConfig {
    //rabbit
    private String rabbitHost;
    private int    rabbitPort;
    private String rabbitVhost;
    private String rabbitQueue;
    private String rabbitUser;
    private String rabbitPassword;
    //redis
    private int redisMaxPool;
    private String redisHost;
    private int redisPort;
    private int redisTimeOut;
    private String redisPassword;
    private int redisDatabase;
    //jetty
    private int jettyPort;
    private String jettyAddress;
    private int jettyMinPool;
    private int jettyMaxPool;
    private int jettyOutputBufferSize;
    private int jettyRequestHeaderSize;
    private int jettyResponseHeaderSize;


    //server config
    private Hashtable<String, ChannelConfig> channelConfigList = new Hashtable<>();
    private Hashtable<String, UserConfig> userConfigList = new Hashtable<>();


    public GlobalConfig(XMLConfiguration xml_config) {

        //sms_servers
        List<HierarchicalConfiguration> xmlChannelConfigList = xml_config.configurationsAt("sms-servers.server");
        for (HierarchicalConfiguration xmlChannelConfig: xmlChannelConfigList) {
            ChannelConfig channelConfig = new ChannelConfig(xmlChannelConfig);
            channelConfigList.put(channelConfig.getChannel(), channelConfig);
        }

        //channel_users
        xmlChannelConfigList = xml_config.configurationsAt("channel-users.user");
        for (HierarchicalConfiguration xmlChannelConfig: xmlChannelConfigList) {
            UserConfig userConfig  = new UserConfig(xmlChannelConfig);
            userConfigList.put(userConfig.getUserName(), userConfig);
        }

        Map<String, String> env = System.getenv();

        //rabbit
        setRabbitHost(env.containsKey("SMSSERVER_RABBIT_HOST") ? env.get("SMSSERVER_RABBIT_HOST")
                : xml_config.getString("rabbit-host", "localhost"));
        setRabbitPort(env.containsKey("SMSSERVER_RABBIT_PORT") ? Integer.parseInt(env.get("SMSSERVER_RABBIT_HOST"))
                : xml_config.getInt("rabbit-port", 5672));
        setRabbitVhost(env.containsKey("SMSSERVER_RABBIT_VHOST") ? env.get("SMSSERVER_RABBIT_VHOST")
                : xml_config.getString("rabbit-vhost", "/smsserver"));
        setRabbitQueue(env.containsKey("SMSSERVER_RABBIT_QUEUE") ? env.get("SMSSERVER_RABBIT_QUEUE")
                : xml_config.getString("rabbit-queue", "message"));
        setRabbitUser(env.containsKey("SMSSERVER_RABBIT_USER") ? env.get("SMSSERVER_RABBIT_USER")
                : xml_config.getString("rabbit-user", "guest"));
        setRabbitPassword(env.containsKey("SMSSERVER_RABBIT_PASSWORD") ? env.get("SMSSERVER_RABBIT_PASSWORD")
                : xml_config.getString("rabbit-password", "guest"));
        //redis
        setRedisPassword(env.containsKey("SMSSERVER_REDIS_PASSWORD") ? env.get("SMSSERVER_REDIS_PASSWORD")
                : xml_config.getString("redis-password", null));
        setRedisMaxPool(env.containsKey("SMSSERVER_REDIS_POOL") ? Integer.parseInt(env.get("SMSSERVER_REDIS_POOL"))
                : xml_config.getInt("redis-max-pool", 12));
        setRedisTimeOut(env.containsKey("SMSSERVER_REDIS_TIMEOUT") ? Integer.parseInt(env.get("SMSSERVER_REDIS_TIMEOUT"))
                : xml_config.getInt("redis-timeout", 10));
        setRedisHost(env.containsKey("SMSSERVER_REDIS_HOST") ? env.get("SMSSERVER_REDIS_HOST")
                : xml_config.getString("redis-host", "localhost"));
        setRedisPort(env.containsKey("SMSSERVER_REDIS_POST") ? Integer.parseInt(env.get("SMSSERVER_REDIS_POST"))
                : xml_config.getInt("redis-port", 6379));
        setRedisDatabase(env.containsKey("SMSSERVER_REDIS_DB") ? Integer.parseInt(env.get("SMSSERVER_REDIS_DB"))
                : xml_config.getInt("redis-db", 0));
        //jetty
        setJettyAddress(env.containsKey("SMSSERVER_HTTP_ADRESS") ? env.get("SMSSERVER_HTTP_ADRESS")
                : xml_config.getString("http-address", "0.0.0.0"));
        setJettyPort(env.containsKey("SMSSERVER_HTTP_PORT") ? Integer.parseInt(env.get("SMSSERVER_HTTP_PORT"))
                : xml_config.getInt("http-port", 13003));
        setJettyMinPool(env.containsKey("SMSSERVER_HTTP_MIN_POOL") ? Integer.parseInt(env.get("SMSSERVER_HTTP_MIN_POOL"))
                : xml_config.getInt("http-min-pool", 1));
        setJettyMaxPool(env.containsKey("SMSSERVER_HTTP_MAX_POOL") ? Integer.parseInt(env.get("SMSSERVER_HTTP_MAX_POOL"))
                : xml_config.getInt("http-max-pool", 10));
        setJettyOutputBufferSize(env.containsKey("SMSSERVER_HTTP_OUT_BUFFER") ? Integer.parseInt(env.get("SMSSERVER_HTTP_OUT_BUFFER"))
                : xml_config.getInt("http-out-buffer", 32768));
        setJettyRequestHeaderSize(env.containsKey("SMSSERVER_HTTP_REQ_HEADER") ? Integer.parseInt(env.get("SMSSERVER_HTTP_REQ_HEADER"))
                : xml_config.getInt("http-req-header", 8192));
        setJettyResponseHeaderSize(env.containsKey("SMSSERVER_HTTP_RES_HEADER") ? Integer.parseInt(env.get("SMSSERVER_HTTP_RES_HEADER"))
                : xml_config.getInt("http-res-header", 8192));

    }

    public String getRabbitHost() {
        return rabbitHost;
    }

    public void setRabbitHost(String rabbitHost) {
        this.rabbitHost = rabbitHost;
    }

    public int getRabbitPort() {
        return rabbitPort;
    }

    public void setRabbitPort(int rabbitPort) {
        this.rabbitPort = rabbitPort;
    }

    public String getRabbitVhost() {
        return rabbitVhost;
    }

    public void setRabbitVhost(String rabbitVhost) {
        this.rabbitVhost = rabbitVhost;
    }

    public String getRabbitQueue() {
        return rabbitQueue;
    }

    public void setRabbitQueue(String rabbitQueue) {
        this.rabbitQueue = rabbitQueue;
    }

    public String getRabbitUser() {
        return rabbitUser;
    }

    public void setRabbitUser(String rabbitUser) {
        this.rabbitUser = rabbitUser;
    }

    public String getRabbitPassword() {
        return rabbitPassword;
    }

    public void setRabbitPassword(String rabbitPassword) {
        this.rabbitPassword = rabbitPassword;
    }

    public int getRedisMaxPool() {
        return redisMaxPool;
    }

    public void setRedisMaxPool(int redisMaxPool) {
        this.redisMaxPool = redisMaxPool;
    }

    public String getRedisHost() {
        return redisHost;
    }

    public void setRedisHost(String redisHost) {
        this.redisHost = redisHost;
    }

    public int getRedisPort() {
        return redisPort;
    }

    public void setRedisPort(int redisPort) {
        this.redisPort = redisPort;
    }

    public int getRedisTimeOut() {
        return redisTimeOut;
    }

    public void setRedisTimeOut(int redisTimeOut) {
        this.redisTimeOut = redisTimeOut;
    }

    public String getRedisPassword() {
        return redisPassword;
    }

    public void setRedisPassword(String redisPassword) {
        this.redisPassword = redisPassword;
    }

    public int getRedisDatabase() {
        return redisDatabase;
    }

    public void setRedisDatabase(int redisDatabase) {
        this.redisDatabase = redisDatabase;
    }

    public List<String> getChannelList() {
        ArrayList <String>channelList = new ArrayList<>();
        for (String key:channelConfigList.keySet()) {
            channelList.add(key);
        }
        return channelList;
    }

    public ChannelConfig getChennelConfig(String channelName) {
        return channelConfigList.get(channelName);
    }

    public List<String> getUserList() {
        ArrayList <String>userList = new ArrayList<>();
        for (String key:userConfigList.keySet()) {
            userList.add(key);
        }
        return userList;
    }

    public UserConfig getUserConfig(String userName) {
        return userConfigList.get(userName);
    }

    public int getJettyPort() {
        return jettyPort;
    }

    public void setJettyPort(int jettyPort) {
        this.jettyPort = jettyPort;
    }

    public String getJettyAddress() {
        return jettyAddress;
    }

    public void setJettyAddress(String jettyAddress) {
        this.jettyAddress = jettyAddress;
    }

    public int getJettyMinPool() {
        return jettyMinPool;
    }

    public void setJettyMinPool(int jettyMinPool) {
        this.jettyMinPool = jettyMinPool;
    }

    public int getJettyMaxPool() {
        return jettyMaxPool;
    }

    public void setJettyMaxPool(int jettyMaxPool) {
        this.jettyMaxPool = jettyMaxPool;
    }

    public int getJettyOutputBufferSize() {
        return jettyOutputBufferSize;
    }

    public void setJettyOutputBufferSize(int jettyOutputBufferSize) {
        this.jettyOutputBufferSize = jettyOutputBufferSize;
    }

    public int getJettyRequestHeaderSize() {
        return jettyRequestHeaderSize;
    }

    public void setJettyRequestHeaderSize(int jettyRequestHeaderSize) {
        this.jettyRequestHeaderSize = jettyRequestHeaderSize;
    }

    public int getJettyResponseHeaderSize() {
        return jettyResponseHeaderSize;
    }

    public void setJettyResponseHeaderSize(int jettyResponseHeaderSize) {
        this.jettyResponseHeaderSize = jettyResponseHeaderSize;
    }
}
