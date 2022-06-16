package com.forschungsprojekt.persister.data;

import java.io.Serializable;
import java.math.BigDecimal;

public class PileEvaluationResult implements Serializable {
    public String caption;
    public BigDecimal left;
    public BigDecimal top;
    public BigDecimal right;
    public BigDecimal bottom;
}
