package com.simile.plan.janusgraph.api.model;

/**
 * @author yitao
 * @since 2020-07-27
 */
public class JanusGraphProperty {
    private String keyName;
    private String cardinality;
    private String dataType;

    public JanusGraphProperty() {
    }

    public JanusGraphProperty(String keyName, String cardinality, String dataType) {
        this.keyName = keyName;
        this.cardinality = cardinality;
        this.dataType = dataType;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getCardinality() {
        return cardinality;
    }

    public void setCardinality(String cardinality) {
        this.cardinality = cardinality;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
