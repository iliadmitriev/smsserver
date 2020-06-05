package net.alfss.smsserver.http.dao;

import net.alfss.smsserver.config.ChannelConfig;
import net.alfss.smsserver.config.GlobalConfig;
import net.alfss.smsserver.config.SharedConfig;
import net.alfss.smsserver.config.UserConfig;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * User: alfss
 * Date: 17.10.13
 * Time: 16:58
 */
@Repository
public class ConfigDao {
    private GlobalConfig globalConfig;

    public ConfigDao() {
        this.globalConfig = SharedConfig.getGlobalConfig();
    }

    public List<String> getAllChannelName() {
        ArrayList<String> listChannelName = new ArrayList<>();
        for (String channelName: globalConfig.getChannelList()) {
            listChannelName.add(channelName);
        }
        return  listChannelName;
    }

    public ChannelConfig getChannelConfig(String channelName) {
        return globalConfig.getChennelConfig(channelName);
    }

    public List<String> getAllUserName() {
        ArrayList<String> userNameList = new ArrayList<>();
        for (String userName: globalConfig.getUserList()) {
            userNameList.add(userName);
        }
        return userNameList;
    }

    public UserConfig getUserConfig(String userName) {
        return globalConfig.getUserConfig(userName);
    }
}
