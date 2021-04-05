
package io.project.clickhouse.app.brain.dto;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Generated("jsonschema2pojo")
@AllArgsConstructor
@NoArgsConstructor
public class TripModel implements Serializable
{

    @SerializedName("allModels")
    @Expose
    private List<AllModel> allModels = new ArrayList<>();
    @SerializedName("count")
    @Expose
    private Integer count;
    
    private final static long serialVersionUID = -1711732594156554382L;

    public List<AllModel> getAllModels() {
        return allModels;
    }

    public void setAllModels(List<AllModel> allModels) {
        this.allModels = allModels;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
