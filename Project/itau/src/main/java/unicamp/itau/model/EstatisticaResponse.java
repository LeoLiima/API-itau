package unicamp.itau.model;

import java.util.DoubleSummaryStatistics;

import lombok.Getter;

@Getter
//DTO
public class EstatisticaResponse {
    private final long count;
    private final double sum;
    private final double avg;
    private final double min;
    private final double max;


   public EstatisticaResponse(DoubleSummaryStatistics estatistica){
        this.count = estatistica.getCount();
        this.sum = estatistica.getSum();
        this.avg = estatistica.getAverage();
        this.min = estatistica.getMin();
        this.max = estatistica.getMax();
   }

}

