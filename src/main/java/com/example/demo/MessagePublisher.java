package com.example.demo;

import java.util.Date;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessagePublisher {
	
	@Autowired
	private RabbitTemplate template;

	@PostMapping("/publish")
	public String publishMessage(@RequestBody CustomMessage msg) {
		msg.setMessageId(UUID.randomUUID().toString());
		msg.setMessageDate(new Date());
		
		template.convertAndSend(MQConfig.QUEUE_EXCHANGE, MQConfig.QUEUE_ROUTING,msg);
		
		return "Message Published..";
	}
	
}
