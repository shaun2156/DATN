package edu.ptit.quynhhtn.entity;

import javax.persistence.*;

@Entity
public class StoreConfig extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long configId;

    @Column(unique = true, length = 120)
    private String configKey;

    private String configValue;

    public StoreConfig() {
    }

    public StoreConfig(String configKey, String value) {
        this.configKey = configKey;
        this.configValue = value;
    }

    public Long getConfigId() {
        return configId;
    }

    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String value) {
        this.configValue = value;
    }
}
