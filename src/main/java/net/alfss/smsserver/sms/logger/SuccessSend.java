package net.alfss.smsserver.sms.logger;

import net.alfss.smsserver.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: alfss
 * Date: 28.10.13
 * Time: 18:04
 */
public class SuccessSend {

    final Logger logger = (Logger) LoggerFactory.getLogger(SuccessSend.class);

    public void writeLog(Message message) {
        logger.info(message.toString());
    }
}
