package com.nc.finanmanager.persistance.mapper;

import com.nc.finanmanager.persistance.entity.Item;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ItemMapper {
    @Results({
          @Result(property = "itemId", column = "item_id"),
          @Result(property = "categoryId", column = "category_id"),
          @Result(property = "expenses", column = "expenses"),
          @Result(property = "date", column = "date")
        })
    @Insert("INSERT into items(category_id, expenses, date) VALUES(#{categoryId}, #{expenses}, #{date})")
    void insertItem(Item item);
    
    @Select("SELECT * from items where item_id = #{itemId}")
    Item selectItem(int id);
    
    @Select("SELECT * from items")
    List<Item> selectItems();
    
    @Update("UPDATE items SET category_id =#{categoryId},  expenses =#{expenses}, date =#{date} WHERE item_id=#{itemId})")
    void updateItem(Item item);
    
    @Delete("DELETE FROM items where item_id = #{itemId}")
    void deleteItem(Item item);
}
