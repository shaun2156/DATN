package edu.ptit.quynhhtn.service;

import edu.ptit.quynhhtn.dao.*;
import edu.ptit.quynhhtn.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class TestDBInitService {
    @Autowired
    StoreConfigDAO configDAO;

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    CustomerDAO customerDAO;

    @Autowired
    FeaturedProductDAO featuredProductDAO;

    @Autowired
    ItemDAO itemDAO;

    @Autowired
    PersonDAO personDAO;

    @Transactional
    public void testDataInitialization() {
        initCategory();
        initItem();
    }

    private void initItem() {
        categoryDAO.findAll().forEach(category -> {
            if (category.getName().equals("root")) return;
            Random rand = new Random();
            int itemQty = rand.nextInt(10) + 5;
            for (int i = 1; i <= itemQty; i++) {
                String itemName = category.getName() + " kiểu " + i;
                String description = "Mô tả " + itemName + " đầy đủ. Dữ liệu thử nghiệm";
                String shortDescription = "Mô tả " + itemName + " ngắn gọn";
                double currentPrice = (rand.nextInt(90) + 10) * 1000d;
                double oldPrice = (rand.nextInt(20) + 1) * 1000d + currentPrice;
                new Item(itemName, description, shortDescription, currentPrice, oldPrice, category);
            }
        });

        itemDAO.findAll().forEach(item -> {
            Random rand = new Random();
            //Store images
            new ImageUrl(String.format("images/items/%02d.png", (rand.nextInt(41) + 1)), item);
            for (int i = 0; i < 3; i++) {
                if (rand.nextDouble() >= 0.5d)
                    new ImageUrl(String.format("images/items/%02d.png", (rand.nextInt(41) + 1)), item);
            }
            //Store itemDetails
            boolean outOfStockFlags = rand.nextDouble() < 0.4;
            String[] size = {"S", "L", "XL", "M"};
            String[] color = {"White", "Red", "Blue", "Black"};
            new ItemDetail("M", "Black", outOfStockFlags ? 0 : rand.nextInt(100), item);
            for (int i = 0; i < 3; i++) {
                if (rand.nextDouble() >= 0.5d)
                    new ItemDetail(size[rand.nextInt(3)], color[rand.nextInt(3)], outOfStockFlags ? 0 : rand.nextInt(100), item);

            }
            if (rand.nextDouble() < 0.08) {
                boolean featured = rand.nextDouble() < 0.5;
                item.setFeatured(featured);
                FeaturedProduct ftProduct = new FeaturedProduct(featured, item);
                featuredProductDAO.save(ftProduct);
            }
        });
    }

    private void initCategory() {
        Category root = new Category("root", "Root Category", (Category) null);
        Category shoes = new Category("Giày dép", "Danh mục giày dép", root);
        Category clothing = new Category("Quần áo", "Danh mục quần áo", root);
        Category accessories = new Category("Phụ kiện", "Danh mục phụ kiện", root);

        Category leatherShoes = new Category("Giày da", "Giày da category", shoes);
        Category sneaker = new Category("Giày thể thao", "Giày thể thao category", shoes);
        Category highHeels = new Category("Giày cao gót", "Giày cao gót category", shoes);

        Category menClothing = new Category("Thời trang Nam", "Men Clothing Category", clothing);
        Category womenClothing = new Category("Thời trang Nữ", "Women Clothing Category", clothing);
        Category shirtClothing = new Category("Áo thun", "Shirt Category", clothing);
        Category jeansClothing = new Category("Quần jeans", "Jeans Category", clothing);

        Category necklace = new Category("Dây chuyền", "Necklace Category", accessories);
        Category braclets = new Category("Lắc tay", "braclets Category", accessories);
        Category ring = new Category("Nhẫn", "ring Category", accessories);
        Category amulets = new Category("Phụ kiện khác", "amulets Category", accessories);
        categoryDAO.save(root);
    }
}
