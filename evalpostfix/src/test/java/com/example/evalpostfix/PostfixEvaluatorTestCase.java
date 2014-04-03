package com.example.evalpostfix;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PostfixEvaluatorTestCase {

    private static final double DELTA = 0.0001;

    private PostfixEvaluator eval;
    
    @Before
    public void setUp() {
        eval = new PostfixEvaluator();
    }

    @Test
    public void testStandard() {
        Assert.assertEquals(eval.evaluate("-3.2"), -3.2, DELTA);
        Assert.assertEquals(eval.evaluate("2 3 +"), 2 + 3, DELTA);
        Assert.assertEquals(eval.evaluate("2 3 -"), 2 - 3, DELTA);
        Assert.assertEquals(eval.evaluate("2.3 3 - 3 *"), (2.3 - 3) * 3, DELTA);
        Assert.assertEquals(eval.evaluate("2 3 / 1.2 - 7 *"), (2.0 / 3 - 1.2) * 7, DELTA);
        Assert.assertEquals(eval.evaluate("2 3 4.1 -2.4 / - *"), 2 * ( 3 - 4.1 / (-2.4)), DELTA);
        Assert.assertEquals(eval.evaluate("2 3 4 + * 7 -"), 2 * (3 + 4) - 7, DELTA);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testEmpty() {
        eval.evaluate("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoOperator() {
        eval.evaluate("2 3 4");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNotEnoughOperators() {
        eval.evaluate("2 3 4 /");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTooManyOperators() {
        eval.evaluate("2 3 4 / - *");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidNumber() {
        eval.evaluate("-2.3.");
    }

}
