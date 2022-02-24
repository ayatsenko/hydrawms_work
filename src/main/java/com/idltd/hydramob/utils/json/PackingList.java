package com.idltd.hydramob.utils.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.idltd.hydramob.utils.FlexibleFloatDeserializer;

import javax.persistence.Id;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PackingList {

    @Id
    @JsonProperty(value="Box")
    public String box_num;

    @JsonProperty(value="Count")
    public Long bc_count;

    @JsonDeserialize(using = FlexibleFloatDeserializer.class)
    @JsonProperty(value="Lenght")
    public Float box_lenght;

    @JsonDeserialize(using = FlexibleFloatDeserializer.class)
    @JsonProperty(value="Width")
    public Float box_width_p;

    @JsonDeserialize(using = FlexibleFloatDeserializer.class)
    @JsonProperty(value="Height")
    public Float box_height_p;

    @JsonDeserialize(using = FlexibleFloatDeserializer.class)
    @JsonProperty(value="Weight")
    public Float box_weight_p;

    @JsonProperty(value="Description")
    public String bc_description;
}
