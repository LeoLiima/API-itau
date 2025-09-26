package unicamp.itau.model;

import lombok.Data;
import lombok.AllArgsConstructor;

import lombok.Getter;




@Getter
//DTO
public class EstatisticaResponse {
    private int count;
    private double sum;
    private double avg;
    private double min;
    private double max;


    public EstatisticaResponse(int count, double sum, double avg, double min, double max){
        this.count = count;
        this.sum = sum;
        this.avg = avg;
        this.min = min;
        this.max = max;

    }

}

