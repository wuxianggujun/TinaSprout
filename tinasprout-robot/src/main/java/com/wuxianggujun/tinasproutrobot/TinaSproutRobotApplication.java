package com.wuxianggujun.tinasproutrobot;

import com.wuxianggujun.tinasproutcore.EnableBot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBot
@SpringBootApplication
public class TinaSproutRobotApplication {

	public static void main(String[] args) {
		SpringApplication.run(TinaSproutRobotApplication.class, args);
	}

}
