package com.github.Kory33.minecartchestfilter.filter;

import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import com.github.Kory33.minecartchestfilter.util.NBTUtil;
import com.github.Kory33.minecartchestfilter.util.TagsUtil;

/**
 * Abstract class that represents generalized filter.
 * @author Kory33
 *
 */
public abstract class Filter {
    protected Filter(){}
    
    /**
     * Judges if the item is allowed through the filter
     * @param itemStack the itemstack to be filtered
     * @return if the item is allowed through the filter
     */
    public abstract boolean isItemAllowed(ItemStack itemStack);

    /**
     * Get an instance of appropriate filter for the given entity
     * @param entity entity that contains filtering tag
     * @return instance of extended Filter class
     * @throws IllegalArgumentException when the entity does not contain any filtering tag
     * @throws IllegalStateException when the entity is reported to contain unknown filtering tag
     */
    public static Filter getFilterInstance(Entity entity){
        String filterTag = NBTUtil.getFilteringTags(entity);
        
        if(filterTag == null){
            throw new IllegalArgumentException("Saving filtering tag: entity does not contain any filtering tag!");
        }
        
        switch(filterTag) {
            case TagsUtil.FILTERED_MINECART_TAG_FURNACE:
                return new FurnaceFilter();
            case TagsUtil.FILTERED_MINECART_TAG_FUEL:
                return new FuelFilter();
        }
        throw new IllegalStateException("Saving filtering tag: got unexpected tag!");
    }
}
