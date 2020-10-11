package equipmgnt;

import equipmgnt.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }
    @Autowired
    ApprovalRepository approvalRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderCanceled_Requestcancel(@Payload OrderCanceled orderCanceled){

        if(orderCanceled.isMe()){
            System.out.println("##### listener Requestcancel : " + orderCanceled.toJson());


            Optional<Approval> approvalrOptional = approvalRepository.findById(orderCanceled.getId());

            Approval approval = approvalrOptional.get();


            approval.setStatus("CANCELLED");

            approvalRepository.save(approval);
        }
    }

}
