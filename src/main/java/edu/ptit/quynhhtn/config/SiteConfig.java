package edu.ptit.quynhhtn.config;

import edu.ptit.quynhhtn.dao.CategoryDAO;
import edu.ptit.quynhhtn.dao.PersonDAO;
import edu.ptit.quynhhtn.dao.StoreConfigDAO;
import edu.ptit.quynhhtn.entity.Category;
import edu.ptit.quynhhtn.entity.Person;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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

    private Person loggedInUser;

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

    public Person getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!StringUtils.isEmpty(auth.getName())) {
            String name = auth.getName();
            loggedInUser = personDAO.findPersonByUsername(name).orElse(null);
            setLoggedInUser(loggedInUser);
        } else {
            loggedInUser = null;
        }
        return loggedInUser;
    }

    public void setLoggedInUser(Person loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}