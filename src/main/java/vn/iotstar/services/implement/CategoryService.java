package vn.iotstar.services.implement;

import vn.iotstar.dao.ICategoryDao;
import vn.iotstar.dao.implement.CategoryDao;
import vn.iotstar.entity.Category;
import vn.iotstar.services.ICategoryService;

public class CategoryService implements ICategoryService{
	private ICategoryDao categoryDAO = new CategoryDao();

    @Override
    public Category getCategoryByName(String name) {
        return categoryDAO.findByName(name);
    }

    @Override
    public void addCategory(Category category) {
        // Kiểm tra category có tồn tại chưa
        Category existingCategory = categoryDAO.findByName(category.getName());
        if (existingCategory == null) {
            categoryDAO.save(category);
        }
    }
}
