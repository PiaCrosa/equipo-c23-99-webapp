package c23_99_m_webapp.backend.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("/*")// Cambia esto a la URL de tu frontend verificar si se puede usar variable de entorno
                .allowedHeaders("Authorization", "Content-Type")
                .allowedMethods("GET","POST","PUT","DELETE");
    }
}
