package com.uracle.sample.api.sample;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SampleMapper {

    Integer insertSample(Sample sample);

    Sample selectSampleById(Sample sample);

    List<Sample> selectSamples();

    Integer updateSampleById(Sample sample);
}
