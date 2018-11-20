package edu.ptit.quynhhtn.service;

import edu.ptit.quynhhtn.dao.CategoryDAO;
import edu.ptit.quynhhtn.dao.StoreConfigDAO;
import edu.ptit.quynhhtn.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StoreConfigUpdateService {
    @Autowired
    private StoreConfigDAO configDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private Category rootCategory;

    @Autowired
    private Map<String, String> storeConfig;

    private boolean refreshing = false;

    //Async --> Yêu cầu chạy hàm này trong 1 thread khác => CChỉ đc thực hiện trong @Service
    @Async
    public void refreshIfNeeded() {
        if(refreshing) return;
        refreshing = true;
        Map<String, String> currentConfig = new HashMap<>();
        configDAO.findAll().forEach(config->{
            currentConfig.put(config.getConfigKey(),config.getConfigValue());
        });

        Category currentCategory = categoryDAO.findCategoryByName("root").orElse(new Category(1L,"root"));
        if(!currentConfig.equals(storeConfig)) {
            currentConfig.entrySet().forEach(entry ->{
                if(storeConfig.get(entry.getKey()) == null) storeConfig.put(entry.getKey(), entry.getValue());
                else storeConfig.replace(entry.getKey(), entry.getValue());
            });
        }
        if(!currentCategory.equals(rootCategory)) {
            rootCategory.setCategoryId(currentCategory.getCategoryId());
            rootCategory.setName(currentCategory.getName());
            rootCategory.setDescription(currentCategory.getDescription());
            rootCategory.getSubCategories().removeAll(rootCategory.getSubCategories());
            rootCategory.getSubCategories().addAll(currentCategory.getSubCategories());
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
        refreshing = false;
    }
}
