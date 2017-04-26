package database.mongodb.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAwardRecord {

    /**
     * clearTag,标签
     */
    private Long clearTag;

    /**
     * 额外信息
     */
    private String extra;

    /**
     * 产品Id
     */
    private int productId;

    /**
     * winningTime,中奖时间
     */
    private Date winningTime;

    private Integer awardId;

    public Long getClearTag() {
        return clearTag;
    }

    public void setClearTag(Long clearTag) {
        this.clearTag = clearTag;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Date getWinningTime() {
        return winningTime;
    }

    public void setWinningTime(Date winningTime) {
        this.winningTime = winningTime;
    }

    public Integer getAwardId() {
        return awardId;
    }

    public void setAwardId(Integer awardId) {
        this.awardId = awardId;
    }
}
