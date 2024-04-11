package microservices.book.multiplication.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class MultiplicationResultAttempt {

        private final User user;
        private final Multiplication multiplication;
        private final int resultAttempt;
        //private final boolean correct;

        public MultiplicationResultAttempt() {
            user = null;
            multiplication = null;
            resultAttempt = -1;
            //correct = false;
        }
}
