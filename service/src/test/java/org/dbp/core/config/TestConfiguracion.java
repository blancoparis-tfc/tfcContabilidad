package org.dbp.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"org.dbp.service","org.dbp.dao"})
@Import({TestJpaConfiguration.class})
public class TestConfiguracion {

}
