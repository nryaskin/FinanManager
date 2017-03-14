package com.nc.finanmanager.persistance.mapper;

import com.nc.finanmanager.persistance.entity.Category;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface CategoryMapper {
    
    @Results({
        @Result(property = "categoryId", column="id_category")
    })
    @Select("SELECT * FROM category WHERE id_category = #{categoryId}")
    Category selectCategory(String categoryId);
    
    @Results({
        @Result(property = "categoryId", column="id_category")
    })
    
    
    @Select("SELECT  * FROM category")
    List<Category> selectAllCategories();
    
    @Insert("INSERT INTO category(id_category) VALUES(#{categoryId})")
    void insertCategory(Category category);
    
    @Update("UPDATE category SET id_category = #{categoryId}")
    void updateCategory(Category category);
    
    @Delete("DELETE FROM category WHERE id_category = #{categoryId}")
    void deleteCategory(Category category);
}