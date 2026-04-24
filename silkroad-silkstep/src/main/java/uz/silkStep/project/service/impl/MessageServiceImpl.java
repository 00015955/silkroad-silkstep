package uz.silkStep.project.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.silkStep.project.common.constants.MessageType;
import uz.silkStep.project.domain.Message;
import uz.silkStep.project.filter.BaseFilter;
import uz.silkStep.project.dto.request.MessageRequest;
import uz.silkStep.project.dto.response.MessageResponse;
import uz.silkStep.project.dto.response.PageableResponse;
import uz.silkStep.project.enums.Language;
import uz.silkStep.project.exception.ExceptionType;
import uz.silkStep.project.mapper.MessageMapper;
import uz.silkStep.project.repository.MessageRepository;
import uz.silkStep.project.service.MessageService;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

/// Created by: Diyora Alieva
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    /**
     * Resolves a localized message for the given {@link ExceptionType}.
     * <p>
     * This method attempts to retrieve a translated message based on the current system locale.
     * If a translation is not found in the persistence layer, the enum name itself is returned
     * as a fallback value.
     * <p>
     * The resolution process depends on the default JVM locale, which determines the language.
     *
     * @param type the exception type used as a message key
     * @return the localized message if available; otherwise, the enum name
     */
    @Override
    public String getMessage(ExceptionType type) {
        return messageRepository.findByMessageKeyAndLanguage(type.name(), Language.valueOf(Locale.getDefault().getLanguage()))
                .map(Message::getTranslation)
                .orElse(type.name());
    }

    /**
     * Resolves a localized message for the given {@link MessageType}.
     * <p>
     * Similar to exception-based message resolution, this method retrieves
     * a translation using the current system language. If no translation exists,
     * a fallback to the message key (enum name) is applied.
     *
     * @param type the message type used as a key
     * @return the localized message if found; otherwise, the enum name
     */
    @Override
    public String getMessage(MessageType type) {
        return messageRepository.findByMessageKeyAndLanguage(type.name(), Language.valueOf(Locale.getDefault().getLanguage()))
                .map(Message::getTranslation)
                .orElse(type.name());
    }

    /**
     * Retrieves a paginated list of message definitions across all supported message types.
     * <p>
     * This method aggregates message keys from both {@link ExceptionType} and {@link MessageType},
     * applies optional search filtering, and performs in-memory pagination.
     * <p>
     * For each message key, translations are resolved for all supported languages and
     * mapped into a unified {@link MessageResponse} DTO.
     * <p>
     * Note: Pagination is performed after combining and sorting enum values, not at database level.
     *
     * @param filter the {@link BaseFilter} containing pagination and optional search criteria
     * @return a {@link PageableResponse} containing localized message representations
     */
    @Override
    public PageableResponse<MessageResponse> findAll(BaseFilter filter) {
        List<MessageResponse> messageResponses = Stream.concat(Arrays.stream(ExceptionType.values()), Arrays.stream(MessageType.values()))
                .map(Enum::name)
                .filter(name -> StringUtils.isEmpty(filter.getSearch()) || name.toLowerCase().contains(filter.getSearch().toLowerCase()))
                .sorted()
                .skip((long) filter.getPage() * filter.getSize())
                .map(messageKey -> {
                    MessageResponse messageResponse = new MessageResponse();
                    messageResponse.setMessageKey(messageKey);
                    messageRepository.findByMessageKey(messageKey).forEach(message -> {
                                switch (message.getLanguage()) {
                                    case en -> messageResponse.setTranslationEn(message.getTranslation());
                                    case ru -> messageResponse.setTranslationRu(message.getTranslation());
                                    case uz -> messageResponse.setTranslationUz(message.getTranslation());
                                }
                            }
                    );
                    return messageResponse;
                })
                .limit(filter.getSize())
                .toList();

        long totalElements = ExceptionType.values().length + MessageType.values().length;
        int totalPages = (int) (totalElements % filter.getSize() != 0 ? totalElements / filter.getSize() + 1 : totalElements / filter.getSize());
        boolean hasNextPage = totalPages > filter.getPage();

        return messageMapper.toPageable(totalElements, totalPages, hasNextPage, messageResponses);
    }

    /**
     * Creates or updates message translations for all supported languages.
     * <p>
     * For each {@link Language}, this method either updates an existing message entry
     * or creates a new one if it does not exist.
     * <p>
     * The operation ensures that all language variants (e.g., EN, RU, UZ) are synchronized
     * for the given message key.
     *
     * @param request the {@link MessageRequest} containing message key and translations
     */
    @Override
    @Transactional
    public void save(MessageRequest request) {
        Arrays.stream(Language.values()).forEach(language -> {
            Message messageEntity = messageRepository.findByMessageKeyAndLanguage(request.getMessageKey(), language).map(message -> {
                        String translation = getTranslation(request, language);
                        message.setTranslation(translation);
                        return message;
                    }
            ).orElseGet(() -> {
                String translation = getTranslation(request, language);
                return messageMapper.toEntity(request.getMessageKey(), language, translation);
            });
            messageRepository.save(messageEntity);
        });
    }

    /**
     * Extracts the appropriate translation value from the request based on the given language.
     * <p>
     * This utility method centralizes language-based field selection, ensuring consistency
     * when mapping request data to entity fields.
     *
     * @param request the {@link MessageRequest} containing translation values
     * @param language the target {@link Language}
     * @return the translation string corresponding to the specified language
     */
    private static String getTranslation(MessageRequest request, Language language) {
        return switch (language) {
            case en -> request.getTranslationEn();
            case ru -> request.getTranslationRu();
            case uz -> request.getTranslationUz();
        };
    }
}

//     * Retrieves a localized message for the given {@link MessageType}.