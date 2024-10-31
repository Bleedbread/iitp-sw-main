package com.uracle.sample.api.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SampleService {

    @Autowired
    private SampleMapper sampleMapper;

    public int insertSample(Sample param) {
        int affectRow = 0;

        try {
            affectRow = sampleMapper.insertSample(param);
            logger.debug("INSERT affectRow: {}", affectRow);
        } catch (DuplicateKeyException e) {
            logger.warn("Duplicate Error!", e);
        }

        return affectRow;
    }

    public Sample getSampleById(Sample param) {
        Sample sample = sampleMapper.selectSampleById(param);

        if (sample == null) {
            sample = new Sample();
        }

        logger.debug("Sample: {}", sample);

        return sample;
    }

    public List<Sample> getSamples() {
        List<Sample> samples = sampleMapper.selectSamples();
        logger.debug("Sample count: {}", samples.size());

        samples.forEach(sample -> logger.debug(">>>> Sample: {}",sample));

        return samples;
    }

    public int updateSampleById(Sample param) {
        int affectRow = 0;

        if (param.getPassword().isEmpty() &&
                param.getUsername().isEmpty() &&
                param.getCompany().isEmpty()) {
            throw new IllegalArgumentException("Empty parameters not allowed");
        } else {
            affectRow = sampleMapper.updateSampleById(param);
        }
        logger.debug("UPDATE affectRow: {}", affectRow);

        return affectRow;
    }

    public int deleteSampleById(Sample param) {
        int affectRow = 0;

        affectRow = sampleMapper.updateSampleById(param);
        logger.debug("UPDATE affectRow: {}", affectRow);

        return affectRow;
    }
}
