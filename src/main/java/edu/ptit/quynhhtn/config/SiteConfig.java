package edu.ptit.quynhhtn.config;

import edu.ptit.quynhhtn.dao.CategoryDAO;
import edu.ptit.quynhhtn.dao.PersonDAO;
import edu.ptit.quynhhtn.dao.StoreConfigDAO;
import edu.ptit.quynhhtn.entity.Category;
import edu.ptit.quynhhtn.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class SiteConfig {
    private static SiteConfig mInstance;

    public static SiteConfig getInstance() {
        return mInstance;
    }

    public SiteConfig() {
        mInstance = this;
    }

    @Autowired
    private StoreConfigDAO configDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private PersonDAO personDAO;

    @Bean("storeConfig")
    public Map<String, String> storeConfig() {
        Map<String, String> configs = new HashMap<>();
        configDAO.findAll().forEach(config -> {
            configs.put(config.getConfigKey(), config.getConfigValue());
        });
        return configs;
    }

    @Bean
    public Category rootCategory() {
        return categoryDAO.findCategoryByName("root").orElse(new Category(1L, "root"));
    }

    @Bean
    public Person adminPerson(){
        return personDAO.findPersonByUsername("admin").orElse(null);
    }

}