import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

public class caseCaseSystemTester{

    @Test
    public void sortNameTester(){
        
        caseCashSystem caseCase = new caseCashSystem(); //initlialize system and students

        //test 1
        List<String> inputs = List.of("INIT, Tammy, 200",
                                        "INIT, Kim, 300",
                                        "INIT, Quyen, 400",
                                        "SORT, name");

        List<String> outputs = caseCase.runSimulation(inputs);

        List<String> answers = List.of("true",
                                        "true",
                                        "true",
                                        "[Kim, Quyen, Tammy]");

        assertArrayEquals(answers.toArray(), outputs.toArray());


        //test 2
        inputs = List.of("INIT, Sanji, 200", 
                            "INIT, Law, 400", 
                            "INIT, Zoro, 0", 
                            "INIT, Luffy, 0",
                            "SORT, name");

        
        answers = List.of("true",
                            "true",
                            "true",
                            "true",
                            "[Law, Luffy, Sanji, Zoro]");

        assertArrayEquals(answers.toArray(), outputs.toArray());

        //test 3
        inputs = List.of("INIT, Karissa, 800", 
                            "INIT, April, 100", 
                            "INIT, Trevor, 300", 
                            "INIT, Sheena, 0");

        
        answers = List.of("true",
                            "true",
                            "true",
                            "true",
                            "[April, Karissa, Sheena, Trevor]");

        assertArrayEquals(answers.toArray(), outputs.toArray());

    }

    @Test
    public void sortBalanceTester(){
        caseCashSystem caseCase = new caseCashSystem(); //initlialize system and students

        //test 1
        List<String> inputs = List.of("INIT, Tammy, 200",
                                        "INIT, Kim, 300",
                                        "INIT, Quyen, 400",
                                        "SORT, balance",
                                        "TRANSFER, Kim, Tammy, 100",
                                        "SORT, balance");

        List<String> outputs = caseCase.runSimulation(inputs);

        List<String> answers = List.of("true",
                                        "true",
                                        "true",
                                        "[Kim, Quyen, Tammy]",
                                        "[Tammy, Kim, Quyen]",
                                        "true",
                                        "[Kim, Quyen, Tammy]",
                                        "[Kim, Tammy, Quyen]");

        assertArrayEquals(answers.toArray(), outputs.toArray());

                //test 2
        inputs = List.of("INIT, Sanji, 200", 
                            "INIT, Law, 400", 
                            "INIT, Zoro, 0", 
                            "INIT, Luffy, 0",
                            "SORT, balance");

        
        answers = List.of("true",
                            "true",
                            "true",
                            "true",
                            "[Law, Luffy, Sanji, Zoro]");

        assertArrayEquals(answers.toArray(), outputs.toArray());

        //test 3
        inputs = List.of("INIT, Karissa, 800", 
                            "INIT, April, 100", 
                            "INIT, Trevor, 300", 
                            "INIT, Sheena, 0",
                            "SORT, balance");

        
        answers = List.of("true",
                            "true",
                            "true",
                            "true",
                            "[April, Karissa, Sheena, Trevor]");

        assertArrayEquals(answers.toArray(), outputs.toArray());

    }

}