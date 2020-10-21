package equipmgnt;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Approval_table")
public class Approval {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long equipmentId;
    private Long orderId;
    private Integer qty;
    private String status;

    @PostPersist
    public void onPostPersist(){
        try {
            Thread.currentThread().sleep((long) (800 + Math.random() * 220));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ApprovalObtained approvalObtained = new ApprovalObtained();
        BeanUtils.copyProperties(this, approvalObtained);
        approvalObtained.publishAfterCommit();


    }

    @PostUpdate
    public void onPostUpdate(){
        if(this.getStatus().equals("CANCELLED")) {
            CancelRequested cancelRequested = new CancelRequested();
            BeanUtils.copyProperties(this, cancelRequested);
            cancelRequested.publishAfterCommit();
        }

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Long equipmentId) {
        this.equipmentId = equipmentId;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




}
