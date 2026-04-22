package uz.silkStep.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.silkStep.project.domain.Guide;
import uz.silkStep.project.dto.request.GuideRequest;
import uz.silkStep.project.dto.response.GuideResponse;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.exception.CustomException;
import uz.silkStep.project.filter.BaseFilter;
import uz.silkStep.project.mapper.GuideMapper;
import uz.silkStep.project.repository.GuideRepository;
import uz.silkStep.project.service.GuideService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static uz.silkStep.project.exception.ExceptionType.GUIDE_NOT_FOUND;

/**
 * Created by: Diyora Alieva
 **/

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GuideServiceImpl implements GuideService {

    private final GuideRepository guideRepository;
    private final GuideMapper guideMapper;

    @Override
    public void create(GuideRequest request) {
        Guide guide = new Guide();
        guide.setNameRu(request.getNameRu());
        guide.setNameUz(request.getNameUz());
        guide.setNameEn(request.getNameEn());
        guide.setDescriptionEn(request.getDescriptionEn());
        guide.setDescriptionRu(request.getDescriptionRu());
        guide.setDescriptionUz(request.getDescriptionUz());
        guide.setRating(request.getRating());
        guide.setPricePerDay(request.getPricePerDay());
        guide.setAvailable(request.getAvailable());
        guide.setLanguages(request.getLanguages());
        guide.setIncludesItems(request.getIncludesItems());

        guideRepository.save(guide);
    }

    @Override
    public void update(UUID id, GuideRequest request) {
        Guide guide = guideRepository.findById(id).orElseThrow(() -> CustomException.of(GUIDE_NOT_FOUND));
        guide.setNameRu(request.getNameRu());
        guide.setNameUz(request.getNameUz());
        guide.setNameEn(request.getNameEn());
        guide.setDescriptionEn(request.getDescriptionEn());
        guide.setDescriptionRu(request.getDescriptionRu());
        guide.setDescriptionUz(request.getDescriptionUz());
        guide.setRating(request.getRating());
        guide.setPricePerDay(request.getPricePerDay());
        guide.setAvailable(request.getAvailable());
        guide.setLanguages(request.getLanguages());
        guide.setIncludesItems(request.getIncludesItems());

        guideRepository.save(guide);
    }

    @Override
    public GuideResponse findById(UUID id) {
        Guide guide = guideRepository.findById(id).orElseThrow(() -> CustomException.of(GUIDE_NOT_FOUND));
        return guideMapper.toResponse(guide);
    }

    @Override
    public PageableResponse<GuideResponse> findAll(BaseFilter filter) {
        Page<GuideResponse> responsePage = guideRepository.findAll(PageRequest.of(filter.getPage(), filter.getSize()))
                .map(guideMapper::toResponse);
        return guideMapper.toPageableResponse(responsePage);
    }

    @Override
    public List<GuideResponse> getAll() {
        return guideRepository.findAll()
                .stream()
                .map(guideMapper::toResponse)
                .collect(Collectors.toList());
    }
}
