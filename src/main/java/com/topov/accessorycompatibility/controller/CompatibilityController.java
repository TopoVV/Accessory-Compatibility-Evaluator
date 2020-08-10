package com.topov.accessorycompatibility.controller;

import com.topov.accessorycompatibility.dto.CompatibilityResultDto;
import com.topov.accessorycompatibility.dto.request.HardwareSpecificationSources;
import com.topov.accessorycompatibility.compatibility.evaluation.CompatibilityResult;
import com.topov.accessorycompatibility.dto.response.CompatibilityResponse;
import com.topov.accessorycompatibility.service.HardwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CompatibilityController {
    private final HardwareService hardwareService;

    @Autowired
    public CompatibilityController(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }

    @ResponseBody
    @PostMapping("/compatibility")
    public ResponseEntity<CompatibilityResponse> compatibilityGet(@RequestBody HardwareSpecificationSources sources) {
        final List<CompatibilityResultDto> compatibilityResults = hardwareService.evaluateHardwareCompatibility(sources);
        return ResponseEntity.ok(new CompatibilityResponse(compatibilityResults));
    }
}
