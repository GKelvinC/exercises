package visual.nuts.exercises;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Exercise1 {
    public static void main(String[] args){
        log.info("Welcome to Exercise 1");
        for (int x = 1; x<= 500; x++){
            if(numberIsDivisibleBy3(x)&&numberIsDivisibleBy5(x)){
                log.info("Visual Nuts");
            } else if (numberIsDivisibleBy3(x)) {
                log.info("Visual");
            } else if (numberIsDivisibleBy5(x)) {
                log.info("Nuts");
            }else{
                log.info(String.valueOf(x));
            }
        }
    }

    private static boolean numberIsDivisibleBy3(int x){
        return isDivisible(x,3);
    }
    private static boolean numberIsDivisibleBy5(int x){
        return isDivisible(x,5);
    }
    private static boolean isDivisible(int x, int byNumber){
        return (x % byNumber) == 0;
    }

}
