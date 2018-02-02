package com.ifinance.tool;

import java.util.Properties;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.OnExceptionContext;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.SendCallback;
import com.aliyun.openservices.ons.api.SendResult;

public class AliyunMQmanager {

	private static AliyunMQmanager	uniqueInstance	= null;

	private Producer				producer;

	private AliyunMQmanager() {

		Properties properties = new Properties();
		properties.put(PropertyKeyConst.ProducerId, "PID_iite_producer");
		properties.put(PropertyKeyConst.AccessKey, "XXdfRVYUuKQrJYBe");
		properties.put(PropertyKeyConst.SecretKey, "7AiZWObytHZOFMwqk7o5ju1liTlCqN");
		properties.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, "3000");// 设置发送超时时间，单位毫秒
	    producer = ONSFactory.createProducer(properties);

		// 在发送消息前，必须调用start方法来启动Producer，只需调用一次即可。
		producer.start();
	}

	public static AliyunMQmanager getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new AliyunMQmanager();
		}
		return uniqueInstance;
	}

	public void sendMQ_checkYellowImage(String key, String body) {
		sendMQ("checkImage", key, body);
	}

	public void sendMQ_push(String key, String body) {
		sendMQ("push", key, body);
	}

	public void sendMQ(String TAG, String key, String body) {
		try {
			Message msg = new Message(
					// Message Topic
					"iite_MQ",
					// Tag,可理解为Gmail中的标签，对消息进行再归类，方便Consumer指定过滤条件在MQ服务器过滤
					TAG,
					// Message
					// Body，任何二进制形式的数据，MQ不做任何干预，需要Producer与Consumer协商好一致的序列化和反序列化方式
					body.getBytes());
			// 设置代表消息的业务关键属性，请尽可能全局唯一。以方便您在无法正常收到消息情况下，可通过MQ控制台查询消息并补发。
			// 注意：不设置也不会影响消息正常收发
			msg.setKey(key);
			// 异步发送消息, 发送结果通过callback返回给客户端。

			producer.sendAsync(msg, new SendCallback() {
				
				public void onSuccess(final SendResult sendResult) {
					// 消费发送成功
				}

				
				public void onException(OnExceptionContext context) {
					// 消息发送失败
				}
			});

		} catch (Exception e) {
		}
	}

	public void close() {
		producer.shutdown();
	}
}
