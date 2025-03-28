package org.study.admin.ui.dto.posts;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.study.common.utils.TimeCalculator;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetPostTableResponseDto {

    private Long postId;
    private Long userId;
    private String userName;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public String getContent() {
        if (content.length() > 10) {
            return content.substring(0, 10) + "...";
        }
        return content;
    }

    public String getCreatedAt() {
        return TimeCalculator.getFormattedDate(createdAt);
    }

    public String getUpdatedAt() {
        return TimeCalculator.getFormattedDate(createdAt);
    }
}
