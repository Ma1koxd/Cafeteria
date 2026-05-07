package ru.ma1ko.cafeteria.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("ru.ma1ko.cafeteria")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {
}
