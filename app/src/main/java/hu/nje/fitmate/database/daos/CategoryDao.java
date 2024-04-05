package hu.nje.fitmate.database.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import hu.nje.fitmate.database.models.Category;

@Dao
public interface CategoryDao {

    @Insert
    public void insertCategory(Category category);

    @Query("SELECT * FROM Category where categoryID = :categoryID")
    public Category getCategoryById(int categoryID);

    @Query("SELECT * FROM Category")
    public List<Category> getCategorys();
}
