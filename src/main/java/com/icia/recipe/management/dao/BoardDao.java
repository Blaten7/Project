package com.icia.recipe.management.dao;

import com.icia.recipe.management.dto.BoardDto;
import com.icia.recipe.management.dto.FoodItemDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface BoardDao {

    List<BoardDto> getRecipeList();

    List<BoardDto> getCgName();

    List<BoardDto> getFoodItemBigCg();

    List<BoardDto> getRecipeBigCg();

    List<BoardDto> getFoodItemMidCg(String cg);

    List<BoardDto> getRecipeMidCg(String cgNum);

    List<BoardDto> getFoodItemSmCg(String cg);

    List<BoardDto> getRecipeSmCg(String cgNum);

    List<BoardDto> getBoardTrade();

    boolean deleteCategory(String name, String cg);

    boolean insertFoodItem(String fiCode, String fiExDate, String fiCounts,
                                     String fiBigCg, String fiMidCg,
                                     String fiPrice, String fiContents, String fiTitle,
                                    String fiVolume, String fiOrigin, String fiCal, String fiSave);

    List<FoodItemDto> getFoodItemList();

    List<FoodItemDto> getSortedFoodItemList(String param, String sort);

    boolean insertFoodItemImg(String fiFile, String path, String role, String fiCode);

    boolean addAllCg(String cgName, String cgNum);

    boolean deleteFoodItemList(ArrayList deleteKey);
}
