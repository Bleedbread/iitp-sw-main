package com.uracle.sample.api.sample;

import com.uracle.sample.support.MspUtil;
import com.uracle.sample.support.annotation.MSP;
import com.uracle.sample.support.protocol.MspResult;
import com.uracle.sample.support.protocol.MspStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@MSP
@RestController
@RequestMapping("/sample")
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @PostMapping("")
    public ResponseEntity<MspResult> addSample(@RequestBody Sample sample) {
        MspResult result;

        int affectRow = sampleService.insertSample(sample);

        if (affectRow > 0) {
            result = MspUtil.makeResult(MspStatus.OK, sample);
        } else {
            result = MspUtil.makeResult("5001", "사용자 등록 오류 발생", sample);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MspResult> getSample(@PathVariable String id) {
        MspResult result;

        Sample param = new Sample();
        param.setId(id);
        Sample body = sampleService.getSampleById(param);

        if( body != null && body.getSeq() != null)
        {
            result = MspUtil.makeResult(MspStatus.OK, body);
        }
        else
        {
            result = MspUtil.makeResult("4001", "등록된 사용자가 없음", body);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<MspResult> getSamples()
    {
        MspResult result;

        List<Sample> body = sampleService.getSamples();

        if(!body.isEmpty())
        {
            result = MspUtil.makeResult(MspStatus.OK, body);
        }
        else
        {
            result = MspUtil.makeResult("4002", "등록된 사용자가 하나도 없음", body);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<MspResult> modSampleByID(@PathVariable("id") String id, @RequestBody Sample sample) {
        MspResult result;

        // 입력값 검증
        if (id.isEmpty()) {
            result = MspUtil.makeResult("4002", "ID가 빈 값은 허용하지 않음", sample);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        sample.setId(id);
        int affectRow = sampleService.updateSampleById(sample);
        if (affectRow > 0) {
            result = MspUtil.makeResult(MspStatus.OK, sample);
        } else {
            result = MspUtil.makeResult("4003", "업데이트할 사용자가 없거나 수정할 값이 없음", sample);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}/delete")
    public ResponseEntity<MspResult> delSample(@PathVariable String id) {
        MspResult result;

        Sample param = new Sample();
        param.setId(id);
        int affectRow = sampleService.deleteSampleById(param);

        if(affectRow > 0)
        {
            result = MspUtil.makeResult(MspStatus.OK, null);
        }
        else
        {
            result = MspUtil.makeResult("4004", "삭제할 사용자가 없음", null);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
