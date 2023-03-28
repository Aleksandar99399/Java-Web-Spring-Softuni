package bg.softuni.aop.sla;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.List;


@Configuration
@ConfigurationProperties(prefix = "slos-config")
public class SLOsConfig {

    List<SLOConfig>slos=new LinkedList<>();

    public List<SLOConfig> getSlos() {
        return slos;
    }

    public SLOsConfig setSlos(List<SLOConfig> slos) {
        this.slos = slos;
        return this;
    }

    public static class  SLOConfig{
        private String id;
        private int threshold;

        public String getId() {
            return id;
        }

        public SLOConfig setId(String id) {
            this.id = id;
            return this;
        }

        public int getThreshold() {
            return threshold;
        }

        public SLOConfig setThreshold(int threshold) {
            this.threshold = threshold;
            return this;
        }

        @Override
        public String toString() {
            return "SLOConfig{" +
                    "id='" + id + '\'' +
                    ", threshold=" + threshold +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SLOsConfig{" +
                "slos=" + slos +
                '}';
    }
}
