package org.smartboot.socket.mqtt.message;

import org.smartboot.socket.mqtt.MqttFixedHeader;
import org.smartboot.socket.mqtt.MqttQoS;
import org.smartboot.socket.util.BufferUtils;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 三刀
 * @version V1.0 , 2018/4/22
 */
public class MqttSubscribeMessage extends MessageIdVariableHeaderMessage {

    private MqttSubscribePayload mqttSubscribePayload;

    public MqttSubscribeMessage(MqttFixedHeader mqttFixedHeader) {
        super(mqttFixedHeader);
    }

    @Override
    public void decodePlayLoad(ByteBuffer buffer) {
        final List<MqttTopicSubscription> subscribeTopics = new ArrayList<MqttTopicSubscription>();
        while (buffer.hasRemaining()) {
            final String decodedTopicName = decodeString(buffer);
            int qos = BufferUtils.readUnsignedByte(buffer) & 0x03;
            subscribeTopics.add(new MqttTopicSubscription(decodedTopicName, MqttQoS.valueOf(qos)));
        }
        this.mqttSubscribePayload = new MqttSubscribePayload(subscribeTopics);
    }

    final class MqttSubscribePayload {

        private final List<MqttTopicSubscription> topicSubscriptions;

        public MqttSubscribePayload(List<MqttTopicSubscription> topicSubscriptions) {
            this.topicSubscriptions = Collections.unmodifiableList(topicSubscriptions);
        }

        public List<MqttTopicSubscription> topicSubscriptions() {
            return topicSubscriptions;
        }

    }

    final class MqttTopicSubscription {

        private final String topicFilter;
        private final MqttQoS qualityOfService;

        public MqttTopicSubscription(String topicFilter, MqttQoS qualityOfService) {
            this.topicFilter = topicFilter;
            this.qualityOfService = qualityOfService;
        }

        public String topicName() {
            return topicFilter;
        }

        public MqttQoS qualityOfService() {
            return qualityOfService;
        }


    }
}
