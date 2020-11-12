package android.wawakidss.musicaltheoryquiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Question {

    private int questionResId;
    private int answerResId;
    private int correctButtonId;
}
