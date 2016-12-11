package org.nd4j.parameterserver.distributed.messages.aggregations;

import lombok.Data;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.exception.ND4JIllegalStateException;
import org.nd4j.linalg.factory.Nd4j;

/**
 * @author raver119@gmail.com
 */
@Data
public class DotAggregation extends BaseAggregation{

    public DotAggregation(short aggregationWidth, INDArray scalar) {
        super(aggregationWidth);

        this.payload = scalar;
    }

    @Override
    public INDArray getAccumulatedResult() {
        INDArray stack = super.getAccumulatedResult();

        if (stack.isRowVector()) {
            return Nd4j.scalar(stack.sumNumber().doubleValue());
        } else {
            return stack.sum(1);
        }
    }
}
