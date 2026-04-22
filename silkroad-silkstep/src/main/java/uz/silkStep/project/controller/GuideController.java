package uz.silkStep.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.silkStep.project.dto.request.GuideRequest;
import uz.silkStep.project.dto.response.GuideResponse;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.filter.BaseFilter;
import uz.silkStep.project.service.GuideService;

import java.util.UUID;

@RestController
@RequestMapping("/v1/guides")
@RequiredArgsConstructor
public class GuideController {

    private final GuideService guideService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void save(@RequestBody GuideRequest request) {
        guideService.create(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable UUID id, @RequestBody GuideRequest request) {
        guideService.update(id, request);
    }

    @GetMapping
    public ResponseEntity<PageableResponse<GuideResponse>> findAll(BaseFilter filter) {
        return ResponseEntity.ok(guideService.findAll(filter));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuideResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(guideService.findById(id));
    }
}
