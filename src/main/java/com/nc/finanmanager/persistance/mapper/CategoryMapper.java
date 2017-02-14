package com.nc.finanmanager.persistance.mapper;

import com.nc.finanmanager.persistance.entity.Category;
import com.nc.finanmanager.persistance.entity.Item;
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
          @Result(property = "cid", column = "category_id"),
          @Result(property = "categoryName", column = "name")
        })
    @Insert("INSERT into categories(name) VALUES(#{categoryName})")
    void insertCategory(Category category);

        @Results({
          @Result(property = "cid", column = "category_id"),
          @Result(property = "categoryName", column = "name"),
          @Result(property ="items", javaType = List.class, column = "category_id", many = @Many("getItems"))
        })
    @Select("SELECT * from categories where category_id = #{cid}")
    Category selectCategory(int id);
    
    
    @Results({
          @Result(property = "cid", column = "category_id"),
          @Result(property = "categoryName", column = "name"),
          @Result(property ="items", javaType = List.class, column = "category_id", many = @Many(select = "getItems"))
        })
    @Select("SELECT category_id, name from categories")
    List<Category> selectAllCategories();

    @Update("UPDATE categories SET name =#{categoryName} WHERE category_id=#{cid}")
    void updateCategory(Category category);
    
    @Delete("DELETE FROM items where item_id = #{itemId}")
    void deleteCategory(Category item);
    
    
    @Select("SELECT * FROM items WHERE category_id=#{cid}")
    List<Item> getItems(int cid);
}
